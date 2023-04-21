package org.example.steps.ya

import io.cucumber.java.ru.И
import io.cucumber.java.ru.Тогда
import org.example.pages.ya.MailBoxPage
import org.example.steps.BasicSteps

class MailBoxPageSteps {
    private val mailBoxPage = MailBoxPage(BasicSteps.driver)
    @И("выйти из почты Яндекс$")
    fun logOut() {
        mailBoxPage.logOut()
    }

    @Тогда("открыта почта Яндекс аккаунта (.*)$")
    fun checkMailBoxUserName(userName: String) {
        mailBoxPage.checkMailBoxUserName(userName)
    }
}