package com.example.springdataredis.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    //    @Autowired
    //    public CacheServiceTest(CacheService cacheService) {
    //        this.cacheService = cacheService;
    //    }

    @Test
    public void 문자열_테스트() {
        String str = "문자열";
        cacheService.put("string", str);
        String stringResult = (String) cacheService.get("string");
        assertThat(stringResult).describedAs("문자열").isInstanceOf(String.class).isEqualTo(str);
    }

    @Test
    public void int_테스트() {
        int intValue = 1;

        cacheService.put("int", intValue);

        int intResult = (int) cacheService.get("int");

        assertThat(intResult).describedAs("int").isEqualTo(intValue);
    }

    /**
     * 실패하는 테스트
     * 역직렬화시에 ObjectMapper 가 Integer 로 처리 가능한 수이면 integer 로 처리함..
     */
    @Test
    public void long_테스트_1() {
        long longValue = 1l;

        cacheService.put("long", longValue);

        long longResult = (long) cacheService.get("long");

        assertThat(longResult).describedAs("int로 처리 가능한 long").isEqualTo(longValue);
    }

    @Test
    public void long_테스트_2() {
        long longValue = 1000000000000l;

        cacheService.put("long2", longValue);

        long longResult = (long) cacheService.get("long2");

        assertThat(longResult).describedAs("int로 처리 불가능한 long").isEqualTo(longValue);
    }

    /**
     * 실패하는 테스트
     * 역직렬화시에 ObjectMapper 가 Integer 로 처리 가능한 수이면 integer 로 처리함..
     */
    @Test
    public void Long_테스트() {
        Long value = Long.valueOf(1);

        cacheService.put("Long", value);

        Long longResult = (Long) cacheService.get("Long");

        assertThat(longResult).describedAs("Long").isEqualTo(value);
    }

    /**
     * 실패하는 테스트
     * 역직렬화시에 ObjectMapper 가 Float 도 Double 로 처리..
     */
    @Test
    public void float_테스트() {
        float floatValue = 1f;

        cacheService.put("float", floatValue);

        float floatResult = (float) cacheService.get("float");

        assertThat(floatResult).describedAs("long").isEqualTo(floatValue);
    }

    /**
     * 실패하는 테스트
     * 역직렬화시에 ObjectMapper 가 Float 도 Double 로 처리..
     */
    @Test
    public void Float_테스트() {
        Float value = Float.valueOf(1.1f);

        cacheService.put("Float", value);

        Float floatResult = (Float) cacheService.get("Float");

        assertThat(floatResult).describedAs("float").isEqualTo(value);
    }

    @Test
    public void double_테스트() {
        double doubleValue = 1.1d;

        cacheService.put("double", doubleValue);

        double doubleResult = (double) cacheService.get("double");

        assertThat(doubleResult).describedAs("double").isEqualTo(doubleValue);
    }

    /**
     * jackson 라이브러리에서 직렬화시 배열을 리스트로 변환.
     * 역직렬화시 리스트를 배열로 변환 살 수 없어 오류 발생
     */
    @Test
   public void 문자열_배열_테스트() {
        String[] stringArray = new String[] { "일", "이", "삼" };

        cacheService.put("문자열_배열", stringArray);

        String[] stringArrayResult = (String[]) cacheService.get("문자열_배열");

        assertThat(stringArrayResult).describedAs("문자열 배열").isEqualTo(stringArray);
    }

    @Test
    public void int_배열_테스트() {
        int[] array = new int[] { 1, 2, 3 };

        cacheService.put("int_배열", array);

        String[] stringArrayResult = (String[]) cacheService.get("int_배열");

        assertThat(stringArrayResult).describedAs("int 배열").isEqualTo(array);
    }
    @Test
    public void 문자열_리스트_테스트() {
        List<String> stringList = Arrays.asList("일", "이", "삼");

        cacheService.put("문자열_목록", stringList);

        List<String> stringListResult = (List<String>) cacheService.get("문자열_목록");

        assertThat(stringListResult).describedAs("문자열 목록").isEqualTo(stringList);
    }
    @Test
    public void Intger_리스트_테스트() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        cacheService.put("Integer_목록", list);

        List<String> stringListResult = (List<String>) cacheService.get("Integer_목록");

        assertThat(stringListResult).describedAs("Integer 목록").isEqualTo(list);
    }
    @Test
    public void Map_테스트() {
        Map<String, Object> m = new HashMap<>();
        m.put("String", "문자열");
        m.put("Integer", 1);
        m.put("Long", 100000000000l);
        m.put("Float", Float.valueOf(1.1f));
        m.put("Double", Double.valueOf(1.2d));
        m.put("배열", new int[] {1, 2, 3});
        m.put("리스트", Arrays.asList(1, 2, 3));

        cacheService.put("map", m);

        Map<String, Object> stringListResult = (Map<String, Object>) cacheService.get("map");

        assertThat(stringListResult).describedAs("Map");
        assertThat(m.get("String")).isEqualTo("문자열");
        assertThat(m.get("Integer")).isEqualTo(1);
        assertThat(m.get("Long")).isEqualTo(100000000000l);
        assertThat(m.get("Float")).isEqualTo(Float.valueOf(1.1f));
        assertThat(m.get("Double")).isEqualTo(Double.valueOf(1.2d));
        assertThat(m.get("배열")).isEqualTo(new int[] {1, 2, 3});
        assertThat(m.get("리스트")).isEqualTo(Arrays.asList(1, 2, 3));
    }
}
