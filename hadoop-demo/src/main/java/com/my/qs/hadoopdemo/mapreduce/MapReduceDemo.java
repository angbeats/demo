package com.my.qs.hadoopdemo.mapreduce;

import com.sun.xml.internal.rngom.util.Uri;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URL;

public class MapReduceDemo {

	static {
		System.setProperty("hadoop.home.dir", "E:\\hadoop\\");
		System.load("E:\\hadoop\\bin\\hdfs.dll");
	}

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
//		configuration.set("fs.defaultFs", "fdfs://106.12.194.126:8020");

		Job job = Job.getInstance(configuration, "wordcount");

		job.setJarByClass(MapReduceDemo.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

//		FileInputFormat.addInputPath(job, new Path("hdfs://106.12.194.126:8020/test/"));
//		FileOutputFormat.setOutputPath(job, new Path("hdfs://106.12.194.126:8020/testOutput"));

		FileInputFormat.addInputPath(job, new Path("E:\\demo\\37.hadoop-demo\\input\\"));
		FileOutputFormat.setOutputPath(job, new Path("E:\\demo\\37.hadoop-demo\\output"));


		job.waitForCompletion(true);

	}
}
