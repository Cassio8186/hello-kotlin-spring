package dev.cassio.kotlinblog

import dev.cassio.kotlinblog.domain.Article
import dev.cassio.kotlinblog.domain.ArticleRepository
import dev.cassio.kotlinblog.domain.User
import dev.cassio.kotlinblog.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val juergen = User("springjuergen", "Jurgen", "Hoeller")
        entityManager.persist(juergen)

        val article = Article("Spring framework is lit", "Dear Spring community", "Lorem Ipsum", juergen)

        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)

        assertThat(found).isEqualTo(article)

    }


    @Test
    fun `When findByLogin then return User`() {
        val juergen = User("springjuergen", "Jurgen", "Hoeller")
        entityManager.persist(juergen)
        val user = this.userRepository.findByLogin(juergen.login)
        assertThat(user).isEqualTo(juergen)
    }
}
