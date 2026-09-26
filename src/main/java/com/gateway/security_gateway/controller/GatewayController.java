package com.gateway.security_gateway.controller;
import com.gateway.security_gateway.dto.CheckRequest;
import com.gateway.security_gateway.dto.CheckResponse;
import com.gateway.security_gateway.model.DecisionLog;
import com.gateway.security_gateway.repository.DecisionLogRepository;
import com.gateway.security_gateway.service.GatewayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private DecisionLogRepository repository;
    @PostMapping("/check")
    public CheckResponse check(@Valid @RequestBody CheckRequest request){
        return gatewayService.evaluate(request.getContent());
    }
    @GetMapping("/logs")
    public List<DecisionLog> getlogs(){
        return repository.findAll();
    }
}
