package com.thongddps08464.assignmentjava5.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thongddps08464.assignmentjava5.config.props.FileStorageProperties;
import com.thongddps08464.assignmentjava5.exception.FileStorageException;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.reponse.UploadFileResponse;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getThuMucHinhNhanVien()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Không thể tạo thư mục để lưu hình.", ex);
        }
    }

	public Response storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				return new Response(false, "Tên file không phù hợp: " + fileName, new UploadFileResponse(fileName, file.getContentType(), file.getSize()));
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return new Response(true, "Lưu hình thành công", new UploadFileResponse(fileName, file.getContentType(), file.getSize()));
		} catch (IOException ex) {
			return new Response(false, "Không thể lưu hình: " + fileName + ". Hãy thử lại sau!", new UploadFileResponse(fileName, file.getContentType(), file.getSize()));
		}
	}
}