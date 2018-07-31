package bpa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Dashboard 
{
	private WebDriver driver;

	@FindBy(xpath = "//div[@class='cbp-caption']/div/a/img[@src='../Resource/Portfolio/Apps/3/Job Pick.jpg']")
	private WebElement applink;

	public Dashboard(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectApp(Logger logr,Timestamp timestamp) throws InterruptedException 
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
	 	logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectApp");	 
		
		driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
		String currenturlapplink = driver.getCurrentUrl();
		if (currenturlapplink.contains("DashboardListNewM.aspx"))
		{
			logr.info("currenturlapplink contains DashboardListNewM.aspx");	 
			if (applink.isDisplayed()) 
			{		
				logr.info("appLink isDisplayed");	
				applink.click();	
				driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
			}
		}
		logr.info("------------------------------------------------------------------------------------");
	}
}
