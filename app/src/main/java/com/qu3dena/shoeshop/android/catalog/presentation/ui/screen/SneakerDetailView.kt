package com.qu3dena.shoeshop.android.catalog.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.qu3dena.shoeshop.android.catalog.domain.model.SizesAvailable
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.presentation.ui.viewmodel.SneakerDetailViewModel

@Composable
fun SneakerDetailView(
    sneakerId: Long,
    viewModel: SneakerDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = sneakerId) {
        viewModel.fetchSneakerById(sneakerId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Crossfade(targetState = uiState.value) { state ->
            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Text(
                        text = "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                else -> {
                    state.sneaker?.let { sneaker ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column {
                                SneakerDetailBannerView(sneaker)
                                SneakerDetailSizesView(sizesAvailable = sneaker.sizesAvailable)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SneakerDetailBannerView(sneaker: Sneaker) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = sneaker.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = sneaker.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Brand") }
                    append(": ${sneaker.brand}")
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Price") }
                    append(": $${sneaker.price}")
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Rating") }
                    append(": ${sneaker.rating} (${sneaker.reviewCount} reviews)")
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Description") }
                    append(": ${sneaker.description.take(100)}...")
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SneakerDetailSizesView(sizesAvailable: List<SizesAvailable>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(sizesAvailable) { size ->
            Text(
                text = "Size: ${size.size} - Quantity: ${size.quantity}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}