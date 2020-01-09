package com.thongddps08464.assignmentjava5.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
    private String thuMucHinhNhanVien;

}