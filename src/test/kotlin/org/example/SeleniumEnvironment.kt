package org.example

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverInfo
import java.io.FileWriter
import java.io.IOException
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

        //todo test for env in allure report
        try {
            FileWriter("allure-results/environment.properties", true).use { writer ->
                writer.write("Browser=Chrome\n\rVersion=${(driver as ChromeDriverInfo).displayName}")
                writer.flush()
            }
        } catch (ex: IOException) {
            println(ex.message)
        }

    }

    @Step("Завершение работы драйвера")
    fun destroy(){
        println("завершение работы драйвера...")
        driver?.quit()
        println("завершение работы выполнено")
    }

}