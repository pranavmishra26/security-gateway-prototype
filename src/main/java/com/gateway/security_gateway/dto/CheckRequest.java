package com.gateway.security_gateway.dto;
import jakarta.validation.constraints.NotBlank;
public class CheckRequest {
    @NotBlank(message = "content must not be empty")
    private String content;
    public CheckRequest() {}
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
}
