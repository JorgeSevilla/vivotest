package br.com.vivo.telefonica.superhero.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vivo.telefonica.superhero.model.UploadFileResponse;
import br.com.vivo.telefonica.superhero.service.FileStorageService;

@RestController
public class FileController {

	private static final Logger loggger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile")
				.path(fileName)
				.toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri,
				file.getContentType(), file.getSize());
	}
	
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloaFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = (Resource) fileStorageService.loadFileAsResource(fileName);
		
		String contenType = null;
		try {
			contenType = request.getServletContext().getMimeType(((org.springframework.core.io.Resource) resource).getFile().getAbsolutePath());
		} catch (IOException ex) {
			// TODO: handle exception
			loggger.info("Undetermine type file");
		}
		
		if(contenType == null) {
			contenType = "appplication/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contenType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ((org.springframework.core.io.Resource) resource).getFilename() + "\"")
				.body(resource);
	}
}
