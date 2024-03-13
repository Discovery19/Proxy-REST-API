package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String username;

    private String methodName;

    private String className;

    private String arguments;

    public AuditLog() {
        this.timestamp = LocalDateTime.now();
    }
    public AuditLog(LocalDateTime timestamp, String username, String methodName, String className, String arguments
    ) {
        this.timestamp = timestamp;
        this.username = username;
        this.methodName = methodName;
        this.className = className;
        this.arguments = arguments;
    }

}
