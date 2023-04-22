package org.example

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

object SeleniumEnvironment {
    var driver: WebDriver? = null

    @Step("Инициализация драйвера")
    fun init(){
        println("выполняется запуск драйвера...")
        System.setProperty("webdriver.chrome.driver", "H:\\chromedriver.exe")
        driver = ChromeDriver()
        (driver as ChromeDriver).manage().window().maximize()
        (driver as ChromeDriver).manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        println("запуск драйвера выполнен")
    }

    @Step("Завершение работы драйвера")
    fun destroy(){
        println("завершение работы драйвера...")
        driver?.quit()
        println("завершение работы выполнено")
    }

}