package br.gohan.apppontos.graph

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.composables.bar.BarGraph
import com.jaikeerthick.composable_graphs.composables.bar.model.BarData
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.pie.PieChart
import com.jaikeerthick.composable_graphs.composables.pie.model.PieData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphScreen(email: String?, backButton: () -> Unit) {
    val notification = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()
    var topBarText by remember { mutableStateOf("Graph Screen") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(notification)
        },
        topBar = {
            TopAppBar(
                navigationIcon = { IconButton(onClick = backButton) { Icon(Icons.Filled.ArrowBack, "Back") } },
                title = { Text(topBarText) }) },
        content = { padding ->
            LaunchedEffect(true) {
                notification.showSnackbar("Welcome back $email")
            }
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val lineData = listOf(
                    LineData(x = "Sun", y = 200),
                    LineData(x = "Mon", y = 40),
                    LineData(x = "Tue", y = 150),
                    LineData(x = "Wed", y = 80),
                    LineData(x = "Thu", y = 120),
                    LineData(x = "Fri", y = 90),
                    LineData(x = "Sat", y = 60)
                )

                Spacer(Modifier.height(32.dp))
                LineGraph(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    data = lineData,
                    onPointClick = { value: LineData ->
                        coroutine.launch {
                            topBarText = "X: ${value.x}, Y: ${value.y}"
                        }
                    },
                )
                Spacer(Modifier.height(32.dp))
                val barData = listOf(
                BarData(x = "22", y = 20),
                BarData(x = "23", y = 30),
                BarData(x = "24", y = 25),
                BarData(x = "25", y = 35),
                BarData(x = "26", y = 40),
                BarData(x = "27", y = 45),
                BarData(x = "28", y = 50)
            )
                BarGraph(
                    data = barData,
                    onBarClick = { value: BarData ->
                        coroutine.launch {
                            topBarText = "X ${value.x}, Y: ${value.y}"
                        }
                    }
                )
                Spacer(Modifier.height(32.dp))

                // sample data
                val pieChartData = listOf(
                    PieData(value = 130F, label = "HTC", color = Color.Green),
                    PieData(value = 260F, label = "Apple", labelColor = Color.Blue),
                    PieData(value = 500F, label = "Google"),
                )

                PieChart(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .size(220.dp),
                    data = pieChartData,
                    onSliceClick = { pieData ->
                        coroutine.launch {
                            topBarText = "${pieData.label} - ${pieData.value}"
                        }
                    }
                )

                Spacer(Modifier.height(32.dp))
            }
        })

}