package com.aelin.kongpart.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.aelin.kongpart.R
import com.aelin.kongpart.di.Injection
import com.aelin.kongpart.model.DummySparepartDatasource
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.ViewModelFactory
import com.aelin.kongpart.ui.common.UiState
import com.aelin.kongpart.ui.component.Search
import com.aelin.kongpart.ui.theme.KongPartTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToPart: (String) -> Unit,
    navigateToDetail: (String, Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllSpareparts()
            }

            is UiState.Success -> {
                HomeContent(
                    spareparts = uiState.data,
                    navigateToDetail = navigateToDetail,
                    navigateToPart = navigateToPart,
                    modifier = modifier,
                )
            }

            is UiState.Error -> {}
        }

    }
}

@Composable
fun HomeContent(
    spareparts: List<Sparepart>,
    navigateToPart: (String) -> Unit,
    navigateToDetail: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 130.dp) ,

        ) {
        val categories = spareparts.map { it.category }.distinct()

        var query by remember { mutableStateOf("") }

        val filteredSpareparts =
            spareparts.filter {
                it.name.contains(query, ignoreCase = true) || it.tags.any { tag ->
                    tag.contains(
                        query,
                        ignoreCase = true
                    )
                }
            }

        val nearEmptyStockSpareparts = spareparts.filter { it.stock <= 10 }




        Search(
            query = query,
            onQueryChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 54.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        // Search Result
        if (query.isNotEmpty() && filteredSpareparts.isNotEmpty()) {
            Text(
                text = "Hasil Pencarian",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            // Menampilkan hasil pencarian dalam LazyColumn
            LazyColumn {
                items(filteredSpareparts) { filteredSparepart ->
                    SearchResultCategoryItem(
                        partName = filteredSparepart.name,
                        partImage = filteredSparepart.imageUrl,
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable {
                                navigateToDetail(
                                    filteredSparepart.category,
                                    filteredSparepart.id
                                )
                            }


                    )
                }
            }
        }

        // Main Menu
        if (query.isEmpty()) {

            Text(
                text = stringResource(R.string.part_category),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(categories) { category ->
                    GridItemCategory(
                        partCategory = category,
                        modifier = modifier.clickable {
                            navigateToPart(category)
                        }
                    )
                }
            }

            Text(
                text = stringResource(R.string.part_nearing_empty),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xffF8F4EC))
            )  {
                items(nearEmptyStockSpareparts) {
                    NearEmptyStockGrid(
                        partName = it.name,
                        partStock = it.stock,
                        partImage = it.imageUrl,
                        modifier = modifier
                            .clickable {
                                navigateToDetail(it.category, it.id)
                            }
                    )
                }
            }

        }


    }
}


@Composable
private fun SearchResultCategoryItem(
    partName: String,
    partImage: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            AsyncImage(
                model = partImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = partName,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterVertically),
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}


@Composable
private fun GridItemCategory(
    partCategory: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFBBDEFB)) // TODO:CHANGE COLOR
    ) {
        // Text
        Text(
            text = partCategory.replaceFirstChar { it.uppercase() },
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp)
        )


        // Background
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.gears),
                contentDescription = null,
                alpha = 0.2f,
                colorFilter = ColorFilter.tint(color = Color.Gray)
            )
        }
    }

}

@Composable
fun NearEmptyStockGrid(
    partName: String,
    partStock: Int,
    partImage: String,
    modifier: Modifier = Modifier,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = partImage,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = partName,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append("$partStock ")
                    }
                    append("Pcs")
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NearEmptyStockGridPreview() {
    KongPartTheme {
        NearEmptyStockGrid(
            partStock = 4,
            partName = "Aki GS",
            partImage = "https://yutakasugih.maketcreator.com/wp-content/uploads/2024/10/aki-gs-astra-gtz7s.jpg"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GridItemPreview() {
    KongPartTheme {
        GridItemCategory(
            "aki"
        )
    }
}


@Preview
@Composable
private fun CategoryItemPreview() {
    KongPartTheme {
        SearchResultCategoryItem(
            "Busi",
            partImage = "https://yutakasugih.maketcreator.com/wp-content/uploads/2024/10/aki-gs-astra-gtz7s.jpg",
            modifier = Modifier.padding(8.dp),
        )
    }
}


