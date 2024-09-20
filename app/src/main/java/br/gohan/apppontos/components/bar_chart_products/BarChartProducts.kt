package br.gohan.apppontos.components.bar_chart_products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.apppontos.ui.theme.highlightColor
import com.jaikeerthick.composable_graphs.composables.bar.BarGraph
import com.jaikeerthick.composable_graphs.composables.bar.model.BarData
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphColors
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphFillType
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphVisibility

@Composable
fun BarChartProducts(modifier: Modifier = Modifier) {
    val productSelected by remember { mutableStateOf(productsPoints.first()) }

    var product by remember { mutableStateOf(productSelected.x) }
    var points by remember { mutableStateOf(productSelected.y) }

    Column(
        Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Products by points",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        Row {
            Text("Type of product : $product", fontSize = 16.sp)
            Spacer(Modifier.weight(1f))
            Text("$points points", fontSize = 16.sp)
        }
        Spacer(Modifier.height(16.dp))
        BarGraph(
            style = BarGraphStyle(
                colors = BarGraphColors(
                    clickHighlightColor = highlightColor,
                    fillType = BarGraphFillType.Gradient(
                        Brush.verticalGradient(
                            listOf(
                                Color.DarkGray,
                                Color.Transparent
                            )
                        )
                    )
                ),
                visibility = BarGraphVisibility(
                isYAxisLabelVisible = true,
            )),
            data = productsPoints,
            onBarClick = { value: BarData ->
                product = value.x
                points = value.y
            }
        )
        Spacer(Modifier.height(12.dp))
    }
}