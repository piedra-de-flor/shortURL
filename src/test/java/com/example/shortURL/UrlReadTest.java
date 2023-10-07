package com.example.shortURL;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlReadByOriginUrlRequestDto;
import com.example.shortURL.vo.NewUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlReadTest extends UrlPropertyForTest {
    @BeforeEach
    public void setUp() {
        super.getTestCrudManager().deleteAllUrl();
    }

    @DisplayName("Url 전체 조회 테스트")
    @Test
    public void Url_전체_조회_테스트() {
        super.makeTestUrl("naver.com");
        super.makeTestUrl("google.com");
        super.makeTestUrl("daum.com");
        List<Url> realUrls = super.getTestController().readAllUrl().getUrls();

        String[] urls = new String[] {"naver.com", "google.com", "daum.com"};

        for (int i = 0; i < realUrls.size(); i++) {
            assertThat(realUrls.get(i).getOriginUrl()).isEqualTo("http://www." + urls[i]);
        }
    }

    @DisplayName("Url 기존 url로 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "naver.com", "www.naver.com", "http://naver.com", "http://www.naver.com"})
    public void Url_기존_Url_조회_테스트(String input) {
        super.makeTestUrl(input);

        UrlReadByOriginUrlRequestDto readByOriginUrlRequestDto = new UrlReadByOriginUrlRequestDto();
        readByOriginUrlRequestDto.setOriginUrl(input);

        assertThat(super.getTestController().readByOriginUrl(readByOriginUrlRequestDto).getOriginUrl()).isEqualTo("http://www.naver.com");
    }

    @DisplayName("Url 새 url로 조회 테스트")
    @Test
    public void Url_새_Url_조회_테스트() {
        Url testUrl = new Url("naver.com", new NewUrl("testKey").getNewUrl());
        super.getTestCrudManager().saveUrl(testUrl);

        assertThat("localhost:8080/testKey").isEqualTo(testUrl.getNewUrl());
    }
}
