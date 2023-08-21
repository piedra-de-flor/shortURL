package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlCreatTest extends UrlPropertyForTest {
    @DisplayName("Url 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "test.com", "www.test.com", "http://test.com", "http://www.test.com"})
    public void Url_생성_테스트(String input) {
        super.getTestController().makeUrl(input);
        Url testUrl = super.getTestCrudManager().readByOriginUrl(input);

        assertThat(testUrl).isEqualTo(super.getTestController().readByOriginUrl(input));
    }

    @DisplayName("Url 중복 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_중복_생성_테스트(String input) {
        super.getTestController().makeUrl("naver.com");
        super.getTestController().makeUrl(input);

        assertThat(super.getTestCrudManager().readAll().size()).isEqualTo(1);
    }
}
