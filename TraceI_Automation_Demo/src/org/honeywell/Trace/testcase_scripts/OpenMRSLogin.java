package org.honeywell.Trace.testcase_scripts;

import java.util.HashMap;
import java.util.Map;

import org.honeywell.Trace.common_methods.GenricApplicationMethods;
import org.honeywell.Trace.driver.DriverScript;
import org.honeywell.Trace.utility.Utility;
import org.openqa.selenium.WebDriver;

public class OpenMRSLogin 
{
	
	
	WebDriver obrowser = null;
	GenricApplicationMethods oGenericAppmethods = new GenricApplicationMethods();
	DriverScript oDriver = new DriverScript();
	Utility appInd = new Utility();
	String strTCID = oDriver.getTestcaseId();
	String strStatus = null;
	
	
	
	
	/********************************
	 * Method Name : TC_Login_MRS
	 * Purpose : this will login to Open MRS application
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Login_MRS() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Login_MRS:- started*****");
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
						
						strStatus+=appInd.setObject(obrowser, "OpenMRS_Username", strUsername);
						strStatus+=appInd.setObject(obrowser, "OpenMRS_Password", strPassword);
						strStatus+=appInd.clickObject(obrowser, "OpenMRS_LoginBtn");
						
											
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_Login_MRS' script was failed");
							System.out.println("'TC_Login_MRS' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Login_MRS\\TC_Login_MRS.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Login_MRS' script was Successful");
							System.out.println("'TC_Login_MRS' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_Login_MRS\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_Login_MRS' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	
	
	/********************************
	 * Method Name : TC_Login_MRS
	 * Purpose : this will login to Open MRS application
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Frame_Automate() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Frame_Automate:- started*****");
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
						
						strStatus+=appInd.clickObject(obrowser, "FrameSet_Tab");
						strStatus+=appInd.SwitchToFrame(obrowser, "ParentFrame");
						String strParent=appInd.getParentWindowId(obrowser);
						strStatus+=appInd.clickObject(obrowser, "Open_Frameset_Window");
						Thread.sleep(6000);
						strStatus+=appInd.switchToChildwindos(obrowser);
						Thread.sleep(5000);
						strStatus+=appInd.SwitchToFrame(obrowser, "Top_Frameset_Window");
						String strTopFrameText=appInd.getTextFromWebelement(obrowser, "Read_Text_Top_Frameset_Window");	
						System.out.println(strTopFrameText);
						strStatus+=appInd.switchToParentWindow();
						Thread.sleep(10000);
						
						strStatus+=appInd.switchToChildwindos(obrowser);
						strStatus+=appInd.SwitchToFrame(obrowser, "Bottom_Frame");
						String strBottomFrameText=appInd.getTextFromWebelement(obrowser, "Bottom_Frame_Text");
						System.out.println(strBottomFrameText);
						
						strStatus+=appInd.switchToParentWindow();
						
											
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_Frame_Automate' script was failed");
							System.out.println("'TC_Frame_Automate' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Frame_Automate\\TC_Login_MRS.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Frame_Automate' script was Successful");
							System.out.println("'TC_Frame_Automate' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_Frame_Automate\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_Frame_Automate' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	/********************************
	 * Method Name : TC_Alert_Automate
	 * Purpose : this will handle the alert
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Alert_Automate() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Alert_Automate:- started*****");
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
						
						String strInputAlertText=oinpuTDtMap.get("Param_1");
						
						strStatus+=appInd.clickObject(obrowser, "Alert_Tab");
						strStatus+=appInd.SwitchToFrame(obrowser, "Alert_Tab_Frame");
						strStatus+=appInd.clickObject(obrowser, "Click_To_Get_AlertBtn");
						strStatus+=appInd.isAlertPresent(obrowser);
						String strAlertText=appInd.getAlertMessage(obrowser);
						System.out.println(strAlertText);
						Thread.sleep(3000);
						
						obrowser.switchTo().defaultContent();
						strStatus+=appInd.clickObject(obrowser, "Input_Alert_Tab");
						
						strStatus+=appInd.SwitchToFrame(obrowser, "Input_Alert_Frame");
						strStatus+=appInd.clickObject(obrowser, "Click_To_Get_Input_AlertBtn");
						strStatus+=appInd.handlInputeAlert(obrowser, strInputAlertText);
						String strInputSentText=appInd.getTextFromWebelement(obrowser, "Input_Alert_Sent_Text");
						System.out.println(strInputSentText);
						
											
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_Alert_Automate' script was failed");
							System.out.println("'TC_Alert_Automate' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Frame_Automate\\TC_Login_MRS.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Alert_Automate' script was Successful");
							System.out.println("'TC_Alert_Automate' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_Alert_Automate\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_Alert_Automate' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	
	/********************************
	 * Method Name : TC_Alert_Automate
	 * Purpose : this will handle the alert
	 * Author : Maruthiraja BN
	 * Reviewer : 
	 * Date of Creation : 18-Jan-2020 
	 * Date of modification :
	 * ******************************
	 */

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_Registration_Form() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_Registration_Form:- started*****");
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
						
						String strFname=oinpuTDtMap.get("Param_1");
						String strLName=oinpuTDtMap.get("Param_2");
						//String strMaritalStatus=oinpuTDtMap.get("Param_3");
						//String strSelectItem=oinpuTDtMap.get("Param_4");
						String strSelectItem=oinpuTDtMap.get("Param_3");
						
						obrowser.switchTo().defaultContent();
						strStatus+=appInd.clickObject(obrowser, "Registration_Tab");
						strStatus+=appInd.setObject(obrowser, "First_Name_Field", strFname);
						strStatus+=appInd.setObject(obrowser, "Last_Name_Feild", strLName);
						//strStatus+=oGenericAppmethods.handleRadioButton(obrowser, strMaritalStatus);
						strStatus+=oGenericAppmethods.handleDropdown(obrowser, "DropDown_Handle", strSelectItem,"SelectByValue");
						
											
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_Registration_Form' script was failed");
							System.out.println("'TC_Registration_Form' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_Frame_Automate\\TC_Login_MRS.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_Registration_Form' script was Successful");
							System.out.println("'TC_Registration_Form' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_Registration_Form\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_Registration_Form' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, HashMap> TC_MRS_Login() {
		Map<String, HashMap> oinputMap = new HashMap<String, HashMap>();
		Map<String, String> oinpuTDtMap = new HashMap<String, String>();
		String strcurrentTD = null;
		String strcommonCountvalue = null;
		String strcommonTime = null;
		try {
			appInd.writeLog("#", "****TC_MRS_Login:- started*****");
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
						
						strStatus+=appInd.setObject(obrowser, "OpenMRS_Username", strUsername);
						strStatus+=appInd.setObject(obrowser, "OpenMRS_Password", strPassword);
						strStatus+=appInd.clickObject(obrowser, "OpenMRS_LoginBtn");
						
											
						// ########################################################################
						if (strStatus.contains("false")) {
							appInd.writeLog("Fail", "'TC_MRS_Login' script was failed");
							System.out.println("'TC_MRS_Login' script was failed");
							appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
									+ "\\Results\\snapshot\\TC_MRS_Login\\TC_Login_MRS.png");
							bolstatus = false;
							strStatus = null;
						} else if (strStatus.contains("true")) {
							appInd.writeLog("Pass", "'TC_MRS_Login' script was Successful");
							System.out.println("'TC_MRS_Login' script was Successful");
							bolstatus = true;
							strStatus = null;
						}
					}
				} else {
					appInd.takeSnapShot(obrowser, System.getProperty("user.dir")
							+ "\\Results\\snapshot\\TC_MRS_Login\\Failed_to_launch_Chrome_browser_snapshot.png");
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
			appInd.writeLog("Exception", "Exception while executing 'TC_MRS_Login' method. " + e.getMessage());
			oinpuTDtMap.put("result", "Fail");
			oinputMap.put(strcurrentTD, (HashMap) oinpuTDtMap);
			return oinputMap;
		} finally {
			System.out.println("end");
		}
	}
	
	
	
	
	
	
	
	
	
}// main class close
