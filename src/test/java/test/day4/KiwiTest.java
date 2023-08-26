package test.day4;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KiwiPage;
import utilities.Driver;
import utilities.ReusableMethods;


import java.time.Duration;

import static io.appium.java_client.touch.offset.PointOption.point;

public class KiwiTest {

    AndroidDriver<AndroidElement> driver= Driver.getAndroidDriver();
    KiwiPage kiwiPage= new KiwiPage();

    @Test
    public void kiwiAppTest() throws InterruptedException {
        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.skypicker.main"));
        // uygulamanin basariyla acildigi dogrulanir
        Assert.assertTrue(kiwiPage.guestButton.isDisplayed());

        // misafir olarak devam et e tiklanir
        kiwiPage.guestButton.click();
        Thread.sleep(1000);
        // ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
        kiwiPage.skipThreeSteps();
        Thread.sleep(2000);
        // Trip type,one way olarak secilir. 321,625
        ReusableMethods.clickWithCoordinates(321,625,1000);
        ReusableMethods.clickWithCoordinates(530,1462,500);//for oneway
        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        //533 785 stockholm coordinate
        ReusableMethods.clickWithCoordinates(533,785,750);
        //clearall
        ReusableMethods.clickWithCoordinates(1019,146,750);

        // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
        kiwiPage.editTextBox.sendKeys("Istanbul");
        //if else ilede yapilir
//         if(!driver.isKeyboardShown()){
//             kiwiPage.editTextBox.sendKeys("Istanbul");
//         }else{
//             driver.getKeyboard().pressKey("Istanbul");
//         }
        Thread.sleep(1000);
        kiwiPage.addDestinationPlusButton.click();
        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir.
        kiwiPage.chooseButton.click();
        ReusableMethods.clickWithCoordinates(511,917,1000);
        if(!driver.isKeyboardShown()){
             kiwiPage.editTextBox.sendKeys("Istanbul");
         }else{
             driver.getKeyboard().pressKey("Toronto");
         }
        kiwiPage.addDestinationPlusButton.click();
        kiwiPage.chooseButton.click();
        // gidis tarihi eylul ayinin 21 i olarak secilir ve set date e tiklanir
        Thread.sleep(1000);
        ReusableMethods.clickWithCoordinates(530,1056,1000);
        //497, 1341 bu koordinetler calenderi yukari kaydirmak icin
        TouchAction action = new TouchAction<>(driver);
        action.press(point(497,1342))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(point(497,321))
                .release().perform();
       //21 eylul icin koordinate 687 1326
        ReusableMethods.clickWithCoordinates(687,1326,1000);
        // search butonuna tiklanir
        kiwiPage.setDateButton.click();
        Thread.sleep(1000);
        kiwiPage.searchButton.click();
        // en  ucuz ve aktarmasiz filtrelemeleri yapilir
        ReusableMethods.clickWithCoordinates(263,252,1000);
        ReusableMethods.clickWithCoordinates(522,577,1000);
        ReusableMethods.clickWithCoordinates(526,263,1000);
        ReusableMethods.clickWithCoordinates(453,1462,1000);
        Thread.sleep(2000);
        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir
        String resultPrice=kiwiPage.cheapestPrice.getText();
        driver.sendSMS("5555555555","Kiwi Uygulamisindan gelen bilet fiyatlari= "+ resultPrice);
        ReusableMethods.screenScrollDown(2000);
    }

}
