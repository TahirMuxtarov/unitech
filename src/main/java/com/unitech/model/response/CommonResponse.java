package com.unitech.model.response;

import com.unitech.model.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CommonResponse {

    public CommonResponse(){

    }


    private String code;
    private String message;
    //private Response response;

}
