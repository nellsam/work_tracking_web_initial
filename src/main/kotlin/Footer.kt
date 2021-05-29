import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.borderTop
import kotlinx.html.contentEditable
import react.RBuilder
import react.dom.a
import react.dom.footer
import react.dom.nav
import styled.*
import ui.darkBackgroundColor
import ui.lighterDarkBackgroundColor
import utils.mobileScreen

object Footer {

    fun RBuilder.displayFooter() {
        styledFooter {

            css { +FooterStyle.footer }

            styledNav {

                css { +FooterStyle.footerNavigation }

                displaySupport()
            }
        }
    }

}

private fun RBuilder.displaySupport() {

    styledDiv {

        css {
            padding(5.px)
            display = Display.flex
            flexDirection = FlexDirection.column
        }

        styledH5 {
            +"Support"

            css {
                padding(0.25.vw)
                margin(0.px)

                fontSize = 1.vw

                mobileScreen {
                    fontSize = 2.25.vw
                }
            }
        }

        styledA {
            +"support@worktrackingapp.com"

            attrs {
                href = "mailto: support@worktrackingapp.com"
                target = "_blank"
                rel = "noopener noreferrer"
            }

            css {
                textDecoration = TextDecoration.none
                color = whiteAlpha(0.5)
                fontSize = 1.1.vw

                mobileScreen {
                    fontSize = 2.5.vw
                }
            }
        }
    }
}

private object FooterStyle : StyleSheet("FooterStyle") {

    val footer by css {
        clear = Clear.both
        position = Position.relative
        marginTop = (-200).px
        marginTop = LinearDimension.auto

        paddingTop = 20.px
        paddingBottom = 20.px
        borderTop(1.px, BorderStyle.solid, lighterDarkBackgroundColor)

        backgroundColor = darkBackgroundColor
        maxWidth = LinearDimension.fillAvailable

        mobileScreen {
            paddingTop = 15.px
            paddingBottom = 15.px
        }
    }

    val footerNavigation by css {
        textAlign = TextAlign.center
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent = JustifyContent.spaceAround
        alignItems = Align.center
    }
}