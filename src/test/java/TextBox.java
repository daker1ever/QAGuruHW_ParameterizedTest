import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBox {
    private final SelenideElement
            userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            userCurrentAdressInput = $("#currentAddress"),
            userPermanentAddress = $("#permanentAddress"),
            submitButton = $("#submit");
    public TextBox openTextBoxPage() {
        open("https://demoqa.com/text-box");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    public TextBox setUserName(String name) {
        userNameInput.setValue(name);
        return this;
    }
    public TextBox setUserEmail(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    public TextBox setUserCurrentAdress(String currentAdress) {
        userCurrentAdressInput.setValue(currentAdress);
        return this;
    }

    public TextBox setUserPermanentAddress(String permanentAddress) {
        userPermanentAddress.setValue(permanentAddress);
        return this;
    }
    public TextBox pressSubmitButton() {
        submitButton.scrollTo().click();
        return this;
    }
    public void checkFillingResult(List<String> expectedData) {
        $("#output #name").shouldHave(text("Name:" + expectedData.get(0)));
        $("#output #email").shouldHave(text("Email:" + expectedData.get(1)));
        $("#output #currentAddress").shouldHave(text("Current Address :" + expectedData.get(2)));
        $("#output #permanentAddress").shouldHave(text("Permananet Address :" + expectedData.get(3)));

    }
}
