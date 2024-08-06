package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public final class YandexPhone {
    @BeforeEach
    void setUp() {
        open("https://market.yandex.ru/");
        Configuration.pageLoadStrategy = "eager";
    }
    @ValueSource(strings = {
            "iphone 15", "samsung s24",
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список табличек")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmptyTest(String searchQuery) {
        $("#header-search").setValue(searchQuery).pressEnter();
        $("#SerpStatic").shouldBe(visible);
    }
}