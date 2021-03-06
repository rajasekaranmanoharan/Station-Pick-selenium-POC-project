package util;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import java.text.SimpleDateFormat;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Transform;



public class HTMLLayout extends org.apache.log4j.HTMLLayout
{
	
	static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
	static final Logger log = Logger.getLogger(HTMLLayout.class);
	public static final String LOCATION_INFO_OPTION = "LocationInfo";
	boolean locationInfo = false;
	
	public static final String TITLE_OPTION = "Title";
    String title = "StationPick HTML Log";
	
	public  StringBuffer sbuf = new StringBuffer(BUF_SIZE);
	
	public HTMLLayout()  
	{  
		super();  
	}  
	
	public String getHeader() 
	{
	    StringBuffer sbuf = new StringBuffer();
	    sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"  + Layout.LINE_SEP);
	    sbuf.append("<html>" + Layout.LINE_SEP);
	    sbuf.append("<head>" + Layout.LINE_SEP);
	    sbuf.append("<title>" + title + "</title>" + Layout.LINE_SEP);
	    sbuf.append("<style>h2{color:#008000}</style>");
	    sbuf.append("<style type=\"text/css\">"  + Layout.LINE_SEP);
	    sbuf.append("<!--"  + Layout.LINE_SEP);
	    sbuf.append("body, table {font-family: arial,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
	    sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
	    sbuf.append("-->" + Layout.LINE_SEP);
	    sbuf.append("</style>" + Layout.LINE_SEP);
	    sbuf.append("</head>" + Layout.LINE_SEP);
	    sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
	    sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
	    sbuf.append("Log session start time " + new java.util.Date() + "<br>" + Layout.LINE_SEP);
	    sbuf.append("<br>" + Layout.LINE_SEP);
	    sbuf.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);
	    sbuf.append("<tr>" + Layout.LINE_SEP);
	    sbuf.append("<th>Time</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Thread</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Level</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Category</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Message</th>" + Layout.LINE_SEP);
	    sbuf.append("</tr>" + Layout.LINE_SEP);
	    return sbuf.toString();
	  }
	    
	 public
	  String format(LoggingEvent event) {

	    if(sbuf.capacity() > MAX_CAPACITY) {
	      sbuf = new StringBuffer(BUF_SIZE);
	    } else {
	      sbuf.setLength(0);
	    }

	    sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

	    sbuf.append("<td>");
	    sbuf.append("<h3>");
	    sbuf.append(event.timeStamp - LoggingEvent.getStartTime());
	    sbuf.append("<h3>");
	    sbuf.append("</td>" + Layout.LINE_SEP);

	    String escapedThread = Transform.escapeTags(event.getThreadName());
	    sbuf.append("<td title=\"" + escapedThread + " thread\">");
	    sbuf.append("<h3>");
	    sbuf.append(escapedThread);
	    sbuf.append("</h3>");
	    sbuf.append("</td>" + Layout.LINE_SEP);

	    sbuf.append("<td title=\"Level\">");
	    sbuf.append("<h3>");
	    if (event.getLevel().equals(Level.DEBUG)) {
	      sbuf.append("<font color=\"#339933\">");
	      sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	      sbuf.append("</font>");
	    }
	    else if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
	      sbuf.append("<font color=\"#993300\"><strong>");
	      sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	      sbuf.append("</strong></font>");
	    } else {
	      sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	    }
	    sbuf.append("</h3>");
	    sbuf.append("</td>" + Layout.LINE_SEP);

	    String escapedLogger = Transform.escapeTags(event.getLoggerName());
	    sbuf.append("<td title=\"" + escapedLogger + " category\">");
	    sbuf.append("<h3>");
	    sbuf.append(escapedLogger);
	    sbuf.append("</h3>");
	    sbuf.append("</td>" + Layout.LINE_SEP);

	    if(locationInfo) {
	      LocationInfo locInfo = event.getLocationInformation();
	      sbuf.append("<td>");
	      sbuf.append("<h3>");
	      sbuf.append(Transform.escapeTags(locInfo.getFileName()));
	      sbuf.append(':');
	      sbuf.append(locInfo.getLineNumber());
	      sbuf.append("</h3>");
	      sbuf.append("</td>" + Layout.LINE_SEP);
	    }

	    sbuf.append("<td title=\"Message\">");
	   // sbuf.append("<h3>");
	    String msg = Transform.escapeTags(event.getRenderedMessage());
	    if(msg.contains("CustomerName"))
	    {
	    	sbuf.append("<h2>"+msg+"</h2>");
	    }
	    else
	    {
	    	sbuf.append("<h3>"+msg+"<h3>");
	    }
	   // sbuf.append("</h3>");
	    sbuf.append("</td>" + Layout.LINE_SEP);
	    sbuf.append("</tr>" + Layout.LINE_SEP);

	    if (event.getNDC() != null) {
	      sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
	      sbuf.append("<h3>");
	      sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
	      sbuf.append("</h3>");
	      sbuf.append("</td></tr>" + Layout.LINE_SEP);
	    }

	    String[] s = event.getThrowableStrRep();
	    if(s != null) {
	      sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
	      sbuf.append("<h3>");
	      appendThrowableAsHTML(s, sbuf);
	      sbuf.append("</h3>");
	      sbuf.append("</td></tr>" + Layout.LINE_SEP);
	    }

	    return sbuf.toString();
	  }
	 void appendThrowableAsHTML(String[] s, StringBuffer sbuf) {
		    if(s != null) {
		      int len = s.length;
		      if(len == 0)
			return;
		      sbuf.append(Transform.escapeTags(s[0]));
		      sbuf.append(Layout.LINE_SEP);
		      for(int i = 1; i < len; i++) {
			sbuf.append(TRACE_PREFIX);
			sbuf.append(Transform.escapeTags(s[i]));
			sbuf.append(Layout.LINE_SEP);
		      }
		    }
		  }

}		


