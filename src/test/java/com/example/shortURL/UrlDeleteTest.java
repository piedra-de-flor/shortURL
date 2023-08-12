package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlDeleteTest extends UrlPropertyForTest {

    @DisplayName("Url 삭제 테스트")
    @Test
    public void Url_삭제_테스트() {
        super.getTestController().makeUrl("test1");
        super.getTestController().makeUrl("test2");
        super.getTestController().makeUrl("test3");

        int beforeSize = super.getTestController().readAllUrl().size();

        Url targetUrl = super.getTestController().readByOriginUrl("test1");
        super.getTestController().deleteUrl(targetUrl.getNewUrl());

        int afterSize = super.getTestController().readAllUrl().size();

        assertThat(beforeSize > afterSize).isEqualTo(true);
        assertThat(afterSize).isEqualTo(2);
    }


}
