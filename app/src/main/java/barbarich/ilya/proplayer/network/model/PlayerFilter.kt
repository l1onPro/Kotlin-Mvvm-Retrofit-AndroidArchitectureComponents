package barbarich.ilya.proplayer.network.model

enum class PlayerFilter(val value: String) {
    SORT_BY_RATING_1_0 (value = "rating_1_0"),
    SORT_BY_RATING(value = "rating"),
    SORT_BY_NAME(value = "nick_name")
}