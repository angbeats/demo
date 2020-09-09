package com.my.qs.grpc.client;

import com.my.qs.grpc.HelloGrpc;
import com.my.qs.grpc.MyRequest;
import com.my.qs.grpc.MyResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

	private ManagedChannel managedChannel;
	private HelloGrpc.HelloBlockingStub helloBlockingStub;


	public GrpcClient (){
		managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 8888)
			.usePlaintext()
			.build();
		helloBlockingStub = HelloGrpc.newBlockingStub(managedChannel);
	}


	private void stop () throws InterruptedException {
		if (managedChannel != null){
			managedChannel.shutdown();
//			managedChannel.awaitTermination(5, TimeUnit.SECONDS);
		}
	}

	public static void main(String[] args) throws Exception{


		GrpcClient grpcClient = new GrpcClient();

		MyRequest request = MyRequest.newBuilder().setName("testtttt").build();

		MyResponse response = grpcClient.helloBlockingStub.sayHello(request);
		System.out.println(response.getAge());
		System.out.println(response);

		grpcClient.stop();

	}
}
