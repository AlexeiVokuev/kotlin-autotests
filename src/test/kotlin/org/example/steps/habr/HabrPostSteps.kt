package org.example.steps.habr

import io.cucumber.java.ru.И
import org.example.SeleniumEnvironment.driver
import org.example.pages.habr.HabrPost

class HabrPostSteps {

    private val habrPost = HabrPost(driver)

    @И("на странице поста отображается автор поста")
    fun checkAutorExist() {
        habrPost.checkAutorExist()
    }

    @И("октрыт пост")
    fun checkPostPage(){
        habrPost.checkPostPage()
    }
}