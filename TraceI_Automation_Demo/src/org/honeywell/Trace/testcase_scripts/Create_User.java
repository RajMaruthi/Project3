package org.honeywell.Trace.testcase_scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.honeywell.Trace.common_methods.GenricApplicationMethods;
import org.honeywell.Trace.driver.DriverScript;
import org.honeywell.Trace.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Create_User 
{
	
	
	WebDriver obrowser = null;
	GenricApplicationMethods oGenericAppmethods = new GenricApplicationMethods();
	DriverScript oDriver = new DriverScript();
	Utility appInd = new Utility();
	String strTCID = oDriver.getTestcaseId();
	String strStatus = null;
	
	
	
	
	/********************************
	 * Method Name : TC_ACT_Create_User
	 * Purpose : this will create the user
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ACT_Create_User() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ACT_Create_User:- started*****");
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
						
							String strFirstname=oinpuTDtMap.get("Param_1");
							String strLastname=oinpuTDtMap.get("Param_2");
							String strEmail=oinpuTDtMap.get("Param_3");
							String strUsername=oinpuTDtMap.get("Param_4");
							String strPassword=oinpuTDtMap.get("Param_5");
							String strRetypePassword=oinpuTDtMap.get("Param_6");
							
							//It will click on the User label
							strStatus+=appInd.clickObject(obrowser, "Act_User_Click");
							
							//It will click on the Add users
							strStatus+=appInd.clickObject(obrowser, "Act_Add_User");
							
							//strStatus+=appInd.setObject(obrowser, "FirstName", strFirstname);
							strStatus+=appInd.clearAndSetObject(obrowser, "FirstName", strFirstname);
							
							
					
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ACT_Create_User' script was failed");
							System.out.println("'TC_ACT_Create_User' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ActiTimeLogin\\TC_ACT_Login.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ACT_Create_User' script was Successful");
							System.out.println("'TC_ACT_Create_User' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ActiTimeCreateUser\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_ACT_Create_User' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}

		
	
	
	

	/********************************
	 * Method Name : TC_ACT_AddTask
	 * Purpose : this will delete the user
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 10-June-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ACT_AddTask() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ACT_AddTask:- started*****");
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
						
						String strCustName=oinpuTDtMap.get("Param_1");
						String strCustdescription=oinpuTDtMap.get("Param_2");
						
						//It will create the new customer
						strStatus+=oGenericAppmethods.create_Customer(obrowser, strCustName, strCustdescription);
						
						
						
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ACT_AddTask' script was failed");
							System.out.println("'TC_ACT_AddTask' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ActiTimeCreatetask\\TC_ACT_Login.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ACT_AddTask' script was Successful");
							System.out.println("'TC_ACT_AddTask' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ActiTimeCreateTask\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_ACT_AddTask' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	
	
	
	
	
	
	/********************************
	 * Method Name : TC_ACT_Delete_User
	 * Purpose : this will delete the user
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 10-June-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_ACT_Delete_User() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_ACT_Delete_User:- started*****");
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
						
						strStatus+=appInd.clickObject(obrowser, "Click_User");
						strStatus+=appInd.clickObject(obrowser, "Click_Delete_Button");
						Thread.sleep(2000);
						obrowser.switchTo().alert().accept();
					
					
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_ACT_Delete_User' script was failed");
							System.out.println("'TC_ACT_Delete_User' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\ActiTimeDeleteUser\\TC_ACT_Login.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_ACT_Delete_User' script was Successful");
							System.out.println("'TC_ACT_Delete_User' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\ActiTimeDeleteUser\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_ACT_Delete_User' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	

}//class close
