package dev.cassio.kotlinblog.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("blog")
@ConstructorBinding()
class BlogProperties (var title: String, val banner: Banner){
data class Banner(val title: String? = null, val content: String)

}
