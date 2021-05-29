package home

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.border
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.*
import styled.*
import ui.blueBackgroundColor
import ui.darkBackgroundColor
import ui.greyTextColor
import utils.mobileScreen
import utils.smallerScreen

fun RBuilder.displayDescription() {

    styledDiv {

        css { +DescriptionStyle.descriptionLayout }

        displayDescriptionHeader()

        displayDescriptionFooter()
    }

}

private fun RBuilder.displayDescriptionHeader() {
    styledDiv {

        css { +DescriptionStyle.descriptionHeader }

        displayDescriptionPoints()

        child(learnMoreOption)
    }
}

private fun RBuilder.displayDescriptionFooter() {
    styledDiv {

        css { +DescriptionStyle.descriptionFooter }

        displayAttendance()

        displayMap()
    }
}

private fun RBuilder.displayDescriptionPoints() {

    styledDiv {

        css {
            margin(left = LinearDimension.auto, right = LinearDimension.auto)
        }

        styledUl {

            +"Simple, concise app that tracks employees"
            css {
                +DescriptionStyle.descriptionTitle
            }

            styledLi {

                +"Get attendance of your employees"

                attrs { id = "descriptionPoint1" }

                css {
                    +DescriptionStyle.descriptionItem
                    marginTop = 10.px
                }
            }

            styledLi {
                +"See where your employees are"

                attrs { id = "descriptionPoint2" }

                css {
                    +DescriptionStyle.descriptionItem
                }
            }

            styledLi {
                +"Define your rules"

                attrs { id = "descriptionPoint3" }

                css {
                    +DescriptionStyle.descriptionItem
                }
            }
        }
    }

}


private val learnMoreOption
    get() = functionalComponent<RProps> {

        val (open, setOpen) = useState(false)

        val handleClickOpen = { setOpen(true) }

        val handleClose = { setOpen(false) }

        styledDiv {

            css {
                textAlign = TextAlign.right

                smallerScreen {
                    textAlign = TextAlign.center
                }
            }

            styledA {
                +"Learn more in About"
                attrs {
                    href = ""
                    onClickFunction = {
                        it.preventDefault()
                        handleClickOpen()
                    }
                }
                css {
                    textAlign = TextAlign.right
                    fontSize = 1.vw // 12px
                    color = whiteAlpha(0.5)
                    textDecoration = TextDecoration.none
                    hover { color = blueBackgroundColor }

                    media("(max-width: 600px)") {
                        fontSize = 6.px
                    }
                }
            }
        }

        alertDialog {

            attrs {
                this.open = open
                arialabelledby = "alert-dialog-title"
                ariadescribedby = "alert-dialog-description"

                backdropprops = "style: { backgroundColor: 'transparent' }"

                overlaystyle = js("{{backgroundColor: 'transparent'}}")

                papercomponent = paper {
                    attrs {
                        classes = js("style: { backgroundColor: 'transparent' }")
                    }
                }

            }

            dialogContent {

                styledDiv {
                    css {
                        textAlign = TextAlign.center
                    }

                    styledH3 {
                        +"App description available soon"
                        css {
                            color = Color.blue
                            fontSize = 1.vw
                        }
                    }

                    styledDiv {

                        css {
                            display = Display.flex
                            flexDirection = FlexDirection.row
                            justifyContent = JustifyContent.spaceBetween
                            alignItems = Align.center

                            margin(LinearDimension.auto)
                        }

                        styledImg {
                            attrs {
                                src = "images/shift_day.webp"
                                alt = "Shift day mode"
                            }

                            css { +DescriptionStyle.dialogImage }
                        }

                        styledImg {
                            attrs {
                                src = "images/attendance_day.webp"
                                alt = "Attendance day mode"
                            }

                            css { +DescriptionStyle.dialogImage }
                        }
                    }

                }
            }

            dialogActions {
                button {
                    +"Cancel"

                    attrs {
                        autoFocus = true
                        color = "primary"
                        variant = "outlined"
                        onClick = {
                            this@alertDialog.attrs {
                                handleClose.invoke()
                            }
                        }
                    }
                }
            }

        }
    }


private fun RBuilder.displayAttendance() {
    styledDiv {

        css { +DescriptionStyle.descriptionImageSection }

        displayAttendanceText()

        displayImageSeparator()

        displayAttendanceImage()
    }
}

private fun RBuilder.displayAttendanceText() {
    styledDiv {

        css { textAlign = TextAlign.center }

        styledP {
            +"Get validated attendance and month total of your organisation's each member."

            css { +DescriptionStyle.descriptionImageText }
        }
    }

}


private fun RBuilder.displayAttendanceImage() {
    styledImg {

        attrs {
            src = "images/half/half_attendance_night.webp"
        }

        css {
            +DescriptionStyle.descriptionImage
        }
    }
}

private fun RBuilder.displayMap() {

    styledDiv {

        css { +DescriptionStyle.descriptionImageSection }

        displayMapImage()

        displayImageSeparator()

        displayMapText()
    }
}

private fun RBuilder.displayMapText() {
    styledDiv {

        css {
            textAlign = TextAlign.center
        }

        styledP {
            +"See where your employees started and ended."
            css {
                +DescriptionStyle.descriptionImageText
            }
        }
    }

}

private fun RBuilder.displayMapImage() {
    styledImg {

        attrs {
            src = "images/half/map_half_night.webp"
        }

        css {
            +DescriptionStyle.descriptionImage
        }
    }
}

private fun RBuilder.displayImageSeparator() {
    styledDiv {
        css {
            +DescriptionStyle.imageSeparator
        }
    }
}

private object DescriptionStyle : StyleSheet("DescriptionStyle") {

    // Whole layout
    val descriptionLayout by css {

        width = 50.pct
        float = Float.left

        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.center

        margin(left = LinearDimension.auto, right = LinearDimension.auto)

        smallerScreen {
            float = Float.none
            width = LinearDimension.fitContent
        }
    }

    val descriptionHeader by css {

        // Display in left top corner
        margin(left = 30.px, top = 30.px, bottom = LinearDimension.auto, right = LinearDimension.auto)

        smallerScreen {
            margin(left = LinearDimension.auto, right = LinearDimension.auto, top = 10.px, bottom = 10.px)
            width = 100.pct
        }
    }

    val descriptionTitle by css {
        fontWeight = FontWeight.bold
        fontSize = 1.9.vw

        smallerScreen {
            fontSize = 15.px
        }
    }

    val descriptionItem by css {

        fontSize = 1.4.vw

        listStyleType = ListStyleType.unset
        fontWeight = FontWeight.normal
        color = whiteAlpha(0.8)

        smallerScreen {
            fontSize = 10.px
        }
    }

    // Footer
    val descriptionFooter by css {

        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        alignContent = Align.center

        width = LinearDimension.fitContent

        margin(left = LinearDimension.auto, right = LinearDimension.auto, top = 75.px, bottom = 50.px)
        padding(top = 10.px, bottom = 10.px, left = 15.px, right = 15.px)
        backgroundColor = darkBackgroundColor
        border(1.px, BorderStyle.solid, backgroundColor, 15.px)

        mobileScreen {
            margin(top = 15.px, bottom = 15.px)
            padding(top = 2.px, bottom = 2.px, left = 4.px, right = 4.px)
        }
    }

    val descriptionImageSectionWidth = 20.vw // 250.px
    val descriptionImageWidth = descriptionImageSectionWidth * 0.8

    val mobileDescriptionImageSectionWidth = 45.vw // descriptionImageSectionWidth * 0.65
    val mobileDescriptionImageWidth = 30.vw // descriptionImageWidth * 0.65

    val descriptionImageSection by css {

        // Display items under each other
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        width = descriptionImageSectionWidth

        mobileScreen {
            width = mobileDescriptionImageSectionWidth
        }
    }

    val descriptionImage by css {
        width = descriptionImageWidth

        mobileScreen {
            width = mobileDescriptionImageWidth
        }
    }

    val descriptionImageText by css {
        color = greyTextColor
        fontSize = 0.9.vw

        mobileScreen {
            fontSize = 2.vw
        }
    }

    val imageSeparator by css {

        height = 1.px
        width = descriptionImageWidth * 1.1
        margin(left = LinearDimension.auto, right = LinearDimension.auto)

        val separatorColor = Color.blue.withAlpha(0.8)
        backgroundColor = separatorColor
        border(1.px, BorderStyle.solid, separatorColor, 2.px)

        mobileScreen {
            height = 0.1.vw
            width = mobileDescriptionImageWidth * 1.1
            borderRadius = 1.px
        }
    }

    val dialogImageHeight = 20.vw

    val dialogImage by css {

        height = dialogImageHeight
        margin(left = 2.vw, right = 2.vw, top = 1.vw, bottom = 1.vw)
    }

}