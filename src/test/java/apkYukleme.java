import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

public class apkYukleme {
    AndroidDriver<AndroidElement>driver;
    @Test
    public void setUp() throws MalformedURLException {
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
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\serpi\\Java\\T_115\\Apps\\Kiwi.com - Book Cheap Flights_2023.14.0_Apkpure (1).apk");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
