package com.suyang.grpc.server;

import com.suyang.grpc.facade.HelloReply;
import com.suyang.grpc.facade.HelloRequest;
import io.grpc.BindableService;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.stub.StreamObserver;

import static com.suyang.grpc.facade.GreeterGrpc.*;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public abstract class GreeterImplBase implements BindableService {

    public void sayHello(HelloRequest request,
                         StreamObserver<HelloReply> responseObserver) {
        asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    @Override
    public final ServerServiceDefinition bindService() {
        return ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                        getSayHelloMethod(),
                        asyncUnaryCall(new MethodHandlers<>(this, 0)))
                .build();
    }

    private static volatile MethodDescriptor<HelloRequest, HelloReply> methodDescriptor;

    private static MethodDescriptor<HelloRequest, HelloReply> getMethodDescriptor() {
        MethodDescriptor<HelloRequest,HelloReply> result = methodDescriptor;
        if(result == null){
            synchronized (GreeterImplBase.class){
                result = methodDescriptor;
                if(result == null){
                    methodDescriptor = result =
                    MethodDescriptor.newBuilder()
                    .setType(MethodDescriptor.MethodType.UNARY)
                    .setFullMethodName("Greeter/SayHello")
                    .setSampledToLocalTracing(true)
                    .setRequestMarshaller()
                }
            }
        }
    }

    private static volatile ServiceDescriptor serviceDescriptor;

    private static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (GreeterImplBase.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result =
                            ServiceDescriptor.newBuilder(SERVICE_NAME)
                                    .addMethod(getSayHelloMethod())
                                    .build();
                }
            }
        }
        return result;
    }
}
