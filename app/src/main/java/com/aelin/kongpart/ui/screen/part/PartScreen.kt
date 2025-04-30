package com.aelin.kongpart.ui.screen.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aelin.kongpart.R
import com.aelin.kongpart.di.Injection
import com.aelin.kongpart.model.DummySparepartDatasource
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.ViewModelFactory
import com.aelin.kongpart.ui.common.UiState
import com.aelin.kongpart.ui.component.Search
import com.aelin.kongpart.ui.screen.home.HomeContent
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun PartScreen(
    partCategory: String,
    viewModel: PartViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (String, Int) -> Unit,
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
                    modifier = modifier
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize()
    ) {
        // appbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.primary)
        ){

            // Back button (kiri)
            IconButton  (
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Search(
                query = "",
                onQueryChange = {},
                modifier.padding(horizontal = 56.dp).align(Alignment.Center)
            )

            // Edit button (kanan)
            IconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }


        }


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(spareparts) {
                    PartItem(
                        sparepart = it,
                        navigateToDetail = navigateToDetail,
                    )
                }
            }

        }
    }

}

@Composable
fun PartItem(
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
            // TODO: CHANGE THE IMAGE
            Image(
                painter = painterResource(R.drawable.profile_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
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
                0,
                listOf("motor", "aki", "battery")
            ),
            navigateToDetail = { _, _ -> }
        )
    }
}