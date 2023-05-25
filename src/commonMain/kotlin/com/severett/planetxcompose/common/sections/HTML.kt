package com.severett.planetxcompose.common.sections

import com.severett.planetxcompose.common.model.s
import kotlinx.html.a
import kotlinx.html.b
import kotlinx.html.div
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import kotlinx.html.style

fun generateHTML(
    helloStr: String,
    isBold: Boolean,
    isUnderlined: Boolean,
    isStrikethrough: Boolean
): String {
    val underlineStr = if (isUnderlined) "text-decoration-line: underline;" else null
    return createHTML().div {
        p {
            if (underlineStr != null) style = underlineStr
            when {
                isBold -> b { if (isStrikethrough) s { +helloStr } else +helloStr }
                isStrikethrough -> s { +helloStr }
                else -> +helloStr
            }
        }
        a("https://github.com/kotlin/kotlinx.html") {
            +"Would you like to know more?"
        }
    }
}