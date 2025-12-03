package id.my.gradien.cloud

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform