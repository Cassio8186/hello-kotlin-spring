package dev.cassio.kotlinblog.domain

import dev.cassio.kotlinblog.toSlug
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "article")
class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne
    var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue
    var id: Int? = null
)

@Entity
@Table(name = "user")
class User(
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String? = null,
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Int? = null
)
