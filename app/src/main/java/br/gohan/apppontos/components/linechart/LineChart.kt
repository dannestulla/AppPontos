package br.gohan.apppontos.components.linechart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import br.gohan.apppontos.components.DropdownMenu
import br.gohan.apppontos.ui.theme.highlightColor
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility

@Composable
fun LineChart(modifier: Modifier = Modifier) {
    var userSelected by remember { mutableStateOf(users[0]) }

    var userPoints by remember { mutableStateOf(userSelected.lineData.last().y) }
    var monthSelected by remember { mutableStateOf(userSelected.lineData.last().x) }

    Column(
        Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Points per user",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        val usersNames = users.map { it.name }
        Spacer(Modifier.height(12.dp))
        DropdownMenu(usersNames, onItemSelect = { user ->
            userSelected = users.first { it.name == user }
            userPoints = userSelected.lineData.last().y
        })
        Spacer(Modifier.height(12.dp))
        Row {
            Text("Month selected: $monthSelected", fontSize = 16.sp)
            Spacer(Modifier.weight(1f))
            Text("$userPoints points", fontSize = 16.sp)
        }
        Spacer(Modifier.height(16.dp))
        LineGraph(
            style = LineGraphStyle(
                colors = LineGraphColors(
                    lineColor = Color.DarkGray,
                    pointColor = Color.DarkGray,
                    clickHighlightColor = highlightColor,
                    fillType = LineGraphFillType.Gradient(
                        Brush.verticalGradient(
                            listOf(
                                Color.Gray,
                                Color.Transparent
                            )
                        )
                    )

                ),
                visibility = LineGraphVisibility(
                    isYAxisLabelVisible = true,
                    isCrossHairVisible = true
                )
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            data = userSelected.lineData,
            onPointClick = { value: LineData ->
                userPoints = value.y
                monthSelected = value.x
            },
        )
    }
}

data class UserLineChart(
    val name: String,
    val lineData: List<LineData>,
)
