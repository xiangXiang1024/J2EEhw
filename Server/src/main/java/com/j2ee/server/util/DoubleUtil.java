package com.j2ee.server.util;

import java.math.BigDecimal;

/**
 * @Author: 许杨
 * @Description: 小数四舍五入
 * @Date: 2019/3/6
 */
public class DoubleUtil {
    public static Double format(double num) {
        BigDecimal bg = new BigDecimal(num);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
