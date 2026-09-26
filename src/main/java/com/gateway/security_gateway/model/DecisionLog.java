package com.gateway.security_gateway.model;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "decision_logs")
public class DecisionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2000)
    private String content;
    private String decision;
    private String source;
    private Instant timestamp;

    public DecisionLog() {}

    public DecisionLog(String content, String decision, String source, Instant timestamp) {
        this.content = content;
        this.decision = decision;
        this.source = source;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}