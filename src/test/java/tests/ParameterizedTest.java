package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class ParameterizedTest {
    @BeforeEach
    void setUp() {
        open("https://www.labirint.ru/");
        Configuration.pageLoadStrategy = "eager";
    }
    @ValueSource(strings = {
            "Книга билла", "Книга для записей",
    })
    @org.junit.jupiter.params.ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список табличек")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#search-field").setValue(searchQuery).pressEnter();
        $(".search-tab").shouldBe(visible);
    }
    @CsvFileSource(resources = "/test_data/testWithCsvFileSource.csv")
    @org.junit.jupiter.params.ParameterizedTest(name = "Для поискового запроса {0} должен быть автор")
    @Tag("BLOCKER")
    void testWithCsvFileSource(String searchQuery, String expectedLink) {
        $("#search-field").setValue(searchQuery).pressEnter();
        $(".search-tab")
                .shouldHave(text(expectedLink));
    }
}