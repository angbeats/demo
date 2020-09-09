package com.my.qs.hadoopdemo.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.net.URI;

public class HdfsSystem {

	public static void main(String[] args) throws Exception {
//		mkdir("/test");
//		putFile();
//		rmdir("/test");
//		listDir("/");
//		listFile("/test");
	}

	public static FileSystem getInstance() throws Exception {
		Configuration configuration = new Configuration();
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://106.12.194.126:8020"),configuration, "root");
		return fileSystem;
	}

	public static void mkdir(String path) throws Exception {
		FileSystem fileSystem = getInstance();
		fileSystem.mkdirs(new Path(path));
		fileSystem.close();
	}

	public static void putFile() throws Exception{
		FileSystem fileSystem = getInstance();
		String path = "E:\\demo\\37.hadoop-demo\\target\\classes\\test.txt";
		fileSystem.copyFromLocalFile(new Path(path), new Path("/test"));
		fileSystem.close();
	}

	public static void rmdir(String path) throws Exception{
		FileSystem fileSystem = getInstance();
		fileSystem.delete(new Path(path), true);
		fileSystem.close();
	}

	public static void listDir(String path) throws Exception {
		FileSystem fileSystem = getInstance();
		FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));
		for (FileStatus fileStatus : fileStatuses) {
			System.out.println(fileStatus.getPath().toString());
		}

	}

	public static void listFile(String path, boolean recursive) throws Exception{
		FileSystem fileSystem = getInstance();
		RemoteIterator<LocatedFileStatus> remoteIterator = fileSystem.listFiles(new Path(path), recursive);
		while (remoteIterator.hasNext()){
			LocatedFileStatus next = remoteIterator.next();
			System.out.println(next.getPath().toString());
		}
	}

	public static void listFile(String path) throws Exception{
		listFile(path, false);
	}

}
