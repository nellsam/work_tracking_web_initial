package database.models.shift

import kotlinx.serialization.Serializable

@Serializable
data class ShiftModel(

        var startTime: String? = null,

        // End of the shift
        var endTime: String? = null,

        // Last time new data has been uploaded to shift.
        // This is used to determine, if shift is still in progress.
        var lastTimeActive: String? = startTime,

        // True, if note is included in the database
        var hasNote: Boolean = false,

        // True, if shift contains location data in database
        var hasLocation: Boolean = false,

        // True, if shift contains one or more photos in the database
        var hasPhoto: Boolean = false,

        // True, if shift was manually added
        var added: Boolean = false,

        // True, if shift has been edited
        var edited: Boolean = false,

        // True, if shift has been edited by employer
        var editedByEmployer: Boolean = false,

        // True, if seen by employer
        var seen: Boolean = false,

        // True, if accepted by employer - true by default, unless employer set otherwise
        var accepted: Boolean = true
)
