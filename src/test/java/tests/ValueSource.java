package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ValueSource {
    @BeforeEach
    void setUp() {
        open("https://market.yandex.ru/");
        Configuration.pageLoadStrategy = "eager";
    }
    @org.junit.jupiter.params.provider.ValueSource(strings = {
            "iphone 15", "samsung s24",
    })
    @org.junit.jupiter.params.ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список табличек")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#header-search").setValue(searchQuery).pressEnter();
        $("#SerpStatic").shouldBe(visible);
    }
}