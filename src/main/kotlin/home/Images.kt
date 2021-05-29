import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.RBuilder
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.styledImg
import ui.darkBackgroundColor
import ui.lighterDarkBackgroundColor
import utils.mobileScreen
import utils.smallerScreen

fun RBuilder.displayImages() {

    styledDiv {

        css { +ImagesStyle.imagesLayout }

        styledDiv {

            css { +ImagesStyle.imagesCard }

            displayFirstImage()

            displayImageSeparator()

            displaySecondImage()
        }
    }
}


private fun RBuilder.displayFirstImage() {

    styledDiv {

        css { +ImagesStyle.imageLayout }

        styledImg {

            css { +ImagesStyle.image }

            attrs {
                src = "images/shift_tracking_night.webp"
                alt = "Tracking in progress"
            }
        }

    }

}

private fun RBuilder.displayImageSeparator() {
    styledDiv {
        css { +ImagesStyle.imageSeparator }
    }
}

private fun RBuilder.displaySecondImage() {

    styledDiv {

        css { +ImagesStyle.imageLayout }

        styledImg {
            css { +ImagesStyle.image }

            attrs {
                src = "images/attendance_night.webp"
                alt = "Attendance"
            }
        }

    }

}

private object ImagesStyle : StyleSheet("ImagesStyle") {

    private val imageHeight = 40.vw
    private val mobileImageHeight = 85.vw // imageHeight * 0.65

    val imagesLayout by css {

        float = Float.right
        width = 50.pct

        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.center
        alignItems = Align.center

        textAlign = TextAlign.center

        margin(left = LinearDimension.auto, right = LinearDimension.auto)

        smallerScreen {
            width = 100.pct
            float = Float.none
        }
    }

    val imagesCard by css {

        width = LinearDimension.fitContent
        padding(5.px)
        margin(left = LinearDimension.auto, right = LinearDimension.auto)

        // Display items in a row
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent = JustifyContent.center
        alignItems = Align.center

        backgroundColor = darkBackgroundColor
        border(width = 0.5.px, style = BorderStyle.solid, color = lighterDarkBackgroundColor, borderRadius = 25.px)
        transition(duration = 0.7.s)
    }

    val imageSeparator by css {
        width = 2.px
        height = imageHeight * 0.8
        margin(left = 10.px, right = 10.px, top = LinearDimension.auto, bottom = LinearDimension.auto)

        lighterDarkBackgroundColor.let {
            backgroundColor = it
            border(width = 1.px, style = BorderStyle.solid, it, borderRadius = 5.px)
        }

        mobileScreen {
            height = mobileImageHeight * 0.75
        }
    }

    val imageLayout by css {

        // Make it appear in the center
        margin(top = LinearDimension.auto, bottom = LinearDimension.auto)

        padding(LinearDimension.none)
    }

    val image by css {
        height = imageHeight

        media("(max-width: 600px)") {
            height = mobileImageHeight
        }
    }

}