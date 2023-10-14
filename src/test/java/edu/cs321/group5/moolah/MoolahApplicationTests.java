package edu.cs321.group5.moolah;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoolahApplicationTests {

	//tests if we get the name can the database find all other personal info
	@Test()
	public void TestName() {
		//System.out.println("TESTING DATABASE");
		//MoolahDB database = new MoolahDB();
		//database.run();
	}
	/*
    tests to see if math works for percentages and the amount of money entered in by the user
    divided correctly. make one for the suggested division that we are going to recommend and the one
    the user might want to use.
     */
	@Test()
	public void TestPercentCalc() {

	}

	//make sure basic calculator addition subtraction and division is working
	@Test()
	public void TestCalculations() {

	}

	//get old monthly spending data
	//add new data for new month and show spending amount over a certain amount of months
	//can also compare the old month with the new month since we are already using that data at the time
	//show comparisons to user
	@Test()
	public void TestMonthlyCalc() {

	}

	//same thing as @TestMonthlyCalc but weekly this time
	@Test()
	public void TestWeeklyCalc() {

	}

	//adding new recurring monthly payment (subscriptions) to previous recurring payments
	//make sure database is recording this info
	@Test()
	public void TestRecurring() {

	}

	//user is asking to show the reoccurring monthly payments he has
	@Test()
	public void TestGetRecurring() {

	}

	//can database capture past money progress/split
	@Test()
	public void TestOldProg(){

	}

	//can database stores old saving plans and show them to user
	@Test()
	public void TestGetSavePlan(){

	}
	//can database store login info
	//if user enters login info will it retrieve the information needed for this login
	@Test()
	public void TestLogin(){

	}

	//can the database edit old login info and change it due to its edits
	//like if they want to change password or something
	@Test()
	public void TestEdit(){

	}

}
