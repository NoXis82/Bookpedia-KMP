package org.noxis.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform