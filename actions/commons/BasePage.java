package commons;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class BasePage {

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

}
