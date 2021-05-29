import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.borderBottom
import react.RBuilder
import styled.*
import ui.blueNavigationColor
import ui.darkBackgroundColor
import ui.greyTextColor
import utils.mobileScreen

object Navigation {

    fun RBuilder.displayNavigation() {

        styledNav {

            css { +NavigationStyle.navigation }

            displayTitle()

            displayNavigationItems()
        }

    }

    private fun RBuilder.displayTitle() {

        styledDiv {

            css {
                float = Float.left
                width = 25.pct
            }

            styledA {

                attrs { href = "/" }
                css {
                    +NavigationStyle.navTitle
                }

                styledH2 {
                    +"Work Tracking"
                }
            }

        }

        styledDiv {
            // Fill space between title and navigation items
            css {
                width = 25.pct

                mobileScreen {
                    width = 10.pct
                }
            }
        }
    }

    private fun RBuilder.displayNavigationItems() {

        styledDiv {

            css {
                float = Float.right
                width = 50.pct
            }

            styledNav {

                css { +NavigationStyle.navItems }

                styledA {
                    +"Home"
                    attrs { href = "#Home" }
                    css { +NavigationStyle.navItem }
                }
                styledA {
                    +"About"
                    attrs { href = "#About" }
                    css { +NavigationStyle.navItem }
                }
                styledA {
                    +"Help"
                    attrs { href = "#Help" }
                    css { +NavigationStyle.navItem }
                }
                styledA {
                    +"Sign in"
                    attrs { href = "#SignIn" }
                    css { +NavigationStyle.navItem }
                }
            }
        }

    }

    private object NavigationStyle : StyleSheet("NavigationStyle") {

        val navItems by css {
            marginTop = LinearDimension.auto
            marginBottom = LinearDimension.auto

            textAlign = TextAlign.center
            display = Display.flex
            flexDirection = FlexDirection.row
            justifyContent = JustifyContent.center
            alignItems = Align.center

            overflow = Overflow.hidden

            margin(top = LinearDimension.auto, bottom = LinearDimension.auto)
        }

        val navigation by css {

            display = Display.flex
            flexDirection = FlexDirection.row
            justifyContent = JustifyContent.center
            alignItems = Align.center

            maxWidth = LinearDimension.fillAvailable
            textAlign = TextAlign.center

            backgroundColor = blueNavigationColor
            borderBottom(1.px, BorderStyle.solid, greyTextColor)

            mobileScreen {
                backgroundColor = darkBackgroundColor
                borderBottom(0.1.vw, BorderStyle.solid, blueNavigationColor)
                padding(top = 10.px, bottom = 10.px)
            }
        }

        val navTitle by css {
            color = Color.white
            textDecoration = TextDecoration.none
            margin(top = LinearDimension.auto, bottom = LinearDimension.auto)
            fontSize = 1.2.vw

            mobileScreen {
                fontSize = 2.25.vw
            }
        }

        val navItem by css {

            // TODO : Temporary hidden items
            visibility = Visibility.hidden

            padding(20.px)
            margin(10.px)

            fontSize = 1.vw
            fontWeight = FontWeight.bold
            color = Color.white
            textDecoration = TextDecoration.none

            overflow = Overflow.auto
            textOverflow = TextOverflow.clip
            wordBreak = WordBreak.keepAll

            hover { color = Color.grey }

            mobileScreen {
                padding(5.px)
                margin(5.px)
                fontSize = 2.vw
            }
        }
    }

}