package io.shock.bootlab.service.excel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다.")
    @Test
    void junitTest() {
        // given
        int a = 1;
        int b = 2;
        // when
        int result = a + b;
        // then
        Assertions.assertEquals(result, a + b);
    }

//    @DisplayName("1 + 3은 4이다.")
//    @Test
//    void junitTest2() {
//        // given
//        int a = 1;
//        int b = 3;
//        // when
//        int result = 3;
//        // then
//        Assertions.assertEquals(result, a + b);
//    }
}
