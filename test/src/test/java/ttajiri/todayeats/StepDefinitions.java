package ttajiri.todayeats;

import io.cucumber.java.en.*;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepDefinitions {
    private static final String BASE_URL     = System.getProperty("frontend.url", "http://localhost:8080/");
    private static final String SETTINGS_URL = BASE_URL +  "#/settings";

    @Given("トップ画面を表示する")
    public void トップ画面を表示する() {
        open(BASE_URL);
    }

    @When("今日のご飯を決めるボタンを押す")
    public void 今日のご飯を決めるボタンを押す() {
        $("#decide-eats").click();
    }

    @Then("今日のご飯が表示される")
    public void 今日のご飯が表示される() {
        $("#suggested-eats").should(appear);
    }

    @Given("設定画面を表示する")
    public void 設定画面を表示する() {
        open(SETTINGS_URL);
    }

    @When("好みのジャンルを選択する")
    public void 好みのジャンルを選択する() {
        $("#settings__categories").selectOptionByValue("1");
        $("button").click();
    }

    @Then("設定が保存される")
    public void 設定が保存される() {
        $("#settings__notification").should(appear);
    }

    @When("ご飯の登録内容を変更する")
    public void ご飯の登録内容を変更する() {
        $("#settings__eats-name-1").should(appear).setValue("変更したよ！");
    }

    @When("変更ボタンを押す")
    public void 変更ボタンを押す() {
        $("#settings__eats-update-1").click();
    }
}
