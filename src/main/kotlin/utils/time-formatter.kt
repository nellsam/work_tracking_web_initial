package utils

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun Duration.formatTotal() : String {

    var hours = kotlin.math.floor(inSeconds / 3600)
    var minutes = kotlin.math.floor((inSeconds - (hours * 3600)) / 60)
    var seconds = kotlin.math.floor(inSeconds - (hours * 3600) - (minutes * 60))

    if (hours < 10) hours += 0
    if (minutes < 10) minutes += 0
    if (seconds < 10) seconds += 0

    return "${hours}h ${minutes}m ${seconds}s"
}