package org.example.steps.habr

import io.cucumber.java.ru.И
import org.example.pages.habr.HabrPost
import org.example.steps.BasicSteps

class HabrPostSteps {

    private val habrPost = HabrPost(BasicSteps.driver)

    @И("на странице поста отображается автор поста")
    fun checkAutorExist() {
        habrPost.checkAutorExist()
    }

    @И("октрыт пост")
    fun checkPostPage(){
        habrPost.checkPostPage()
    }
}