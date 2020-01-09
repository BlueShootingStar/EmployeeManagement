package com.thongddps08464.assignmentjava5.reponse;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String fileName;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
}