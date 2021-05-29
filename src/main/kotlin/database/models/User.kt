package database.models

import kotlinx.serialization.Serializable

/**
 * Use this to retrieve user's personal info from the database.
 *
 * Note that UserBase is abstract and cannot be used for this purpose,
 * that's why this class has to be used.
 */

@Serializable
data class User(override val employer: Boolean,
                override val firstName: String,
                override val lastName: String,
                override val email: String) : UserBase()
