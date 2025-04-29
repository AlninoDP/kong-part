package com.aelin.kongpart.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aelin.kongpart.R
import com.aelin.kongpart.di.Injection
import com.aelin.kongpart.model.DummySparepartDatasource
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.ViewModelFactory
import com.aelin.kongpart.ui.common.UiState
import com.aelin.kongpart.ui.component.CategoryItem

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
                    categoryIcon = DummySparepartDatasource.categoryIcons,
                    defaultIcon = R.drawable.gears_icon,
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
    categoryIcon: Map<String, Int>,
    defaultIcon: Int,
    navigateToPart: (String) -> Unit,
    navigateToDetail: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val categories = spareparts.map { it.category.uppercase() }.distinct()

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

        Search(
            query = query,
            onQueryChange = { query = it },
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
                    CategoryItem(
                        categoryName = filteredSparepart.name,
                        categoryIcon = R.drawable.oil_icon,
                        modifier = Modifier.clickable {
                            navigateToDetail(filteredSparepart.category, filteredSparepart.id)
                        }
                    )
                }
            }
        }

        // Main Menu
        if (query.isEmpty()) {
            LazyColumn {
                items(categories) { category ->
                    val iconCategory = categoryIcon[category.lowercase()] ?: defaultIcon
                    CategoryItem(
                        categoryName = category,
                        categoryIcon = iconCategory,
                        modifier = Modifier.clickable {
                            navigateToPart(category)
                        }
                    )
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = { Text(stringResource(R.string.placeholder_search)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(min = 48.dp)
    ) { }
}
