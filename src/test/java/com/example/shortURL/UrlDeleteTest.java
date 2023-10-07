package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlDeleteTest extends UrlPropertyForTest {
    @BeforeEach
    public void setUp() {
        super.getTestCrudManager().deleteAllUrl();
    }

    @DisplayName("Url 전체 삭제 테스트")
    @Test
    public void Url_전체_삭제_테스트() {
        super.makeTestUrl("naver.com");
        super.makeTestUrl("google.com");
        super.makeTestUrl("daum.com");

        int beforeSize = super.getTestController().readAllUrl().getUrls().size();

        super.getTestCrudManager().deleteAllUrl();

        int afterSize = super.getTestController().readAllUrl().getUrls().size();

        assertThat(beforeSize > afterSize).isEqualTo(true);
        assertThat(afterSize).isEqualTo(0);
    }

    @DisplayName("Url 삭제 테스트")
    @Test
    public void Url_삭제_테스트() {
        super.makeTestUrl("naver.com");
        super.makeTestUrl("google.com");
        super.makeTestUrl("daum.com");

        int beforeSize = super.getTestController().readAllUrl().getUrls().size();

        Url targetUrl = super.getTestCrudManager().getDB().findByOriginUrl("http://www.naver.com");
        super.getTestController().deleteUrl(targetUrl.getOriginUrl());

        int afterSize = super.getTestController().readAllUrl().getUrls().size();

        assertThat(beforeSize > afterSize).isEqualTo(true);
        assertThat(afterSize).isEqualTo(2);
    }

    @DisplayName("Url 삭제 실패 테스트")
    @Test
    public void Url_삭제_실패_테스트() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            super.getTestController().deleteUrl("null");});
    }
}
