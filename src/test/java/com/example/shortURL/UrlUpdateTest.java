package com.example.shortURL;

import com.example.shortURL.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UrlUpdateTest extends UrlPropertyForTest {

    @DisplayName("Url 소멸 기간 갱신 테스트")
    @Test
    public void Url_Update_테스트() throws InterruptedException {
        Url testUrl = new Url("http://www.test", "testKey");
        super.getTestCrudManager().saveUrl(testUrl);

        LocalDateTime originTime = testUrl.getDeleteDate();
        Thread.sleep(1000);

        super.getTestCrudManager().updateUrl("test");

        LocalDateTime afterUpdateTime = testUrl.getDeleteDate();

        assertThat(originTime.isBefore(afterUpdateTime)).isEqualTo(true);
    }
}
