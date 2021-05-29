package database.references

import database.references.DatabaseReferences.usersReference
import dev.gitlive.firebase.database.DatabaseReference

object UserReferences {

    private const val PERSONAL_INFO = "personal_info"

    fun getUserReference(userId: String): DatabaseReference =
        usersReference.child(userId)

    fun getPersonalInfoReference(userId: String) =
        getUserReference(userId).child(PERSONAL_INFO)

}