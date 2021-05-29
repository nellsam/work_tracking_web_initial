package database.initializers

import database.models.User
import database.models.employee.Employee
import database.models.employer.Employer
import database.references.UserReferences.getUserReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun getUser(userId: String, callback: (User?) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    val userReference = getUserReference(userId)

    userReference.valueEvents.collect {

        val user = it.value<User>()

        callback(user)
    }
}

fun getEmployee(userId: String, callback: (Employee?) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    val userReference = getUserReference(userId)

    userReference.valueEvents.collect {

        val employee = it.value<Employee?>()

        callback(employee)
    }
}

fun getEmployer(userId: String, callback: (Employer?) -> Unit) = CoroutineScope(Dispatchers.Default).launch {

    val userReference = getUserReference(userId)

    userReference.valueEvents.collect {

        val employer = it.value<Employer?>()

        callback(employer)
    }
}