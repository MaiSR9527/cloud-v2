package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: MaiShuRen
 * @date: 2020/4/21 15:30
 * @version: v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StreamConsumer9003Application {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumer9003Application.class, args);
    }
}
