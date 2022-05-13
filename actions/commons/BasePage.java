package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class BasePage {


    /* Web browser */
    public void openPageURL(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public void acceptAlert(WebDriver driver){
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver){
        driver.switchTo().alert().dismiss();
    }

    public void sendKeysToAlert(WebDriver driver, String valueToSendkey){
        driver.switchTo().alert().sendKeys(valueToSendkey);
    }

    public String getAlertText(WebDriver driver){
        return driver.switchTo().alert().getText();
    }

    public void switchToWindowByID(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)){
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows){
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        else return false;
    }

    /* Web element */

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }

    public void checkToCheckBoxOrRadio(WebDriver driver, String locator){
        if (!getWebElement(driver, locator).isSelected()){
            clickToElement(driver, locator);
        }
    }

    public void uncheckCheckbox(WebDriver driver, String locator){
        if (getWebElement(driver, locator).isSelected()){
         getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String valueToInput){
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(valueToInput);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText){
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public String  getFirstSelectedTextItem(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropDownMultiple(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath,String childXpath, String expectedItem ){
        getWebElement(driver,parentXpath).click();
        sleepSecond(2);

        List<WebElement> childItems = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));

        for (WebElement tempElement : childItems){
            if (tempElement.getText().trim().equals(expectedItem)){
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);",tempElement);
                sleepSecond(1);
                tempElement.click();
                sleepSecond(1);
                break;
            }
        }

    }

    public void sleepSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void hoverMouseToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String souceLocator, String targetLocator){
        Actions action = new Actions(driver);
        action.dragAndDrop(getWebElement(driver,souceLocator),getWebElement(driver,targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key){
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver,locator),key).perform();
    }

    private long longTimtOuts = 30;
    private long shorTimeOuts = 3;
}
