package database.models.employer

import kotlinx.serialization.Serializable
import kotlin.js.Date

@Serializable
data class Member (val email: String) {

    // Time when new member was added to member list
    val addedDateTime: String = Date(Date.now()).toLocaleDateString()

    // Employee's id assigned once he signs up with email
    var userId: String = ""

    // Time when employee successfully signed up and joined organisation
    var joinedDateTime: String = ""
}