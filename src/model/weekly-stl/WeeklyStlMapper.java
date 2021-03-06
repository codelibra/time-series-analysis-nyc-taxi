import java.util.*;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * Input - daily data grid, long, lat, freq
 * Output - stl
 */

public class WeeklyStlMapper extends Mapper<LongWritable, Text, Text,Text> {
 @Override
 public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

   String[] inputLine = value.toString().trim().split("\t");
   String fileKey = inputLine[0];
   String fileValue[] = inputLine[1].split(",");

   String newKeyGridId = fileValue[0]; 
   if(newKeyGridId.equals("-1")) return; 
   String outputValue = fileKey;
   for(int i=1;i<fileValue.length;++i){
      outputValue += "," + fileValue[i];
   }

   context.write(new Text(newKeyGridId), new Text(outputValue));
 }
}

