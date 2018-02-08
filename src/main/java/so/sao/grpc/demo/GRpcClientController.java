package so.sao.grpc.demo;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import so.sao.test.protobuf.GreeterGrpc;
import so.sao.test.protobuf.Hello;
import so.sao.test.protobuf.PhoneServiceGrpc;
import so.sao.test.protobuf.TestServer;

/**
 * @author xingxing.wu
 * @date 2018/2/8
 */
@RestController
public class GRpcClientController {

    @GrpcClient("grpc-server-demo")
    private Channel serverChannel;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(serverChannel);
        Hello.HelloResponse response = stub.sayHello(Hello.HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }

    @GetMapping("/addPhone/{uid}/{phoneNumber}/{phoneType}")
    public String addPhone(@PathVariable Integer uid, @PathVariable String phoneNumber, @PathVariable TestServer.PhoneType phoneType) {
        PhoneServiceGrpc.PhoneServiceBlockingStub stub = PhoneServiceGrpc.newBlockingStub(serverChannel);
        TestServer.AddPhoneToUserRequest request = TestServer.AddPhoneToUserRequest.newBuilder().setUid(uid).setPhoneType(phoneType)
                .setPhoneNumber(phoneNumber).build();
        TestServer.AddPhoneToUserResponse addPhoneToUserResponse = stub.addPhoneToUser(request);
        return addPhoneToUserResponse.toString();
    }
}
