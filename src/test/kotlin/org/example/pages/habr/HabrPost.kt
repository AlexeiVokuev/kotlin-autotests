package org.example.pages.habr

import org.junit.Assert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class HabrPost(driver: WebDriver?) {
    private val driver: WebDriver?

    @FindBy(css = "#app > div.tm-layout__wrapper > div.tm-layout > main > div > div > div.tm-page__wrapper > " +
            "div.tm-page__main.tm-page__main_has-sidebar > div > div.tm-article-presenter > " +
            "div.tm-article-presenter__body > div.tm-misprint-area > div > article > div.tm-article-presenter__header " +
            "> div > div.tm-article-snippet__meta-container > div > span > span > a")
    private val author: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun checkPostPage(){
        val factURL: String? = driver?.getCurrentUrl()
        Assert.assertTrue(factURL!!.contains("articles"))
    }

    fun checkAutorExist() {
        Assert.assertTrue(author!!.isDisplayed)
    }

    /*
    public void locateElement(By elementLocator) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initial element count
		int elementCount = driver.findElements(elementLocator).size();

		while (true) {
			// javascriptexecutor to scroll the page
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			wait.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));

			// Wait to load the new elements
			Thread.sleep(2000);

			// Check if the last fetch element count is same as new count,
			// If it's same then we already have fetch all the elements on the page.
			if (driver.findElements(elementLocator).size() == elementCount)
				break;

			// fetch the latest elements count
			elementCount = driver.findElements(elementLocator).size();
		}
	}












	try:
    element_present = EC.presence_of_element_located((By.LINK_TEXT, 'Sitemap'))
    WebDriverWait(driver, timeout).until(element_present)
except TimeoutException:
    print("Timed out while waiting for page to load")
     */

}