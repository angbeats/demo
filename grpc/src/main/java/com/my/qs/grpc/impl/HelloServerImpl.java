package com.my.qs.grpc.impl;

import com.my.qs.grpc.HelloGrpc;
import com.my.qs.grpc.MyRequest;
import com.my.qs.grpc.MyResponse;
import io.grpc.stub.StreamObserver;

public class HelloServerImpl extends HelloGrpc.HelloImplBase {
	@Override
	public void sayHello(MyRequest request, StreamObserver<MyResponse> responseObserver) {
		System.out.println(request);
		String name = request.getName();
		System.out.println(name);

		MyResponse response = MyResponse.newBuilder().setAge("12").build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
