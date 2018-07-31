package util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class UtilFunctionality {

	WebDriver driver;

	public UtilFunctionality(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void encryptUserName(String strUserName ,String decryptUserName)
	{
		//UserName 
		//Encrypting
		strUserName=new String(Base64.getEncoder().encode(strUserName.getBytes()));
		System.out.println(strUserName);     
		
		//Decrypting             

				decryptUserName= new String(Base64.getDecoder().decode(strUserName.getBytes()));
				System.out.println(decryptUserName);	
	}
	public static void encryptPassword(String strPassword ,String decryptPassword)
		
	{
		//Password 
		//Encrypting password
		strPassword = new String(Base64.getEncoder().encode(strPassword.getBytes()));
		System.out.println(strPassword);
		
		//Decrypting password
	    decryptPassword = new String(Base64.getDecoder().decode(strPassword.getBytes()));
		System.out.println(decryptPassword);


	}
	public void captureScreenshotSave(Logger logr,Timestamp timestamp,String strCustName) throws IOException {
		
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: captureScreenshotSave");
		
		EventFiringWebDriver e = new EventFiringWebDriver(driver);
		File srcfile = e.getScreenshotAs(OutputType.FILE);
		File destfile = new File("C://Selenium Environment/Screenshot/UsersJobSelection/"+strCustName+".png");
		logr.info("UsersJobSelection captureScreenshot Saved");
		FileUtils.copyFile(srcfile, destfile);
		
		logr.info("------------------------------------------------------------------------------------");

	}

	public void captureScreenshotError(String strCustName) throws IOException {
		
		
		EventFiringWebDriver e = new EventFiringWebDriver(driver);
		File srcfile = e.getScreenshotAs(OutputType.FILE);
		File destfile = new File("C://Selenium Environment/Screenshot/Error/"+strCustName+".png");
		FileUtils.copyFile(srcfile, destfile);

	}

	public void sendEmailError(String strCustName) throws EmailException, InterruptedException {
		Thread.sleep(3000);
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("QATeamSynaptris@gmail.com", "Synaptris123"));
		email.setSSLOnConnect(true);
		email.setFrom("QATeamSynaptris@gmail.com");
		email.setSubject("Mail Notification");
		email.setMsg("Please check the attachment for error picture");
		email.addTo("rajasekaran.m@synaptris.com");
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C://Selenium Environment/Screenshot/Error/" + strCustName + ".png");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Error Pic");
		attachment.setName(strCustName);
		// add the attachment

		email.attach(attachment);
		Thread.sleep(3000);

		email.send();
		Thread.sleep(3000);
	}

	public  void sendEmailTenUserLog(int userCount) throws EmailException, InterruptedException {
		Thread.sleep(3000);
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("QATeamSynaptris@gmail.com", "Synaptris123"));
		email.setSSLOnConnect(true);
		email.setFrom("QATeamSynaptris@gmail.com");
		email.setSubject("Mail Notification");
		email.setMsg("Please check the attachment for log: " + userCount);
		email.addTo("rajasekaran.m@synaptris.com");
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("C://Selenium Environment/Log/StationPickPOC2.html");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Log File.html");
		attachment.setName("Log File");
		// add the attachment

		email.attach(attachment);
		Thread.sleep(3000);

		email.send();
		Thread.sleep(5000);
	}

}
