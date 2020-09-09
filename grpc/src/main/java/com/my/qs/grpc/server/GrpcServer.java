package com.my.qs.grpc.server;

import com.my.qs.grpc.impl.HelloServerImpl;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class GrpcServer {
	private Server server;

	private void start() throws IOException {
		server =NettyServerBuilder.forPort(8888)
			.addService(new HelloServerImpl())
			.build().start();

		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				GrpcServer.this.stop();
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	private void awaitForTerminate() throws InterruptedException {
		if (server != null){
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws Exception{
		GrpcServer grpcServer = new GrpcServer();
		grpcServer.start();
		grpcServer.awaitForTerminate();

	}
}
