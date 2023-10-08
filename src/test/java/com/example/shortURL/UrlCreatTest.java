package com.example.shortURL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlCreatTest extends UrlPropertyForTest {
    @BeforeEach
    public void setUp() {
        super.getTestCrudManager().deleteAllUrl();
    }
    
    @DisplayName("Url 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_생성_테스트(String input) {
        super.makeTestUrl(input);

        assertThat(1).isEqualTo(super.getTestCrudManager().getDB().getDataSize());
    }

    @DisplayName("Url 중복 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_중복_생성_테스트(String input) {
        super.makeTestUrl("http://www.naver.com");
        super.makeTestUrl(input);

        assertThat(super.getTestCrudManager().readAll().getUrls().size()).isEqualTo(1);
    }

    @DisplayName("Url 유효성 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"UrlOfErrorSite", "UrlOfErrorSite1", "UrlOfErrorSite2"})
    public void Url_유효성_예외_테스트(String url) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            super.makeTestUrl(url);});
    }
}
