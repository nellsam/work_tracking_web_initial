package database.models


/**
 * Common attributes for all kinds of users.
 *
 * Note it cannot be used to retrieve data model from the database.
 * @see User
 */
abstract class UserBase {

    abstract val firstName: String
    abstract val lastName: String
    abstract val email: String
    abstract val employer: Boolean

    val phoneNumber: String = ""

    val lastTimeSignedIn: String? = null
    val lastTimeActive: String? = null

    val fullName: String
        get() = "$firstName $lastName"
}





