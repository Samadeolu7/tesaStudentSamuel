package com.example.tesastudentsamuel.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<AnyDataType> {

    private String responseCode;
    private String responseMessage;
    private AnyDataType data;

}

