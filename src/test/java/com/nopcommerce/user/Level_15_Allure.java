package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;


public class Level_15_Allure extends BaseTest {
	private WebDriver driver;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;


	private String emailAddress, firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.us";
		password = "123456";
	}

	//@Description("Register to system")
	//@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register() {
		
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);

		registerPage.inputToLastNameTextbox(lastName);

		
		registerPage.inputToEmailTextbox(emailAddress);

		
		registerPage.inputToPasswordTextbox(password);

		
		registerPage.inputToConfirmPasswordTextbox(password);

	
		registerPage.clickToRegisterButton();

		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();

	}

	//@Description("Login to system")
	@Test
	public void User_02_Login() {
		
		loginPage = homePage.openLoginPage();

		
		loginPage.inputToEmailTexttbox(emailAddress);

		
		loginPage.inputToPasswordTextbox(password);

		
		homePage = loginPage.clickToLoginButton();

		
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		
		customerInforPage = homePage.openMyAccountLink();

	
		Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}



}
