package firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

/**
 * Firebase initialization using GitLive.
 */
fun initFirebase() = Firebase.initialize(options = FirebaseConfig.firebaseOptions)
