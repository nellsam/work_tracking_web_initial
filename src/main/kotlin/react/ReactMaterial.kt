@file:JsModule("@material-ui/core")
@file:JsNonModule

package react

import org.w3c.dom.events.Event
import styled.StyleSheet


@JsName("Dialog")
external val alertDialog: RClass<DialogProps>

external interface DialogProps : RProps {
    var children: dynamic
    var onCLose: Unit
    var onEnter: Unit
    var maxWidth: Int
    var fullScreen: Boolean
    var fullWidth: Boolean
    var open: Boolean
    var backdropprops : dynamic
    var papercomponent : ReactElement
    var paperprops : dynamic
    var style : dynamic
    var overlaystyle : dynamic
    var arialabelledby: dynamic
    var ariadescribedby: dynamic
}

@JsName("DialogActions")
external val dialogActions: RClass<DialogActionProps>

external interface DialogActionProps : RProps {
    var children: dynamic
    var spacing: Boolean
}

@JsName("Button")
external val button: RClass<ButtonProps>

external interface ButtonProps : RProps {

    var onClick: () -> Unit
    var autoFocus: Boolean
    var color: dynamic
    var disabled: Boolean
    var fullWidth: String
    var size: dynamic
    var href: String
    var startIcon: dynamic
    var variant: String
}

@JsName("DialogContent")
external val dialogContent: RClass<DialogContentProps>

external interface DialogContentProps : RProps {
    var children: dynamic
    var dividers: Boolean
}

@JsName("DialogContentText")
external val dialogContentText: RClass<DialogContentTextProps>


external interface DialogContentTextProps : RProps {
    var children: dynamic
}

@JsName("DialogTitle")
external val dialogTitle: RClass<DialogTitleProps>

external interface DialogTitleProps : RProps {
    var children: dynamic
}

@JsName("Paper")
external val paper: RClass<PaperProps>

external interface PaperProps : RProps {

    // Styles applied to the root element.
    val root: StyleSheet

    /**
     * Override or extend the styles applied to the component.
     */
    var classes : dynamic

}

@JsName("Snackbar")
external val snackbar : RClass<SnackbarProps>

external interface SnackbarProps : RProps {
    var open: Boolean
    var autoHideDuration : Int
    var onClose: (Event, Any) -> Unit
}

@JsName("Checkbox")
external val checkbox: RClass<CheckboxProps>

external interface CheckboxProps : RProps {

    var onClick: () -> Unit
    var autoFocus: Boolean

    var checked : Boolean
    var inputProps : dynamic
    var color: dynamic
    var disabled: Boolean
    var onChange : (Event) -> Unit
}

