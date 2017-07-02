
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RanorexTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private RanorexPage ranorexPage;

    //Preparacion de la prueba
    @Before
    public void setUp() throws Exception {
        //Se levanta una aplicación Firefox
        driver = new FirefoxDriver();
        //Url
        baseUrl = "https://www.ranorex.com";
        //Manage: gestiona al driver
        //Espera máximo 30 segundos para realizar cada paso del test
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Instanciar RanorexPage, pasándole el driver
        ranorexPage = PageFactory.initElements(driver, RanorexPage.class);
        //Ingresa a la dirección
        driver.get(baseUrl + "/web-testing-examples/vip/");
    }

    @Test
    public void testRanorex() throws Exception {
        /*
        //Ingresa a la dirección
        driver.get(baseUrl + "/web-testing-examples/vip/");

        driver.findElement(By.id("FirstName")).clear();
        driver.findElement(By.id("FirstName")).sendKeys("Juan");
        driver.findElement(By.id("LastName")).clear();
        driver.findElement(By.id("LastName")).sendKeys("Perez");
        new Select(driver.findElement(By.id("Category"))).selectByVisibleText("Other");
        driver.findElement(By.xpath("(//input[@id='Gender'])[2]")).click();
        driver.findElement(By.id("Add")).click();
        */
        ranorexPage.insertClient("Juan", "Perez", "Other", "Male");
        /*
        try {
            assertEquals("Juan", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[2]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Perez", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[3]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Male", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[4]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Other", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[5]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        */
        assertTrue(ranorexPage.compareResult("Juan", "Perez", "Other", "Male"));
        try {
            assertEquals("VIP count: 1", driver.findElement(By.id("count")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("", driver.findElement(By.id("FirstName")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("", driver.findElement(By.id("LastName")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @Test
    public void testTC002() throws Exception {
        driver.get(baseUrl + "/web-testing-examples/vip/");
        driver.findElement(By.id("FirstName")).clear();
        driver.findElement(By.id("FirstName")).sendKeys("Juan");
        driver.findElement(By.xpath("(//input[@id='Gender'])[2]")).click();
        driver.findElement(By.id("Add")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | modalDialogOK | 30000]]
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modalDialogOK | ]]
        try {
            assertEquals("Please specify 'Last Name' value", driver.findElement(By.id("alertTextOK")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @Test
    public void testTC003() throws Exception {
        driver.get(baseUrl + "/web-testing-examples/vip/");
        driver.findElement(By.id("LastName")).clear();
        driver.findElement(By.id("LastName")).sendKeys("Perez");
        driver.findElement(By.xpath("(//input[@id='Gender'])[2]")).click();
        driver.findElement(By.id("Add")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | modalDialogOK | 30000]]
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modalDialogOK | ]]
        try {
            assertEquals("Please specify 'First Name' value", driver.findElement(By.id("alertTextOK")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @Test
    public void testTC004() throws Exception {
        driver.get(baseUrl + "/web-testing-examples/vip/");
        driver.findElement(By.id("FirstName")).clear();
        driver.findElement(By.id("FirstName")).sendKeys("Juan");
        driver.findElement(By.id("LastName")).clear();
        driver.findElement(By.id("LastName")).sendKeys("Perez");
        driver.findElement(By.id("FirstName")).clear();
        driver.findElement(By.id("FirstName")).sendKeys("Juan");
        driver.findElement(By.xpath("(//input[@id='Gender'])[2]")).click();
        driver.findElement(By.id("Add")).click();
        try {
            assertEquals("on", driver.findElement(By.id("VIP")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Juan", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[2]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Perez", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[3]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Male", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[4]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Other", driver.findElement(By.xpath("//table[@id='VIPs']/tbody/tr[2]/td[5]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("VIP count: 1", driver.findElement(By.id("count")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.id("Save")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | modalDialogOK | 30000]]
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modalDialogOK | ]]
        try {
            assertEquals("1 VIP(s) stored sucessfully", driver.findElement(By.id("alertTextOK")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
