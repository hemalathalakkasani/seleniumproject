package utilities;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	public void onStart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("nopcommerce");
		sparkReporter.config().setReportName("registrationpage");
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("application","nopcomm");
		extent.setSystemInfo("module", "frontend");
		extent.setSystemInfo("submodule", "customers");
		extent.setSystemInfo("operation", System.getProperty("os.name"));
		extent.setSystemInfo("UserName", System.getProperty("username"));
		extent.setSystemInfo("environment", "QA");
		extent.setSystemInfo("user", "hema");
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "test passed");
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "test failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		try
		{
			String screenshotpath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName();
			test.addScreenCaptureFromPath(screenshotpath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "test skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	 public void sendmail()
	 {
		try
		{
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("hemalatha.lakkasani@gmail.com","yuvaneeth@1"));
			email.setSSLOnConnect(true);
			email.setFrom("hemalatha.lakkasani@gmail.com");
			email.setSubject("test results");
			email.setMsg("please find attached report");
			email.addTo("hemalatha.lakkasani@gmail.com");
			email.attach(url,"extent report","please check report");
			email.send();
		}
		 catch(Exception e)
		{
			 e.printStackTrace();
		}
	}

}

