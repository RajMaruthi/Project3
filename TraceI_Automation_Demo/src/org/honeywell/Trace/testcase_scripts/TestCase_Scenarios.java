package org.honeywell.Trace.testcase_scripts;

import java.io.File;
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

public class TestCase_Scenarios {

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
							+ "\\Results\\snapshot\\Failed_to_launch_IE_browser_snapshort.png");
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
	// test case for F channel

	/********************************
	 * Method Name : TC_FChannelstatus Purpose : This method will count the F
	 * Channels screen Author : Vijay.k Reviewer : Date of Creation : 21-Nov-2018
	 * Date of
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FChannelstatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
		boolean result=false; //added by archana
		String dropDown = null;

		try {
			appInd.writeLog("#", "****TC_FChannelstatus:- started*****");
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
						// click on menu bar and click on spares
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(2000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(2000);

						// ==============================

						for (int row = 1;; row++) {
							try {
								// click on drop down and select
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element1 = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
								
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								
								System.out.println("dropDown::" +dropDown);
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

								By byF_Channel_Count = appInd.createAndGetByObject("F_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);
							
								/*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("F"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
							/*************************************************code added by archana *************/
                            outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
                            oGenericAppmethods.strcount=null;
                        	/*************************************************code added by archana ends*************/
                         
							// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						}
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FChannelstatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FChannelstatus' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
				// write the result into Map
				// =======================
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				// ====================
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_FChannelstatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_UChannelstatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		//String strcommonCountvalue = null;
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
		boolean result=false; //added by archana
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_UChannelstatus:- started*****");
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
						// click on menu bar and click on spares
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// =========================================================================
		

						// ====================
						for (int row = 1;; row++) {
							try {
								// click on drop down and select
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								Thread.sleep(10000);
								By byF_Channel_Count = appInd.createAndGetByObject("U_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);
								
								
								/*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("U"));

								System.out.println("Status:::" + strStatus);
								System.out.println("oGenericAppmethods.strcount::" +oGenericAppmethods.strcount);
							} catch (Exception e) {
								break;
							}
						/*************************************************code added by archana *************/
                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
                        oGenericAppmethods.strcount=null;
                    	/*************************************************code added by archana ends*************/
							
						}
						
						// ========================================================================
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_UChannelstatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_UChannelstatus' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_UChannelstatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_RChannelstatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
		boolean result=false; //added by archana
		String dropDown = null;
		//String strcommonCountvalue = null;
		try {
			appInd.writeLog("#", "****TC_RChannelstatus:- started*****");
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
						// click on menu bar and click on spares
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================


						// ====================
						for (int row = 1;; row++) {
							try {
								// click on drop down and select
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								 System.out.println("dropDown::"+ dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byF_Channel_Count = appInd.createAndGetByObject("R_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);
								
								
								/*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
						//		 System.out.println("dropDown::"+ oGenericAppmethods.strcount);
								/*************************************************code added by archana ends*************/
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("R"));

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
							/*************************************************code added by archana *************/
						//	 System.out.println("dropDownyy::"+ oGenericAppmethods.strcount);
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						}

						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_RChannelstatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_RChannelstatus' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				 result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
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
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_RChannelstatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterFreeSave Purpose : This method is saving the entry
	 * screen Author : Vijay.k Reviewer : Date of Creation : 22-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterFreeSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterFreeSave:- started*****");
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
						// click on menu bar and click on spare option
						By byclickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byclickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
	
						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								// click on drop down and select
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								
									WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li["+row+"]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);
							//==================	
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								// click on Filter and check box
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								// ================================================
								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
								WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);
									if (elementbyclick_On_PopUp_Ok_Button != null) {
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\FREE_FAIL_SNAPSHOT\\"+dropDown+" FREE_SaveFailed_snapshot.png");
										strStatus += "false";
										Thread.sleep(3000);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterFreeSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterFreeSave' script was Successful");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterFreeSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : CH_PSS_TC_FilterFreeStatus Purpose : This method is Filter the
	 * Free Status screen Author : Vijay.k Reviewer : Date of Creation : 22-Nov-2018
	 * Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterFreeStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_FilterFreeStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================

						for (int row = 1;; row++) {
							try {
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								// ==============================
								String dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

						/*		By byF_Channel_Count = appInd.createAndGetByObject("F_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("F"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}

						}

						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterFreeStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterFreeStatus' script was Successful");
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
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					//oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterFreeStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : CH_PSS_TC_FilterFreeCountResult Purpose : This method is Filter
	 * the Free Count and Showing Result screen Author : Vijay.k Reviewer : Date of
	 * Creation : 28-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterFreeCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	

		try {
			appInd.writeLog("#", "****TC_FilterFreeCountResult:- started*****");
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
						// click on menu bar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
						// click on dropdown
						// ================================================
                        
						By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
						WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
						List<WebElement> system_elements = obrowser.findElements(systems);
												
						for (int row = 0;row<system_elements.size();row++) {
							try {
								// ================================================
															
								system_elements = obrowser.findElements(systems);
								
								dropDown = system_elements.get(row).getText();
							
								appInd.waitForElementToBeClick(obrowser, system_elements.get(row), 240);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", system_elements.get(row));
								
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								Thread.sleep(700);
								
								By Spare_Reset_Button = appInd.createAndGetByObject("Spare_Reset_Button");
								WebElement spare_Reset_Button = obrowser.findElement(Spare_Reset_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",spare_Reset_Button);
								Thread.sleep(2000);
								
//								if (row > 0) {
//									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
//									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//											elementbyclick_On_CheckBox);
//								}
//								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);
							
								/*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("F"));
								System.out.println("Status:::" + strStatus);
								for(int i = 0;i<1;i++) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
									System.out.println("Clicked on Dropdown");
									Thread.sleep(4000);
								}
							} catch (Exception e) {
								//break;
								strStatus = "false";
								appInd.writeLog("Fail", "Exception in TC_FilterFreeCountResult Test case");
							}
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						}
						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterFreeCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterFreeCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterFreeCountResult' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : CH_PSS_TC_FilterUsedStatus Purpose : This method is Filter the
	 * Free Status screen Author : Vijay.k Reviewer : Date of Creation : 24-Nov-2018
	 * Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterUsedStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcommonCountvalue = null;
		try {
			appInd.writeLog("#", "****TC_FilterUsedStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);

						// ==============================
						for (int row = 1;; row++) {
							try {
								
								// ================================================
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								String dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

				/*				By byF_Channel_Count = appInd.createAndGetByObject("U_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("U"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}

						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterUsedStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterUsedStatus' script was Successful");
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
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterUsedStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterUsedSave Purpose : This method is saving the entry
	 * screen Author : Vijay.k Reviewer : Date of Creation : 25-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterUsedSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterUsedSave:- started*****");
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
						// click on menu bar and click on spare option
						By byclickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byclickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								// click on Filter and check box
								Thread.sleep(1000);
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								// ================================================
								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
											
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
									WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);

									if (elementbyclick_On_PopUp_Ok_Button != null) {
										
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\USED_FAIL_SNAPSHOT\\"+dropDown+" USED_SaveFailed_snapshot.png");
										strStatus += "false";
										Thread.sleep(3000);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterUsedSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterUsedSave' script was Successful");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterUsedSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : CH_PSS_TC_FilterUsedCountResult Purpose : This method is Filter
	 * the Free Count and Showing Result screen Author : Vijay.k Reviewer : Date of
	 * Creation : 28-Nov-2018 Date of
	 */
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Map<String, HashMap> TC_FilterUsedCountResult() {
//		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
//		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
//		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
//        boolean result=false; //added by archana	
//        String dropDown = null;
//		try {
//			appInd.writeLog("#", "****TC_FilterUsedStatus:- started*****");
//			obrowser = oDriver.getWebDriver();
//			boolean bolstatus = false;
//			String strExecutionsts = null;
//			oinputMap = appInd.getInputData(strTCID);
//			for (int TD = 1; TD <= oinputMap.size(); TD++) {
//				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
//				if ((obrowser != null)) {
//					// Read the Execution Status
//					strExecutionsts = oinpuTDtMap.get("Executestatus");
//					if (strExecutionsts.equalsIgnoreCase("yes")) {
//						// ########################################################################
//						// click on menu bar and click on spare option
//						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
//						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
//						Thread.sleep(3000);
//						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
//						Thread.sleep(3000);
//
//						// ==============================
//						for (int row = 1;; row++) {
//							try {
//								// ================================================
//								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
//								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//										elementbySelect_DropDown);
//								
//								 dropDown = obrowser
//										.findElement(By.xpath(
//												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
//										.getText();
//							
//								WebElement element = obrowser.findElement(
//										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
//
///*								By byF_Channel_Count = appInd.createAndGetByObject("U_Channel_Count");
//								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
//								Thread.sleep(2000);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//										elementbyF_Channel_Count);*/
//								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
//								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//										elementbybyclickOnFilter);
//								
//								if (row > 1) {
//									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
//									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//											elementbyclick_On_CheckBox);
//								}
//								Thread.sleep(1000);
//								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
//								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//										elementbyclick_On_CheckBox);
//								
//								// click on filter button
//								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
//								WebElement elementbyclick_on_Filter_Button = obrowser
//										.findElement(byclick_on_Filter_Button);
//								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//										elementbyclick_on_Filter_Button);
//
//				                 /*************************************************code added by archana *************/
//								oGenericAppmethods.strcount=dropDown+"#";
//								/*************************************************code added by archana ends*************/
//								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("U"));
//								System.out.println("Status:::" + strStatus);
//							} catch (Exception e) {
//								break;
//							}
//							
//							/*************************************************code added by archana *************/
//	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
//	                        oGenericAppmethods.strcount=null;
//	                    	/*************************************************code added by archana ends*************/
//						}
//
//						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
//						// ########################################################################
//						if (strStatus.contains("false")) {
//							appInd.writeLog("Fail", "'TC_FilterUsedStatus' script was failed");
//							bolstatus = false;
//							strStatus = null;
//						} else if (strStatus.contains("true")) {
//							appInd.writeLog("Pass", "'TC_FilterUsedStatus' script was Successful");
//							bolstatus = true;
//							strStatus = null;
//						}
//					}
//				} else {
//					appInd.writeLog("Fail", "Failed to launch the IE browser");
//					bolstatus = false;
//				}
//				/*************************************************code added by archana *************/
//				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
//				/*************************************************code added by archana ends*************/
//				// write the result into Map
//				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Fail");
//					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
//
//				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Skip");
//					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
//				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Pass");
//					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
//				} else {
//					oinpuTDtMap.put("result", "Skip");
//					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
//				}
//				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
//				oinpuTDtMap = null;
//			} // for loop
//			return oinputMap;
//		} catch (Exception e) {
//			appInd.writeLog("Exception",
//					"Exception while executing 'TC_FilterUsedCountResult' method. " + e.getMessage());
//			oinpuTDtMap.put("result", "Fail");
//			oinputMap.put("result", (HashMap) oinpuTDtMap);
//			return oinputMap;
//		}
//	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterUsedCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	
        String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterUsedStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);

						// ==============================
						By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
						WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
						List<WebElement> system_elements = obrowser.findElements(systems);
												
						for (int row = 0;row<system_elements.size();row++) {
							try {
								// ================================================
															
								system_elements = obrowser.findElements(systems);
								
								dropDown = system_elements.get(row).getText();
							
//								WebElement element = obrowser.findElement(
//										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								
								
								appInd.waitForElementToBeClick(obrowser, system_elements.get(row), 240);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", system_elements.get(row));
								
//								dropDown = obrowser
//										.findElement(By.xpath(
//												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
//										.getText();
								

/*								By byF_Channel_Count = appInd.createAndGetByObject("U_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								Thread.sleep(700);
								
								By Spare_Reset_Button = appInd.createAndGetByObject("Spare_Reset_Button");
								WebElement spare_Reset_Button = obrowser.findElement(Spare_Reset_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",spare_Reset_Button);
								Thread.sleep(2000);
								
//								if (row > 0) {
//									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
//									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//											elementbyclick_On_CheckBox);
//								}
//								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Used");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);

				                 /*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								//strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("U"));
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("U"));
								System.out.println("Status:::" + strStatus);
								for(int i = 0;i<1;i++) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
									System.out.println("Clicked on Dropdown");
									Thread.sleep(4000);
								}
								System.out.println("==========="+oGenericAppmethods.strcount);
							} catch (Exception e) {
								//break;
								strStatus = "false";
								appInd.writeLog("Fail", "Exception in TC_FilterUsedCountResult Test case");
							}
							
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        System.out.println(oGenericAppmethods.strcount);
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						}

						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterUsedCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterUsedCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterUsedCountResult' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterReserveStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_FilterReserveStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);

						// ==============================
						for (int row = 1;; row++) {
							try {
								// ================================================
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								
								String dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

/*								By byF_Channel_Count = appInd.createAndGetByObject("R_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);

								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("R"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}

						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterReserveStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterReserveStatus' script was Successful");
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
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterReserveStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterReservedSave Purpose : This method is saving the entry
	 * screen Author : Vijay.k Reviewer : Date of Creation : 28-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterReservedSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterReservedSave:- started*****");
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
						// click on menu bar and click on spare option
						By byclickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byclickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);

								if (row > 1) {
									// click on Filter and check box
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								// click on Filter and check box
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								// ================================================
								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
									Thread.sleep(3000);
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\RESERVE_FAIL_SNAPSHOT\\"+dropDown+" RESERVED_SaveFailed_snapshot.png");
			
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
									WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);

									if (elementbyclick_On_PopUp_Ok_Button != null) {
										strStatus += "false";
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterReservedSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterReservedSave' script was Successful");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterReservedSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterReservedCountResult Purpose : This method is Filter
	 * the Free Count and Showing Result screen Author : Vijay.k Reviewer : Date of
	 * Creation : 29-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterReservedCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	
        String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterReservedCountResult:- started*****");
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
						// click on menu bar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
						// click on dropdown

						By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
						WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
						Thread.sleep(4000);
						
						By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
						List<WebElement> system_elements = obrowser.findElements(systems);
												
						for (int row = 0;row<system_elements.size();row++) {
							try {
								// ================================================
															
								system_elements = obrowser.findElements(systems);
								
								dropDown = system_elements.get(row).getText();
							
								appInd.waitForElementToBeClick(obrowser, system_elements.get(row), 240);
								
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", system_elements.get(row));
								
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								Thread.sleep(700);
								
								By Spare_Reset_Button = appInd.createAndGetByObject("Spare_Reset_Button");
								WebElement spare_Reset_Button = obrowser.findElement(Spare_Reset_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",spare_Reset_Button);
								Thread.sleep(2000);
								
//								if (row > 0) {
//									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
//									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
//									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//											elementbyclick_On_CheckBox);
//								}
//								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Reserved");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);

				                 /*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("R"));
								System.out.println("Status:::" + strStatus);
								for(int i = 0;i<1;i++) {
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
									System.out.println("Clicked on Dropdown");
									Thread.sleep(4000);
								}
							} catch (Exception e) {
								//break;
								strStatus = "false";
								appInd.writeLog("Fail", "Exception in TC_FilterReservedCountResult Test case");
							}
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						}
						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterReservedCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterReservedCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterReservedCountResult' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterConflictStatus Purpose : This method is Filter the
	 * Free Status screen Author : Vijay.k Reviewer : Date of Creation : 26-Nov-2018
	 * Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterConflictStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_FilterConflictStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================

						// ==============================
						for (int row = 1;; row++) {
							try {
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								
								String dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

/*								By byF_Channel_Count = appInd.createAndGetByObject("C_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/


								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);

								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("C"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}

						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterConflictStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterConflictStatus' script was Successful");
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
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterConflictStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterConflictSave Purpose : This method is saving the entry
	 * screen Author : Vijay.k Reviewer : Date of Creation : 29-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterConflictSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterConflictSave:- started*****");
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
						// click on menu bar and click on spare option
						By byclickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byclickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								// click on Filter and check box
								Thread.sleep(1000);
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								// ================================================
								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
									WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);

									if (elementbyclick_On_PopUp_Ok_Button != null) {
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\CONFLICT_FAIL_SNAPSHOT\\"+dropDown+" CONFLICT_SaveFailed_snapshot.png");
								       strStatus += "false";
										Thread.sleep(3000);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterConflictSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterConflictSave' script was Successful");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterConflictSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterConflictCountResult Purpose : This method is Filter
	 * the Free Count and Showing Result screen Author : Vijay.k Reviewer : Date of
	 * Creation : 29-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterConflictCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	

		try {
			appInd.writeLog("#", "****TC_FilterConflictCountResult:- started*****");
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
						// click on menu bar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						// ================================================
						// click on dropdown
		
						// ==============================
						for (int row = 1;; row++) {
							try {
								// ================================================
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

/*								By byF_Channel_Count = appInd.createAndGetByObject("C_Channel_Count");
								WebElement elementbyF_Channel_Count = obrowser.findElement(byF_Channel_Count);
								Thread.sleep(2000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyF_Channel_Count);*/

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								
								if (row > 1) {
									By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
									WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_On_CheckBox);
								}
								Thread.sleep(1000);
								By byclick_On_CheckBox = appInd.createAndGetByObject("Click_On_CheckBox_Conflict");
								WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_On_CheckBox);
								
								// click on filter button
								By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
								WebElement elementbyclick_on_Filter_Button = obrowser
										.findElement(byclick_on_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Filter_Button);

				                 /*************************************************code added by archana *************/
								oGenericAppmethods.strcount=dropDown+"#";
								/*************************************************code added by archana ends*************/
								
								strStatus += String.valueOf(oGenericAppmethods.commonSparesChannel("C"));
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						}
						// strcommonCountvalue = String.valueOf(oGenericAppmethods.commonCountRecord);
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterConflictCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterConflictCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				
                 /*************************************************code added by archana *************/
                  result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
                  /*************************************************code added by archana ends*************/
				// write the result into Map
				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Fail");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);

				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
					oinpuTDtMap.put("result", "Pass");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				} else {
					oinpuTDtMap.put("result", "Skip");
					// oinpuTDtMap.put("countvalue", strcommonCountvalue);
				}
				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterConflictCountResult' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectStatusAll() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterProjectStatusAll:- started*****");
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
						// click on menubar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ================================================
			
						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								// click on dropdown
								By byclickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byclickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(
											By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
									System.out.println("selectElements:include:" + selectElements.size());
									for (WebElement checkbox : selectElements) {
										System.out.println("checkboxinclude:" + checkbox.isSelected());
										if (!checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
									}
								} catch (Exception e) {
								}
								try {
									List<WebElement> selectElements = obrowser
											.findElements(By.xpath("//*[@data-ng-model='sparefiltervm.checkAll']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());

										if (checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(
													By.xpath("//*[@id='projectsForFilter']/div/div/div/div[" + k
															+ "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
										WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
												"//*[@id='projectsForFilter']/div/div/div/div[" + k + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementSelectbyproject);
										
										
								
										try {
										WebElement elementbybyProject = obrowser.findElement(By.xpath("//*[@id='projectsForFilter']/div/div/div/div[2]/label/span"));
										if(elementbybyProject != null) {
									    	strStatus += true;
											// click on filter button
											By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
											WebElement elementbyclick_on_Filter_Button = obrowser
													.findElement(byclick_on_Filter_Button);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclick_on_Filter_Button);
											strStatus += String.valueOf(oGenericAppmethods.commonSparesChannelForAll());
									    }
										
										}catch(Exception e) {
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshot\\PROJECT_ALL_STATUS_FAIL_SNAPSHOT\\"+dropDown+" Project_Failed_snapshot.png");
											strStatus += false;
											System.out.println("strStatus::" +strStatus);
										}
										
			
									}
									
								} catch (Exception e) {
								}
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						} // ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterProjectStatusAll' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterProjectStatusAll' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterProjectStatusAll' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectSaveAll() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterProjectSaveAll:- started*****");
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
						// click on menu bar and click on spare option
						By byclickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byclickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(3000);
					    // =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(
											By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
									System.out.println("selectElements:include:" + selectElements.size());
									for (WebElement checkbox : selectElements) {
										System.out.println("checkboxinclude:" + checkbox.isSelected());
										if (!checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
									}
								} catch (Exception e) {
								}
								try {

									List<WebElement> selectElements = obrowser
											.findElements(By.xpath("//*[@data-ng-model='sparefiltervm.checkAll']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());

										if (checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(
													By.xpath("//*[@id='projectsForFilter']/div/div/div/div[" + k
															+ "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
										WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
												"//*[@id='projectsForFilter']/div/div/div/div[" + k + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementSelectbyproject);

									}
								} catch (Exception e) {
									break;
								}
								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
									WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);

									if (elementbyclick_On_PopUp_Ok_Button != null) {
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\PROJECT_ALL_SAVE_FAIL_SNAPSHOT\\"+dropDown+" PROJECTALL_SaveFailed_snapshot.png");
										strStatus += "false";
										Thread.sleep(3000);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								System.out.println("strFilterText::" +strFilterText);
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);

							} catch (Exception e) {
								break;
							}
						}
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterProjectSaveForAll' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterProjectSaveAll' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
					
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterProjectSaveAll' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectCountResultAll() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	

		try {
			appInd.writeLog("#", "****TC_FilterProjectCountResultAll:- started*****");
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
						// click on menubar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ================================================

						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								// click on dropdown
								By byclickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byclickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(
											By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
									System.out.println("selectElements:include:" + selectElements.size());
									for (WebElement checkbox : selectElements) {
										System.out.println("checkboxinclude:" + checkbox.isSelected());
										if (!checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
									}
								} catch (Exception e) {
								}
								try {
									List<WebElement> selectElements = obrowser
											.findElements(By.xpath("//*[@data-ng-model='sparefiltervm.checkAll']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());

										if (checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(
													By.xpath("//*[@id='projectsForFilter']/div/div/div/div[" + k
															+ "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
										WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
												"//*[@id='projectsForFilter']/div/div/div/div[" + k + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementSelectbyproject);
										
										
								
										try {
										WebElement elementbybyProject = obrowser.findElement(By.xpath("//*[@id='projectsForFilter']/div/div/div/div[2]/label/span"));
										if(elementbybyProject != null) {
									    	strStatus += true;
											// click on filter button
											By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
											WebElement elementbyclick_on_Filter_Button = obrowser
													.findElement(byclick_on_Filter_Button);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclick_on_Filter_Button);

							                 /*************************************************code added by archana *************/
											oGenericAppmethods.strcount=dropDown+"#";
											/*************************************************code added by archana ends*************/
											strStatus += String.valueOf(oGenericAppmethods.commonSparesChannelForAll());
									    }
										
										}catch(Exception e) {
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshot\\PROJECT_ALL_COUNT_FAIL_SNAPSHOT\\"+dropDown+" Project_Failed_snapshot.png");
											strStatus += false;
											System.out.println("strStatus::" +strStatus);
											oGenericAppmethods.strcount=dropDown+"#"+"No Project"+"#"+"No Project"+"#"+"Fail";
										}
										
			
									}
									
								} catch (Exception e) {
								}
								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
							
						/*************************************************code added by archana *************/
                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
                        oGenericAppmethods.strcount=null;
                    	/*************************************************code added by archana ends*************/
                        
						} // ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterProjectCountResultAll' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterProjectCountResultAll' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterProjectCountResultAll' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterProjectStatus:- started*****");
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
						// click on menubar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ================================================

						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								// click on dropdown
								By byclickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byclickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								dropDown = obrowser
											.findElement(By.xpath(
													"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
											.getText();
									WebElement elementSelectbyDropdown = obrowser.findElement(
											By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementSelectbyDropdown);

									By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
									WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbybyclickOnFilter);
									try {
										List<WebElement> selectElements = obrowser.findElements(
												By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
										System.out.println("selectElements:include:" + selectElements.size());
									
										for (WebElement checkbox : selectElements) {
											System.out.println("checkboxinclude:" + checkbox.isSelected());
											if (checkbox.isSelected()) {
												WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
														"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
														elementSelectbyproject);
											}
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
									} catch (Exception e) {
									}
				
									try {
										List<WebElement> selectElements = obrowser.findElements(By.xpath(
												"//*[@data-ng-model='sparefiltervm.selectedProject[project.ProjectId]']"));
										System.out.println("selectElements::" + selectElements.size());
										int k = 1;
										for (WebElement checkbox : selectElements) {
											k++;
											System.out.println("checkbox:" + checkbox.isSelected());
											if (checkbox.isSelected()) {
												WebElement elementbyclickChannel = obrowser.findElement(By
														.xpath("//*[@id='projectsForFilter']/div/div/div/div["+ k +"]/label/span"));
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
														elementbyclickChannel);
											}
										}
										try {
										WebElement elementbyclickChannel = obrowser.findElement(By
												.xpath("//*[@id='projectsForFilter']/div/div/div/div[2]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										
										// click on filter button
										By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
										WebElement elementbyclick_on_Filter_Button = obrowser
												.findElement(byclick_on_Filter_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_on_Filter_Button);
										strStatus += String.valueOf(oGenericAppmethods.commonSparesChannelForAll());
										}catch(Exception e) {
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshot\\PROJECT_STATUS_FAIL_SNAPSHOT\\"+dropDown+" Project_Failed_snapshot.png");
										strStatus += false;
										}
									} catch (Exception e) {
										//break;
									}

									System.out.println("Status:::" + strStatus);
								} catch (Exception e) {
								break;
							}
						} // ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterProjectStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterProjectStatus' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterProjectStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterProjectSave:- started*****");
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
						// click on menubar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(
											By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
									System.out.println("selectElements:include:" + selectElements.size());
								
									for (WebElement checkbox : selectElements) {
										System.out.println("checkboxinclude:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
										WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
												"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementSelectbyproject);
									}
								} catch (Exception e) {
								}
			
								try {
									List<WebElement> selectElements = obrowser.findElements(By.xpath(
											"//*[@data-ng-model='sparefiltervm.selectedProject[project.ProjectId]']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 1;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											WebElement elementbyclickChannel = obrowser.findElement(By
													.xpath("//*[@id='projectsForFilter']/div/div/div/div["+ k +"]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannel);
										}
									}
									WebElement elementbyclickChannel = obrowser.findElement(By
											.xpath("//*[@id='projectsForFilter']/div/div/div/div[2]/label/span"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannel);
								} catch (Exception e) {
									//break;
								}

								// enter data to filter
								By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
								obrowser.findElement(byFilterName).clear();
								String paramValue = oinpuTDtMap.get("Param_1");
								obrowser.findElement(byFilterName).sendKeys(paramValue);
								// obrowser.findElement(byFilterName).sendKeys("Free");

								// ====================================================
								By byclick_on_Save_and_Filter_Button = appInd
										.createAndGetByObject("Click_on_Save_and_Filter_Button");
								WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
										.findElement(byclick_on_Save_and_Filter_Button);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_Save_and_Filter_Button);
								try {
									By byclick_On_PopUp_Ok_Button = appInd
											.createAndGetByObject("Click_On_PopUp_Ok_Button");
									WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
											.findElement(byclick_On_PopUp_Ok_Button);

									if (elementbyclick_On_PopUp_Ok_Button != null) {
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\PROJECT_SAVE_FAIL_SNAPSHOT\\"+dropDown+" PROJECT_SaveFailed_snapshot.png");
									    strStatus += "false";
										Thread.sleep(3000);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_On_PopUp_Ok_Button);
									}
								} catch (Exception e) {
								}
								// ========================================================
								// click on save filter button to check saved status
								By byclick_and_check_Save_Filter = appInd
										.createAndGetByObject("Click_and_check_Save_Filter");
								WebElement elementbyclick_and_check_Save_Filter = obrowser
										.findElement(byclick_and_check_Save_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_and_check_Save_Filter);
								// verify in saved filter
								By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
								String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
								if (strFilterText.equalsIgnoreCase(paramValue)) {
									strStatus += "true";
								} else {
									strStatus += "false";
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterFreeSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterFreeSave' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterProjectSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterProjectCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana	

		String dropDown= null;
		try {
			appInd.writeLog("#", "****TC_FilterProjectCountResult:- started*****");
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
						// click on menubar and spare
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ================================================

						// =================================================
						// click on filter select

						for (int row = 1;; row++) {
							try {
								// click on dropdown
								By byclickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element = obrowser.findElement(byclickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								WebElement elementSelectbyDropdown = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementSelectbyDropdown);

								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbybyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbybyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(
											By.xpath("//*[@data-ng-model='sparefiltervm.includeProject']"));
									System.out.println("selectElements:include:" + selectElements.size());
								
									for (WebElement checkbox : selectElements) {
										System.out.println("checkboxinclude:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
													"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementSelectbyproject);
										}
										WebElement elementSelectbyproject = obrowser.findElement(By.xpath(
												"//*[@id='divEditFilter']/div[1]/div/div[1]/div[1]/div/div/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementSelectbyproject);
									}
								} catch (Exception e) {
								}
			
								try {
									List<WebElement> selectElements = obrowser.findElements(By.xpath(
											"//*[@data-ng-model='sparefiltervm.selectedProject[project.ProjectId]']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 1;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											WebElement elementbyclickChannel = obrowser.findElement(By
													.xpath("//*[@id='projectsForFilter']/div/div/div/div["+ k +"]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannel);
										}
									}
									try {
									WebElement elementbyclickChannel = obrowser.findElement(By
											.xpath("//*[@id='projectsForFilter']/div/div/div/div[2]/label/span"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannel);
									
									// click on filter button
									By byclick_on_Filter_Button = appInd.createAndGetByObject("Click_on_Filter_Button");
									WebElement elementbyclick_on_Filter_Button = obrowser
											.findElement(byclick_on_Filter_Button);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclick_on_Filter_Button);
					                
									/*************************************************code added by archana *************/
									oGenericAppmethods.strcount=dropDown+"#";
									/*************************************************code added by archana ends*************/
									strStatus += String.valueOf(oGenericAppmethods.commonSparesChannelForAll());
									}catch(Exception e) {
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
												+ "\\Results\\snapshot\\PROJECT_COUNT_FAIL_SNAPSHOT\\"+dropDown+" Project_Failed_snapshot.png");
										strStatus += false;
										oGenericAppmethods.strcount=dropDown+"#"+"No Project"+"#"+"No Project"+"#"+"Fail";
									}
								} catch (Exception e) {
									//break;
								}

								System.out.println("Status:::" + strStatus);
							} catch (Exception e) {
								break;
							}
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
						} 
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterProjectCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterProjectCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterProjectCountResultAll' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	public Map<String, HashMap> TC_FilterChannelsStatus() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_FilterChannelsStatus:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);

						// ==============================
						for (int row = 1;; row++) {
							try {
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								String dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" + dropDown);

								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

								// click on filter select
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclickOnFilter);

								try {
									List<WebElement> selectElements = obrowser.findElements(By.xpath(
											"//*[@data-ng-model='sparefiltervm.selectedchannelTypes[channelType]']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											WebElement elementbyclickChannel = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ k + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannel);
										}
									}
								} catch (Exception e) {
									break;
								}
								for (int i = 1;; i++) {
									try {
										if (i > 1) {
											// click on filter select
											By byclickOnFilter1 = appInd.createAndGetByObject("ClickOnFilter");
											WebElement elementbyclickOnFilter1 = obrowser.findElement(byclickOnFilter1);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickOnFilter1);

											WebElement elementbyclickChannelDeselect = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ (i - 1) + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannelDeselect);
										}
										WebElement elementbyclickChannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);

										Thread.sleep(1000);
										String elementchannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label"))
												.getText();

										System.out.println("elementchannel::" + elementchannel);
										//

										// click on filter button
										By byclick_on_Filter_Button = appInd
												.createAndGetByObject("Click_on_Filter_Button");
										WebElement elementbyclick_on_Filter_Button = obrowser
												.findElement(byclick_on_Filter_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_on_Filter_Button);

										strStatus += String
												.valueOf(oGenericAppmethods.commonSparesChannelType(elementchannel));
									} catch (Exception e) {
										break;
									}
								}
							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterChannelsStatus' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterChannelsStatus' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing TC_FilterChannelsStatus' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : CH_PSS_TC_FilterChannalsSave Purpose : This method is saving
	 * the entry screen Author : Vijay.k Reviewer : Date of Creation : 30-Nov-2018
	 * Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterChannalsSave() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_FilterChannalsSave:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ==============================
						for (int row = 1;; row++) {
							try {
								By byClickOnDropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement element1 = obrowser.findElement(byClickOnDropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
								
								dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" +dropDown);
					

								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

								// click on filter select
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclickOnFilter);

								try {
									List<WebElement> selectElements = obrowser.findElements(By.xpath(
											"//*[@data-ng-model='sparefiltervm.selectedchannelTypes[channelType]']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										if (checkbox.isSelected()) {
											WebElement elementbyclickChannel = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ k + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannel);
										}
									}
								} catch (Exception e) {
									break;
								}
								for (int i = 1;; i++) {
									try {
										if (i > 1) {
											// click on filter select
											By byclickOnFilter1 = appInd.createAndGetByObject("ClickOnFilter");
											WebElement elementbyclickOnFilter1 = obrowser.findElement(byclickOnFilter1);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickOnFilter1);

											WebElement elementbyclickChannelDeselect = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ (i - 1) + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannelDeselect);
										}
										WebElement elementbyclickChannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);

										Thread.sleep(1000);
										String elementchannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label"))
												.getText();

										System.out.println("elementchannel::" + elementchannel);
										//

										// enter data to filter
										By byFilterName = appInd.createAndGetByObject("Enter_Filter_Name");
										obrowser.findElement(byFilterName).clear();
										// String paramValue = oinpuTDtMap.get("Param_1");
										obrowser.findElement(byFilterName).sendKeys(elementchannel);
										// ====================================================
										By byclick_on_Save_and_Filter_Button = appInd
												.createAndGetByObject("Click_on_Save_and_Filter_Button");
										WebElement elementbyclick_on_Save_and_Filter_Button = obrowser
												.findElement(byclick_on_Save_and_Filter_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_on_Save_and_Filter_Button);
										try {
											By byclick_On_PopUp_Ok_Button = appInd
													.createAndGetByObject("Click_On_PopUp_Ok_Button");
											WebElement elementbyclick_On_PopUp_Ok_Button = obrowser
													.findElement(byclick_On_PopUp_Ok_Button);
											if (elementbyclick_On_PopUp_Ok_Button != null) {
												appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
														+ "\\Results\\snapshot\\CHANNEL_FAIL_SNAPSHOT\\"+dropDown+" CHANNEL_SaveFailed_snapshot.png");
												strStatus += "false";
												Thread.sleep(3000);
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
														elementbyclick_On_PopUp_Ok_Button);
											}

											// click on save filter button to check saved status
											By byclick_and_check_Save_Filter = appInd
													.createAndGetByObject("Click_and_check_Save_Filter");
											WebElement elementbyclick_and_check_Save_Filter = obrowser
													.findElement(byclick_and_check_Save_Filter);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclick_and_check_Save_Filter);
											// verify in saved filter
											By checkedSaveFilter = appInd.createAndGetByObject("Checked_saved");
											String strFilterText = obrowser.findElement(checkedSaveFilter).getText();
											if (strFilterText.equalsIgnoreCase(elementchannel)) {
												strStatus += "true";
											} else {
												strStatus += "false";
											}
										} catch (Exception e) {
										}
									} catch (Exception e) {
										break;
									}
								}

								System.out.println("Status:::" + strStatus);

							} catch (Exception e) {
								break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterChannalsSave' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterChannalsSave' script was Successful");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_FilterChannalsSave' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	/********************************
	 * Method Name : TC_FilterChannalsCountResult Purpose : This method is Filter
	 * the Free Count and Showing Result screen Author : Vijay.k Reviewer : Date of
	 * Creation : 30-Nov-2018 Date of
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_FilterChannalsCountResult() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String dropDown= null;
		Map<Integer,String> outputMap=new HashMap<Integer,String>(); //added by archana
        boolean result=false; //added by archana
		try {
			appInd.writeLog("#", "****TC_FilterChannalsCountResult:- started*****");
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
						// click on menu bar and click on spare option
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						// ==============================
						for (int row = 1;; row++) {
							try {
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								 dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("dropDown::" + dropDown);

								WebElement element = obrowser.findElement(
										By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

								// click on filter select
								By byclickOnFilter = appInd.createAndGetByObject("ClickOnFilter");
								WebElement elementbyclickOnFilter = obrowser.findElement(byclickOnFilter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclickOnFilter);
								try {
									List<WebElement> selectElements = obrowser.findElements(By.xpath(
											"//*[@data-ng-model='sparefiltervm.selectedchannelTypes[channelType]']"));
									System.out.println("selectElements::" + selectElements.size());
									int k = 0;
									for (WebElement checkbox : selectElements) {
										k++;
										System.out.println("checkbox:" + checkbox.isSelected());
										if (checkbox.isSelected()) {
											System.out.println("KKKK: " + k);
											WebElement elementbyclickChannel = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ k + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannel);
										}
									}
								} catch (Exception e) {
									break;
								}
								for (int i = 1;; i++) {
									try {
										if (i > 1) {
											// click on filter select
											By byclickOnFilter1 = appInd.createAndGetByObject("ClickOnFilter");
											WebElement elementbyclickOnFilter1 = obrowser.findElement(byclickOnFilter1);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickOnFilter1);

											WebElement elementbyclickChannelDeselect = obrowser.findElement(By
													.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
															+ (i - 1) + "]/label/span"));
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyclickChannelDeselect);
										}
										WebElement elementbyclickChannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label/span"));
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);

										Thread.sleep(1000);
										String elementchannel = obrowser.findElement(
												By.xpath("//*[@id='divEditFilter']/div[1]/div/div[2]/div[3]/div/div["
														+ i + "]/label"))
												.getText();
										System.out.println("elementchannel::" + elementchannel);
										// click on filter button
										By byclick_on_Filter_Button = appInd
												.createAndGetByObject("Click_on_Filter_Button");
										WebElement elementbyclick_on_Filter_Button = obrowser
												.findElement(byclick_on_Filter_Button);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclick_on_Filter_Button);

						                 /*************************************************code added by archana *************/
										oGenericAppmethods.strcount=dropDown+"#";
										/*************************************************code added by archana ends*************/
										strStatus += String
												.valueOf(oGenericAppmethods.commonSparesChannelType(elementchannel));

									} catch (Exception e) {
										break;
									}
								}
								System.out.println("strStatus::" + strStatus);
							} catch (Exception e) {
								break;
							}
							/*************************************************code added by archana *************/
	                        outputMap.put(outputMap.size()+1,oGenericAppmethods.strcount );
	                        oGenericAppmethods.strcount=null;
	                    	/*************************************************code added by archana ends*************/
							
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_FilterChannalsCountResult' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_FilterChannalsCountResult' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.writeLog("Fail", "Failed to launch the IE browser");
					bolstatus = false;
				}
				/*************************************************code added by archana *************/
				result=appInd.writecountexcel(outputMap, strTCID, "Spares_Module");
				/*************************************************code added by archana ends*************/
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_FilterChannalsCountResult' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	//==================Parameter Query
	
	

	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_Print_ResultPage() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_Print_ResultPage:- started*****");
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
									// click on menu bar and click on spare option
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										String filePath = System.getProperty("user.dir")+"\\AutoIT\\";
										// boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_11"), "Query_PrintFile", "pdf");
										 boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_11"), "Query_PrintFile", "xps");
											if(deleteFileStatus==true) {
												strStatus = "true";
												//System.out.println("Files are deleted");
												appInd.writeLog("pass", "Files are deleted");
											}else {
												//System.out.println("Files are not deleted");
												appInd.writeLog("pass", "Files are not deleted");
											}
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(3000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										
										try {
											start = System.currentTimeMillis();
											for(count = 1;;count++) {
												By executionInProgress = appInd.createAndGetByObject("Query_Execution_In_Progress");
												WebElement ele = obrowser.findElement(executionInProgress);
												if(ele.isDisplayed()) {
													System.out.println("Query execution in progress: "+count);
												}
											}
											
										}catch(Exception e) {
											System.out.println("Result is displayed");
											Thread.sleep(1000);
											By print_Button = appInd.createAndGetByObject("Query_Print_Button");
											WebElement Query_Print_Button = obrowser.findElement(print_Button);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",Query_Print_Button);
											System.out.println("Clicked on Print Button");
											Thread.sleep(3000);
											//appInd.printFileInIE_New();
											//Runtime.getRuntime().exec(filePath+"ParameterQuery_PrintFile.exe");
											Runtime.getRuntime().exec(filePath+"ParameterQuery_PrintFile.exe");
											System.out.println("File Printed");
											Thread.sleep(15000);
											//File file = new File(oinpuTDtMap.get("Param_11")+"Query_PrintFile"+".pdf");
											File file = new File(oinpuTDtMap.get("Param_11")+"Query_PrintFile"+".xps");
											//File file = new File(oinpuTDtMap.get("Param_11")+"Query_PrintFile");
											  if(file.exists()){
												  strStatus = "true";
												  System.out.println("File is printed successfully");
												  appInd.writeLog("Fail", "File is printed successfully");
											  }else{
												  strStatus = "false";
												  System.out.println("File is not printed successfully");
												  appInd.writeLog("Fail", "File is not printed successfully");
											  }
																						
										}										
										
									}catch(Exception e) {
										strStatus = "false";
										appInd.writeLog("Fail", "'TC_ParameterQuery_Print_ResultPage' script was failed");
									}	
									break;
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
									
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "'TC_ParameterQuery_Print_ResultPage' script was failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\ParameterQuery_Print_ResultPage_Failed.png");
								} 
							}catch(Exception e) {
								strStatus = "false";
								appInd.writeLog("Fail", "'TC_ParameterQuery_Print_ResultPage' script was failed");
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_Print_ResultPage' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_Print_ResultPage' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_Print_ResultPage' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Map<String, HashMap> TC_ParameterQuery_Validation_results_page () {
//		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
//		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
//		double pageLoadTime_Seconds=0;
//		double totaltime;
//		String strProjectName = null;
//		try {
//			System.out.println("**************parameterQuery***********");
//			appInd.writeLog("#", "****TC_ParameterQuery_Validation_results_page:- started*****");
//			obrowser = oDriver.getWebDriver();
//			boolean bolstatus = false;
//			String strExecutionsts = null;
//			oinputMap = appInd.getInputData(strTCID);
//			System.out.println("oinputMap.tostring::" +oinputMap.toString());
//			for (int TD = 1; TD <= oinputMap.size(); TD++) {
//				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
//				if ((obrowser != null)) {
//					// Read the Execution Status
//					strExecutionsts = oinpuTDtMap.get("Executestatus");
//					if (strExecutionsts.equalsIgnoreCase("yes")) {
//						// ########################################################################
//									// click on menu bar and click on spare option
//						
//						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
//						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
//						Thread.sleep(1000);
//						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
//						Thread.sleep(1000);
//						
//						// ########################################################################
//						  strProjectName  = oinpuTDtMap.get("Param_1");    
//						  String param9= oinpuTDtMap.get("Param_9");
//						  System.out.println("param9::" +param9);
//					//	for(int row = 1;;row++) {
//							try {
//							By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
//							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//									elementbySelect_DropDown);
//						
//							
//					/*		WebElement elementbyclickChannel = obrowser.findElement(
//									By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + 1 + "]/a"));
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//									elementbyclickChannel);
//							System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);
//							
//						String 	dropDown = obrowser
//									.findElement(By.xpath(
//											"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + 1 + "]/a"))
//									.getText();
//						*/
//						
//							WebElement elementbyclickChannel = obrowser.findElement(
//									By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[1]/a"));
//							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
//									elementbyclickChannel);
//							System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);
//			
//					     System.out.println("strProjectName::" +strProjectName);
//						
//					     if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
//								try {
//									    System.out.println("****inside for loop****");
//										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
//										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
//										//strStatus += "true";
//										Thread.sleep(3000);
//										By click_on_run_query = appInd.createAndGetByObject("Click_on_validate_query");
//										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
//										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
//										//===============
//										
//										try {
//											long time = System.currentTimeMillis();
//										     System.out.println("TIME::"+time);
//											boolean jx = oGenericAppmethods.waitForJSandJQueryToLoad(obrowser);
//											System.out.println(jx);
//											long time1 = System.currentTimeMillis();
//											//pageLoad.stop();
//											//pageLoadTime_ms = pageLoad.getTime();
//											System.out.println("TIME1::"+time1);
//										    totaltime = time1-time;
//										    System.out.println(totaltime);
//										    pageLoadTime_Seconds = totaltime / 1000;
//
//										} catch (Exception e) {
//											appInd.writeLog("Exception",
//													"Exception while executing waituntill process in 'TC_ParameterQuery_Validation_results_page' method. ");// +
//																																	// e.getMessage());
//										}
//										if (pageLoadTime_Seconds <= 30000) {
//											System.out.println("okkkk");
//											strStatus += true;
//											appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
//										} else {
//											System.out.println("Notokkkk");
//											strStatus += false;
//											appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
//										}
//										//===============		
//										Thread.sleep(3000);
//										By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
//										WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
//										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
//										
//								}catch(Exception e) {
//		       							System.out.println("***forLoopException***");
//						        		break;
//						        	}	
//						}  
//							}catch(Exception e) {
//								break;
//							}
//						//}
//						// ########################################################################
//						if (strStatus.contains("false")) {
//							appInd.writeLog("Fail", "'TC_ParameterQuery_Validation_results_page' script was failed");
//							bolstatus = false;
//							strStatus = null;
//						} else if (strStatus.contains("true")) {
//							appInd.writeLog("Pass", "'TC_ParameterQuery_Validation_results_page' script was Successful");
//							bolstatus = true;
//							strStatus = null;
//						}
//					}
//				} else {
//					appInd.writeLog("Fail", "Failed to launch the IE browser");
//					bolstatus = false;
//				}
//				// write the result into Map
//				if ((bolstatus == false) && (strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Fail");
//				} else if ((bolstatus == false) && !(strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Skip");
//				} else if ((bolstatus == true) && (strExecutionsts.equalsIgnoreCase("yes"))) {
//					oinpuTDtMap.put("result", "Pass");
//				} else {
//					oinpuTDtMap.put("result", "Skip");
//				}
//				oinputMap.put(String.valueOf(TD), (HashMap) oinpuTDtMap);
//				oinpuTDtMap = null;
//			} // for loop
//			System.out.println("oinputMap.toString :::::::::::" +oinputMap);
//			return oinputMap;
//		} catch (Exception e) {
//				appInd.writeLog("Exception",
//					"Exception while executing 'TC_ParameterQuery_Validation_results_page' method. " + e.getMessage());
//			oinpuTDtMap.put("result", "Fail");
//			oinputMap.put("result", (HashMap) oinpuTDtMap);
//			return oinputMap;
//		}
//	}
	
//=========test cases by janhavi=============
	/********************************
	 * Method Name : TC_ProjectCreation Purpose : This method is for creation of new
	 * project screen Author : Janhavi Reviewer : Date of Creation : 21-Nov-2018
	 * Date of modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ProjectCreation() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
				appInd.writeLog("#", "****TC_ProjectCreation:- started*****");
				obrowser = oDriver.getWebDriver();
				boolean bolstatus = false;
				String strExecutionsts = null;
				String strparamvalue = null;
				oinputMap = appInd.getInputData(strTCID);
				for (int TD = 1; TD <= oinputMap.size(); TD++) {
					oinpuTDtMap = oinputMap.get(String.valueOf(TD));
					if ((obrowser != null)) {
						// Read the Execution Status
						strExecutionsts = oinpuTDtMap.get("Executestatus");
						
						if (strExecutionsts.equalsIgnoreCase("yes"))
						{
							// #######################################################################
						
	                  	//		strStatus += true;
							boolean result1=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

	    						By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
	    						Thread.sleep(3000);
	    						WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
	    						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
	    						Thread.sleep(5000);
								bolstatus = false;
						
								 strStatus += String.valueOf(
											appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_1")));
									// Set the Password value
									strStatus += String.valueOf(
											appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_2")));
									// click on the ok button
									By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
									WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
								boolean result2=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

	                  	
							
							
							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
							By byClickOnDropDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
							//Thread.sleep(3000);
							WebElement element = obrowser.findElement(byClickOnDropDown_Spares_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
							System.out.println("byClickOnDropDown_Spares_system::" + byClickOnDropDown_Spares_system);
							appInd.waitFor(obrowser, "All_spare", "element", "", 5000);
							//##################################################################################
							//Click on Projects
							Thread.sleep(20000);
							By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
							Thread.sleep(3000);
							WebElement elementw = obrowser.findElement(byClickOnProject_List);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
							//#################################################################################
							//Click on Create new project
							//strStatus += String.valueOf(appInd.setObject(obrowser, "New_Project", "E"));
							By byClickOnNew_Project = appInd.createAndGetByObject("New_Project");
							Thread.sleep(3000);
							WebElement elementz = obrowser.findElement(byClickOnNew_Project);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementz);
							appInd.waitFor(obrowser, "Select", "element", "", 5000);
							//##################################################################################
							//Select Systems
							By Select_System = appInd.createAndGetByObject("Select_System");
							WebElement ele = obrowser.findElement(Select_System);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
							appInd.waitFor(obrowser, "Wait_for_system", "element", "", 50000);
								boolean flag = true;
								int i = 1;
								String p;
								String xp = null;
								String system = "Param_" + 3;
								try {
									while (flag) {
										xp = "//*[@id=\"project-step2\"]/div[4]/div[1]/div[1]/ul/li[" + i
												+ "]/div/label";
										System.out.println("select" + obrowser.findElement(By.xpath(xp)).getText());

										p = obrowser.findElement(By.xpath(xp)).getText();
										if (p.equalsIgnoreCase(oinpuTDtMap.get(system)))
											flag = false;
										i++;
									}
									ele = obrowser.findElement(By.xpath(xp));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
								} catch (Exception e) {

								}
							
							appInd.waitFor(obrowser, "Select", "element", "", 5000);
							Select_System = appInd.createAndGetByObject("Select_System");
							ele = obrowser.findElement(Select_System);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
							//###############################################################################
							//Click and enter Project name
							By byclickOnClick_Pname = appInd.createAndGetByObject("Click_Pname");
							Thread.sleep(3000);
							WebElement elementi = obrowser.findElement(byclickOnClick_Pname);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementi);
							appInd.waitFor(obrowser, "Select", "element", "", 5000);
							strStatus += String
									.valueOf(appInd.setObject(obrowser, "Click_Pname", oinpuTDtMap.get("Param_4")));
							appInd.waitFor(obrowser, "Select", "element", "", 1000);
							//####################################################################################
							//Click and enter description
							By byclickOnClick_Description = appInd.createAndGetByObject("Click_Description");
							Thread.sleep(3000);
							WebElement elementa = obrowser.findElement(byclickOnClick_Description);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementa);
							strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Description", "abcf"));// description
							appInd.waitFor(obrowser, "Select", "element", "", 5000);
							//##################################################################################
							//Click on OK button
							try
							{
							By byClickOnCreate_Project_name = appInd.createAndGetByObject("Create_Project_name");
							WebElement elementh = obrowser.findElement(byClickOnCreate_Project_name);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
							}
							catch(Exception e)
							{
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\ProjectCreation\\TC_ProjectCreation_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ProjectCreation' script was failed");
								strStatus+= false;
							}
							//check project created
							By created_project = appInd.createAndGetByObject("Created_project");
							Thread.sleep(5000);
														String projectName = obrowser.findElement(created_project).getText();
							System.out.println(projectName);
							if (projectName.contains(oinpuTDtMap.get("Param_4"))) {
								strStatus += true;
							} else
								strStatus += false;
							// ########################################################################
							if (strStatus.contains("false")) {
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\Project_creation\\TC_ProjectCreation_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ProjectCreation' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ProjectCreation' script was Successful");
								bolstatus = true;
								strStatus = null;
							}
						}

					} else {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshort\\Failed_to_launch_IE_browser_snapshort.png");
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
						+ "\\Results\\snapshort\\TC_ProjectCreation_Fail_deuTO_Exception_snapshort.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ProjectCreation' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	
	/********************************
	 * Method Name : TC_ProjectCreationbymultiplesystem Purpose : This method is for
	 * creation of project by selecting multiple systems screen Author : Janhavi
	 * Reviewer : Date of Creation : 21-Nov-2018 Date of modification : 21-Nov-2018
	 * ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ProjectCreationbymultiplesystem() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_ProjectCreationbymultiplesystem:- started*****");
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
						
						//String ur=obrowser.getCurrentUrl();
						//System.out.println(ur);
					//	String strproject= String.valueOf(appInd.isElementPresent(obrowser, "Project_disabled"));
                  		boolean result1=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

    						By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
    						Thread.sleep(3000);
    						WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
    						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
    						Thread.sleep(5000);
													 strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_1")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_2")));
								// click on the ok button
								By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
								WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
						 		boolean result2=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

                  	
						
						
						// #######################################################################
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
						By byClickOnDropDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
						Thread.sleep(3000);
						WebElement element = obrowser.findElement(byClickOnDropDown_Spares_system);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						System.out.println("byClickOnDropDown_Spares_system::" + byClickOnDropDown_Spares_system);
						appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
						
						Thread.sleep(30000);
						//###########################################################################################
						//Click on Projects
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Project_List", "E"));
						By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byClickOnProject_List);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						//########################################################################################
						//Click on create new Project
						//strStatus += String.valueOf(appInd.setObject(obrowser, "New_Project", "E"));
						By byClickOnNew_Project = appInd.createAndGetByObject("New_Project");
						Thread.sleep(3000);
						 elementq = obrowser.findElement(byClickOnNew_Project);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
						appInd.waitFor(obrowser, "Select", "element", "", 1000);
						//#######################################################################################
						//Select systems
						By Select_System = appInd.createAndGetByObject("Select_System");
						WebElement ele = obrowser.findElement(Select_System);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
						appInd.waitFor(obrowser, "Wait_for_system", "element", "", 5000);
                         System.out.println(oinpuTDtMap.size());
						for (int j =3; j <= oinpuTDtMap.size()-2 ; j++) {
							boolean flag = true;
							int i = 1;
							String p;
							String xp = null;
							String system = "Param_" + j;
							while (flag) {
								xp = "//*[@id=\"project-step2\"]/div[4]/div[1]/div[1]/ul/li[" + i + "]/div/label";
								System.out.println("select" + obrowser.findElement(By.xpath(xp)).getText());

								p = obrowser.findElement(By.xpath(xp)).getText();
								if (p.equalsIgnoreCase(oinpuTDtMap.get(system)))
									flag = false;
								i++;
							}
							ele = obrowser.findElement(By.xpath(xp));
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
						}
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						System.out.println("in select");
					    By select_system = appInd.createAndGetByObject("Select_System");
					    WebElement elee = obrowser.findElement(select_system);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elee);
						//########################################################################################
						//Click and enter Project name
					//strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Pname", "E"));
						By byClick_Pname = appInd.createAndGetByObject("Click_Pname");
						WebElement elementa = obrowser.findElement(byClick_Pname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementa);
						
						String Pname = "Param_" + (oinpuTDtMap.size() - 1);
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Pname", oinpuTDtMap.get(Pname)));
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						//#########################################################################################
						//Click and enter description
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Description", "abcs"));// description
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						//########################################################################################
						//Click on OK button
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Create_Project_name", "E"));
						By byClickOnCreate_Project_name = appInd.createAndGetByObject("Create_Project_name");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byClickOnCreate_Project_name);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(6000);
						By created_project = appInd.createAndGetByObject("Created_project");
						String projectName = obrowser.findElement(created_project).getText();

						if (projectName.contains(oinpuTDtMap.get(Pname))) {
							strStatus += true;
						} else
							strStatus += false;
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\Projectcreationmultiple\\TC_ProjectCreationbymultiplesystem_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_ProjectCreationbymultiplesystem' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ProjectCreationbymultiplesystem' script was Successful");
							bolstatus = true;
							strStatus = null;
						}

					}

				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshort\\Projectcreationmultiple\\Failed_to_launch_IE_browser_snapshort.png");

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
						+ "\\Results\\snapshort\\Projectcreationmultiple\\TC_ProjectCreationbymultiplesystem_Fail_deuTO_Exception_snapshort.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			appInd.writeLog("Exception",
					"Exception while executing 'TC_ProjectCreationbymultiplesystem' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	
	/********************************
	 * Method Name : TC_EditandsaveProject Purpose : This method is for edit and
	 * save existing project screen Author : Janhavi Reviewer : Date of Creation :
	 * 21-Nov-2018 Date of modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_EditandsaveProject() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_EditandsaveProject:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;

			String strExecutionsts = null;
			String strparamvalue = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));

				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes"))

					{
						// #######################################################################
						boolean result1=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

						
                  		By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
    						Thread.sleep(3000);
    						WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
    						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
    						Thread.sleep(5000);
						 strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_1")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_2")));
								// click on the ok button
								By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
								WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
						boolean result2=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

						
                  		
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
						By byClickOnDropDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
						Thread.sleep(3000);
						WebElement element = obrowser.findElement(byClickOnDropDown_Spares_system);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
						Thread.sleep(3000);
						//#################################################################################
						//Click on Projects
						By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byClickOnProject_List);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
					   //####################################################################################
						//Click on Edit Projects
						if(appInd.isElementPresent(obrowser, "Projects"))
						{
						By byClickOnEdit_Project = appInd.createAndGetByObject("Edit_Project");// Click on edit
						 element1 = obrowser.findElement(byClickOnEdit_Project);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						Thread.sleep(20000);
						//#####################################################################################
						//Select System
						By Select_System = appInd.createAndGetByObject("Select_System");
						WebElement ele = obrowser.findElement(Select_System);
						Thread.sleep(10000);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
						appInd.waitFor(obrowser, "Wait_for_system", "element", "", 5000);
													boolean flag = true;
							int i = 1;
							String p;
							String xp = null;
							String system = "Param_" + 3;
							try {
								while (flag) {
									xp = "//*[@id=\"project-step2\"]/div[4]/div[1]/div[1]/ul/li[" + i
											+ "]/div/label";
									System.out.println("select" + obrowser.findElement(By.xpath(xp)).getText());

									p = obrowser.findElement(By.xpath(xp)).getText();
									if (p.equalsIgnoreCase(oinpuTDtMap.get(system)))
										flag = false;
									i++;
								}
								ele = obrowser.findElement(By.xpath(xp));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
							} catch (Exception e) {

							}
						
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						Select_System = appInd.createAndGetByObject("Select_System");
						ele = obrowser.findElement(Select_System);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
						//###########################################################################################
						//Click and enter description
						appInd.click_and_clearObject(obrowser, "Click_Description");
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Description", oinpuTDtMap.get("Param_4")));
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Save", "E"));
						By byClickOnSave= appInd.createAndGetByObject("Save");// Click on edit
						WebElement element11 = obrowser.findElement(byClickOnSave);
						if(element11.isEnabled())
						{
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element11);
						}
						else
						{
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\projectedit\\TC_EditandsaveProject_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_EditandsaveProject' script was failed");
							bolstatus = false;
							strStatus = null;
										}
						}
						else
			            {
			            	strStatus+=false;
			            }
						
						
                        // ########################################################################
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\projectedit\\TC_EditandsaveProject_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_EditandsaveProject' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_EditandsaveProject' script was Successful");
							bolstatus = true;
							strStatus = null;
						}

					}

				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshort\\projectedit\\Failed_to_launch_IE_browser_snapshort.png");

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
						+ "\\Results\\snapshort\\projectedit\\TC_EditandsaveProject_Fail_deuTO_Exception_snapshort.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_EditandsaveProject' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	/********************************
	 * Method Name : TC_Deleteexistingproject Purpose : This method will delete the
	 * exixting project screen Author : Janhavi Reviewer : Date of Creation :
	 * 21-Nov-2018 Date of modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Deleteexistingproject() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_Deleteexistingproject:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;

			String strExecutionsts = null;
			String strparamvalue = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));

				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// #######################################################################
						boolean result1=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

					  By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
    						Thread.sleep(3000);
    						WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
    						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
    						Thread.sleep(5000);
    						boolean result2=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
							 strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_1")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_2")));
								// click on the ok button
								By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
								WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
						 
								boolean result3=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
						
                        //*******click on menu***********
						
						Thread.sleep(20000);
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
                       //***************click on spare**********************
											
						
						By byClickOnDropDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
					
						WebElement element = obrowser.findElement(byClickOnDropDown_Spares_system);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
						//############################################################
						Thread.sleep(10000);
						
						//Click on Projects
						By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byClickOnProject_List);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						//##############################################################################
						//Click on delete projects
						if(appInd.isElementPresent(obrowser, "Projects"))
						{
						appInd.waitFor(obrowser, "wait_for_edit", "element", "", 1000);
						By byClickOnDelete_Project = appInd.createAndGetByObject("Delete_Project");
					 element1 = obrowser.findElement(byClickOnDelete_Project);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
                      //###################################################################################
						//Click on OK button
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Delete_confirm", "E"));
						By byClickOnDelete_confirm = appInd.createAndGetByObject("Delete_confirm");
						WebElement elementj = obrowser.findElement(byClickOnDelete_confirm);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
                   // ########################################################################
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\DeleteProject\\TC_Deleteexistingproject_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_Deleteexistingproject' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Deleteexistingproject' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshort\\DeleteProject\\Failed_to_launch_IE_browser_snapshort.png");

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
						+ "\\Results\\snapshort\\DeleteProject\\TC_Deleteexistingproject_Fail_deuTO_Exception_snapshort.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception",
					"Exception while executing 'TC_Deleteexistingproject' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	/********************************
	 * Method Name : TC_SaveAs Purpose : This method will launch the home screen
	 * Author : Janhavi Reviewer : Date of Creation : 21-Nov-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_SaveAs() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		try {
			appInd.writeLog("#", "****TC_SaveAs:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			String strparamvalue = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes"))
					{
				// #######################################################################
				boolean result1=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);

					    						By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
    						Thread.sleep(3000);
    						WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
    						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
    						Thread.sleep(5000);
							 strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_1")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_2")));
								// click on the ok button
								By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
								WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
								boolean result2=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
						
                 		
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
						By byClickOnDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
					
						WebElement element = obrowser.findElement(byClickOnDown_Spares_system);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						Thread.sleep(20000);
						By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byClickOnProject_List);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						//######################################################################################
						//Click on SaveAs
						if(appInd.isElementPresent(obrowser, "Projects"))
						{
						By byClickOnEdit_Project = appInd.createAndGetByObject("Edit_Project");// Click on edit
						 element1 = obrowser.findElement(byClickOnEdit_Project);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
						
						Thread.sleep(1000);
						//#####################################################################################
					
					appInd.waitFor(obrowser, "Select", "element", "", 5000);
						appInd.click_and_clearObject(obrowser, "Click_Pname");
						strStatus += String
								.valueOf(appInd.setObject(obrowser, "Click_Pname", oinpuTDtMap.get("Param_3")));// project
																												// name
						appInd.waitFor(obrowser, "Select", "element", "", 5000);
					

						//strStatus += String.valueOf(appInd.setObject(obrowser, "Save_As", "E"));
						By byClickOnSave_As = appInd.createAndGetByObject("Save_As");
						WebElement elementp = obrowser.findElement(byClickOnSave_As);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementp);
						Thread.sleep(10000);
						try
						{
						By created_project = appInd.createAndGetByObject("Created_project");
						String projectName = obrowser.findElement(created_project).getText();
						if (projectName.contains(oinpuTDtMap.get("Param_3"))) {
							strStatus += true;
						} else
							strStatus += false;
						}
						catch(Exception e) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ProjectSaveaAs\\TC_SaveAs_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_SaveAs' script was failed");
							bolstatus = false;
							strStatus += false;
						}
						
						
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ProjectSaveaAs\\TC_SaveAs_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_SaveAs' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_SaveAs' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
						}

					}

				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshort\\ProjectSaveAs\\Failed_to_launch_IE_browser_snapshort.png");

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
						+ "\\Results\\snapshort\\ProjectSaveAs\\TC_SaveAs_Fail_deuTO_Exception_snapshort.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			appInd.writeLog("Exception", "Exception while executing 'TC_SaveAs' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	/********************************
	 * Method Name : TC_ReserveaChannel Purpose : This method will launch the home
	 * screen Author : Janhavi Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
@SuppressWarnings({ "unchecked", "rawtypes" })
        public Map<String, HashMap> TC_ReserveaChannel() {
                Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
                Map<String, String> oinpuTDtMap = new HashMap<String, String>();
                String strcurrentTD = null;
                int totalfcount=0;
                
                String strcommonCountvalue = null;
                try {
                        
                                appInd.writeLog("#", "****TC_ReserveaChannel:- started*****");
                                obrowser = oDriver.getWebDriver();
                                boolean bolstatus = false;
                                String strExecutionsts = null;
                                String strparamvalue = null;
                                
                        
                                oinputMap = appInd.getInputData(strTCID);
                                for (int TD = 1; TD <= oinputMap.size(); TD++) {
                                        oinpuTDtMap = oinputMap.get(String.valueOf(TD));
                                        if ((obrowser != null)) {
                                                // Read the Execution Status
                                                strExecutionsts = oinpuTDtMap.get("Executestatus");
                                                strparamvalue = oinpuTDtMap.get("Param_1");
                                                System.out.println(strparamvalue);
                                                if (strExecutionsts.equalsIgnoreCase("yes"))
                                                {
                                                // ########################################################################
                                                // Set the username value
                                                                                                  
                                                By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
                                                    //        Thread.sleep(3000);
                                                            WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
                                                            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
                                                            Thread.sleep(5000);
                                                                //bolstatus = false;
                                                                //strStatus += false;
                                                                 strStatus += String.valueOf(
                                                                                        appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_5")));
                                                                        // Set the Password value
                                                                        strStatus += String.valueOf(
                                                                                        appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_6")));
                                                                        // click on the ok button
                                                                        By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
                                                                        WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
                                                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
                                                         
                                          
                                                        
                                                        
                                                                        boolean result=        appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
                                                        By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
                                                        obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
                                                        Thread.sleep(1000);
                                                        obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
                                                        Thread.sleep(1000);
                                                //        appInd.waitFor(obrowser, "Spare_management", "element", "", 5000);
                                                By byClickOnDropDown_Spares_system = appInd.createAndGetByObject("DropDown_Spares_system");
                                                //Thread.sleep(3000);
                                                WebElement elementd = obrowser.findElement(byClickOnDropDown_Spares_system);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementd);
                                                System.out.println("byClickOnDropDown_Spares_system::" + byClickOnDropDown_Spares_system);
                                                appInd.waitFor(obrowser, "All_spare", "element", "", 5000);
                                                //##################################################################################
                                                //Click on Projects
                                                By byClickOnProject_List = appInd.createAndGetByObject("Project_List");
                                                Thread.sleep(3000);
                                                WebElement elementw = obrowser.findElement(byClickOnProject_List);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
                                                //###################################################################################
                                                //Click on create new project
                                                Thread.sleep(3000);
                                                By byClickOnNew_Project = appInd.createAndGetByObject("New_Project");
                                                 elementq = obrowser.findElement(byClickOnNew_Project);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
                                                appInd.waitFor(obrowser, "Select", "element", "", 5000);
                                                //#################################################################################
                                                //Select System
                                                By Select_System = appInd.createAndGetByObject("Select_System");
                                                WebElement ele = obrowser.findElement(Select_System);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
                                                appInd.waitFor(obrowser, "Wait_for_system", "element", "", 5000);
                                                //for (int j = 1; j < oinpuTDtMap.size(); j++) {
                                                        boolean flag = true;
                                                        int j = 1;
                                                        String p;
                                                        String xp = null;
                                        //                String system = "Param_" + j;
                                                        try {
                                                                while (flag) {
                                                                        xp = "//*[@id=\"project-step2\"]/div[4]/div[1]/div[1]/ul/li[" + j
                                                                                        + "]/div/label";
                                                                        System.out.println("select" + obrowser.findElement(By.xpath(xp)).getText());

                                                                        p = obrowser.findElement(By.xpath(xp)).getText();
                                                                        if (p.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))
                                                                                flag = false;
                                                                        j++;
                                                                }
                                                                ele = obrowser.findElement(By.xpath(xp));
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", ele);
                                                        } catch (Exception e) {

                                                        }
                                        //#########################################################################################
                                                        //Click and enter project name
                                                By byclickOnClick_Pname = appInd.createAndGetByObject("Click_Pname");
                                                Thread.sleep(3000);
                                                WebElement elementi = obrowser.findElement(byclickOnClick_Pname);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementi);
                                                appInd.waitFor(obrowser, "Select", "element", "", 5000);
                                                strStatus += String
                                                                .valueOf(appInd.setObject(obrowser, "Click_Pname", oinpuTDtMap.get("Param_2")));
                                                appInd.waitFor(obrowser, "Select", "element", "", 1000);
                                                //###########################################################################################
                                                //Click and enter deescription
                                                By byclickOnClick_Description = appInd.createAndGetByObject("Click_Description");
                                                Thread.sleep(3000);
                                                WebElement elementa = obrowser.findElement(byclickOnClick_Description);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementa);
                                                strStatus += String.valueOf(appInd.setObject(obrowser, "Click_Description", "abcf"));// description
                                                appInd.waitFor(obrowser, "Select", "element", "", 5000);
                                                //#####################################################################################
                                                //Click on OK button
                                                //strStatus += String.valueOf(appInd.setObject(obrowser, "Create_Project_name", "E"));

                                                By byClickOnCreate_Project_name = appInd.createAndGetByObject("Create_Project_name");
                                                WebElement elementh = obrowser.findElement(byClickOnCreate_Project_name);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
                                                
                                                By created_project = appInd.createAndGetByObject("Created_project");
                                                Thread.sleep(10000);
                                        String projectName = obrowser.findElement(created_project).getText();
                                                if (projectName.contains(oinpuTDtMap.get("Param_2"))) {
                                                        strStatus += true;
                                                } 
                                                
                                                byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
                                        
                                                 Object element11 = obrowser.findElement(byclickOnLogout_btn_OK);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element11);
                                                Thread.sleep(5000);
                                                //############################################################################################
                                                //Login with engineer to reserve the channel
                                                strStatus += String.valueOf(
                                                                appInd.setObject(obrowser, "Login_txtbx_Username1", oinpuTDtMap.get("Param_3")));
                                                
                                                Thread.sleep(10000);
                                                // Set the Password value
                                                strStatus += String.valueOf(
                                                                appInd.setObject(obrowser, "Login_txtbx_Password1", oinpuTDtMap.get("Param_4")));
                                                // click on the ok button
                                                 byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK1");
                                         elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
                                                
                                                 result=        appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
                                                //################################################################################################
                                                //Thread.sleep(20000);
                                                byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
                                                obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
                                                Thread.sleep(1000);
                                                obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
                                                Thread.sleep(1000);
                                
                                        String dropDown=null;
                                        for (int row = 1;; row++) {
                                                try {
                                                        By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
                                                        WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
                                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                        elementbySelect_DropDown);
                                                         dropDown = obrowser
                                                                        .findElement(By.xpath(
                                                                                        "//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
                                                                        .getText();
                                                         System.out.println("dropdowm"+dropDown);
                                                
                                        if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))
                                                                                                        
                                        {                                                        WebElement element = obrowser.findElement(
                                                        By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
                                                        
                                                        strStatus+=true;
                                                        break;
                                                        }
                                         bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
                                        elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
                                        }
                                        
                                                catch(Exception e)
                                                        {strStatus+=false;
                                                        appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                                        + "\\Results\\snapshot\\ReserveChannel\\TC_ReserveaChannel_invalidCredential_Fail_snapshot.png");
                                                        appInd.writeLog("Fail", "'TC_ReserveaChannel' script was failed");
                                                        }
                                        }        
                                                        
                                                        Thread.sleep(10000);                                                //##############################################################################
                                                        //Select free channel

                                                        By byclickOnFree_Channel_Count = appInd.createAndGetByObject("Free_Channel_Count");
                                                        System.out.println("byclickOnFree_Channel_Count::::"+ byclickOnFree_Channel_Count);
                                                        WebElement elebyclickOnFree_Channel_Count = obrowser.findElement(byclickOnFree_Channel_Count);
                                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elebyclickOnFree_Channel_Count);
                                                         result=        appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
                                                         By rchannelCount = appInd.createAndGetByObject("R_Channel_Count");
                                                         totalfcount= Integer.parseInt(obrowser.findElement(rchannelCount).getText());
                                                                                                        try {        
                                                                for(int k=1;k<=3;k++) {
                                                                        WebElement elementSelectbyDropdowng = obrowser.findElement(
                                                                                        By.xpath("//*[@id='defectMainTableRows']/tr["+k+"]/td[1]/div/label/span"));
                                                                                           ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                                                        elementSelectbyDropdowng);        
                                                                }
                                                                //###################################################################################
                                                                //Click on Actions
                                                                By byclickOnAction_Filter = appInd.createAndGetByObject("Action_Filter");
                                                                Thread.sleep(3000);
                                                                WebElement elementc = obrowser.findElement(byclickOnAction_Filter);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
                                                                //######################################################################################
                                                                //Select Reserve
                                                                By byclickOnSelect_Reserve = appInd.createAndGetByObject("Select_Reserve");
                                                                Thread.sleep(3000);
                                                                WebElement element3 = obrowser.findElement(byclickOnSelect_Reserve);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
                                                                //######################################################################################
                                                                
                                                                //Select project
                                                                
                                                                //Select_Project_dropdown
                                                         byclickOnSelect_Reserve = appInd.createAndGetByObject("Select_Project_dropdown");
                                                                Thread.sleep(3000);
                                                                 element3 = obrowser.findElement(byclickOnSelect_Reserve);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
                                                                String pName=null;
                                                                List<WebElement> project=obrowser.findElements(By.cssSelector("#frmAssignment > div > div.lsspt-lightbox.unacknowledge > div.clearfix.mb15 > div > div.dropdown-menu.open > ul > li > a > span.text"));
                                                            System.out.println(project.size());
                                                            Iterator<WebElement> itr = project.iterator();
                                                            WebElement we=null;
                                                                while(itr.hasNext())
                                                                {
                                                                        we=itr.next();
                                                                 pName=we.getText();
                                                                System.out.println("select" +pName);
                                                                if(pName.equalsIgnoreCase(oinpuTDtMap.get("Param_2")))
                                                                                {
                                                                         
                                                                                           ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",we);        
                                                                                   break;
                                                                                }
                                                                /* byclickOnSelect_Reserve = appInd.createAndGetByObject("Select_Project_dropdown");
                                                                 element3 = obrowser.findElement(byclickOnSelect_Reserve);
                                                                        ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);*/
                                                                        
                                                                }
                                                                        /*By byclickOnSelect_Project = appInd.createAndGetByObject("Select_Project");
                                                                WebElement element4 = obrowser.findElement(byclickOnSelect_Project);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element4);
                                                                
                                                                strStatus += strStatus += String.valueOf(
                                                                                appInd.setObject(obrowser, "Projectname_for_Reserve", oinpuTDtMap.get("Param_2")));*/
                                                                //####################################################################################
                                                                //Enter comment
                                                                By byclickOnEnter_comment = appInd.createAndGetByObject("Enter_comment");
                                                                Thread.sleep(3000);
                                                                WebElement elementb = obrowser.findElement(byclickOnEnter_comment);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
                                                                appInd.setObject(obrowser, "Enter_comment", "abcabcz");
                                                                appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
                                                                //###################################################################################
                                                                //Click on OK button
                                                                 byclickOnEnter_comment = appInd.createAndGetByObject("Reserve_OK");
                                                                Thread.sleep(3000);
                                                         elementb = obrowser.findElement(byclickOnEnter_comment);
                                                                ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
                                                                appInd.setObject(obrowser, "Enter_comment", "abcabcz");
                                                                appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
                                                                //strStatus += String.valueOf(appInd.setObject(obrowser, "Reserve_OK", "E"));
                                                                                                
                                        
                                                            } catch (Exception e) {
                                                                    appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                                                        + "\\Results\\snapshot\\ReserveChannel\\Reserve.png");
                                                                        appInd.writeLog("Fail", "'TC_ReserveaChannel' script was failed");
                                                                        bolstatus = false;
                                                                        strStatus+=false;
                                                                        break;
                                                                }
                                        //        }   
                                                         rchannelCount = appInd.createAndGetByObject("R_Channel_Count");
                                                        Integer totalcommonFRUchannelCountValue = Integer.parseInt(obrowser.findElement(rchannelCount).getText());
                                                        totalfcount+=3;
                                                        if(totalcommonFRUchannelCountValue==totalfcount)
                                                                strStatus+=true;
                                                        else
                                                                strStatus+=false;
                                                                
                                                // ########################################################################
                                                if (strStatus.contains("false")) {
                                                       
                                                        appInd.writeLog("Fail", "'TC_ReserveaChannel' script was failed");
                                                        bolstatus = false;
                                                        strStatus = null;
                                                } else  {
                                                        appInd.writeLog("Pass", "'TC_ReserveaChannel' script was Successful");
                                                        bolstatus = true;
                                                        strStatus = null;
                                                }
                                        }
                                } else {
                                        appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                        + "\\Results\\snapshot\\TC_ReserveaChannel\\Failed_to_launch_IE_browser_snapshort.png");
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
                                //}
                                strcurrentTD = String.valueOf(TD);
                                oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
                                oinpuTDtMap = null;
                        } // for loop
                        return oinputMap;
                                }
                        } catch (Exception e) {
                        appInd.writeLog("Exception", "Exception while executing 'TC_ReserveaChannel' method. " + e.getMessage());
                        oinpuTDtMap.put("result", "Fail");
                        //oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
                        return oinputMap;
                } finally {
                        System.out.println("end");
                        return oinputMap;
                        }
                
                }


		
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, HashMap> TC_ParameterQuery_Save_Query() {
            Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
            Map<String, String> oinpuTDtMap = new HashMap<String, String>();
            String strProjectName = null;
            String dropDown = null;
           
            try {
                    appInd.writeLog("#", "****TC_ParameterQuery_Save_Query:- started*****");
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
                                            // click on menu bar and click on spare option

                                    
                                                    By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
                                                    obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
                                                    Thread.sleep(1000);
                                                    obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
                                                    Thread.sleep(1000);
                                    

                                            // ########################################################################
                                            strProjectName = oinpuTDtMap.get("Param_1");
                                            WebElement elementbyclickChannel = null;
                                            for (int row = 1;; row++) {
                                                    try {

                                                            if (row > 1) {
                                                                    By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
                                                                    WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
                                                                    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                                    elementbySelect_DropDown);
                                                            }
                                                            By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
                                                            WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
                                                            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                            elementbySelect_DropDown);

                                                            dropDown = obrowser.findElement(By.xpath(
                                                                            "//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row
                                                                                            + "]/a"))
                                                                            .getText();
                                                            if (strProjectName.equalsIgnoreCase(dropDown)) {
                                                                    elementbyclickChannel = obrowser.findElement(By.xpath(
                                                                                    "//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li["
                                                                                                    + row + "]/a"));
                                                                    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                                    elementbyclickChannel);
                                                                    Thread.sleep(2000);
                                                                    break;
                                                            }
                                                    } catch (Exception e) {
                                                            break;
                                                    }
                                            }
                                            
                                            if(elementbyclickChannel != null) {
                                            if (oinpuTDtMap.get("Param_2") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
                                            }
                                            if (oinpuTDtMap.get("Param_3") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
                                            }
                                            if (oinpuTDtMap.get("Param_4") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
                                            }
                                            if (oinpuTDtMap.get("Param_5") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
                                            }
                                            if (oinpuTDtMap.get("Param_6") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
                                            }
                                            if (oinpuTDtMap.get("Param_7") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
                                            }
                                            if (oinpuTDtMap.get("Param_8") != null) {
                                                    appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
                                            }
                                            if (oinpuTDtMap.get("Param_9") != null) {
                                                    appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
                                            }
                                            
                                            WebElement elementbyclickdeseable = obrowser.findElement(By.xpath(
                                                            "//*[@id='parameter-query']/section/div[2]/div[3]/div/div[2]/div/button[1]"));
                                            
                                            if(!elementbyclickdeseable.isEnabled()) {
                                                    strStatus += false;
                                                    appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                            + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\SAVE_SaveFailed_snapshot.png");
                                            }
                                            else {
                                                    By click_on_run_query = appInd.createAndGetByObject("Click_on_Save_query");
                                                    WebElement elementbyclick_on_run_query = obrowser
                                                                    .findElement(click_on_run_query);
                                                    ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                    elementbyclick_on_run_query);
                                                    
                                                    String paramValue = oinpuTDtMap.get("Param_8");

                                                    try {
                                                            By byclick_and_check_Save_Filter = appInd
                                                                            .createAndGetByObject("Click_on_Filter_checksave_Button");
                                                            WebElement elementbyclick_and_check_Save_Filter = obrowser
                                                                            .findElement(byclick_and_check_Save_Filter);
                                                            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
                                                                            elementbyclick_and_check_Save_Filter);

                                                            List<WebElement> rows = obrowser
                                                                            .findElements(By.xpath("//*[@data-ng-click='applyQuery(query)']"));

                                                            for (WebElement rowElement : rows) {
                                                                    String strrowElementarr[] = rowElement.getText().split("\\n");
                                                                    System.out.println("strrowElementarr[0]::" + strrowElementarr[0]);
                                                                    if (strrowElementarr[0].equals(paramValue)) {
                                                                              strStatus += true;
                                                                    }

                                                            }

                                                         } catch (Exception e) {
                                                            strStatus += "false";
                                                            Thread.sleep(5000);
                                                            appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                                            + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\SAVE_SaveFailed_snapshot.png");

                                                            obrowser.getWindowHandles();
                                                    }
                                            }
                                                    
                                            }
                                            else {
                                                    strStatus += "false";
                                                    appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                                            + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\SAVE_SaveFailed_snapshot.png");
                                            }
                    //###################################                        
                                            if (strStatus.contains("false")) {
                                                    appInd.writeLog("Fail", "'TC_ParameterQuery_Save_Query' script was failed");
                                                    bolstatus = false;
                                                    strStatus = null;
                                            } else if (strStatus.contains("true")) {
                                                    appInd.writeLog("Pass", "'TC_ParameterQuery_Save_Query' script was Successful");
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
                    appInd.writeLog("Exception",
                                    "Exception while executing 'TC_ParameterQuery_Save_Query' method. " + e.getMessage());
                    oinpuTDtMap.put("result", "Fail");
                    oinputMap.put("result", (HashMap) oinpuTDtMap);
                    return oinputMap;
            }
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_Edit_and_Save() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strProjectName = null;
		String dropDown = null;
		String streditSave = null;
		try {
			appInd.writeLog("#", "****TC_ParameterQuery_Edit_and_Save:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				String flagValue = "false";
				int k = 0;
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// click on menu bar and click on spare option
							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
						// ########################################################################
						strProjectName = oinpuTDtMap.get("Param_1");
						streditSave = oinpuTDtMap.get("Param_10");
						WebElement elementbyclickChannel = null;
						for (int row = 1;; row++) {
							try {
								if (row > 1) {
									By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
									WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbySelect_DropDown);
								}
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
								dropDown = obrowser.findElement(By.xpath(
										"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row
												+ "]/a"))
										.getText();
								if (strProjectName.equalsIgnoreCase(dropDown)) {
									 elementbyclickChannel = obrowser.findElement(By.xpath(
											"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li["
													+ row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannel);
									Thread.sleep(2000);
									break;
								}
							} catch (Exception e) {
								break;
							}
						}
						if(elementbyclickChannel != null) {
							By click_on_Filter_checksave_Button = appInd
									.createAndGetByObject("Click_on_Filter_checksave_Button");
							WebElement elementclick_on_Filter_checksave_Button = obrowser
									.findElement(click_on_Filter_checksave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementclick_on_Filter_checksave_Button);

							List<WebElement> rows = obrowser
									.findElements(By.xpath("//*[@data-ng-click='applyQuery(query)']"));
							// ======================
							for (WebElement rowElement : rows) {
								k++;
								String strrowElementarr[] = rowElement.getText().split("\\n");
								if (strrowElementarr[0].equalsIgnoreCase(streditSave)) {
									flagValue = "true";
									WebElement click_on_Edit_Button = obrowser
											.findElement(By.xpath("//*[@id='saved-filters']/li[" + k + "]/span/a[1]"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											click_on_Edit_Button);
								}
							}
							if (flagValue.contains("false")) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                        + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Edit_and_Save.png");
							} else {
								strStatus += true;
							}
							if(strStatus.contains("true")) {
							if (oinpuTDtMap.get("Param_2") != null) {
								appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
							}
							if (oinpuTDtMap.get("Param_3") != null) {
								appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
							}
							if (oinpuTDtMap.get("Param_4") != null) {
								appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
							}
							if (oinpuTDtMap.get("Param_5") != null) {
								appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
							}
							if (oinpuTDtMap.get("Param_6") != null) {
								appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
							}
							if (oinpuTDtMap.get("Param_7") != null) {
								appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
							}
							if (oinpuTDtMap.get("Param_8") != null) {
								appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
							}
							if (oinpuTDtMap.get("Param_9") != null) {
								appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
							}
                           	Thread.sleep(2000);
							WebElement elementbyclickdeseable = obrowser.findElement(By.xpath(
									"//*[@id='parameter-query']/section/div[2]/div[3]/div/div[2]/div/button[2]"));
							if(!elementbyclickdeseable.isEnabled()) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                        + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Edit_and_Save.png");
							}
							else {
							try {
								By click_on_Save_as = appInd.createAndGetByObject("Click_on_Save_as");
								WebElement elementclick_on_Save_as = obrowser.findElement(click_on_Save_as);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementclick_on_Save_as);
								obrowser.getWindowHandles();
							} catch (Exception e) {
								System.out.println("EXception");
							}
							try {
								By click_on_Filter = appInd.createAndGetByObject("Click_on_Filter_checksave_Button");
								WebElement elementclickclick_on_Filter = obrowser.findElement(click_on_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementclickclick_on_Filter);
								List<WebElement> rownumber = obrowser
										.findElements(By.xpath("//*[@data-ng-click='applyQuery(query)']"));
								for (WebElement rowElement1 : rownumber) {
									String strElementarr[] = rowElement1.getText().split("\\n");
									System.out.println("strElementarr::" + strElementarr);
									if (strElementarr[0].equalsIgnoreCase(oinpuTDtMap.get("Param_8"))) {
										strStatus += true;
									}
								}
							} catch (Exception e) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                        + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Edit_and_Save.png");
							}
							}
						}
							}
						else {
							strStatus += false;
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Edit_and_Save.png");
						}
						// ####################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_Edit_and_Save' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_Edit_and_Save' script was Successful");
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
			System.out.println("oinputMap.toString :::::::::::" + oinputMap);
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_ParameterQuery_Edit_and_Save' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_DeleteQuery() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strProjectName = null;
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_ParameterQuery_DeleteQuery:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				String flagValue = null;
				int k = 0;
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// click on menu bar and click on spare option
							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
						// ########################################################################
						strProjectName = oinpuTDtMap.get("Param_1");
						WebElement elementbyclickChannels = null;
						for (int row = 1;; row++) {
							if (row > 1) {
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);
							}
							By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementbySelect_DropDown);

							dropDown = obrowser.findElement(
									By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li["
											+ row + "]/a"))
									.getText();
							if (strProjectName.equalsIgnoreCase(dropDown)) {
								 elementbyclickChannels = obrowser.findElement(By.xpath(
										"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row
												+ "]/a"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclickChannels);
								Thread.sleep(2000);
								break;
							}
						}
						if(elementbyclickChannels != null) {
							By click_on_Filter_checksave_Button = appInd
									.createAndGetByObject("Click_on_Filter_checksave_Button");
							WebElement elementclick_on_Filter_checksave_Button = obrowser
									.findElement(click_on_Filter_checksave_Button);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementclick_on_Filter_checksave_Button);
						List<WebElement> rownumber = obrowser
									.findElements(By.xpath("//*[@data-ng-click='applyQuery(query)']"));
							for (WebElement rowElement1 : rownumber) {
	                             k++;
								String strElementarr[] = rowElement1.getText().split("\\n");
								if (strElementarr[0].equals(oinpuTDtMap.get("Param_2"))) {
								 WebElement elementbyclickChannel = obrowser.findElement(By.xpath(
											"//*[@id='saved-filters']/li["+k+"]/span/a[2]"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannel);
								}
								}
							 WebElement elementbyclickChannel = obrowser.findElement(By.xpath(
										"//*[@id='abort-collection-confirm']"));
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclickChannel);
							
							List<WebElement> rownumber2 = obrowser
									.findElements(By.xpath("//*[@data-ng-click='applyQuery(query)']"));
					         for (WebElement rowElement1 : rownumber2) {
	                            k++;
								String strElementarr[] = rowElement1.getText().split("\\n");
								if (strElementarr[0].equals(oinpuTDtMap.get("Param_2"))) {
									strStatus += false;
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	                                        + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_DeleteQuery.png");
								}
								else {
									strStatus += true;
								}
							}
						}
						else {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_DeleteQuery.png");
						}
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_DeleteQuery' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_DeleteQuery' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_ParameterQuery_DeleteQuery' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}		

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_Expression_syntex_validation() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strProjectName = null;
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_ParameterQuery_Expression_syntex_validation:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;

			oinputMap = appInd.getInputData(strTCID);
			System.out.println("oinputMap.tostring::" + oinputMap.toString());
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// click on menu bar and click on spare option

							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
						// ########################################################################
						strProjectName = oinpuTDtMap.get("Param_1");
						WebElement elementbyclickChannels = null;
						for (int row = 1;; row++) {
							try {
								if (row > 1) {
									By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
									WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbySelect_DropDown);
								}
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);

								dropDown = obrowser.findElement(By.xpath(
										"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row
												+ "]/a"))
										.getText();
								System.out.println("dropDown::" + dropDown);

								if (strProjectName.equalsIgnoreCase(dropDown)) {
									elementbyclickChannels = obrowser.findElement(By.xpath(
											"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li["
													+ row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannels);
									Thread.sleep(2000);
									break;
								}
							} catch (Exception e) {
								break;
							}
						}
						
			           if(elementbyclickChannels != null){	
			        	if (oinpuTDtMap.get("Param_2") != null) {
							appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
						}
						if (oinpuTDtMap.get("Param_3") != null) {
							appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
						}
						if (oinpuTDtMap.get("Param_4") != null) {
							appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
						}
						if (oinpuTDtMap.get("Param_5") != null) {
							appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
						}
						if (oinpuTDtMap.get("Param_6") != null) {
							appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
						}
						if (oinpuTDtMap.get("Param_7") != null) {
							appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
						}
						if (oinpuTDtMap.get("Param_8") != null) {
							appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
						}
						if (oinpuTDtMap.get("Param_9") != null) {
							appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
						}
		             	Thread.sleep(2000);
						WebElement elementbyclickdeseable = obrowser.findElement(By.xpath(
								"//*[@id='parameter-query']/section/div[2]/div[3]/div/div[2]/div/button[1]"));
												
						if(!elementbyclickdeseable.isEnabled()) {
							strStatus += false;
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Expression_syntax_validation.png");
						}
						else {
							By click_on_validate_query = appInd.createAndGetByObject("Click_on_run_query");
							WebElement elementbyclick_on_run_query = obrowser
									.findElement(click_on_validate_query);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementbyclick_on_run_query);
							
							try {
								int count;
								start = System.currentTimeMillis();
								for(count = 1;;count++) {
									//WebElement ele = obrowser.findElement(By.xpath("//span[text()='Query execution in progress']"));
									By executionInProgress = appInd.createAndGetByObject("Query_Execution_In_Progress");
									WebElement ele = obrowser.findElement(executionInProgress);
									if(ele.isDisplayed()) {
										System.out.println("Query execution in progress: "+count);
									}
								}

	
							}catch(Exception e) {
								System.out.println("Result is displayed");
								end = System.currentTimeMillis();
								System.out.println("start"+ start);
								System.out.println(end);
								totalTime = (end - start)/1000;
								System.out.println("Total Time for getting the result - "+totalTime+" Second");
								newTotalTime = String.valueOf(totalTime)+" Seconds";
										
								if (newTotalTime!=null) {
									strStatus += true;
									try {
									WebElement elementNoResultFound = obrowser.findElement(By.xpath("//*[@id='tableResults']/tbody/tr[1]/td[1]/span"));
									}catch(Exception ex) {
										strStatus += false;	
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
			                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Expression_syntax_validation.png");
									}
								} else {
									strStatus += false;
									appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
		                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Expression_syntax_validation.png");
								}
								By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
								WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
								
							}
						}
						}
			           else {
			        	   strStatus += false;
			        	   appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                   + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_Expression_syntax_validation.png");
			           }
			//###################################			
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_Expression_syntex_validation' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_Expression_syntex_validation' script was Successful");
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
			appInd.writeLog("Exception",
					"Exception while executing 'TC_ParameterQuery_Expression_syntex_validation' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_validationResultPage() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strProjectName = null;
		String dropDown = null;
		try {
			appInd.writeLog("#", "****TC_ParameterQuery_validationResultPage:- started*****");
			obrowser = oDriver.getWebDriver();
			boolean bolstatus = false;
			String strExecutionsts = null;
			oinputMap = appInd.getInputData(strTCID);
			System.out.println("oinputMap.tostring::" + oinputMap.toString());
			for (int TD = 1; TD <= oinputMap.size(); TD++) {
				oinpuTDtMap = oinputMap.get(String.valueOf(TD));
				if ((obrowser != null)) {
					// Read the Execution Status
					strExecutionsts = oinpuTDtMap.get("Executestatus");
					if (strExecutionsts.equalsIgnoreCase("yes")) {
						// ########################################################################
						// click on menu bar and click on spare option
							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
							Thread.sleep(1000);
							//click_on_page_open_button
							WebElement elementbyclick = obrowser.findElement(By.xpath(
									"//*[@id='expansionRow']/a"));
							if(elementbyclick != null) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick);	
							}
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementbyclick);	
							// ########################################################################
						strProjectName = oinpuTDtMap.get("Param_1");
						WebElement elementbyclickChannels = null;
						for (int row = 1;; row++) {
							try {
								if (row > 1) {
									By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
									WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbySelect_DropDown);
								}
								By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
								WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_DropDown);

								dropDown = obrowser.findElement(By.xpath(
										"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row
												+ "]/a"))
										.getText();
								if (strProjectName.equalsIgnoreCase(dropDown)) {
									 elementbyclickChannels = obrowser.findElement(By.xpath(
											"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li["
													+ row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
											elementbyclickChannels);
									Thread.sleep(2000);
									break;
								}
							} catch (Exception e) {
								break;
							}
						}
						if(elementbyclickChannels != null) {
							if (oinpuTDtMap.get("Param_2") != null) {
								appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
							}
							if (oinpuTDtMap.get("Param_3") != null) {
								appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
							}
							if (oinpuTDtMap.get("Param_4") != null) {
								appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
							}
							if (oinpuTDtMap.get("Param_5") != null) {
								appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
							}
							if (oinpuTDtMap.get("Param_6") != null) {
								appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
							}
								if (oinpuTDtMap.get("Param_7") != null) {
								appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
							}
							if (oinpuTDtMap.get("Param_8") != null) {
								appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
							}
							if (oinpuTDtMap.get("Param_9") != null) {
 								appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
							}
							WebElement elementbyclickdeseable= null;
							Thread.sleep(2000);

							try {
							 elementbyclickdeseable = obrowser.findElement(By.xpath(
									"//button[@data-ng-click='validate()' and @class='btn btn-flatblue customValidate validate mr0']"));
							}catch(Exception e) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
							}
							
							try {
							if(!elementbyclickdeseable.isEnabled()) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
							}
							else {
								By click_on_validate_query = appInd.createAndGetByObject("Click_on_validate_query");
								WebElement elementbyclick_on_run_query = obrowser
										.findElement(click_on_validate_query);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbyclick_on_run_query);
										
								try {
									int count;
									start = System.currentTimeMillis();
									for(count = 1;;count++) {
										//WebElement ele = obrowser.findElement(By.xpath("//span[text()='Query execution in progress']"));
										By executionInProgress = appInd.createAndGetByObject("Query_Validation_In_Progress");
										WebElement ele = obrowser.findElement(executionInProgress);
										if(ele.isDisplayed()) {
											System.out.println("Query validation in progress: "+count);
										}
									}
								}catch(Exception e) {
									System.out.println("Result is displayed");
									end = System.currentTimeMillis();
									System.out.println("start"+ start);
									System.out.println(end);
									totalTime = (end - start)/1000;
									System.out.println("Total Time for getting the result - "+totalTime+" Second");
									newTotalTime = String.valueOf(totalTime)+" Seconds";
										if (newTotalTime!=null) {
										strStatus += true;
										try {
										WebElement elementNoResultFound = obrowser.findElement(By.xpath("//*[@id='table']/tbody/tr[1]/td[3]/span"));
										if(elementNoResultFound != null) {
											System.out.println("elementNoResultFound::" +elementNoResultFound);
											strStatus += false;
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
				                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
										}
										}catch(Exception ex) {
											strStatus += true;	
										}
									} else {
										strStatus += false;
										appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
			                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
									}
									By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
									WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
							}
							}
							}catch(Exception e) {
								strStatus += false;
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
							}
							}
						else {
							strStatus += false;
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
                                    + "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\TC_ParameterQuery_validationResultPage.png");
						}
						//###################################			
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_validationResultPage' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_validationResultPage' script was Successful");
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
				System.out.println("oinputMap::" +oinputMap.toString());
				System.out.println("oinpuTDtMap::" +oinpuTDtMap.toString());
				oinpuTDtMap = null;
			} // for loop
			return oinputMap;
		} catch (Exception e) {
			appInd.writeLog("Exception",
					"Exception while executing 'TC_ParameterQuery_validationResultPage' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}

		//=======================RajaShree

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_LSS_PT_server_responsetime () {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String systemName = null;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_LSS_PT_server_responsetime:- started*****");
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
									// click on menu bar and click on Query option
						
//						By Home_Icon = appInd.createAndGetByObject("Home_Icon");
//						WebElement home_Icon = obrowser.findElement(Home_Icon);
//						
//						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",home_Icon);
//						Thread.sleep(5000);
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
							System.out.println(system_elements.size());
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(3000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										//===============
										
										try {
											start = System.currentTimeMillis();
											for(count = 1;;count++) {
												//WebElement ele = obrowser.findElement(By.xpath("//span[text()='Query execution in progress']"));
												By executionInProgress = appInd.createAndGetByObject("Query_Execution_In_Progress");
												WebElement ele = obrowser.findElement(executionInProgress);
												if(ele.isDisplayed()) {
													System.out.println("Query execution in progress: "+count);
												}
											}
											
										}catch(Exception e) {
											System.out.println("Result is displayed");
											end = System.currentTimeMillis();
											System.out.println(start);
											System.out.println(end);
											totalTime = (end - start)/1000;
											System.out.println("Total Time for getting the result - "+totalTime+" Second");
											newTotalTime = String.valueOf(totalTime)+" Seconds";
										}										
										if (newTotalTime!=null) {
											strStatus = "true";
											appInd.writeLog("Pass", "Total Time for page load:" + newTotalTime);
										} else {
											strStatus = "false";
											appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
										}
										//===============		
										Thread.sleep(3000);
										
//										By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
//										WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
//										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
//										System.out.println("Clicked on arrow Button");
//										Thread.sleep(3000);
										
										//break;

						
									}catch(Exception e) {
										System.out.println("***forLoopException***");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\LSS_PT_server_responsetime_Failed.png");
									}
									break;
								}else {
									strStatus = "false";
									appInd.writeLog("Fail", "Total Time for page load:" + newTotalTime);
									//appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\LSS_PT_server_responsetime_Failed.png");
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "'TC_ParameterQuery_LSS_PT_server_responsetime' script was failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\LSS_PT_server_responsetime_Failed.png");
								}
							}catch(Exception e) {
								e.printStackTrace();
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\LSS_PT_server_responsetime_Failed.png");
								//break;
							}
							
							
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_LSS_PT_server_responsetime' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_LSS_PT_server_responsetime' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_LSS_PT_server_responsetime' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_csv_Download() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_csv_Download:- started*****");
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
									// click on menu bar and click on spare option
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										boolean deleteFileStatus = appInd.deleteExistFile(oinpuTDtMap.get("Param_11"), oinpuTDtMap.get("Param_10"), "csv");
										if(deleteFileStatus==true) {
											strStatus = "true";
											System.out.println("Files are deleted");
											appInd.writeLog("pass", "Files are deleted");
										}else {
											System.out.println("Files are not deleted");
											appInd.writeLog("pass", "Files are not deleted");
										}
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(3000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										
										try {
											start = System.currentTimeMillis();
											for(count = 1;;count++) {
												//WebElement ele = obrowser.findElement(By.xpath("//span[text()='Query execution in progress']"));
												By executionInProgress = appInd.createAndGetByObject("Query_Execution_In_Progress");
												WebElement ele = obrowser.findElement(executionInProgress);
												if(ele.isDisplayed()) {
													System.out.println("Query execution in progress: "+count);
												}
											}
											
										}catch(Exception e) {
											System.out.println("Result is displayed");
											Thread.sleep(1000);
											By export_Button = appInd.createAndGetByObject("Query_Export_Button");
											WebElement export_Button_ele = obrowser.findElement(export_Button);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",export_Button_ele);
											System.out.println("Clicked on Export Button");
											Thread.sleep(1000);
											By downloadFileNameEditBox = appInd.createAndGetByObject("Query_OutputFile_Name_EditBox");
											List<WebElement> download_FileName_EditBox = obrowser.findElements(downloadFileNameEditBox);
											if(download_FileName_EditBox.size()>0) {
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",download_FileName_EditBox.get(0));
												System.out.println("Clicked");
												download_FileName_EditBox.get(0).clear();
												System.out.println("Cleared");
												download_FileName_EditBox.get(0).sendKeys(oinpuTDtMap.get("Param_10"));
												System.out.println("Set Values in Edit Box");
												//Thread.sleep(1000);
												By ok_Button = appInd.createAndGetByObject("Query_Ok_button");
												WebElement click_Ok_Button = obrowser.findElement(ok_Button);
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",click_Ok_Button);
												Thread.sleep(180000);
												//appInd.clickAndSaveFileIE(click_Ok_Button);
												System.out.println("Clicked on Ok Button");
												File file = new File(oinpuTDtMap.get("Param_11")+oinpuTDtMap.get("Param_10")+".csv");

												  if(file.exists()){
													  strStatus = "true";
													  System.out.println("File is downloaded successfully");
												  }else{
													  strStatus = "false";
													  System.out.println("File is not downloaded successfully");
												  }
												
											}else {
												strStatus = "false";
												System.out.println("Download file name edit box is not displayed");
											}
											
//											By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
//											WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
//											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
//											System.out.println("Clicked on arrow Button");
//											Thread.sleep(3000);
											Thread.sleep(3000);
											
											//break;
											
										}										

									}catch(Exception e) {
										System.out.println("***forLoopException***");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\csv_Download_Failed.png");
										//break;
									}
									break;
								}else {
									strStatus = "false";
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "csv_Download_Failed script is failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\csv_Download_Failed.png");
								}
							}catch(Exception e) {
								e.printStackTrace();
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\csv_Download_Failed.png");
								//break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_csv_Download' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_csv_Download' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_csv_Download' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_Stop_LongRunQuery() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_Stop_LongRunQuery:- started*****");
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
									// click on menu bar and click on spare option
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(1000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										System.out.println("Clicked on Run Query Button");
										Thread.sleep(1000);
	                                    boolean result=	appInd.waitFor(obrowser, "click_on_arrow", "element", "",600);
										
										boolean flag = appInd.ifElementsPresent(obrowser, "click_on_arrow");
										if(flag==true) {
											By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
											WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
											System.out.println("Clicked on arrow Button");
										}
										try {
											start = System.currentTimeMillis();
											Thread.sleep(500);
											By stop_Button = appInd.createAndGetByObject("Query_Stop_Button");
											WebElement query_Stop_Button = obrowser.findElement(stop_Button);
											//if(System.currentTimeMillis()==end && query_Stop_Button.isDisplayed()) {
											if(query_Stop_Button.isDisplayed()) {
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",query_Stop_Button);
												Thread.sleep(6000);	
												By click_on_rerun = appInd.createAndGetByObject("Query_Rerun_Button");
												WebElement click_on_rerun_Button = obrowser.findElement(click_on_rerun);
												if(click_on_rerun_Button.isDisplayed()) {
													strStatus = "true";
													appInd.writeLog("Pass", "Long Running Query is stopped successfully" + strStatus);
												}else {
													strStatus = "false";
													appInd.writeLog("Fail", "After Clicking on Stop Button Run Query Button is not displayed" + strStatus);
													appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
												}
												
											}else {
												strStatus = "false";
												appInd.writeLog("Fail", "Long Running Query is not stopped successfully" + strStatus);
												appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
											}
																						
										}catch(Exception e) {
											System.out.println("Long Running Query is not stopped successfully");
											strStatus = "false";
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
										}
										Thread.sleep(3000);
										//break;
									}catch(Exception e) {
										System.out.println("***forLoopException***");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
										//break;
									}
									break;
								}else {
									strStatus = "false";
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Stop_LongRunQuery script is failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
								}
							}catch(Exception e) {
								e.printStackTrace();
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Stop_LongRunQuery_Failed.png");
								//break;
							}
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_Stop_LongRunQuery' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_Stop_LongRunQuery' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_Stop_LongRunQuery' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_Rerun_Stopped_Query() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_Rerun_Stopped_Query:- started*****");
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
									// click on menu bar and click on spare option
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(10000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(1000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										System.out.println("Clicked on Run Query Button");
										//Thread.sleep(30000);
										Thread.sleep(1000);
										boolean result=	appInd.waitFor(obrowser, "click_on_arrow", "element", "",600);
										
										boolean flag = appInd.ifElementsPresent(obrowser, "click_on_arrow");
										if(flag==true) {
											By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
											WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
											System.out.println("Clicked on arrow Button");
										}
										
										try {
											start = System.currentTimeMillis();
											Thread.sleep(500);
											By stop_Button = appInd.createAndGetByObject("Query_Stop_Button");
											WebElement query_Stop_Button = obrowser.findElement(stop_Button);
											if(query_Stop_Button.isDisplayed()) {
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",query_Stop_Button);
												Thread.sleep(6000);	
												By click_on_rerun = appInd.createAndGetByObject("Query_Rerun_Button");
												WebElement click_on_rerun_Button = obrowser.findElement(click_on_rerun);
												if(click_on_rerun_Button.isDisplayed()) {
													((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",click_on_rerun_Button);
													Thread.sleep(2000);
													strStatus = "true";	
													appInd.writeLog("Pass", "Clicked on Rerun Query Button" + strStatus);

												}else {
													strStatus = "false";
													appInd.writeLog("Fail", "Unable to click on Rerun Query button" + strStatus);

													appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+"\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
												}
												
											}else {
												strStatus = "false";
												appInd.writeLog("Fail", "Re-run query is failed" + strStatus);
												appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
											}
																						
										}catch(Exception e) {
											System.out.println("Re-run query is failed");
											strStatus = "false";
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
										}								
										Thread.sleep(3000);
									}catch(Exception e) {
										System.out.println("***forLoopException***");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
										//break;
									}
									break;
								} else {
									strStatus = "false";
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "Re-runStoppedQuery script is failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
								} 
							}catch(Exception e) {
								e.printStackTrace();
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\Re-runStoppedQuery_failed.png");
								//break;
							}
							//Thread.sleep(3000);
							
							
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_Rerun_Stopped_Query' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_Rerun_Stopped_Query' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_Rerun_Stopped_Query' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ParameterQuery_ResultPageValidationWithTheNumberOfRecords() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		double pageLoadTime_Seconds=0;
		double totaltime;
		String strProjectName = null;
		try {
			System.out.println("**************parameterQuery***********");
			appInd.writeLog("#", "****TC_ParameterQuery_ResultPageValidationWithTheNumberOfRecords:- started*****");
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
									// click on menu bar and click on spare option
						
						By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						obrowser.findElement(By.linkText("Query")).sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						
						// ########################################################################
						  strProjectName  = oinpuTDtMap.get("Param_1"); 
						  System.out.println(strProjectName);
						  String param9= oinpuTDtMap.get("Param_9");
						  System.out.println("param9::" +param9);
						  
						  By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown_System_Query");
							WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
							Thread.sleep(3000);
							
							By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
							List<WebElement> system_elements = obrowser.findElements(systems);
							
						for(int row = 1;row<=system_elements.size();row++) {
							try {
								WebElement elementbyclickChannel = obrowser.findElement(
										By.xpath("//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"));
								
								String 	dropDown = obrowser
										.findElement(By.xpath(
												"//*[@id='parameter-query']/section/div[1]/ul/li[2]/div/div[1]/div/ul/li[" + row + "]/a"))
										.getText();
								System.out.println("strProjectName::" +strProjectName);

								//if(strProjectName.equalsIgnoreCase("EPKS_NRSRV3") || strProjectName.equalsIgnoreCase("EPKS_NRSRV414")) {
								if(dropDown.equalsIgnoreCase(strProjectName)) {
									try {
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbyclickChannel);
										System.out.println("elementbyclickChannel:: "+ elementbyclickChannel);

										System.out.println("****inside for loop****");
										appInd.clearAndSetObject(obrowser, "Point_Name", oinpuTDtMap.get("Param_2"));
//										appInd.clearAndSetObject(obrowser, "Asset", oinpuTDtMap.get("Param_3"));
//										appInd.clearAndSetObject(obrowser, "Controller", oinpuTDtMap.get("Param_4"));
//										appInd.clearAndSetObject(obrowser, "Output_Type", oinpuTDtMap.get("Param_5"));
//										appInd.clearAndSetObject(obrowser, "Query_String", oinpuTDtMap.get("Param_6"));
										appInd.clearAndSetObject(obrowser, "Output_Parameter_Name", oinpuTDtMap.get("Param_7"));
//										appInd.clearAndSetObject(obrowser, "SaveQuery", oinpuTDtMap.get("Param_8"));
//										appInd.clearAndSetObject(obrowser, "Descriptions", oinpuTDtMap.get("Param_9"));
										//strStatus += "true";
										Thread.sleep(3000);
										By click_on_run_query = appInd.createAndGetByObject("Click_on_run_query");
										WebElement elementbyclick_on_run_query = obrowser.findElement(click_on_run_query);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_run_query);
										
										try {
											start = System.currentTimeMillis();
											for(count = 1;;count++) {
												By executionInProgress = appInd.createAndGetByObject("Query_Execution_In_Progress");
												WebElement ele = obrowser.findElement(executionInProgress);
												if(ele.isDisplayed()) {
													System.out.println("Query execution in progress: "+count);
												}
											}
											
										}catch(Exception e) {
											System.out.println("Result is displayed");
											Thread.sleep(1000);
											WebElement ele = obrowser.findElement(By.xpath("//span[@class='error-no ng-binding ng-scope']"));
											String str = ele.getText();
											System.out.println("str : "+str);
											String objArr[]  = str.split("/");
											System.out.println("Array lenght : "+objArr.length);
											String str1 = objArr[0].trim();
											System.out.println("str1 : "+str1);
											int recordShown = Integer.parseInt(str1);
											System.out.println("RecordShown : "+recordShown);
											String str2 = objArr[1].trim();
											System.out.println("str2 : "+str2);
											int totalRecord = Integer.parseInt(str2);
											System.out.println("TotalRecord : "+totalRecord);
											int noOfTimesToScroll = (totalRecord/recordShown);
											System.out.println("noOfTimesToScroll "+noOfTimesToScroll);
											
											
											for(int i= recordShown;i<totalRecord;) {
												WebElement element = obrowser.findElement(By.xpath("//div[@class='scrollArea ng-scope']/descendant::td[@class='']["+i+"]"));
												((JavascriptExecutor) obrowser).executeScript("arguments[0].scrollIntoView(true);",element);
												Thread.sleep(500);
												i=i+recordShown;
												System.out.println("Size : "+i);
											}
											
											WebElement elen = obrowser.findElement(By.xpath("//span[@class='error-no ng-binding ng-scope']"));
											String strn = elen.getText();
											String objArrn[]  = strn.split("/");
											String str1n = objArrn[0].trim();
											int recordShownn = Integer.parseInt(str1n);
											String str2n = objArrn[1].trim();
											int totalRecordn = Integer.parseInt(str2n);
											if(recordShownn==totalRecordn) {
												strStatus = "true";
												appInd.writeLog("Pass", "Result Page of Run Query is Passed");
											}else {
												strStatus = "false";
												appInd.writeLog("Fail", "Result Page of Run Query is failed");
												appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\ResultPageValidationWithTheNumberOfRecords_failed.png");
											}
											
//											By click_on_arrow = appInd.createAndGetByObject("click_on_arrow");
//											WebElement elementbyclick_on_arrow = obrowser.findElement(click_on_arrow);
//											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbyclick_on_arrow);
//											System.out.println("Clicked on arrow Button");
//											Thread.sleep(3000);
											
										}										
										
									}catch(Exception e) {
										System.out.println("***forLoopException***");
										appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\ResultPageValidationWithTheNumberOfRecords_failed.png");
										//break;
									}
									break;
								} else {
									strStatus = "false";
								}
								if (strStatus.contains("false")) {
									strStatus = "false";
									appInd.writeLog("Fail", "ResultPageValidationWithTheNumberOfRecords script is failed");
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\ResultPageValidationWithTheNumberOfRecords_failed.png");
								}  
							}catch(Exception e) {
								e.printStackTrace();
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")+ "\\Results\\snapshot\\PARAMETER_QUERY_SAVE_FAIL_SNAPSHOT\\ResultPageValidationWithTheNumberOfRecords_failed.png");
								//break;
							}
							//break;
						}
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ParameterQuery_LSS_PT_server_responsetime' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ParameterQuery_LSS_PT_server_responsetime' script was Successful");
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
					"Exception while executing 'TC_ParameterQuery_LSS_PT_server_responsetime' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
		
		/********************************
	 * Method Name : TC_Freeupareservedchannel Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Freeupareservedchannel() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
	
		int totalcommonFRUchannelCountValue = 0;
		String strcommonCountvalue = null;
		try {
				appInd.writeLog("#", "****TC_Freeupareservedchannel:- started*****");
				obrowser = oDriver.getWebDriver();
				boolean bolstatus = false;
				String strExecutionsts = null;
				String strparamvalue = null;
				oinputMap = appInd.getInputData(strTCID);
				for (int TD = 1; TD <= oinputMap.size(); TD++) {
					oinpuTDtMap = oinputMap.get(String.valueOf(TD));
					if ((obrowser != null)) {
						// Read the Execution Status
						strExecutionsts = oinpuTDtMap.get("Executestatus");
						strparamvalue = oinpuTDtMap.get("Param_1");
						System.out.println(strparamvalue);
						if (strExecutionsts.equalsIgnoreCase("yes"))
						{
					//################################################################################################
						
							By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout");
							Thread.sleep(3000);
							WebElement element1 = obrowser.findElement(byclickOnLogout_btn_OK);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
							Thread.sleep(5000);
							
							
								 strStatus += String.valueOf(
											appInd.setObject(obrowser, "Login_txtbx_Username", oinpuTDtMap.get("Param_1")));
									// Set the Password value
									strStatus += String.valueOf(
											appInd.setObject(obrowser, "Login_txtbx_Password", oinpuTDtMap.get("Param_2")));
									// click on the ok button
									By byclickOnLogin_btn_OK1 = appInd.createAndGetByObject("Login_btn_OK");
									WebElement elementq = obrowser.findElement(byclickOnLogin_btn_OK1);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
							 
	                  	
  							Thread.sleep(10000);
  							Thread.sleep(20000);
  							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
  							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
  							Thread.sleep(1000);
  							obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
  							Thread.sleep(1000);
	                         //click on dropdown
  							String dropDown=null;
  							for (int row = 1;; row++) {
  								try {
  									By bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
  									WebElement elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
  									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
  											elementbySelect_DropDown);
  									 dropDown = obrowser
  												.findElement(By.xpath(
  														"//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"))
  												.getText();
  								
  						     	if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_3")))
  															
  							{			WebElement			 element = obrowser.findElement(
  									By.xpath("//*[@id='frmSpare']/div/div/div[1]/div[1]/div/ul/li[" + row + "]/a"));
  							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
  									
  									strStatus+=true;
  									break;
  									}
  							 bySelect_DropDown = appInd.createAndGetByObject("Select_DropDown");
  							elementbySelect_DropDown = obrowser.findElement(bySelect_DropDown);
  							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_DropDown);
  							}
  							
  								catch(Exception e)
  									{strStatus+=false;
  									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
  											+ "\\Results\\snapshot\\ReserveChannel\\TC_ReserveaChannel_invalidCredential_Fail_snapshot.png");
  									appInd.writeLog("Fail", "'TC_ReserveaChannel' script was failed");
  									}
  							}	
  									
								Thread.sleep(3000);
								By rchannelCount = appInd.createAndGetByObject("Reserve_channel_count");
								totalcommonFRUchannelCountValue = Integer.parseInt(obrowser.findElement(rchannelCount).getText());
								System.out.println("rcount"+totalcommonFRUchannelCountValue);
								if(totalcommonFRUchannelCountValue==0)
								{
									strStatus+="false";
								}
								else
								{
								
								By byReserve_channel_count = appInd.createAndGetByObject("Reserve_channel_count");
								System.out.println("byclickOnFree_Channel_Count::::"+ byReserve_channel_count);
								WebElement elebyReserve_channel_count = obrowser.findElement(byReserve_channel_count);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elebyReserve_channel_count);
								boolean jx=oGenericAppmethods.waitForJSandJQueryToLoad(obrowser);
								System.out.println(jx);
								try {	
                                     int countr =3;
                                     if (totalcommonFRUchannelCountValue<countr)
                                    	 countr=totalcommonFRUchannelCountValue;
                                     
									for(int i=1;i<=countr;i++) {
							
										WebElement elementSelectbyDropdowng = obrowser.findElement(
												By.xpath("//*[@id='defectMainTableRows']/tr["+i+"]/td[1]/div/label/span"));
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
														elementSelectbyDropdowng);	
									}
									//#####################################################################################
									//Click on Actions
									By byclickOnAction_Filter = appInd.createAndGetByObject("Action_Filter");
									Thread.sleep(3000);
									WebElement elementc = obrowser.findElement(byclickOnAction_Filter);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
									Thread.sleep(5000);
									//######################################################################################
									//Select FREEUP
									By byclickOnSelect_FreeUp = appInd.createAndGetByObject("Select_FreeUp");
									Thread.sleep(3000);
									WebElement element3 = obrowser.findElement(byclickOnSelect_FreeUp);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
									//######################################################################################
									//click on yes
								 byclickOnSelect_FreeUp = appInd.createAndGetByObject("Click_On_PopUp_Ok_Button");
									Thread.sleep(3000);
									 element3 = obrowser.findElement(byclickOnSelect_FreeUp);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
									
                                 //##############################################################################################
								//Enter comment	
									By byclickOnFreeUp_Comment = appInd.createAndGetByObject("FreeUp_Comment");
									Thread.sleep(3000);
									WebElement elementb = obrowser.findElement(byclickOnFreeUp_Comment);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
									appInd.setObject(obrowser, "Enter_comment", "abcabcz");
									appInd.waitFor(obrowser, "All_spare", "element", "", 10000);
									Thread.sleep(5000);
									//#################################################################################
									//Click on OK button
									//strStatus += String.valueOf(appInd.setObject(obrowser, "FreeUP_btn_OK", "E"));
									 byclickOnFreeUp_Comment = appInd.createAndGetByObject("FreeUP_btn_OK");
									Thread.sleep(3000);
									 elementb = obrowser.findElement(byclickOnFreeUp_Comment);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
								}catch(Exception e) {
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\FreeupChannel\\clickfail.png");
									appInd.writeLog("Fail", "'TC_Freeupareservedchannel' script was failed");
									bolstatus = false;
									strStatus = null;
									
							}
								}
								
								    
													
							// ########################################################################
							if (strStatus.contains("false")) {
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\TC_Freeupareservedchannel_invalidCredential_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_Freeupareservedchannel' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_Freeupareservedchannel' script was Successful");
								bolstatus = true;
								strStatus = null;
							}
						}
					 else {
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
					//}
					strcurrentTD = String.valueOf(TD);
					oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
					oinpuTDtMap = null;
				} // for loop
				return oinputMap;
					}
				}
				} catch (Exception e) {
				appInd.writeLog("Exception", "Exception while executing 'TC_Freeupareservedchannel' method. " + e.getMessage());
				oinpuTDtMap.put("result", "Fail");
				//oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
				return oinputMap;
			} finally {
				System.out.println("end");
				return oinputMap;
				}
	}
			
	public Map<String, HashMap> performance_Tag_reference() {
			Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
			Map<String, String> oinpuTDtMap = new HashMap<String, String>();
			double pageLoadTime_Seconds=0;
			double totaltime;
			String	strcommonCountvalue=null;
			
			String strcurrentTD = null;
			try {
				appInd.writeLog("#", "****performance_Tag_reference:- started*****");
				obrowser = oDriver.getWebDriver();
				boolean bolstatus = false;
				String strExecutionsts = null;
				oinputMap = appInd.getInputData(strTCID);
				for (int TD = 1; TD <= oinputMap.size(); TD++) {
					oinpuTDtMap = oinputMap.get(String.valueOf(TD));

					if ((obrowser != null)) {
						// Read the Execution Status
						strExecutionsts = oinpuTDtMap.get("Executestatus");
						if (strExecutionsts.equalsIgnoreCase("yes"))

						{
							// ########################################################################
							try
							{// click on Menubar and Search bar
							
							By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(3000);
							obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
							boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
							By byClickOnSearchBar = appInd.createAndGetByObject("Search_bar");
							WebElement element = obrowser.findElement(byClickOnSearchBar);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
							Thread.sleep(3000);
							// ########################################################################
							                    // click on check box and tag search bar
							 byClickOnSearchBar = appInd.createAndGetByObject("snapshot_id");
								element = obrowser.findElement(byClickOnSearchBar);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
									
							
									List<WebElement> snapshot=obrowser.findElements(By.xpath("//button[@class='btn dropdown-toggle selectpicker btn-default' and @aria-expanded='true']/following-sibling::div/ul[@class='dropdown-menu inner selectpicker']/li/a/span[@class='text']"));
				    System.out.println(snapshot.size());
				    Iterator<WebElement> itr = snapshot.iterator();
				    WebElement we=null;
				   
					while(itr.hasNext()) {
						 we=itr.next();
					 //  System.out.println(we.getText());
						
					}
					Thread.sleep(30000);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",we);
										Thread.sleep(3000);
								
							
							System.out.println(oinpuTDtMap.get("Param_1"));
							By byclick_On_CheckBox = appInd.createAndGetByObject("CheckBox_Tag_Reference");
							WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementbyclick_On_CheckBox);
							obrowser.findElement(byclick_On_CheckBox).clear();
							obrowser.findElement(byclick_On_CheckBox).sendKeys(oinpuTDtMap.get("Param_1"));
						
							Thread.sleep(5000);
							 byClickOnMenuBar = appInd.createAndGetByObject("Tag_Search");
							obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
							Thread.sleep(5000);
						        //click on search
							By byClickOnTagSearchBar = appInd.createAndGetByObject("Tag_Reference");
							 elementbyclick_On_CheckBox = obrowser.findElement(byClickOnTagSearchBar);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
									elementbyclick_On_CheckBox);
							
							//Thread.sleep(3000);
													
							
							long time = System.currentTimeMillis();
						
							System.out.println(time);
						
					
								
							
								result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "",300);
								long time1 = System.currentTimeMillis();
															totaltime=time1-time;
							System.out.println(totaltime);
							 pageLoadTime_Seconds = totaltime / 1000;
							 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
								System.out.println(pageLoadTime_Seconds);
								

							
							if (pageLoadTime_Seconds <= 30) {
								strStatus += true;
								appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
							} else {
								strStatus += false;
								appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
							}
						//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
							} catch (Exception e) {
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\Performance\\TagReference.png");
								bolstatus = false;
								strStatus+=false;
								
								appInd.writeLog("Exception",
										"Exception while executing waituntill process in 'performance_Tag_reference' method. ");// +
																														// e.getMessage());
							}
							Thread.sleep(2000);
							System.out.println("strStatus:::"+ strStatus);
							// "Home_chart_Leavesum"));
							// ########################################################################
							if (strStatus.contains("false")) {
								appInd.writeLog("Fail", "'performance_Tag_reference' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'performance_Tag_reference' script was Successful");
								bolstatus = true;
								strStatus = null;
							}

						}

					} else {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\TagReference.png");
						bolstatus = false;
								
						
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
				
				appInd.writeLog("Fail","Performance_tag script was failed"+e.getMessage());
				oinpuTDtMap.put("result", "Fail");
				oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
				return oinputMap;
			} finally {
				System.out.println("end");
			}
		}					
/********************************
* Method Name : performance_Logical_view Purpose : This method is Filter the
* Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
* Creation : 11-Jan-2019 Date of
*/
public Map<String, HashMap> performance_Logical_view() {
Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
Map<String, String> oinpuTDtMap = new HashMap<String, String>();
double pageLoadTime_Seconds=0;
double totaltime;
String	strcommonCountvalue=null;

String strcurrentTD = null;
try {
	appInd.writeLog("#", "****performance_Logical_View:- started*****");
	obrowser = oDriver.getWebDriver();
	boolean bolstatus = false;
	String strExecutionsts = null;
	oinputMap = appInd.getInputData(strTCID);
	for (int TD = 1; TD <= oinputMap.size(); TD++) {
		oinpuTDtMap = oinputMap.get(String.valueOf(TD));

		if ((obrowser != null)) {
			// Read the Execution Status
			strExecutionsts = oinpuTDtMap.get("Executestatus");
			if (strExecutionsts.equalsIgnoreCase("yes"))

			{
				// ########################################################################
									// click on Menubar and Search bar
				try
				{
				By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
				boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
				By byClickOnSearchBar = appInd.createAndGetByObject("Search_bar");
				WebElement element = obrowser.findElement(byClickOnSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
				Thread.sleep(3000);
				// ########################################################################
				                    // click on check box and tag search bar
				 byClickOnSearchBar = appInd.createAndGetByObject("snapshot_id");
					element = obrowser.findElement(byClickOnSearchBar);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
					System.out.println("hi");
				
						List<WebElement> snapshot=obrowser.findElements(By.xpath("//button[@class='btn dropdown-toggle selectpicker btn-default' and @aria-expanded='true']/following-sibling::div/ul[@class='dropdown-menu inner selectpicker']/li/a/span[@class='text']"));
	    System.out.println(snapshot.size());
	    Iterator<WebElement> itr = snapshot.iterator();
	    WebElement we=null;
	   
		while(itr.hasNext()) {
			 we=itr.next();
		 //  System.out.println(we.getText());
			
		}
		((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",we);
							Thread.sleep(10000);
					
				
				System.out.println(oinpuTDtMap.get("Param_1"));
				By byclick_On_CheckBox = appInd.createAndGetByObject("CheckBox_Tag_Reference");
				WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				obrowser.findElement(byclick_On_CheckBox).clear();
				
				obrowser.findElement(byclick_On_CheckBox).sendKeys(oinpuTDtMap.get("Param_1"));
			
				Thread.sleep(5000);
				 byClickOnMenuBar = appInd.createAndGetByObject("Tag_Search");
				 
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
				Thread.sleep(10000);
			        //click on search
				By byClickOnTagSearchBar = appInd.createAndGetByObject("logical_view");
				 elementbyclick_On_CheckBox = obrowser.findElement(byClickOnTagSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				}
				catch(Exception e)
				{
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Performance\\LogicalView.png");
					bolstatus = false;
					strStatus+=false;
					
					appInd.writeLog("Exception",
							"Exception while executing waituntill process in 'Performance_logical_view' method. ");// +
				}
				
				//Thread.sleep(3000);
										
				
				long time = System.currentTimeMillis();
			
				System.out.println(time);
			
				try {
					
				
					boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
					long time1 = System.currentTimeMillis();
												totaltime=time1-time;
				System.out.println(totaltime);
				 pageLoadTime_Seconds = totaltime / 1000;
				 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
					System.out.println(pageLoadTime_Seconds);
					

				} catch (Exception e) {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Performance\\LogicalView.png");
					bolstatus = false;
					strStatus+=false;
					
					appInd.writeLog("Exception",
							"Exception while executing waituntill process in 'Performance_logical_view' method. ");// +
																											// e.getMessage());
				}
				if (pageLoadTime_Seconds <= 30) {
					strStatus += true;
					appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
				} else {
					strStatus += false;
					appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
				}
			//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
			
				Thread.sleep(2000);
				System.out.println("strStatus:::"+ strStatus);
				// "Home_chart_Leavesum"));
				// ########################################################################
				if (strStatus.contains("false")) {
					appInd.writeLog("Fail", "'performance_Logical_View' script was failed");
					bolstatus = false;
					strStatus = null;
				} else if (strStatus.contains("true")) {
					appInd.writeLog("Pass", "'performance_Logical_view' script was Successful");
					bolstatus = true;
					strStatus = null;
				}

			}

		} else {
			appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
					+ "\\Results\\snapshot\\Performance\\Logicalview.png");
			bolstatus = false;
					
			
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
	
	appInd.writeLog("Fail","Performance_tag script was failed"+e.getMessage());
	oinpuTDtMap.put("result", "Fail");
	oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
	return oinputMap;
} finally {
	System.out.println("end");
}
}									
							
/********************************
* Method Name : performance_hardware_view Purpose : This method is Filter the
* Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
* Creation : 11-Jan-2019 Date of
*/
public Map<String, HashMap> performance_Hardware_view() {
Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
Map<String, String> oinpuTDtMap = new HashMap<String, String>();
double pageLoadTime_Seconds=0;
double totaltime;
String	strcommonCountvalue=null;

String strcurrentTD = null;
try {
	appInd.writeLog("#", "****performance_Hardware_View:- started*****");
	obrowser = oDriver.getWebDriver();
	boolean bolstatus = false;
	String strExecutionsts = null;
	oinputMap = appInd.getInputData(strTCID);
	for (int TD = 1; TD <= oinputMap.size(); TD++) {
		oinpuTDtMap = oinputMap.get(String.valueOf(TD));

		if ((obrowser != null)) {
			// Read the Execution Status
			strExecutionsts = oinpuTDtMap.get("Executestatus");
			if (strExecutionsts.equalsIgnoreCase("yes"))

			{
				// ########################################################################
									// click on Menubar and Search bar
				try
				{
				By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
				boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
				By byClickOnSearchBar = appInd.createAndGetByObject("Search_bar");
				WebElement element = obrowser.findElement(byClickOnSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
				Thread.sleep(3000);
				// ########################################################################
				                    // click on check box and tag search bar
				 byClickOnSearchBar = appInd.createAndGetByObject("snapshot_id");
					element = obrowser.findElement(byClickOnSearchBar);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
						
				
						List<WebElement> snapshot=obrowser.findElements(By.xpath("//button[@class='btn dropdown-toggle selectpicker btn-default' and @aria-expanded='true']/following-sibling::div/ul[@class='dropdown-menu inner selectpicker']/li/a/span[@class='text']"));
	    System.out.println(snapshot.size());
	    Iterator<WebElement> itr = snapshot.iterator();
	    WebElement we=null;
	   
		while(itr.hasNext()) {
			 we=itr.next();
		 //  System.out.println(we.getText());
			
		}
		((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",we);
							Thread.sleep(3000);
					
				
				System.out.println(oinpuTDtMap.get("Param_1"));
				By byclick_On_CheckBox = appInd.createAndGetByObject("CheckBox_Tag_Reference");
				WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				obrowser.findElement(byclick_On_CheckBox).clear();
				
				obrowser.findElement(byclick_On_CheckBox).sendKeys(oinpuTDtMap.get("Param_1"));
			
				Thread.sleep(5000);
				 byClickOnMenuBar = appInd.createAndGetByObject("Tag_Search");
				 
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
				Thread.sleep(10000);
			        //click on search
				By byClickOnTagSearchBar = appInd.createAndGetByObject("hardware_view");
				 elementbyclick_On_CheckBox = obrowser.findElement(byClickOnTagSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				
				
				//Thread.sleep(3000);
										
				
				long time = System.currentTimeMillis();
			
				System.out.println(time);
			
			
					
				
				 result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
					long time1 = System.currentTimeMillis();
												totaltime=time1-time;
				System.out.println(totaltime);
				 pageLoadTime_Seconds = totaltime / 1000;
				 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
					System.out.println(pageLoadTime_Seconds);
					

				} catch (Exception e) {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Performance\\HardwareView.png");
					bolstatus = false;
					strStatus+=false;
					
					appInd.writeLog("Exception",
							"Exception while executing waituntill process in 'Performance_logical_view' method. ");// +
																											// e.getMessage());
				}
				if (pageLoadTime_Seconds <= 30) {
					strStatus += true;
					appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
				} else {
					strStatus += false;
					appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
				}
			//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
			
				Thread.sleep(2000);
				System.out.println("strStatus:::"+ strStatus);
				// "Home_chart_Leavesum"));
				// ########################################################################
				if (strStatus.contains("false")) {
					appInd.writeLog("Fail", "'performance_Logical_View' script was failed");
					bolstatus = false;
					strStatus = null;
				} else if (strStatus.contains("true")) {
					appInd.writeLog("Pass", "'performance_Logical_view' script was Successful");
					bolstatus = true;
					strStatus = null;
				}

			}

		} else {
			appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
					+ "\\Results\\snapshot\\Performance\\Hardwareview.png");
			bolstatus = false;
					
			
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
	
	appInd.writeLog("Fail","Performance_Hardware_view script was failed"+e.getMessage());
	oinpuTDtMap.put("result", "Fail");
	oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
	return oinputMap;
} finally {
	System.out.println("end");
}
}							
/********************************
* Method Name : performance_network_iew Purpose : This method is Filter the
* Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
* Creation : 11-Jan-2019 Date of
*/
public Map<String, HashMap> performance_Network_view() {
Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
Map<String, String> oinpuTDtMap = new HashMap<String, String>();
double pageLoadTime_Seconds=0;
double totaltime;
String	strcommonCountvalue=null;

String strcurrentTD = null;
try {
	appInd.writeLog("#", "****performance_Network_View:- started*****");
	obrowser = oDriver.getWebDriver();
	boolean bolstatus = false;
	String strExecutionsts = null;
	oinputMap = appInd.getInputData(strTCID);
	for (int TD = 1; TD <= oinputMap.size(); TD++) {
		oinpuTDtMap = oinputMap.get(String.valueOf(TD));

		if ((obrowser != null)) {
			// Read the Execution Status
			strExecutionsts = oinpuTDtMap.get("Executestatus");
			if (strExecutionsts.equalsIgnoreCase("yes"))

			{
				// ########################################################################
									// click on Menubar and Search bar
				try
				{
/*				By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				obrowser.findElement(By.linkText("Dashboard")).sendKeys(Keys.ENTER);
				boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);*/
				By byClickOnSearchBar = appInd.createAndGetByObject("Search_bar");
				WebElement element = obrowser.findElement(byClickOnSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
				Thread.sleep(3000);
				// ########################################################################
				                    // click on check box and tag search bar
				 byClickOnSearchBar = appInd.createAndGetByObject("snapshot_id");
				element = obrowser.findElement(byClickOnSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
				Thread.sleep(10000);
			
				List<WebElement> snapshot=obrowser.findElements(By.xpath("//button[@class='btn dropdown-toggle selectpicker btn-default' and @aria-expanded='true']/following-sibling::div/ul[@class='dropdown-menu inner selectpicker']/li/a/span[@class='text']"));
    System.out.println(snapshot.size());
    Iterator<WebElement> itr = snapshot.iterator();
    WebElement we=null;
   
	while(itr.hasNext()) {
		 we=itr.next();
	 //  System.out.println(we.getText());
		
	}
	((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",we);
						Thread.sleep(3000);
				
				
				System.out.println(oinpuTDtMap.get("Param_1"));
				By byclick_On_CheckBox = appInd.createAndGetByObject("CheckBox_Tag_Reference");
				WebElement elementbyclick_On_CheckBox = obrowser.findElement(byclick_On_CheckBox);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				obrowser.findElement(byclick_On_CheckBox).clear();
				
				obrowser.findElement(byclick_On_CheckBox).sendKeys(oinpuTDtMap.get("Param_1"));
			
				Thread.sleep(10000);
				By byClickOnMenuBar = appInd.createAndGetByObject("Tag_Search");
				 
				obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
			boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
			        //click on search
				By byClickOnTagSearchBar = appInd.createAndGetByObject("network_view");
				 elementbyclick_On_CheckBox = obrowser.findElement(byClickOnTagSearchBar);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
						elementbyclick_On_CheckBox);
				
				
				//Thread.sleep(3000);
										
				
				long time = System.currentTimeMillis();
			
				System.out.println(time);
			
			
					
				
					 result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 100);
					long time1 = System.currentTimeMillis();
												totaltime=time1-time;
				System.out.println(totaltime);
				 pageLoadTime_Seconds = totaltime / 1000;
				 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
					System.out.println(pageLoadTime_Seconds);
					

				} catch (Exception e) {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\Performance\\NetworkView.png");
					bolstatus = false;
					strStatus+=false;
					
					appInd.writeLog("Exception",
							"Exception while executing waituntill process in 'Performance_Network_view' method. ");// +
																											// e.getMessage());
				}
				if (pageLoadTime_Seconds <= 30) {
					strStatus += true;
					appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
				} else {
					strStatus += false;
					appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
				}
			//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
			
				Thread.sleep(2000);
				System.out.println("strStatus:::"+ strStatus);
				// "Home_chart_Leavesum"));
				// ########################################################################
				if (strStatus.contains("false")) {
					appInd.writeLog("Fail", "'performance_Network_View' script was failed");
					bolstatus = false;
					strStatus = null;
				} else if (strStatus.contains("true")) {
					appInd.writeLog("Pass", "'performance_Network_view' script was Successful");
					bolstatus = true;
					strStatus = null;
				}

			}

		} else {
			appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
					+ "\\Results\\snapshot\\Performance\\Hardwareview.png");
			bolstatus = false;
					
			
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
	
	appInd.writeLog("Fail","Performance_Hardware_view script was failed"+e.getMessage());
	oinpuTDtMap.put("result", "Fail");
	oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
	return oinputMap;
} finally {
	System.out.println("end");
}
}							

/********************************
 * Method Name : performance_report Purpose : This method is Filter the
 * Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
 * Creation : 03-jan-2019 Date of
 */
public Map<String, HashMap> performance_report() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_report:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
					try {
											// click on Menubar and Report
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Reports")).sendKeys(Keys.ENTER);
					//Thread.sleep(3000);
					
					long time = System.currentTimeMillis();
				
					System.out.println(time);
					
				
				
						boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
						long time1 = System.currentTimeMillis();
						//pageLoad.stop();
						//pageLoadTime_ms = pageLoad.getTime();
						System.out.println(time1);
					totaltime=time1-time;
					System.out.println(totaltime);
					 pageLoadTime_Seconds = totaltime / 1000;
					 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
						System.out.println(pageLoadTime_Seconds);
						

					} catch (Exception e) {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\Report.png");
								strStatus+=false;
						appInd.writeLog("Exception",
								"Exception while executing waituntill process in 'performance_report' method. ");// +
																												// e.getMessage());
					}
					if (pageLoadTime_Seconds <= 30) {
						strStatus += true;
						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
					} else {
						strStatus += false;
						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
					}
				//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
				//	Thread.sleep(3000);
				//	WebElement element1 = obrowser.findElement(byclickOnLogout);
			//		((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
					Thread.sleep(2000);
					System.out.println("strStatus:::"+ strStatus);
					// "Home_chart_Leavesum"));
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_report' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_report' script was Successful");
						bolstatus = true;
						strStatus = null;
					}

				}

			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
						+ "\\Results\\snapshot\\Performance\\Report.png");
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
		appInd.writeLog("Exception", "Exception while executing 'performance_report' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
}												
/********************************
 * Method Name : performance_data_collection Purpose : This method is Filter the
 * Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
 * Creation : 03-jan-2019 Date of
 */
public Map<String, HashMap> performance_data_collection() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_data_collection:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
											// click on Menubar and data_collection
					try {
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Data collection")).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					
					long time = System.currentTimeMillis();
				//	StopWatch pageLoad = new StopWatch();
					//pageLoad.start();
					System.out.println(time);
					//long pageLoadTime_ms = 0;
				//	long pageLoadTime_Seconds = 0;
					
						
												
					//	String xp="//*[@id='ajax-loader']/div[2]";
					//	WebDriverWait wait = new WebDriverWait(obrowser, 30);
					//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xp)));
					boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
						long time1 = System.currentTimeMillis();
						//pageLoad.stop();
						//pageLoadTime_ms = pageLoad.getTime();
						System.out.println(time1);
					totaltime=time1-time;
					System.out.println(totaltime);
					 pageLoadTime_Seconds = totaltime / 1000;
					 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
						System.out.println(pageLoadTime_Seconds);
						

					} catch (Exception e) {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\Data_collection.png");
						strStatus+=false;
						appInd.writeLog("Exception",
								"Exception while executing waituntill process in 'performance_data_collection' method. ");// +
																												// e.getMessage());
					}
					if (pageLoadTime_Seconds <= 30) {
						strStatus += true;
						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
					} else {
						strStatus += false;
						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
					}
				//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
				//	Thread.sleep(3000);
				//	WebElement element1 = obrowser.findElement(byclickOnLogout);
			//		((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
					Thread.sleep(2000);
					System.out.println("strStatus:::"+ strStatus);
					// "Home_chart_Leavesum"));
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_data_collection' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_data_collection' script was Successful");
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
		appInd.writeLog("Exception", "Exception while executing 'performance_data_collection' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
}
/********************************
 * Method Name : performance_Permission Purpose : This method is Filter the
 * Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
 * Creation : 03-jan-2019 Date of
 */
public Map<String, HashMap> performance_Permission() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_Permission:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
					try {					// click on Menubar and Permission
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Permissions")).sendKeys(Keys.ENTER);
					//Thread.sleep(3000);
					
					long time = System.currentTimeMillis();
				
					System.out.println(time);
					
				
						
					
						boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
						long time1 = System.currentTimeMillis();
						//pageLoad.stop();
						//pageLoadTime_ms = pageLoad.getTime();
						System.out.println(time1);
					totaltime=time1-time;
					System.out.println(totaltime);
					 pageLoadTime_Seconds = totaltime / 1000;
					 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
						System.out.println(pageLoadTime_Seconds);
						

					} catch (Exception e) {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\permission.png");
						strStatus+=false;
						appInd.writeLog("Exception",
								"Exception while executing waituntill process in 'performance_permission' method. ");// +
																												// e.getMessage());
					}
					if (pageLoadTime_Seconds <= 30) {
						strStatus += true;
						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
					} else {
						strStatus += false;
						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
					}
				//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
				//	Thread.sleep(3000);
				//	WebElement element1 = obrowser.findElement(byclickOnLogout);
			//		((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
					Thread.sleep(2000);
					System.out.println("strStatus:::"+ strStatus);
					// "Home_chart_Leavesum"));
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_Permission' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_Permission' script was Successful");
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
				
		appInd.writeLog("Exception", "Exception while executing 'performance_Permission' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
}
/********************************
 * Method Name : performance_License Purpose : This method is Filter the
 * Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
 * Creation : 03-jan-2019 Date of
 */
public Map<String, HashMap> performance_License() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_License:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
					try {				// click on Menubar and License
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("License")).sendKeys(Keys.ENTER);
					//Thread.sleep(3000);
					
					long time = System.currentTimeMillis();
				//	StopWatch pageLoad = new StopWatch();
					//pageLoad.start();
					System.out.println(time);
					//long pageLoadTime_ms = 0;
				//	long pageLoadTime_Seconds = 0;
				
						
						
						
					boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
					
						long time1 = System.currentTimeMillis();
						//pageLoad.stop();
						//pageLoadTime_ms = pageLoad.getTime();
						System.out.println(time1);
					totaltime=time1-time;
					System.out.println(totaltime);
					 pageLoadTime_Seconds = totaltime / 1000;
					 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
						System.out.println(pageLoadTime_Seconds);
						

					} catch (Exception e) {
						appInd.writeLog("Exception",
								"Exception while executing waituntill process in 'performance_License' method. ");
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\License.png");// +
																												// e.getMessage());
					}
					if (pageLoadTime_Seconds <= 30) {
						strStatus += true;
						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
					} else {
						strStatus += false;
						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
					}
				
					Thread.sleep(2000);
					System.out.println("strStatus:::"+ strStatus);
					// "Home_chart_Leavesum"));
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_License' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_License' script was Successful");
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
		appInd.writeLog("Exception", "Exception while executing 'performance_License' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
}
/********************************
 * Method Name : performance_License Purpose : This method is Filter the
 * Free Count and Showing Result screen Author : Dhyanchand hota Reviewer : Date of
 * Creation : 03-jan-2019 Date of
 */
public Map<String, HashMap> performance_Spares() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_spare:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
											// click on Menubar and License
					
					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Spares")).sendKeys(Keys.ENTER);
					//Thread.sleep(3000);
					
					long time = System.currentTimeMillis();
				//	StopWatch pageLoad = new StopWatch();
					//pageLoad.start();
					System.out.println(time);
					//long pageLoadTime_ms = 0;
				//	long pageLoadTime_Seconds = 0;
					try {
						
						
						
					boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
					
						long time1 = System.currentTimeMillis();
						//pageLoad.stop();
						//pageLoadTime_ms = pageLoad.getTime();
						System.out.println(time1);
					totaltime=time1-time;
					System.out.println(totaltime);
					 pageLoadTime_Seconds = totaltime / 1000;
					 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
						System.out.println(pageLoadTime_Seconds);
						

					} catch (Exception e) {
						appInd.writeLog("Exception",
								"Exception while executing waituntill process in 'performance_Spares' method. ");
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\License.png");// +
																												// e.getMessage());
					}
					if (pageLoadTime_Seconds <= 30) {
						strStatus += true;
						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
					} else {
						strStatus += false;
						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
					}
				
					Thread.sleep(2000);
					System.out.println("strStatus:::"+ strStatus);
					// "Home_chart_Leavesum"));
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_Spares' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_Spares' script was Successful");
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
		appInd.writeLog("Exception", "Exception while executing 'performance_License' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
}
/********************************
 * Method Name : TC_Loginscreen Purpose : This method will launch the home
 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
 * modification : 21-Nov-2018 ******************************
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap>Login_performance() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	String strcurrentTD = null;
	String strcommonCountvalue = null;
	String strcommonTime = null;
	double pageLoadTime_Seconds=0;
	double totaltime;
	
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
				
					//boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 50);
					//Thread.sleep(20000);
				
				/*		appInd.writeLog("Pass", "'TC_Loginscreen' script was Successful");
				/bolstatus = false;
						strStatus = null;*/
				
						 long time = System.currentTimeMillis();
							
							System.out.println(time);
						
							try {
								
							
							boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "",600);
							//Thread.sleep(20000);
							String ur=obrowser.getCurrentUrl();
							System.out.println(ur);
		              		if (ur.contains("home")) {
								appInd.writeLog("Fail", "'TC_Loginscreen' script was failed");
								bolstatus = false;
								strStatus += false;
							}
		              		else
		              		{
								long time1 = System.currentTimeMillis();
										totaltime=time1-time;
							System.out.println(totaltime);
							 pageLoadTime_Seconds = totaltime / 1000;
							 strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
								System.out.println(pageLoadTime_Seconds);
		              		}	
                   
							} catch (Exception e) {
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\Performance\\Loginw.png");
								bolstatus = false;
								strStatus+=false;
								
								appInd.writeLog("Exception",
										"Exception while executing waituntill process in 'Performance_logical_view' method. ");// +
																														// e.getMessage());
							}
							if (pageLoadTime_Seconds <= 30) {
								strStatus += true;
								appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
							} else {
								strStatus += false;
								appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
							}
						//	By byclickOnLogout = appInd.createAndGetByObject("Logout");
						
							Thread.sleep(2000);
							System.out.println("strStatus:::"+ strStatus);
							// "Home_chart_Leavesum"));
							// ########################################################################
							if (strStatus.contains("false")) {
								appInd.writeLog("Fail", "'performance_Login' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'performance_Login' script was Successful");
								bolstatus = true;
								strStatus = null;
							}

						

					} else {
						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								+ "\\Results\\snapshot\\Performance\\Login.png");
						bolstatus = false;
								
						
						appInd.writeLog("Fail", "Failed to launch the IE browser");
						bolstatus = false;
					}
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
				
				appInd.writeLog("Fail","Performance_tag script was failed"+e.getMessage());
				oinpuTDtMap.put("result", "Fail");
				oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
				return oinputMap;
			} finally {
				System.out.println("end");
			}
			}									

			
			@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap>performance_Navigation_Load() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	Map<Integer, String> outputMap = new HashMap<Integer, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	String totalchannelCountValue="0";
	String systemName = null;
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performanceNavigationt:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
					// click on Menubar and Report

					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Change Detection")).sendKeys(Keys.ENTER);
					Thread.sleep(10000);
					
					
					
					By select_DropDown = appInd.createAndGetByObject("Change_Detection_Select_System_Dropdown");
					WebElement select_System_DropDown = obrowser.findElement(select_DropDown);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",select_System_DropDown);
					Thread.sleep(2000);
					
					By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
					List<WebElement> system_elements = obrowser.findElements(systems);
					System.out.println(system_elements.size());
					Iterator<WebElement> itr = system_elements.iterator();
										
					for(int k=0;k<system_elements.size();k++) {
						try {
							system_elements = obrowser.findElements(systems);
							systemName = system_elements.get(k).getText(); 
							System.out.println("System"+systemName);
							                         				
											
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(k));

																
							long time = System.currentTimeMillis();
	    					System.out.println(time);
	    					try {
	    						boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
	    						long time1 = System.currentTimeMillis();
	    						System.out.println(time1);
	    						totaltime=time1-time;
	    						System.out.println(totaltime);
	    						pageLoadTime_Seconds = totaltime / 1000;
	    						strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
	    						System.out.println(pageLoadTime_Seconds);
	    						

	    					} catch (Exception e) {
	    						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	    								+ "\\Results\\snapshot\\Performance\\Navigation.png");
	    						strStatus+=false;
	    						appInd.writeLog("Exception",
	    								"Exception while executing waituntill process in 'performance_report' method. ");
	    						// e.getMessage());
	    					}
	    					try {
	    					By totalchannelCount = appInd.createAndGetByObject("total_change");
	    					totalchannelCountValue=obrowser.findElement(totalchannelCount).getText();
	    					
	    					}
	    					catch(Exception e)
	    					{
	    						totalchannelCountValue="0";
	    					
	    					}
	    					System.out.println("totalchannelCountValue::" + totalchannelCountValue);
	    					String mapString= systemName+"#"+strcommonCountvalue+"#"+totalchannelCountValue+"#";
	    					if (pageLoadTime_Seconds <= 30) {
	    						strStatus += true;
	    						mapString+="Pass";
	    						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
	    					} else {
	    						strStatus += false;
	    						mapString+="Fail";
	    						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
	    					}
	    				
	    					
	    					Thread.sleep(2000);
	    					System.out.println("strStatus:::"+ strStatus);
	    					System.out.println("strStatus:::"+ mapString);
	    					
	    						outputMap.put(outputMap.size()+1, mapString);  
    						Thread.sleep(2000);
	    					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",select_System_DropDown);
	    					Thread.sleep(2000);
						}catch(Exception e) {
							//e.printStackTrace();
							//break;
						}
					}
					
					
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_report' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_report' script was Successful");
						bolstatus = true;
						strStatus = null;
					}

				}
			

			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
						+ "\\Results\\snapshot\\Performance\\Report.png");
				bolstatus = false;
			}
			
			appInd.writeperformancetimeexcel(outputMap, DriverScript.strTCID, DriverScript.strModulename);
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
			strcurrentTD = String.valueOf(TD);
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
			strcommonCountvalue = null;


		} // for loop
		return oinputMap;
	} catch (Exception e) {
		appInd.writeLog("Exception", "Exception while executing 'performance_report' method. " + e.getMessage());
		appInd.writeperformancetimeexcel(outputMap, DriverScript.strTCID, DriverScript.strModulename);
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
	
}	
@SuppressWarnings({ "unchecked", "rawtypes" })
public Map<String, HashMap>performance_Engineering_anamoly() {
	Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
	Map<String, String> oinpuTDtMap = new HashMap<String, String>();
	Map<Integer, String> outputMap = new HashMap<Integer, String>();
	double pageLoadTime_Seconds=0;
	double totaltime;
	String	strcommonCountvalue=null;
	String totalchannelCountValue="0";
	String systemName = null;
	String strcurrentTD = null;
	try {
		appInd.writeLog("#", "****performance_anamoly:- started*****");
		obrowser = oDriver.getWebDriver();
		boolean bolstatus = false;
		String strExecutionsts = null;
		oinputMap = appInd.getInputData(strTCID);
		for (int TD = 1; TD <= oinputMap.size(); TD++) {
			oinpuTDtMap = oinputMap.get(String.valueOf(TD));

			if ((obrowser != null)) {
				// Read the Execution Status
				strExecutionsts = oinpuTDtMap.get("Executestatus");
				if (strExecutionsts.equalsIgnoreCase("yes"))

				{
					// ########################################################################
					// click on Menubar and Report

					By byClickOnMenuBar = appInd.createAndGetByObject("Menu_Bar");
					obrowser.findElement(byClickOnMenuBar).sendKeys(Keys.ENTER);
					Thread.sleep(3000);
					obrowser.findElement(By.linkText("Engineering Anomaly")).sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					
					
					
					By select_DropDown = appInd.createAndGetByObject("E_Anamoly");
					WebElement select_System_DropDown = obrowser.findElement(select_DropDown);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",select_System_DropDown);
					Thread.sleep(2000);
					
					By systems = appInd.createAndGetByObject("Change_Detection_SystemNames");
					List<WebElement> system_elements = obrowser.findElements(systems);
					System.out.println(system_elements.size());
					Iterator<WebElement> itr = system_elements.iterator();
										
					for(int k=0;k<system_elements.size();k++) {
						try {
							system_elements = obrowser.findElements(systems);
							systemName = system_elements.get(k).getText(); 
							System.out.println("System"+systemName);
							                         				
											
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",system_elements.get(k));

																
							long time = System.currentTimeMillis();
	    					System.out.println(time);
	    					try {
	    						boolean result=	appInd.waitFor(obrowser, "ajax_loading", "invisible", "", 600);
	    						long time1 = System.currentTimeMillis();
	    						System.out.println(time1);
	    						totaltime=time1-time;
	    						System.out.println(totaltime);
	    						pageLoadTime_Seconds = totaltime / 1000;
	    						strcommonCountvalue = pageLoadTime_Seconds +"Seconds";
	    						System.out.println(pageLoadTime_Seconds);
	    						

	    					} catch (Exception e) {
	    						appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
	    								+ "\\Results\\snapshot\\Performance\\E_anamoly.png");
	    						strStatus+=false;
	    						appInd.writeLog("Exception",
	    								"Exception while executing waituntill process in 'performance_Enamoly' method. ");
	    						// e.getMessage());
	    					}
	    					try {
		    					By totalchannelCount = appInd.createAndGetByObject("total_change");
		    					totalchannelCountValue=obrowser.findElement(totalchannelCount).getText();
		    					if(totalchannelCountValue.equalsIgnoreCase(""))
		    						totalchannelCountValue="0";
		    										}
	    					
		    					catch(Exception e)
		    					{
		    						totalchannelCountValue="0";
		    					
		    					}
		    					System.out.println("totalchannelCountValue::" + totalchannelCountValue);
		    					String mapString= systemName+"#"+strcommonCountvalue+"#"+totalchannelCountValue+"#";
	    				
	    					if (pageLoadTime_Seconds <= 30) {
	    						strStatus += true;
	    						mapString+="Pass";
	    						appInd.writeLog("Pass", "Total Time for page load:" + pageLoadTime_Seconds);
	    					} else {
	    						strStatus += false;
	    						mapString+="Fail";
	    						appInd.writeLog("Fail", "Total Time for page load:" + pageLoadTime_Seconds);
	    					}
	    					Thread.sleep(2000);
	    					System.out.println("strStatus:::"+ strStatus);
	    					
	    						outputMap.put(outputMap.size()+1, mapString);  
    						Thread.sleep(2000);
	    					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",select_System_DropDown);
	    					Thread.sleep(2000);
						}catch(Exception e) {
							e.printStackTrace();
							//break;
						}
					}
					
					
					
					// ########################################################################
					if (strStatus.contains("false")) {
						appInd.writeLog("Fail", "'performance_report' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'performance_report' script was Successful");
						bolstatus = true;
						strStatus = null;
					}

				}

			} else {
				appInd.writeLog("Fail", "Failed to launch the IE browser");
				appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
						+ "\\Results\\snapshot\\Performance\\Report.png");
				bolstatus = false;
			}
			appInd.writeperformancetimeexcel(outputMap, DriverScript.strTCID, DriverScript.strModulename);
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
			strcurrentTD = String.valueOf(TD);
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			oinpuTDtMap = null;
			strcommonCountvalue = null;


		} // for loop
		return oinputMap;
	} catch (Exception e) {
		appInd.writeLog("Exception", "Exception while executing 'performance_report' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
		return oinputMap;
	} finally {
		System.out.println("end");
	}
	
}	


//Janhavi test cases**************Adding Systems***********//
	/********************************
	 * Method Name : TC_addasystemofExperionIntegratedTPS Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_addasystemofExperionIntegratedTPS() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_addasystemofExperionIntegratedTPS:- started*****");
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
							// #######################################################################
							Thread.sleep(5000);
							//strStatus += String.valueOf(appInd.setObject(obrowser, "Menu_bar", "E"));
							
							By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
							Thread.sleep(3000);
							WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
							Thread.sleep(5000);
							By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
							Thread.sleep(3000);
							WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
							//Thread.sleep(5000);	
							Thread.sleep(30000);
						
							//Click on add system
							By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
							Thread.sleep(3000);
							WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
							Thread.sleep(5000);
						//###########################################################################################
							//Click on select systems
							By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
							Thread.sleep(3000);
							WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
							Thread.sleep(5000);
						//######################################################################################
							//Select System from dropdown
							By byclickOnSelect_EI_TPS = appInd.createAndGetByObject("Select_EI_TPS");
							Thread.sleep(3000);
							WebElement elementx = obrowser.findElement(byclickOnSelect_EI_TPS);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
							Thread.sleep(5000);
						//#######################################################################################
							//Click and enter Integrated system name
							
							obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div/div/div[1]/div[2]/div/input")).clear();

							By byclickOnEnter_IntegratedSystemName = appInd.createAndGetByObject("Enter_IntegratedSystemName");
							Thread.sleep(3000);
							WebElement elementh = obrowser.findElement(byclickOnEnter_IntegratedSystemName);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
							Thread.sleep(5000);
							 strStatus += String.valueOf(
										appInd.setObject(obrowser, "Enter_IntegratedSystemName", oinpuTDtMap.get("Param_1")));
								Thread.sleep(5000);
						//###################################################################################################
								//Click and enter system name
                        obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_tps\"]/div/div[1]/div[2]/div/input")).clear();
                        By byclickOnEnter_SystemName = appInd.createAndGetByObject("Enter_SystemName");
							Thread.sleep(3000);
							WebElement elementf = obrowser.findElement(byclickOnEnter_SystemName);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementf);
							Thread.sleep(3000);
								strStatus += String.valueOf(
										appInd.setObject(obrowser, "Enter_SystemName", oinpuTDtMap.get("Param_2")));
							//##############################################################################################
							//Click and enter IP address	
								 obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
								 By byclickOnEnter_IPaddress = appInd.createAndGetByObject("Enter_IPaddress");
									Thread.sleep(3000);
									WebElement elementu = obrowser.findElement(byclickOnEnter_IPaddress);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementu);
									Thread.sleep(5000);
									strStatus +=		String.valueOf(
											appInd.setObject(obrowser, "Enter_IPaddress", oinpuTDtMap.get("Param_3")));
						//############################################################################################################
						              //Enter user name			
									obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_tps\"]/div/div[3]/div/div[2]/div/input")).clear();
									 By byclickOnEnter_UserName = appInd.createAndGetByObject("Enter_UserName");
										Thread.sleep(3000);
										WebElement elementg = obrowser.findElement(byclickOnEnter_UserName);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementg);
										Thread.sleep(5000);	
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_UserName", oinpuTDtMap.get("Param_4")));
							//######################################################################################################
										//Enter password
										obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_tps\"]/div/div[4]/div/div[2]/input")).clear();
										 By byclickOnEnter_Password = appInd.createAndGetByObject("Enter_Password");
											Thread.sleep(3000);
											WebElement elements = obrowser.findElement(byclickOnEnter_Password);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elements);
											Thread.sleep(5000);	
											strStatus += String.valueOf(
													appInd.setObject(obrowser, "Enter_Password", oinpuTDtMap.get("Param_5")));
								//############################################################################################################			
											By byclickOnAdd_EPKSin_EITPS = appInd.createAndGetByObject("Add_EPKSin_EITPS");
											Thread.sleep(3000);
											WebElement elementq = obrowser.findElement(byclickOnAdd_EPKSin_EITPS);
											((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
											Thread.sleep(5000);	
											
										obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_0\"]/div/div[1]/div[2]/div/input")).clear();
										
										By byclickOnEnter_SystemnameEPKS_EITPS = appInd.createAndGetByObject("Enter_SystemnameEPKS_EITPS");
										Thread.sleep(3000);
										WebElement element1 = obrowser.findElement(byclickOnEnter_SystemnameEPKS_EITPS);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
										Thread.sleep(5000);	
										

										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_SystemnameEPKS_EITPS", oinpuTDtMap.get("Param_6")));
										
										
										By byclickOnEnter_EITPS_Servername = appInd.createAndGetByObject("Enter_EITPS_Servername");
										Thread.sleep(3000);
										WebElement element2 = obrowser.findElement(byclickOnEnter_EITPS_Servername);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element2);
										Thread.sleep(5000);
										
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_EITPS_Servername", oinpuTDtMap.get("Param_7")));
										
										By byclickOnEnter_EITPS_Modelservarname = appInd.createAndGetByObject("Enter_EITPS_Modelservarname");
										Thread.sleep(3000);
										WebElement element3 = obrowser.findElement(byclickOnEnter_EITPS_Modelservarname);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
										Thread.sleep(5000);
										
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_EITPS_Modelservarname", oinpuTDtMap.get("Param_8")));
										Thread.sleep(3000);
										
										By byclickOnEnter_FMIP_EITPS = appInd.createAndGetByObject("Enter_FMIP_EITPS");
										Thread.sleep(3000);
										WebElement element4 = obrowser.findElement(byclickOnEnter_FMIP_EITPS);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element4);
										Thread.sleep(5000);
										
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_FMIP_EITPS", oinpuTDtMap.get("Param_9")));
										
										Thread.sleep(3000);
										obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_0\"]/div/div[5]/div/div[2]/div/input")).clear();
										
										By byclickOnEnter_EITPS_username = appInd.createAndGetByObject("Enter_EITPS_username");
										Thread.sleep(3000);
										WebElement element5 = obrowser.findElement(byclickOnEnter_EITPS_username);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element5);
										Thread.sleep(5000);
										
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_EITPS_username", oinpuTDtMap.get("Param_10")));
										
										
										obrowser.findElement(By.xpath("//*[@id=\"esvt_collapse_experion_0\"]/div/div[6]/div/div[2]/input")).clear();
										By byclickOnEnter_EITPS_password = appInd.createAndGetByObject("Enter_EITPS_password");
										Thread.sleep(3000);
										WebElement elementtt = obrowser.findElement(byclickOnEnter_EITPS_password);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementtt);
										Thread.sleep(5000);
										
										strStatus +=	String.valueOf(
												appInd.setObject(obrowser, "Enter_EITPS_password", oinpuTDtMap.get("Param_11")));
											
								//############################################################################################################			
												By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
												Thread.sleep(3000);
												WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
												((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
												Thread.sleep(5000);
												
													
											
												
												try {
													
													By byabort_collection_confirm_System = appInd
													.createAndGetByObject("abort_collection_confirm_System");
													WebElement elementbyabort_collection_confirm_System = obrowser
													.findElement(byabort_collection_confirm_System);

													if (elementbyabort_collection_confirm_System != null) {
													strStatus += "false";
													appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
															+ "\\Results\\snapshot\\ExperionIntegratedTPS_Fail_systemalreadyExistsnapshot.png");
													((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
													elementbyabort_collection_confirm_System);
													
													Thread.sleep(3000);
													
													By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
													Thread.sleep(3000);
													WebElement elementb = obrowser.findElement(byclickOnClose_system);
													((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
													Thread.sleep(5000);	
													}
													} catch (Exception e) {
														
													}
												
												
												String paramValue = oinpuTDtMap.get("Param_1");
												List<WebElement> rows = obrowser
														.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

												for (WebElement rowElement : rows) {
													System.out.println("rowElement.getText()::" +rowElement.getText());
													if (rowElement.getText().equals(paramValue)) {
														System.out.println("matched" +rowElement.getText());
														System.out.println("matched:::" +paramValue);
														strStatus += true;
														System.out.println("strStatus:::" +strStatus);
														//break;
													} 

												}
												
												
												// ########################################################################
												if (strStatus.contains("false")) {
												//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
															//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
													appInd.writeLog("Fail", "'TC_addasystemofExperionIntegratedTPS' script was failed");
													bolstatus = false;
													strStatus = null;
												} else if (strStatus.contains("true")) {
													appInd.writeLog("Pass", "'TC_addasystemofExperionIntegratedTPS' script was Successful");
													bolstatus = true;
													strStatus = null;
												}
											}

										} else {
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshort\\Failed_to_launch_IE_browser_snapshot.png");
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
											+ "\\Results\\snapshort\\TC_addasystemofExperionIntegratedTPS_Fail_deuTO_Exception_snapshot.png");
								} catch (Exception e1) {
									
								}
								appInd.writeLog("Exception", "Exception while executing 'TC_addasystemofExperionIntegratedTPS' method. " + e.getMessage());
								oinpuTDtMap.put("result", "Fail");
								oinputMap.put("result", (HashMap) oinpuTDtMap);
								return oinputMap;
							}
						}
	
	
	
	/********************************
	 * Method Name : TC_addasystemofExperionPKSProcessServer Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_addasystemofExperionPKSProcessServer() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_addasystemofExperionPKSProcessServer:- started*****");
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
	
						// #######################################################################
						Thread.sleep(5000);
						/*strStatus += String.valueOf(appInd.setObject(obrowser, "Menu_bar", "E"));
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_DataCollection", "E"))*/
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);
						
						By byclickOnSelect_Experion = appInd.createAndGetByObject("Select_Experion");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_Experion);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnEnter_Experion_Systemname = appInd.createAndGetByObject("Enter_Experion_Systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnEnter_Experion_Systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_Systemname", oinpuTDtMap.get("Param_1")));
						
						By byclickOnEnter_Experion_Servername = appInd.createAndGetByObject("Enter_Experion_Servername");
						Thread.sleep(3000);
						WebElement elementi = obrowser.findElement(byclickOnEnter_Experion_Servername);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementi);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_Servername", oinpuTDtMap.get("Param_2")));
						
						By byclickOnEnter_Experion_modelservername = appInd.createAndGetByObject("Enter_Experion_modelservername");
						Thread.sleep(3000);
						WebElement elementj = obrowser.findElement(byclickOnEnter_Experion_modelservername);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_modelservername", oinpuTDtMap.get("Param_3")));
				
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnEnter_Experion_IPaddress = appInd.createAndGetByObject("Enter_Experion_IPaddress");
						Thread.sleep(3000);
						WebElement elementl = obrowser.findElement(byclickOnEnter_Experion_IPaddress);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementl);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_IPaddress", oinpuTDtMap.get("Param_4")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[5]/div/div[2]/div/input")).clear();
						By byclickOnEnter_Experion_username = appInd.createAndGetByObject("Enter_Experion_username");
						Thread.sleep(3000);
						WebElement elementm = obrowser.findElement(byclickOnEnter_Experion_username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementm);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_username", oinpuTDtMap.get("Param_5")));

						
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[6]/div/div[2]/input")).clear();
						By byclickOnEnter_Experion_Password = appInd.createAndGetByObject("Enter_Experion_Password");
						Thread.sleep(3000);
						WebElement elementn = obrowser.findElement(byclickOnEnter_Experion_Password);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementn);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_Experion_Password", oinpuTDtMap.get("Param_6")));
						
							
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						
							
						

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_addasystemofExperionPKSProcessServer_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						
            
						
						// ########################################################################
						if (strStatus.contains("false")) {
						//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
							appInd.writeLog("Fail", "'TC_addasystemofExperionPKSProcessServer' script was failed");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_addasystemofExperionPKSProcessServer' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}

				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshort\\Failed_to_launch_IE_browser_snapshot.png");
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
					+ "\\Results\\snapshort\\TC_addasystemofExperionPKSProcessServer_Fail_deuTO_Exception_snapshot.png");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		appInd.writeLog("Exception", "Exception while executing 'TC_addasystemofExperionPKSProcessServer' method. " + e.getMessage());
		oinpuTDtMap.put("result", "Fail");
		oinputMap.put("result", (HashMap) oinpuTDtMap);
		return oinputMap;
	}
}
	
	/********************************
	 * Method Name : TC_ToaddasystemofTPS Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofTPS() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofTPS:- started*****");
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
				// #######################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						/*strStatus += String.valueOf(appInd.setObject(obrowser, "Menu_bar", "E"));
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_DataCollection", "E"));*/
						
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_System_TPS = appInd.createAndGetByObject("Select_System_TPS");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_System_TPS);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						
						By byclickOnEnter_TPS_systemname = appInd.createAndGetByObject("Enter_TPS_systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnEnter_TPS_systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_TPS_systemname", oinpuTDtMap.get("Param_1")));
						
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnEnter_TPS_IP = appInd.createAndGetByObject("Enter_TPS_IP");
						Thread.sleep(3000);
						WebElement elementrr = obrowser.findElement(byclickOnEnter_TPS_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementrr);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_TPS_IP", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
						
						By byclickOnEnter_TPS_Username = appInd.createAndGetByObject("Enter_TPS_Username");
						Thread.sleep(3000);
						WebElement elementp = obrowser.findElement(byclickOnEnter_TPS_Username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementp);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_TPS_Username", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/input")).clear();
						By byclickOnEnter_TPS_Password = appInd.createAndGetByObject("Enter_TPS_Password");
						Thread.sleep(3000);
						WebElement elementg = obrowser.findElement(byclickOnEnter_TPS_Password);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementg);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_TPS_Password", oinpuTDtMap.get("Param_4")));
						
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						
							

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddasystemofTPS_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						
                
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddasystemofTPS' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ToaddasystemofTPS' script was Successful");
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
						+ "\\Results\\snapshot\\TC_ToaddasystemofTPS_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofTPS' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
	}
	
	/********************************
	 * Method Name : TC_ToaddasystemofSafetyManagerAutomatic Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofSafetyManagerAutomatic() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofSafetyManagerAutomatic:- started*****");
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
				// #######################################################################
						Thread.sleep(5000);
						/*strStatus += String.valueOf(appInd.setObject(obrowser, "Menu_bar", "E"));
						strStatus += String.valueOf(appInd.setObject(obrowser, "Click_DataCollection", "E"));*/
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						
						
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_Sm_Automatic = appInd.createAndGetByObject("Select_Sm_Automatic");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_Sm_Automatic);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnSMA_Systemname = appInd.createAndGetByObject("SMA_Systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnSMA_Systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SMA_Systemname", oinpuTDtMap.get("Param_1")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						
						By byclickOnSM_IP = appInd.createAndGetByObject("SM_IP");
						Thread.sleep(3000);
						WebElement elementrr = obrowser.findElement(byclickOnSM_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementrr);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SM_IP", oinpuTDtMap.get("Param_2")));
						
						By byclickOnSelect_DCMode = appInd.createAndGetByObject("Select_DCMode");
						Thread.sleep(3000);
						WebElement element1 = obrowser.findElement(byclickOnSelect_DCMode);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
						Thread.sleep(5000);
						
						appInd.selectDropdownvalue(obrowser, "Select_Automaticmode", "Automatic");
						
						
						
						By byclickOnSelect_BuildRelease = appInd.createAndGetByObject("Select_BuildRelease");
						Thread.sleep(3000);
						WebElement element2 = obrowser.findElement(byclickOnSelect_BuildRelease);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element2);
						Thread.sleep(5000);
						
						appInd.selectDropdownvalue(obrowser, "Select_BuildRelease", oinpuTDtMap.get("Param_3"));
						
//						strStatus += String.valueOf(
//								appInd.setObject(obrowser, "Select_BuildRelease", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"SMPlantName\"]")).clear();
						By byclickOnSelect_Plantname = appInd.createAndGetByObject("Select_Plantname");
						Thread.sleep(3000);
						WebElement element3 = obrowser.findElement(byclickOnSelect_Plantname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Select_Plantname", oinpuTDtMap.get("Param_4")));
						
	           obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[7]/div/div[2]/div/input")).clear();
	
	            By byclickOnSM_UserName = appInd.createAndGetByObject("SM_UserName");
	             Thread.sleep(3000);
	             WebElement element4 = obrowser.findElement(byclickOnSM_UserName);
	            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element4);
	            Thread.sleep(5000);
	           strStatus += String.valueOf(
			appInd.setObject(obrowser, "SM_UserName", oinpuTDtMap.get("Param_5")));
	           
	           obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[8]/div/div[2]/input")).clear();
	           By byclickOnSM_Password = appInd.createAndGetByObject("SM_Password");
	             Thread.sleep(3000);
	             WebElement element5 = obrowser.findElement(byclickOnSM_Password);
	            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element5);
	            Thread.sleep(5000);
	           strStatus += String.valueOf(
			appInd.setObject(obrowser, "SM_Password", oinpuTDtMap.get("Param_6")));
	           
	       	By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
			Thread.sleep(3000);
			WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
			((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
			Thread.sleep(5000);
			
				
				

				try {
					
					By byabort_collection_confirm_System = appInd
					.createAndGetByObject("abort_collection_confirm_System");
					WebElement elementbyabort_collection_confirm_System = obrowser
					.findElement(byabort_collection_confirm_System);

					if (elementbyabort_collection_confirm_System != null) {
					strStatus += "false";
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ExperionIntegratedTPS_Fail_systemalreadyExistsnapshot.png");
					Thread.sleep(5000);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
					elementbyabort_collection_confirm_System);
					
					Thread.sleep(3000);
					/*strStatus += String.valueOf(
							appInd.setObject(obrowser, "Close_system", "E"));	*/
					By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
					Thread.sleep(3000);
					WebElement elementb = obrowser.findElement(byclickOnClose_system);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
					Thread.sleep(5000);	
					}
					} catch (Exception e) {
						
					}
				
				
			
				String paramValue = oinpuTDtMap.get("Param_1");
				List<WebElement> rows = obrowser
						.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

				for (WebElement rowElement : rows) {
					System.out.println("rowElement.getText()::" +rowElement.getText());
					if (rowElement.getText().equals(paramValue)) {
						System.out.println("rowElement.getText()1111::" +rowElement.getText());
						System.out.println("paramValue:::" +paramValue);
						strStatus += true;
						System.out.println("strStatus:::" +strStatus);
						//break;
					} 

				}
			
		
				
				// ########################################################################
				if (strStatus.contains("false")) {
					//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
						appInd.writeLog("Fail", "'TC_ToaddasystemofSafetyManagerAutomatic' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_ToaddasystemofSafetyManagerAutomatic' script was Successful");
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
				+ "\\Results\\snapshot\\TC_ToaddasystemofSafetyManagerAutomatic_Fail_deuTO_Exception_snapshot.png");
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofSafetyManagerAutomatic' method. " + e.getMessage());
	oinpuTDtMap.put("result", "Fail");
	oinputMap.put("result", (HashMap) oinpuTDtMap);
	return oinputMap;
}
}
	
	/********************************
	 * Method Name : TC_ToaddasystemofSafetyManagerManual Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofSafetyManagerManual() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofSafetyManagerManual:- started*****");
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
				// #######################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_Sm_Automatic = appInd.createAndGetByObject("Select_Sm_Automatic");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_Sm_Automatic);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnSelect_SM_name = appInd.createAndGetByObject("Select_SM_name");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnSelect_SM_name);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Select_SM_name", oinpuTDtMap.get("Param_1")));
	           
				obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
				By byclickOnSelect_SM_IP = appInd.createAndGetByObject("Select_SM_IP");
				Thread.sleep(3000);
				WebElement elementyy = obrowser.findElement(byclickOnSelect_SM_IP);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementyy);
				Thread.sleep(5000);
				strStatus += String.valueOf(
						appInd.setObject(obrowser, "Select_SM_IP", oinpuTDtMap.get("Param_2")));
				
				By byclickOnSelect_DCMode_manual = appInd.createAndGetByObject("Select_DCMode_manual");
				Thread.sleep(3000);
				WebElement element1 = obrowser.findElement(byclickOnSelect_DCMode_manual);
				//((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
				//Thread.sleep(5000);
				
				appInd.selectDropdownvalue(obrowser, "Select_DCMode_manual", "Manual");
				
				obrowser.findElement(By.xpath("//*[@id=\"SMSourcePath\"]/div[2]/input")).clear();
				By byclickOnEnter_SM_DBpath = appInd.createAndGetByObject("Enter_SM_DBpath");
				Thread.sleep(3000);
				WebElement element2 = obrowser.findElement(byclickOnEnter_SM_DBpath);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element2);
				Thread.sleep(5000);
				
				strStatus += String.valueOf(
						appInd.setObject(obrowser, "Enter_SM_DBpath", oinpuTDtMap.get("Param_3")));
				
				obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[7]/div/div[2]/div/input")).clear();
				By byclickOnEnter_SM_UserName = appInd.createAndGetByObject("Enter_SM_UserName");
				Thread.sleep(3000);
				WebElement element3 = obrowser.findElement(byclickOnEnter_SM_UserName);
				((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element3);
				Thread.sleep(5000);
				strStatus += String.valueOf(
						appInd.setObject(obrowser, "Enter_SM_UserName", oinpuTDtMap.get("Param_4")));
				
				obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[8]/div/div[2]/input")).clear();
				By byclickOnEnter_SM_Password = appInd.createAndGetByObject("Enter_SM_Password");
	             Thread.sleep(3000);
	             WebElement element4 = obrowser.findElement(byclickOnEnter_SM_Password);
	            ((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element4);
	            Thread.sleep(5000);
	           strStatus += String.valueOf(
			appInd.setObject(obrowser, "Enter_SM_Password", oinpuTDtMap.get("Param_5")));
	           
	       	By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
			Thread.sleep(3000);
			WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
			((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
			Thread.sleep(5000);	
			

				try {
					
					By byabort_collection_confirm_System = appInd
					.createAndGetByObject("abort_collection_confirm_System");
					WebElement elementbyabort_collection_confirm_System = obrowser
					.findElement(byabort_collection_confirm_System);

					if (elementbyabort_collection_confirm_System != null) {
					strStatus += "false";
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_ToaddasystemofSafetyManagerManual_Fail_systemalreadyExistsnapshot.png");
					Thread.sleep(5000);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
					elementbyabort_collection_confirm_System);
					
					Thread.sleep(3000);
					/*strStatus += String.valueOf(
							appInd.setObject(obrowser, "Close_system", "E"));	*/
					By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
					Thread.sleep(3000);
					WebElement elementb = obrowser.findElement(byclickOnClose_system);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
					Thread.sleep(5000);	
					}
					} catch (Exception e) {
						
					}
				
				
			
				String paramValue = oinpuTDtMap.get("Param_1");
				List<WebElement> rows = obrowser
						.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

				for (WebElement rowElement : rows) {
					System.out.println("rowElement.getText()::" +rowElement.getText());
					if (rowElement.getText().equals(paramValue)) {
						System.out.println("rowElement.getText()1111::" +rowElement.getText());
						System.out.println("paramValue:::" +paramValue);
						strStatus += true;
						System.out.println("strStatus:::" +strStatus);
						//break;
					} 

				}
			
			
				
				// ########################################################################
				if (strStatus.contains("false")) {
					//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
								//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
						appInd.writeLog("Fail", "'TC_ToaddasystemofSafetyManagerManual' script was failed");
						bolstatus = false;
						strStatus = null;
					} else if (strStatus.contains("true")) {
						appInd.writeLog("Pass", "'TC_ToaddasystemofSafetyManagerManual' script was Successful");
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
				+ "\\Results\\snapshot\\TC_ToaddasystemofSafetyManagerManual_Fail_deuTO_Exception_snapshot.png");
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofSafetyManagerManual' method. " + e.getMessage());
	oinpuTDtMap.put("result", "Fail");
	oinputMap.put("result", (HashMap) oinpuTDtMap);
	return oinputMap;
}
}
	
	/********************************
	 * Method Name : TC_ToaddasystemofTriconex Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofTriconex() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofTriconex:- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_Triconex = appInd.createAndGetByObject("Select_Triconex");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_Triconex);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnSelect_Systemname_triconex = appInd.createAndGetByObject("Select_Systemname_triconex");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnSelect_Systemname_triconex);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Select_Systemname_triconex", oinpuTDtMap.get("Param_1")));

				   obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
				   By byclickOnEnter_triconex_IP = appInd.createAndGetByObject("Enter_triconex_IP");
					Thread.sleep(3000);
					WebElement elemento = obrowser.findElement(byclickOnEnter_triconex_IP);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elemento);
					Thread.sleep(5000);
					strStatus += String.valueOf(
							appInd.setObject(obrowser, "Enter_triconex_IP", oinpuTDtMap.get("Param_2")));
					
					obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
					By byclickOnEnter_triconex_username = appInd.createAndGetByObject("Enter_triconex_username");
					Thread.sleep(3000);
					WebElement elementl = obrowser.findElement(byclickOnEnter_triconex_username);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementl);
					Thread.sleep(5000);
					strStatus += String.valueOf(
							appInd.setObject(obrowser, "Enter_triconex_username", oinpuTDtMap.get("Param_3")));
					
					obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/input")).clear();
					By byclickOnEnter_triconex_password = appInd.createAndGetByObject("Enter_triconex_password");
					Thread.sleep(3000);
					WebElement element9 = obrowser.findElement(byclickOnEnter_triconex_password);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element9);
					Thread.sleep(5000);
					strStatus += String.valueOf(
							appInd.setObject(obrowser, "Enter_triconex_password", oinpuTDtMap.get("Param_4")));
					
					obrowser.findElement(By.xpath("//*[@id=\"TriconexDataFilePath\"]/div[2]/input")).clear();
					By byclickOnEnter_filepath = appInd.createAndGetByObject("Enter_filepath");
					Thread.sleep(3000);
					WebElement element2 = obrowser.findElement(byclickOnEnter_filepath);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element2);
					Thread.sleep(5000);
					strStatus += String.valueOf(
							appInd.setObject(obrowser, "Enter_filepath", oinpuTDtMap.get("Param_5")));
					
					
					By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
					Thread.sleep(3000);
					WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
					((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
					Thread.sleep(5000);
					
						

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddasystemofTriconex_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
					
					
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddasystemofTriconex' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ToaddasystemofTriconex' script was Successful");
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
						+ "\\Results\\snapshot\\TC_ToaddasystemofTriconex_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofTriconex' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
			
	/********************************
	 * Method Name : TC_ToaddasystemofFSC Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofFSC() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofFSC:- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_FSC = appInd.createAndGetByObject("Select_FSC");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_FSC);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnFSC_Systemname = appInd.createAndGetByObject("FSC_Systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnFSC_Systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_Systemname", oinpuTDtMap.get("Param_1")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnFSC_IP = appInd.createAndGetByObject("FSC_IP");
						Thread.sleep(3000);
						WebElement elementz = obrowser.findElement(byclickOnFSC_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementz);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_IP", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"FSCProjectName\"]")).clear();
						By byclickOnFSC_Projectname = appInd.createAndGetByObject("FSC_Projectname");
						Thread.sleep(3000);
						WebElement elementf = obrowser.findElement(byclickOnFSC_Projectname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementf);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_Projectname", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"FSCProjectPath\"]/div[2]/input")).clear();
						By byclickOnFSC_Projectpath = appInd.createAndGetByObject("FSC_Projectpath");
						Thread.sleep(3000);
						WebElement elementj = obrowser.findElement(byclickOnFSC_Projectpath);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_Projectpath", oinpuTDtMap.get("Param_4")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[5]/div/div[2]/div/input")).clear();
						
						By byclickOnFSC_Username = appInd.createAndGetByObject("FSC_Username");
						Thread.sleep(3000);
						WebElement elementd = obrowser.findElement(byclickOnFSC_Username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementd);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_Username", oinpuTDtMap.get("Param_5")));
				
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[6]/div/div[2]/input")).clear();
						
						By byclickOnFSC_Password = appInd.createAndGetByObject("FSC_Password");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byclickOnFSC_Password);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "FSC_Password", oinpuTDtMap.get("Param_6")));
						
						

						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						

							try {
								
								By byabort_collection_confirm_System = appInd
								.createAndGetByObject("abort_collection_confirm_System");
								WebElement elementbyabort_collection_confirm_System = obrowser
								.findElement(byabort_collection_confirm_System);

								if (elementbyabort_collection_confirm_System != null) {
								strStatus += "false";
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\TC_ToaddasystemofFSC_Fail_systemalreadyExistsnapshot.png");
								Thread.sleep(5000);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
								elementbyabort_collection_confirm_System);
								
								Thread.sleep(3000);
								/*strStatus += String.valueOf(
										appInd.setObject(obrowser, "Close_system", "E"));	*/
								By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
								Thread.sleep(3000);
								WebElement elementb = obrowser.findElement(byclickOnClose_system);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
								Thread.sleep(5000);	
								}
								} catch (Exception e) {
									
								}
							
							
						
							String paramValue = oinpuTDtMap.get("Param_1");
							List<WebElement> rows = obrowser
									.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

							for (WebElement rowElement : rows) {
								System.out.println("rowElement.getText()::" +rowElement.getText());
								if (rowElement.getText().equals(paramValue)) {
									System.out.println("rowElement.getText()1111::" +rowElement.getText());
									System.out.println("paramValue:::" +paramValue);
									strStatus += true;
									System.out.println("strStatus:::" +strStatus);
									//break;
								} 

							}
						
						
							
							// ########################################################################
							if (strStatus.contains("false")) {
								//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
									appInd.writeLog("Fail", "'TC_ToaddasystemofFSC' script was failed");
									bolstatus = false;
									strStatus = null;
								} else if (strStatus.contains("true")) {
									appInd.writeLog("Pass", "'TC_ToaddasystemofFSC' script was Successful");
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
							+ "\\Results\\snapshot\\TC_ToaddasystemofFSC_Fail_deuTO_Exception_snapshot.png");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofFSC' method. " + e.getMessage());
				oinpuTDtMap.put("result", "Fail");
				oinputMap.put("result", (HashMap) oinpuTDtMap);
				return oinputMap;
			}
			}
				
	/********************************
	 * Method Name : TC_ToaddasystemofSPI Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofSPI() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofSPI:- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_SPI = appInd.createAndGetByObject("Select_SPI");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_SPI);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnSPI_Systemname = appInd.createAndGetByObject("SPI_Systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnSPI_Systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SPI_Systemname", oinpuTDtMap.get("Param_1")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnSPI_IP = appInd.createAndGetByObject("SPI_IP");
						Thread.sleep(3000);
						WebElement elementq = obrowser.findElement(byclickOnSPI_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementq);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SPI_IP", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
						By byclickOnSPI_Username = appInd.createAndGetByObject("SPI_Username");
						Thread.sleep(3000);
						WebElement elementr = obrowser.findElement(byclickOnSPI_Username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementr);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SPI_Username", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/input")).clear();
						
						By byclickOnSPI_password = appInd.createAndGetByObject("SPI_password");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byclickOnSPI_Username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SPI_password", oinpuTDtMap.get("Param_4")));
						
						/*By byclickOnMapping_Spi = appInd.createAndGetByObject("Mapping_Spi");
						Thread.sleep(3000);
						WebElement elementcc = obrowser.findElement(byclickOnMapping_Spi);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementcc);
						Thread.sleep(5000);*/
						
						/*By byclickOnClick_SPIchkBox = appInd.createAndGetByObject("Click_SPIchkBox");
						Thread.sleep(3000);
						WebElement element11 = obrowser.findElement(byclickOnClick_SPIchkBox);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element11);
						Thread.sleep(5000);*/
						
						/*obrowser.findElement(By.xpath("//*[@id=\"SPIMappingParamModal\"]/div/div[2]/div[2]/div/div/input")).clear();
						By byEnterSPI_Plant = appInd.createAndGetByObject("EnterSPI_Plant");
						Thread.sleep(3000);
						WebElement element88 = obrowser.findElement(byEnterSPI_Plant);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element88);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "EnterSPI_Plant", oinpuTDtMap.get("Param_5")));*/
						
						/*By byclickOnClick_SPI_Add = appInd.createAndGetByObject("Click_SPI_Add");
						Thread.sleep(3000);
						WebElement element13 = obrowser.findElement(byclickOnClick_SPI_Add);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element13);
						Thread.sleep(5000);*/
						
						
						
						
						/*strStatus += String.valueOf(
								appInd.setObject(obrowser, "Save_Experion_system", "E"));*/	
						
						
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						if(element22.isEnabled())
						{	
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddasystemofSPI_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						}
									
							else {
								
								appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										+ "\\Results\\snapshot\\SPI\\Failed_to_launch_IE_browser_snapshot.png");
								appInd.writeLog("Fail", "Failed to launch the IE browser");
								strStatus+=false;
						}
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddasystemofSPI' script was failed");
								bolstatus = false;
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
						+ "\\Results\\snapshot\\TC_ToaddasystemofSPI_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofSPI' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
	
			
						
	/********************************
	 * Method Name : TC_ToaddControllogix Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddControllogix() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddControllogix:- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_Controllogix = appInd.createAndGetByObject("Select_Controllogix");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_Controllogix);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						
						By byclickOnEnter_CT_systemname = appInd.createAndGetByObject("Enter_CT_systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnEnter_CT_systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "SPI_Systemname", oinpuTDtMap.get("Param_1")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnEnter_CTIP = appInd.createAndGetByObject("Enter_CTIP");
						Thread.sleep(3000);
						WebElement elementu = obrowser.findElement(byclickOnEnter_CTIP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementu);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_CTIP", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
						
						By byclickOnEnter_CTusername = appInd.createAndGetByObject("Enter_CTusername");
						Thread.sleep(3000);
						WebElement elementv = obrowser.findElement(byclickOnEnter_CTusername);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementv);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_CTusername", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/input")).clear();
						
						By byclickOnEnter_CTpassword = appInd.createAndGetByObject("Enter_CTpassword");
						Thread.sleep(3000);
						WebElement elementw = obrowser.findElement(byclickOnEnter_CTpassword);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementw);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_CTpassword", oinpuTDtMap.get("Param_4")));
	
						obrowser.findElement(By.xpath("//*[@id=\"ControlLogixDataFilePath\"]/div[2]/input")).clear();
						By byclickOnEnter_CTfilepath = appInd.createAndGetByObject("Enter_CTfilepath");
						Thread.sleep(3000);
						WebElement elementt = obrowser.findElement(byclickOnEnter_CTfilepath);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementt);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_CTfilepath", oinpuTDtMap.get("Param_5")));
						
						
						/*strStatus += String.valueOf(
								appInd.setObject(obrowser, "Save_Experion_system", "E"));	*/
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddControllogix_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddControllogix' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ToaddControllogix' script was Successful");
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
						+ "\\Results\\snapshot\\TC_ToaddControllogix_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddControllogix' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
	
	
	/********************************
	 * Method Name : TC_ToaddasystemofPHD Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofPHD() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofPHD:- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_PHD = appInd.createAndGetByObject("Select_PHD");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_PHD);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						
						By byclickOnPHD_systemname = appInd.createAndGetByObject("PHD_systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnPHD_systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "PHD_systemname", oinpuTDtMap.get("Param_1")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnPHD_IP = appInd.createAndGetByObject("PHD_IP");
						Thread.sleep(3000);
						WebElement elementi = obrowser.findElement(byclickOnPHD_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementi);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "PHD_IP", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
						By byclickOnPHD_username = appInd.createAndGetByObject("PHD_username");
						Thread.sleep(3000);
						WebElement elementj = obrowser.findElement(byclickOnPHD_username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "PHD_username", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/input")).clear();
						By byclickOnPHD_password = appInd.createAndGetByObject("PHD_password");
						Thread.sleep(3000);
						WebElement element33 = obrowser.findElement(byclickOnPHD_password);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element33);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "PHD_password", oinpuTDtMap.get("Param_4")));
						
						obrowser.findElement(By.xpath("//*[@id=\"PHDServerName\"]")).clear();
						By byclickOnPHD_hostname = appInd.createAndGetByObject("PHD_hostname");
						Thread.sleep(3000);
						WebElement elementl = obrowser.findElement(byclickOnPHD_hostname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementl);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "PHD_hostname", oinpuTDtMap.get("Param_5")));
						
						
						/*strStatus += String.valueOf(
								appInd.setObject(obrowser, "Save_Experion_system", "E"));	*/
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddasystemofPHD_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddasystemofPHD' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ToaddasystemofPHD' script was Successful");
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
						+ "\\Results\\snapshot\\TC_ToaddasystemofPHD_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofPHD' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
	
	/********************************
	 * Method Name : TC_ToaddasystemofOSIPI Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ToaddasystemofOSIPI() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ToaddasystemofOSIPI :- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						By byclickOnClick_AddSystem = appInd.createAndGetByObject("Click_AddSystem");
						Thread.sleep(3000);
						WebElement elementc = obrowser.findElement(byclickOnClick_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementc);
						Thread.sleep(5000);
						
						By byclickOnSelect_AddSystem = appInd.createAndGetByObject("Select_AddSystem");
						Thread.sleep(3000);
						WebElement elementk = obrowser.findElement(byclickOnSelect_AddSystem);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementk);
						Thread.sleep(5000);		
						
						By byclickOnSelect_OSIPI = appInd.createAndGetByObject("Select_OSIPI");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_OSIPI);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[1]/div[2]/div/input")).clear();
						By byclickOnEnter_OSIPI_systemname = appInd.createAndGetByObject("Enter_OSIPI_systemname");
						Thread.sleep(3000);
						WebElement elementh = obrowser.findElement(byclickOnEnter_OSIPI_systemname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementh);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_OSIPI_systemname", oinpuTDtMap.get("Param_1")));
						
						
						
						obrowser.findElement(By.xpath("//*[@id=\"OSIPIServerName\"]")).clear();
						By byclickOnEnter_OSIPI_Servername = appInd.createAndGetByObject("Enter_OSIPI_Servername");
						Thread.sleep(3000);
						WebElement elementi = obrowser.findElement(byclickOnEnter_OSIPI_Servername);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementi);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_OSIPI_Servername", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"ComputerName\"]")).clear();
						By byclickOnEnter_OSIPI_IP = appInd.createAndGetByObject("Enter_OSIPI_IP");
						Thread.sleep(3000);
						WebElement elementj = obrowser.findElement(byclickOnEnter_OSIPI_IP);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_OSIPI_IP", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[4]/div/div[2]/div/input")).clear();
						By byclickOnOSIPI_Username = appInd.createAndGetByObject("OSIPI_Username");
						Thread.sleep(3000);
						WebElement elementn = obrowser.findElement(byclickOnOSIPI_Username);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementn);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "OSIPI_Username", oinpuTDtMap.get("Param_4")));
						
						obrowser.findElement(By.xpath("//*[@id=\"content_modal\"]/div[1]/div[5]/div/div[2]/input")).clear();
						
						By byclickOnOSIPI_password = appInd.createAndGetByObject("OSIPI_password");
						Thread.sleep(3000);
						WebElement elements = obrowser.findElement(byclickOnOSIPI_password);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elements);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "OSIPI_password", oinpuTDtMap.get("Param_5")));	
						
						/*strStatus += String.valueOf(
								appInd.setObject(obrowser, "Save_Experion_system", "E"));	*/
						
						By byClick_On_Savebutton = appInd.createAndGetByObject("Click_On_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byClick_On_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						

						try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "false";
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_ToaddasystemofOSIPI_Fail_systemalreadyExistsnapshot.png");
							Thread.sleep(5000);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement elementb = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						String paramValue = oinpuTDtMap.get("Param_1");
						List<WebElement> rows = obrowser
								.findElements(By.xpath("//div[@class='panel panel-default inside_panel']/div[@class='panel-heading']/span[@class='ng-binding']"));

						for (WebElement rowElement : rows) {
							System.out.println("rowElement.getText()::" +rowElement.getText());
							if (rowElement.getText().equals(paramValue)) {
								System.out.println("rowElement.getText()1111::" +rowElement.getText());
								System.out.println("paramValue:::" +paramValue);
								strStatus += true;
								System.out.println("strStatus:::" +strStatus);
								//break;
							} 

						}
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_ToaddasystemofOSIPI' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_ToaddasystemofOSIPI' script was Successful");
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
						+ "\\Results\\snapshot\\TC_ToaddasystemofOSIPI_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_ToaddasystemofOSIPI' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
	
						
	/********************************
	 * Method Name : TC_Toaddtheglobalsetting Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Toaddtheglobalsetting() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Toaddtheglobalsetting :- started*****");
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
				// ################################################################################
						Thread.sleep(5000);
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Menu_bar", "E"));
						//strStatus += String.valueOf(appInd.setObject(obrowser, "Click_DataCollection", "E"));
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byclickOnClick_DataCollection = appInd.createAndGetByObject("Click_DataCollection");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byclickOnClick_DataCollection);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
						//Thread.sleep(5000);	
						Thread.sleep(30000);
						
						By byclickOnSelect_globalsettings = appInd.createAndGetByObject("Select_globalsettings");
						Thread.sleep(3000);
						WebElement elementx = obrowser.findElement(byclickOnSelect_globalsettings);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementx);
						Thread.sleep(5000);					
						
						By byclickOnSelect_performance = appInd.createAndGetByObject("Select_performance");
						Thread.sleep(3000);
						WebElement elementr = obrowser.findElement(byclickOnSelect_performance);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementr);
						Thread.sleep(5000);	
						
						obrowser.findElement(By.xpath("//*[@id=\"IAASettings\"]/div[1]/div[1]/div/div[2]/div/input")).clear();
						By byclickOnEnter_customername = appInd.createAndGetByObject("Enter_customername");
						Thread.sleep(3000);
						WebElement elements = obrowser.findElement(byclickOnEnter_customername);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elements);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_customername", oinpuTDtMap.get("Param_1")));	
						
						obrowser.findElement(By.xpath("//*[@id=\"IAASettings\"]/div[1]/div[2]/div/div[2]/div/input")).clear();
						By byclickOnEnter_sitename = appInd.createAndGetByObject("Enter_sitename");
						Thread.sleep(3000);
						WebElement elementv = obrowser.findElement(byclickOnEnter_sitename);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementv);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_sitename", oinpuTDtMap.get("Param_2")));
						
						obrowser.findElement(By.xpath("//*[@id=\"IAASettings\"]/div[1]/div[3]/div/div[2]/div/input")).clear();
						By byclickOnEnter_location = appInd.createAndGetByObject("Enter_location");
						Thread.sleep(3000);
						WebElement elementb = obrowser.findElement(byclickOnEnter_location);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_location", oinpuTDtMap.get("Param_3")));
						
						obrowser.findElement(By.xpath("//*[@id=\"IAASettings\"]/div[1]/div[4]/div/div[2]/div/input")).clear();
						
						By byclickOnEnter_contactname = appInd.createAndGetByObject("Enter_contactname");
						Thread.sleep(3000);
						WebElement elementz = obrowser.findElement(byclickOnEnter_contactname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementz);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_contactname", oinpuTDtMap.get("Param_4")));
						
						obrowser.findElement(By.xpath("//*[@id=\"IAASettings\"]/div[1]/div[5]/div/div[2]/div/input")).clear();
						
						By byclickOnEnter_honeywellcontactname = appInd.createAndGetByObject("Enter_honeywellcontactname");
						Thread.sleep(3000);
						WebElement elemento = obrowser.findElement(byclickOnEnter_honeywellcontactname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elemento);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_honeywellcontactname", oinpuTDtMap.get("Param_5")));
						
						
						By byclickOnClick_switchconfiguration = appInd.createAndGetByObject("Click_switchconfiguration");
						Thread.sleep(3000);
						WebElement elemente = obrowser.findElement(byclickOnClick_switchconfiguration);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elemente);
						Thread.sleep(5000);
						
						obrowser.findElement(By.xpath("//*[@id=\"SwitchSettings\"]/div[1]/div[2]/div/input[1]")).clear();
						By byclickOnEnter_raw_community = appInd.createAndGetByObject("Enter_raw_community");
						Thread.sleep(3000);
						WebElement element4 = obrowser.findElement(byclickOnEnter_honeywellcontactname);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element4);
						Thread.sleep(5000);
						strStatus += String.valueOf(
								appInd.setObject(obrowser, "Enter_raw_community", oinpuTDtMap.get("Param_6")));
						
						By byGlobal_Savebutton = appInd.createAndGetByObject("Global_Savebutton");
						Thread.sleep(3000);
						WebElement element22 = obrowser.findElement(byGlobal_Savebutton);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element22);
						Thread.sleep(5000);
						
try {
							
							By byabort_collection_confirm_System = appInd
							.createAndGetByObject("abort_collection_confirm_System");
							WebElement elementbyabort_collection_confirm_System = obrowser
							.findElement(byabort_collection_confirm_System);

							if (elementbyabort_collection_confirm_System != null) {
							strStatus += "true";
							
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
							elementbyabort_collection_confirm_System);
							
							Thread.sleep(3000);
							/*strStatus += String.valueOf(
									appInd.setObject(obrowser, "Close_system", "E"));	*/
							By byclickOnClose_system = appInd.createAndGetByObject("Close_system");
							Thread.sleep(3000);
							WebElement element9 = obrowser.findElement(byclickOnClose_system);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element9);
							Thread.sleep(5000);	
							}
							} catch (Exception e) {
								
							}
						
						
					
						
						
						// ########################################################################
						if (strStatus.contains("false")) {
							//	appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
										//+ "\\Results\\snapshot\\TC_addasystemofExperionIntegratedTPS_Fail_snapshot.png");
								appInd.writeLog("Fail", "'TC_Toaddtheglobalsetting' script was failed");
								bolstatus = false;
								strStatus = null;
							} else if (strStatus.contains("true")) {
								appInd.writeLog("Pass", "'TC_Toaddtheglobalsetting' script was Successful");
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
						+ "\\Results\\snapshot\\TC_Toaddtheglobalsetting_Fail_deuTO_Exception_snapshot.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			appInd.writeLog("Exception", "Exception while executing 'TC_Toaddtheglobalsetting' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put("result", (HashMap) oinpuTDtMap);
			return oinputMap;
		}
		}
	
	
//=====================================================================================//								
	//Filter test cases(Engineering Anomaly) by Janhavi//
	
	
	/********************************
	 * Method Name : TC_Tocreateprivatefilter Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Tocreateprivatefilter() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Tocreateprivatefilter :- started*****");
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
				// ################################################################################
						
						Thread.sleep(5000);
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byEngineering_Anomaly = appInd.createAndGetByObject("Engineering_Anomaly");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byEngineering_Anomaly);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
					//#################################################################################
						//Selecting System
						List <WebElement> system= obrowser.findElements(By.xpath("\r\n" + 
										"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li/a"));
						System.out.println(system.size());
						
						String dropDown=null;
						int row;
						for (row = 1;row<=system.size(); row++) {
							try {
								By bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
								WebElement elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_systemdropdown);
								 dropDown = obrowser
										.findElement(By.xpath(
												"\r\n" + 
												"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"))
										.getText();
								 System.out.println("dropdowm"+dropDown);
						String sysName = oinpuTDtMap.get("Param_1");
						
							if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))

							{							WebElement element = obrowser.findElement(
									By.xpath("\r\n" + 
											"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"));
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);

							strStatus+=true;
							break;
							}
//							strStatus+=false;
//							appInd.writeLog("Fail", "System can not be find");
//						}
							
							bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
							elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_systemdropdown);
							}
							
								catch(Exception e)
									{strStatus+=false;
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\Anamoly\\TC_TC_Tocreateprivatefilter_systemnotfound_Fail_snapshot.png");
									appInd.writeLog("Fail", "'TC_Tocreateprivatefilter' script was failed");
									}
							}	
						
						
						if(row>system.size())
						{
							strStatus+=false;
						}
						else
						{
							try {
							//###########################################################################
								//Click on Filter
							By byClick_filter = appInd.createAndGetByObject("Click_filter");
							Thread.sleep(2000);
							WebElement element1 = obrowser.findElement(byClick_filter);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
							Thread.sleep(3000);
							//###########################################################################
							//Select Anomaly name
							By checkBox_List = appInd.createAndGetByObject("AnomalyName_CheckBox_List");
							List<WebElement> anomalyName_CheckBox_List = obrowser.findElements(checkBox_List);
							
							for(int i=0;i<2;i++) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",anomalyName_CheckBox_List.get(i));
								Thread.sleep(5000);
							}
								
								By bySelect_Private = appInd.createAndGetByObject("Select_Private");
								Thread.sleep(3000);
								WebElement element2 = obrowser.findElement(bySelect_Private);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", element2);
								Thread.sleep(3000);
							//##########################################################################
								//Enter Filter name
								By byEnter_filtername = appInd.createAndGetByObject("Enter_filtername");
								Thread.sleep(3000);
								WebElement elementk = obrowser.findElement(byEnter_filtername);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elementk);
								obrowser.findElement(By.xpath("//*[@id=\"formDefectFilter\"]/div[2]/div[1]/div[2]/div[2]/input")).clear();
								appInd.setObject(obrowser, "Enter_filtername", oinpuTDtMap.get("Param_2"));
								Thread.sleep(3000);
							//##############################################################################
								//Enter Description
								By byEnter_description = appInd.createAndGetByObject("Enter_description");
								Thread.sleep(3000);
								WebElement elements = obrowser.findElement(byEnter_description);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elements);
								obrowser.findElement(By.xpath("//*[@id=\"formDefectFilter\"]/div[2]/div[1]/div[2]/div[3]/input")).clear();
								appInd.setObject(obrowser, "Enter_description",  oinpuTDtMap.get("Param_3"));
								Thread.sleep(3000);
							//###############################################################################
								//Click on Save filter
								By byClick_SaveFilter_Anomaly = appInd.createAndGetByObject("Click_SaveFilter_Anomaly");
								Thread.sleep(3000);
								WebElement element5 = obrowser.findElement(byClick_SaveFilter_Anomaly);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", element5);
								Thread.sleep(3000);
							//##################################################################################	
								//Logout
								By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
								Thread.sleep(3000);
								WebElement elementb = obrowser.findElement(byclickOnLogout_btn_OK);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
								Thread.sleep(5000);
							//####################################################################################
								//Login with another user
								strStatus += String.valueOf(
										appInd.clearAndSetObject(obrowser, "Login_txtbx_Username", oinpuTDtMap.get("Param_4")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.clearAndSetObject(obrowser, "Login_txtbx_Password", oinpuTDtMap.get("Param_5")));
								// click on the ok button
								By byclickOnLogin_btn_OK = appInd.createAndGetByObject("Login_btn_OK");
								WebElement element = obrowser.findElement(byclickOnLogin_btn_OK);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
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
								
								Thread.sleep(5000);
								By clickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
								Thread.sleep(3000);
								WebElement element8 = obrowser.findElement(clickOnMenu_bar);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element8);
								Thread.sleep(5000);
								By Engineering_Anomaly = appInd.createAndGetByObject("Engineering_Anomaly");
								Thread.sleep(3000);
								WebElement elementj = obrowser.findElement(Engineering_Anomaly);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
							//###################################################################################
								//Select Sysyem 

								List <WebElement> system1= obrowser.findElements(By.xpath("\r\n" + 
												"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li/a"));
								System.out.println(system.size());
								
								String dropDown1=null;
								int row1;
								for (row = 1;row<=system.size(); row++) {
									try {
										By bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
										WebElement elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbySelect_systemdropdown);
										 dropDown = obrowser
												.findElement(By.xpath(
														"\r\n" + 
														"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"))
												.getText();
										 System.out.println("dropdowm"+dropDown);
								String sysName = oinpuTDtMap.get("Param_1");
									if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))

									{							WebElement elementm = obrowser.findElement(
											By.xpath("\r\n" + 
													"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementm);

									strStatus+=true;
									break;
									}
//									strStatus+=false;
//									appInd.writeLog("Fail", "System can not be find");
//								}
									
									bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
									elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_systemdropdown);
									}
									
										catch(Exception e)
											{strStatus+=false;
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshot\\Anamoly\\TC_Tocreateprivatefilter_system_Fail_snapshot.png");
											appInd.writeLog("Fail", "'TC_Tocreateprivatefilter' script was failed");
											}
									}
								//#############################################################################
								//Click on saved filter icon
								By byVerify_Filter = appInd.createAndGetByObject("Verify_Filter");
								Thread.sleep(3000);
								WebElement elementp = obrowser.findElement(byVerify_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elementp);
								Thread.sleep(3000);
							//#################################################################################
								//Verify saved filter list
								By bySavedFilter_Name_List = appInd.createAndGetByObject("SavedFilter_Name_List");
								Thread.sleep(2000);
								List<WebElement> savedFilter_Name_List = obrowser.findElements(bySavedFilter_Name_List);
								for(int j=0;j<savedFilter_Name_List.size();j++) {
									if(savedFilter_Name_List.get(j).getText().contains(oinpuTDtMap.get("Param_2"))) {
										strStatus = "false";
										 System.out.println("strStatus:::"+ strStatus);
										 break;
									 }
									strStatus = "true";
								}}catch(Exception e) {
									e.printStackTrace();
									strStatus += false;
									appInd.writeLog("Fail", "Fiter is not Saved Successfully");	
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir"));
								}
								// ########################################################################
								if (strStatus.contains("false")) {
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\TC_Tocreateprivatefilter\\Anamoly.png");
									appInd.writeLog("Fail", "'TC_Tocreateprivatefilter' script was failed");
									bolstatus = false;
									strStatus = null;
								} else  {
									appInd.writeLog("Pass", "'TC_Tocreateprivatefilter' script was Successful");
									bolstatus = true;
									strStatus = null;
								}
							}
						
						}
					}
							 else {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Tocreateprivatefilter\\Failed_to_launch_IE_browser_snapshort.png");
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
					} // for loop
					return oinputMap;
						
				
					} catch (Exception e) {
					appInd.writeLog("Exception", "Exception while executing 'TC_Tocreateprivatefilter' method. " + e.getMessage());
					oinpuTDtMap.put("result", "Fail");
					//oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
					return oinputMap;
				} finally {
					System.out.println("end");
					return oinputMap;
				}
				
				}	
	
	
	/********************************
	 * Method Name : TC_Tocreatepublicfilter Purpose : This method will launch the home
	 * screen Author : Janhavi TK Reviewer : Date of Creation : 29-Oct-2018 Date of
	 * modification : 21-Nov-2018 ******************************
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Tocreatepublicfilter() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Tocreatepublicfilter :- started*****");
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
				// ################################################################################
						
						Thread.sleep(5000);
						
						By byclickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
						Thread.sleep(3000);
						WebElement element7 = obrowser.findElement(byclickOnMenu_bar);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element7);
						Thread.sleep(5000);
						By byEngineering_Anomaly = appInd.createAndGetByObject("Engineering_Anomaly");
						Thread.sleep(3000);
						WebElement element6 = obrowser.findElement(byEngineering_Anomaly);
						((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element6);
				//###########################################################################################		
				//Selecting System		
						List <WebElement> system= obrowser.findElements(By.xpath("\r\n" + 
										"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li/a"));
						System.out.println(system.size());
						String dropDown=null;
						int row;
						for (row = 1;row<=system.size(); row++) {
							try {
								By bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
								WebElement elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
										elementbySelect_systemdropdown);
								 dropDown = obrowser
										.findElement(By.xpath(
												"\r\n" + 
												"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"))
										.getText();
								 System.out.println("dropdowm"+dropDown);
						String sysName = oinpuTDtMap.get("Param_1");
						
							if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))

							{							WebElement element = obrowser.findElement(
									By.xpath("\r\n" + 
											"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"));
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
							strStatus+=true;
							break;
							}
//							strStatus+=false;
//							appInd.writeLog("Fail", "System can not be find");
//						}
							bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
							elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_systemdropdown);
							}
								catch(Exception e)
									{strStatus+=false;
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\Anamoly\\TC_Tocreatepublicfilter_invalidCredential_Fail_snapshot.png");
									appInd.writeLog("Fail", "'TC_Tocreatepublicfilter' script was failed");
									}
							}	
						if(row>system.size())
						{
							strStatus+=false;
						}
						else
						{
							try {
					//##########################################################################
					    //Click on filter			
							By byClick_filter = appInd.createAndGetByObject("Click_filter");
							Thread.sleep(2000);
							WebElement element1 = obrowser.findElement(byClick_filter);
							((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element1);
							Thread.sleep(3000);
					//#############################################################################
					    //Select Anomaly name		
							By checkBox_List = appInd.createAndGetByObject("AnomalyName_CheckBox_List");
							List<WebElement> anomalyName_CheckBox_List = obrowser.findElements(checkBox_List);
							
							for(int i=0;i<2;i++) {
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",anomalyName_CheckBox_List.get(i));
								Thread.sleep(5000);
							}
					//##################################################################################
					   //Select public		
								By bySelect_Public = appInd.createAndGetByObject("Select_Public");
								Thread.sleep(3000);
								WebElement element2 = obrowser.findElement(bySelect_Public);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", element2);
								Thread.sleep(3000);
					//#####################################################################################
					        //Enter filtername			
								By byEnter_filtername = appInd.createAndGetByObject("Enter_filtername");
								Thread.sleep(3000);
								WebElement elementk = obrowser.findElement(byEnter_filtername);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elementk);
								obrowser.findElement(By.xpath("//*[@id=\"formDefectFilter\"]/div[2]/div[1]/div[2]/div[2]/input")).clear();
								appInd.setObject(obrowser, "Enter_filtername", oinpuTDtMap.get("Param_2"));
								Thread.sleep(3000);
				        //#######################################################################################
				              //Enter Description				
								By byEnter_description = appInd.createAndGetByObject("Enter_description");
								Thread.sleep(3000);
								WebElement elements = obrowser.findElement(byEnter_description);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elements);
								obrowser.findElement(By.xpath("//*[@id=\"formDefectFilter\"]/div[2]/div[1]/div[2]/div[3]/input")).clear();
								appInd.setObject(obrowser, "Enter_description",  oinpuTDtMap.get("Param_3"));
								Thread.sleep(3000);
				       //##################################################################################
				             //Click Save Filter				
								By byClick_SaveFilter_Anomaly = appInd.createAndGetByObject("Click_SaveFilter_Anomaly");
								Thread.sleep(3000);
								WebElement element5 = obrowser.findElement(byClick_SaveFilter_Anomaly);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", element5);
								Thread.sleep(3000);
				         //#######################################################################################
						    //Logout		
								
								By byclickOnLogout_btn_OK = appInd.createAndGetByObject("Logout_btn_OK");
								Thread.sleep(3000);
								WebElement elementb = obrowser.findElement(byclickOnLogout_btn_OK);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementb);
								Thread.sleep(5000);
							//############################################################################################
								//Login with another user
								strStatus += String.valueOf(
										appInd.clearAndSetObject(obrowser, "Login_txtbx_Username", oinpuTDtMap.get("Param_4")));
								// Set the Password value
								strStatus += String.valueOf(
										appInd.clearAndSetObject(obrowser, "Login_txtbx_Password", oinpuTDtMap.get("Param_5")));
								// click on the ok button
								By byclickOnLogin_btn_OK = appInd.createAndGetByObject("Login_btn_OK");
								WebElement element = obrowser.findElement(byclickOnLogin_btn_OK);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element);
								
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
								
								Thread.sleep(5000);
								
								By clickOnMenu_bar = appInd.createAndGetByObject("Menu_bar");
								Thread.sleep(3000);
								WebElement element8 = obrowser.findElement(clickOnMenu_bar);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", element8);
								Thread.sleep(5000);
								By Engineering_Anomaly = appInd.createAndGetByObject("Engineering_Anomaly");
								Thread.sleep(3000);
								WebElement elementj = obrowser.findElement(Engineering_Anomaly);
								((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementj);
								

								List <WebElement> system1= obrowser.findElements(By.xpath("\r\n" + 
												"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li/a"));
								System.out.println(system.size());
								
								String dropDown1=null;
								int row1;
								for (row = 1;row<=system.size(); row++) {
									try {
										By bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
										WebElement elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
										((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",
												elementbySelect_systemdropdown);
										 dropDown = obrowser
												.findElement(By.xpath(
														"\r\n" + 
														"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"))
												.getText();
										 System.out.println("dropdowm"+dropDown);
								String sysName = oinpuTDtMap.get("Param_1");
								
									if(dropDown.equalsIgnoreCase(oinpuTDtMap.get("Param_1")))

									{							WebElement elementm = obrowser.findElement(
											By.xpath("\r\n" + 
													"//*[@id=\"frmMain\"]/div[2]/div/div/ul/li[" + row + "]/a"));
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();", elementm);

									strStatus+=true;
									break;
									}
//									strStatus+=false;
//									appInd.writeLog("Fail", "System can not be find");
//								}
									
									bySelect_systemdropdown = appInd.createAndGetByObject("Select_systemdropdown");
									elementbySelect_systemdropdown = obrowser.findElement(bySelect_systemdropdown);
									((JavascriptExecutor) obrowser).executeScript("arguments[0].click();",elementbySelect_systemdropdown);
									}
									
										catch(Exception e)
											{strStatus+=false;
											appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
													+ "\\Results\\snapshot\\Anamoly\\TC_Tocreatepublicfilter_invalidCredential_Fail_snapshot.png");
											appInd.writeLog("Fail", "'TC_Tocreatepublicfilter' script was failed");
											}
									}	
								//######################################################################################
								//Click on saved filter icon
								By byVerify_Filter = appInd.createAndGetByObject("Verify_Filter");
								Thread.sleep(3000);
								WebElement elementp = obrowser.findElement(byVerify_Filter);
								((JavascriptExecutor) obrowser).executeScript("arguments[0]. click();", elementp);
								Thread.sleep(3000);
								//#######################################################################################
								//Check the saved filter list
								By bySavedFilter_Name_List = appInd.createAndGetByObject("SavedFilter_Name_List");
								Thread.sleep(2000);
								List<WebElement> savedFilter_Name_List = obrowser.findElements(bySavedFilter_Name_List);
								for(int j=0;j<savedFilter_Name_List.size();j++) {
									if(savedFilter_Name_List.get(j).getText().contains(oinpuTDtMap.get("Param_2"))) {
										strStatus = "true";
										 System.out.println("strStatus:::"+ strStatus);
										 break;
									 }
									strStatus = "false";
								}}catch(Exception e) {
									e.printStackTrace();
									strStatus += false;
									appInd.writeLog("Fail", "Fiter is not Saved Successfully");	
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir"));
								}
								// ########################################################################
								if (strStatus.contains("false")) {
									appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
											+ "\\Results\\snapshot\\TC_Tocreatepublicfilter\\Anamoly.png");
									appInd.writeLog("Fail", "'TC_Tocreatepublicfilter' script was failed");
									bolstatus = false;
									strStatus = null;
								} else  {
									appInd.writeLog("Pass", "'TC_Tocreatepublicfilter' script was Successful");
									bolstatus = true;
									strStatus = null;
								}
							}
						
						}
					}
							 else {
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Tocreatepublicfilter\\Failed_to_launch_IE_browser_snapshort.png");
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
					} // for loop
					return oinputMap;
						
				
					} catch (Exception e) {
					appInd.writeLog("Exception", "Exception while executing 'TC_Tocreatepublicfilter' method. " + e.getMessage());
					oinpuTDtMap.put("result", "Fail");
					//oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
					return oinputMap;
				} finally {
					System.out.println("end");
					return oinputMap;
				}
				
				}	
	
	
	
	
								
	
	
}
