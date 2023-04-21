package org.example.steps.habr

import io.cucumber.java.ru.И
import io.cucumber.java.ru.Когда
import org.example.pages.habr.HabrMainPage
import org.example.steps.BasicSteps


class HabrMainPageSteps {

    private val habrMain = HabrMainPage(BasicSteps.driver)

    @И("пользователь открывает главную страницу Хабрахабра")
    fun goToMainPage() {
        habrMain.goToMainPage()
    }

    @И("скроллит страницу вниз до конца")
    fun scrollDown(){
        habrMain.scrollDown()
    }

    @И("скроллит страницу вверх до конца")
    fun scrollUp(){
        habrMain.scrollUp()
    }

    @И("отображается футер Хабрахабра")
    fun checkFooter(){
        habrMain.checkFooter()
    }

    @Когда("переходит на {int}-й пост на странице")
    fun selectPost(number: Int) {
        habrMain.selectPost(number)
    }
}