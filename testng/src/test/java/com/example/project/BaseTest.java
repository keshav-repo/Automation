package com.example.project;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("🚀 BeforeSuite: Setup before all test classes in the suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("📦 BeforeClass: Setup before all test methods in this class");
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("🔧 BeforeMethod: Setup before each test method");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("🧹 AfterMethod: Cleanup after each test method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("📦 AfterClass: Cleanup after all test methods in this class");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("🚀 AfterSuite: Cleanup after all test classes in the suite");
    }
}
