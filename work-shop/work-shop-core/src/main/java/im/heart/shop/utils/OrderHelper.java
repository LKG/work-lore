package im.heart.shop.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.LongAdder;

public class OrderHelper {

    private static LongAdder longAdder = new LongAdder();
    private static int MAX_LONG_ADDER = 99;
    public final static String DATE_PATTERN = "yyMMddHHmmss";
    public final static String DECIMAL_PATTERN = "00";
    /**
     * 生成订单编号
     * @return
     */
    public static String generateOrderNum() {
        if (longAdder.intValue() > MAX_LONG_ADDER) {
            longAdder.reset();
        } else {
            longAdder.increment();
        }
        return DateTime.now().toString(DATE_PATTERN) + new DecimalFormat(DECIMAL_PATTERN).format(longAdder.intValue())
               + RandomStringUtils.randomNumeric(2);
    }

}
