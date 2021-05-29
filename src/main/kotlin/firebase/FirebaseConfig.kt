package firebase

import dev.gitlive.firebase.FirebaseOptions

/**
 * Config for initializing Firebase with GitLive.
 */
object FirebaseConfig {

    private const val apiKey = "yourApiKey"
    private const val authDomain = "yourAuthDomain"
    private const val databaseURL = "yourDatabaseUrl"
    private const val projectId = "yourProjectId"
    private const val storageBucket = "yourStorageBucket"
    private const val messagingSenderId = "yourMessagingSenderId"
    private const val appId = "yourAppId"
    private const val measurementId = "yourMeasurementId"

    /**
     * Configured firebase options.
     */
    val firebaseOptions: FirebaseOptions
        get() {
            return FirebaseOptions(
                apiKey = apiKey,
                applicationId = appId,
                databaseUrl = databaseURL,
                projectId = projectId,
                storageBucket = storageBucket,
                gcmSenderId = messagingSenderId,
                gaTrackingId = null,
            )
        }
}


