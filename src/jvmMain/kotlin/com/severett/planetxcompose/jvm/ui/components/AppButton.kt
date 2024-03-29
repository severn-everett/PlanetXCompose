package com.severett.planetxcompose.jvm.ui.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.severett.planetxcompose.jvm.ui.theme.Beige
import com.severett.planetxcompose.jvm.ui.theme.lexendFamily

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    fontSize: TextUnit
) {
    FilledTonalButton(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(containerColor = Beige),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontFamily = lexendFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
