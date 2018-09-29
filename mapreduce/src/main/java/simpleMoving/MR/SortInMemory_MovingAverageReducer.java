package simpleMoving.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author rk
 * @Date 2018/9/17 12:12
 * @Description:
 **/
public class  SortInMemory_MovingAverageReducer extends Reducer<Text,TimeSeriesData,Text,Text> {
    private int windowSize = 2; // 默认

    /**
     * 任务开始调用一次
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
         Configuration conf = new Configuration();
//        windowSize = Integer.parseInt(conf.get("moving.average.window.size"));
    }

    /**
     *
     * @param key <name-as-string>
     * @param values List<TimeSeriesData> TimeSeriesData (timestamp, value)
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<TimeSeriesData> values, Context context) throws IOException, InterruptedException {
        List<TimeSeriesData> sortedTimeSeries = DateUtil.sort(values);
        //调用movingAverage(sortedTimeSeries, windowSize)并发输出
        //对sortedTimeSeries应用移动平均算法
        double sum = 0.0;
        //计算前缀和
        for(int i=0; i < windowSize-1; i++){
            sum += sortedTimeSeries.get(i).getValue();
            long timestamp = sortedTimeSeries.get(i).getTimestamp();
            String date = DateUtil.stampToDate(timestamp);
            //在数据不足时，移动平均
            Text outputValue = new Text(date + "---" + sum);
            context.write(key,outputValue);
        }
        //现在有足够的时间序列数据来计算移动平均
        for(int i = windowSize-1; i < sortedTimeSeries.size(); i++){
            sum += sortedTimeSeries.get(i).getValue();
            double movingAverage = sum / windowSize;
            long timestamp = sortedTimeSeries.get(i).getTimestamp();
            String date = DateUtil.stampToDate(timestamp);
            System.out.println(date);
            Text outputValue = new Text(date + "," + movingAverage);

            //准备下一次迭代
            sum -= sortedTimeSeries.get(i-windowSize+1).getValue();

            context.write(key,outputValue);



        }

    }



}
