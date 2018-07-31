package bpa.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import bpa.page.Dashboard;
import bpa.page.JobSelection;
import bpa.page.LoginApplication;
import bpa.page.ReviewSelection;
import bpa.page.WeekSelection;
import util.HTMLLayout;
import util.WriteConfig;

public class BaseStationPOM extends HTMLLayout 
{
	static LoginApplication loginPage;
	static Dashboard dashboardapp;
	static JobSelection jobselect;
	static WeekSelection weekselect;
	static ReviewSelection reviewselect;
	static private Timestamp timestamp;
	static private WebDriver driver;
	
	protected static Logger logr = Logger.getLogger(BaseStationPOM.class);
	
	Properties props = new Properties();

	@BeforeClass
	public void setupConfig() throws IOException, InterruptedException 
	{
		WriteConfig.writeTextFile();
		FileInputStream fis = new FileInputStream("C://Selenium Environment/config.properties");
		props.load(fis);
		// loading properites from properties file
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateformat.format(new Date()));
		Thread.sleep(3000);
		PropertyConfigurator.configure(props.getProperty("Log4j"));
		PropertyConfigurator.configure("C://Selenium Environment/config.properties");
		Thread.sleep(3000);
	}

	@Parameters({ "browser" })
	@Test(priority = 0)
	public void launchBrowser(String browser) throws IOException, InterruptedException 
	{
		timestamp = new Timestamp(System.currentTimeMillis());
		logr.info("------------------------------------------------------------------------------------");
		logr.info("Timestamp: " + timestamp + "");
		logr.info("Category: launchBrowser");
		
		if (browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "" + props.getProperty("ChromeDriverPath"));			
			driver = new ChromeDriver();
		}
		else if (browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", props.getProperty("IEDriverPath"));
			driver = new InternetExplorerDriver();
		} 
		else if (browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "" + props.getProperty("FireFoxDriverPath"));
			driver = new FirefoxDriver();
		} 
		else if (browser.equals("edge"))
		{
			System.setProperty("webdriver.gecko.driver", "" + props.getProperty("EdgeDriverPath"));
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(props.getProperty("URL"));
		
		logr.info("------------------------------------------------------------------------------------");
	}

	@Test(priority = 1, dataProvider = "testDataProvider")
	public static void testFunctionality(String strcategory, String strtour, String strrdo, String strcustname,
			String strusername, String strpassword, String strjobid, String weekone, String weektwo, String weekthree,
			String weekfour, String weekfive, String strsubcategory)
			throws InterruptedException, IOException, EmailException, ParserConfigurationException, SAXException 
	{

		loginPage = new LoginApplication(driver);
		loginPage.enterUserName(logr, timestamp, strusername, strcustname);
		loginPage.setPassword(logr, timestamp, strpassword);
		loginPage.clickLogin(logr, timestamp, strcustname);

		dashboardapp = new Dashboard(driver);
		dashboardapp.selectApp(logr, timestamp);

		jobselect = new JobSelection(driver);
		jobselect.selectCategory(logr, timestamp, strcategory, strsubcategory, strtour);
		jobselect.selectJobs(logr, timestamp, strcategory, strrdo);

		weekselect = new WeekSelection(driver);
		weekselect.selectWeek(logr, timestamp, weekone, weektwo, weekthree, weekfour, weekfive);

		reviewselect = new ReviewSelection(driver);
		reviewselect.saveReview(logr, timestamp, strcustname);

	}

	@DataProvider(name = "testDataProvider")
	public Object[][] loginData() throws InvalidFormatException, IOException, InterruptedException, EmailException
	{
		String filename = props.getProperty("ExcelFile");
		String sheetname = props.getProperty("SheetName");
		Object[][] arrayObject = getExcelData(filename, sheetname);
		return arrayObject;
	}

	public Object[][] getExcelData(String filename, String sheetname)
			throws InvalidFormatException, IOException, InterruptedException, EmailException 
	{
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		int totalrows = sheet.getLastRowNum();
		int totalColums = 13;
		Object obj[][] = new Object[totalrows][totalColums];
		
		for (int i = 0; i < totalrows; i++) 
		{
			obj[i][0] = sheet.getRow(i + 1).getCell(4).toString();
			obj[i][1] = sheet.getRow(i + 1).getCell(5).toString();
			obj[i][2] = sheet.getRow(i + 1).getCell(6).toString();
			obj[i][3] = sheet.getRow(i + 1).getCell(13).toString();
			obj[i][4] = sheet.getRow(i + 1).getCell(15).toString();
			obj[i][5] = sheet.getRow(i + 1).getCell(16).toString();
			obj[i][6] = sheet.getRow(i + 1).getCell(18).toString();
			obj[i][7] = sheet.getRow(i + 1).getCell(28).toString();
			obj[i][8] = sheet.getRow(i + 1).getCell(29).toString();
			obj[i][9] = sheet.getRow(i + 1).getCell(30).toString();
			obj[i][10] = sheet.getRow(i + 1).getCell(31).toString();
			obj[i][11] = sheet.getRow(i + 1).getCell(32).toString();
			obj[i][12] = sheet.getRow(i + 1).getCell(47).toString();
		}
		workbook.close();
		return obj;
	}
}
