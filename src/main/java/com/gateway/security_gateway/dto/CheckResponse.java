package com.gateway.security_gateway.dto;

public class CheckResponse {
    private String decision;
    private String source;
    public CheckResponse() {}
    public CheckResponse(String decision,String source){
        this.decision=decision;
        this.source=source;
    }
    public String getDecision() {
        return decision;
    }
    public void setDecision(String decision) {
        this.decision = decision;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
}
