package org.honeywell.Trace.testcase_scripts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.honeywell.Trace.common_methods.GenricApplicationMethods;
import org.honeywell.Trace.driver.DriverScript;
import org.honeywell.Trace.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Reports_TestCases {

	// Assign of created IE driver browser object
	WebDriver obrowser = null;
	GenricApplicationMethods oGenericAppmethods = new GenricApplicationMethods();
	DriverScript oDriver = new DriverScript();
	Utility appInd = new Utility();
	String strTCID = oDriver.getTestcaseId();
	String strStatus = null;

	int count;
	long start;
	long end;
	long totalTime;
	String newTotalTime;
	String headerName = "HeaderUI";
	String headerText = "Test";
	String footerName = "FooterUI";
	String footerText = "Test";



	
	/********************************
	 * Method Name : TC_Loginscreen Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Loginscreen() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Loginscreen:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));

				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// Set the username value
						strStatus += String.valueOf(
								appInd.clearAndSetObject(obrowser, "Login_txtbx_Username", oinpuTDtMap.get("Param_1")));
						// Set the Password value
						strStatus += String.valueOf(
								appInd.clearAndSetObject(obrowser, "Login_txtbx_Password", oinpuTDtMap.get("Param_2")));
						// click on the ok button
						By byclickOnLogin_btn_OK = appInd.createAndGetByObject("Login_btn_OK");
						WebElement element = obrowser.findElement(byclickOnLogin_btn_OK);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
					
						Thread.sleep(30000);
						String ur=obrowser.getCurrentUrl();
						System.out.println(ur);
                  		if (ur.contains("home")) {
							appInd.writeLog("Fail", "'TC_Loginscreen' script was failed");
							bolstatus = false;
							strStatus += false;
						}
						 else {
					/*		appInd.writeLog("Pass", "'TC_Loginscreen' script was Successful");
					/bolstatus = false;
							strStatus = null;*/
					
							StopWatch pageLoad = new StopWatch();
						pageLoad.start();
						long pageLoadTime_ms = 0;
						double pageLoadTime_Seconds = 0;
						try {
														
							WebDriverWait wait = new WebDriverWait(obrowser, 30);
							wait.until(ExpectedConditions.presenceOfElementLocated(By.id("defect-trend-chart")));
							System.out.println("after waituntill");
							pageLoad.stop();
							pageLoadTime_ms = pageLoad.getTime();
							System.out.println(pageLoad.getTime());
							String waitingTime = String.valueOf(pageLoad.getTime());
							if (waitingTime != null) {
								pageLoadTime_Seconds = pageLoadTime_ms / 1000.0;
								if (pageLoadTime_ms <= 30000) {
									strcommonTime= String.valueOf(pageLoadTime_Seconds) ;
									strcommonCountvalue = strcommonTime + " second";
									strStatus += true;
									appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_ms);
								} else {
									strcommonCountvalue = strcommonTime + " second";
									strStatus += false;
									appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_ms);
									
								}
							}
							Thread.sleep(3000);
							By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
							Thread.sleep(3000);
							WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
						
						} catch (Exception e) {
							strStatus += false;
							appInd.writeLog("Exception",
									"Exception while executing wait untill process in 'TC_Loginscreen' method. ");// +
							// e.getMessage());
						}
					 }
						
						// ########################################################################
						if (strStatus.contains("false")) {
							String s=oinpuTDtMap.get("Param_1");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\Login\\"+s+"Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_Loginscreen' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Loginscreen' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Failed_to_launch_IE_browser_snapshot.png");
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				strcurrentTD = String.valueOf(TD);
				oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
				strcommonCountvalue = null;
			} // for loop
			return oinputMap;
			
			
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_Loginscreen' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}

	/********************************
	 * Method Name : TC_Loginscreen Purpose : This method will launch the home
	 * screen Author : Vijay k Reviewer : Date of Creation : 21-Nov-2018 Date of
	 * modification ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> Login() {	
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Loginscreen:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));

				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// Set the username value
						strStatus += String.valueOf(
								appInd.clearAndSetObject(obrowser, "Login_txtbx_Username", oinpuTDtMap.get("Param_1")));
						// Set the Password value
						strStatus += String.valueOf(
								appInd.clearAndSetObject(obrowser, "Login_txtbx_Password", oinpuTDtMap.get("Param_2")));
						// click on the ok button
						By byclickOnLogin_btn_OK = appInd.createAndGetByObject("Login_btn_OK");
						WebElement element = obrowser.findElement(byclickOnLogin_btn_OK);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						Thread.sleep(30000);
						//Thread.sleep(60000);
						String ur=obrowser.getCurrentUrl();
	              		if (ur.contains("home")) {
							appInd.writeLog("Fail", "'TC_Loginscreen' script was failed");
							bolstatus = false;
							strStatus += false;
						}
						 else {
							 bolstatus = true;
								strStatus += true;	 
						 }
						
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Loginscreen_invalidCredential_Fail_snapshot.png");
							System.out.println("System.getProperty(\"user.dir\"):::" +System.getProperty("user.dir"));
							appInd.writeLog("Fail", "'TC_Loginscreen' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Loginscreen' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Failed_to_launch_IE_browser_snapshort.png");
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					//oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					//oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					//oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					//oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				strcurrentTD = String.valueOf(TD);
				oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
			
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_Loginscreen' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
}

	/********************************
	 * Method Name : Logout Purpose : This method will logout the application screen
	 * Author : Vijay k Reviewer : Date of Creation : 30-Oct-2018 Date of
	 * modification :
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> Logout() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_Loginscreen:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {

						By logout = appInd.createAndGetByObject("Logout");
						WebElement elementbylogout = obrowser.findElement(logout);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementbylogout);
						if (elementbylogout != null) {
							strStatus += "true";
						} else {

							strStatus += "false";
						}
						// strStatus += String.valueOf(appInd.clickObject(obrowser, "Logout"));
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser,
									System.getProperty("user.dir") + "\\Results\\snapshot\\Logout_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_Logout' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Logout' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
				} else {
					oinpuTDtMap.put("result", "Skip");
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			try {
				appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
						+ "\\Results\\snapshot\\Logout_Fail_dueTo_Exception_snapshot.png");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_Logout' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}


//==============Reports TestCases============


/********************************
 * Method Name : TC_Reports_CreateReportPackageWithSingleSystemAndSingleQuery
 * Purpose : This method will Create a Report with one System and One Query
 * Author : Rajashree Reviewer : Date of Creation : 28-Jan-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_CreateReportPackageWithSingleSystemAndSingleQuery() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_CreateReportPackageWithSingSystemAndSingleQuery:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());
						
						if(system_elements.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "System is not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
						WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
						List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
						System.out.println(reports_ChangeQuries_AddFilterButton.size());
						
						if(reports_ChangeQuries_AddFilterButton.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							System.out.println("Change Queries are not present");
							appInd.writeLog("", "Change Queries are not present");	
							
							By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
							WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
							
							By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
							List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
							System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
							
							if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
								strStatus = "true";
								Thread.sleep(3000);
							}else {
								System.out.println("Engineering Anomaly are not present");
								appInd.writeLog("", "Engineering Anomaly are not present");	
								
								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
								
								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
								System.out.println(reports_ParameterQueries_AddFilterButton.size());
								
								if(reports_ParameterQueries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Parameter Queries are not present");
									appInd.writeLog("", "Parameter Queries are not present");	
									
									By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
									WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
									
									By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
									List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
									System.out.println(reports_Spare_AddFilterButton.size());
									
									if(reports_Spare_AddFilterButton.size()>0) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
										strStatus = "true";
										Thread.sleep(3000);
									}else {
										System.out.println("Spares are not present");
										appInd.writeLog("", "Spares are not present");	
									}
									
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
							}
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						
						for(int i=0;i<savedHeaderNameList.size();i++) {
							if(savedHeaderNameList.get(i).getAttribute("id").equalsIgnoreCase(headerName)) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(i));
								appInd.writeLog("Pass", "Clicked on header");
								strStatus="true";
								Thread.sleep(3000);
								break;
							}

							strStatus="false";
							
						}
						if(strStatus.contains("false")) {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
//						if(savedHeaderNameList.size()>0) {
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
//						}else {
//							appInd.writeLog("Fail", "Saved header names are not present");	
//							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
//						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
					
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(5000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						
						for(int i=0;i<savedFooterNameList.size();i++) {
							String str = savedHeaderNameList.get(i).getAttribute("id");
							System.out.println(str);
							if(savedFooterNameList.get(i).getAttribute("id").equalsIgnoreCase(footerName)) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(i));
								appInd.writeLog("Pass", "Clicked on footer");
								strStatus="true";
								Thread.sleep(3000);
								break;
							}

							strStatus="false";
							
						}
						if(strStatus.contains("false")) {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						
//						if(savedFooterNameList.size()>0) {
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
//						}else {
//							appInd.writeLog("Fail", "Saved footer names are not present");	
//							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
//						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("fail", "Report Package is not saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
							}
						}else {
							
							if(flag1==true) {
								strStatus = "false";
								appInd.writeLog("fail", "Report Package is not saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "Report Package is not saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
							
						}
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_CreateReportPackageWithSingSystemAndSingleQuery' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_CreateReportPackageWithSingSystemAndSingleQuery' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_CreateReportPackageWithSingSystemAndSingleQuery' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery
 * Purpose : This method will Create a Report with two System and multiple Query
 * Author : Rajashree Reviewer : Date of Creation : 28-Jan-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());
						
				
						if(system_elements.size()>=2) {
							for(int i=0;i<2;i++) {
								system_elements = obrowser.findElements(systems);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(i));
								strStatus = "true";
								Thread.sleep(3000);
								
								By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
								WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
								Thread.sleep(4000);
								
								By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
								List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
								System.out.println(reports_ChangeQuries_AddFilterButton.size());
								
								if(reports_ChangeQuries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Change Queries are not present");
									appInd.writeLog("", "Change Queries are not present");
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
								Thread.sleep(4000);
								
								By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
								WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
								Thread.sleep(4000);
								
								By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
								List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
								System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
								
								if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Engineering Anomaly are not present");
									appInd.writeLog("", "Engineering Anomaly are not present");	
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
								Thread.sleep(4000);
								
//								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
//								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
//								Thread.sleep(4000);
//								
//								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
//								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
//								System.out.println(reports_ParameterQueries_AddFilterButton.size());
//								
//								if(reports_ParameterQueries_AddFilterButton.size()>0) {
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
//									strStatus = "true";
//									Thread.sleep(3000);
//								}else {
//									System.out.println("Parameter Queries are not present");
//									appInd.writeLog("", "Parameter Queries are not present");
//								}
//								
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
//								Thread.sleep(4000);
								
								By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
								WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
								Thread.sleep(4000);
								
								By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
								List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
								System.out.println(reports_Spare_AddFilterButton.size());
								
								if(reports_Spare_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Spares are not present");
									appInd.writeLog("", "Spares are not present");	
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
								Thread.sleep(4000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
								Thread.sleep(4000);
							}
							
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "Multiple System are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
						}
						
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
						
						
						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);
												
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportPackage_AlreadyExist_Ok_Button");
						boolean flag2 = appInd.ifElementsPresent(obrowser, "NoFilterAddedToSystem_Ok_Button");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package is already exist");
							strStatus = "false";
							appInd.writeLog("Fail", "Report Package is already exist");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
														
							By ReportPackage_AlreadyExist_Ok_Button = appInd.createAndGetByObject("ReportPackage_AlreadyExist_Ok_Button");
							WebElement reportPackage_AlreadyExist_Ok_Button = obrowser.findElement(ReportPackage_AlreadyExist_Ok_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportPackage_AlreadyExist_Ok_Button);
							
							System.out.println("clicked on okay button");
							Thread.sleep(3000);
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
							Thread.sleep(5000);	
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
							Thread.sleep(5000);	
						}else if(flag2==true) {
							System.out.println("No Filter Added To System");
							strStatus = "false";
							appInd.writeLog("Fail", "No Filter Added To System");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
														
							By NoFilterAddedToSystem_Ok_Button = appInd.createAndGetByObject("NoFilterAddedToSystem_Ok_Button");
							WebElement noFilterAddedToSystem_Ok_Button = obrowser.findElement(NoFilterAddedToSystem_Ok_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",noFilterAddedToSystem_Ok_Button);
							
							System.out.println("clicked on okay button");
							Thread.sleep(3000);
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
							Thread.sleep(5000);	
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
							Thread.sleep(5000);	
						}else if(flag1==true){
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("Fail", "Report Package is not saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
							}
						}else {
							
							if(flag3==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {

								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
										
							
						}
						
						
						
						
//						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
//						System.out.println(flag);
//						if(flag==true) {
//							System.out.println("Report Package saved successfully popup is displayed");
//							strStatus = "true";
//							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
//																					
//							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
//							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
//							
//							System.out.println("clicked on okay button");
//							Thread.sleep(5000);
//							
//							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
//							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
//							for(int j=0;j<report_PackageName_List.size();j++) {
//								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
//									strStatus = "true";
//									appInd.writeLog("pass", "Report Package saved successfully");
//									Thread.sleep(5000);	
//									break;
//								}
//								strStatus = "false";
//							}
//							if(strStatus.contains("false")) {
//								strStatus = "false";
//								appInd.writeLog("Fail", "Report Package is not saved successfully");
//								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
//							}
//						}else {
//							strStatus = "false";
//							appInd.writeLog("pass", "Report Package saved successfully");
//							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
//						}
						
						
						
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithMultilepleSystemAndMultipleQuery.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_CreateReportPackageWithMultilepleSystemAndMultipleQuery' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_CreateReportPackageWithExternalSystems
 * Purpose : This method will Create a Report with Adding External Systems
 * Author : Rajashree Reviewer : Date of Creation : 28-Jan-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_CreateReportPackageWithExternalSystems() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_CreateReportPackageWithExternalSystems:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						System.out.println("Clicked on create Report Package");
						Thread.sleep(4000);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						System.out.println("Clicked on configure_Report_Tab");
						Thread.sleep(4000);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						System.out.println("Clicked on reports_SelectSystem_dropdown");
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println("system_elements : "+system_elements.size());
						
				
						if(system_elements.size()>=2) {
							for(int i=0;i<2;i++) {
								system_elements = obrowser.findElements(systems);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(i));
								strStatus = "true";
								Thread.sleep(3000);
								
								By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
								WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
								System.out.println("Clicked on reports_ChangeQuries_dropdown");
								Thread.sleep(4000);
								
								By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
								List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
								System.out.println(reports_ChangeQuries_AddFilterButton.size());
								
								if(reports_ChangeQuries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Change Queries are not present");
									appInd.writeLog("", "Change Queries are not present");
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
								System.out.println("Clicked on reports_ChangeQuries_dropdown");
								Thread.sleep(4000);
								
								By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
								WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
								System.out.println("Clicked on reports_EngineeringAnomaly_dropdown");
								Thread.sleep(4000);
								
								By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
								List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
								System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
								
								if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Engineering Anomaly are not present");
									appInd.writeLog("", "Engineering Anomaly are not present");	
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
								System.out.println("Clicked on reports_EngineeringAnomaly_dropdown");
								Thread.sleep(4000);
								
//								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
//								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
//								System.out.println("Clicked on reports_ParameterQueries_dropdown");
//								Thread.sleep(4000);
//								
//								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
//								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
//								System.out.println(reports_ParameterQueries_AddFilterButton.size());
//								
//								if(reports_ParameterQueries_AddFilterButton.size()>0) {
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
//									strStatus = "true";
//									Thread.sleep(3000);
//								}else {
//									System.out.println("Parameter Queries are not present");
//									appInd.writeLog("", "Parameter Queries are not present");
//								}
//								
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
//								System.out.println("Clicked on reports_ParameterQueries_dropdown");
//								Thread.sleep(4000);
								
								By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
								WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
								System.out.println("Clicked on reports_Spare_dropdown");
								Thread.sleep(4000);
								
								By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
								List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
								System.out.println(reports_Spare_AddFilterButton.size());
								
								if(reports_Spare_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Spares are not present");
									appInd.writeLog("", "Spares are not present");	
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
								System.out.println("Clicked on reports_Spare_dropdown");
								Thread.sleep(4000);	
								
								By Reports_ExternalReference_dropdown = appInd.createAndGetByObject("Reports_ExternalReference_dropdown");
								WebElement reports_ExternalReference_dropdown = obrowser.findElement(Reports_ExternalReference_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ExternalReference_dropdown);
								System.out.println("Clicked on reports_ExternalReference_dropdown");
								Thread.sleep(4000);
								
								By Reports_ExternalReference_System_AddFilterButton = appInd.createAndGetByObject("Reports_ExternalReference_System_AddFilterButton");
								List<WebElement> reports_ExternalReference_System_AddFilterButton = obrowser.findElements(Reports_ExternalReference_System_AddFilterButton);
								System.out.println(reports_ExternalReference_System_AddFilterButton.size());
								
//								By Reports_ExternalReference_Controller_AddFilterButton = appInd.createAndGetByObject("Reports_ExternalReference_Controller_AddFilterButton");
//								List<WebElement> reports_ExternalReference_Controller_AddFilterButton = obrowser.findElements(Reports_ExternalReference_Controller_AddFilterButton);
//								System.out.println(reports_ExternalReference_Controller_AddFilterButton.size());
								
								if(reports_ExternalReference_System_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ExternalReference_System_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("External Reference Systems are not present");
									appInd.writeLog("", "External Reference Systems are not present");	
								}
								
//								if(reports_ExternalReference_Controller_AddFilterButton.size()>0) {
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ExternalReference_Controller_AddFilterButton.get(0));
//									strStatus = "true";
//									Thread.sleep(3000);
//								}else {
//									System.out.println("External Reference Controllers are not present");
//									appInd.writeLog("", "External Reference Controllers are not present");	
//								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ExternalReference_dropdown);
								System.out.println("Clicked on reports_ExternalReference_dropdown");
								Thread.sleep(4000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
								Thread.sleep(4000);
							}
							
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "Multiple System are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
						}
						
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						System.out.println("Clicked on reports_Next_Button");
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
							System.out.println("Clicked on first saved header");
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
							
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
							System.out.println("Clicked on first saved footer");
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_SaveAndGenerate_Button = appInd.createAndGetByObject("Reports_SaveAndGenerate_Button");
						WebElement reports_SaveAndGenerate_Button = obrowser.findElement(Reports_SaveAndGenerate_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SaveAndGenerate_Button);
						System.out.println("Clicked on save and generate report button");
						Thread.sleep(4000);
						
						long time = System.currentTimeMillis();
						System.out.println(time);
						//boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
						boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						long time1 = System.currentTimeMillis();
						totaltime=time1-time;
						System.out.println(totaltime);
						pageLoadTime_Seconds = totaltime / 1000;
						newTotalTime = pageLoadTime_Seconds +"Seconds";
						System.out.println(newTotalTime);
						
														
						if (newTotalTime!=null) {
							strStatus = "true";
							appInd.writeLog("Pass", "Total Time for page load:" + newTotalTime);
						} else {
							strStatus = "false";
							appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
						}
						
						
						
//						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//						WebElement home_Icon = obrowser.findElement(Home_Icon);
//						
//						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
//						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
//						
//						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportPackage_AlreadyExist_Ok_Button");
						boolean flag2 = appInd.ifElementsPresent(obrowser, "NoFilterAddedToSystem_Ok_Button");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package is already exist");
							strStatus = "false";
							appInd.writeLog("Fail", "Report Package is already exist");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
														
							By ReportPackage_AlreadyExist_Ok_Button = appInd.createAndGetByObject("ReportPackage_AlreadyExist_Ok_Button");
							WebElement reportPackage_AlreadyExist_Ok_Button = obrowser.findElement(ReportPackage_AlreadyExist_Ok_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportPackage_AlreadyExist_Ok_Button);
							
							System.out.println("clicked on okay button");
							Thread.sleep(3000);
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
							Thread.sleep(5000);	
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
							Thread.sleep(5000);	
						}else if(flag2==true) {
							System.out.println("No Filter Added To System");
							strStatus = "false";
							appInd.writeLog("Fail", "No Filter Added To System");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
														
							By NoFilterAddedToSystem_Ok_Button = appInd.createAndGetByObject("NoFilterAddedToSystem_Ok_Button");
							WebElement noFilterAddedToSystem_Ok_Button = obrowser.findElement(NoFilterAddedToSystem_Ok_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",noFilterAddedToSystem_Ok_Button);
							
							System.out.println("clicked on okay button");
							Thread.sleep(3000);
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
							Thread.sleep(5000);	
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
							Thread.sleep(5000);	
						}else if(flag1==true){
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("Fail", "Report Package is not saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
							}
						}else {
							
							if(flag3==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								Thread.sleep(5000);	
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
									
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
								
							}else {
								
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
						}
						
						
//						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
//						System.out.println(flag);
//						if(flag==true) {
//							System.out.println("Report Package saved successfully popup is displayed");
//							strStatus = "true";
//							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
//																					
//							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
//							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
//							
//							System.out.println("clicked on okay button");
//							Thread.sleep(5000);
//							
//							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
//							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
//							for(int j=0;j<report_PackageName_List.size();j++) {
//								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
//									strStatus = "true";
//									appInd.writeLog("pass", "Report Package saved successfully");
//									Thread.sleep(5000);	
//									break;
//								}
//								strStatus = "false";
//							}
//							if(strStatus.contains("false")) {
//								strStatus = "false";
//								appInd.writeLog("pass", "Report Package saved successfully");
//								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
//							}
//						}else {
//							strStatus = "false";
//							appInd.writeLog("pass", "Report Package saved successfully");
//							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
//						}
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithExternalSystems.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_CreateReportPackageWithExternalSystems' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_CreateReportPackageWithExternalSystems' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
				oinpuTDtMap.put("countvalue", newTotalTime);
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
				oinpuTDtMap.put("countvalue", newTotalTime);
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
				oinpuTDtMap.put("countvalue", newTotalTime);
			} else {
				oinpuTDtMap.put("result", "Skip");
				oinpuTDtMap.put("countvalue", newTotalTime);
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_CreateReportPackageWithExternalSystems' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_CreateReportHeader
 * Purpose : This method will Create a Header for the Report
 * Author : Rajashree Reviewer : Date of Creation : 28-Jan-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_CreateReportHeader() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_CreateReportHeader:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By Home_Icon = appInd.createAndGetByObject("Home_Icon");
					WebElement home_Icon = obrowser.findElement(Home_Icon);
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						String filePath = System.getProperty("user.dir")+"\\AutoIT\\";
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						
						By Add_Header_Tab = appInd.createAndGetByObject("Add_Header_Tab");
						WebElement add_Header_Tab = obrowser.findElement(Add_Header_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",add_Header_Tab);
						System.out.println("clicked on header Tab");
						Thread.sleep(4000);
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						System.out.println("Clicked on savedHeader Radio button");
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						
						List<String> addedHeaderNameList = new ArrayList<String>();

						for(int i=0;i<savedHeaderNameList.size();i++) {
							//if(savedHeaderNameList.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
							if(savedHeaderNameList.get(i).getAttribute("id").contains(headerName)) {
								appInd.writeLog("Pass", "Header Name is present");
								addedHeaderNameList.add(savedHeaderNameList.get(i).getAttribute("id"));
								strStatus += false;
								Thread.sleep(3000);
								//break;
							}

							strStatus += true;
							
						}
						if(strStatus.contains("false")) {
							
							int size = addedHeaderNameList.size();
							
							By CreateHeader_RadioButton = appInd.createAndGetByObject("CreateHeader_RadioButton");
							WebElement createHeader_RadioButton = obrowser.findElement(CreateHeader_RadioButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",createHeader_RadioButton);
							System.out.println("Clicked on createHeader Radio button");
							Thread.sleep(4000);
							
							By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
							List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
							if(headerTemplateList.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
								appInd.writeLog("pass", "clicked on header template");
								System.out.println("clicked on header template");
							}else {
								strStatus="false";
								appInd.writeLog("Fail", "Header Temaplates are not present");	
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
							}
							
							By HeaderUpload_Button = appInd.createAndGetByObject("HeaderUpload_Button");
							WebElement headerUpload_Button = obrowser.findElement(HeaderUpload_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerUpload_Button);
							System.out.println("Clicked on upload button");
							Thread.sleep(4000);
							
							Runtime.getRuntime().exec(filePath+"UploadHeaderImage.exe");
							Thread.sleep(7000);
							
							appInd.clearAndSetObject(obrowser, "HeaderName_TextBox", headerName+size);
							System.out.println("Set Header Name");
							
							By Header_TextBoxList = appInd.createAndGetByObject("Header_TextBoxList");
							List<WebElement> header_TextBoxList = obrowser.findElements(Header_TextBoxList);
									
							header_TextBoxList.get(0).clear();
							header_TextBoxList.get(0).sendKeys(headerText);
							appInd.writeLog("Pass", "Enter Text");
							System.out.println("Enter Text");
																			
							By HeaderSave_Button = appInd.createAndGetByObject("HeaderSave_Button");
							WebElement headerSave_Button = obrowser.findElement(HeaderSave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerSave_Button);
							System.out.println("clicked on header Save Button");
							Thread.sleep(4000);
							
												
//							By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//							WebElement home_Icon = obrowser.findElement(Home_Icon);
							
							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							
							boolean flag = appInd.ifElementsPresent(obrowser, "Header_AlreadyExist_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "Header_Saved_Ok_Button");
							System.out.println(flag);
							if(flag==true) {
								System.out.println("Header is already exist");
								strStatus = "false";
								appInd.writeLog("Fail", "Header is already exist");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
															
								By Header_AlreadyExist_Ok_Button = appInd.createAndGetByObject("Header_AlreadyExist_Ok_Button");
								WebElement header_AlreadyExist_Ok_Button = obrowser.findElement(Header_AlreadyExist_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",header_AlreadyExist_Ok_Button);
								
								System.out.println("clicked on okay button");
								Thread.sleep(3000);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
								Thread.sleep(5000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else if(flag1==true){
								
								//boolean flag1 = appInd.ifElementsPresent(obrowser, "Header_Saved_Ok_Button");
								System.out.println(flag1);
								if(flag1==true) {
									strStatus="true";
									appInd.writeLog("Pass", "Header Saved successfully popup is displayed");
									By Header_Saved_Ok_Button = appInd.createAndGetByObject("Header_Saved_Ok_Button");
									WebElement header_Saved_Ok_Button = obrowser.findElement(Header_Saved_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",header_Saved_Ok_Button);
									
									System.out.println("clicked on okay button");
									Thread.sleep(3000);
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Header Saved successfully popup is not displayed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
									
								}
								
//								By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
//								WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
								System.out.println("clicked on Saved header radio button");
								Thread.sleep(4000);
								
//								By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
//								List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
								
								savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);

								for(int i=0;i<savedHeaderNameList.size();i++) {
									if(savedHeaderNameList.get(i).getAttribute("id").equalsIgnoreCase(headerName+size)) {
										appInd.writeLog("Pass", "Header saved successfully");
										strStatus="true";
										Thread.sleep(3000);
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
										Thread.sleep(5000);	
										break;
									}

									strStatus="false";
									
								}
								if(strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Header is not created successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
									
									boolean flag2 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
									if(flag2==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}
									
								}
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
							}
						}else {
							By CreateHeader_RadioButton = appInd.createAndGetByObject("CreateHeader_RadioButton");
							WebElement createHeader_RadioButton = obrowser.findElement(CreateHeader_RadioButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",createHeader_RadioButton);
							System.out.println("Clicked on createHeader Radio button");
							Thread.sleep(4000);
							
							By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
							List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
							if(headerTemplateList.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
								appInd.writeLog("pass", "clicked on header template");
								System.out.println("clicked on header template");
							}else {
								strStatus="false";
								appInd.writeLog("Fail", "Header Temaplates are not present");	
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
							}
							
							By HeaderUpload_Button = appInd.createAndGetByObject("HeaderUpload_Button");
							WebElement headerUpload_Button = obrowser.findElement(HeaderUpload_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerUpload_Button);
							System.out.println("Clicked on upload button");
							Thread.sleep(4000);
							
							Runtime.getRuntime().exec(filePath+"UploadHeaderImage.exe");
							Thread.sleep(7000);
							
							appInd.clearAndSetObject(obrowser, "HeaderName_TextBox", headerName);
							System.out.println("Set Header Name");
							
							By Header_TextBoxList = appInd.createAndGetByObject("Header_TextBoxList");
							List<WebElement> header_TextBoxList = obrowser.findElements(Header_TextBoxList);
									
							header_TextBoxList.get(0).clear();
							header_TextBoxList.get(0).sendKeys(headerText);
							appInd.writeLog("Pass", "Enter Text");
							System.out.println("Enter Text");
																			
							By HeaderSave_Button = appInd.createAndGetByObject("HeaderSave_Button");
							WebElement headerSave_Button = obrowser.findElement(HeaderSave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerSave_Button);
							System.out.println("clicked on header Save Button");
							Thread.sleep(4000);
							
																			
							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							
							boolean flag = appInd.ifElementsPresent(obrowser, "Header_AlreadyExist_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "Header_Saved_Ok_Button");
							System.out.println(flag);
							if(flag==true) {
								System.out.println("Header is already exist");
								strStatus = "false";
								appInd.writeLog("Fail", "Header is already exist");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
															
								By Header_AlreadyExist_Ok_Button = appInd.createAndGetByObject("Header_AlreadyExist_Ok_Button");
								WebElement header_AlreadyExist_Ok_Button = obrowser.findElement(Header_AlreadyExist_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",header_AlreadyExist_Ok_Button);
								
								System.out.println("clicked on okay button");
								Thread.sleep(3000);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
								Thread.sleep(5000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else if(flag1==true){
								
								//boolean flag1 = appInd.ifElementsPresent(obrowser, "Header_Saved_Ok_Button");
								System.out.println(flag1);
								if(flag1==true) {
									strStatus="true";
									appInd.writeLog("Pass", "Header Saved successfully popup is displayed");
									By Header_Saved_Ok_Button = appInd.createAndGetByObject("Header_Saved_Ok_Button");
									WebElement header_Saved_Ok_Button = obrowser.findElement(Header_Saved_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",header_Saved_Ok_Button);
									
									System.out.println("clicked on okay button");
									Thread.sleep(3000);
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Header Saved successfully popup is not displayed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
									
								}
								
//								By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
//								WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
								System.out.println("clicked on Saved header radio button");
								Thread.sleep(4000);
								
//								By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
//								List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
								
								savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);

								for(int i=0;i<savedHeaderNameList.size();i++) {
									if(savedHeaderNameList.get(i).getAttribute("id").equalsIgnoreCase(headerName)) {
										appInd.writeLog("Pass", "Header saved successfully");
										strStatus="true";
										Thread.sleep(3000);
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
										Thread.sleep(5000);	
										break;
									}

									strStatus="false";
									
								}
								if(strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Header is not created successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
									
									boolean flag2 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
									if(flag2==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}
									
								}
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
							}
						}						
						
						
					}catch(Exception e) {
						   e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportHeader.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_CreateReportHeader' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_CreateReportHeader' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_CreateReportHeader' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_CreateReportFooter
 * Purpose : This method will Create a Footer for the Report
 * Author : Rajashree Reviewer : Date of Creation : 28-Jan-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_CreateReportFooter() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_CreateReportFooter:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By Home_Icon = appInd.createAndGetByObject("Home_Icon");
					WebElement home_Icon = obrowser.findElement(Home_Icon);
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						
						By Add_Footer_Tab = appInd.createAndGetByObject("Add_Footer_Tab");
						WebElement add_Footer_Tab = obrowser.findElement(Add_Footer_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",add_Footer_Tab);
						Thread.sleep(4000);
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						
						List<String> addedFooterNameList = new ArrayList<String>();
						
						for(int i=0;i<savedFooterNameList.size();i++) {
							if(savedFooterNameList.get(i).getAttribute("id").contains(footerName)) {
								appInd.writeLog("Pass", "Footer name is present");
								addedFooterNameList.add(savedFooterNameList.get(i).getAttribute("id"));
								strStatus += false;
								Thread.sleep(3000);
								
							}

							strStatus += true;
							
						}
						
						if(strStatus.contains("false")) {
							int size = addedFooterNameList.size();
							
							By CreateFooter_RadioButton = appInd.createAndGetByObject("CreateFooter_RadioButton");
							WebElement createFooter_RadioButton = obrowser.findElement(CreateFooter_RadioButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",createFooter_RadioButton);
							Thread.sleep(4000);
							
							By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
							List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
							if(footerTemplateList.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
								appInd.writeLog("pass", "clicked on footer template");
							}else {
								strStatus="false";
								appInd.writeLog("Fail", "Footer Temaplates are not present");	
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
							}
							
							appInd.clearAndSetObject(obrowser, "FooterName_TextBox", footerName+size);
							
							appInd.clearAndSetObject(obrowser, "Footer_TextBox", footerText);
							
							//appInd.clearAndSetObject(obrowser, "FooterPageNo_TextBox", oinpuTDtMap.get("Param_3"));
							
							//appInd.clearAndSetObject(obrowser, "FooterDate_TextBox", oinpuTDtMap.get("Param_4"));
																			
							By FooterSave_Button = appInd.createAndGetByObject("FooterSave_Button");
							WebElement footerSave_Button = obrowser.findElement(FooterSave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerSave_Button);
							Thread.sleep(4000);
							
//							By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//							WebElement home_Icon = obrowser.findElement(Home_Icon);
							
							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							
							boolean flag = appInd.ifElementsPresent(obrowser, "Footer_AlreadyExist_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "Footer_Saved_Ok_Button");
							System.out.println(flag);
							if(flag==true) {
								System.out.println("Header is already exist");
								strStatus = "false";
								appInd.writeLog("Fail", "Header is already exist");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
															
								By Footer_AlreadyExist_Ok_Button = appInd.createAndGetByObject("Footer_AlreadyExist_Ok_Button");
								WebElement footer_AlreadyExist_Ok_Button = obrowser.findElement(Footer_AlreadyExist_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footer_AlreadyExist_Ok_Button);
								
								System.out.println("clicked on okay button");
								Thread.sleep(3000);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
								Thread.sleep(5000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else if(flag1==true){
								
								//boolean flag1 = appInd.ifElementsPresent(obrowser, "Footer_Saved_Ok_Button");
								System.out.println(flag1);
								if(flag1==true) {
									strStatus="true";
									appInd.writeLog("Pass", "Footer Saved successfully popup is displayed");
									By Footer_Saved_Ok_Button = appInd.createAndGetByObject("Footer_Saved_Ok_Button");
									WebElement footer_Saved_Ok_Button = obrowser.findElement(Footer_Saved_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footer_Saved_Ok_Button);
									
									System.out.println("clicked on okay button");
									Thread.sleep(3000);
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Footer Saved successfully popup is not displayed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
									
								}
								
//								By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
//								WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
								Thread.sleep(4000);
								
//								By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
//								List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
								
								savedFooterNameList = obrowser.findElements(SavedFooterNameList);
								
								for(int i=0;i<savedFooterNameList.size();i++) {
									if(savedFooterNameList.get(i).getAttribute("id").equalsIgnoreCase(footerName+size)) {
										appInd.writeLog("Pass", "Footer saved successfully");
										strStatus="true";
										Thread.sleep(3000);
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
										Thread.sleep(5000);	
										break;
									}

									strStatus="false";
									
								}
								if(strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Footer is not created successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
									
									boolean flag2 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
									if(flag2==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}
								}
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
							}
							
						}else {
							By CreateFooter_RadioButton = appInd.createAndGetByObject("CreateFooter_RadioButton");
							WebElement createFooter_RadioButton = obrowser.findElement(CreateFooter_RadioButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",createFooter_RadioButton);
							Thread.sleep(4000);
							
							By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
							List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
							if(footerTemplateList.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
								appInd.writeLog("pass", "clicked on footer template");
							}else {
								strStatus="false";
								appInd.writeLog("Fail", "Footer Temaplates are not present");	
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
							}
							
							appInd.clearAndSetObject(obrowser, "FooterName_TextBox", footerName);
							
							appInd.clearAndSetObject(obrowser, "Footer_TextBox", footerText);
							
							//appInd.clearAndSetObject(obrowser, "FooterPageNo_TextBox", oinpuTDtMap.get("Param_3"));
							
							//appInd.clearAndSetObject(obrowser, "FooterDate_TextBox", oinpuTDtMap.get("Param_4"));
																			
							By FooterSave_Button = appInd.createAndGetByObject("FooterSave_Button");
							WebElement footerSave_Button = obrowser.findElement(FooterSave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerSave_Button);
							Thread.sleep(4000);
							
//							By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//							WebElement home_Icon = obrowser.findElement(Home_Icon);
							
							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							
							boolean flag = appInd.ifElementsPresent(obrowser, "Footer_AlreadyExist_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "Footer_Saved_Ok_Button");
							System.out.println(flag);
							if(flag==true) {
								System.out.println("Header is already exist");
								strStatus = "false";
								appInd.writeLog("Fail", "Header is already exist");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
															
								By Footer_AlreadyExist_Ok_Button = appInd.createAndGetByObject("Footer_AlreadyExist_Ok_Button");
								WebElement footer_AlreadyExist_Ok_Button = obrowser.findElement(Footer_AlreadyExist_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footer_AlreadyExist_Ok_Button);
								
								System.out.println("clicked on okay button");
								Thread.sleep(3000);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
								Thread.sleep(5000);	
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else if(flag1==true){
								
								//boolean flag1 = appInd.ifElementsPresent(obrowser, "Footer_Saved_Ok_Button");
								System.out.println(flag1);
								if(flag1==true) {
									strStatus="true";
									appInd.writeLog("Pass", "Footer Saved successfully popup is displayed");
									By Footer_Saved_Ok_Button = appInd.createAndGetByObject("Footer_Saved_Ok_Button");
									WebElement footer_Saved_Ok_Button = obrowser.findElement(Footer_Saved_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footer_Saved_Ok_Button);
									
									System.out.println("clicked on okay button");
									Thread.sleep(3000);
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Footer Saved successfully popup is not displayed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
									
								}
								
//								By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
//								WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
								Thread.sleep(4000);
								
//								By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
//								List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
								
								savedFooterNameList = obrowser.findElements(SavedFooterNameList);
								
								for(int i=0;i<savedFooterNameList.size();i++) {
									if(savedFooterNameList.get(i).getAttribute("id").equalsIgnoreCase(footerName)) {
										appInd.writeLog("Pass", "Footer saved successfully");
										strStatus="true";
										Thread.sleep(3000);
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
										Thread.sleep(5000);	
										break;
									}

									strStatus="false";
									
								}
								if(strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Footer is not created successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
									
									boolean flag2 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
									if(flag2==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}
								}
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
							}
						}
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportFooter.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_CreateReportFooter' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_CreateReportFooter' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_CreateReportFooter' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsCSVFile
 * Purpose : This method will Save the Report as CSV File
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsCSVFile() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsCSVFile:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						//System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "csv");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "CSV", oinpuTDtMap.get("Param_1"), oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "csv");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
						}
						
						
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsCSV.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsCSVFile' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsCSVFile' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsCSVFile' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsWordDocx
 * Purpose : This method will Save the Report as Word docx
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsWordDocx() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsWordDocx:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDocx.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "docx");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDocx.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "Word", oinpuTDtMap.get("Param_1"),oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "docx");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsWordDocx.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsWordDocx.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsWordDocx.png");
						}
						
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsWordDocx.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsWordDocx' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsWordDocx' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsWordDocx' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsExcel
 * Purpose : This method will Save the Report as Excel
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsExcel() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsExcel:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "xlsx");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "Excel", oinpuTDtMap.get("Param_1"),oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "xlsx");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
						}
						
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsExcel.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsExcel' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsExcel' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsExcel' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsPowerPoint_pptx
 * Purpose : This method will Save the Report as pptx
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsPowerPoint_pptx() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsPowerPoint_pptx:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "pptx");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "PowerPoint", oinpuTDtMap.get("Param_1"),oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "pptx");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
						}
						
						
					}catch(Exception e) {
						 //   e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAs_pptx.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsPowerPoint_pptx' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsPowerPoint_pptx' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsPowerPoint_pptx' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsPDF
 * Purpose : This method will Save the Report as PDF
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsPDF() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsPDF:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "pdf");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "PDF", oinpuTDtMap.get("Param_1"),oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "pdf");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
						}
						
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsPDF.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsPDF' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsPDF' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsPDF' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_SaveAsDataFeed
 * Purpose : This method will Save the Report as atomsvc file
 * Author : Rajashree Reviewer : Date of Creation : 05-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_SaveAsDataFeed() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_SaveAsDataFeed:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(30000);
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							//System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
						}
						boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "atomsvc");
						if(deleteFileStatus==true) {
							strStatus = "true";
							//System.out.println("Files are deleted");
							appInd.writeLog("pass", "Files are deleted");
						}else {
							//System.out.println("Files are not deleted");
							appInd.writeLog("pass", "Files are not deleted");
						}
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
						boolean flag3 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
						if(flag3==true) {
							if(report_Name_List.size()>0) {
//								By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//								List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
								for(int i=0;i<report_Name_List.size();i++) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
									    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Name_List.get(i));
									    strStatus = "true";
										appInd.writeLog("pass", "Clicked on "+oinpuTDtMap.get("Param_1"));
										Thread.sleep(20000);
										break;
									}
									strStatus = "false";
								}
								if(strStatus.contains("false")) {
									appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" Report is not Present");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
								}
							}
							
						}
						
						boolean flag = oGenericAppmethods.exportReportAs(obrowser, "Data Feed", oinpuTDtMap.get("Param_1"),oinpuTDtMap.get("Param_3"));
						if(flag==true) {
							strStatus = "true";
							boolean flag1 = appInd.isFileExist(obrowser, oinpuTDtMap.get("Param_2"), oinpuTDtMap.get("Param_1"), "atomsvc");
							if(flag1==true) {
								strStatus = "true";
								appInd.writeLog("pass", "File is downloaded successfully");
								//System.out.println("File is downloaded successfully");
								boolean flag2 = appInd.switchToParentAndChildClose();
								if(flag2==true) {
									strStatus = "true";
									appInd.writeLog("pass", "child window is closed successfully");
								}else {
									strStatus = "false";
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
									appInd.switchToParentWindow();
									appInd.writeLog("fail", "child window is not closed successfully");
								}
							}else {
								strStatus = "false";
								appInd.writeLog("fail", "File is not downloaded successfully");
								//System.out.println("File is not downloaded successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
							}
						}else {
							strStatus = "false";
							appInd.writeLog("fail", "File is not downloaded successfully");
							//System.out.println("File is not downloaded successfully");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
						}
						
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\ExportReportAsDataFeed.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_SaveAsDataFeed' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_SaveAsDataFeed' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_SaveAsDataFeed' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_GenerateNowOptionOnReportingDashborad
 * Purpose : This method will Generate option is available on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_GenerateNowOptionOnReportingDashborad() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_GenerateNowOptionOnReportingDashborad:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());
						
						if(system_elements.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "System is not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}
						
						By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
						WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
						List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
						System.out.println(reports_ChangeQuries_AddFilterButton.size());
						
						if(reports_ChangeQuries_AddFilterButton.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							System.out.println("Change Queries are not present");
							appInd.writeLog("", "Change Queries are not present");	
							
							By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
							WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
							
							By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
							List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
							System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
							
							if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
								strStatus = "true";
								Thread.sleep(3000);
							}else {
								System.out.println("Engineering Anomaly are not present");
								appInd.writeLog("", "Engineering Anomaly are not present");	
								
								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
								
								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
								System.out.println(reports_ParameterQueries_AddFilterButton.size());
								
								if(reports_ParameterQueries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Parameter Queries are not present");
									appInd.writeLog("", "Parameter Queries are not present");	
									
									By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
									WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
									
									By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
									List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
									System.out.println(reports_Spare_AddFilterButton.size());
									
									if(reports_Spare_AddFilterButton.size()>0) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
										strStatus = "true";
										Thread.sleep(3000);
									}else {
										System.out.println("Spares are not present");
										appInd.writeLog("", "Spares are not present");	
									}
									
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
							}
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);
						
//						By Reports_SaveAndGenerate_Button = appInd.createAndGetByObject("Reports_SaveAndGenerate_Button");
//						WebElement reports_SaveAndGenerate_Button = obrowser.findElement(Reports_SaveAndGenerate_Button);
//						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SaveAndGenerate_Button);
//						System.out.println("Clicked on save and generate report button");
//						Thread.sleep(4000);
//						
//						boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
							}
							boolean eleStatus = appInd.isElementsPresent(obrowser, "//a[contains(text(),'"+oinpuTDtMap.get("Param_1")+"')]/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::a[@id='start-collection']");
							if(eleStatus==true) {
								strStatus = "true";
								System.out.println("Generate Now Button is available");
								appInd.writeLog("pass", "Generate Now Button is available");
							}else {
								strStatus = "false";
								System.out.println("Generate Now Button is not available");
								appInd.writeLog("fail", "Generate Now Button is not available");
							}
						}else {
							
							if(flag1==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
								

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
							
						}
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_GenerateNowOptionOnReportingDashborad' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_GenerateNowOptionOnReportingDashborad' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_GenerateNowOptionOnReportingDashborad' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_6_NoOfReportsPerPackage
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_6_NoOfReportsPerPackage() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_6_NoOfReportsPerPackage:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);

						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);

						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));

						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);

						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());

						if(system_elements.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "System is not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}

						By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
						WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);

						By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
						List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
						System.out.println(reports_ChangeQuries_AddFilterButton.size());

						if(reports_ChangeQuries_AddFilterButton.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							System.out.println("Change Queries are not present");
							appInd.writeLog("", "Change Queries are not present");	

							By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
							WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);

							By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
							List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
							System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());

							if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
								strStatus = "true";
								Thread.sleep(3000);
							}else {
								System.out.println("Engineering Anomaly are not present");
								appInd.writeLog("", "Engineering Anomaly are not present");	

								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);

								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
								System.out.println(reports_ParameterQueries_AddFilterButton.size());

								if(reports_ParameterQueries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Parameter Queries are not present");
									appInd.writeLog("", "Parameter Queries are not present");	

									By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
									WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);

									By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
									List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
									System.out.println(reports_Spare_AddFilterButton.size());

									if(reports_Spare_AddFilterButton.size()>0) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
										strStatus = "true";
										Thread.sleep(3000);
									}else {
										System.out.println("Spares are not present");
										appInd.writeLog("", "Spares are not present");	
									}

									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
							}

							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
						}

						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);

						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);

						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}

						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);

						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}

						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);

						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}

						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);

						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
						}

						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);

						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	

						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
						WebElement home_Icon = obrowser.findElement(Home_Icon);

						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");

						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);

//						By Reports_SaveAndGenerate_Button = appInd.createAndGetByObject("Reports_SaveAndGenerate_Button");
//						WebElement reports_SaveAndGenerate_Button = obrowser.findElement(Reports_SaveAndGenerate_Button);
//						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SaveAndGenerate_Button);
//						System.out.println("Clicked on save and generate report button");
//						Thread.sleep(4000);
//						
//						boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);

						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");

						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");

							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);

							System.out.println("clicked on okay button");
							Thread.sleep(5000);

							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
							}
							boolean eleStatus = appInd.isElementsPresent(obrowser, "//a[contains(text(),'"+oinpuTDtMap.get("Param_1")+"')]/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::a[@id='start-collection']");						
							if(eleStatus==true) {
								strStatus = "true";
								System.out.println("Generate Now Button is available");
								appInd.writeLog("pass", "Generate Now Button is available");
								for(int i=0;i<6;i++) {
									WebElement element = obrowser.findElement(By.xpath("//a[contains(text(),'"+oinpuTDtMap.get("Param_1")+"')]/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::a[@id='start-collection']"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",element);
									boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
									System.out.println("Clicked on generate now button for "+oinpuTDtMap.get("Param_1")+" Package");
									boolean flag2 = appInd.ifElementsPresent(obrowser, "Report_Generated_Ok_Button");
									boolean flag3 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
									if(flag2==true) {
										By Report_Generated_Ok_Button = appInd.createAndGetByObject("Report_Generated_Ok_Button");
										WebElement report_Generated_Ok_Button = obrowser.findElement(Report_Generated_Ok_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Generated_Ok_Button);
										Thread.sleep(2000);
										// result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
										System.out.println("Clicked on report_Generated_Ok_Button");
										strStatus = "true";
									}else if(flag3==true) {
										strStatus = "false";
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\6_NoOfReportsPerPackage.png");
										By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
										WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
										System.out.println("Clicked on popup_Ok_Button");
									}else {
										strStatus = "false";
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\6_NoOfReportsPerPackage.png");
									}

								}
								List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
								boolean flag5 = appInd.ifElementsPresent(obrowser, "Report_Name_List");
								if(flag5==true) {
									report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
									int noOfReports = 0;
									for(int i=0;i<report_Name_List.size();i++) {
										if(report_Name_List.get(i).getAttribute("id").contains(oinpuTDtMap.get("Param_1"))) {
											noOfReports++;
											System.out.println("noOfReports : "+noOfReports);
										}
									}
									if(noOfReports>=6) {
										strStatus = "true";
									}else {
										strStatus = "false";
										WebElement element_ViewAll = obrowser.findElement(By.xpath("//a[contains(text(),'"+oinpuTDtMap.get("Param_1")+"')]/ancestor::div[@class='list']/following-sibling::div[@class='view pull-right']/descendant::a[contains(@ng-click,'viewallclick')]"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",element_ViewAll);
										System.out.println("Clicked on View All button for "+oinpuTDtMap.get("Param_1")+" Package");
										Thread.sleep(4000);
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\6_NoOfReportsPerPackage.png");
									}

								}
							}else {
								strStatus = "false";
								System.out.println("Generate Now Button is not available");
								appInd.writeLog("fail", "Generate Now Button is not available");
							}

						}else {

							
							if(flag1==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\GenerateNowOptionOnReportingDashborad.png");
								

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
							
						
						}
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\6_NoOfReportsPerPackage.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_6_NoOfReportsPerPackage' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_6_NoOfReportsPerPackage' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_6_NoOfReportsPerPackage' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}

/********************************
 * Method Name : TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
//						By Report_Name_List = appInd.createAndGetByObject("Report_Name_List");
//						List<WebElement> report_Name_List = obrowser.findElements(Report_Name_List);
						
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard.png");
						}
						
						List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::li"));
						System.out.println(report_Name_List.size());
						int i=0;
						//while(i<report_Name_List.size() && report_Name_List.get(i).getText().contains(oinpuTDtMap.get("Param_1"))) {
						while(i<report_Name_List.size()) {
							if(report_Name_List.get(i).isDisplayed()) {
								System.out.println(report_Name_List.get(i).getText());
								System.out.println("Element is Displayed");

							}else {
								System.out.println("=======");
								break;
							}
							System.out.println(i);
							i++;

						}
						if(i<=2) {
							System.out.println("pass");
							strStatus = "true";
						}else {
							System.out.println("fail");
							strStatus = "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard.png");
						}
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_OnlyTwoLatestReportsAvailableOnTheReportingPackageDashboard' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_NextScheduleOptionOnReportingDashborad
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_NextScheduleOptionOnReportingDashborad() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_NextScheduleOptionOnReportingDashborad:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################

					//click on the Reports from the menu 

					By Home_Icon = appInd.createAndGetByObject("Home_Icon");
					WebElement home_Icon = obrowser.findElement(Home_Icon);

					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					Thread.sleep(10000);
					
					boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);

					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
					
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
					     result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);
						
						result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());
						
						if(system_elements.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "System is not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
						WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
						List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
						System.out.println(reports_ChangeQuries_AddFilterButton.size());
						
						if(reports_ChangeQuries_AddFilterButton.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							System.out.println("Change Queries are not present");
							appInd.writeLog("", "Change Queries are not present");	
							
							By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
							WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
							
							By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
							List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
							System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
							
							if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
								strStatus = "true";
								Thread.sleep(3000);
							}else {
								System.out.println("Engineering Anomaly are not present");
								appInd.writeLog("", "Engineering Anomaly are not present");	
								
								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
								
								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
								System.out.println(reports_ParameterQueries_AddFilterButton.size());
								
								if(reports_ParameterQueries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Parameter Queries are not present");
									appInd.writeLog("", "Parameter Queries are not present");	
									
									By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
									WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
									
									By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
									List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
									System.out.println(reports_Spare_AddFilterButton.size());
									
									if(reports_Spare_AddFilterButton.size()>0) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
										strStatus = "true";
										Thread.sleep(3000);
									}else {
										System.out.println("Spares are not present");
										appInd.writeLog("", "Spares are not present");	
									}
									
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
							}
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
//						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							//Thread.sleep(15000);
							result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
							}
						}else {
							
							if(flag1==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
							
						}
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
					  }

					try {
						boolean flag = oGenericAppmethods.scheduleReport(obrowser, oinpuTDtMap.get("Param_1"));
						if(flag==true) {
							String today = appInd.fetchTodayDate();
							System.out.println(today);
							String[]  strArr_2 = today.split(" ");
							System.out.println("Date="+strArr_2[0]);
							System.out.println("Time="+strArr_2[1]);
							String[]  strArr = today.split("/");
							System.out.println("Month="+strArr[0]);
							System.out.println("Date="+strArr[1]);
							System.out.println(strArr[2]);
							String[]  strArrNew = strArr[2] .split(" ");
							System.out.println("==========");
							System.out.println("Year="+strArrNew[0]);
							System.out.println(strArrNew[1]);
							System.out.println("==========");
							String[]  strArrTime = strArrNew[1] .split(":");
							System.out.println("Hour="+strArrTime[0]);
							System.out.println("Minute="+strArrTime[1]);
							System.out.println("Second="+strArrTime[2]);
							
							int hour = Integer.parseInt(strArrTime[0]);
							System.out.println(hour);
							int newHour = hour+1;
							System.out.println(newHour);
//							int newHour2 = newHour+12;
//							System.out.println(newHour2);
							String scheduledHour = Integer.toString(newHour);
							//String newScheduledHour = Integer.toString(newHour2);
							if(strArrTime[0].equals("23")) {
								scheduledHour = "00";
								int Date = Integer.parseInt(strArr[1]);
								System.out.println(Date);
								int NewDate = Date+1;
								System.out.println(NewDate);
								strArr[1] = Integer.toString(NewDate);
							}
							
							if(strArr[1].equals("01")) {
								strArr[1] = "1";
							}
							if(strArr[1].equals("02")) {
								strArr[1] = "2";
							}
							if(strArr[1].equals("03")) {
								strArr[1] = "3";
							}
							if(strArr[1].equals("04")) {
								strArr[1] = "4";
							}
							if(strArr[1].equals("05")) {
								strArr[1] = "5";
							}
							if(strArr[1].equals("06")) {
								strArr[1] = "6";
							}
							if(strArr[1].equals("07")) {
								strArr[1] = "7";
							}
							if(strArr[1].equals("08")) {
								strArr[1] = "8";
							}
							if(strArr[1].equals("09")) {
								strArr[1] = "9";
							}
							
							if(strArr[0].equals("01")) {
								strArr[0] = "1";
							}
							if(strArr[0].equals("02")) {
								strArr[0] = "2";
							}
							if(strArr[0].equals("03")) {
								strArr[0] = "3";
							}
							if(strArr[0].equals("04")) {
								strArr[0] = "4";
							}
							if(strArr[0].equals("05")) {
								strArr[0] = "5";
							}
							if(strArr[0].equals("06")) {
								strArr[0] = "6";
							}
							if(strArr[0].equals("07")) {
								strArr[0] = "7";
							}
							if(strArr[0].equals("08")) {
								strArr[0] = "8";
							}
							if(strArr[0].equals("09")) {
								strArr[0] = "9";
							}
							
							strArr_2[0] = strArr[0]+"/"+strArr[1]+"/"+strArrNew[0];
							System.out.println(strArr_2[0]);
							System.out.println("*******************************************");
							System.out.println(strArr[1]);


							By Reports_Date_List = appInd.createAndGetByObject("Reports_Date_List");
							List<WebElement> reports_Date_List = obrowser.findElements(Reports_Date_List);
							

							for(int i=0;i<reports_Date_List.size();i++) {
								System.out.println(reports_Date_List.get(i).getText());
								if(reports_Date_List.get(i).getText().equals(strArr[1])) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Date_List.get(i));
									System.out.println("Clicked on Date");
									strStatus="true";
									break;
								}
								strStatus="false";

							}

							appInd.clearAndSetObject(obrowser, "Schedule_Hour_EditBox", scheduledHour);

							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");

							By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
							WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
							Thread.sleep(4000);

							boolean flag2 = appInd.ifElementsPresent(obrowser, "Report_Package_Updated_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "StartDateTimeCannotBeLesserThanCurrentDateTime_Ok_Button ");
							boolean flag5 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");

							System.out.println(flag2);
							if(flag2==true) {
								System.out.println("Report Package updated successfully popup is displayed");
								//strStatus = "true";
								appInd.writeLog("pass", "Report Package updated successfully popup is displayed");

								By Report_Package_Updated_Ok_Button = appInd.createAndGetByObject("Report_Package_Updated_Ok_Button");
								WebElement report_Package_Updated_Ok_Button = obrowser.findElement(Report_Package_Updated_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Package_Updated_Ok_Button);

								System.out.println("clicked on okay button");
								Thread.sleep(5000);
								//Thread.sleep(15000);
								result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);

								boolean eleStatus = appInd.isElementsPresent(obrowser, "//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='left-side']/span[@class='date ng-binding']");
								if(eleStatus==true) {
									WebElement element = obrowser.findElement(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='left-side']/span[@class='date ng-binding']"));
									String scheduleTime = element.getText();
									String arr[] = scheduleTime.split(" ");
									System.out.println(arr[0]);
									System.out.println(arr[1]);
									System.out.println(arr[2]);

									String arr2[] = arr[1].split(":");

									System.out.println(arr2[0]);
									System.out.println(arr2[1]);
									System.out.println(arr2[2]);
									
									if(newHour>12) {
										newHour=newHour-12;
										scheduledHour = Integer.toString(newHour);
									}
									
									if(strArr_2[0].equalsIgnoreCase(arr[0]) && arr2[0].equalsIgnoreCase(scheduledHour)) {
										strStatus = "true";
										System.out.println("pass");
									}else {
										strStatus = "false";
										System.out.println("fail");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									}
								}

							}else {
								if(flag5==true) {
									strStatus = "false";
									appInd.writeLog("pass", "Report Package saved successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									
									By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
									WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
									
									if(flag4==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}

									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
									Thread.sleep(5000);	
								}else {
									strStatus = "false";
									appInd.writeLog("pass", "Report Package saved successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									

									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
									Thread.sleep(5000);	
								}
								
								
							
							}
						}
					}catch(Exception e) {
						//e.printStackTrace();
						strStatus += false;
						appInd.writeLog("Fail", "NextScheduleOptionOnReportingDashborad is failed");	
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
					}

					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_NextScheduleOptionOnReportingDashborad' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_NextScheduleOptionOnReportingDashborad' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
		appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_NextScheduleOptionOnReportingDashborad' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By Home_Icon = appInd.createAndGetByObject("Home_Icon");
					WebElement home_Icon = obrowser.findElement(Home_Icon);
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					Thread.sleep(10000);
					
					boolean result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
					try {
						System.out.println("Reports");	
						
						By Create_Report_package_Button = appInd.createAndGetByObject("Create_Report_package_Button");
						WebElement create_Report_package_Button = obrowser.findElement(Create_Report_package_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",create_Report_package_Button);
						Thread.sleep(4000);
						result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						
						By Configure_Report_Tab = appInd.createAndGetByObject("Configure_Report_Tab");
						WebElement configure_Report_Tab = obrowser.findElement(Configure_Report_Tab);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",configure_Report_Tab);
						Thread.sleep(4000);
						result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
						
						appInd.clearAndSetObject(obrowser, "Package_Name_TextBox", oinpuTDtMap.get("Param_1"));
						
						By Reports_SelectSystem_dropdown = appInd.createAndGetByObject("Reports_SelectSystem_dropdown");
						WebElement reports_SelectSystem_dropdown = obrowser.findElement(Reports_SelectSystem_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_SelectSystem_dropdown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Reports_SystemNameList");
						List<WebElement> system_elements = obrowser.findElements(systems);
						System.out.println(system_elements.size());
						
						if(system_elements.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							strStatus = "false";
							appInd.writeLog("Fail", "System is not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By Reports_ChangeQuries_dropdown = appInd.createAndGetByObject("Reports_ChangeQuries_dropdown");
						WebElement reports_ChangeQuries_dropdown = obrowser.findElement(Reports_ChangeQuries_dropdown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_ChangeQuries_AddFilterButton = appInd.createAndGetByObject("Reports_ChangeQuries_AddFilterButton");
						List<WebElement> reports_ChangeQuries_AddFilterButton = obrowser.findElements(Reports_ChangeQuries_AddFilterButton);
						System.out.println(reports_ChangeQuries_AddFilterButton.size());
						
						if(reports_ChangeQuries_AddFilterButton.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_AddFilterButton.get(0));
							strStatus = "true";
							Thread.sleep(3000);
						}else {
							System.out.println("Change Queries are not present");
							appInd.writeLog("", "Change Queries are not present");	
							
							By Reports_EngineeringAnomaly_dropdown = appInd.createAndGetByObject("Reports_EngineeringAnomaly_dropdown");
							WebElement reports_EngineeringAnomaly_dropdown = obrowser.findElement(Reports_EngineeringAnomaly_dropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
							
							By Reports_EngineeringAnomaly_AddFilterButton = appInd.createAndGetByObject("Reports_EngineeringAnomaly_AddFilterButton");
							List<WebElement> reports_EngineeringAnomaly_AddFilterButton = obrowser.findElements(Reports_EngineeringAnomaly_AddFilterButton);
							System.out.println(reports_EngineeringAnomaly_AddFilterButton.size());
							
							if(reports_EngineeringAnomaly_AddFilterButton.size()>0) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_AddFilterButton.get(0));
								strStatus = "true";
								Thread.sleep(3000);
							}else {
								System.out.println("Engineering Anomaly are not present");
								appInd.writeLog("", "Engineering Anomaly are not present");	
								
								By Reports_ParameterQueries_dropdown = appInd.createAndGetByObject("Reports_ParameterQueries_dropdown");
								WebElement reports_ParameterQueries_dropdown = obrowser.findElement(Reports_ParameterQueries_dropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
								
								By Reports_ParameterQueries_AddFilterButton = appInd.createAndGetByObject("Reports_ParameterQueries_AddFilterButton");
								List<WebElement> reports_ParameterQueries_AddFilterButton = obrowser.findElements(Reports_ParameterQueries_AddFilterButton);
								System.out.println(reports_ParameterQueries_AddFilterButton.size());
								
								if(reports_ParameterQueries_AddFilterButton.size()>0) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_AddFilterButton.get(0));
									strStatus = "true";
									Thread.sleep(3000);
								}else {
									System.out.println("Parameter Queries are not present");
									appInd.writeLog("", "Parameter Queries are not present");	
									
									By Reports_Spare_dropdown = appInd.createAndGetByObject("Reports_Spare_dropdown");
									WebElement reports_Spare_dropdown = obrowser.findElement(Reports_Spare_dropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
									
									By Reports_Spare_AddFilterButton = appInd.createAndGetByObject("Reports_Spare_AddFilterButton");
									List<WebElement> reports_Spare_AddFilterButton = obrowser.findElements(Reports_Spare_AddFilterButton);
									System.out.println(reports_Spare_AddFilterButton.size());
									
									if(reports_Spare_AddFilterButton.size()>0) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_AddFilterButton.get(0));
										strStatus = "true";
										Thread.sleep(3000);
									}else {
										System.out.println("Spares are not present");
										appInd.writeLog("", "Spares are not present");	
									}
									
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Spare_dropdown);
									Thread.sleep(4000);
								}
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ParameterQueries_dropdown);
								Thread.sleep(4000);
							}
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_EngineeringAnomaly_dropdown);
							Thread.sleep(4000);
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_ChangeQuries_dropdown);
						Thread.sleep(4000);
						
						By Reports_Next_Button = appInd.createAndGetByObject("Reports_Next_Button");
						WebElement reports_Next_Button = obrowser.findElement(Reports_Next_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						Thread.sleep(4000);
						
						By HeaderTemplateList = appInd.createAndGetByObject("HeaderTemplateList");
						List<WebElement> headerTemplateList = obrowser.findElements(HeaderTemplateList);
						if(headerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",headerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Header Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedHeader_RadioButton = appInd.createAndGetByObject("SavedHeader_RadioButton");
						WebElement savedHeader_RadioButton = obrowser.findElement(SavedHeader_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeader_RadioButton);
						Thread.sleep(4000);
						
						By SavedHeaderNameList = appInd.createAndGetByObject("SavedHeaderNameList");
						List<WebElement> savedHeaderNameList = obrowser.findElements(SavedHeaderNameList);
						if(savedHeaderNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedHeaderNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved header names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Next_Button);
						
						By FooterTemplateList = appInd.createAndGetByObject("FooterTemplateList");
						List<WebElement> footerTemplateList = obrowser.findElements(FooterTemplateList);
						if(footerTemplateList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",footerTemplateList.get(0));
						}else {
							appInd.writeLog("Fail", "Footer Temaplates are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By SavedFooter_RadioButton = appInd.createAndGetByObject("SavedFooter_RadioButton");
						WebElement savedFooter_RadioButton = obrowser.findElement(SavedFooter_RadioButton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooter_RadioButton);
						Thread.sleep(4000);
						
						By SavedFooterNameList = appInd.createAndGetByObject("SavedFooterNameList");
						List<WebElement> savedFooterNameList = obrowser.findElements(SavedFooterNameList);
						if(savedFooterNameList.size()>0) {
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",savedFooterNameList.get(0));
						}else {
							appInd.writeLog("Fail", "Saved footer names are not present");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
						}
						
						By Schedule_CheckBox = appInd.createAndGetByObject("Schedule_CheckBox");
						WebElement schedule_CheckBox = obrowser.findElement(Schedule_CheckBox);
						
						if(schedule_CheckBox.getAttribute("class").contains("unselect")) {
							System.out.println("Checkbox is not selected");
						}else {
							System.out.println("CheckBox is selected");
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",schedule_CheckBox);
							System.out.println("unselect schedule check box");
						}	
						
//						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//						WebElement home_Icon = obrowser.findElement(Home_Icon);
						
						By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
						WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
						boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");
						
						By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
						WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
						Thread.sleep(4000);
						
						boolean flag = appInd.ifElementsPresent(obrowser, "ReportSaved_OkayButton");
						boolean flag1 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");
						
						System.out.println(flag);
						if(flag==true) {
							System.out.println("Report Package saved successfully popup is displayed");
							strStatus = "true";
							appInd.writeLog("pass", "Report Package saved successfully popup is displayed");
																					
							By ReportSaved_OkayButton = appInd.createAndGetByObject("ReportSaved_OkayButton");
							WebElement reportSaved_OkayButton = obrowser.findElement(ReportSaved_OkayButton);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reportSaved_OkayButton);
							
							System.out.println("clicked on okay button");
							Thread.sleep(5000);
							result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);
							
							By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
							List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							for(int j=0;j<report_PackageName_List.size();j++) {
								if(report_PackageName_List.get(j).getText().trim().equalsIgnoreCase(oinpuTDtMap.get("Param_1").trim())) {
									strStatus = "true";
									appInd.writeLog("pass", "Report Package saved successfully");
									Thread.sleep(5000);	
									break;
								}
								strStatus = "false";
							}
							if(strStatus.contains("false")) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
							}
						}else {
							
							if(flag1==true) {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								
								By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
								WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
								
								if(flag4==true) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
									Thread.sleep(5000);	
								}

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}else {
								strStatus = "false";
								appInd.writeLog("pass", "Report Package saved successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
								

								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
								Thread.sleep(5000);	
							}
							
							
						}
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\CreateReportPackageWithSingSystemAndSingleQuery.png");
					  }			  
					try {
						
						boolean flag = oGenericAppmethods.scheduleReport(obrowser, oinpuTDtMap.get("Param_1"));
						if(flag==true) {
							String today = appInd.fetchTodayDate();
							System.out.println(today);
							String[]  strArr_2 = today.split(" ");
							System.out.println("Date="+strArr_2[0]);
							System.out.println("Time="+strArr_2[1]);
							String[]  strArr = today.split("/");
							System.out.println("Month="+strArr[0]);
							System.out.println("Date="+strArr[1]);
							System.out.println(strArr[2]);
							String[]  strArrNew = strArr[2] .split(" ");
							System.out.println("==========");
							System.out.println("Year="+strArrNew[0]);
							System.out.println(strArrNew[1]);
							System.out.println("==========");
							String[]  strArrTime = strArrNew[1] .split(":");
							System.out.println("Hour="+strArrTime[0]);
							System.out.println("Minute="+strArrTime[1]);
							System.out.println("Second="+strArrTime[2]);
							
							int hour = Integer.parseInt(strArrTime[0]);
//							System.out.println(hour);
//							int newHour = hour+1;
//							System.out.println(newHour);
//							int newHour2 = newHour+12;
//							System.out.println(newHour2);
							String scheduledHour = Integer.toString(hour);
							//String newScheduledHour = Integer.toString(newHour2);
							
							int minute = Integer.parseInt(strArrTime[1]);
							System.out.println(minute);
							int newMinute = minute+2;
							System.out.println(newMinute);
							String scheduledMinute = Integer.toString(newMinute);
							
							if(strArrTime[0].equals("23")) {
								scheduledHour = "00";
								int Date = Integer.parseInt(strArr[1]);
								System.out.println(Date);
								int NewDate = Date+1;
								System.out.println(NewDate);
								strArr[1] = Integer.toString(NewDate);
							}
							
							if(strArr[1].equals("01")) {
								strArr[1] = "1";
							}
							if(strArr[1].equals("02")) {
								strArr[1] = "2";
							}
							if(strArr[1].equals("03")) {
								strArr[1] = "3";
							}
							if(strArr[1].equals("04")) {
								strArr[1] = "4";
							}
							if(strArr[1].equals("05")) {
								strArr[1] = "5";
							}
							if(strArr[1].equals("06")) {
								strArr[1] = "6";
							}
							if(strArr[1].equals("07")) {
								strArr[1] = "7";
							}
							if(strArr[1].equals("08")) {
								strArr[1] = "8";
							}
							if(strArr[1].equals("09")) {
								strArr[1] = "9";
							}
							
							if(strArr[0].equals("01")) {
								strArr[0] = "1";
							}
							if(strArr[0].equals("02")) {
								strArr[0] = "2";
							}
							if(strArr[0].equals("03")) {
								strArr[0] = "3";
							}
							if(strArr[0].equals("04")) {
								strArr[0] = "4";
							}
							if(strArr[0].equals("05")) {
								strArr[0] = "5";
							}
							if(strArr[0].equals("06")) {
								strArr[0] = "6";
							}
							if(strArr[0].equals("07")) {
								strArr[0] = "7";
							}
							if(strArr[0].equals("08")) {
								strArr[0] = "8";
							}
							if(strArr[0].equals("09")) {
								strArr[0] = "9";
							}
							
							strArr_2[0] = strArr[0]+"/"+strArr[1]+"/"+strArrNew[0];
							System.out.println(strArr_2[0]);
							System.out.println("*******************************************");
							System.out.println(strArr[1]);


							By Reports_Date_List = appInd.createAndGetByObject("Reports_Date_List");
							List<WebElement> reports_Date_List = obrowser.findElements(Reports_Date_List);
							

							for(int i=0;i<reports_Date_List.size();i++) {
								System.out.println(reports_Date_List.get(i).getText());
								if(reports_Date_List.get(i).getText().equals(strArr[1])) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Date_List.get(i));
									System.out.println("Clicked on Date");
									strStatus="true";
									break;
								}
								strStatus="false";

							}

							appInd.clearAndSetObject(obrowser, "Schedule_Hour_EditBox", scheduledHour);
							appInd.clearAndSetObject(obrowser, "Schedule_Minute_EditBox", scheduledMinute);

							By Reports_CancelButton = appInd.createAndGetByObject("Reports_CancelButton");
							WebElement reports_CancelButton = obrowser.findElement(Reports_CancelButton);
							boolean flag4 = appInd.ifElementsPresent(obrowser, "Reports_CancelButton");

							By Reports_Save_Button = appInd.createAndGetByObject("Reports_Save_Button");
							WebElement reports_Save_Button = obrowser.findElement(Reports_Save_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_Save_Button);
							Thread.sleep(4000);

							boolean flag2 = appInd.ifElementsPresent(obrowser, "Report_Package_Updated_Ok_Button");
							boolean flag1 = appInd.ifElementsPresent(obrowser, "StartDateTimeCannotBeLesserThanCurrentDateTime_Ok_Button ");
							boolean flag5 = appInd.ifElementsPresent(obrowser, "Popup_Ok_Button");

							System.out.println(flag2);
							if(flag2==true) {
								System.out.println("Report Package updated successfully popup is displayed");
								//strStatus = "true";
								appInd.writeLog("pass", "Report Package updated successfully popup is displayed");

								By Report_Package_Updated_Ok_Button = appInd.createAndGetByObject("Report_Package_Updated_Ok_Button");
								WebElement report_Package_Updated_Ok_Button = obrowser.findElement(Report_Package_Updated_Ok_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Package_Updated_Ok_Button);

								System.out.println("clicked on okay button");
								Thread.sleep(5000);
								result=	appInd.waitFor(obrowser, "PageLoading_Icon", "invisible", "",600);

								boolean eleStatus = appInd.isElementsPresent(obrowser, "//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='left-side']/span[@class='date ng-binding']");
								if(eleStatus==true) {
									WebElement element = obrowser.findElement(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='left-side']/span[@class='date ng-binding']"));
									String scheduleTime = element.getText();
									String arr[] = scheduleTime.split(" ");
									System.out.println("DateOnDashBoard="+arr[0]);
									System.out.println("TimeOnDashBoard="+arr[1]);
									System.out.println(arr[2]);

									String arr2[] = arr[1].split(":");

									System.out.println("Hour="+arr2[0]);
									System.out.println("Minute="+arr2[1]);
									System.out.println("Second="+arr2[2]);
									
									if(hour>12) {
										hour=hour-12;
										scheduledHour = Integer.toString(hour);
									}
									
									if(strArr_2[0].equalsIgnoreCase(arr[0]) && arr2[0].equalsIgnoreCase(scheduledHour) && arr2[1].equalsIgnoreCase(scheduledMinute)) {
										
										Thread.sleep(120000);
										
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
										Thread.sleep(5000);	
										obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
										Thread.sleep(1000);
										obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
										Thread.sleep(10000);
										
										//Thread.sleep(10000);
										//List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
										List<WebElement> report_Name_List = obrowser.findElements(By.xpath("//a[@id='"+oinpuTDtMap.get("Param_1")+"']/ancestor::div[@class='title']/following-sibling::div[@class='reports']/descendant::div[@class='list']/descendant::a"));
										if(report_Name_List.size()>0) {
											String str = report_Name_List.get(0).getText();
											String[] reportStr = str.split(" ");
											System.out.println("RepoPackName="+reportStr[0]);
											System.out.println("RepoMonth="+reportStr[1]);
											System.out.println("RepoDate="+reportStr[2]);
											System.out.println("RepoYear="+reportStr[3]);
											System.out.println("RepohourMin="+reportStr[4]);
											System.out.println("PM="+reportStr[5]);
											String[] reportStr2 = reportStr[4].split(":");
											System.out.println("hour="+reportStr2[0]);
											System.out.println("Minute="+reportStr2[1]);
											if(scheduledMinute.equalsIgnoreCase(reportStr2[1])) {
												strStatus = "true";
												System.out.println("pass");
											}else {
												strStatus = "false";
												System.out.println("fail");
											}
										}
										
										
									}else {
										strStatus = "false";
										System.out.println("fail");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									}
								}

							}else {
								if(flag5==true) {
									strStatus = "false";
									appInd.writeLog("pass", "Report Package saved successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									
									By Popup_Ok_Button = appInd.createAndGetByObject("Popup_Ok_Button");
									WebElement popup_Ok_Button = obrowser.findElement(Popup_Ok_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",popup_Ok_Button);
									
									if(flag4==true) {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",reports_CancelButton);
										Thread.sleep(5000);	
									}

									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
									Thread.sleep(5000);	
								}else {
									strStatus = "false";
									appInd.writeLog("pass", "Report Package saved successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\NextScheduleOptionOnReportingDashborad.png");
									

									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
									Thread.sleep(5000);	
								}
								
								
							
							}
						}
					
						
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\VerifyReportIsGettingGeneratedInScheduleTime.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_VerifyReportIsGettingGeneratedInScheduleTime' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_DeleteSingleReport
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_DeleteSingleReport() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_DeleteSingleReport:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						boolean flag = oGenericAppmethods.deleteReports(obrowser, oinpuTDtMap.get("Param_1"), oinpuTDtMap.get("Param_2"),"Single");
						if(flag==true && oinpuTDtMap.get("Param_2").equalsIgnoreCase("1")) {
							strStatus = "true";
							System.err.println("Reports are deleted successfully");
							appInd.writeLog("pass", "Reports are deleted successfully");	
						}else {
							strStatus = "false";
							System.err.println("Reports are not deleted successfully");
							appInd.writeLog("Fail", "Reports are not deleted successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteSingleReport.png");
						}
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "Report Package is not created successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteSingleReport.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_DeleteSingleReport' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_DeleteSingleReport' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_DeleteSingleReport' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_DeleteMultipleReport
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_DeleteMultipleReport() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_DeleteMultipleReport:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						boolean flag = oGenericAppmethods.deleteReports(obrowser, oinpuTDtMap.get("Param_1"), oinpuTDtMap.get("Param_2"),"Multiple");
						int i = Integer.parseInt(oinpuTDtMap.get("Param_2"));
						if(flag==true && i>=2) {
							strStatus = "true";
							System.err.println("Reports are deleted successfully");
							appInd.writeLog("pass", "Reports are deleted successfully");	
						}else {
							strStatus = "false";
							System.err.println("Reports are not deleted successfully");
							appInd.writeLog("Fail", "Reports are not deleted successfully");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteMultipleReport.png");
						}
					}catch(Exception e) {
						   // e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "TC_Reports_DeleteMultipleReport is failed");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteMultipleReport.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_DeleteMultipleReport' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_DeleteMultipleReport' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_DeleteMultipleReport' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
/********************************
 * Method Name : TC_Reports_DeleteReportPackage
 * Purpose : This method will Generate 6 no of reports on the package
 * Author : Rajashree Reviewer : Date of Creation : 06-Feb-2019
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap> TC_Reports_DeleteReportPackage() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String strProjectName = null;
	try {
		System.out.println("**************Reports***********");
		appInd.writeLog("#", "****TC_Reports_DeleteReportPackage:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		System.out.println("oinputMap.tostring::" +oinputMap.toString());
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));
			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes")) {
					// ########################################################################
					
					//click on the Reports from the menu 
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					//click on the Reports from the menu 
					//strStatus += oGenericAppmethods.navigateToReportsModule(obrowser);
									  
					try {
						By Report_PackageName_List = appInd.createAndGetByObject("Report_PackageName_List");
						List<WebElement> report_PackageName_List = obrowser.findElements(Report_PackageName_List);
						for(int i= 0;i<report_PackageName_List.size();i++) {
							report_PackageName_List = obrowser.findElements(Report_PackageName_List);
							if(report_PackageName_List.get(i).getAttribute("id").equalsIgnoreCase(oinpuTDtMap.get("Param_1"))) {
								//System.out.println("============");
								strStatus = "true";
								break;
							}else {
								strStatus = "false";
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",report_PackageName_List.get(i));
							}
						}
						if(strStatus.contains("false")) {
							strStatus = "false";
							System.out.println(oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.writeLog("fail", oinpuTDtMap.get("Param_1")+" package name is not present");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteReportPackage.png");
						}
						 boolean delBtnStatus = appInd.isElementsPresent(obrowser, "//div[@id='collapseButtonId' and contains(@style,'none')]/ancestor::div[@class='reports']/preceding-sibling::div[@class='title']/a[@id='"+oinpuTDtMap.get("Param_1")+"']/following-sibling::a[@ng-click='DeleteReportPackage(package)']");
						    if(delBtnStatus==true) {
								WebElement delete_Button = obrowser.findElement(By.xpath("//div[@id='collapseButtonId' and contains(@style,'none')]/ancestor::div[@class='reports']/preceding-sibling::div[@class='title']/a[@id='"+oinpuTDtMap.get("Param_1")+"']/following-sibling::a[@ng-click='DeleteReportPackage(package)']"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",delete_Button);
								Thread.sleep(6000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",delete_Button);
								strStatus="true";
								System.out.println("Clicked on Delete Report package Button");
								appInd.writeLog("pass", "Clicked on Delete Report package Button");
							}else {
								strStatus="false";
								System.out.println("unable to click on Delete Report package button");
								appInd.writeLog("fail", "unable to click on Report package button");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteReportPackage.png");
							}
						    boolean flag = appInd.ifElementsPresent(obrowser, "Report_Package_Delete_Yes_Button");
										
							//System.out.println(flag);
							if(flag==true) {
								System.out.println("Report_Delete_Yes_Buttons is displayed");
								strStatus="true";
								appInd.writeLog("pass", "Report_Delete_Yes_Buttons is displayed");
																						
								By Report_Package_Delete_Yes_Button = appInd.createAndGetByObject("Report_Package_Delete_Yes_Button");
								WebElement report_Package_Delete_Yes_Button = obrowser.findElement(Report_Package_Delete_Yes_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",report_Package_Delete_Yes_Button);
								
								System.out.println("clicked on delete Report YES button");
								Thread.sleep(5000);
								
								delBtnStatus = appInd.isElementsPresent(obrowser, "//div[@id='collapseButtonId' and contains(@style,'display: none')]/ancestor::div[@class='reports']/preceding-sibling::div[@class='title']/a[@id='"+oinpuTDtMap.get("Param_1")+"']/following-sibling::a[@ng-click='DeleteReportPackage(package)']");
								if(delBtnStatus==false) {
									strStatus="true";
									System.out.println("Report package is deleted successfully");
									appInd.writeLog("pass", "Report package is deleted successfully");
								}else {
									System.out.println("Report package is not deleted successfully");
									strStatus="false";
									appInd.writeLog("fail", "Report package is not deleted successfully");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteReportPackage.png");
								}
							}else {
								System.out.println("Report package is not deleted successfully");
								strStatus="false";
								appInd.writeLog("fail", "Report package is not deleted successfully");
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteReportPackage.png");
							}
					}catch(Exception e) {
						  //  e.printStackTrace();
						    strStatus += false;
							appInd.writeLog("Fail", "TC_Reports_DeleteReportPackage is failed");	
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\Reporting_Failed_Snapshot\\DeleteReportPackage.png");
					  }
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'TC_Reports_DeleteReportPackage' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_Reports_DeleteReportPackage' script was Successful");
						bolstatus = true;
						strStatus = null;
					}
				}
			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				bolstatus = false;
			}
			// write the result into Map
			if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Fail");
			} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Skip");
			} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
				oinpuTDtMap.put("result", "Pass");
			} else {
				oinpuTDtMap.put("result", "Skip");
			}
			oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
		} // for loop
		System.out.println("oinputMap.toString :::::::::::" +oinputMap);
		return oinputMap;
	} catch (Exception e) {
			appInd.writeLog("Exception",
				"Exception while executing 'TC_Reports_DeleteReportPackage' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}


	
}
