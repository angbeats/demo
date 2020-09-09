package com.my.qs.hadoopdemo.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {


	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		for (String s : value.toString().split("\t")) {
			context.write(new Text(s), new LongWritable(1));
		}
	}
}
