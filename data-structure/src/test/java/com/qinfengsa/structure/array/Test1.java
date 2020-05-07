package com.qinfengsa.structure.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: qinfengsa
 * @date: 2019/5/13 13:26
 */
@Slf4j
public class Test1 {

    @Test
    public void  test11() {

        TestFather inf = new TestSon();

        Object result = null;
        try {
            result = inf.add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("result:{}",result);
    }
}
