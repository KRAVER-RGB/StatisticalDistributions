package org.luffer.statisticaldistributions

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform