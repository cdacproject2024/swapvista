package com.swapvista.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageServiceImpl implements StorageService {

//	assigns default value to basepath
	@Value("${com.onlinebidding.image.folder.path}")
	private String BASEPATH;

	@Override
	public List<String> loadAll() {
		File dirPath = new File(BASEPATH);
//		list method returns the array of names of all the files in the given path
		return Arrays.asList(dirPath.list());
	}

	
	
	@Override
//	takes MultipartFile as input and returns file name
	public String store(MultipartFile file) {

//		extracting file extension
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
//		generated Universal Unique ID, converts to string, removes hyphens, appends files extension to it
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
		
//		creates file object respresenting path of the file where ims are stored
//		BASEPATH = path of folder where img is stored 
//		filename is concatenated to create complete file path
		File filePath = new File(BASEPATH, fileName);
		
//		opens FileOutputStream to the specified filepath
		try (FileOutputStream out = new FileOutputStream(filePath)) 
		{
			
//			 copy the contents of the uploaded file (obtained via file.getInputStream()) 
//			 to the output stream
			FileCopyUtils.copy(file.getInputStream(), out);
			
//			file stored successfully then file name is returned
			return fileName;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Resource load(String fileName)
	{
//		filePath represents the path of files where all images are stored
//		fileName represents name of the file
		File filePath = new File(BASEPATH, fileName);
		if (filePath.exists())
			return new FileSystemResource(filePath);
		return null;
	}

	@Override
	public void delete(String fileName) {
		File filePath = new File(BASEPATH, fileName);
		if (filePath.exists())
			filePath.delete();
	}

}
