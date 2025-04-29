package com.aelin.kongpart.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aelin.kongpart.R
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {

}

@Composable
fun DetailContent(
     name: String,
     description: String,
     stock: Int,
     price: Double,
     category: String,
     @DrawableRes image: Int,
     tags: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(150.dp)
                    .width(150.dp)
            )
            Column {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    )
                Text(
                    text = stringResource(R.string.remaining_stocks, stock),
                )
                Text(text = stringResource(R.string.sale_prices, price))
            }
        }

        Text(stringResource(R.string.category))
        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Text(category)
            for (tag in tags ){
                Text(tag)
            }
        }

        Text(stringResource(R.string.description))
        Card (
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ){
            Text(description, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailContentPreview() {
    KongPartTheme {
        DetailContent(
            image = R.drawable.oil_icon,
            name = "Yamalube 0.8",
            stock = 10,
            price = 56000.0,
            category = "Oli",
            description = "Oli Yamalube",
            tags = listOf("Oli", "Motor", "0.8L")
        )
    }
}