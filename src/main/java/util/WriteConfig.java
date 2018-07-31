package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriteConfig 
{
	
      public static void writeTextFile() throws IOException 
	        {
	        
	        File statText = new File("C:/Selenium Environment/config.properties");
	        FileOutputStream is = new FileOutputStream(statText);
	        OutputStreamWriter osw = new OutputStreamWriter(is);    
	        Writer w = new BufferedWriter(osw);
	        w.write("URL = http://10.60.11.98:8080/Kezava/Stations/default.aspx"+System.lineSeparator());
	        w.write("LogsFolder = C://Selenium Environment/Log"+System.lineSeparator());
	        w.write("Log4j = C://Selenium Environment/log4j.properties"+System.lineSeparator());
	        w.write("ExcelFile = C://Selenium Environment/Station51Users.xlsx"+System.lineSeparator());
	        w.write("SheetName = Sheet1"+System.lineSeparator());
	        w.write("Screenshotlocation = C://Selenium Environment/Screenshot/UsersJobSelection/"+System.lineSeparator());
	        w.write("ChromeDriverPath = C://Selenium Environment/Browser Drivers/chromedriver.exe"+System.lineSeparator());
	        w.write("FireFoxDriverPath = C://Selenium Environment/Browser Drivers/geckodriver.exe"+System.lineSeparator());
	        w.write("IEDriverPath = C://Selenium Environment/Browser Drivers/IEDriverServer.exe"+System.lineSeparator());
	        w.write("EdgeDriverPath = C://Selenium Environment/Browser Drivers/MicrosoftWebDriver.exe"+System.lineSeparator());
	        System.out.println("Created Config file successfully");
	        w.close();
	        }

}

