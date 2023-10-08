package com.example.shortURL;

import com.example.shortURL.domain.Url;
import com.example.shortURL.dto.UrlReadByOriginUrlRequestDto;
import com.example.shortURL.dto.UrlUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlUpdateTest extends UrlPropertyForTest {
    @BeforeEach
    public void setUp() {
        super.getTestCrudManager().deleteAllUrl();
    }

    @DisplayName("Url 소멸 기간 갱신 테스트")
    @Test
    public void Url_Update_테스트() throws InterruptedException {
        Url testUrl = new Url("http://www.naver.com", "testKey");
        super.getTestCrudManager().saveUrl(testUrl);

        LocalDateTime originTime = testUrl.getDeleteDate();
        Thread.sleep(1000);

        UrlUpdateRequestDto urlUpdateRequestDto = new UrlUpdateRequestDto();
        urlUpdateRequestDto.setOriginUrl("http://www.naver.com");
        super.getTestCrudManager().updateUrl(urlUpdateRequestDto);

        UrlReadByOriginUrlRequestDto readByOriginUrlRequestDto = new UrlReadByOriginUrlRequestDto();
        readByOriginUrlRequestDto.setOriginUrl("http://www.naver.com");
        LocalDateTime afterUpdateTime = super.getTestController().readByOriginUrl(readByOriginUrlRequestDto).getTime();

        assertThat(originTime.isBefore(afterUpdateTime)).isEqualTo(true);
    }
}
