import database.references.AppReleaseReferences.getAwaitingUsersReference
import database.references.AwaitingUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlin.js.Date

fun addNewAwaitingUser(email: String, callback: (Boolean) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    if (!isEmailValid(email)) {
        callback(false)
        return@launch
    }

    // Get reference to awaiting users
    val awaitingUsersRef = getAwaitingUsersReference()

    // Get unique key for new user
    val uniqueKey = awaitingUsersRef.push().key ?: run {
        callback(false)
        return@launch
    }

    // Get current time
    val timestamp = Date.now().toString()

    // Create new awaiting user
    val awaitingUser = AwaitingUser(email, timestamp)

    try {

        // Set new awaiting user
        awaitingUsersRef.child(uniqueKey).setValue(AwaitingUser.serializer(), awaitingUser)

    } catch (e: SerializationException) {
        e.printStackTrace()
    }

    // Return result
    callback(true)
}


private fun isEmailValid(email: String) = email.contains("@")
