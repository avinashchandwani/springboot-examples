package com.service.tracing.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@AllArgsConstructor
public class ActivityLog {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.ASCENDING)
    private UUID id;

    private int responseStatus;

    private String apiPath;

    private long apiExecutionTime;

    private long apiExecutionMillis;

    private String apiMethod;

    private String clientIpAddress;

    @PrimaryKeyColumn(name = "organization_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private int organizationId;

    public ActivityLog() {
        this.id = UUID.randomUUID();
    }

    public ActivityLog(int responseStatus, String apiPath, Long apiExecutionTime, Long apiExecutionMillis, String apiMethod,
                       String clientIpAddress, int organizationId) {
        this.id = UUID.randomUUID();
        this.responseStatus = responseStatus;
        this.apiPath = apiPath;
        this.apiExecutionTime = apiExecutionTime;
        this.apiExecutionMillis = apiExecutionMillis;
        this.apiMethod = apiMethod;
        this.clientIpAddress = clientIpAddress;
        this.organizationId = organizationId;
    }
}
