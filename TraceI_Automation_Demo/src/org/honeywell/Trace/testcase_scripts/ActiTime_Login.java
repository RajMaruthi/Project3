package org.honeywell.Trace.testcase_scripts;

import java.util.HashMap;
import java.util.Map;

import org.honeywell.Trace.common_methods.GenricApplicationMethods;
import org.honeywell.Trace.driver.DriverScript;
import org.honeywell.Trace.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActiTime_Login
{
	
	
	
	WebDriver obrowser = null;
	GenricApplicationMethods oGenericAppmethods = new GenricApplicationMethods();
	DriverScript oDriver = new DriverScript();
	Utility appInd = new Utility();
	String strTCID = oDriver.getTestcaseId();
	String strStatus = null;
	
	
	
	
	/********************************
	 * Method Name : TC_ACT_Login
	 * Purpose : this will login to actitime application
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ACT_Login() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ACT_Login:- started*****");
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
						
							String strUsername=oinpuTDtMap.get("Param_1");
							String strPassword=oinpuTDtMap.get("Param_2");
							
							strStatus+=appInd.setObject(obrowser, "Act_Username", strUsername);
							Thread.sleep(2000);
							strStatus+=appInd.setObject(obrowser, "Act_Password", strPassword);
							Thread.sleep(2000);
							strStatus+=appInd.clickObject(obrowser, "Act_Login");
							strStatus+=appInd.isElementPresent(obrowser, "Act_Logout");
							strStatus+=appInd.clickObject(obrowser, "Getting_Started_Shortcut");
							//strStatus+=appInd.clickObject(obrowser, "Act_Logout");
						
					
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ACT_Login' script was failed");
							System.out.println("'TC_ACT_Login' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ActiTimeLogin\\TC_ACT_Login.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ACT_Login' script was Successful");
							System.out.println("'TC_ACT_Login' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ActiTimeLogin\\Failed_to_launch_Chrome_browser_snapshot.png");
					appInd.writeLog("Fail", "Failed to launch the Chrome browser");
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
				strcommonCountvalue = null;
			} // for loop
			return oinputMap;
			
			
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_ACT_Login' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}

	
	
	
	
	
	/********************************
	 * Method Name : TC_ACT_Login_MutipleUser
	 * Purpose : this will login to actitime application with multiple users
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ACT_Login_MutipleUser() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ACT_Login_MutipleUser:- started*****");
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
						
							try
							{
							
								String strUsername=oinpuTDtMap.get("Param_1");
								String strPassword=oinpuTDtMap.get("Param_2");
								
								strStatus+=appInd.setObject(obrowser, "Act_Username", strUsername);
								Thread.sleep(2000);
								strStatus+=appInd.setObject(obrowser, "Act_Password", strPassword);
								Thread.sleep(2000);
								strStatus+=appInd.clickObject(obrowser, "Act_Login");
								if(appInd.isElementPresent(obrowser, "Act_LogoutBtn"))
								{
									strStatus+=true;
									appInd.clickObject(obrowser, "Act_LogoutBtn");
								}
								else
								{
									strStatus+=false;
								}
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						
					
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ACT_Login_MutipleUser' script was failed");
							System.out.println("'TC_ACT_Login_MutipleUser' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ActiTimeLogin\\TC_ACT_Login.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ACT_Login_MutipleUser' script was Successful");
							System.out.println("'TC_ACT_Login_MutipleUser' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ActiTimeLogin\\Failed_to_launch_Chrome_browser_snapshot.png");
					appInd.writeLog("Fail", "Failed to launch the Chrome browser");
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
				strcommonCountvalue = null;
			} // for loop
			return oinputMap;
			
			
		} catch (Exception e) {
			appInd.writeLog("Exception", "Exception while executing 'TC_ACT_Login_MutipleUser' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}

	
	
	

}//class end
