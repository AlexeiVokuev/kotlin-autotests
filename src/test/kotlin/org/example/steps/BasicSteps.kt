package org.example.steps

import io.cucumber.java.After
import io.cucumber.java.AfterAll
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.qameta.allure.Allure
import io.qameta.allure.Attachment
import io.qameta.allure.Step
import org.example.SeleniumEnvironment.destroy
import org.example.SeleniumEnvironment.driver
import org.example.SeleniumEnvironment.init
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot


object BasicSteps {

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
            makeScreenshotOnFailure().inputStream().use {
                    `is` -> Allure.addAttachment("screen_${sc.id}","image/png", `is`, "png")
            }

            //Allure.addAttachment("screen_${sc.id}","image/png",
            //    makeScreenshotOnFailure().inputStream(), ".png")

            //sc.attach(makeScreenshotOnFailure(), "image/png", "screen_${sc.id}")
    }

    @JvmStatic
    @AfterAll
    fun shutdown(){
        destroy()
    }

}