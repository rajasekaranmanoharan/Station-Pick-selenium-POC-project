package bpa.page;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobSelection
{
	private WebDriver driver;
	private String vTriggerId;
	private int max1;

	// Select Next
	@FindBy(css = "#divfiltercount > div > a")
	private WebElement uppernext;
	@FindBy(css = "#form1 > div.page-container > div.page-content-wrapper > div > div.row > form > div > div > a")
	private WebElement downnext;

	// Select Category
	@FindBy(css = "#rptJobType_ctl01_hLinkShop")
	private WebElement jobcelevator;
	@FindBy(css = "#rptJobType_ctl02_hLinkShop")
	private WebElement jobcextra;
	@FindBy(css = "#rptJobType_ctl03_hLinkShop")
	private WebElement jobcrdoreleif;
	@FindBy(css = "#rptJobType_ctl04_hLinkShop")
	private WebElement jobcspecialitycleaning;
	@FindBy(css = "#rptJobType_ctl05_hLinkShop")
	private WebElement jobcstationnorth;
	@FindBy(css = "#rptJobType_ctl06_hLinkShop")
	private WebElement jobcstationsouth;
	@FindBy(css = "#rptJobType_ctl07_hLinkShop")
	private WebElement jobcvacationrelief;

	// Select Tour
	@FindBy(css = "#rptDuration_ctl01_iTextDuration")
	private WebElement jobscnight;
	@FindBy(css = "#rptDuration_ctl02_iTextDuration")
	private WebElement jobscam;
	@FindBy(css = "#rptDuration_ctl03_iTextDuration")
	private WebElement jobscpm;

	// Select SubCategory Station North /South
	@FindBy(css = "#rptJobSubType_ctl01_hLinkSubCategory > div")
	private WebElement jobsccleaning;
	@FindBy(css = "#rptJobSubType_ctl02_hLinkSubCategory > div")
	private WebElement jobhdc;
	@FindBy(css = "#rptJobSubType_ctl03_hLinkSubCategory > div")
	private WebElement jobrestrictedduty;

	// SelectJob
	@FindBy(css = "input[type='button'][class='btn blue btn-sm cbtnsel']")
	private WebElement selectjobcategoryone;
	@FindBy(css = "input[id='selectExtraBTN']")
	private WebElement selectjobcategorytwo;
	@FindBy(css = "input[id='selectVacBTN']")
	private WebElement selectjobcategoryvacation;

	// SelectRDO
	@FindBy(css = "button[id='smTR']")
	private WebElement rdosmextrardo;
	@FindBy(css = "button[id='mtTR']")
	private WebElement rdomtextrardo;
	@FindBy(css = "button[id='twTR']")
	private WebElement rdotwextrardo;
	@FindBy(css = "button[id='wtTR']")
	private WebElement rdowtextrardo;
	@FindBy(css = "button[id='tfTR']")
	private WebElement rdotfextrardo;
	@FindBy(css = "button[id='fsTR']")
	private WebElement rdofsextrardo;
	@FindBy(css = "button[id='ssTR']")
	private WebElement rdossextrardo;

	// Select SubCategory Station Specialty Cleaning
	@FindBy(css = "#rptJobSubType_ctl01_hLinkSubCategory > div")
	private WebElement jobscdeepcleaning;
	@FindBy(css = "#rptJobSubType_ctl02_hLinkSubCategory > div")
	private WebElement jobmobile;
	@FindBy(css = "#rptJobSubType_ctl03_hLinkSubCategory > div")
	private WebElement jobrefuse;
	@FindBy(css = "#rptJobSubType_ctl04_hLinkSubCategory > div")
	private WebElement jobscrestricteduty;
	@FindBy(css = "#rptJobSubType_ctl05_hLinkSubCategory > div")
	private WebElement jobtrack;

	// Select RDO South/North
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='smTR']")
	private WebElement rdosm;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='mtTR']")
	private WebElement rdomt;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='twTR']")
	private WebElement rdotw;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='wtTR']")
	private WebElement rdowt;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='tfTR']")
	private WebElement rdotf;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='fsTR']")
	private WebElement rdofs;
	@FindBy(css = "tr[style='background: rgb(255, 255, 255);'] + tr > td>div>button[id='ssTR']")
	private WebElement rdoss;

	public JobSelection(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCategory(Logger logr, Timestamp timestamp, String strcategory, String strsubcategory,
			String strtour) throws InterruptedException, IOException, EmailException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectCategory");

		logr.info("strcategory: "+strcategory);
		logr.info("strsubcategory :"+strsubcategory);		
		logr.info("strtour :"+strtour);	
		
		Thread.sleep(3000);
		String currentURLCategory = driver.getCurrentUrl();
		logr.info("currentURLCategory :"+currentURLCategory);	
		if (currentURLCategory.contains("CustomApps")) {
			logr.info("currentURLCategory contains CustomApps");	
			
			if (strcategory.contentEquals("ELEVATOR")) {
				logr.info("Category contentEquals ELEVATOR");	
				
				if (jobcelevator.isDisplayed()) 
				{
					logr.info("jobcElevator isDisplayed ");	
					jobcelevator.click();				
					Thread.sleep(3000);								
				}

			} else if (strcategory.contentEquals("EXTRA"))
			{
				logr.info("strcategory contentEquals EXTRA");	
				if (jobcextra.isDisplayed()) {
					logr.info("jobcExtra isDisplayed ");	
					jobcextra.click();		
					Thread.sleep(3000);
					selectTour(logr, timestamp, strcategory, strtour);					
				}
			} else if (strcategory.contentEquals("RDO RELIEF")) {
				logr.info("strcategory contentEquals RDO RELIEF ");
				if (jobcrdoreleif.isDisplayed()) {
					logr.info("jobcRDOReleif isDisplayed ");
					jobcrdoreleif.click();			
					Thread.sleep(3000);
					selectTour(logr, timestamp, strcategory, strtour);						
				}

			} else if (strcategory.contentEquals("SPECIALTY CLEANING")) {
				logr.info("strcategory contentEquals SPECIALTY CLEANING");
				if (jobcspecialitycleaning.isDisplayed()) 
				{
					logr.info("jobcspecialitycleaning isDisplayed ");
						jobcspecialitycleaning.click();		
						Thread.sleep(3000);
						selectSubCategory(logr, timestamp, strcategory, strsubcategory);											
				}
			} else if (strcategory.contentEquals("STATION NORTH")) {
				logr.info("strcategory contentEquals STATION NORTH ");
				if (jobcstationnorth.isDisplayed()) {
					logr.info("jobcStationNorth isDisplayed ");
					jobcstationnorth.click();		
					Thread.sleep(3000);
						selectSubCategory(logr, timestamp, strcategory, strsubcategory);
				}
			} else if (strcategory.contentEquals("STATION SOUTH"))
			{
				logr.info("strcategory contentEquals STATION SOUTH");
				if (jobcstationsouth.isDisplayed()) 
				{
					logr.info("jobcStationSouth isDisplayed ");
					jobcstationsouth.click();				
					Thread.sleep(3000);
					selectSubCategory(logr, timestamp, strcategory, strsubcategory);						
				} 
			}
			else if (strcategory.contentEquals("VACATION RELIEF")) 
			{
				logr.info("Category contentEquals VACATION RELIEF");	
					if (jobcvacationrelief.isDisplayed()) {
						logr.info("jobcVacationRelief isDisplayed VACATION RELIEF");	
						jobcvacationrelief.click();		
						Thread.sleep(3000);						
					}
					else
					{
						System.out.println("Not displayed");
					}
			}			
		}

		logr.info("------------------------------------------------------------------------------------");

	}

	public void selectSubCategory(Logger logr, Timestamp timestamp, String strcategory, String strsubcategory)
			throws InterruptedException
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectSubCategory");
		
		logr.info("strcategory: "+strcategory);
		logr.info("strsubcategory :"+strsubcategory);	

		if (strcategory.contentEquals("SPECIALTY CLEANING"))
		{
			logr.info("strcategory contentEquals SPECIALTY CLEANING");	
			if (strsubcategory.contentEquals("DEEP CLEANING")) 
			{
				logr.info("strsubcategory contentEquals DEEP CLEANING");	
				if (jobscdeepcleaning.isDisplayed())
				{				
					logr.info("jobSCDeepCleaning isDisplayed ");	
					jobscdeepcleaning.click();
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("REFUSE")) 
			{
				logr.info("strsubcategory contentEquals REFUSE ");
				if (jobrefuse.isDisplayed())
				{
					logr.info("jobRefuse isDisplayed ");
					jobrefuse.click();
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("MOBILE")) 
			{
				logr.info("strsubcategory contentEquals MOBILE ");	
				if (jobmobile.isDisplayed())
				{
					logr.info("jobMobile isDisplayed ");
					jobmobile.click();		
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("RESTRICTED DUTY")) 
			{
				logr.info("strsubcategory contentEquals RESTRICTED DUTY");
				if (jobscrestricteduty.isDisplayed()) {				
					logr.info("jobSCRestrictedDuty isDisplayed ");				
					jobscrestricteduty.click();		
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("TRACK"))
			{
				logr.info("strsubcategory contentEquals TRACK");	
				if (jobtrack.isDisplayed()) {
					logr.info("jobTrack isDisplayed ");
					jobtrack.click();	
					Thread.sleep(3000);
				}
			}
		} else if (strcategory.contentEquals("STATION SOUTH") || strcategory.contentEquals("STATION NORTH")) {
			logr.info("strcategory contentEquals STATION SOUTH or STATION NORTH");
			if (strsubcategory.contentEquals("HDC")) {
				logr.info("strsubcategory contentEquals HDC");
				if (jobhdc.isDisplayed()) {
					logr.info("jobHDC isDisplayed ");
					jobhdc.click();
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("CLEANING"))
			{
				logr.info("strsubcategory contentEquals CLEANING");
				if (jobsccleaning.isDisplayed()) {
					logr.info("jobSCCleaning isDisplayed ");
					jobsccleaning.click();
					Thread.sleep(3000);
				}
			} else if (strsubcategory.contentEquals("RESTRICTED DUTY")) {
				logr.info("strsubcategory contentEquals RESTRICTED DUTY");
				if (jobrestrictedduty.isDisplayed()) {
					logr.info("jobRestrictedDuty isDisplayed ");
					jobrestrictedduty.click();
					Thread.sleep(3000);
				}
			}
		}
		logr.info("------------------------------------------------------------------------------------");

	}

	public void selectTour(Logger logr, Timestamp timestamp, String strcategory, String strtour)
			throws InterruptedException 
	{
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectTour");
		logr.info("strcategory: "+strcategory);	
		logr.info("strtour :"+strtour);	
	
		if (strcategory.contentEquals("RDO RELIEF"))
		{
			logr.info("strcategory contentEquals RDO RELIEF");
			try {
				logr.info("Trying to select Tour for RDO Relief");
			if (strtour.contentEquals("Night")) {
				logr.info("strtour contentEquals Night ");
				if (jobscnight.isDisplayed()) {
					logr.info("jobscnight isDisplayed ");			
					jobscnight.click();
					Thread.sleep(3000);
				}
			} else if (strtour.contentEquals("PM")) {
				logr.info("strtour contentEquals PM ");
				if (jobscpm.isDisplayed()) {
					logr.info("jobscpm isDisplayed ");
					jobscpm.click();
					Thread.sleep(3000);
				}
			} else if (strtour.contentEquals("AM")) {
				logr.info("strtour contentEquals AM ");
				if (jobscam.isDisplayed()) {
					logr.info("jobscam isDisplayed ");
					jobscam.click();
					Thread.sleep(3000);
				}				
			}
			} catch (Exception e) 
			{
			}
		} else if (strcategory.contentEquals("EXTRA")) 
		{					
			logr.info("strcategory contentEquals EXTRA");	
				try {
					logr.info("Trying to select Tour for EXTRA");
					if (strtour.contentEquals("Night")) {
						logr.info("strtour contentEquals Night");
						if (jobscnight.isDisplayed()) {
							logr.info("jobscpm isDisplayed ");
							jobscnight.click();
							Thread.sleep(3000);
						}
					} else if (strtour.contentEquals("PM")) {
						logr.info("strtour contentEquals PM");
						if (jobscpm.isDisplayed())
						{						
							logr.info("jobscpm isDisplayed");												
							driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;
							jobscpm.click();
					
							Thread.sleep(3000);
						}						
					} else if (strtour.contentEquals("AM"))
					{	
						logr.info("strtour contentEquals AM");
						if (jobscam.isDisplayed()) {
							logr.info("jobscam isDisplayed");	
							jobscam.click();
							Thread.sleep(3000);
						}
					}
				} catch (Exception e) {
				}
			}
		logr.info("------------------------------------------------------------------------------------");
		}
	
	public void selectJobs(Logger logr, Timestamp timestamp, String strcategory, String strrdo)
			throws IOException, InterruptedException, EmailException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectJobs");		
		logr.info("strcategory: "+strcategory);	
		logr.info("strrdo :"+strrdo);	
		if (strcategory.contentEquals("VACATION RELIEF"))
		{
			logr.info("strcategory contentEquals VACATION RELIEF");
			try {
				logr.info("Trying to selectJobs for VACATION RELIEF");
				driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;			
				selectjobcategoryvacation.click();
				driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;
				nextButtonJob(logr, timestamp);
			} catch (Exception e) {
			}

		} else if (strcategory.contentEquals("EXTRA") || strcategory.contentEquals("RDO RELIEF")) {
			logr.info("strcategory contentEquals EXTRA or RDO RELIEF");	
			try {
				logr.info("Trying to selectJobs for EXTRA or RDO RELIEF");		
				driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;				
				selectjobcategorytwo.click();
				driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;
			} catch (Exception e) {
			}
			selectJobRDO(logr, timestamp, strcategory, strrdo);
			nextButtonJob(logr, timestamp);
		} else if (strcategory.contentEquals("STATION SOUTH")||strcategory.contentEquals("STATION NORTH")||strcategory.contentEquals("SPECIALTY CLEANING")||strcategory.contentEquals("ELEVATOR"))
		{			
			try {
				driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS) ;

				int icount;
				icount = driver.findElements(By
						.xpath("//div[@class='col-md-6 jobDataAvailable']/div/table/tbody/tr[@class='even gradeX gridRow']"))
						.size();
				System.out.println("icount :" + icount);
				Random random = new Random();
				int min = 1;
				int max = 2;				
				if((icount % 2 != 0 ))
				{
						max1 =	Math.round(icount);
			
				}
				else {
						max1 = icount / 2;					
				}						
							for(int i = 1;i<=max1;)
							{																		
							System.out.println("max1: "+max1);
							int randomnumber = random.nextInt(max + 1 - min) + min;
							System.out.println("randomNumber: "+randomnumber);
							int randomnumber1 = random.nextInt(max1 + 1 - min) + min;
							System.out.println("randomNumber1: "+randomnumber1);
							String findjob = "//div[@class='col-md-6 jobDataAvailable']["+randomnumber+"]/div/table/tbody/tr[@class='even gradeX gridRow']["+randomnumber1+"]/td/input[@type='button']";
							System.out.println("findJob: "+findjob);	
								try
								{
							
								if (driver.findElement(By.xpath(findjob)).isDisplayed())
								{
										logr.info("findJobElement isDisplayed");	
										driver.findElement(By.xpath(findjob)).click();
										vTriggerId = driver.findElement(By.xpath(findjob)).getAttribute("id");
										break;
								}
								else 
								{								
										String jobSelected =driver.findElement(By.xpath("//div[@class='col-md-6 jobDataAvailable'][2]/div/table/tbody/tr[@class='even gradeX gridRow'][5]/td[@colspan]")).getText();
										if(jobSelected.contains("selected by"))
										{
											System.out.println("For loop next increment");
											i++;
										}
								}							
								}
								catch(Exception e)
								{
								
								}
					} 
					selectJobRDO(logr, timestamp, strcategory, strrdo);
					nextButtonJob(logr, timestamp);	
			} catch (Exception e)
			{
			}			
		}

		logr.info("------------------------------------------------------------------------------------");
	}

	public void selectJobRDO(Logger logr, Timestamp timestamp, String strcategory, String strrdo)
			throws IOException, InterruptedException, EmailException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: selectJobRDO");	
		logr.info("strcategory: "+strcategory);	
		logr.info("strrdo :"+strrdo);	
		
		if (strcategory.contentEquals("STATION NORTH") || strcategory.contentEquals("STATION SOUTH")) {
			logr.info("strcategory contentEquals STATION NORTH or STATION SOUTH");	
			try {
				logr.info("Trying to selectJobRDO for STATION NORTH or STATION SOUTH");	
				if (strrdo.contentEquals("S/M")) {
					logr.info("strrdo to contentEquals S/M");	
					{
						Thread.sleep(3000);
						Thread.sleep(3000);
						System.out.println(vTriggerId+": vTriggerId");
						String trigPath = "//input[@id='"+vTriggerId+"']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='smTR']";
						System.out.println(trigPath);
						driver.findElement(By.xpath("//input[@id='"+vTriggerId+"']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='smTR']")).click();
						Thread.sleep(3000);
					}

				} else if (strrdo.contentEquals("M/T")) {
					logr.info("strrdo to contentEquals M/T");	
					{
						Thread.sleep(3000);
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='mtTR']")).click();
						Thread.sleep(3000);
					}

				} else if (strrdo.contentEquals("T/W")) {
					logr.info("strrdo to contentEquals T/W");	
					{
						Thread.sleep(2000);
						//Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='twTR']")).click();
						Thread.sleep(3000);
					}

				} else if (strrdo.contentEquals("W/T")) {
					logr.info("strrdo to contentEquals W/T");	
					{
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='wtTR']")).click();
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("T/F")) {
					logr.info("strrdo to contentEquals T/F");	
					{
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='tfTR']")).click();
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("F/S")) {
					logr.info("strrdo to contentEquals F/S");	
					{
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='fsTR']")).click();
						Thread.sleep(3000);
					}
				} else {
					{
						Thread.sleep(3000);
						driver.findElement(By.xpath("//input[@id='" + vTriggerId
								+ "']/parent::td/parent::tr/following-sibling::tr[1]/td/div/button[@id='ssTR']")).click();
						Thread.sleep(3000);
					}
				}
			} catch (Exception e)
			{
			}
		} else if (strcategory.contentEquals("EXTRA") || strcategory.contentEquals("RDO RELIEF")) {
			logr.info("strcategory contentEquals EXTRA or RDO RELIEF");
			try {
				logr.info("Trying to select RDO");
				if (strrdo.contentEquals("S/M")) {
					logr.info("strrdo to contentEquals S/M");	
					if (rdosmextrardo.isDisplayed()) {
						logr.info("rdosmextrardo isDisplayed");
						Thread.sleep(3000);
						rdosmextrardo.click();
						Thread.sleep(3000);
					}

				} else if (strrdo.contentEquals("M/T")) {
					logr.info("strrdo to contentEquals M/T");	
					if (rdomtextrardo.isDisplayed()) {
						logr.info("rdomtextrardo isDisplayed");
						Thread.sleep(3000);
						rdomtextrardo.click();
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("T/W")) {
					logr.info("strrdo contentEquals T/W");
					
					if (rdotwextrardo.isDisplayed()) {
						logr.info("rdotwextrardo isDisplayed ");
						Thread.sleep(3000);
						rdotwextrardo.click();
						logr.info("rdotwextrardo is clicked ");
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("W/T")) {
					logr.info("strrdo to contentEquals W/T");	
					if (rdowtextrardo.isDisplayed()) {
						logr.info("rdowtextrardo isDisplayed");
						Thread.sleep(3000);
						rdowtextrardo.click();
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("T/F")) {
					logr.info("strrdo to contentEquals T/F");	
					if (rdotfextrardo.isDisplayed()) {
						logr.info("rdotfextrardo isDisplayed");
						rdotfextrardo.click();
						Thread.sleep(3000);
						Thread.sleep(3000);
					}
				} else if (strrdo.contentEquals("F/S")) {
					logr.info("strrdo to contentEquals F/S");	
					if (rdofsextrardo.isDisplayed()) {
						logr.info("rdofsextrardo isDisplayed");
						Thread.sleep(3000);
						Thread.sleep(3000);
						rdofsextrardo.click();
						Thread.sleep(3000);
						Thread.sleep(3000);
					}
				} else {
					if (rdossextrardo.isDisplayed()) {
						logr.info("rdossextrardo isDisplayed");
						Thread.sleep(3000);
						rdossextrardo.click();
						Thread.sleep(3000);
					}
				}
			} catch (Exception e) 
			{
			}
		}
		
		logr.info("------------------------------------------------------------------------------------");
	}

	public void nextButtonJob(Logger logr, Timestamp timestamp) throws InterruptedException {
		timestamp  = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: nextButtonJob");
		Thread.sleep(3000);
		
			try {
				((JavascriptExecutor) driver)
			     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
				
				if(downnext.isDisplayed())
				{
					System.out.println("downNext is displayed");
				Thread.sleep(3000);
				downnext.click();
				
				} else
				{
				   JavascriptExecutor jse = (JavascriptExecutor) driver;
				    jse.executeScript("window.scrollBy(0,250)", "");				    					
					if (uppernext.isDisplayed()) 
					{
						System.out.println("upperNext is displayed");
						Thread.sleep(3000);
						uppernext.click();
					}
				}
					
						
			
		} catch (Exception e) 
			{
			}
		logr.info("------------------------------------------------------------------------------------");
	}
}
