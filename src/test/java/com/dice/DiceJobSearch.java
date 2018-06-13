package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

/*
 Test case:
      Title: dice job search 

      Step 1. Launch browser and navigate to https://dice.com 
        Expected: dice home page should be displayed

      Step 2. Insert search keyword and location then click on
      find tech jobs
      Expected: -Search results page should be loaded.
                -Page title should contain count of results , 
                along with search keyword.
                -Count of results should be displayed on the page.
      ====================
      Steps to automate:
        -Make sure you understand what functionality is being tested 
        and each step. What is expected, what is being tested.

        If you don't understand:  Ask manual tester/person who wrote it.
        BAs, Developers, Lead 

        -Manually test it and make sure , it is passing manually.
        And make sure no defects/bugs around it.
        -if a test case is failing manually, it does not qualify 
        for automation.

        --> starts automating:
        Java + Selenium + Maven + Git > Github
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {

		// Step 1. Launch browser and navigate to https://dice.com

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// makes full screen
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String url = "https://dice.com";
		driver.get(url);

		// Expected: dice home page should be displayed

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step pass. Dice homepage succesfully loaded");
		} else {
			System.out.println("Step fail. Dice homepage cannot be loaded successfully.");
			throw new RuntimeException("Step fail. Dice homepage cannot be loaded successfully.");
		}

		String keyword = "Java Developer";
		driver.findElement(By.cssSelector("#search-field-keyword")).sendKeys(keyword);

		driver.findElement(By.cssSelector("#search-field-location")).clear();
		String zipCode = "03045";
		driver.findElement(By.cssSelector("#search-field-location")).sendKeys(zipCode);

		driver.findElement(By.cssSelector("#findTechJobs")).click();

		String count = driver.findElement(By.cssSelector("#posiCountId")).getText();
		System.out.println(count);
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		if(countResult>0) {
			System.out.println("Keyword : " + keyword + " search returned " + countResult + " results in " + zipCode);
		}
		driver.close();

	}

}
