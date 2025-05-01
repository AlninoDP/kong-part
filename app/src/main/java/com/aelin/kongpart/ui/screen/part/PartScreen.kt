package com.aelin.kongpart.ui.screen.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.aelin.kongpart.R
import com.aelin.kongpart.di.Injection
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.ViewModelFactory
import com.aelin.kongpart.ui.common.UiState
import com.aelin.kongpart.ui.component.Search
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun PartScreen(
    partCategory: String,
    viewModel: PartViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (String, Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getSparepartByCategory(partCategory)
            }

            is UiState.Success -> {
                PartContent(
                    spareparts = uiState.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier,
                    navigateBack = navigateBack,
                )
            }

            is UiState.Error -> {}
        }

    }
}

@Composable
fun PartContent(
    spareparts: List<Sparepart>,
    navigateToDetail: (String, Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        var searchQuery by remember  { mutableStateOf("") }

        val filteredParts = spareparts.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }


        // Appbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 54.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = navigateBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back"
                )
            }

            Text(
                text = spareparts.first().category.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.Center)
            )

        }


        Text(
            text = stringResource(R.string.part_header_text, spareparts.first().category),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        Text(
            text = stringResource(R.string.part_sub_header_text, spareparts.first().category),
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top= 4.dp )
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            item {
                Search(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it},
                   modifier = modifier.padding(bottom = 8.dp)
                )
            }
            items(filteredParts) {
                PartItem(
                    sparepart = it,
                    navigateToDetail = navigateToDetail,
                )
                Spacer(modifier.height(8.dp))
            }
        }
    }
}


@Composable
private fun PartItem(
    sparepart: Sparepart,
    navigateToDetail: (String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { navigateToDetail(sparepart.category, sparepart.id) }
    ) {
        Row(
            modifier = modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = sparepart.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    sparepart.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = stringResource(R.string.sale_prices, sparepart.price.toInt()),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(R.string.remaining_stocks, sparepart.stock),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PartItemPreview() {
    KongPartTheme {
        PartItem(
            sparepart = Sparepart(
                1,
                "Yuasa",
                "Lorem ipsum dolor amet",
                25,
                500000.0,
                "aki",
                "0",
                listOf("motor", "aki", "battery")
            ),
            navigateToDetail = { _, _ -> }
        )
    }
}