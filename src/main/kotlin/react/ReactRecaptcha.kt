@file:JsModule("react-google-recaptcha")
@file:JsNonModule

package react

/**
 * Recaptcha wrapper for Kotlin-JS React
 */

@JsName("default")
external val recaptcha: RClass<RecaptchaProps>

external interface RecaptchaProps : RProps {

    var sitekey : String
    var onChange : (dynamic) -> Unit
    var theme : String
    var size : String
    var badge : String
    var onExpired : () -> Unit
}