package database.models.employee

import database.models.UserBase
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    override val employer: Boolean = false,
    val memberOf: String,
    val workLoad: String = ""
) : UserBase()