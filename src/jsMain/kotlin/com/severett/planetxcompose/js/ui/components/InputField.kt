package com.severett.planetxcompose.js.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input

@Composable
fun <K> InputField(type: InputType<K>, placeHolder: String, onChange: (String) -> Unit) {
    Input(type = type, attrs = {
        placeholder(placeHolder)
        onInput { onChange.invoke(it.value.toString()) }
        style {
            listOf("top", "left", "right").forEach { position ->
                property("border-$position", "none")
            }
            fontSize(18.px)
            margin(12.px, 50.px)
        }
    })
}
