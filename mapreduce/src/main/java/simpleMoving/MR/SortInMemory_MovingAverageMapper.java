package simpleMoving.MR;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author rk
 * @Date 2018/9/17 11:50
 * @Description:
 **/

public class SortInMemory_MovingAverageMapper extends Mapper<LongWritable, Text,Text,TimeSeriesData> {
    /**
     *
     * @param key
     * @param value  <name-as-string><,><timestamp><,><value-as-double>
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TimeSeriesData timeSeries = DateUtil.getTimeSeriesData(value.toString(),",");
        String name = value.toString().split(",")[0];
        context.write(new Text(name),timeSeries);
    }
}
