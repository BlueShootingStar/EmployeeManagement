package com.thongddps08464.assignmentjava5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.thongddps08464.assignmentjava5.config.props.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Java5Ps08464AssignmentApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(Java5Ps08464AssignmentApplication.class, args);
	}
}
