package ttajiri.todayeats;

import io.cucumber.java.en.*;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepDefinitions {
    private static final String URL = System.getProperty("frontend.url", "http://localhost:8080/");

    @Given("トップ画面を表示する")
    public void トップ画面を表示する() {
        open(URL);
    }

    @When("今日のご飯を決めるボタンを押す")
    public void 今日のご飯を決めるボタンを押す() {
        $("#decide-eats").click();
    }

    @Then("今日のご飯が表示される")
    public void 今日のご飯が表示される() {
        $("#today-eat").should(appear);
    }
}
