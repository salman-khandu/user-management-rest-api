/**
 * 
 */
package com.example.base.dto;

/**
 * 
 * @author Salman.Khandu
 *
 * @param <T>
 */
public class DataGridSearchCriteria<T> {

	private PageInfo pageInfo;
	private T searchCriteria;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public T getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(T searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
