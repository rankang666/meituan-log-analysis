package simpleMoving.MR2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author rk
 * @Date 2018/9/17 16:28
 * @Description:
 **/
public class CompositeKey implements WritableComparable<CompositeKey> {
    private String name;
    private long timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public CompositeKey() {
    }

    public CompositeKey(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public int compareTo(CompositeKey o) {
        int comparsion = this.getName().compareTo(o.getName());
        if(comparsion == 0){
            return this.getTimestamp() > o.getTimestamp() ? 1 : -1;
        }else {
            return comparsion;
        }

    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeLong(timestamp);

    }

    public void readFields(DataInput in) throws IOException {
        this.name = in.readUTF();
        this.timestamp = in.readLong();

    }
}
