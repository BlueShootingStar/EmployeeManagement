package com.thongddps08464.assignmentjava5.exception;

public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = -1254551566155996270L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}