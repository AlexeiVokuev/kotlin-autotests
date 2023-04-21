package org.example.pages.ya

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class PassportPage(driver: WebDriver?) {
    private val driver: WebDriver?

    @FindBy(name = "login")
    private val loginField: WebElement? = null

    @FindBy(css = "#passp-field-passwd")
    private val passField: WebElement? = null

    @FindBy(css = "#passp\\:sign-in")
    private val loginButton: WebElement? = null

    private val passportUrl = "https://passport.yandex.ru/auth"

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun checkPassportPage() {
        val factURL: String? = driver?.currentUrl
        println("пытаемся сравнить $passportUrl с $factURL")
        Assert.assertTrue(factURL!!.contains(passportUrl))
    }

    fun confirmLogin(password: String){
        passField?.sendKeys(password)
        println("выполняется клик на " + loginButton?.text)
        loginButton?.click()
    }

    fun logIn(login: String) {
        println("выполняется ввод логина: $login")
        loginField?.sendKeys(login)
        loginButton?.click()
    }

}