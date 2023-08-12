package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlCreatTest extends UrlCRUDTest{
    @DisplayName("Url 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_생성_테스트(String input) {
        Url testUrl = super.getTestCrudManager().makeUrl(input);
        Url predictUrl = new Url("http://www.naver.com", "123");

        assertThat(testUrl.getOriginUrl()).isEqualTo(predictUrl.getOriginUrl());
    }

    @DisplayName("Url 중복 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_중복_생성_테스트(String input) {
        super.getTestController().makeUrl(input);
        super.getTestController().makeUrl(input);

        assertThat(super.getTestCrudManager().readAll().size()).isEqualTo(1);
    }
}
