package com.parfumaria.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String  message;
    private T data;

    public static <T> GenericResponse <T> empty(){
        return success(null, "no data no show");
    }

    public static<T> GenericResponse<T> success(T data,String message){
        return GenericResponse.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
    }

    public static <T> GenericResponse <T> eror(String message){
        return GenericResponse.<T>builder()
        .success(false)
        .message(message)
        .build();
    }
}
