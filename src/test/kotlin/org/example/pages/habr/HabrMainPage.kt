package org.example.pages.habr

import org.junit.Assert
import org.openqa.selenium.By.ByClassName
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class HabrMainPage(driver: WebDriver?) {
    private val driver: WebDriver?

    @FindBy(css = "#app > div.tm-layout__wrapper > div.tm-footer-menu > div > div")
    private val footer: WebElement? = null

    @FindBy(css = "#app > div.tm-layout__wrapper > header")
    private val header: WebElement? = null

    private var mainPageURL = "https://habr.com/ru/all/"

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun selectPost(number: Int){
        driver?.findElements(ByClassName("tm-title__link"))!![number].click()
    }

    fun goToMainPage() {
        println("пытаемся перейти к $mainPageURL")
        driver?.get(mainPageURL)
        println("переход к $mainPageURL осуществлен")
    }

    fun scrollDown(){
        val js = (driver as JavascriptExecutor)
        js.executeScript("arguments[0].scrollIntoView();", footer)
    }

    fun checkFooter() {
        Assert.assertTrue(footer!!.isDisplayed)
    }

    fun scrollUp(){
        val js = (driver as JavascriptExecutor)
        js.executeScript("arguments[0].scrollIntoView();", header)
    }

}