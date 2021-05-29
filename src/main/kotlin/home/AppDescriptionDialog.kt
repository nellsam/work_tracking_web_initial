package home

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.html.js.onClickFunction
import react.*
import styled.*
import ui.blueBackgroundColor


private val learnMoreOption
    get() = functionalComponent<RProps> {

        val (open, setOpen) = useState(false)

        val handleClickOpen = { setOpen(true) }

        val handleClose = { setOpen(false) }

        styledDiv {

            css {
                textAlign = TextAlign.right
            }

            styledA {
                +"Learn more in About"
                attrs {
                    href = "#"
                    onClickFunction = { handleClickOpen() }
                }
                css {
                    textAlign = TextAlign.right
                    fontSize = 12.px
                    color = whiteAlpha(0.5)
                    textDecoration = TextDecoration.none
                    hover { color = blueBackgroundColor }
                }
            }
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

                styledDiv {
                    css {
                        padding(left = 80.px, right = 80.px, top = 10.px, bottom = 10.px)
                        textAlign = TextAlign.center
                    }

                    styledH3 {
                        +"App description available soon"
                        css {
                            color = Color.blue
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
                                src = "images/shift_day.png"
                                alt = "Shift day mode"
                            }

                            css {
                                height = 400.px
                                margin(right = 25.px)
                            }
                        }

                        styledImg {
                            attrs {
                                src = "images/attendance_day.png"
                                alt = "Attendance day mode"
                            }

                            css {
                                height = 400.px
                                margin(left = 25.px)
                            }
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
                                println("Button clicked")
                                handleClose.invoke()
                            }
                        }
                    }
                }
            }
        }
    }

val descriptionAvailableSoonSnackbar
    get() = functionalComponent<RProps> {

        val (open, setOpen) = useState(true)

        val handleClickOpen = { setOpen(true) }

        styledDiv {

            css {
                textAlign = TextAlign.right
            }

            styledA {
                +"Learn more in About"
                attrs {
                    href = "#"
                    onClickFunction = { handleClickOpen() }
                }
                css {
                    textAlign = TextAlign.right
                    fontSize = 12.px
                    color = whiteAlpha(0.5)
                    textDecoration = TextDecoration.none
                    hover { color = blueBackgroundColor }
                }
            }
        }

        styledDiv {

            snackbar {

                attrs {
                    this.open = true
                    autoHideDuration = 4000
                    onClose = { event, reason ->
                        run {
                            if (reason == "clickaway") {
                                return@run
                            }

                            setOpen(false)
                        }
                    }
                }

                button {
                    +"Cancel"

                    attrs {
                        color = "primary"
                        variant = "outlined"

                    onClick = { setOpen(false) }
                    }
                }

            }

        }
    }