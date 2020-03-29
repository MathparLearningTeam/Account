package mathpar.web.learning.account.utils;

import java.util.Date;

public class DateUtils {
    public final static long HOUR = 60 * 60 * 1000L;
    public final static long DAY = 24*HOUR;
    public static Date getOffsetDate(Date from, long amount){
        return new Date(from.getTime()+amount);
    }
}
