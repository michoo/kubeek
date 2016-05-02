package com.kubeek.service;

import com.kubeek.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.kubeek")
public class PlatformApplication {

	public static void main(String[] args) throws UnknownHostException {
		if(Utils.javaVersion()>=8){

            SpringApplication app = new SpringApplication(PlatformApplication.class);
            SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
            //addDefaultProfile(app, source);
            Environment env = app.run(args).getEnvironment();

            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://127.0.0.1:{}\n\t" +
                            "External: \thttp://{}:{}\n----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));

		} else{
			log.error("Please install jdk 8 to run kubeek-server");
		}


	}
}
