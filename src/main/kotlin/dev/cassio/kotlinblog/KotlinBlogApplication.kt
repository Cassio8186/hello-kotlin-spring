package dev.cassio.kotlinblog

import dev.cassio.kotlinblog.config.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class KotlinBlogApplication

fun main(args: Array<String>) {
    runApplication<KotlinBlogApplication>(*args)
}
