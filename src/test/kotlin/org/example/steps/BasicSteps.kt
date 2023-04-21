package org.example.steps

import io.cucumber.java.After
import io.cucumber.java.AfterAll
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.qameta.allure.Attachment
import io.qameta.allure.Step
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

object BasicSteps {
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

    @Step("добавление скриншота")
    @Attachment("fail_screenshot", type = "image/png")
    fun makeScreenshotOnFailure(): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    @JvmStatic
    @Before
    fun setup(): Unit {
        if(driver == null) init()
    }

    @Step("Завершение теста")
    @JvmStatic
    @After
    fun down(sc:Scenario): Unit {
        if(sc.isFailed)
            sc.attach(makeScreenshotOnFailure(), "image/png", "screen_${sc.id}")

    }

    @Step("Завершение работы драйвера")
    @JvmStatic
    @AfterAll
    fun destroy(){
        println("завершение работы драйвера...")
        driver?.quit()
        println("завершение работы выполнено")
    }

}