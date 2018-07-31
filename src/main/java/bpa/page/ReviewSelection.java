package bpa.page;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class ReviewSelection {

	private WebDriver driver;
	static private int usercount = 0;
	@FindBy(css = "#Table8 > tbody > tr > td:nth-child(2) > select")
	private WebElement pickedtypedropdown;
	@FindBy(css = "#asavesubmit1")
	private WebElement savesubmit;
	@FindBy(css = "#divMainSection > div.form-group > div>a[id='asavesubmit']")
	private WebElement savesubmit1;
	@FindBy(css = "#divMainSection > div.col-md-12 > div > div>a[id='aLogout1']")
	private WebElement logout;
	
	Properties props = new Properties();
	FileInputStream fis;

	public ReviewSelection(WebDriver driver) 
	{
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}


	public void saveReview(Logger logr,Timestamp timestamp,String strcustname) throws IOException, InterruptedException, EmailException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: saveReview");
		
		driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;		
		try {
			logr.info("Trying to pick Dropdown");
			if (pickedtypedropdown.isDisplayed()) 
			{
				logr.info("pickedtypedropdown isDisplayed");
				driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
				pickedtypedropdown.sendKeys("By Employee");
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
			}

		} catch (Exception e) 
		{

		}

		try
		{
			logr.info("Trying to save selection");
			if (savesubmit.isDisplayed()) 
			{
				logr.info("savesubmit isDisplayed");
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,-250)", "");
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
				Thread.sleep(4000);
				savesubmit.click();
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
				Thread.sleep(2000);
				captureScreenshotSave(logr, timestamp, strcustname);
				
			} else
			{
				logr.info("savesubmit1 isDisplayed");
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
				Thread.sleep(4000);
				savesubmit1.click();
				Thread.sleep(2000);
				captureScreenshotSave(logr,timestamp,strcustname);
			}
		} catch (Exception e)
		{

		}			
		usercount++;
		
		logr.info("************************************************************************************");
		logr.info("CustomerNumber :"+usercount+" CustomerName :"+strcustname+" Completed");
		logr.info("************************************************************************************");
		
		System.out.println(usercount);
		if(usercount==10 || usercount==20 || usercount==30 || usercount==40 || usercount==50)
		{
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
			sendEmailTenUserLog(usercount);
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;					
		}				
		clickLogout(logr,timestamp);		
		logr.info("------------------------------------------------------------------------------------");
	}

	public void clickLogout(Logger logr,Timestamp timestamp) throws IOException, InterruptedException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: clickLogout");
		
		if (logout.isDisplayed()) 
		{
			logr.info("logOut isDisplayed");
			driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
			logout.click();
			logr.info("logOut is clicked");
			Thread.sleep(4000);
		}
		logr.info("------------------------------------------------------------------------------------");
	}
	
	public void captureScreenshotSave(Logger logr,Timestamp timestamp,String strcustname) throws IOException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: captureScreenshotSave");
	
		 //loading properites from properties file
		fis = new FileInputStream("C:/Selenium Environment/config.properties");
	    props.load(fis);		
		EventFiringWebDriver e = new EventFiringWebDriver(driver);
		File srcfile = e.getScreenshotAs(OutputType.FILE);
		System.out.println(props.getProperty("Screenshot location"));
		File destfile = new File(props.getProperty("Screenshotlocation")+strcustname+".png");	
		System.out.println("DestinationFileLocation"+destfile);
		logr.info("UsersJobSelection captureScreenshot Saved");
		FileUtils.copyFile(srcfile, destfile);
		
		logr.info("------------------------------------------------------------------------------------");

	}
	

	public  void sendEmailTenUserLog(int usercount) throws EmailException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("QATeamSynaptris@gmail.com", "Synaptris123"));
		email.setSSLOnConnect(true);
		email.setFrom("QATeamSynaptris@gmail.com");
		email.setSubject("Mail Notification");
		email.setMsg("Please check the attachment for log file. usercount: " + usercount +"\n"+"\n"+"***This is an automatically generated email, please do not reply***");
		email.addTo("john.sundar@synaptris.com");
		email.addTo("rajasekaran.m@synaptris.com");
		email.addTo("vishnupriya.v@synaptris.com");
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C://Selenium Environment/Log/StationPickPOC2.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Log File.html");
		attachment.setName("Log File");
		// add the attachment
		email.attach(attachment);
		driver.manage().timeouts().implicitlyWait(250,TimeUnit.SECONDS) ;
		email.send();
		driver.manage().timeouts().implicitlyWait(600,TimeUnit.SECONDS) ;
		/*Thread.sleep(5000);*/
	}



}