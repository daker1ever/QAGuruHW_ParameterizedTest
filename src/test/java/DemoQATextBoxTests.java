import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class DemoQATextBoxTests {
    TextBox textBox = new TextBox();

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1900x1080";
    }

    @ValueSource(strings = {
            "Ivan Ivanov",
            "Petr Petrov"
    })
    @ParameterizedTest(name = "Заполнение формы валидными данными параметризованным тестом с ValueSource")
    void fillTextFormWithValueSource(String name) {
        List<String> expectedData = List.of
                (
                        name,
                        "randomMail@email.com",
                        "some adress",
                        "NCR Delhi"
                );
        textBox.openTextBoxPage()
                .setUserName(name)
                .setUserEmail("randomMail@email.com")
                .setUserCurrentAdress("some adress")
                .setUserPermanentAddress("NCR Delhi")
                .pressSubmitButton()
                .checkFillingResult(expectedData);
    }

    @CsvSource(value = {
            "Ivan Ivanov, randomMail@email.com, some address, NCR Delhi",
            "Petr Petrov, secondrandomMail@email.com, another address, SPb"
    })
    @ParameterizedTest(name = "Заполнение формы валидными данными параметризованным тестом с CsvSource")
    void fillTextFormWithCsvSource(String name, String email, String curAddress, String permAddress) {
        List<String> expectedData = List.of
                (
                        name,
                        email,
                        curAddress,
                        permAddress
                );
        textBox.openTextBoxPage()
                .setUserName(name)
                .setUserEmail(email)
                .setUserCurrentAdress(curAddress)
                .setUserPermanentAddress(permAddress)
                .pressSubmitButton()
                .checkFillingResult(expectedData);
    }
    static Stream<Arguments> fillTextFormWithMethodSource(){
        return Stream.of(
                Arguments.of(List.of("Ivan Ivanov", "randomMail@email.com", "some address", "NCR Delhi")),
                Arguments.of(List.of("Petr Petrov", "secondrandomMail@email.com", "another address", "SPb"))
        );
    }
@MethodSource
    @ParameterizedTest(name = "Заполнение формы валидными данными параметризованным тестом с MethodSource")
    void fillTextFormWithMethodSource(List<String> data) {
        List<String> expectedData = List.of
                (
                        data.get(0),
                        data.get(1),
                        data.get(2),
                        data.get(3)
                );
        textBox.openTextBoxPage()
                .setUserName(data.get(0))
                .setUserEmail(data.get(1))
                .setUserCurrentAdress(data.get(2))
                .setUserPermanentAddress(data.get(3))
                .pressSubmitButton()
                .checkFillingResult(expectedData);
    }
}


