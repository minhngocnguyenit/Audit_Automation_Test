package stepdefs;

import com.sm.page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class LoginDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public LoginDefinition() {
        if(webDriver == null)
            super.initPage();
        loginPage = new LoginPage(webDriver);
    }

    @Given("I navigate to Login page")
    public void i_navigate_to_Login_page() {
        String env = System.getProperty("server");
        logger.info("I navigate to login page: " + env);
        loginPage.load(env);
    }

    @When("I enter user and password")
    public void i_enter_user_and_password() {
        logger.info("I enter username and password");
        String userName = System.getProperty("username");
        String password = System.getProperty("password");

        loginPage.login(userName, password);
    }

    @Given("Close browser")
    public void closeBrowser(){
        logger.info("I close this browser.");
        webDriver.close();
        webDriver.quit();
    }
}
