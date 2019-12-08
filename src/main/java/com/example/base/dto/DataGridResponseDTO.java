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
public class DataGridResponseDTO<T> {

    private PageInfo paginationDTO;
    private T data;

    public DataGridResponseDTO() {
    }

    public DataGridResponseDTO(T data) {
        this.data = data;
    }

    public DataGridResponseDTO(PageInfo paginationDTO, T data) {
        this.paginationDTO = paginationDTO;
        this.data = data;
    }

    public PageInfo getPaginationDTO() {
        return paginationDTO;
    }

    public void setPaginationDTO(PageInfo paginationDTO) {
        this.paginationDTO = paginationDTO;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
