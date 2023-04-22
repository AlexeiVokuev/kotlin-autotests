package org.example.steps.ya

import io.cucumber.java.ru.И
import io.cucumber.java.ru.Тогда
import org.example.SeleniumEnvironment.driver
import org.example.pages.ya.MailBoxPage

class MailBoxPageSteps {
    private val mailBoxPage = MailBoxPage(driver)
    @И("выйти из почты Яндекс$")
    fun logOut() {
        mailBoxPage.logOut()
    }

    @Тогда("открыта почта Яндекс аккаунта (.*)$")
    fun checkMailBoxUserName(userName: String) {
        mailBoxPage.checkMailBoxUserName(userName)
    }
}