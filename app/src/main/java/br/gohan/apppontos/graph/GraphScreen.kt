package br.gohan.apppontos.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.gohan.apppontos.components.bar_chart_products.BarChartProducts
import br.gohan.apppontos.components.bar_chart_score.BarChartUsersScore
import br.gohan.apppontos.components.linechart.LineChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphScreen(email: String?, backButton: () -> Unit) {
    val notification = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()
    var topBarText by remember { mutableStateOf("Statistics") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(notification)
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = backButton) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "Back"
                        )
                    }
                },
                title = { Text(topBarText, textAlign = TextAlign.Center) })
        },
        content = { padding ->
            LaunchedEffect(true) {
                notification.showSnackbar("Welcome back $email")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(top = padding.calculateTopPadding()))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LineChart()
                HorizontalDivider()
                Spacer(Modifier.height(32.dp))
                BarChartProducts()
                HorizontalDivider()
                Spacer(Modifier.height(32.dp))
                BarChartUsersScore()
                Spacer(Modifier.height(64.dp))
            }
        })

}