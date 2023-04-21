package org.example.pages.ya

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class MainPage(driver: WebDriver?) {
    private val driver: WebDriver?

    @FindBy(css = "body > div.content > div.dzen-desktop__container-29 > header > div > div.dzen-header-desktop__rightItems-3y > div.dzen-header-desktop__profileMenu-3q > div > button")
    private val loginButton: WebElement? = null

    @FindBy(css = "#tooltip-0-1 > div > div.login-content__yaButtonWrapper-15 > a > span.base-login-button__loginButtonText-cT.base-button__childrenContent-DJ")
    private val loginWithIdButton: WebElement? = null

    private var mainPageURL = "https://yandex.ru/"

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun loginButtonClick(){
        println("выполняется клик на " + loginButton?.text)
        loginButton?.click()
    }

    fun goToMainPage() {
        println("пытаемся перейти к $mainPageURL")
        driver?.get(mainPageURL)
        println("переход к $mainPageURL осуществлен")
    }

    fun checkMainPage() {
        val factURL: String? = driver?.getCurrentUrl()
        println("пытаемся сравнить $mainPageURL с $factURL")
        Assert.assertEquals(mainPageURL, factURL)
    }

    fun checkLoginWithIdButton() {
        Assert.assertTrue(loginWithIdButton != null)
        Assert.assertTrue(loginWithIdButton!!.isDisplayed)
    }

    fun loginWithIdButtonClick(){
        println("выполняется клик на " + loginWithIdButton?.text)
        loginWithIdButton?.click()
    }
}