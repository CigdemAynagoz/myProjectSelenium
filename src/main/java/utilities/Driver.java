package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
     POM'de Driver icin TestBase class'ina extends etmek yerine
     Driver class'indan static method'lar kullanarak
     driver olusturup, ilgili ayarlarin yapilmasi
     ve en sonda driver'in kapatilmasi tercih edilmistir.

     POM'de driver classındaki getDriver methoduna obj olusturularak kullanılmasını engellemek ıcın
     Singleton pattern kullanımı benımsenmıstır

     Singleton pattern: tekli kullanım.bir classın farklı classlardan obj olusturularak kullanımı engellemek ıcın kullanılır

     Bunu saglamak ıcın yapmamaız gereken sey oldukca basıt, obj olusturmak ıcın kullanılan constructor'i prpvate
     yaptıgımızda baska classlarda driver classından obj olusturması mumkun olamaz
      */
    private Driver(){

    }
    static WebDriver driver;
    public static WebDriver getDriver(){
        if (driver==null) {
            switch (ConfigReader.getProperty("browser")){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "safari" :
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){ // driver'a deger atanmissa
            driver.close();
            driver=null;
        }
    }
}