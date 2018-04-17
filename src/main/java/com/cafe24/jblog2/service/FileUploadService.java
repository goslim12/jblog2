package com.cafe24.jblog2.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/uploads";  //실제 드라이브에 저장되는 위치
	private static String PREFIX_URL = "/uploads/images/";  //서버의 가상 URL(실제위치와 매핑시켜줘야 웹화면에서 읽을 수 있음)
	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
			Long size = multipartFile.getSize();
			String saveFileName = genSaveFileName(extName);

//			System.out.println("===============");
//			System.out.println("originFileName : " + originFileName);
//			System.out.println("extName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);

			writeFile(multipartFile, saveFileName);
			
			url = PREFIX_URL + saveFileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return url;
	}

	private String genSaveFileName(String extName) {

		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
		fos.write(fileData);
		fos.close();
	}
}
