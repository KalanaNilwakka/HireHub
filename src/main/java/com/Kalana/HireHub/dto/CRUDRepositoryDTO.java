package com.Kalana.HireHub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CRUDRepositoryDTO<T> {
    private Boolean status;
    private String message;
    private T data;
}
