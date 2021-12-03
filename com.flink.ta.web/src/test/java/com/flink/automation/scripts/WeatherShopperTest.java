package com.flink.automation.scripts;

import com.flink.automation.base.BaseTest;
import com.flink.automation.listener.ApplicationListener;
import com.flink.automation.pages.CartPage;
import com.flink.automation.pages.HomePage;
import com.flink.automation.pages.MoisturizerPage;
import com.flink.automation.pages.SunscreenPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ApplicationListener.class)
public class WeatherShopperTest extends BaseTest {

	@Test
	public void productShop() {
		HomePage homePage = new HomePage(getDriver());
		MoisturizerPage moisturizerPage;
		SunscreenPage sunscreenPage;
		CartPage cartPage = null;
		int temperature = homePage.getTemperature();

		if(temperature < 19) {
			moisturizerPage = homePage.clickOnMoisturizerButton();
			moisturizerPage.selectAloeProduct();
			moisturizerPage.selectAlmondProduct();
			cartPage = moisturizerPage.clickOnCartButton();
		} else if (temperature > 34){
			sunscreenPage = homePage.clickOnSunscreenButton();
			sunscreenPage.selectSPF50Product();
			sunscreenPage.selectSPF30Product();
			cartPage = sunscreenPage.clickOnCartButton();
		}

		Assert.assertTrue(cartPage.getTotalRowInTable() ==  2, "No product added to card");
	}
}
