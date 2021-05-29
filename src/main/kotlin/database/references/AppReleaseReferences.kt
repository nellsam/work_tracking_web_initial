package database.references

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.serialization.Serializable

object AppReleaseReferences {

    private const val APP_RELEASE_AWAITING = "app_release_awaiting"

    /**
     * @return Reference to users waiting for app release.
     */
    fun getAwaitingUsersReference() = Firebase.database.reference("/$APP_RELEASE_AWAITING")
}

@Serializable
data class AwaitingUser(val email : String,
                        val timestamp : String)