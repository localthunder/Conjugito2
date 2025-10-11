package com.conjugito.conjugito

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.conjugito.conjugito.playStoreManager.InAppUpdateManager
import com.conjugito.conjugito.ui.theme.MyApplicationTheme
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.review.ReviewManagerFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MainActivity : ComponentActivity() {
    companion object {
        const val AUDIO_VERSION_KEY = "audio_version_key"
        const val CURRENT_AUDIO_VERSION = 1 // Increase this when you update your audio files
    }

    private lateinit var updateManager: InAppUpdateManager

    private val updateResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode != RESULT_OK) {
            Log.e("MainActivity", "Update flow failed! Result code: ${result.resultCode}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize updateManager BEFORE calling checkForAppUpdates()
        updateManager = InAppUpdateManager(this)

        setContent {
            val context = LocalContext.current
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    LoadingScreen(context = context, lifecycleCoroutineScope = lifecycleScope)
                }
            }
        }

        checkAndCopyAudioFiles()
        trackAppLaunchAndShowReview()
        checkForAppUpdates()
    }

    override fun onResume() {
        super.onResume()
        // Check for any updates that might have been downloaded while the app was paused
        if (::updateManager.isInitialized) {
            updateManager.resumeUpdates(updateResultLauncher)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up the update manager to prevent memory leaks
        if (::updateManager.isInitialized) {
            updateManager.cleanup()
        }
    }

    private fun checkForAppUpdates() {
        updateManager.checkForUpdates(
            onUpdateAvailable = { appUpdateInfo ->
                Log.d("MainActivity", "Update available: ${appUpdateInfo.availableVersionCode()}")

                val updateType = if (appUpdateInfo.updatePriority() >= InAppUpdateManager.HIGH_PRIORITY_UPDATE) {
                    AppUpdateType.IMMEDIATE
                } else {
                    AppUpdateType.FLEXIBLE
                }

                updateManager.startUpdate(
                    appUpdateInfo = appUpdateInfo,
                    updateType = updateType,
                    activityResultLauncher = updateResultLauncher,
                    allowAssetPackDeletion = true
                )
            },
            onNoUpdateAvailable = {
                Log.d("MainActivity", "No updates available")
            }
        )
    }

    private fun checkAndCopyAudioFiles() {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val storedVersion = sharedPreferences.getInt(AUDIO_VERSION_KEY, 0)

        if (storedVersion < CURRENT_AUDIO_VERSION) {
            // Logic to copy your audio files to internal storage
            copyAudioFiles()

            // Update the stored version in SharedPreferences
            sharedPreferences.edit().putInt(AUDIO_VERSION_KEY, CURRENT_AUDIO_VERSION).apply()
        }
    }

    private fun copyAudioFiles() {
        try {
            copyAssetFolder(
                assets,
                "audio",
                filesDir.absolutePath + "/audio"
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun copyAssetFolder(
        assetManager: AssetManager,
        fromAssetPath: String,
        toPath: String
    ): Boolean {
        val files = assetManager.list(fromAssetPath) ?: return false
        var success = true

        if (files.isEmpty()) {
            success = copyAsset(assetManager, fromAssetPath, toPath)
        } else {
            val targetDir = File(toPath)
            if (!targetDir.exists() && !targetDir.mkdirs()) {
                throw IOException("Cannot create dir: $toPath")
            }

            for (file in files) {
                success = success and copyAssetFolder(
                    assetManager,
                    "$fromAssetPath/$file",
                    "$toPath/$file"
                )
            }
        }

        return success
    }

    @Throws(IOException::class)
    private fun copyAsset(
        assetManager: AssetManager,
        fromAssetPath: String,
        toPath: String
    ): Boolean {
        var `in`: InputStream? = null
        var out: OutputStream? = null
        try {
            `in` = assetManager.open(fromAssetPath)
            File(toPath).createNewFile()
            out = FileOutputStream(toPath)
            copyFile(`in`, out)
            `in`.close()
            `in` = null
            out.flush()
            out.close()
            out = null
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }

    private fun trackAppLaunchAndShowReview() {
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Get and increment launch count
        var launchCount = sharedPreferences.getInt("launchCount", 0)
        launchCount++
        editor.putInt("launchCount", launchCount)
        editor.apply()
        Log.e("Launch count", "Launch count = $launchCount")

        // Trigger review flow on the 3rd launch
        if (launchCount == 2) {
            val reviewManager = ReviewManagerFactory.create(this)
            val request = reviewManager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviewInfo = task.result
                    if (reviewInfo != null) {
                        Log.e("Review", "Review flow requested successfully")
                        val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                        flow.addOnCompleteListener {
                            Log.e("Review", "Review flow completed")
                        }
                    } else {
                        Log.e("Review", "ReviewInfo is null")
                    }
                } else {
                    Log.e("Review", "Review flow request failed: ${task.exception}")
                }
            }

            // Optionally reset the count
            editor.putInt("launchCount", 0)
            editor.apply()
        }
    }
}