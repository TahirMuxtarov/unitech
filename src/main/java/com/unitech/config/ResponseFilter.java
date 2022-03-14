package com.unitech.config;

import com.unitech.model.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@Slf4j
@ControllerAdvice
public class ResponseFilter implements ResponseBodyAdvice<CommonResponse> {
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        Type returnType = methodParameter.getGenericParameterType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType parameterizedReturnType = (ParameterizedType) returnType;
            Class<?> clazz = (Class<?>) parameterizedReturnType.getRawType();
            Type typeParam = parameterizedReturnType.getActualTypeArguments()[0];
            if (ResponseEntity.class.equals(clazz)) {
                Type nestedTypeParam = typeParam instanceof ParameterizedType ?
                        ((ParameterizedType) typeParam).getRawType() : typeParam;
                Class<?> classParam = (Class<?>) nestedTypeParam;
                return CommonResponse.class.isAssignableFrom(classParam) ||
                        methodParameter.getDeclaringClass() == ResponseEntityExceptionHandler.class;
            } else {
                return CommonResponse.class.isAssignableFrom(clazz);
            }
        } else {
            Class<?> clazz = (Class<?>) returnType;
            return CommonResponse.class.isAssignableFrom(clazz);
        }
    }

    @Override
    public CommonResponse beforeBodyWrite(CommonResponse body,
                                            MethodParameter returnType,
                                            MediaType selectedContentType,
                                            Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            ServerHttpRequest request, ServerHttpResponse response) {

        if (body.getMessage() == null) {
            System.err.println("inside response filter before body write");
            body.setCode("200");
            body.setMessage("Success");
        }
        return body;
    }
}
