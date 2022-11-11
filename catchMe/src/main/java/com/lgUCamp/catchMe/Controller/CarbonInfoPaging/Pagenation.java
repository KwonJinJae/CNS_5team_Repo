package com.lgUCamp.catchMe.Controller.CarbonInfoPaging;

public class Pagenation {
	
	/* 검색어가 없는 경우 페이징 처리 */
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount) {
		
		return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null);
	}

	/* 검색값만 있을 경우 */
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition) {

		return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, null);
	}
	
	/* 검색어 있을 경우 */
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue) {
		
		int maxPage;			
		int startPage;		
		int endPage;		
		int startRow;
		int endRow;
		

		maxPage = (int) Math.ceil((double) totalCount / limit);

		startPage = (int) (Math.ceil((double) pageNo / buttonAmount) - 1) * buttonAmount + 1;


		endPage = startPage + buttonAmount - 1;
		
		if(maxPage < endPage){
			endPage = maxPage;
		}
		
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		/* 조회할 시작 번호와 마지막 행 번호를 계산한다. */
		startRow = (pageNo - 1) * limit + 1;
		endRow = startRow + limit - 1;
		
		System.out.println("startRow : " + startRow);
		System.out.println("endRow : " + endRow);
		
		SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount ,maxPage, startPage, endPage, startRow, endRow, searchCondition, searchValue);
		
		return selectCriteria;
	}
}
