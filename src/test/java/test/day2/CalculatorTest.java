package test.day2;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalculatorTest {


    //Android isletim sistemine sahip olan cep telefonlari icin ilgili methodlarin olusturuldugu driver. Bu driver
    //android cihaz testlerinde kullanimi onemlidir. Bu driver i sadece android cihazlarin testinde kullanilir.
    AndroidDriver<AndroidElement>driver;

    //hem android isletim sistemine sahip olan telefonlar icin hemde iOS islemin sistemine sahip olan cihazlar icin
    //kullanilir. Android driver in olmasi Appium driver uzerinden Android cihazlarda calismayacagi anlamina gelmez.
    //AppiumDriver, iOS de zorunlu, androidlerde ise kullanilabilir.
  //  AppiumDriver<MobileElement>driver2;


    @Test
    public void test01() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName","Pixel 2");
        //Bu sekilde de yazilabilir,MobileCapabilityType olmasa da olur.
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        /*
        kullanmis oldugumuz android cihazimin surumu 6.0 dan dusuk bir surumse UiAutomator kullanilir.
        Eger ki kullanmis oldugum surum 6.0 dan buyukse UiAutomator2 kullanilir.
         */
        //Apk bilgisi ceptelefonundaki bilgilere ulasmak icin kullandik.Apk infodan kimlik bilgisini aliyoruz.
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\serpi\\Java\\T_115\\Apps\\Calculator_8.4 (503542421)_Apkpure.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // uygulamanin yuklendigini dogrular(isAppInstalled)
        // uygulamanin acildigini dogrular
        Assert.assertTrue( driver.isAppInstalled("com.google.android.calculator"));
        // carpma,bolme,toplama,cikarma islemleri yaparak sonuclari dogrular

        AndroidElement acButon =driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"clear\"]");
        Assert.assertTrue(acButon.isDisplayed());
        driver.findElementByAccessibilityId("2").click();
        driver.findElementByAccessibilityId("5").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("1").click();
        driver.findElementByAccessibilityId("0").click();
        //Sonucun 250 oldugunu dogrula
        AndroidElement resultBox = driver.findElementById("com.google.android.calculator:id/result_preview");
        String result = resultBox.getText();
        Assert.assertTrue(Integer.parseInt(result)==250);

    }

}
