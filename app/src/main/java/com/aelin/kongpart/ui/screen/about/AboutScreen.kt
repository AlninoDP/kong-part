package com.aelin.kongpart.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aelin.kongpart.R
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(8.dp)
                .size(120.dp)
                .clip(CircleShape)
        )

        Text("Alnino Dio Putera")

        Text("alninodp@gmail.com")
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AboutScreenPreview() {
    KongPartTheme {
        AboutScreen()
    }
}