package com.my.qs.hadoopdemo.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {


	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		Iterator<LongWritable> iterator = values.iterator();
		while (iterator.hasNext()){
			LongWritable next = iterator.next();
			sum += next.get();
		}
		context.write(key, new LongWritable(sum));
	}
}
