package day3;

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

public class arabamApp {
    AndroidDriver<AndroidElement> driver;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName","Pixel 2");
        //Bu sekilde de yazilabilir,MobileCapabilityType olmasa da olur.
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability("addPackage","com.dogan.arabam");//hangi uygulmayi baslatmak istiyorsak o uygulamaya ait bundleID
      //degerini value kismina girer uygulamayi belirtmis ve acmis oluruz
        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        //acilacak olan uygulamayi
        /*
        kullanmis oldugumuz android cihazimin surumu 6.0 dan dusuk bir surumse UiAutomator kullanilir.
        Eger ki kullanmis oldugum surum 6.0 dan buyukse UiAutomator2 kullanilir.
         */
        //Apk bilgisi ceptelefonundaki bilgilere ulasmak icin kullandik.Apk infodan kimlik bilgisini aliyoruz.capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\serpi\\Java\\T_115\\Apps\\arabam.com_4.8.0_Apkpure.apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Test
    public void arabamTest(){
        // driver.activateApp("com.dogan.arabam");

        // Uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());
        // Arabam kac para bolumune tiklayalim
        driver.findElementById("com.dogan.arabam:id/tvPricePrediction").click();
        // Aracimin fiyatini merak ediyorum bolumune tiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();
        //  driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvTitle'])[4]").click();
        // Wolkswagen markasini secelim
        // yil secimi yapalim
        // model secimi yapalim
        // govde tipini secelim
        // yakit tipini secelim
        // vites tipini secelim
        // Versiyon secimi yapalim
        // aracin km bilgilerini girelim
        // aracin rengini secelim
        // opsiyel donanim (varsa) seecelim
        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        // uygulamayi kapatalim
    }

}
