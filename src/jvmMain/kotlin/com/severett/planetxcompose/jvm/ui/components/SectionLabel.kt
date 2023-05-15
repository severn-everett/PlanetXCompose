package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.severett.planetxcompose.jvm.ui.theme.ApiumBlack
import com.severett.planetxcompose.jvm.ui.theme.ApiumGreen

@Composable
fun SectionLabel(
    modifier: Modifier = Modifier,
    text: String = "",
    padding: Dp = 0.dp,
    textAlign: TextAlign? = null
) {
    Box(
        modifier = modifier
            .height(36.dp)
            .fillMaxWidth()
            .background(
                shape = RectangleShape,
                color = ApiumGreen,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = padding, end = padding),
            textAlign = textAlign,
            color = ApiumBlack
        )
    }
}
