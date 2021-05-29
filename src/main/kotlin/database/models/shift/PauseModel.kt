package database.models.shift

import kotlinx.serialization.Serializable


/**
 * Pause data model contained in pauses of shift.
 */
@Serializable
data class PauseModel(var pauseStart : String? = null,
                      var pauseEnd: String? = null)

