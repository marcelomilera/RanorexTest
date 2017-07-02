
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by MARCELO on 24/06/2017.
 */
public class RanorexPage {
    WebDriver driver;

    @FindBy(id="FirstName")
    WebElement txtFirstName;

    @FindBy(id="LastName")
    WebElement txtLastName;

    @FindBy(how = How.ID, id="Category")
    WebElement listCategory;

    @FindBy(id="Gender")
    WebElement rbtGenderFemale;

    @FindBy(how = How.XPATH, using="(//input[@id='Gender'])[2]")
    WebElement rbtGenderMale;

    @FindBy(id="Add")
    WebElement btnAdd;

    @FindBy(how = How.XPATH, using="//table[@id='VIPs']/tbody/tr[2]/td[2]")
    WebElement lblFirstName;

    @FindBy(how = How.XPATH, using="//table[@id='VIPs']/tbody/tr[2]/td[3]")
    WebElement lblLastName;

    @FindBy(how = How.XPATH, using="//table[@id='VIPs']/tbody/tr[2]/td[4]")
    WebElement lblGender;

    @FindBy(how = How.XPATH, using="//table[@id='VIPs']/tbody/tr[2]/td[5]")
    WebElement lblCategory;

    public RanorexPage(WebDriver driver){
        this.driver = driver;
    }

    public void typeFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }

    public void typeLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }

    public void selectCategory(String category){
        new Select(listCategory).selectByVisibleText(category);
    }

    public void selectGender(String gender){
        String s = gender.toLowerCase();
        if (s.equals("Male")){
            rbtGenderMale.click();
        }
        else if (s.equals("Female")){
            rbtGenderFemale.click();
        }
        else {
            System.out.println("Invalid Gender");
        }
    }

    public  void clicKAdd(){
        btnAdd.click();
    }

    public void insertClient(String firstName, String lastName, String category, String gender){
        this.typeFirstName(firstName);
        this.typeLastName(lastName);
        this.selectCategory(category);
        this.selectGender(gender);
        this.clicKAdd();
    }

    public boolean compareResult(String firstName, String lastName, String category, String gender){
        return this.lblFirstName.getText().equals(firstName) &
                this.lblLastName.getText().equals(lastName) &
                this.lblCategory.getText().equals(category) &
                this.lblGender.getText().equals(gender);
    }
}
