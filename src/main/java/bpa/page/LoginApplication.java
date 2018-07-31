package bpa.page;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.UtilFunctionality;

public class LoginApplication 
{
	private WebDriver driver;
	UtilFunctionality utilfunction;

	@FindBy(css = "input[name='txtUserName']")
	private WebElement username;
	@FindBy(css = "input[name='txtPassword']")
	private WebElement password;
	@FindBy(css = "button[id='AhLogin']")
	private WebElement loginbutton;
	@FindBy(css = "div.modal-body>p")
	private WebElement activeuser;
	@FindBy(css = "button[class='btn green']")
	private WebElement okactiveuser;
	@FindBy(css = "#txtPassword-error")
	private WebElement passwordrequired;

	public LoginApplication(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(Logger logr, Timestamp timestamp, String strusername, String strcustname) 
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: setUserName");

		try {
			
			logr.info("Trying to enter UserName in Texboxt");
			String loginurl = driver.getCurrentUrl();
			if (loginurl.contains("default.aspx"))
			{
				logr.info("loginURL contains default.aspx");	
				if (username.isDisplayed())
				{
					logr.info("userName isDisplayed ");
					username.clear();
					// Encrypting
					String encptusername = new String(Base64.getEncoder().encode(strusername.getBytes()));				
					// Decrypting
					String decryptuserName = new String(Base64.getDecoder().decode(encptusername.getBytes()));
					System.out.println(decryptuserName);
					username.clear();
					username.sendKeys(decryptuserName);
					//username.sendKeys("Vishnupriya");
				}
			}
		} catch (Exception e) 
		{
			driver.navigate().to("about:blank");
			driver.manage().timeouts().pageLoadTimeout(55000, TimeUnit.SECONDS);
		}
		logr.info("------------------------------------------------------------------------------------");
	}

	public void setPassword(Logger logr, Timestamp timestamp, String strpassword)
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: setPassword");		

		try {
			logr.info("Trying to enter Password in Textbox");
			if (password.isDisplayed())
			{
				logr.info("Password isDisplayed");
				password.clear();
				String encstrpassword = new String(Base64.getEncoder().encode(strpassword.getBytes()));
				System.out.println(encstrpassword);
				// Decrypting password
				String decryptpassword = new String(Base64.getDecoder().decode(encstrpassword.getBytes()));
				System.out.println(decryptpassword);
				password.clear();
				password.sendKeys(decryptpassword);
				//password.sendKeys("TestPassword");
			}
		} catch (Exception e) 
		{
			driver.navigate().to("about:blank");
			driver.manage().timeouts().pageLoadTimeout(55000, TimeUnit.SECONDS);
		}
		logr.info("------------------------------------------------------------------------------------");
	}

	public void clickLogin(Logger logr, Timestamp timestamp, String strcustname) throws InterruptedException 
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: clickLogin");
		logr.info("strcustname: " + strcustname);
		try {
			logr.info("Trying to click login button");
			if (loginbutton.isDisplayed())
			{
				logr.info("loginbutton isDisplayed");
				loginbutton.click();
				driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
				String currenturl = driver.getCurrentUrl();
				logr.info("currenturl :" + currenturl);
				if (currenturl.contains("DashboardListNewM.aspx")) 
				{
					logr.info("currenturl contains DashboardListNewM.aspx");
					//response.setHeader("Refresh", "0; URL=http://10.60.11.98:8080/Kezava/Stations/default.aspx");
					
				} else if (currenturl.contains("default.aspx")) 
				{
					logr.info("currenturl contains default.aspx");
					Thread.sleep(2000);
					try {
						logr.info("Trying to handle active user session");
						if (activeuser.isDisplayed()) {
							logr.info("activeuser isDisplayed");
							if (okactiveuser.isDisplayed()) {
								logr.info("okactiveuser isDisplayed");
								okactiveuser.click();
								Thread.sleep(2000);
								String currenturlactive = driver.getCurrentUrl();
								logr.info("currenturlactive :" + currenturlactive);
								if (currenturlactive.contains("DashboardListNewM.aspx"))
								{
									logr.info("currenturl contains DashboardListNewM.aspx");
								}
							} else
							{
										driver.navigate().refresh();					
							}
						}
					} catch (Exception e) 
					{
						try {
							logr.info("Trying to check Credentials is failed");
							Thread.sleep(2000);
							if (passwordrequired.isDisplayed())
							{								
								logr.info("passwordRequired isDisplayed");
								driver.navigate().refresh();
								enterUserName(logr, timestamp, currenturl, currenturl);
								setPassword(logr, timestamp, currenturl);
								clickLogin(logr, timestamp, currenturl);
								driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
								utilfunction.captureScreenshotError(strcustname);
								logr.info("captureScreenshotError");
								Thread.sleep(2000);
								utilfunction.sendEmailError(strcustname);
								logr.info("sendEmailError");
								driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
								driver.navigate().to("file:///C:/Users/QA/Desktop/image.html");
								logr.info("Navigating to Blank Page");
								driver.manage().timeouts().pageLoadTimeout(55000, TimeUnit.SECONDS);
							} else {
							}
						} catch (Exception ee) 
						{
							driver.navigate().to("file:///C:/Users/QA/Desktop/image.html");
							driver.manage().timeouts().pageLoadTimeout(55000, TimeUnit.SECONDS);
						}
					}
				}
			}
		} catch (Exception e) 
		{
			driver.navigate().to("about:blank");
			driver.manage().timeouts().pageLoadTimeout(55000, TimeUnit.SECONDS);
		}
		logr.info("------------------------------------------------------------------------------------");
	}
}
