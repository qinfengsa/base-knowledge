package com.qinfengsa.structure.map;

import com.qinfengsa.enumtype.DayEnum;
import java.util.EnumMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Map测试
 *
 * @author: qinfengsa
 * @date: 2019/6/11 15:46
 */
@Slf4j
public class MapTest {

    @Test
    public void testEnum() {
        EnumMap<DayEnum, String> enumMap = new EnumMap<>(DayEnum.class);
        enumMap.put(DayEnum.MONDAY, "A");
        enumMap.put(DayEnum.TUESDAY, "B");
        enumMap.put(DayEnum.WEDNESDAY, "C");
        enumMap.put(DayEnum.THURSDAY, "D");
        enumMap.put(DayEnum.FRIDAY, "E");
        enumMap.put(DayEnum.SATURDAY, "F");
        log.debug("abc:{}", enumMap);
    }
}
