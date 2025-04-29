package com.aelin.kongpart.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun DetailCategoryItem(text:String, modifier: Modifier = Modifier) {
    Surface  (
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(50),
        shadowElevation = 4.dp,
        modifier = Modifier
            .padding(end = 8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview (showBackground = true )
@Composable
private fun DetailCategoryItemPreview() {
    KongPartTheme {
        DetailCategoryItem("Category")
    }
}