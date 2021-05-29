package utils


/**
 * Retrieve month from number 1-12 (e.g. 3 returns MARCH)
 */
fun getMonth(monthKey: Int): String? =
    try {
        Months.values()[monthKey - 1].name
    } catch (e: IndexOutOfBoundsException) {
        null
    }

