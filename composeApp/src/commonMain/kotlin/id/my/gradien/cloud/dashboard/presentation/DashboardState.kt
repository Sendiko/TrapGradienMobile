package id.my.gradien.cloud.dashboard.presentation

data class DashboardState(
    val userName: String = "Sendiko",
    val location: String = "Cikoneng, Bojongsoang, Bandung",
    val version: String = "0.0.1",
    val alatList: List<String> = listOf("Alat Lorem #1", "Alat Lorem #2", "Alat Lorem #2", "Alat Lorem #2"),
    val isLocationPickerOpen: Boolean = false,
    val availableLocations: List<String> = listOf(
        "Cikoneng, Bojongsoang, Bandung",
        "Dago, Coblong, Bandung",
        "Lembang, Bandung Barat",
        "Ciputat, Tangerang Selatan"
    )
)
