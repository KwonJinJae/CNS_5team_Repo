package com.lgUCamp.catchMe.Controller.CarbonInfoPaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SelectCriteria implements java.io.Serializable {
	private static final long serialVersionUID = 5942028854052073017L;
	
	private int pageNo;		
	private int totalCount;	
	private int limit;				
	private int buttonAmount;		
	private int maxPage;		
	private int startPage;			
	private int endPage;		
	private int startRow;				
	private int endRow;				
	private String searchCondition;		//검색 조건
	private String searchValue;			
	

}
