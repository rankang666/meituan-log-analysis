package simpleMoving.MR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @Author rk
 * @Date 2018/9/17 14:25
 * @Description:
 **/
public class SortInMemory_MovingAverageDriver {
    public static void main(String[] args) throws Exception  {
        Configuration conf = new Configuration();

//    conf.set("fs.defaultFS","hdfs://ran:9000")

//    System.setProperty("HADOOP_USER_NAME","ran")

        Job job = Job.getInstance(conf);

        job.setJarByClass(SortInMemory_MovingAverageDriver.class);

        job.setMapperClass(SortInMemory_MovingAverageMapper.class);
        job.setReducerClass(SortInMemory_MovingAverageReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TimeSeriesData.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);


        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        FileInputFormat.setInputPaths(job,inputPath);
        FileSystem fs = FileSystem.get(conf);

        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        FileOutputFormat.setOutputPath(job,outputPath);

        boolean isDone = job.waitForCompletion(true);

        System.exit(isDone ? 0 : 1);


    }

}
