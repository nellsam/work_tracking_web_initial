import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.border
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import styled.*
import ui.blueBackgroundColor
import ui.greyTextColor
import ui.lighterDarkBackgroundColor
import utils.mobileScreen

fun RBuilder.displayReleaseDate() {

    styledDiv {
        css {
            maxWidth = LinearDimension.fillAvailable
            textAlign = TextAlign.center
            padding(2.5.vw)
            display = Display.flex
            flexDirection = FlexDirection.column

            mobileScreen {
                padding(top = 20.px, bottom = 20.px)
            }
        }

        displayReleaseInfo()
        displayWaitForRelease()
        displayMoreInfoAvailableSoon()
    }
}

private fun RBuilder.displayReleaseInfo() {

    styledH3 {
        +"Release date"
        css {
            padding(2.px)
            margin(0.px)

            fontSize = 1.5.vw

            mobileScreen {
                fontSize = 2.5.vw
            }
        }
    }

    styledP {
        +"Alpha version expected by the mid of 2021 on Android platform."

        css {
            fontSize = 1.25.vw

            mobileScreen {
                fontSize = 2.5.vw
            }
        }
    }
}

private fun RBuilder.displayMoreInfoAvailableSoon() {
    styledDiv {

        css {
            position = Position.relative
            display = Display.tableCell
            width = LinearDimension.fitContent
            margin(left = LinearDimension.auto, right = LinearDimension.auto)
        }

        styledP {
            +"More info available soon."
            attrs { id = "moreInfoAvailableSoon" }

            css {
                fontSize = 1.25.vw

                mobileScreen {
                    fontSize = 2.5.vw
                }
            }
        }
    }
}

private fun RBuilder.displayWaitForRelease() {

    styledDiv {

        css {
            marginTop = 1.vw
            marginBottom = 3.vw
        }

        styledH3 {
            +"Be the first to try the app"
            css {
                padding(0.8.vw)
                margin(0.px)
                fontSize = 1.5.vw

                mobileScreen {
                    fontSize = 2.5.vw
                }
            }
        }

        styledP {
            +"Provide us with your email and we'll contact you once the app is released."
            css {
                padding(0.vw) // 2.px
                margin(0.vw, bottom = 2.vw)

                fontSize = 1.25.vw

                mobileScreen {
                    fontSize = 2.5.vw
                }
            }
        }

        child(EmailInputField::class) { }
//        displayPrivacyPolicy()
    }
}

private data class EmailInputFieldState(
    val email: String = "",
    val agreeToPrivacyPolicy: Boolean,
    val promptPrivacyPolicy: Boolean,
    val submitted: Boolean? = null,
    val notRobot: Boolean = false,
    val promptRecaptcha: Boolean = false
) : RState

private class EmailInputField : RComponent<RProps, EmailInputFieldState>() {

    override fun RBuilder.render() {

        styledDiv {

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                maxWidth = LinearDimension.fillAvailable
                justifyContent = JustifyContent.center
                textAlign = TextAlign.center
            }

            displayInputField()
            child(getNotifiedButton)
        }

        displayPrivacyPolicy()
    }

    private fun RBuilder.displayInputField() {
        styledInput {

            css { +ReleaseDateStyle.inputField }

            attrs {
                type = InputType.email
                placeholder = "Your email"
                onChangeFunction = { event ->

                    // Update email with current value
                    updateEmail((event.target as HTMLInputElement).value)
                }

                if (state.submitted == true) disabled = true
            }
        }
    }


    private val getNotifiedButton
        get() = functionalComponent<RProps> {
//            val (success, setSuccess) = useState<Boolean?>(null)
            val submitted = state.submitted

            styledButton {

                if (submitted == true) +"✓"
                else +"Get notified"

                attrs {
                    type = ButtonType.button

                    // Animate changing button's color
                    if (submitted == true) {
                        id = "submittedNotifyButton"
                    }

                    // Disabled while not submitted or provided email is not valid
                    disabled = !canProceed || submitted == true

                    onClickFunction = { submit() }
                }

                css {

                    if (submitted == true) {
                        +ReleaseDateStyle.confirmButtonSuccessful
                    } else {
                        +ReleaseDateStyle.confirmButton
                        disabled { backgroundColor = blueBackgroundColor }
                    }

                }
            }
        }

    private fun submit() {

        if (!state.agreeToPrivacyPolicy) {
//            println("User hasn't agreed to privacy policy")
            promptPrivacyPolicy(true)
            return
        }

        if (!state.notRobot) {
//            println("User hasn't confirmed he isn't robot")
            promptRecaptcha()
            return
        }

        if (isProvidedEmailValid(state.email))
            getNotifiedClicked { successful ->
                if (successful) {
                    // Update state
                    setSubmitted()
                }
            }
    }

    private fun getNotifiedClicked(callback: (Boolean) -> Unit) {
//        println("Get notified button clicked")

        val email = state.email

        if (!isProvidedEmailValid(email)) {
//            println("Provided email is not valid")
            return
        }
//        println("Provided email is valid")

        addNewAwaitingUser(email) { successful ->
            if (successful) {
//                println("New awaiting user with email $email, add successfully")

                // Return result
                callback(true)

            } else {
//                println("Failed to add new user")
                callback(false)
            }
        }
    }

    private val canProceed: Boolean
        get() = state.agreeToPrivacyPolicy && isProvidedEmailValid(state.email)

    private fun isProvidedEmailValid(email: String?) = email != null && email.isNotEmpty() && email.contains("@")
            && email.contains(".") && email.length > 3

    private fun RBuilder.displayPrivacyPolicy() {
        styledDiv {

            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
                paddingTop = 0.25.vw
            }

            child(privacyPolicyCheckbox)

            styledP {
                +"I agree with processing my personal data"
                css {
                    fontSize = 0.8.vw
                    color = greyTextColor
                    textAlign = TextAlign.center
                    padding(0.75.vw)
                    margin(0.px)
                    mobileScreen {
                        fontSize = 1.5.vw
                    }
                }
            }
        }

        if (state.promptRecaptcha) {
            displayRecaptcha()
        }
    }

    private fun RBuilder.displayRecaptcha() {
        styledDiv {

            css {
                marginTop = 1.5.vw
                marginBottom = 1.5.vw
                marginLeft = LinearDimension.auto
                marginRight = LinearDimension.auto
                width = LinearDimension.fitContent
            }

            recaptcha {
                attrs {

                    // Test key, generate your own
                    sitekey = "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"

                    onChange = {
                        setNotRobot(notRobot = true)
                        submit()
                    }
                    onExpired = {
                        setNotRobot(notRobot = false)
                    }
                    theme = "dark"
                    size = "normal"
                    badge = "bottomright"
                }
            }
        }

    }

    private val privacyPolicyCheckbox
        get() = functionalComponent<RProps> {
            val (checked, setChecked) = useState(state.agreeToPrivacyPolicy)

            val handleChange: (dynamic) -> Unit = { event ->

                // Get new value
                val isChecked = (event.target.checked) as Boolean

                // Update state
                agreeToPrivacyPolicy(isChecked)

                // Update UI
                setChecked(isChecked)
            }

            styledInput {

                css {
                    textAlign = TextAlign.center
                    marginTop = LinearDimension.auto
                    marginBottom = LinearDimension.auto
                    height = 1.vw
                    mobileScreen { height = 1.5.vw }
                }

                attrs {
                    type = InputType.checkBox
                    defaultChecked = checked
                    onChangeFunction = { handleChange(it) }

                    if (state.submitted == true) disabled = true
                }
            }
        }

    private fun updateEmail(email: String) {
        setState(
            EmailInputFieldState(
                email = email,
                agreeToPrivacyPolicy = state.agreeToPrivacyPolicy,
                promptPrivacyPolicy = state.promptPrivacyPolicy,
                submitted = state.submitted,
                notRobot = state.notRobot
            )
        )
    }

    private fun agreeToPrivacyPolicy(agree: Boolean) {

        val promptPrivacyPolicy = !agree

        // Update state
        setState(
            EmailInputFieldState(
                state.email,
                agreeToPrivacyPolicy = agree,
                promptPrivacyPolicy = promptPrivacyPolicy,
                submitted = state.submitted,
                notRobot = state.notRobot
            )
        )
    }

    private fun promptPrivacyPolicy(prompt: Boolean) {
        setState(
            EmailInputFieldState(
                email = state.email,
                agreeToPrivacyPolicy = state.agreeToPrivacyPolicy,
                promptPrivacyPolicy = prompt,
                submitted = state.submitted,
                notRobot = state.notRobot
            )
        )
    }

    private fun setSubmitted() {
        setState(
            EmailInputFieldState(
                email = state.email,
                agreeToPrivacyPolicy = state.agreeToPrivacyPolicy,
                promptPrivacyPolicy = state.promptPrivacyPolicy,
                submitted = true,
                notRobot = state.notRobot
            )
        )
    }

    private fun promptRecaptcha() {
        setState(
            EmailInputFieldState(
                email = state.email,
                agreeToPrivacyPolicy = state.agreeToPrivacyPolicy,
                promptPrivacyPolicy = state.promptPrivacyPolicy,
                submitted = state.submitted,
                notRobot = state.promptRecaptcha,
                promptRecaptcha = true
            )
        )
    }

    private fun setNotRobot(notRobot: Boolean) {
        setState(
            EmailInputFieldState(
                email = state.email,
                agreeToPrivacyPolicy = state.agreeToPrivacyPolicy,
                promptPrivacyPolicy = state.promptPrivacyPolicy,
                submitted = state.submitted,
                notRobot = notRobot,
                promptRecaptcha = if (notRobot) false else state.promptRecaptcha
            )
        )
    }
}


private object ReleaseDateStyle : StyleSheet("ReleaseDateStyle") {

    val inputHeight = 3.vw // 40.px
    val mobileInputHeight = 15.px

    val inputField by css {

        +noBorder

        width = 30.pct
        height = inputHeight
        backgroundColor = lighterDarkBackgroundColor
        color = greyTextColor

        padding(left = 1.vw, right = 1.vw) // 10.px

        focus { outline = Outline.none }

        marginLeft = LinearDimension.auto

        mobileScreen {
            height = mobileInputHeight
            width = 50.pct
            borderRadius = 3.px

            fontSize = 1.25.vw
        }
    }

    val noBorder by css {
        borderStyle = BorderStyle.none
        borderRadius = 5.px
    }

    val confirmButton by css {
        margin(
            left = 10.px, right = LinearDimension.auto,
            top = LinearDimension.auto, bottom = LinearDimension.auto
        )
        padding(left = 1.2.vw, right = 1.2.vw)
        height = inputHeight
        backgroundColor = Color.blue.withAlpha(0.8)

        border(width = 0.1.px, style = BorderStyle.none, whiteAlpha(0.2), borderRadius = 5.px)

        color = Color.white
        textAlign = TextAlign.center
        textDecoration = TextDecoration.none

        focus {
            outline = Outline.none
        }

        active {
            backgroundColor = blueBackgroundColor
        }

        hover {
            marginTop = 1.px
        }

        mobileScreen {
            height = mobileInputHeight
            borderRadius = 3.px
            fontSize = 1.25.vw
        }
    }

    val confirmButtonSuccessful by css {
        +confirmButton
        backgroundColor = Color.green
    }

    val confirmButtonUnsuccessful by css {
        backgroundColor = Color.darkRed
    }
}