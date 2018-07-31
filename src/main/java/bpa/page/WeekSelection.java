package bpa.page;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeekSelection 
{

	private WebDriver driver;
	
	// Select Next
	@FindBy(css = "#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-9 > div:nth-child(1) > div > div:nth-child(2) > a:nth-child(2)")
	private WebElement nextweek;
	@FindBy(css = "#form1 > div.page-container > div > div > div.row > div > div:nth-child(4) > div.form-group > div > a:nth-child(2)")
	private WebElement downnextweek;

	public WeekSelection(WebDriver driver)
	{
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public void selectWeek(Logger logr,Timestamp timestamp,String weekone, String weektwo, String weekthree, String weekfour, String weekfive)
			throws IOException, InterruptedException 
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectWeek");
		
		logr.info("weekone: "+weekone);
		logr.info("weektwo: "+weektwo);
		logr.info("weekthree: "+weekthree);
		logr.info("weekfour: "+weekfour);
		logr.info("weekfive: "+weekfive);

		try {
			logr.info("Trying to select Weeks");
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;;
			driver.findElement(By.cssSelector(
					"#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-3 > div.portlet.light.bordered.divWeekNo > div.portlet-body > button:nth-child("
							+ weekone.replace(".0", "") + ")"))
					.click();
			driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;;
			driver.findElement(By.cssSelector(
					"#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-3 > div.portlet.light.bordered.divWeekNo > div.portlet-body > button:nth-child("
							+ weektwo.replace(".0", "") + ")"))
					.click();
			driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;;
			driver.findElement(By.cssSelector(
					"#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-3 > div.portlet.light.bordered.divWeekNo > div.portlet-body > button:nth-child("
							+ weekthree.replace(".0", "") + ")"))
					.click();
			driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;;
			driver.findElement(By.cssSelector(
					"#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-3 > div.portlet.light.bordered.divWeekNo > div.portlet-body > button:nth-child("
							+ weekfour.replace(".0", "") + ")"))
					.click();
			driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;;
			driver.findElement(By.cssSelector(
					"#form1 > div.page-container > div > div > div.row > div > div:nth-child(3) > div.col-md-3 > div.portlet.light.bordered.divWeekNo > div.portlet-body > button:nth-child("
							+ weekfive.replace(".0", "") + ")"))
					.click();
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;;

		} catch (Exception e) 
		{

		}
		
		nextButtonWeek(logr,  timestamp);
		logr.info("------------------------------------------------------------------------------------");
		
	}
	
	public void nextButtonWeek(Logger logr, Timestamp timestamp) throws InterruptedException
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: nextButtonWeek");	
		
		try {
			logr.info("Trying to click Next button in Weeks Page");
			 ((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");			
			if(downnextweek.isDisplayed())
			{
				System.out.println("downNext is displayed");
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;;
			downnextweek.click();			
			} else
			{
			   JavascriptExecutor jse = (JavascriptExecutor) driver;
			    jse.executeScript("window.scrollBy(0,250)", "");				
				if (nextweek.isDisplayed())
				{
					System.out.println("upperNext is displayed");
					driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;;
					nextweek.click();
			}
			}
	} catch (Exception e) 
		{

		}
		logr.info("------------------------------------------------------------------------------------");
	}
	
}
