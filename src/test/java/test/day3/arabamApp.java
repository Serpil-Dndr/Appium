package test.day3;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.PointOption.point;

public class arabamApp {
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
        capabilities.setCapability("appPackage", "com.dogan.arabam"); // Hangi uygulamyi baslatmak istiyorsak o uygulamaya ait bundleId
        // degerini value kismina girer uygulamayi belirtmis ve acmis oluyoruz
        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity"); // Acilacak olan uygulamanin icersinde hangi sayfadan uygulamanin baslatilacagini
        // belirlemek icin o sayfaya ait activity degerini girerek uygulamayi o sayfadan baslatmis oluruz

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    @Test
    public void arabamTest() throws InterruptedException {
        // driver.activateApp("com.dogan.arabam");

        // Uygulamanin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());
        // Arabam kac para bolumune tiklayalim
        driver.findElementById("com.dogan.arabam:id/tvPricePrediction").click();
        // Aracimin fiyatini merak ediyorum bolumune tiklayalim
        driver.findElementByXPath("//*[@text='Aracımın fiyatını merak ediyorum']").click();
        //  driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvTitle'])[4]").click();
        // Wolkswagen markasini secelim
        Thread.sleep(1000);
        TouchAction action = new TouchAction<>(driver);
        action.press(point(541,1753))//sayfanin neresinde olursan x ve y degismez. ilk dokunma islemi
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))//kaydirma baslangic noktasi ile bitis noktasi arasindaki gecen sure asagidan yukari
                .moveTo(point(541,387))//ekrani kaydirmak icin surukleyecegimiz son nokta
                .release()// ekrandan parmagimizi cekme
                .perform(); //Action methodalrin gorevlerinin yerine getiribilmesi icin verilen emir komutu.
//        Thread.sleep(2000);
//      //kaydirma islemini yukardan asgi yaptigimizda eger ki yukardan asagi kaydirmak istiyorsak y koordinatalrinin yerini degistirmek yani press ile move to degerlerini degistirmek
        //bizim icin bu islemi gerceklestirir
//        action.press(PointOption.point(541,387))//sayfanin neresinde olursan x ve y degismez. ilk dokunma islemi
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))//kaydirma baslangic noktasi ile bitis noktasi arasindaki gecen sure asagidan yukari
//                .moveTo(PointOption.point(541,1753))//ekrani kaydirmak icin surukleyecegimiz son nokta
//                .release()// ekrandan parmagimizi cekme
//                .perform(); //Action methodalrin gorevlerinin yerine getiribilmesi icin verilen emir komutu.
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Volkswagen']").click();
        // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2018']").click();
        // model secimi yapalim
        driver.findElementByXPath("//*[@text='Jetta']").click();
        // govde tipini secelim
        driver.findElementByXPath("//*[@text='Sedan']").click();

        // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();
        // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();
        // Versiyon secimi yapalim
        Thread.sleep(1000);
        action.press(point(449,778)).release().perform();
        // aracin km bilgilerini girelim
        AndroidElement kmBox = driver.findElementByXPath("//*[@class='android.widget.EditText']");
        if(driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("150000");
        }else{
            kmBox.sendKeys("200000");
        }
        driver.findElementByXPath("//*[@text='Devam']").click();
        // aracin rengini secelim
        driver.findElementByXPath("//*[@text='Beyaz']").click();
        // opsiyel donanim (varsa) seecelim
        driver.findElementByXPath("//*[@text='Devam']").click();
        // degisen bilgisi ekleyerek tramer kaydi belirtelim
        Thread.sleep(1000);
        action.press(point(555,1374)).release().perform();
        Thread.sleep(1000);
        driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvItem'])[3]").click();
        Thread.sleep(500);
        driver.findElementByXPath("//*[@text='Devam']").click();
        driver.findElementByXPath("//*[@text='Tramer kaydı yok']").click();
        driver.findElementByXPath("//*[@text='Devam']").click();
        // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        String resultPrice= driver.findElementByXPath("com.dogan.arabam:id/tvAveragePrice").getText();//930.500 TL
        resultPrice=resultPrice.replaceAll("\\D","");
        Assert.assertTrue(Integer.parseInt(resultPrice)>500000);
        // uygulamayi kapatalim
        driver.closeApp();
    }

}
