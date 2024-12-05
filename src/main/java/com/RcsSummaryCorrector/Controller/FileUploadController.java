package com.RcsSummaryCorrector.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RcsSummaryCorrector.Service.RcsSummaryCorrrecterService;


@RestController
@RequestMapping("/Rcs/SummaryCorrector")
public class FileUploadController {

	
	@Autowired
	RcsSummaryCorrrecterService rcsSummaryCorrrecterService;
	
	Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	

	@PostMapping(value = "/uploadViFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> uploadUserIpMappingFile(@RequestPart("file") MultipartFile file, @RequestParam("date") String date) throws IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		
		 // Validate the date format
        try {
            sdf.parse(date); 
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format. Expected format: yyyy-MM-dd");
        }
			
		
		try {
			
			if (file.getOriginalFilename().endsWith("xlsx")) {

					String  fileName = file.getOriginalFilename();
					return ResponseEntity.ok(rcsSummaryCorrrecterService.correctRcsSummaryByUploadedFile(file,date));
	

			} else {
				return ResponseEntity.badRequest().body("Invalid File Format, Format Should be .xlsx !! ");
			}

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("SomeException Occur : " + e.getMessage());
		}
	}
}
