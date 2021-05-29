import Footer.displayFooter
import Navigation.displayNavigation
import database.initializers.getShifts
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import firebase.initFirebase
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.color
import react.RBuilder
import react.dom.p
import react.dom.render
import styled.StyleSheet
import styled.css
import styled.styledDiv
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalJsExport
fun main() {

    initFirebase()

    Firebase.auth.currentUser?.displayName?.let { displayName ->
        console.log("Welcome $displayName")
    }

    window.onload = {
        render(document.getElementById("root")) {

            displayNavigation()

            displayHome()

            displayFooter()
        }
    }
}

object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val wrapper by css {
        backgroundColor = Color.darkGray
        color = Color.white
    }
}

fun RBuilder.displayShifts() {
    styledDiv {
        css {
            getShifts { shifts ->
                shifts.forEach {
                    p {
                        if (it == null) return@forEach
                        +"Start time ${it.first}, end time ${it.second}"
                    }
                }
            }

        }
    }
}


