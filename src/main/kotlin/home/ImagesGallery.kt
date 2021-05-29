package home

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.html.js.onClickFunction
import react.*
import styled.*
import ui.blueBackgroundColor

/**
 * Images gallery was buggy and not used in published version.
 */

val moreScreenshotsOption = functionalComponent<RProps> {

    val (open, setOpen) = useState(false)

    val handleClickOpen = {
        setOpen(true)
    }

    val handleClose = {
        setOpen(false)
    }

    styledA {
        +"See more"

        attrs {
            href = "#"
            onClickFunction = {
                handleClickOpen()
            }
        }

        css { +DialogStyle.seeMoreText }
    }

    alertDialog {

        attrs {
            this.open = open
            arialabelledby = "alert-dialog-title"
            ariadescribedby = "alert-dialog-description"

            backdropprops = "style: { backgroundColor: 'transparent' }"

            style = js("{ {width: '200px'; marginLeft: '40%'; backgroundColor: 'transparent'}}")
            overlaystyle = js("{{backgroundColor: 'transparent'}}")

            papercomponent = paper {
                attrs {
                    classes = js("style: { backgroundColor: 'transparent' }")
                }
            }

        }

        dialogContent {
            +"Welcome"
            displayImages()
        }

        dialogActions {
            button {
                +"Cancel"

                attrs {
                    autoFocus = true
                    color = "inherit"
                    variant = "outlined"
                    onClick = {
                        this@alertDialog.attrs {
                            println("Button clicked")
                            handleClose.invoke()
                        }
                    }
                }
            }
        }
    }

}

private fun RBuilder.displayImages() {

    styledDiv {

        css {
            backgroundColor = Color.darkGrey.withAlpha(0.1)
            overflow = Overflow.hidden
            textAlign = TextAlign.center
            margin(LinearDimension.auto)
            position = Position.relative
        }

        imageGallery {

            attrs {
                items = images
                originalTitle = "Screenshots"
                autoPlay = true
                infinite = true
                slideDuration = 500
                slideInterval = 4000
                showIndex = false
                showBullets = false
                showThumbnails = false
                showFullscreenButton = false
                showGalleryFullscreenButton = false
                showPlayButton = false
                showGalleryPlayButton = false
                showNav = false
            }
        }
    }

}

private val images: Array<ImageItem>
    get() {

        val shiftTrackingNightImage = ImageItem(
            original = "images/shift_tracking_night.webp",
            thumbnail = "images/shift_tracking_night.webp"
        )

        val attendanceNightImage = ImageItem(
            original = "images/attendance_night.webp",
            thumbnail = "images/attendance_night.webp"
        )

        val shiftNightImage = ImageItem(
            original = "images/shift_night.webp",
            thumbnail = "images/shift_night.webp"
        )

        val editDurationNightImage = ImageItem(
            original = "images/edit_duration_night.webp",
            thumbnail = "images/edit_duration_night.webp"
        )

        return arrayOf(shiftTrackingNightImage, attendanceNightImage, shiftNightImage, editDurationNightImage)
    }


private object DialogStyle : StyleSheet("DialogStyle") {

    val seeMoreText by css {
        color = whiteAlpha(0.5)
        fontSize = 12.px
        textDecoration = TextDecoration.none
        padding(5.px)
        margin(0.px)

        hover { color = blueBackgroundColor }

        width = LinearDimension.fitContent
        textAlign = TextAlign.center
        margin(left = LinearDimension.auto, right = LinearDimension.auto)
    }

}