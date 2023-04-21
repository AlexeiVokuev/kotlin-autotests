package org.example.pages.ya

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class MailBoxPage(driver: WebDriver?) {
    private val driver: WebDriver?

    @FindBy(id = "recipient-1")
    private val userMenu: WebElement? = null

    @FindBy(xpath = "//*[@id=\"nb-4\"]/div/div/div[8]/a")
    private val menuExit: WebElement? = null

    @FindBy(className = "mail-User-Name")
    var userNameLabel: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun logOut() {
        println("выполняется клик на " + userMenu!!.text)
        userMenu.click()
        println("клик на " + userMenu.text + " выполнен")
        println("выполняется клик на " + menuExit!!.text)
        menuExit.click()
        println("клик на " + menuExit.text + " выполнен")
    }

    fun checkMailBoxUserName(userName: String) {
        val factUserName = userNameLabel!!.text
        println("пытаемся сравнить $userName с $factUserName")
        Assert.assertEquals(userName, factUserName)
    }
}