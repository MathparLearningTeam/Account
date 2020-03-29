package mathpar.web.learning.gateway.utils;

import mathpar.web.learning.account.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

class DateUtilsTest {

    @Test
    void getOffsetDatePrecise() {
        Date from = new Date(10000000L);
        assert 11000000L == DateUtils.getOffsetDate(from, 1000000L).getTime();
    }

    @Test
    void getOffsetDate() {
        Calendar calendar = Calendar.getInstance();
        Date from = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date expectedResult = calendar.getTime();
        assert expectedResult.compareTo(DateUtils.getOffsetDate(from, DateUtils.DAY))==0;
    }
}