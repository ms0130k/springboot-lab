package io.shock.bootlab.service.excel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitQuiz {
    @Test
    void junitTest() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길은";

        Assertions.assertThat(name1).isNotNull();
        Assertions.assertThat(name2).isNotNull();
        Assertions.assertThat(name3).isNotNull();

        Assertions.assertThat(name1).isEqualTo(name2);
        Assertions.assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    void junitTest2() {
        int a = 15;
        int b = 0;
        int c = -5;

        Assertions.assertThat(a).isPositive();
        Assertions.assertThat(b).isZero();
        Assertions.assertThat(c).isNegative();
        Assertions.assertThat(a).isGreaterThan(c);
        Assertions.assertThat(c).isLessThan(a);
    }
}
