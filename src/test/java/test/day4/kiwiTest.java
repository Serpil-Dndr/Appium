package test.day4;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.PointOption.point;

public class kiwiTest {
    AndroidDriver<AndroidElement> driver;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); // Kullanmis oldugumuz Android cihazin surumu eger ki
        // 6.0 dan dusuk bir surumse UiAutomator kullanilir. Eger ki kullanmis oldugum surum 6 dan buyukse UiAutomator2 kullanir
        // capabilities.setCapability(MobileCapabilityType.APP, "");
        capabilities.setCapability("appPackage", "com.skypicker.main"); // Hangi uygulamyi baslatmak istiyorsak o uygulamaya ait bundleId
        // degerini value kismina girer uygulamayi belirtmis ve acmis oluyoruz
        capabilities.setCapability("appActivity", "com.kiwi.android.feature.splash.impl.ui.SplashActivity"); // Acilacak olan uygulamanin icersinde hangi sayfadan uygulamanin baslatilacagini
        // belirlemek icin o sayfaya ait activity degerini girerek uygulamayi o sayfadan baslatmis oluruz
        capabilities.setCapability(MobileCapabilityType.NO_RESET,false);
        /*
        Eger ki bizler uygulamanin teste baslamadan once ilk yuklenmis haline sahip olmak yani kullani hareketlerinin her seferinde sifirlamak icin
        noReset capabilities degeri False olmak zorundadir.
        Eger ki uygulamanin kullanici tercihlerini kaydetmesini istiyorsak noReset degeri true olmalidir.
         */
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @Test
    public void kiwiAppTest() throws InterruptedException {
        // uygulamanin yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.skypicker.main"));
        // uygulamanin basariyla acildigi dogrulanir
        Assert.assertTrue(driver.findElementByXPath("//*[@text='Continue as a guest']").isDisplayed());

        // misafir olarak devam et e tiklanir
        driver.findElementByXPath("//*[@text='Continue as a guest']").click();

        // ardinda gelecek olan 3 adimada yesil butona basilarak devam edilir
        TouchAction action = new TouchAction<>(driver);
        for(int i =0 ; i<3;i++){
            action.press(point(541,1695)).release().perform();
            Thread.sleep(500);
        }
        // Trip type,one way olarak secilir
        // kalkis ulkesi secenegine tiklanir ve default olan ulke kaldirilir
        // kalkis yapilacak ulke/sehir girilir ve sec e tiklanir
        // varis ulkesi secenegine tiklanir ve gidilecek ulke girilir
        // gidis tarihi eylul ayinin 21 i olarak secilir ve set date e tiklanir
        // search butonuna tiklanir
        // en  ucuz ve aktarmasiz filtrelemeleri yapilir
        // gelen bilet fiyati kaydedilir ve kullanicin telefonuna sms olarak gonderilir

    }

}
