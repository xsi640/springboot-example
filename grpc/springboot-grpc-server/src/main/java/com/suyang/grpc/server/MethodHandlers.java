package com.suyang.grpc.server;

import com.suyang.grpc.facade.HelloReply;
import com.suyang.grpc.facade.HelloRequest;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

public final class MethodHandlers<Req, Resp> implements
        ServerCalls.UnaryMethod<Req, Resp>,
        ServerCalls.ServerStreamingMethod<Req, Resp>,
        ServerCalls.ClientStreamingMethod<Req, Resp>,
        ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
        this.serviceImpl = serviceImpl;
        this.methodId = methodId;
    }

    @Override
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
        switch (methodId) {
            case 0:
                serviceImpl.sayHello((HelloRequest) request, (StreamObserver<HelloReply>) responseObserver);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public StreamObserver<Req> invoke(StreamObserver<Resp> responseObserver) {
        switch (methodId) {
            default:
                throw new AssertionError();
        }
    }
}
