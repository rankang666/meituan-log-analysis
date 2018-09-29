package simpleMoving.MR2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
  * @class_name TimeSeriesData
　* @author rk
　* @date 2018/9/17 11:36
  * @Description: <name-as-string><,><timestamp><,><value-as-double>
　*/
public class TimeSeriesData implements WritableComparable<TimeSeriesData> {
    private long timestamp;
    private double value;
/*    public static TimeSeriesData copy(TimeSeriesData tsd){
        return new TimeSeriesData(tsd.timestamp, tsd.value);
    }*/
    public TimeSeriesData(long timestamp, double value){
        this.timestamp = timestamp;
        this.value = value;
    }

    public TimeSeriesData() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TimeSeriesData{" +
                "timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int compareTo(TimeSeriesData o) {

        long diff = o.getTimestamp() - this.getTimestamp();
        if(diff == 0){
            return 0;
        }else{
            return diff < 0 ? 1 : -1;
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(timestamp);
        out.writeDouble(value);
    }

    public void readFields(DataInput in) throws IOException {
        this.timestamp = in.readLong();
        this.value = in.readDouble();
    }




}
