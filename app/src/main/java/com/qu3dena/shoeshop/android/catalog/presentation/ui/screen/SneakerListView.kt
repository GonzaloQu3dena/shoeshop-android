package com.qu3dena.shoeshop.android.catalog.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.presentation.ui.viewmodel.SneakerListViewModel

@Composable
fun SneakerListView(
    onSneakerDetail: (Long) -> Unit,
    viewModel: SneakerListViewModel = hiltViewModel(),
) {

    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            uiState.value.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.value.error != null -> {
                Text(
                    text = "Error: ${uiState.value.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                LazyColumn {
                    items(uiState.value.sneakers) { sneaker ->
                        SneakerItemView(
                            sneaker = sneaker,
                            onSneakerDetail = {
                                onSneakerDetail(sneaker.id)
                            },
                            onToggleFavorite = {
                                viewModel.toggleFavorite(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SneakerItemView(
    sneaker: Sneaker,
    onSneakerDetail: () -> Unit,
    onToggleFavorite: (Sneaker) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onSneakerDetail()
                }
            ),
        elevation = CardDefaults.cardElevation()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = sneaker.image,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = sneaker.name, fontWeight = FontWeight.Bold)
                Text(
                    text = "${sneaker.description} • ${sneaker.price} USD"
                )
            }

            IconButton(onClick = { onToggleFavorite(sneaker) }) {
                Icon(
                    imageVector = if (sneaker.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Toggle Favorite"
                )
            }
        }
    }
}
