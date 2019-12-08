/**
 * 
 */
package com.example.base.dto;

/**
 * 
 * @author Salman.Khandu
 *
 */
public class PageInfo {

	private int totalPages;
	private long totalElements;
	private int currentPageNumber;
	private int pageSize;
	private Sort sort;

	public PageInfo() {
	}

	public PageInfo(int totalPages, long totalElements, int currentPageNumber, int pageSize) {
		super();
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.currentPageNumber = currentPageNumber;
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Sort getSort() {
		if (sort == null) {
			sort = new Sort();
		}
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

}
