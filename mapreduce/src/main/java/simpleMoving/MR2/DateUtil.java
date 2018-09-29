package simpleMoving.MR2;

import simpleMoving.MR.TimeSeriesData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2018/9/17 12:05
 * @Description:
 **/
public class DateUtil {

    public static TimeSeriesData  getTimeSeriesData(String value, String field) {
        String[] splits = value.split(field);
        Long ts = dateToStamp(splits[1]);
        return new TimeSeriesData(ts, Double.parseDouble(splits[2]));

    }

    public static List<TimeSeriesData>  sort(Iterable<TimeSeriesData> value){
        List<TimeSeriesData> list = new ArrayList<TimeSeriesData>();
        for (TimeSeriesData t : value){
            //必须要重新创建一个TimeSeriesData类
            TimeSeriesData series = new TimeSeriesData(t.getTimestamp(),t.getValue());
            list.add(series);
        }
        Collections.sort(list);
        for (TimeSeriesData t : list){
            System.out.println(t);
        }
        return list;
    }

    public static Long dateToStamp(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String stampToDate(Long stamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(stamp);
       return simpleDateFormat.format(date);
    }


}
