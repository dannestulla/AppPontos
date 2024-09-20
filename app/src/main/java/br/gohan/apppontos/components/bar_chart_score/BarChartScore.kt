package br.gohan.apppontos.components.bar_chart_score

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
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType

@Composable
fun BarChartUsersScore(modifier: Modifier = Modifier) {
    var selected by remember { mutableStateOf(usersScore.first()) }

    var group by remember { mutableStateOf(selected.x) }
    var points by remember { mutableStateOf(selected.y) }

    Column(
        Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Users points by group",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        /* DropdownMenu(usersNames) { user ->
             userSelected = usersBarData.first { it.name == user }
         }*/
        Spacer(Modifier.height(12.dp))
        Row {
            Text("Group : $group points", fontSize = 16.sp)
            Spacer(Modifier.weight(1f))
            Text("$points users", fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
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
                    isGridVisible = true
                )
            ),
            data = usersScore,
            onBarClick = { value ->
                group = value.x
                points = value.y
            }
        )
        Spacer(Modifier.height(12.dp))
    }
}
