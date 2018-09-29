package simpleMoving.MR2;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @Author rk
 * @Date 2018/9/17 16:41
 * @Description:
 **/
public class CompositeKeyComparator extends WritableComparator {
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return super.compare(a, b);
    }
}
