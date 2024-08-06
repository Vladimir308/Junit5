package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public final class LibrarySearch {
    @BeforeEach
    void setUp() {
        open("https://www.labirint.ru/");
        Configuration.pageLoadStrategy = "eager";
    }
    @ValueSource(strings = {
            "Книга билла", "Книга для записей",
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список табличек")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmptyTest(String searchQuery) {
        $("#search-field").setValue(searchQuery).pressEnter();
        $(".search-tab").shouldBe(visible);
    }
    @CsvFileSource(resources = "/test_data/testWithCsvFileSource.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} должен быть автор")
    @Tag("BLOCKER")
    void testWithCsvFileSourceTest(String searchQuery, String expectedLink) {
        $("#search-field").setValue(searchQuery).pressEnter();
        $(".search-tab")
                .shouldHave(text(expectedLink));
    }
}