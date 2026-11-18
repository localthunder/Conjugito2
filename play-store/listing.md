# Google Play listing: Hablito

Paste each section into Play Console → Grow users → Store presence → Main store listing.
Limits: title 30, short description 80, full description 4000, release notes 500.

## App name (30 max)

```
Hablito: Spanish Conjugation
```

## Short description (80 max)

```
Practise 637 Spanish verbs in 15 tenses, with audio. No Spanish keyboard needed.
```

## Full description (4000 max)

```
Hablito turns Spanish verb conjugation into quick, focused practice. You get a verb, a tense and a pronoun, then type the answer. That's it. No streaks to protect, no ads, no account, just the repetition that makes conjugations stick.

★ 637 SPANISH VERBS
Every common verb you'll need, from ser, estar, tener and ir to hundreds of everyday regular and irregular verbs. Turn on reflexive verbs (levantarse, acostarse) and less common verbs when you're ready for more.

★ 15 TENSES AND MOODS
Practise all the main indicative, subjunctive and imperative forms, or pick just the ones you're working on:
• Present
• Preterite
• Imperfect
• Future
• Conditional
• Present perfect
• Pluperfect
• Preterite perfect (past anterior)
• Future perfect
• Conditional perfect
• Present subjunctive
• Imperfect subjunctive (-ra endings)
• Imperfect subjunctive (-se endings)
• Imperative
• Negative imperative

★ HEAR EVERY CONJUGATION
Over 56,000 audio clips let you hear each verb and its conjugations spoken aloud, so you learn how they sound as well as how they're spelled.

★ NO SPANISH KEYBOARD? NO PROBLEM
Type "subirian" and Hablito accepts it, then shows you the correct spelling, "subirían". Accents and ñ never slow you down, and you still see the correct spelling every time.

★ KNOW WHEN TO USE EACH TENSE
Unsure about the imperfect or the subjunctive? Tap the colour-coded tense label for a short explanation of when it's used, with examples.

★ A FULL CONJUGATION REFERENCE
Browse complete conjugation tables for every verb. Search in Spanish ("tener") or in English ("to have") to find the verb you need.

★ STUCK? TAP "SHOW ME"
See the answer, learn it, and move on. Practice should help you learn, not just test you.

★ MADE FOR FOCUSED STUDY
• Works offline, so you can practise on the bus, on a plane or anywhere else
• Light and dark mode
• Free, with no ads and no sign-up

Whether you're starting out with the present tense or finally getting to grips with the subjunctive, Hablito helps you practise the verbs and tenses you need, in short sessions that fit your day.

¡Vamos a practicar!
```

## Release notes / What's new (500 max)

Draft: remove any line that isn't new in this release.

```
• Hear it: audio pronunciation for verbs and conjugations
• Updated app icon
• Fixed accents on pronoun labels (tú, él) in conjugation tables
• Performance and stability improvements
```

## Graphics

| Asset | File | Spec |
|---|---|---|
| App icon | `app/src/main/ic_launcher-playstore.png` | 512×512 PNG |
| Feature graphic | `play-store/feature-graphic.png` | 1024×500 |
| Phone screenshots | `play-store/screenshots/01–07.png` | 1080×1920 (9:16) |

Upload the screenshots in numbered order. The first three show on the listing before anyone scrolls, so they carry the core pitch: practice, accent-free typing, tense explanations.

To edit or regenerate them, change the captions in `source/screenshot.html` (and `source/feature-graphic.html`), then render with headless Chrome:

```bash
cd play-store/source
C="/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
for n in 1 2 3 4 5 6 7; do
  "$C" --headless=new --hide-scrollbars --force-device-scale-factor=1 --virtual-time-budget=5000 \
    --window-size=1080,1920 --screenshot="$PWD/../screenshots/0$n.png" "file://$PWD/screenshot.html?n=$n"
done
"$C" --headless=new --hide-scrollbars --force-device-scale-factor=1 --virtual-time-budget=5000 \
  --window-size=1024,500 --screenshot="$PWD/../feature-graphic.png" "file://$PWD/feature-graphic.html"
```

`source/raw-*.png` are the unedited captures from a Pixel 4 emulator (1080×2280).

## Other Play Console settings worth changing

- **Category:** keep *Education*. Add the tags *Language learning* and *Education* under Store settings → Tags.
- **Store listing experiments:** A/B test the first screenshot and the short description. With only a few installs a week, run each test for at least 4 weeks.
- **Custom listing for Spanish-speaking markets:** not needed. The audience is English speakers learning Spanish, so the en-US listing serves every country. Add an **en-GB** translation (a copy of this one) so UK users see the same spelling.
- **Reply to every review.** Play shows developer replies, and they help conversion when an app has few reviews.
- **Contact details:** add a website or a simple privacy page if you have one. Listings with them look more trustworthy.
