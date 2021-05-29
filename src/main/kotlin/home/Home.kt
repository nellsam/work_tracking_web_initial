import home.displayDescription
import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv

fun RBuilder.displayHome() {

    styledDiv {

        displayAppDescription()

        displayReleaseDate()
    }

}

private fun RBuilder.displayAppDescription() {
    styledDiv {

        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center

            margin(left = LinearDimension.auto, right = LinearDimension.auto)

            media("(max-width: 1000px)") {
                // Items displayed in column on smaller screen
                display = Display.flex
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.spaceBetween
                alignItems = Align.center
            }
        }

        displayDescription()
        displayImages()
    }
}
