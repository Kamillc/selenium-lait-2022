import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestFive {

    public ChromeDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void sumIntegers() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        driver.findElement(By.id("sum1")).sendKeys("2");
        driver.findElement(By.id("sum2")).sendKeys("5");
        driver.findElement(By.xpath("//*[@id='gettotal']/button")).click();


//        boolean result = driver.findElement(By.xpath("//*[@id=\'addmessage\']")).isDisplayed();
        String result = driver.findElement(By.xpath("//*[@id=\'addmessage\']")).getText();
        System.out.println(result);
        //Assert.assertEquals("9", result);
    }

    @Test
    public void selectCheckBox() {
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();


        //Uruchom strone
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        //Przeslij wartosci i kliknij przycisk
        driver.findElement(By.id("isAgeSelected")).click();

        //Zweryfikuj rezultat
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='txtAge']"))));
        Boolean message_is_displayed = driver.findElement(By.xpath("//div[@id='txtAge']")).isDisplayed();
        Assert.assertTrue(message_is_displayed);
    }

    @Test
    public void dwachckboxy() {
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/label[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/label[1]/input")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/p[2]/span"))));
        String text = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/p[2]/span")).getText();
        Assert.assertEquals(0 - 5, text);
    }


}
