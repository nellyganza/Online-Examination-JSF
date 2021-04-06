/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.exams.Registration.Automated;

import utils.HibernateUtil;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.exams.DbSetup.AbtractTest;

/**
 *
 * @author Nishimwe Elysee
 */
public class TestStudentRegistrationWeb extends AbtractTest{
    
    WebDriver driver;
    @BeforeMethod
    public void invokeBrower(){
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andela\\Documents\\selenium\\chromedriver_win32_2\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            driver.get("http://localhost:8080/Students/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void endDriver(){
        driver.close();
    }
    
    @BeforeClass
    public void initializeDB(){
        HibernateUtil.getSessionFactory();
    }
    
    @Test
    public void submitStudent(){
        try {
            driver.findElement(By.name("studentId")).sendKeys("21823");
            driver.findElement(By.cssSelector("button[type='button']")).click();
            String name = driver.findElement(By.name("names")).getAttribute("value");
            if(name.isEmpty()){
                driver.findElement(By.name("names")).sendKeys("Nishimwe Elysee");
                driver.findElement(By.name("gender")).sendKeys("MALE");
                driver.findElement(By.name("dob")).sendKeys("02/12/1998");
            }
            driver.findElement(By.tagName("form")).submit();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("#CourseC08>td>a")).click();
            driver.findElement(By.cssSelector("#CourseC01>td>a")).click();
            driver.findElement(By.cssSelector("#CourseC02>td>a")).click();
            driver.findElement(By.cssSelector("#CourseC06>td>a")).click();
            String numberofcourse = driver.findElement(By.className("numberofcourse")).getText();
            String numberofcredit = driver.findElement(By.className("numberofcredits")).getText();
            String totalpayment = driver.findElement(By.className("totalpayment")).getText();
            
            Assert.assertEquals(numberofcourse, "4");
            Assert.assertEquals(numberofcredit, "14");
            Assert.assertEquals(totalpayment, "210,000Frw");
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("#CourseC06>td>a")).click();
            driver.findElement(By.linkText("View Details")).click();
            Thread.sleep(3000);
            driver.findElement(By.linkText("Go Back")).click();
            driver.findElement(By.cssSelector("#CourseC07>td>a")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Complete Registration")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText("Register Now")).click();
            String feedbackTitle = driver.findElement(By.cssSelector("div>h3")).getText();
            Assert.assertEquals(feedbackTitle, "Registration Feedback");
           
        } catch (InterruptedException ex) {
            Logger.getLogger(TestStudentRegistrationWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void studentNullInformation(){
        try {
            driver.findElement(By.name("studentId")).sendKeys("21823");
            driver.findElement(By.name("names")).sendKeys("Nishimwe Elysee");
            driver.findElement(By.tagName("form")).submit();
            String error = driver.findElement(By.className("error")).getText();
            Thread.sleep(2000);
            Assert.assertNotNull(error);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestStudentRegistrationWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
