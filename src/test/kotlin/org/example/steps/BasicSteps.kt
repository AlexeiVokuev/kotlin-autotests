package org.example.steps

import io.cucumber.java.After
import io.cucumber.java.AfterAll
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.qameta.allure.Attachment
import org.example.SeleniumEnvironment.destroy
import org.example.SeleniumEnvironment.driver
import org.example.SeleniumEnvironment.init
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot

object BasicSteps {

    @Attachment("fail_screenshot", type = "image/png")
    fun makeScreenshotOnFailure(): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    @JvmStatic
    @Before
    fun setup(): Unit {
        if(driver == null) init()
    }

    @JvmStatic
    @After
    fun down(sc:Scenario): Unit {
        if(sc.isFailed)
            sc.attach(makeScreenshotOnFailure(), "image/png", "screen_${sc.id}")
    }

    @JvmStatic
    @AfterAll
    fun shutdown(){
        destroy()
    }

}