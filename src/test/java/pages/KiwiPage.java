package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

import static io.appium.java_client.touch.offset.PointOption.point;

public class KiwiPage {

    public KiwiPage(){

        PageFactory.initElements((WebDriver) Driver.getAndroidDriver(),this);

  // WebDriver API'sini
        //kullanarak mobil uygulamalardaki nesnelere erişebilmenizi ve uygulamanın
        //davranışlarını kontrol edebilmenizi sağlar.
    }
       @FindBy(xpath = "//*[@text='Continue as a guest']")
        public WebElement guestButton;


       @FindBy(xpath = "//*[@class='android.widget.EditText']")
       public WebElement editTextBox;
       @FindBy(xpath = "//*[@content-desc='Android destination'])[1]")
       public WebElement addDestinationPlusButton;
       @FindBy(xpath = "//*[@text='Choose']")
       public WebElement chooseButton;
       @FindBy(id="com.skypicker.main:id/saveButton")
       public WebElement setDateButton;
       @FindBy(xpath = "//*[@text='Search']")
       public WebElement searchButton;
       @FindBy(xpath = "(//*[@class='android.widget.TextView'])[12]")
       public WebElement cheapestPrice;









    public void skipThreeSteps() throws InterruptedException {
        TouchAction action = new TouchAction<>(Driver.getAndroidDriver());
        for(int i =0 ; i<3;i++){
            action.press(point(541,1695)).release().perform();
            Thread.sleep(500);
        }


        }
    public void twoSecondScroll(){

        ReusableMethods.screenScroll(497,1341,2000,497,321);
    }
}
