package com.RcsSummaryCorrector.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RcsSummaryCorrector.Modal.RcsSummary;
import com.RcsSummaryCorrector.Repository.RcsSummaryRepository;

@Service
public class RcsSummaryCorrrecterService {
	
	@Autowired
	RcsSummaryRepository rcsSummaryRepository;
	
	@Autowired
	RequesttoExternalService requesttoExternalService;

	public List<?> correctRcsSummaryByUploadedFile(MultipartFile file, String date)
	{
		
		List<Map<String,String>> rcsSummaryViData = parseExcelFile(file);		
		for(Map<String, String> mps : rcsSummaryViData )
		{
			if (!mps.get("Template").equals("default")) {
				
				
				List<RcsSummary> rcsSummariesbyTemplate= rcsSummaryRepository.findtemplatebyLeadid(mps.get("Template"), date);				
				String rcssummaryleadidString = rcsSummariesbyTemplate.stream().filter(rcsSummary->rcsSummary.getLeadId()!=null).map(RcsSummary::getLeadId).collect(Collectors.joining(", "));

				
				
			if (rcsSummariesbyTemplate.isEmpty()) {
				
				continue;
			}	
			
				int submitted = Integer.parseInt(mps.get("Messages Submitted").replace(",", ""));
				int submitSent = Integer.parseInt(mps.get("Messages Sent").replace(",", ""));
				int submitFailed = Integer.parseInt(mps.get("Messages Failed").replace(",", ""));
				int submitDelivered = Integer.parseInt(mps.get("Messages Delivered").replace(",", ""));
				int submitRead = Integer.parseInt(mps.get("Messages Read").replace(",", ""));
			
				
				double sentvsSubmitted = (((double)submitSent/submitted)*100);
//				sentvsSubmitted = Math.round(sentvsSubmitted * 100.0) / 100.0;

				double failedvsSubmitted = (((double)submitFailed/submitted)*100);
//				failedvsSubmitted = Math.round(failedvsSubmitted * 100.0) / 100.0;
				
				double deliveredvsSubmitted = (((double)submitDelivered/submitted)*100);
//				deliveredvsSubmitted = Math.round(deliveredvsSubmitted * 100.0) / 100.0;
				
				double readvsSubmitted = (((double)submitRead/submitted)*100);
//				readvsSubmitted = Math.round(readvsSubmitted * 100.0) / 100.0;
								
				
				
				int applicationSubmitCount = rcsSummariesbyTemplate.stream()
					    .filter(rcsSummary -> rcsSummary.getSubmitted() != null && !rcsSummary.getSubmitted().isEmpty())
					    .mapToInt(rcsSummary -> {
					        try {
					            return Integer.parseInt(rcsSummary.getSubmitted().replace(",", ""));
					        } catch (NumberFormatException e) {
					            return 0;
					        }
					    })
					    .sum();

				System.out.println("applicationSubmitCount : "+ applicationSubmitCount);
				
				System.out.println("rcsSummariesbyTemplate : "+rcsSummariesbyTemplate.size() );
				
				if (applicationSubmitCount!=submitted) {
					System.out.println("Application Subbmited count is not matched with operator "+ rcssummaryleadidString );
//					requesttoExternalService.SlackAlert("Application Subbmited count is not matched with operator "+ rcssummaryleadidString, "Alert generated from rcs Summay Corrector");
					continue;
				}
				
				else {
					
					
					
					if (rcsSummariesbyTemplate.size()==1) {
						
						RcsSummary rcsSummary = rcsSummariesbyTemplate.get(0);
						
						System.out.println("submitDelivered :"+ submitDelivered + " submitFailed : "+ submitFailed+ " submitRead : "+ submitRead + " submitSent : "+ submitSent );
						
						rcsSummary.setDelivered(String.valueOf(submitDelivered));
						rcsSummary.setFailed(String.valueOf(submitFailed));
						rcsSummary.setReadNo(String.valueOf(submitRead));
						rcsSummary.setSent(String.valueOf(submitSent));
						
						rcsSummaryRepository.save(rcsSummary);
						
					}
					
					else {
						
						
						for(RcsSummary rcsSummary:rcsSummariesbyTemplate )
						{
							int applicationRcsSummarySubmitted = Integer.parseInt( rcsSummary.getSubmitted());	

							int correctedSentCount = (int)((sentvsSubmitted / 100.0 )* applicationRcsSummarySubmitted);
							int correctedFailedCount = (int)((failedvsSubmitted / 100.0 )* applicationRcsSummarySubmitted);
							int correctedDeliverCount = (int)((deliveredvsSubmitted / 100.0 )* applicationRcsSummarySubmitted);
							int correctedReadCount = (int)((readvsSubmitted / 100.0 )* applicationRcsSummarySubmitted);

							
							System.out.println("Sent :"+ correctedSentCount + " Failed : "+ correctedFailedCount+ " Delivered : "+ correctedDeliverCount + " Read : "+ correctedReadCount );
							
							rcsSummary.setDelivered(String.valueOf(correctedDeliverCount));
							rcsSummary.setFailed(String.valueOf(correctedFailedCount));
							rcsSummary.setReadNo(String.valueOf(correctedReadCount));
							rcsSummary.setSent(String.valueOf(correctedSentCount));
							
							rcsSummaryRepository.save(rcsSummary);
							
						}
						
						
					}
					
					
					
					
					
				}
				
				
			
				
			}
					
		}
		

		
		return rcsSummaryViData;
	
	}
	
	
	
	  public List<Map<String,String>> parseExcelFile(MultipartFile file) {
	        List<Map<String,String>> data = new ArrayList<>();

	        try (InputStream inputStream = file.getInputStream();
	             Workbook workbook = new XSSFWorkbook(inputStream)) {

	            // Get the first sheet
	            Sheet sheet = workbook.getSheetAt(0); 
	                 
	            
	            Row headeRow = sheet.getRow(5);
	            List<String> headerRowString = new ArrayList<>();
	            
	            for (Cell cell : headeRow) {
                		
	            	headerRowString.add(getCellValueAsString(cell));
	                   
	                }
	            
	            System.out.println("Header of File : "+ headerRowString);
	            
	            
	            
	            // Iterate through rows
	            for (Row row : sheet) {
	            	if (row.getRowNum()>5) {
		               Map<String,String> rowData = new HashMap<>();
		             // Iterate through cells in the row
		                for (int colIndex = 0; colIndex < headerRowString.size(); colIndex++) {
		                	
		                	Cell cell = row.getCell(colIndex,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		                	
		                   System.out.print(getCellValueAsString(cell) +" ,");
		                }

		                	System.out.println();
	            	}

	                
	            }
	            
	            // Iterate through rows
	            for (Row row : sheet) {
	            	if (row.getRowNum()>5) {
		               Map<String,String> rowData = new HashMap<>();
		             // Iterate through cells in the row
		                for (int colIndex = 0; colIndex < headerRowString.size(); colIndex++) {
		                	
		                	Cell cell = row.getCell(colIndex,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		                	
		                   rowData.put(headerRowString.get(colIndex), getCellValueAsString(cell));
		                }

		                data.add(rowData);
					}

	                
	            }

	        } catch (Exception e) {
	        	
	        	e.printStackTrace();
	            throw new RuntimeException("Error while parsing Excel file", e);
	        }

	        return data;
	    }

	  
	  
	  
	    private String getCellValueAsString(Cell cell) {
	        if (cell == null) return "";

	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString();
	                } else {
	                    return String.valueOf(cell.getNumericCellValue());
	                }
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return "";
	        }
	    }
	
}
