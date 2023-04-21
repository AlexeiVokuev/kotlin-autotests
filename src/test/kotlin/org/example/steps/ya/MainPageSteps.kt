package org.example.steps.ya

import io.cucumber.java.ru.И
import io.cucumber.java.ru.Когда
import io.cucumber.java.ru.То
import io.cucumber.java.ru.Тогда
import org.example.pages.ya.MainPage
import org.example.steps.BasicSteps

class MainPageSteps {
    private val mainPage = MainPage(BasicSteps.driver)
    @Когда("пользователь открывает главную страницу Яндекс$")
    fun goToMainPage() {
        mainPage.goToMainPage()
    }

    @И("нажимает на кнопку \"Войти\"$")
    fun loginButtonClick(){
        mainPage.loginButtonClick()
    }

    @То("кнопка \"Войти с помощью Яндекс ID\" отображается")
    fun checkLoginWithId(){
        mainPage.checkLoginWithIdButton()
    }

    @Когда("нажимает на кнопку \"Войти с помощью Яндекс ID\"")
    fun loginWithIdButtonClick(){
        mainPage.loginWithIdButtonClick()
    }

    @Тогда("открыта главная Яндекс$")
    fun checkMainPage() {
        mainPage.checkMainPage()
    }
}