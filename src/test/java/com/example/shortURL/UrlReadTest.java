package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlReadTest extends UrlPropertyForTest {
    @DisplayName("Url 전체 조회 테스트")
    @Test
    public void Url_전체_조회_테스트() {
        super.getTestController().makeUrl("testUrl0");
        super.getTestController().makeUrl("testUrl1");
        super.getTestController().makeUrl("testUrl2");

        List<Url> realUrls = super.getTestController().readAllUrl();

        for (int i = 0; i < realUrls.size(); i++) {
            assertThat(realUrls.get(i).getOriginUrl()).isEqualTo("http://www.testUrl" + i);
        }
    }

    @DisplayName("Url 기존 url로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "test.com", "www.test.com", "http://test.com", "http://www.test.com"})
    public void Url_기존_Url_조회_테스트(String input) {
        Url testUrl = super.getTestCrudManager().makeUrl(input);

        assertThat(super.getTestController().readByOriginUrl(input)).isEqualTo(testUrl);
    }

    @DisplayName("Url 새 url로 조회 테스트")
    @Test
    public void Url_새_Url_조회_테스트() {
        Url testUrl = new Url("test", "testKey");

        assertThat(testUrl.getNewUrl()).isEqualTo("localhost:8080/testKey");
    }
}
