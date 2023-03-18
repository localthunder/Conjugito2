package com.conjugito.conjugito.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Verb(
    @PrimaryKey(autoGenerate = false)
    val infinitive: String,
    val gerund: String,
    val pastParticiple: String,
    val irregular: Boolean,
    val reflexive: Boolean,
    val common: Boolean,
    var englishInfinitive: String,
) {
    fun doesMatchSearchQuery(query: String) : Boolean {
        val matchingQueries = listOf(
            "$infinitive",
            "$englishInfinitive"
        )
        return matchingQueries.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

val immutableListOfVerbForms = listOf(
        "yo",
        "tú",
        "él",
        "nosostros",
        "vosotros",
        "ellos"
)

val listOfTensesStrings = mutableListOf(
    "presentTense",
    "preteriteTense",
    "futureTense",
    "imperfectTense",
    "conditionalTense",
    "presentPerfectTense",
    "preteritePerfectTense",
    "futurePerfectTense",
    "conditionalPerfectTense",
    "pluperfectTense",
    "imperativeTense",
    "negativeImperativeTense",
    "presentSubjunctiveTense",
    "imperfectSubjunctiveTenseRa",
    "imperfectSubjunctiveTenseSe"
)

val presentTenseDescription = "Refers to actions or states that are currently happening or true in the present moment. For example, \"I am eating breakfast\" or \"She speaks Spanish.\""
val preteriteTenseDescription = "Refers to actions or events that happened in the past and are completed. It is often used to describe a specific moment or duration of time in the past. For example, \"I ate breakfast this morning\" or \"He went to the store yesterday.\""
val futureTenseDescription = "Refers to actions or events that will happen in the future. It is often used to make predictions or express plans. For example, \"I will eat breakfast tomorrow\" or \"They are going to the beach next weekend.\""
val imperfectTenseDescription = "Refers to actions or states that were ongoing or repeated in the past. It is often used to describe background information or to set the scene. For example, \"I used to eat cereal for breakfast every day\" or \"She would always go for a walk after dinner.\""
val conditionalTenseDescription = "Refers to actions that would happen if a certain condition were met. It is often used to express hypothetical situations or make polite requests. For example, \"If I had more time, I would read a book\" or \"Could you pass me the salt, please?\""
val presentPerfectTenseDescription = "Refers to actions or events that happened at an unspecified time in the past but have a connection to the present. It is often used to talk about experiences or accomplishments. For example, \"I have eaten breakfast already\" or \"She has visited Europe several times.\""
val preteritePerfectTenseDescription = "Refers to actions or events that happened at a specific time in the past and have a connection to the present. It is often used to talk about recent experiences or actions. For example, \"I just finished eating breakfast\" or \"He has just returned from vacation.\""
val futurePerfectTenseDescription = "Refers to actions or events that will have been completed at a certain point in the future. It is often used to talk about expectations or plans for the future. For example, \"By this time next year, I will have graduated from college\" or \"They will have been married for 20 years next month.\""
val conditionalPerfectTenseDescription = "Refers to actions that would have happened if a certain condition had been met in the past. It is often used to talk about missed opportunities or regrets. For example, \"If I had studied harder, I would have passed the test\" or \"He would have been on time if he hadn't missed the train.\""
val pluperfectTenseDescription = "Refers to actions or states that were completed before another past action or event. It is often used to describe the background or history of a story. For example, \"I had eaten breakfast before I went to the gym\" or \"They had been married for five years before they had children.\""
val imperativeTenseDescription = "Used to give commands or make requests. It is often used in informal situations or when speaking to someone familiar. For example, \"Pass me the salt\" or \"Come here.\""
val negativeImperativeTenseDescription = "Used to give negative commands or make negative requests. It is often used to express prohibitions or to give warnings. For example, \"Don't touch that\" or \"Don't be late.\""
val presentSubjunctiveTenseDescription = "Used to express doubt, uncertainty, or hypothetical situations in the present. It is often used after certain verbs or expressions that indicate doubt or emotion. For example, \"It's important that he be on time\" or \"I doubt"
val imperfectSubjunctiveRaTenseDescription = "Used to express hypothetical or contrary-to-fact situations in the past. It is formed by taking the third person plural (ellos/ellas) preterite form of the verb, dropping the -ron ending, and adding the appropriate subjunctive endings (-ra, -ras, -ra, -ramos, -rais, -ran). This tense is often used in the same situations as the imperfect subjunctive Se, but the Ra form is more common in written Spanish."
val imperfectSubjunctiveSeTenseDescription = "Used to express hypothetical or contrary-to-fact situations in the past. It is formed by taking the third person plural (ellos/ellas) preterite form of the verb, dropping the -ron ending, and adding the appropriate subjunctive endings (-se, -ses, -se, -semos, -seis, -sen). This tense is often used after certain conjunctions (such as como si, aunque, or como) and can also express wishes, doubts, or desires. It is more commonly used in spoken Spanish than the imperfect subjunctive Ra."


val tenseDescriptions = mapOf(
    "presentTense" to presentTenseDescription,
    "preteriteTense" to preteriteTenseDescription,
    "futureTense" to futureTenseDescription,
    "imperfectTense" to imperfectTenseDescription,
    "conditionalTense" to conditionalTenseDescription,
    "presentPerfectTense" to presentPerfectTenseDescription,
    "preteritePerfectTense" to preteriteTenseDescription,
    "futurePerfectTense" to futurePerfectTenseDescription,
    "conditionalPerfectTense" to conditionalPerfectTenseDescription,
    "pluperfectTense" to pluperfectTenseDescription,
    "imperativeTense" to imperativeTenseDescription,
    "negativeImperativeTense" to negativeImperativeTenseDescription,
    "presentSubjunctiveTense" to presentSubjunctiveTenseDescription,
    "imperfectSubjunctiveTenseRa" to imperfectSubjunctiveRaTenseDescription,
    "imperfectSubjunctiveTenseSe" to imperfectSubjunctiveSeTenseDescription
)

val abandonar = Verb("abandonar", "abandonando", "abandonado", false, false, true, "to abandon, leave behind, desert; to quit, give up")
val abordar = Verb("abordar", "abordando", "abordado", false, false, false, "to board, get on [plane, bus, etc.]; to approach, accost [a person]")
val abortar = Verb("abortar", "abortando", "abortado", false, false, false, "to abort, cause to miscarry; to have a miscarriage")
val abrazar = Verb("abrazar", "abrazando", "abrazado", true, false, false, "to hug, embrace")
val abrir = Verb("abrir", "abriendo", "abierto", true, false, true, "to open")
val aburrir = Verb("aburrir", "aburriendo", "aburrido", false, false, false, "to bore; to tire, weary")
val aburrirse = Verb("aburrirse", "aburriéndose", "aburrido", false, true, true, "to get bored, become bored, be bored")
val abusar = Verb("abusar", "abusando", "abusado", false, false, false, "to go too far, take advantage")
val acabar = Verb("acabar", "acabando", "acabado", false, false, false, "to finish, end")
val acampar = Verb("acampar", "acampando", "acampado", false, false, false, "to camp, encamp, go camping")
val aceptar = Verb("aceptar", "aceptando", "aceptado", false, false, false, "to accept, approve; to agree to")
val acercar = Verb("acercar", "acercando", "acercado", true, false, false, "to bring near[er], move [something] nearer")
val acercarse = Verb("acercarse", "acercándose", "acercado", false, true, false, "to approach")
val acompañar = Verb("acompañar", "acompañando", "acompañado", false, false, false, "to accompany, go with, escort")
val aconsejar = Verb("aconsejar", "aconsejando", "aconsejado", false, false, false, "to advise")
val acontecer = Verb("acontecer", "aconteciendo", "acontecido", true, false, false, "to happen, occur, come about")
val acordar = Verb("acordar", "acordando", "acordado", true, false, true, "to decide, resolve, agree [on]")
val acordarse = Verb("acordarse", "acordándose", "acordado", true, true, false, "to remember")
val acortar = Verb("acortar", "acortando", "acortado", false, false, false, "to shorten")
val acostar = Verb("acostar", "acostando", "acostado", true, false, true, "to put to bed")
val acostarse = Verb("acostarse", "acostándose", "acostado", true, true, false, "to go to bed, lie down")
val acostumbrar = Verb("acostumbrar", "acostumbrando", "acostumbrado", false, false, false, "to be accustomed to, be in the habit of; to get")
val acostumbrarse = Verb("acostumbrarse", "acostumbrándose", "acostumbrado", false, true, true, "to get used to, get accustomed to")
val actuar = Verb("actuar", "actuando", "actuado", true, false, false, "to act, perform, actuate, operate")
val adivinar = Verb("adivinar", "adivinando", "adivinado", false, false, false, "to guess, fortell, prophesy; to guess correctly")
val admirar = Verb("admirar", "admirando", "admirado", false, false, false, "to admire, respect, look up to")
val admitir = Verb("admitir", "admitiendo", "admitido", false, false, false, "to admit, accept, allow, recognize")
val adorar = Verb("adorar", "adorando", "adorado", false, false, false, "to adore, worship")
val adornar = Verb("adornar", "adornando", "adornado", false, false, false, "to adorn; to decorate, embellish; to garnish [food]")
val advertir = Verb("advertir", "advirtiendo", "advertido", true, false, false, "to notice, observe, advise, warn")
val afeitar = Verb("afeitar", "afeitando", "afeitado", false, false, false, "to shave")
val afeitarse = Verb("afeitarse", "afeitándose", "afeitado", false, true, false, "to shave [oneself]")
val afirmar = Verb("afirmar", "afirmando", "afirmado", false, false, false, "to make firm, steady, strengthen; to affirm, state, assert")
val afligir = Verb("afligir", "afligiendo", "afligido", true, false, false, "to afflict, to grieve, to pain, to distress")
val agorar = Verb("agorar", "agorando", "agorado", true, false, false, "to predict, prophesy")
val agradar = Verb("agradar", "agradando", "agradado", false, false, false, "to please, be pleasing")
val agradecer = Verb("agradecer", "agradeciendo", "agradecido", true, false, false, "to be thankful for")
val aguantar = Verb("aguantar", "aguantando", "aguantado", false, false, false, "to put up with, endure, bear, stand")
val ahorcar = Verb("ahorcar", "ahorcando", "ahorcado", true, false, false, "to hang")
val ahorrar = Verb("ahorrar", "ahorrando", "ahorrado", false, false, false, "to save")
val alcanzar = Verb("alcanzar", "alcanzando", "alcanzado", true, false, false, "to reach, catch, catch up to, catch up with")
val alegrar = Verb("alegrar", "alegrando", "alegrado", false, false, false, "to make happy, cheer [up], gladden")
val alegrarse = Verb("alegrarse", "alegrándose", "alegrado", false, true, false, "to be glad, happy; to become/get happy; to rejoice")
val alentar = Verb("alentar", "alentando", "alentado", true, false, false, "to encourage, cheer, inspire, bolster up")
val aliviar = Verb("aliviar", "aliviando", "aliviado", false, false, false, "to alleviate, ease, lessen, lighthen, relieve")
val almorzar = Verb("almorzar", "almorzando", "almorzado", true, false, true, "to lunch, eat lunch, have lunch")
val alquilar = Verb("alquilar", "alquilando", "alquilado", false, false, true, "to rent; to rent out, let")
val amanecer = Verb("amanecer", "amaneciendo", "amanecido", true, false, false, "to dawn")
val amar = Verb("amar", "amando", "amado", false, false, true, "to love")
val amenazar = Verb("amenazar", "amenazando", "amenazado", true, false, false, "to threaten, menace")
val añadir = Verb("añadir", "añadiendo", "añadido", false, false, false, "to add; to increase")
val andar = Verb("andar", "andando", "andado", true, false, true, "to walk, go")
val anhelar = Verb("anhelar", "anhelando", "anhelado", false, false, false, "to be eager for/to, long for/to, yearn for/to")
val anunciar = Verb("anunciar", "anunciando", "anunciado", false, false, false, "to announce")
val apagar = Verb("apagar", "apagando", "apagado", true, false, true, "to extinguish, put out, turn off")
val aparecer = Verb("aparecer", "apareciendo", "aparecido", true, false, false, "to appear")
val aplaudir = Verb("aplaudir", "aplaudiendo", "aplaudido", false, false, false, "to applaud, cheer, clap")
val aplicar = Verb("aplicar", "aplicando", "aplicado", true, false, false, "to apply")
val apostar = Verb("apostar", "apostando", "apostado", true, false, false, "to bet, wager")
val apoyar = Verb("apoyar", "apoyando", "apoyado", false, false, false, "to support, hold up, prop up; to back")
val apreciar = Verb("apreciar", "apreciando", "apreciado", false, false, false, "to appreciate, value, esteem, estimate, notice")
val aprender = Verb("aprender", "aprendiendo", "aprendido", false, false, false, "to learn")
val apretar = Verb("apretar", "apretando", "apretado", true, false, false, "to be too tight; to squeeze; to tighten [up]; to press [down/against]")
val aprobar = Verb("aprobar", "aprobando", "aprobado", true, false, false, "to pass")
val arreglar = Verb("arreglar", "arreglando", "arreglado", false, false, true, "to arrange, settle, fix up, repair, tidy up")
val arrepentirse = Verb("arrepentirse", "arrepintiéndose", "arrepentido", true, true, false, "to repent, be repentant, regret")
val arrojar = Verb("arrojar", "arrojando", "arrojado", false, false, false, "to throw, hurl, cast, toss")
val asistir = Verb("asistir", "asistiendo", "asistido", false, false, false, "to attend")
val asociar = Verb("asociar", "asociando", "asociado", false, false, false, "to associate; to pool, put together")
val aspirar = Verb("aspirar", "aspirando", "aspirado", false, false, false, "to breath in, inhale; to suck in; to aspirate; to aspire")
val asustar = Verb("asustar", "asustando", "asustado", false, false, false, "to frighten, scare, startle")
val asustarse = Verb("asustarse", "asustándose", "asustado", false, true, false, "to be frightened [oneself]")
val atacar = Verb("atacar", "atacando", "atacado", true, false, false, "to attack")
val atender = Verb("atender", "atendiendo", "atendido", true, false, false, "to attend to, pay attention to")
val atraer = Verb("atraer", "atrayendo", "atraído", true, false, false, "to attract, draw, lure")
val atravesar = Verb("atravesar", "atravesando", "atravesado", true, false, false, "to cross, cross over, go across, go over, pass through")
val atreverse = Verb("atreverse", "atreviéndose", "atrevido", false, true, false, "to dare")
val aumentar = Verb("aumentar", "aumentando", "aumentado", false, false, false, "to increase, add to, augment; to be on the increase, rise")
val avanzar = Verb("avanzar", "avanzando", "avanzado", true, false, false, "to advance, move forward")
val averiguar = Verb("averiguar", "averiguando", "averiguado", false, false, false, "to find out, discover")
val avisar = Verb("avisar", "avisando", "avisado", false, false, false, "to warn, inform, notify")
val ayudar = Verb("ayudar", "ayudando", "ayudado", false, false, true, "to help")
val bailar = Verb("bailar", "bailando", "bailado", false, false, true, "d a n c e")
val bajar = Verb("bajar", "bajando", "bajado", false, false, false, "to lower, go down, descend, download")
val bañar = Verb("bañar", "bañando", "bañado", false, false, false, "to give a bath")
val bañarse = Verb("bañarse", "bañándose", "bañado", false, true, false, "to take a bath, bathe [oneself]")
val barrer = Verb("barrer", "barriendo", "barrido", false, false, false, "to sweep, sweep clean, sweep out, sweep away")
val batir = Verb("batir", "batiendo", "batido", false, false, false, "to beat, hammer, pound [on]")
val bautizar = Verb("bautizar", "bautizando", "bautizado", true, false, false, "to baptize, christen")
val beber = Verb("beber", "bebiendo", "bebido", false, false, true, "to drink")
val bendecir = Verb("bendecir", "bendiciendo", "bendito", true, false, false, "to bless, foretell, forecast")
val besar = Verb("besar", "besando", "besado", false, false, false, "k i s s")
val bordar = Verb("bordar", "bordando", "bordado", false, false, false, "to embroider")
val borrar = Verb("borrar", "borrando", "borrado", false, false, false, "to erase, rub out, to cross out, obliterate, wipe out")
val brillar = Verb("brillar", "brillando", "brillado", false, false, false, "to shine, sparkle, glitter, gleam")
val brindar = Verb("brindar", "brindando", "brindado", false, false, false, "to toast")
val broncearse = Verb("broncearse", "bronceándose", "bronceado", false, true, false, "to get a suntan, tan")
val bucear = Verb("bucear", "buceando", "buceado", false, false, false, "to skin-dive; to dive under water; to dive")
val burlar = Verb("burlar", "burlando", "burlado", false, false, false, "to deceive, trick; to seduce")
val burlarse = Verb("burlarse", "burlándose", "burlado", false, true, true, "to mock, ridicule, make fun of [someone/something]")
val buscar = Verb("buscar", "buscando", "buscado", true, false, true, "to search for, look for")
val caber = Verb("caber", "cabiendo", "cabido", true, false, true, "to fit")
val caer = Verb("caer", "cayendo", "caído", true, false, true, "to fall")
val calcular = Verb("calcular", "calculando", "calculado", false, false, false, "to calculate, compute, add up")
val calentar = Verb("calentar", "calentando", "calentado", true, false, true, "to heat [up], warm [up]")
val calentarse = Verb("calentarse", "calentándose", "calentado", true, true, false, "to heat up, warm up")
val callar = Verb("callar", "callando", "callado", false, false, false, "to keep quiet about, pass over in silence; to hush")
val callarse = Verb("callarse", "callándose", "callado", false, true, true, "to keep quiet , be quiet, shut up, be silent, remain silent")
val calmar = Verb("calmar", "calmando", "calmado", false, false, false, "to calm (down), quiet, soothe")
val calmarse = Verb("calmarse", "calmándose", "calmado", false, true, false, "to calm down [oneself]")
val caminar = Verb("caminar", "caminando", "caminado", false, false, true, "to walk, go")
val cancelar = Verb("cancelar", "cancelando", "cancelado", false, false, false, "to cancel; to wipe out, write off [debt]")
val cansar = Verb("cansar", "cansando", "cansado", false, false, false, "to tire, tire out, fatigue, wear out")
val cansarse = Verb("cansarse", "cansándose", "cansado", false, true, false, "to get tired, get worn out, get weary")
val caracterizar = Verb("caracterizar", "caracterizando", "caracterizado", true, false, false, "to characterize")
val cargar = Verb("cargar", "cargando", "cargado", true, false, false, "to load, load up; to charge")
val casar = Verb("casar", "casando", "casado", false, false, false, "to marry, join in marriage, join in wedlock")
val casarse = Verb("casarse", "casándose", "casado", false, true, false, "to marry [someone], get married [to someone]")
val castigar = Verb("castigar", "castigando", "castigado", true, false, false, "to punish, penalize, castigate")
val causar = Verb("causar", "causando", "causado", false, false, false, "to cause; to create, make")
val cazar = Verb("cazar", "cazando", "cazado", true, false, false, "to hunt, represent, stand for")
val celebrar = Verb("celebrar", "celebrando", "celebrado", false, false, false, "to celebrate; to praise, applaud")
val cenar = Verb("cenar", "cenando", "cenado", false, false, true, "to eat supper, have supper; to eat dinner, have dinner; to dine; to have for supper [or dinner]")
val censurar = Verb("censurar", "censurando", "censurado", false, false, false, "to censor; to censure, criticize, blame")
val cepillar = Verb("cepillar", "cepillando", "cepillado", false, false, false, "to brush")
val cerrar = Verb("cerrar", "cerrando", "cerrado", true, false, true, "to close, shut")
val cesar = Verb("cesar", "cesando", "cesado", false, false, false, "to cease, stop")
val charlar = Verb("charlar", "charlando", "charlado", false, false, false, "to chat, talk")
val chismear = Verb("chismear", "chismeando", "chismeado", false, false, false, "to gossip")
val chocar = Verb("chocar", "chocando", "chocado", true, false, false, "to shock; to startle; to be suprising, startling; to collide, crash")
val civilizar = Verb("civilizar", "civilizando", "civilizado", true, false, false, "to civilize")
val clarificar = Verb("clarificar", "clarificando", "clarificado", true, false, false, "to clarify, illuminate, light [up], brighten")
val clasificar = Verb("clasificar", "clasificando", "clasificado", true, false, false, "to classify, grade, rate, sort")
val cobrar = Verb("cobrar", "cobrando", "cobrado", false, false, false, "to charge [a price]; to cash [a check]; to collect, receive [an amount]")
val cocinar = Verb("cocinar", "cocinando", "cocinado", false, false, true, "to cook; to do the cooking")
val coger = Verb("coger", "cogiendo", "cogido", false, false, true, "to catch, grasp, take hold of")
val colgar = Verb("colgar", "colgando", "colgado", true, false, false, "to hang, hang up, be hanging, be suspended")
val colocar = Verb("colocar", "colocando", "colocado", true, false, false, "to locate, place")
val colonizar = Verb("colonizar", "colonizando", "colonizado", true, false, false, "to colonize, settle")
val combatir = Verb("combatir", "combatiendo", "combatido", false, false, false, "to combat, fight, oppose; to attack")
val comenzar = Verb("comenzar", "comenzando", "comenzado", true, false, true, "to begin, start, commence")
val comer = Verb("comer", "comiendo", "comido", false, false, true, "to eat")
val compartir = Verb("compartir", "compartiendo", "compartido", false, false, false, "to share; to divide [up]")
val competir = Verb("competir", "compitiendo", "competido", true, false, false, "to compete")
val componer = Verb("componer", "componiendo", "compuesto", true, false, false, "to compose, make up, put together")
val comprar = Verb("comprar", "comprando", "comprado", false, false, true, "to buy, purchase")
val comprender = Verb("comprender", "comprendiendo", "comprendido", false, false, false, "to understand, comprehend")
val comunicar = Verb("comunicar", "comunicando", "comunicado", true, false, false, "to communicate, transmit, tell, pass on")
val comunicarse = Verb("comunicarse", "comunicándose", "comunicado", true, true, false, "to communicate [e.g., with someone], be in touch, correspond; to be transmitted")
val condenar = Verb("condenar", "condenando", "condenado", false, false, false, "to condemn, convict, sentence")
val conducir = Verb("conducir", "conduciendo", "conducido", true, false, false, "to drive, conduct")
val confesar = Verb("confesar", "confesando", "confesado", true, false, false, "to confess")
val confiar = Verb("confiar", "confiando", "confiado", true, false, false, "to trust, be trusting, entrust")
val confirmar = Verb("confirmar", "confirmando", "confirmado", false, false, false, "to confirm, corroborate, endorse")
val confiscar = Verb("confiscar", "confiscando", "confiscado", true, false, false, "to confiscate")
val conjugar = Verb("conjugar", "conjugando", "conjugado", true, false, false, "to conjugate")
val conocer = Verb("conocer", "conociendo", "conocido", true, false, true, "to know")
val conquistar = Verb("conquistar", "conquistando", "conquistado", false, false, false, "to conquer, overcome, win")
val conseguir = Verb("conseguir", "consiguiendo", "conseguido", true, false, false, "to get, obtain")
val consentir = Verb("consentir", "consintiendo", "consentido", true, false, false, "to consent to; to allow, permit; to tolerate, put up with; to admit; to agree, consent")
val conservar = Verb("conservar", "conservando", "conservado", false, false, false, "to preserve, conserve; to keep, retain")
val consistir = Verb("consistir", "consistiendo", "consistido", false, false, false, "to consist [of], be composed [of]")
val constituir = Verb("constituir", "constituyendo", "constituido", false, false, false, "to constitute")
val construir = Verb("construir", "construyendo", "construido", false, false, false, "to build, construct")
val consumir = Verb("consumir", "consumiendo", "consumido", false, false, false, "to consume, eat, use upe")
val contaminar = Verb("contaminar", "contaminando", "contaminado", false, false, false, "to contaminate, pollute; to corrupt")
val contar = Verb("contar", "contando", "contado", true, false, true, "to count, relate, tell")
val contener = Verb("contener", "conteniendo", "contenido", true, false, false, "to contain, hold")
val contestar = Verb("contestar", "contestando", "contestado", false, false, true, "to answer")
val continuar = Verb("continuar", "continuando", "continuado", true, false, true, "to continue")
val contribuir = Verb("contribuir", "contribuyendo", "contribuido", false, false, false, "to contribute")
val controlar = Verb("controlar", "controlando", "controlado", false, false, false, "to control; to inspect, check")
val convencer = Verb("convencer", "convenciendo", "convencido", false, false, false, "to convince")
val convenir = Verb("convenir", "conviniendo", "convenido", true, false, false, "to agree; to suit, be suited to; to be good for")
val conversar = Verb("conversar", "conversando", "conversado", false, false, false, "to talk, converse; to tell, relate")
val convertir = Verb("convertir", "convirtiendo", "convertido", true, false, false, "to convert")
val convidar = Verb("convidar", "convidando", "convidado", false, false, false, "to invite")
val copiar = Verb("copiar", "copiando", "copiado", false, false, false, "to copy")
val corregir = Verb("corregir", "corrigiendo", "corregido", true, false, false, "to correct")
val correr = Verb("correr", "corriendo", "corrido", false, false, true, "to run")
val cortar = Verb("cortar", "cortando", "cortado", false, false, false, "to cut")
val coser = Verb("coser", "cosiendo", "cosido", false, false, false, "to sew, sew up; to stitch, stitch up")
val costar = Verb("costar", "costando", "costado", true, false, false, "to cost")
val crear = Verb("crear", "creando", "creado", false, false, false, "to create; to make")
val crecer = Verb("crecer", "creciendo", "crecido", true, false, false, "to grow [up]")
val creer = Verb("creer", "creyendo", "creído", true, false, true, "to believe")
val criar = Verb("criar", "criando", "criado", true, false, false, "to raise")
val criarse = Verb("criarse", "criándose", "criado", true, true, false, "to grow [up]; to be brought up")
val criticar = Verb("criticar", "criticando", "criticado", true, false, false, "to criticize")
val crucificar = Verb("crucificar", "crucificando", "crucificado", true, false, false, "to crucify")
val cruzar = Verb("cruzar", "cruzando", "cruzado", true, false, false, "to cross")
val cubrir = Verb("cubrir", "cubriendo", "cubierto", false, false, false, "to cover")
val cuidar = Verb("cuidar", "cuidando", "cuidado", false, false, true, "to take care of, look after")
val culpar = Verb("culpar", "culpando", "culpado", false, false, false, "to blame, accuse; to condemn")
val cultivar = Verb("cultivar", "cultivando", "cultivado", false, false, false, "to cultivate; to grow")
val cumplir = Verb("cumplir", "cumpliendo", "cumplido", false, false, false, "to fulfil, carry out; to reach [")
val curar = Verb("curar", "curando", "curado", false, false, false, "to cure; to treat, dress [a wound]")
val dar = Verb("dar", "dando", "dado", true, false, true, "to give")
val deber = Verb("deber", "debiendo", "debido", false, false, true, "to owe; must, should, ought to")
val decidir = Verb("decidir", "decidiendo", "decidido", false, false, false, "to decide, settle, resolve")
val decidirse = Verb("decidirse", "decidiéndose", "decidido", false, true, false, "to decide, make up one's mind")
val decir = Verb("decir", "diciendo", "dicho", true, false, true, "to say, tell")
val declarar = Verb("declarar", "declarando", "declarado", false, false, false, "to declare, state; to bid [in cards]")
val decorar = Verb("decorar", "decorando", "decorado", false, false, false, "to decorate, adorn")
val dedicar = Verb("dedicar", "dedicando", "dedicado", true, false, false, "to dedicate")
val dedicarse = Verb("dedicarse", "dedicándose", "dedicado", false, true, false, "to devote oneself, dedicate oneself, go in for, take up [carrer/interest in something]")
val defender = Verb("defender", "defendiendo", "defendido", true, false, false, "to defend")
val dejar = Verb("dejar", "dejando", "dejado", false, false, true, "to leave, abandon; to let, allow")
val demostrar = Verb("demostrar", "demostrando", "demostrado", true, false, false, "to demonstrate, show")
val depender = Verb("depender", "dependiendo", "dependido", false, false, false, "to depend")
val depositar = Verb("depositar", "depositando", "depositado", false, false, false, "to deposit; to place; to put away, store")
val deprimir = Verb("deprimir", "deprimiendo", "deprimido", false, false, false, "to depress, press down, flatten")
val derretir = Verb("derretir", "derritiendo", "derretido", true, false, false, "to melt, liquefy, thaw")
val desagradar = Verb("desagradar", "desagradando", "desagradado", false, false, false, "to displease, be displeasing")
val desagradecer = Verb("desagradecer", "desagradeciendo", "desagradecido", true, false, false, "to be unthankful for, to be unappreciative of")
val desaparecer = Verb("desaparecer", "desapareciendo", "desaparecido", true, false, false, "to disappear")
val desarrollar = Verb("desarrollar", "desarrollando", "desarrollado", false, false, false, "to develop, expand; to unroll, unwind; to unfold")
val desarrollarse = Verb("desarrollarse", "desarrollándose", "desarrollado", false, true, false, "to develop, evolve; to unwind")
val desayunar = Verb("desayunar", "desayunando", "desayunado", false, false, false, "to eat breakfast, have breakfast")
val descansar = Verb("descansar", "descansando", "descansado", false, false, true, "to rest, take a rest; to support, lean [on")
val descender = Verb("descender", "descendiendo", "descendido", true, false, false, "to descend, lower")
val describir = Verb("describir", "describiendo", "descrito", false, false, false, "to describe")
val descubrir = Verb("descubrir", "descubriendo", "descubierto", false, false, false, "to discover")
val desear = Verb("desear", "deseando", "deseado", false, false, false, "to desire, want, wish")
val deshacer = Verb("deshacer", "deshaciendo", "deshecho", true, false, true, "to undo, unmake, ruin, spoil, take apart")
val despedir = Verb("despedir", "despidiendo", "despedido", true, false, false, "to say goodbye to, show out, dismiss")
val despertar = Verb("despertar", "despertando", "despertado", true, false, false, "to awaken")
val despertarse = Verb("despertarse", "despertándose", "despertado", true, true, false, "to wake up, lie down")
val destruir = Verb("destruir", "destruyendo", "destruido", false, false, false, "to destroy")
val detener = Verb("detener", "deteniendo", "detenido", true, false, false, "to stop; to hold up, delay; to arrest, detain")
val detenerse = Verb("detenerse", "deteniéndose", "detenido", true, true, false, "to stop, pause, linger")
val detestar = Verb("detestar", "detestando", "detestado", false, false, false, "to detest, hate")
val devolver = Verb("devolver", "devolviendo", "devuelto", true, false, false, "to return, give back")
val devorar = Verb("devorar", "devorando", "devorado", false, false, false, "to devour, eat up, gobble up")
val dibujar = Verb("dibujar", "dibujando", "dibujado", false, false, false, "to draw, sketch, depict")
val dirigir = Verb("dirigir", "dirigiendo", "dirigido", true, false, false, "to direct")
val diseñar = Verb("diseñar", "diseñando", "diseñado", false, false, false, "to design")
val disfrutar = Verb("disfrutar", "disfrutando", "disfrutado", false, false, false, "to enjoy")
val disgustar = Verb("disgustar", "disgustando", "disgustado", false, false, false, "to displease, be displeasing; to annoy, upset")
val disminuir = Verb("disminuir", "disminuyendo", "disminuido", false, false, false, "to diminish, decrease")
val distinguir = Verb("distinguir", "distinguiendo", "distinguido", false, false, false, "to distinguish, discern, make out, recognize, single out")
val distribuir = Verb("distribuir", "distribuyendo", "distribuido", false, false, false, "to distribute, hand out, send out, give out")
val divertir = Verb("divertir", "divirtiendo", "divertido", true, false, false, "to amuse, entertain")
val divertirse = Verb("divertirse", "divirtiéndose", "divertido", true, true, false, "to have fun, have a good time, enjoy oneself")
val divorciar = Verb("divorciar", "divorciando", "divorciado", false, false, false, "to divorce")
val divorciarse = Verb("divorciarse", "divorciándose", "divorciado", false, true, false, "to get divorced [from somebody]")
val doblar = Verb("doblar", "doblando", "doblado", false, false, false, "to double; to turn [a corner, to the right, etc.]; to fold, crease")
val doler = Verb("doler", "doliendo", "dolido", true, false, false, "to hurt, pain, ache")
val dormir = Verb("dormir", "durmiendo", "dormido", true, false, true, "to sleep")
val dormirse = Verb("dormirse", "durmiéndose", "dormido", true, true, false, "to go to sleep, fall asleep")
val duchar = Verb("duchar", "duchando", "duchado", false, false, false, "to douche")
val ducharse = Verb("ducharse", "duchándose", "duchado", false, true, true, "to take a shower, shower [oneself]")
val dudar = Verb("dudar", "dudando", "dudado", false, false, false, "to doubt")
val durar = Verb("durar", "durando", "durado", false, false, false, "to last, go on, continue")
val echar = Verb("echar", "echando", "echado", false, false, true, "to throw, cast, fling, hurl, pitch, toss")
val echarse = Verb("echarse", "echándose", "echado", false, true, false, "to throw, fling, hurl [oneself]")
val educar = Verb("educar", "educando", "educado", true, false, false, "to educate; to bring up")
val efectuar = Verb("efectuar", "efectuando", "efectuado", true, false, false, "to effect, carry out, bring about")
val ejercer = Verb("ejercer", "ejerciendo", "ejercido", false, false, false, "to exercise, wield [power, influence, etc.]; to practice [a profession]; to manage, conduct [a businsess]")
val elegir = Verb("elegir", "eligiendo", "elegido", true, false, false, "to elect, choose")
val eliminar = Verb("eliminar", "eliminando", "eliminado", false, false, false, "to eliminate, remove, get rid of")
val emborrachar = Verb("emborrachar", "emborrachando", "emborrachado", false, false, false, "to make drunk, intoxicate; to get [someone] drunk")
val emborracharse = Verb("emborracharse", "emborrachándose", "emborrachado", false, true, false, "to get drunk, become drunk")
val emigrar = Verb("emigrar", "emigrando", "emigrado", false, false, false, "to emigrate; to migrate")
val empezar = Verb("empezar", "empezando", "empezado", true, false, true, "to begin, start")
val emplear = Verb("emplear", "empleando", "empleado", false, false, false, "to employ, hire; to use")
val enamorar = Verb("enamorar", "enamorando", "enamorado", false, false, false, "to inspire love in, win the love of")
val enamorarse = Verb("enamorarse", "enamorándose", "enamorado", false, true, false, "to fall in love")
val encantar = Verb("encantar", "encantando", "encantado", false, false, false, "to delight, be delighting, charm, enchant")
val encender = Verb("encender", "encendiendo", "encendido", true, false, false, "to burn, light, turn on")
val encontrar = Verb("encontrar", "encontrando", "encontrado", true, false, true, "to find, encounter")
val enfadar = Verb("enfadar", "enfadando", "enfadado", false, false, false, "to anger, irritate, annoy")
val enfadarse = Verb("enfadarse", "enfadándose", "enfadado", false, true, false, "to get angry, get irratated, get annoyed")
val enfermar = Verb("enfermar", "enfermando", "enfermado", false, false, false, "to make ill, make sick, cause illness in")
val enfermarse = Verb("enfermarse", "enfermándose", "enfermado", false, true, false, "to get sick, become ill, fall ill. become sick")
val enflaquecer = Verb("enflaquecer", "enflaqueciendo", "enflaquecido", true, false, false, "to make thin, make weak, weaken")
val enflaquecerse = Verb("enflaquecerse", "enflaqueciéndose", "enflaquecido", true, true, false, "to get thin; lose weight; to grow weak")
val engañar = Verb("engañar", "engañando", "engañado", false, false, false, "to deceive, cheat, trick, swindle")
val enojar = Verb("enojar", "enojando", "enojado", false, false, false, "to anger, upset, annoy; to make angry")
val enojarse = Verb("enojarse", "enojándose", "enojado", false, true, true, "to get angry, get upset, lose one's temper")
val enriquecer = Verb("enriquecer", "enriqueciendo", "enriquecido", true, false, false, "to enrich, make rich")
val enriquecerse = Verb("enriquecerse", "enriqueciéndose", "enriquecido", true, true, false, "to get rich")
val enseñar = Verb("enseñar", "enseñando", "enseñado", false, false, false, "to teach, instruct, train, educate")
val ensuciar = Verb("ensuciar", "ensuciando", "ensuciado", false, false, false, "to dirty")
val entender = Verb("entender", "entendiendo", "entendido", true, false, true, "to understand")
val enterarse = Verb("enterarse", "enterándose", "enterado", false, true, false, "to find out [about something], to learn [of something])")
val entrar = Verb("entrar", "entrando", "entrado", false, false, false, "to enter, go in, come in")
val entregar = Verb("entregar", "entregando", "entregado", true, false, false, "to deliver, hand over")
val entretener = Verb("entretener", "entreteniendo", "entretenido", true, false, false, "to entertain, amuse")
val entrevistar = Verb("entrevistar", "entrevistando", "entrevistado", false, false, false, "to interview")
val entusiasmar = Verb("entusiasmar", "entusiasmando", "entusiasmado", false, false, false, "to excite; to fill with excitement [or enthusiasm]")
val entusiasmarse = Verb("entusiasmarse", "entusiasmándose", "entusiasmado", false, true, false, "to get excited, become excited [filled with excitement or enthusiasm]")
val envejecer = Verb("envejecer", "envejeciendo", "envejecido", true, false, false, "to age, make old, grow old, get old")
val envejecerse = Verb("envejecerse", "envejeciéndose", "envejecido", true, true, false, "to get old, age")
val enviar = Verb("enviar", "enviando", "enviado", true, false, true, "to send")
val equivocar = Verb("equivocar", "equivocando", "equivocado", true, false, false, "to mistake")
val equivocarse = Verb("equivocarse", "equivocándose", "equivocado", false, true, false, "to make a mistake")
val errar = Verb("errar", "errando", "errado", false, false, false, "to err, make an error")
val escoger = Verb("escoger", "escogiendo", "escogido", false, false, true, "to choose, select, pick")
val esconder = Verb("esconder", "escondiendo", "escondido", false, false, false, "to hide, conceal")
val esconderse = Verb("esconderse", "escondiéndose", "escondido", false, true, false, "to hide [oneself], be hidden")
val escribir = Verb("escribir", "escribiendo", "escrito", false, false, true, "to write")
val escuchar = Verb("escuchar", "escuchando", "escuchado", false, false, true, "to listen to, hear")
val esperar = Verb("esperar", "esperando", "esperado", false, false, true, "t o hope")
val esquiar = Verb("esquiar", "esquiando", "esquiado", true, false, false, "to ski")
val establecer = Verb("establecer", "estableciendo", "establecido", true, false, false, "to establish")
val estar = Verb("estar", "estando", "estado", true, false, true, "to be")
val estimar = Verb("estimar", "estimando", "estimado", false, false, false, "to estimate; to appraise; to esteem, respect")
val estudiar = Verb("estudiar", "estudiando", "estudiado", false, false, false, "to study")
val evacuar = Verb("evacuar", "evacuando", "evacuado", false, false, false, "to evacuate, empty")
val evitar = Verb("evitar", "evitando", "evitado", false, false, false, "to avoid; to prevent")
val exhibir = Verb("exhibir", "exhibiendo", "exhibido", false, false, false, "to exhibit, show, display")
val exigir = Verb("exigir", "exigiendo", "exigido", true, false, false, "to demand, require")
val explicar = Verb("explicar", "explicando", "explicado", true, false, false, "to explain")
val explorar = Verb("explorar", "explorando", "explorado", false, false, false, "to explore; to pioneer")
val explotar = Verb("explotar", "explotando", "explotado", false, false, false, "to exploit; to explode")
val exponer = Verb("exponer", "exponiendo", "expuesto", true, false, false, "to expose")
val exportar = Verb("exportar", "exportando", "exportado", false, false, false, "to export")
val expresar = Verb("expresar", "expresando", "expresado", false, false, false, "to express, voice, state")
val extender = Verb("extender", "extendiendo", "extendido", true, false, false, "to extend")
val extinguir = Verb("extinguir", "extinguiendo", "extinguido", false, false, false, "to extinguish, put out")
val fabricar = Verb("fabricar", "fabricando", "fabricado", true, false, false, "to manufacture")
val faltar = Verb("faltar", "faltando", "faltado", false, false, true, "to lack, be lacking, be missing, be absent")
val fascinar = Verb("fascinar", "fascinando", "fascinado", false, false, false, "to fascinate, captivate")
val felicitar = Verb("felicitar", "felicitando", "felicitado", false, false, false, "to congratulate")
val fijar = Verb("fijar", "fijando", "fijado", false, false, false, "to fix, fasten, secure")
val fingir = Verb("fingir", "fingiendo", "fingido", true, false, false, "to fake, feign, pretend")
val firmar = Verb("firmar", "firmando", "firmado", false, false, false, "to sign")
val florecer = Verb("florecer", "floreciendo", "florecido", true, false, false, "to flourish, flower, bloom")
val formar = Verb("formar", "formando", "formado", false, false, false, "to form, shape, fashion, make")
val fortalecer = Verb("fortalecer", "fortaleciendo", "fortalecido", true, false, false, "to fortify, strenthen")
val freír = Verb("freír", "friendo", "frito", true, false, false, "to fry")
val fumar = Verb("fumar", "fumando", "fumado", false, false, false, "to smoke")
val funcionar = Verb("funcionar", "funcionando", "funcionado", false, false, false, "to function; to run, work")
val ganar = Verb("ganar", "ganando", "ganado", false, false, true, "to win, gain, earn, get, acquire")
val gastar = Verb("gastar", "gastando", "gastado", false, false, true, "to spend, expend; to use up, consume; to wear away, wear down")
val generalizar = Verb("generalizar", "generalizando", "generalizado", true, false, false, "to generalize")
val glorificar = Verb("glorificar", "glorificando", "glorificado", true, false, false, "to glorify, praise")
val gobernar = Verb("gobernar", "gobernando", "gobernado", true, false, false, "to govern")
val graduar = Verb("graduar", "graduando", "graduado", true, false, false, "to graduate")
val graduarse = Verb("graduarse", "graduándose", "graduado", false, true, false, "to graduate")
val gritar = Verb("gritar", "gritando", "gritado", false, false, true, "to shout, yell, scream, cry out")
val gruñir = Verb("gruñir", "gruñendo", "gruñido", false, false, false, "to grunt, growl")
val guardar = Verb("guardar", "guardando", "guardado", false, false, true, "to guard, watch over, protect, take care of; to keep, hold on to; to put away, store away")
val guiar = Verb("guiar", "guiando", "guiado", true, false, false, "to guide")
val gustar = Verb("gustar", "gustando", "gustado", false, false, true, "to please, be pleasing")
val hablar = Verb("hablar", "hablando", "hablado", false, false, true, "to speak")
val hacer = Verb("hacer", "haciendo", "hecho", true, false, true, "to do, make")
val hallar = Verb("hallar", "hallando", "hallado", false, false, false, "to find; to discover; to locate; to find out")
val hallarse = Verb("hallarse", "hallándose", "hallado", false, true, false, "to be, to find oneself; to be located, found")
val helar = Verb("helar", "helando", "helado", true, false, false, "to freeze, to congeal, to chill")
val heredar = Verb("heredar", "heredando", "heredado", false, false, false, "to inherit")
val herir = Verb("herir", "hiriendo", "herido", true, false, false, "to injure, hurt, wound; to beat, strike")
val hervir = Verb("hervir", "hirviendo", "hervido", true, false, false, "to boil, entertain")
val huir = Verb("huir", "huyendo", "huido", false, false, false, "to flee, run away")
val ilustrar = Verb("ilustrar", "ilustrando", "ilustrado", false, false, false, "to illustrate, explain, make clear")
val importar = Verb("importar", "importando", "importado", false, false, false, "to import")
val imprimir = Verb("imprimir", "imprimiendo", "imprimido, impreso", false, false, false, "to print")
val incluir = Verb("incluir", "incluyendo", "incluido", false, false, true, "to include")
val indicar = Verb("indicar", "indicando", "indicado", true, false, false, "to indicate")
val inducir = Verb("inducir", "induciendo", "inducido", true, false, false, "to induce")
val influir = Verb("influir", "influyendo", "influido", false, false, false, "to influence")
val informar = Verb("informar", "informando", "informado", false, false, false, "to inform, tell, announce")
val iniciar = Verb("iniciar", "iniciando", "iniciado", false, false, false, "to initiate, begin, start")
val inmigrar = Verb("inmigrar", "inmigrando", "inmigrado", false, false, false, "to immigrate")
val insistir = Verb("insistir", "insistiendo", "insistido", false, false, false, "to insist")
val instalar = Verb("instalar", "instalando", "instalado", false, false, false, "to install; to set up, erect")
val insultar = Verb("insultar", "insultando", "insultado", false, false, false, "to insult")
val intentar = Verb("intentar", "intentando", "intentado", false, false, true, "to try, attempt")
val interesar = Verb("interesar", "interesando", "interesado", false, false, false, "to interest, be of interest [to]")
val interpretar = Verb("interpretar", "interpretando", "interpretado", false, false, false, "to interpret; to translate")
val introducir = Verb("introducir", "introduciendo", "introducido", true, false, false, "to introduce, insert")
val invadir = Verb("invadir", "invadiendo", "invadido", false, false, false, "to invade, overrun")
val inventar = Verb("inventar", "inventando", "inventado", false, false, false, "to invent")
val invertir = Verb("invertir", "invirtiendo", "invertido", true, false, false, "to invest")
val investigar = Verb("investigar", "investigando", "investigado", true, false, false, "to investigate")
val invitar = Verb("invitar", "invitando", "invitado", false, false, false, "to invite")
val invocar = Verb("invocar", "invocando", "invocado", true, false, false, "to invoke")
val ir = Verb("ir", "yendo", "ido", true, false, true, "to go")
val irse = Verb("irse", "yéndose", "ido", true, true, true, "to go away, leave, depart")
val jactarse = Verb("jactarse", "jactándose", "jactado", false, true, false, "to boast, brag")
val jugar = Verb("jugar", "jugando", "jugado", true, false, true, "to play")
val juntar = Verb("juntar", "juntando", "juntado", false, false, false, "to join, unite, put together, assemble")
val juntarse = Verb("juntarse", "juntándose", "juntado", false, true, false, "to join, come together, gather together, meet")
val jurar = Verb("jurar", "jurando", "jurado", false, false, false, "to swear")
val ladrar = Verb("ladrar", "ladrando", "ladrado", false, false, false, "to bark")
val lamentar = Verb("lamentar", "lamentando", "lamentado", false, false, false, "to lament, regret, feel sorry about")
val lanzar = Verb("lanzar", "lanzando", "lanzado", true, false, false, "to throw, hurl, cast, fling")
val lastimar = Verb("lastimar", "lastimando", "lastimado", false, false, false, "to hurt, injure, harm; to pity; to move to pity")
val lavar = Verb("lavar", "lavando", "lavado", false, false, false, "to wash")
val lavarse = Verb("lavarse", "lavándose", "lavado", false, true, false, "to wash [oneself], to wash up")
val leer = Verb("leer", "leyendo", "leído", true, false, true, "to read")
val legalizar = Verb("legalizar", "legalizando", "legalizado", true, false, false, "to legalize, make lawful")
val levantar = Verb("levantar", "levantando", "levantado", false, false, true, "to raise, to lift")
val levantarse = Verb("levantarse", "levantándose", "levantado", false, true, false, "to get up")
val limpiar = Verb("limpiar", "limpiando", "limpiado", false, false, true, "to clean")
val llamar = Verb("llamar", "llamando", "llamado", false, false, true, "to call, to name")
val llamarse = Verb("llamarse", "llamándose", "llamado", false, true, false, "to be called, be named")
val llegar = Verb("llegar", "llegando", "llegado", true, false, true, "to arrive, come, reach; to bring up, bring over; to gather together")
val llenar = Verb("llenar", "llenando", "llenado", false, false, false, "to fill; to fulfill")
val llevar = Verb("llevar", "llevando", "llevado", false, false, true, "to carry, bring")
val llorar = Verb("llorar", "llorando", "llorado", false, false, false, "to cry, weep; to weep for/about, cry about, lament")
val llover = Verb("llover", "lloviendo", "llovido", true, false, true, "to rain")
val lograr = Verb("lograr", "logrando", "logrado", false, false, true, "to get, obtain; to achieve, attain")
val luchar = Verb("luchar", "luchando", "luchado", false, false, false, "to fight, struggle; to wrestle")
val madurar = Verb("madurar", "madurando", "madurado", false, false, false, "to mature; to ripen")
val mandar = Verb("mandar", "mandando", "mandado", false, false, false, "to order")
val manejar = Verb("manejar", "manejando", "manejado", false, false, true, "to drive")
val mantener = Verb("mantener", "manteniendo", "mantenido", true, false, false, "to maintain, get")
val maquillar = Verb("maquillar", "maquillando", "maquillado", false, false, false, "to put make-up on")
val maquillarse = Verb("maquillarse", "maquillándose", "maquillado", false, true, false, "to put on make-up [on oneself]")
val marcar = Verb("marcar", "marcando", "marcado", true, false, false, "to mark, stamp, to show, indicate; to dial [e.g., a phone]")
val masticar = Verb("masticar", "masticando", "masticado", true, false, false, "to chew")
val matar = Verb("matar", "matando", "matado", false, false, true, "to kill, slay, slaughter")
val matricular = Verb("matricular", "matriculando", "matriculado", false, false, false, "to register")
val matricularse = Verb("matricularse", "matriculándose", "matriculado", false, true, false, "to register, enroll, matriculate [oneself]")
val medir = Verb("medir", "midiendo", "medido", true, false, false, "to measure, be")
val mentir = Verb("mentir", "mintiendo", "mentido", true, false, true, "to lie")
val merecer = Verb("merecer", "mereciendo", "merecido", true, false, false, "to deserve, merit")
val merendar = Verb("merendar", "merendando", "merendado", true, false, false, "to have a[n afternoon] snack, have as a snack")
val meter = Verb("meter", "metiendo", "metido", false, false, true, "to put [in], place, insert")
val mezclar = Verb("mezclar", "mezclando", "mezclado", false, false, false, "to mix, mix up, mix together, blend; to combine, merge, shuffle")
val mirar = Verb("mirar", "mirando", "mirado", false, false, true, "to watch, look at")
val modificar = Verb("modificar", "modificando", "modificado", true, false, false, "to modify, change")
val molestar = Verb("molestar", "molestando", "molestado", false, false, true, "to bother, annoy, inconovenience, put out, upset")
val montar = Verb("montar", "montando", "montado", false, false, false, "to mount, to get on, to ride")
val morir = Verb("morir", "muriendo", "muerto", true, false, false, "to die")
val mostrar = Verb("mostrar", "mostrando", "mostrado", true, false, false, "to show")
val mover = Verb("mover", "moviendo", "movido", true, false, false, "to move")
val moverse = Verb("moverse", "moviéndose", "movido", true, true, false, "to move [oneself]")
val mudar = Verb("mudar", "mudando", "mudado", false, false, false, "to change, alter; to move")
val mudarse = Verb("mudarse", "mudándose", "mudado", false, true, false, "to move [=change residence]")
val nacer = Verb("nacer", "naciendo", "nacido", true, false, false, "to be born")
val nadar = Verb("nadar", "nadando", "nadado", false, false, false, "to swim")
val navegar = Verb("navegar", "navegando", "navegado", true, false, false, "to navigate, sail")
val necesitar = Verb("necesitar", "necesitando", "necesitado", false, false, true, "to need, require")
val negar = Verb("negar", "negando", "negado", true, false, false, "to deny, refuse")
val negarse = Verb("negarse", "negándose", "negado", true, true, false, "to refuse")
val negociar = Verb("negociar", "negociando", "negociado", false, false, false, "to negotiate")
val nevar = Verb("nevar", "nevando", "nevado", true, false, false, "to snow")
val notar = Verb("notar", "notando", "notado", false, false, false, "to note, notice, observe")
val obedecer = Verb("obedecer", "obedeciendo", "obedecido", true, false, false, "to obey")
val obligar = Verb("obligar", "obligando", "obligado", true, false, false, "to oblige")
val obtener = Verb("obtener", "obteniendo", "obtenido", true, false, false, "to obtain, get")
val ocurrir = Verb("ocurrir", "ocurriendo", "ocurrido", true, false, false, "to occur, happen")
val odiar = Verb("odiar", "odiando", "odiado", false, false, false, "to hate")
val ofender = Verb("ofender", "ofendiendo", "ofendido", false, false, false, "to offend")
val ofrecer = Verb("ofrecer", "ofreciendo", "ofrecido", true, false, false, "to offer")
val oír = Verb("oír", "oyendo", "oído", true, false, true, "to hear")
val oler = Verb("oler", "oliendo", "olido", true, false, true, "to smell")
val olvidar = Verb("olvidar", "olvidando", "olvidado", false, false, true, "to forget")
val olvidarse = Verb("olvidarse", "olvidándose", "olvidado", false, true, false, "to forget")
val oponer = Verb("oponer", "oponiendo", "opuesto", true, false, false, "to pit [A against B], set up [A in opposition to B], play off [A against B]")
val oponerse = Verb("oponerse", "oponiéndose", "opuesto", true, true, false, "to put on [clothing]; to put/place [oneself]; to turn/get/become")
val organizar = Verb("organizar", "organizando", "organizado", true, false, false, "to organize")
val padecer = Verb("padecer", "padeciendo", "padecido", true, false, false, "to suffer")
val pagar = Verb("pagar", "pagando", "pagado", true, false, true, "to pay, pay for")
val parar = Verb("parar", "parando", "parado", false, false, true, "to stop, halt")
val parecer = Verb("parecer", "pareciendo", "parecido", true, false, true, "to seem, appear")
val participar = Verb("participar", "participando", "participado", false, false, false, "to participate, take part in; to share in; to inform, notify")
val pasar = Verb("pasar", "pasando", "pasado", false, false, true, "to pass, pass by, go; to pass on, hand; to spend [time]; to happen")
val patinar = Verb("patinar", "patinando", "patinado", false, false, false, "to skate; to slide, skid")
val pedir = Verb("pedir", "pidiendo", "pedido", true, false, true, "to request, ask for")
val pegar = Verb("pegar", "pegando", "pegado", true, false, false, "to stick, glue, hit")
val peinar = Verb("peinar", "peinando", "peinado", false, false, false, "to comb")
val peinarse = Verb("peinarse", "peinándose", "peinado", false, true, false, "to comb one's hair; to do one's hair")
val pelear = Verb("pelear", "peleando", "peleado", false, false, false, "to fight; to brawl, scruffle")
val pensar = Verb("pensar", "pensando", "pensado", true, false, false, "to think")
val perder = Verb("perder", "perdiendo", "perdido", true, false, false, "to lose")
val perdonar = Verb("perdonar", "perdonando", "perdonado", false, false, false, "to forgive, pardon, excuse")
val permanecer = Verb("permanecer", "permaneciendo", "permanecido", true, false, false, "to stay, remain")
val permitir = Verb("permitir", "permitiendo", "permitido", false, false, false, "to permit, allow")
val perseguir = Verb("perseguir", "persiguiendo", "perseguido", true, false, false, "to pursue, chase, hunt down")
val pertenecer = Verb("pertenecer", "perteneciendo", "pertenecido", true, false, false, "to belong, pertain")
val pesar = Verb("pesar", "pesando", "pesado", false, false, false, "to weigh; to weigh down, weigh heavily;")
val pescar = Verb("pescar", "pescando", "pescado", true, false, false, "to fish")
val picar = Verb("picar", "picando", "picado", true, false, false, "to prick, puncture, perforate; to sting, bite")
val pintar = Verb("pintar", "pintando", "pintado", false, false, false, "to paint")
val planchar = Verb("planchar", "planchando", "planchado", false, false, false, "to iron, press; to do the ironing")
val plantar = Verb("plantar", "plantando", "plantado", false, false, false, "to plant")
val platicar = Verb("platicar", "platicando", "platicado", true, false, false, "to chat, talk")
val poder = Verb("poder", "pudiendo", "podido", true, false, true, "to be able, can")
val poner = Verb("poner", "poniendo", "puesto", true, false, true, "to put, place, set")
val ponerse = Verb("ponerse", "poniéndose", "puesto", true, true, true, "to put on [clothing]; to put/place [oneself]; to turn/get/become")
val practicar = Verb("practicar", "practicando", "practicado", true, false, true, "to practice")
val predecir = Verb("predecir", "prediciendo", "predicho", true, false, false, "to predict, foretell, forecast")
val preferir = Verb("preferir", "prefiriendo", "preferido", true, false, false, "to prefer")
val preguntar = Verb("preguntar", "preguntando", "preguntado", false, false, true, "to ask, inquire")
val preguntarse = Verb("preguntarse", "preguntándose", "preguntado", false, true, false, "to wonder, ask oneself")
val preparar = Verb("preparar", "preparando", "preparado", false, false, false, "to prepare, get")
val prepararse = Verb("prepararse", "preparándose", "preparado", false, true, false, "to get")
val presentar = Verb("presentar", "presentando", "presentado", false, false, false, "t o i n t r o d u c e")
val presentir = Verb("presentir", "presintiendo", "presentido", true, false, false, "to have a premonition of")
val preservar = Verb("preservar", "preservando", "preservado", false, false, false, "to preserve, protect")
val prever = Verb("prever", "previendo", "previsto", true, false, false, "to foresee, anticipate, envisage, visualize")
val probar = Verb("probar", "probando", "probado", true, false, true, "to taste, try, test")
val producir = Verb("producir", "produciendo", "producido", true, false, false, "to produce")
val prohibir = Verb("prohibir", "prohibiendo", "prohibido", false, false, false, "to prohibit, forbid")
val prometer = Verb("prometer", "prometiendo", "prometido", false, false, false, "to promise")
val proponer = Verb("proponer", "proponiendo", "propuesto", true, false, false, "to propose")
val proseguir = Verb("proseguir", "prosiguiendo", "proseguido", true, false, false, "to continue, proceed with")
val proteger = Verb("proteger", "protegiendo", "protegido", false, false, false, "to protect")
val protestar = Verb("protestar", "protestando", "protestado", false, false, false, "to protest")
val provocar = Verb("provocar", "provocando", "provocado", true, false, false, "to provoke")
val publicar = Verb("publicar", "publicando", "publicado", true, false, false, "to publish")
val purificar = Verb("purificar", "purificando", "purificado", true, false, false, "to purify")
val quebrar = Verb("quebrar", "quebrando", "quebrado", true, false, false, "to break, smash; to fail, go bankrupt")
val quebrarse = Verb("quebrarse", "quebrándose", "quebrado", true, true, false, "to break, smash, get broken")
val quedar = Verb("quedar", "quedando", "quedado", false, false, true, "to stay, remain, be left; to be [indicating location]")
val quedarse = Verb("quedarse", "quedándose", "quedado", false, true, true, "to stay [behind], remain [behind]")
val quejarse = Verb("quejarse", "quejándose", "quejado", false, true, false, "to complain")
val quemar = Verb("quemar", "quemando", "quemado", false, false, true, "to burn [up], set on fire, scald, scorch; to be burning hot")
val quemarse = Verb("quemarse", "quemándose", "quemado", false, true, false, "to burn [oneself], to burn up, burn down")
val querer = Verb("querer", "queriendo", "querido", true, false, true, "to want, love")
val realizar = Verb("realizar", "realizando", "realizado", true, false, false, "to achieve, attain, accomplish, realize")
val rechazar = Verb("rechazar", "rechazando", "rechazado", true, false, false, "to reject; to push back, repel")
val recibir = Verb("recibir", "recibiendo", "recibido", false, false, false, "to receive; to welcome, greet")
val reciclar = Verb("reciclar", "reciclando", "reciclado", false, false, false, "to recycle; to retrain [a person]")
val recoger = Verb("recoger", "recogiendo", "recogido", false, false, true, "to pick up")
val recomendar = Verb("recomendar", "recomendando", "recomendado", true, false, false, "to recommend")
val reconocer = Verb("reconocer", "reconociendo", "reconocido", true, false, false, "to recognize")
val recordar = Verb("recordar", "recordando", "recordado", true, false, false, "to remember, remind")
val reducir = Verb("reducir", "reduciendo", "reducido", true, false, false, "to reduce")
val regalar = Verb("regalar", "regalando", "regalado", false, false, true, "to give [as a gift]; to present; to give away; to treat royally, pamper")
val regar = Verb("regar", "regando", "regado", true, false, false, "to water, irrigate, wash, sprinkle, spray")
val regatear = Verb("regatear", "regateando", "regateado", false, false, false, "to haggle (over), bargain (over)")
val regir = Verb("regir", "rigiendo", "regido", true, false, false, "to rule, govern, be in charge of, be at the head of")
val registrar = Verb("registrar", "registrando", "registrado", false, false, false, "to register, record; to search")
val registrarse = Verb("registrarse", "registrándose", "registrado", false, true, false, "to register [oneself]")
val regresar = Verb("regresar", "regresando", "regresado", false, false, true, "to return, go back")
val regular = Verb("regular", "regulando", "regulado", false, false, false, "to regulate, adjust, control")
val rehusar = Verb("rehusar", "rehusando", "rehusado", false, false, false, "to refuse")
val reinar = Verb("reinar", "reinando", "reinado", false, false, false, "to reign, rule")
val reír = Verb("reír", "riendo", "reído", true, false, true, "to laugh")
val renacer = Verb("renacer", "renaciendo", "renacido", true, false, false, "to be reborn")
val reñir = Verb("reñir", "riñendo", "reñido", true, false, false, "to quarrel, fight; to scold, tell off, reprimand")
val renovar = Verb("renovar", "renovando", "renovado", true, false, false, "to renew; to renovate")
val renunciar = Verb("renunciar", "renunciando", "renunciado", false, false, false, "to renounce, surrender, resign")
val reparar = Verb("reparar", "reparando", "reparado", false, false, false, "to repair, mend, restore")
val repasar = Verb("repasar", "repasando", "repasado", false, false, false, "to review, go over again; to do again")
val repetir = Verb("repetir", "repitiendo", "repetido", true, false, true, "to repeat")
val replicar = Verb("replicar", "replicando", "replicado", true, false, false, "to reply, answer back")
val reportar = Verb("reportar", "reportando", "reportado", false, false, false, "to bring, carry; to obtain; to report, inform; to denounce, accuse")
val requerir = Verb("requerir", "requiriendo", "requerido", true, false, false, "to require, need; to request, ask; to send for, call for")
val reservar = Verb("reservar", "reservando", "reservado", false, false, false, "to reserve; to keep, keep in reserve")
val resolver = Verb("resolver", "resolviendo", "resuelto", true, false, false, "to solve, resolve")
val respetar = Verb("respetar", "respetando", "respetado", false, false, false, "to respect")
val respirar = Verb("respirar", "respirando", "respirado", false, false, false, "to breathe, breathe in, inhale")
val responder = Verb("responder", "respondiendo", "respondido", false, false, false, "to respond, answer, reply to")
val resultar = Verb("resultar", "resultando", "resultado", false, false, false, "to turn out (to be), prove [to be], ensue")
val revelar = Verb("revelar", "revelando", "revelado", false, false, false, "to reveal, disclose; to develop [film]")
val rezar = Verb("rezar", "rezando", "rezado", true, false, false, "to pray")
val robar = Verb("robar", "robando", "robado", false, false, false, "to rob, steal, burgle, break into")
val rogar = Verb("rogar", "rogando", "rogado", true, false, false, "to plead, beg, ask for")
val romper = Verb("romper", "rompiendo", "roto", false, false, true, "to break")
val saber = Verb("saber", "sabiendo", "sabido", true, false, true, "to know")
val sacar = Verb("sacar", "sacando", "sacado", true, false, true, "to take out, stick out")
val sacrificar = Verb("sacrificar", "sacrificando", "sacrificado", true, false, false, "to sacrifice")
val sacudir = Verb("sacudir", "sacudiendo", "sacudido", false, false, false, "to shake, beat, flap")
val salir = Verb("salir", "saliendo", "salido", true, false, true, "to leave, go out")
val saltar = Verb("saltar", "saltando", "saltado", false, false, true, "to jump, leap; to jump over, leap over; to omit, skip, leave out")
val saludar = Verb("saludar", "saludando", "saludado", false, false, true, "to greet, salute, hail, welcome")
val salvar = Verb("salvar", "salvando", "salvado", false, false, false, "to save, rescue; to except, exclude")
val satirizar = Verb("satirizar", "satirizando", "satirizado", true, false, false, "to satirize")
val satisfacer = Verb("satisfacer", "satisfaciendo", "satisfecho", true, false, false, "to satisfy")
val secar = Verb("secar", "secando", "secado", true, false, false, "to dry, dry up, dry off, wipe dry")
val secarse = Verb("secarse", "secándose", "secado", true, true, false, "to dry, dry off, dry up")
val seguir = Verb("seguir", "siguiendo", "seguido", true, false, true, "to follow, continue")
val señalar = Verb("señalar", "señalando", "señalado", false, false, false, "to point out, point to, indicate; to signal; to mark")
val sentar = Verb("sentar", "sentando", "sentado", true, false, false, "to seat, sit")
val sentarse = Verb("sentarse", "sentándose", "sentado", true, true, true, "to sit down, seat oneself")
val sentir = Verb("sentir", "sintiendo", "sentido", true, false, true, "to feel, regret")
val sentirse = Verb("sentirse", "sintiéndose", "sentido", true, true, true, "to feel")
val ser = Verb("ser", "siendo", "sido", true, false, true, "to be")
val servir = Verb("servir", "sirviendo", "servido", true, false, false, "to serve")
val significar = Verb("significar", "significando", "significado", true, false, true, "to signify, mean")
val simbolizar = Verb("simbolizar", "simbolizando", "simbolizado", true, false, false, "to symbolize, represent, stand for")
val situar = Verb("situar", "situando", "situado", true, false, false, "to place, put, situate")
val sobrevivir = Verb("sobrevivir", "sobreviviendo", "sobrevivido", false, false, false, "to survive, outlive, outlast")
val soler = Verb("soler", "soliendo", "solido", true, false, false, "to be accustomed to, be accustomed to")
val sonar = Verb("sonar", "sonando", "sonado", true, false, false, "to sound; to ring")
val soñar = Verb("soñar", "soñando", "soñado", true, false, false, "to dream")
val sonreír = Verb("sonreír", "sonriendo", "sonreído", true, false, false, "to smile")
val soportar = Verb("soportar", "soportando", "soportado", false, false, false, "to support, hold up, bear; to put up with, tolerate")
val sorprender = Verb("sorprender", "sorprendiendo", "sorprendido", false, false, false, "to surprise, take by surprise, startle, amaze")
val subir = Verb("subir", "subiendo", "subido", false, false, true, "to go up, rise, move up, climb; to lift up, raise up")
val suceder = Verb("suceder", "sucediendo", "sucedido", false, false, false, "to happen; to succeed, follow")
val sufrir = Verb("sufrir", "sufriendo", "sufrido", false, false, false, "to suffer; to undergo, experience, put up with")
val sugerir = Verb("sugerir", "sugiriendo", "sugerido", true, false, false, "to suggest")
val suponer = Verb("suponer", "suponiendo", "supuesto", true, false, true, "to suppose")
val surgir = Verb("surgir", "surgiendo", "surgido", true, false, false, "to arise, emerge, spring up, come out, appear")
val suspirar = Verb("suspirar", "suspirando", "suspirado", false, false, false, "to sigh")
val sustituir = Verb("sustituir", "sustituyendo", "sustituido", false, false, false, "to substitute, replace")
val tañer = Verb("tañer", "tañendo", "tañido", false, false, false, "to play")
val tapar = Verb("tapar", "tapando", "tapado", false, false, false, "to cover; to put the lid on, put the cap on, put the stopper in")
val tardar = Verb("tardar", "tardando", "tardado", false, false, false, "to delay, take a long time, be long, be slow [in doing something]")
val temer = Verb("temer", "temiendo", "temido", false, false, false, "to fear")
val tener = Verb("tener", "teniendo", "tenido", true, false, true, "to have")
val teñir = Verb("teñir", "tiñendo", "teñido", true, false, false, "to dye, tint, tinge, color, stain")
val terminar = Verb("terminar", "terminando", "terminado", false, false, false, "to finish, end")
val tirar = Verb("tirar", "tirando", "tirado", false, false, true, "to throw, hurl; to shoot, fire; to throw away; to pull [out]")
val tocar = Verb("tocar", "tocando", "tocado", true, false, true, "to touch; to play")
val tomar = Verb("tomar", "tomando", "tomado", false, false, true, "to take, drink")
val torcer = Verb("torcer", "torciendo", "torcido", true, false, false, "to twist, bend, warp, wring, sprain")
val toser = Verb("toser", "tosiendo", "tosido", false, false, false, "to cough")
val trabajar = Verb("trabajar", "trabajando", "trabajado", false, false, true, "to work")
val traducir = Verb("traducir", "traduciendo", "traducido", true, false, false, "to translate")
val traer = Verb("traer", "trayendo", "traído", true, false, true, "to bring; to get, fetch; to carry")
val tragar = Verb("tragar", "tragando", "tragado", true, false, false, "to swallow, drink up, gulp down; to absorb, soak up; to put up with")
val tratar = Verb("tratar", "tratando", "tratado", false, false, true, "to treat, handle")
val triunfar = Verb("triunfar", "triunfando", "triunfado", false, false, false, "to triumph, win")
val tropezar = Verb("tropezar", "tropezando", "tropezado", true, false, false, "to trip, stumble, slip up")
val ubicar = Verb("ubicar", "ubicando", "ubicado", true, false, true, "to place, locate; to be located, be situated")
val unir = Verb("unir", "uniendo", "unido", false, false, false, "to unite, join; to tie together; to merge")
val untar = Verb("untar", "untando", "untado", false, false, false, "to anoint; to smear, rub")
val usar = Verb("usar", "usando", "usado", false, false, false, "to use")
val utilizar = Verb("utilizar", "utilizando", "utilizado", true, false, true, "to use, utilize")
val vaciar = Verb("vaciar", "vaciando", "vaciado", false, false, false, "to empty")
val valer = Verb("valer", "valiendo", "valido", true, false, false, "to be worth")
val variar = Verb("variar", "variando", "variado", true, false, false, "to vary")
val vencer = Verb("vencer", "venciendo", "vencido", false, false, false, "to conquer, defeat, beat, overcome, win; to expire, cease to apply")
val vender = Verb("vender", "vendiendo", "vendido", false, false, true, "to sell")
val venir = Verb("venir", "viniendo", "venido", true, false, true, "to come")
val ver = Verb("ver", "viendo", "visto", true, false, true, "to see")
val verificar = Verb("verificar", "verificando", "verificado", true, false, true, "to verify, check, inspect")
val vestir = Verb("vestir", "vistiendo", "vestido", true, false, false, "to dress")
val vestirse = Verb("vestirse", "vistiéndose", "vestido", true, true, false, "to get dressed, dress")
val viajar = Verb("viajar", "viajando", "viajado", false, false, true, "to travel, journey")
val violar = Verb("violar", "violando", "violado", false, false, false, "to violate; to rape")
val visitar = Verb("visitar", "visitando", "visitado", false, false, false, "to visit")
val vivir = Verb("vivir", "viviendo", "vivido", false, false, true, "to live")
val volar = Verb("volar", "volando", "volado", true, false, false, "to fly")
val volver = Verb("volver", "volviendo", "vuelto", true, false, true, "to return, go back")
val vomitar = Verb("vomitar", "vomitando", "vomitado", false, false, false, "to vomit, throw up, bring up")
val votar = Verb("votar", "votando", "votado", false, false, false, "to vote")
val yacer = Verb("yacer", "yaciendo", "yacido", true, false, false, "to lie")
val zambullirse = Verb("zambullirse", "zambulléndose", "zambullido", false, true, false, "to dive, plunge")

//val immutableListOfTenses = listOf(
//    Conditional()
//    Present(),
//    Preterite(),
//
//
//)
val immutableListOfVerbs = listOf(
    abandonar,
    abordar,
    abortar,
    abrazar,
    abrir,
    aburrir,
    aburrirse,
    abusar,
    acabar,
    acampar,
    aceptar,
    acercar,
    acercarse,
    acompañar,
    aconsejar,
    acontecer,
    acordar,
    acordarse,
    acortar,
    acostar,
    acostarse,
    acostumbrar,
    acostumbrarse,
    actuar,
    adivinar,
    admirar,
    admitir,
    adorar,
    adornar,
    advertir,
    afeitar,
    afeitarse,
    afirmar,
    afligir,
    agorar,
    agradar,
    agradecer,
    aguantar,
    ahorcar,
    ahorrar,
    alcanzar,
    alegrar,
    alegrarse,
    alentar,
    aliviar,
    almorzar,
    alquilar,
    amanecer,
    amar,
    amenazar,
    añadir,
    andar,
    anhelar,
    anunciar,
    apagar,
    aparecer,
    aplaudir,
    aplicar,
    apostar,
    apoyar,
    apreciar,
    aprender,
    apretar,
    aprobar,
    arreglar,
    arrepentirse,
    arrojar,
    asistir,
    asociar,
    aspirar,
    asustar,
    asustarse,
    atacar,
    atender,
    atraer,
    atravesar,
    atreverse,
    aumentar,
    avanzar,
    averiguar,
    avisar,
    ayudar,
    bailar,
    bajar,
    bañar,
    bañarse,
    barrer,
    batir,
    bautizar,
    beber,
    bendecir,
    besar,
    bordar,
    borrar,
    brillar,
    brindar,
    broncearse,
    bucear,
    burlar,
    burlarse,
    buscar,
    caber,
    caer,
    calcular,
    calentar,
    calentarse,
    callar,
    callarse,
    calmar,
    calmarse,
    caminar,
    cancelar,
    cansar,
    cansarse,
    caracterizar,
    cargar,
    casar,
    casarse,
    castigar,
    causar,
    cazar,
    celebrar,
    cenar,
    censurar,
    cepillar,
    cerrar,
    cesar,
    charlar,
    chismear,
    chocar,
    civilizar,
    clarificar,
    clasificar,
    cobrar,
    cocinar,
    coger,
    colgar,
    colocar,
    colonizar,
    combatir,
    comenzar,
    comer,
    compartir,
    competir,
    componer,
    comprar,
    comprender,
    comunicar,
    comunicarse,
    condenar,
    conducir,
    confesar,
    confiar,
    confirmar,
    confiscar,
    conjugar,
    conocer,
    conquistar,
    conseguir,
    consentir,
    conservar,
    consistir,
    constituir,
    construir,
    consumir,
    contaminar,
    contar,
    contener,
    contestar,
    continuar,
    contribuir,
    controlar,
    convencer,
    convenir,
    conversar,
    convertir,
    convidar,
    copiar,
    corregir,
    correr,
    cortar,
    coser,
    costar,
    crear,
    crecer,
    creer,
    criar,
    criarse,
    criticar,
    crucificar,
    cruzar,
    cubrir,
    cuidar,
    culpar,
    cultivar,
    cumplir,
    curar,
    dar,
    deber,
    decidir,
    decidirse,
    decir,
    declarar,
    decorar,
    dedicar,
    dedicarse,
    defender,
    dejar,
    demostrar,
    depender,
    depositar,
    deprimir,
    derretir,
    desagradar,
    desagradecer,
    desaparecer,
    desarrollar,
    desarrollarse,
    desayunar,
    descansar,
    descender,
    describir,
    descubrir,
    desear,
    deshacer,
    despedir,
    despertar,
    despertarse,
    destruir,
    detener,
    detenerse,
    detestar,
    devolver,
    devorar,
    dibujar,
    dirigir,
    diseñar,
    disfrutar,
    disgustar,
    disminuir,
    distinguir,
    distribuir,
    divertir,
    divertirse,
    divorciar,
    divorciarse,
    doblar,
    doler,
    dormir,
    dormirse,
    duchar,
    ducharse,
    dudar,
    durar,
    echar,
    echarse,
    educar,
    efectuar,
    ejercer,
    elegir,
    eliminar,
    emborrachar,
    emborracharse,
    emigrar,
    empezar,
    emplear,
    enamorar,
    enamorarse,
    encantar,
    encender,
    encontrar,
    enfadar,
    enfadarse,
    enfermar,
    enfermarse,
    enflaquecer,
    enflaquecerse,
    engañar,
    enojar,
    enojarse,
    enriquecer,
    enriquecerse,
    enseñar,
    ensuciar,
    entender,
    enterarse,
    entrar,
    entregar,
    entretener,
    entrevistar,
    entusiasmar,
    entusiasmarse,
    envejecer,
    envejecerse,
    enviar,
    equivocar,
    equivocarse,
    errar,
    escoger,
    esconder,
    esconderse,
    escribir,
    escuchar,
    esperar,
    esquiar,
    establecer,
    estar,
    estimar,
    estudiar,
    evacuar,
    evitar,
    exhibir,
    exigir,
    explicar,
    explorar,
    explotar,
    exponer,
    exportar,
    expresar,
    extender,
    extinguir,
    fabricar,
    faltar,
    fascinar,
    felicitar,
    fijar,
    fingir,
    firmar,
    florecer,
    formar,
    fortalecer,
    freír,
    fumar,
    funcionar,
    ganar,
    gastar,
    generalizar,
    glorificar,
    gobernar,
    graduar,
    graduarse,
    gritar,
    gruñir,
    guardar,
    guiar,
    gustar,
    hablar,
    hacer,
    hallar,
    hallarse,
    helar,
    heredar,
    herir,
    hervir,
    huir,
    ilustrar,
    importar,
    imprimir,
    incluir,
    indicar,
    inducir,
    influir,
    informar,
    iniciar,
    inmigrar,
    insistir,
    instalar,
    insultar,
    intentar,
    interesar,
    interpretar,
    introducir,
    invadir,
    inventar,
    invertir,
    investigar,
    invitar,
    invocar,
    ir,
    irse,
    jactarse,
    jugar,
    juntar,
    juntarse,
    jurar,
    ladrar,
    lamentar,
    lanzar,
    lastimar,
    lavar,
    lavarse,
    leer,
    legalizar,
    levantar,
    levantarse,
    limpiar,
    llamar,
    llamarse,
    llegar,
    llenar,
    llevar,
    llorar,
    llover,
    lograr,
    luchar,
    madurar,
    mandar,
    manejar,
    mantener,
    maquillar,
    maquillarse,
    marcar,
    masticar,
    matar,
    matricular,
    matricularse,
    medir,
    mentir,
    merecer,
    merendar,
    meter,
    mezclar,
    mirar,
    modificar,
    molestar,
    montar,
    morir,
    mostrar,
    mover,
    moverse,
    mudar,
    mudarse,
    nacer,
    nadar,
    navegar,
    necesitar,
    negar,
    negarse,
    negociar,
    nevar,
    notar,
    obedecer,
    obligar,
    obtener,
    ocurrir,
    odiar,
    ofender,
    ofrecer,
    oír,
    oler,
    olvidar,
    olvidarse,
    oponer,
    oponerse,
    organizar,
    padecer,
    pagar,
    parar,
    parecer,
    participar,
    pasar,
    patinar,
    pedir,
    pegar,
    peinar,
    peinarse,
    pelear,
    pensar,
    perder,
    perdonar,
    permanecer,
    permitir,
    perseguir,
    pertenecer,
    pesar,
    pescar,
    picar,
    pintar,
    planchar,
    plantar,
    platicar,
    poder,
    poner,
    ponerse,
    practicar,
    predecir,
    preferir,
    preguntar,
    preguntarse,
    preparar,
    prepararse,
    presentar,
    presentir,
    preservar,
    prever,
    probar,
    producir,
    prohibir,
    prometer,
    proponer,
    proseguir,
    proteger,
    protestar,
    provocar,
    publicar,
    purificar,
    quebrar,
    quebrarse,
    quedar,
    quedarse,
    quejarse,
    quemar,
    quemarse,
    querer,
    realizar,
    rechazar,
    recibir,
    reciclar,
    recoger,
    recomendar,
    reconocer,
    recordar,
    reducir,
    regalar,
    regar,
    regatear,
    regir,
    registrar,
    registrarse,
    regresar,
    regular,
    rehusar,
    reinar,
    reír,
    renacer,
    reñir,
    renovar,
    renunciar,
    reparar,
    repasar,
    repetir,
    replicar,
    reportar,
    requerir,
    reservar,
    resolver,
    respetar,
    respirar,
    responder,
    resultar,
    revelar,
    rezar,
    robar,
    rogar,
    romper,
    saber,
    sacar,
    sacrificar,
    sacudir,
    salir,
    saltar,
    saludar,
    salvar,
    satirizar,
    satisfacer,
    secar,
    secarse,
    seguir,
    señalar,
    sentar,
    sentarse,
    sentir,
    sentirse,
    ser,
    servir,
    significar,
    simbolizar,
    situar,
    sobrevivir,
    soler,
    sonar,
    soñar,
    sonreír,
    soportar,
    sorprender,
    subir,
    suceder,
    sufrir,
    sugerir,
    suponer,
    surgir,
    suspirar,
    sustituir,
    tañer,
    tapar,
    tardar,
    temer,
    tener,
    teñir,
    terminar,
    tirar,
    tocar,
    tomar,
    torcer,
    toser,
    trabajar,
    traducir,
    traer,
    tragar,
    tratar,
    triunfar,
    tropezar,
    ubicar,
    unir,
    untar,
    usar,
    utilizar,
    vaciar,
    valer,
    variar,
    vencer,
    vender,
    venir,
    ver,
    verificar,
    vestir,
    vestirse,
    viajar,
    violar,
    visitar,
    vivir,
    volar,
    volver,
    vomitar,
    votar,
    yacer,
    zambullirse
)