package database.references

import database.references.UserReferences.getUserReference
import dev.gitlive.firebase.database.DatabaseReference
import utils.getMonth

object ShiftReferences {

    private const val ATTENDANCE = "attendance"
    private const val PERIODS = "periods"

    private const val SHIFTS = "shifts"
    private const val PAUSES = "pauses"

    fun getShiftsDataReference(userId : String, yearKey : Int, monthKey : Int) : DatabaseReference {

        // Get month from monthKey number 1-12 (e.g. 3 returns MARCH)
        val month = getMonth(monthKey)!!

        // Get string year value
        val year = yearKey.toString()

        return getUserReference(userId).child(ATTENDANCE).child(year).child(month)
    }


    /**
     * @return Reference to all shifts in the given period
     */
    fun getShiftsReference(userId: String,
                           yearKey: Int, monthKey: Int): DatabaseReference =
        getShiftsDataReference(userId, yearKey, monthKey).child(SHIFTS)


    /**
     * @return Reference to particular shift.
     */
    fun getShiftReference(userId: String,
                          yearKey: Int, monthKey: Int,
                          shiftKey: String): DatabaseReference =
        getShiftsReference(userId, yearKey, monthKey).child(shiftKey)

    /**
     * @return Reference to all pauses of shift.
     */
    fun getPausesReference(userId: String,
                           yearKey: Int, monthKey: Int,
                           shiftKey: String): DatabaseReference =
        getShiftReference(userId, yearKey, monthKey, shiftKey).child(PAUSES)

    fun getPausesReference(shiftReference: DatabaseReference): DatabaseReference =
        shiftReference.child(PAUSES)

    /**
     * @return Reference to particular pause of shift.
     */
    fun getPauseReference(userId: String,
                          yearKey: Int, monthKey: Int,
                          shiftKey: String,
                          pauseKey: String): DatabaseReference =
        getPausesReference(userId, yearKey, monthKey, shiftKey).child(pauseKey)

    fun getPauseReference(shiftReference: DatabaseReference,
                          pauseKey: String): DatabaseReference =
        getPausesReference(shiftReference).child(pauseKey)

}