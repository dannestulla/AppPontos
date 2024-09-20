package br.gohan.apppontos.components.line_chart

import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import kotlin.random.Random

fun randomNumber() = Random.nextInt(20, 1500)

val users = listOf(
    UserLineChart(
        name = "Daniel",
        listOf(
            LineData(x = "Jan", y = randomNumber()),
            LineData(x = "Feb", y = randomNumber()),
            LineData(x = "Mar", y = randomNumber()),
            LineData(x = "Apr", y = randomNumber()),
            LineData(x = "May", y = randomNumber()),
            LineData(x = "Jun", y = randomNumber()),
            LineData(x = "Jul", y = randomNumber()),
        )
    ),
    UserLineChart(
        name = "Maria",
        listOf(
            LineData(x = "Jan", y = randomNumber()),
            LineData(x = "Feb", y = randomNumber()),
            LineData(x = "Mar", y = randomNumber()),
            LineData(x = "Apr", y = randomNumber()),
            LineData(x = "May", y = randomNumber()),
            LineData(x = "Jun", y = randomNumber()),
            LineData(x = "Jul", y = randomNumber()),
        )
    ),
    UserLineChart(
        name = "Carlos",
        listOf(
            LineData(x = "Jan", y = randomNumber()),
            LineData(x = "Feb", y = randomNumber()),
            LineData(x = "Mar", y = randomNumber()),
            LineData(x = "Apr", y = randomNumber()),
            LineData(x = "May", y = randomNumber()),
            LineData(x = "Jun", y = randomNumber()),
            LineData(x = "Jul", y = randomNumber()),
        )
    ),
    UserLineChart(
        name = "Ana",
        listOf(
            LineData(x = "Jan", y = randomNumber()),
            LineData(x = "Feb", y = randomNumber()),
            LineData(x = "Mar", y = randomNumber()),
            LineData(x = "Apr", y = randomNumber()),
            LineData(x = "May", y = randomNumber()),
            LineData(x = "Jun", y = randomNumber()),
            LineData(x = "Jul", y = randomNumber()),
        )
    ),
    UserLineChart(
        name = "Total",
        listOf(
            LineData(x = "Jan", y = randomNumber()),
            LineData(x = "Feb", y = randomNumber()),
            LineData(x = "Mar", y = randomNumber()),
            LineData(x = "Apr", y = randomNumber()),
            LineData(x = "May", y = randomNumber()),
            LineData(x = "Jun", y = randomNumber()),
            LineData(x = "Jul", y = randomNumber()),
        )
    )
)
