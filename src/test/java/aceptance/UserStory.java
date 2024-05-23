package aceptance;

import aceptance.utils.TradingRequests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStory {

    TradingRequests tradingRequests = new TradingRequests();

    @Given("^one security \"(.*)\" and two users \"(.*)\" and \"(.*)\" exist$")
    public void givenStatement(String securityName, String userName1, String userName2) throws Throwable {
        System.out.println("Given statement executed successfully");
        this.tradingRequests.createSecurity(securityName);
        this.tradingRequests.createUser(userName1, "secret");
        this.tradingRequests.createUser(userName2, "secret");
    }

    @When("^user \"(.*)\" puts a \"(buy|sell)\" order for security \"(.*)\" with a price of \"(.*)\" and quantity of \"(.*)\"$")
    public void whenStatement(String userName, String operation, String securityName, Double price, Integer quantity) throws Throwable {
        System.out.println("Then statement executed successfully");
        //this.tradingRequests.createOrder(userName, operation, securityName, price, quantity);
    }

    @Then("^a trade occurs with the price of \"(.*)\" and quantity of \"(.*)\"$")
    public void thenStatement(Integer price, Integer quantity) throws Throwable {
        System.out.println("Then statement executed successfully");
    }
}
