package database.models.employer

import database.models.UserBase
import kotlinx.serialization.Serializable

@Serializable
data class Employer(
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    override val employer: Boolean = true,
    val company: String? = null
) : UserBase()