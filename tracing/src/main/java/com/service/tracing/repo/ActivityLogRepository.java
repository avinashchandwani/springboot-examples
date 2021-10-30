package com.service.tracing.repo;

import com.service.tracing.entity.ActivityLog;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityLogRepository extends CassandraRepository<ActivityLog, UUID> {

}
