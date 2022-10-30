package dev.cassio.kotlinblog.controller

import dev.cassio.kotlinblog.domain.Article
import dev.cassio.kotlinblog.domain.ArticleRepository
import dev.cassio.kotlinblog.domain.User
import dev.cassio.kotlinblog.format
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController @Autowired constructor(val articleRepository: ArticleRepository) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = this.articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog";
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable("slug") slug: String, model: Model): String {
        val article = articleRepository.findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exists")

        model["title"]= article.title
        model["article"]= article
        return "article"
    }

    fun Article.render()= RenderedArticle(slug,title,headline,content,author,addedAt.format())

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    ) {

    }

}
