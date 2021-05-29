package database.references

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database

/**
 * Top references
 */
object DatabaseReferences {

    private const val USERS = "users"
    private const val INVITE_CODES = "invite_codes"

    val usersReference: DatabaseReference
        get() = Firebase.database.reference("/$USERS")

    val inviteCodesReference: DatabaseReference
        get() = Firebase.database.reference("/$INVITE_CODES")
}