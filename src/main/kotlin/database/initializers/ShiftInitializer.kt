package database.initializers

import database.models.shift.ShiftModel
import database.references.ShiftReferences
import database.references.ShiftReferences.getShiftReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import utils.formatTotal
import kotlin.js.Date
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

fun getShiftModel(userId : String, yearKey : Int, monthKey : Int, shiftKey : String,
                  callback : (ShiftModel?) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    val shiftReference = getShiftReference(userId,yearKey, monthKey, shiftKey)

    shiftReference.valueEvents.collect {

        val shiftModel = it.value<ShiftModel?>()

        // Return shift model
        callback(shiftModel)
    }
}


fun getShifts(callback: (List<Pair<String, String>?>) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    val shiftsReference = ShiftReferences.getShiftsReference("userId", 2021, 1)

    launch {
        try {
            println("Third")
            shiftsReference.valueEvents.collectIndexed { index, snapshot ->
                println("Index $index, count ${snapshot.children.count()}")


            }
        } catch(e : Exception) {
            println("Exception caught")
        }
    }
}

@ExperimentalTime
fun getShiftDuration() = CoroutineScope(Dispatchers.Default).launch {

    val shiftReference = getShiftReference("userId", 2021, 1, "shiftKey")

    println(shiftReference.toString())

    shiftReference.valueEvents.collect { snapshot ->

        try {

            // Get shift model from snapshot
            val shiftModel = snapshot.value<ShiftModel>()

            println(shiftModel)

            val startTime = shiftModel.startTime ?: return@collect
            val endTime = shiftModel.endTime ?: return@collect

            val startTimeMillis = Date.parse(startTime)
            val endTimeMillis = Date.parse(endTime)

            // Duration between start and end time
            val duration = (endTimeMillis - startTimeMillis).toDuration(DurationUnit.MILLISECONDS)

            /** @see utils.formatTotal */
            println(duration.formatTotal())

        } catch (e: IllegalArgumentException) {
            console.log("setLocalDate: Illegal argument exception")
        }
    }
}