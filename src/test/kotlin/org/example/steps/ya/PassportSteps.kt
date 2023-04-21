package org.example.steps.ya

import io.cucumber.java.ru.И
import io.cucumber.java.ru.Тогда
import org.example.pages.ya.PassportPage
import org.example.steps.BasicSteps

class PassportSteps {
    private val passportPage = PassportPage(BasicSteps.driver)

    @И("пользователь логинится с почтой (.*)$")
    fun logIn(login: String) {
        passportPage.logIn(login)
    }

    @И("подтверждает вход с паролем (.*)$")
    fun confirmLogin(password: String){
        passportPage.confirmLogin(password)
    }

    @Тогда("открыта страница Яндекс Пасспорт$")
    fun checkPassportPage() {
        passportPage.checkPassportPage()
    }
}