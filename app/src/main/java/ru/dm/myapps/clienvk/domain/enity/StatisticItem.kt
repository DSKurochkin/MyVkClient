package ru.dm.myapps.clienvk.domain.enity

data class StatisticItem(
    val statisticType: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}
