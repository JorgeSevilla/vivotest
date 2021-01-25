package br.com.vivo.telefonica.superhero.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import br.com.vivo.telefonica.superhero.util.FileStorageException;
import br.com.vivo.telefonica.superhero.util.FileStorageProperties;
import br.com.vivo.telefonica.superhero.util.MyFileNotFoundException;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService() {
		this.fileStorageLocation = null;
	}
	
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			// TODO: handle exception
			throw new FileStorageException("Can't create the directory where uoload files", ex);
		}
	}
	
	public String storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			readJson(file);
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! File name contains invalid path sequence " + fileName);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
			
		} catch (IOException ex) {
			// TODO: handle exception
			throw new FileStorageException("Not store file " + fileName + ". Pelase try again", ex);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new MyFileNotFoundException("File not found" + fileName);
			}
		} catch (MalformedURLException ex) {
			// TODO: handle exception
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
	
	public void readJson(MultipartFile file) throws IOException {
		InputStream is = null;
		
		try {
			is = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonMap = mapper.readTree(is).get("data");
			
			ArrayNode data = (ArrayNode)jsonMap;
			
			if(data != null) {
				for (JsonNode result : data) {
					JsonNode time = result.get("from").get("time");
					JsonNode nameSuperHero = result.get("from").get("nameSuperHero");
					JsonNode returnNumber = result.get("from").get("returnNumber");
					JsonNode returnTime = result.get("from").get("returnTime");
					JsonNode speed = result.get("from").get("speed");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			is.close();
		}
	}
}
