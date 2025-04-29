package com.aelin.kongpart.ui.screen.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun PartScreen(
    partCategory: String,
    modifier: Modifier = Modifier,
) {

}

@Composable
fun PartContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(6.dp),
            modifier = modifier

                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Nama",
                    modifier = modifier.weight(1f)
                )

                Text(
                    text = "1 Pcs",
                    textAlign = TextAlign.End,
                )
            }

        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PartContentPreview() {
    KongPartTheme {
        PartContent()
    }
}