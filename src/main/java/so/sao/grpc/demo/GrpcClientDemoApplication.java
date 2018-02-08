package so.sao.grpc.demo;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientDemoApplication.class, args);
	}
}
