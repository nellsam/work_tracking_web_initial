package utils

import kotlinx.css.CSSBuilder
import kotlinx.css.RuleSet

/**
 * Use this in your css to build responsive layout.
 */

fun CSSBuilder.mobileScreen(ruleSet: RuleSet) {
    media("(max-width: 600px)", ruleSet)
}

fun CSSBuilder.smallerScreen(ruleSet: RuleSet) {
    media("(max-width: 1000px)", ruleSet)
}