package com.service.tracing.repo;

import com.service.tracing.entity.ActivityLog;
import com.service.tracing.repo.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Component
@EnableAsync
public class CustomHttpTraceRepository implements HttpTraceRepository {

    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    @Autowired
    private ActivityLogRepository activityLogRepository;

    private static boolean shouldTraceMethod(String method) {
        return HttpMethod.GET.matches(method) || HttpMethod.POST.matches(method) ||
                HttpMethod.PUT.matches(method) || HttpMethod.PATCH.matches(method) ||
                HttpMethod.DELETE.matches(method);
    }

    @Override
    public List<HttpTrace> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Async("threadPoolTaskExecutor")
    @Override
    public void add(HttpTrace trace) {
        if (shouldTraceMethod(trace.getRequest().getMethod())) {
            Random random = new Random();
            ActivityLog activityLog = new ActivityLog(trace.getResponse().getStatus(),
                    trace.getRequest().getUri().getPath(),
                    trace.getTimeTaken(),
                    trace.getTimestamp().getEpochSecond(), trace.getRequest().getMethod(), trace.getRequest().getRemoteAddress(), random.nextInt(10));
            lastTrace.set(trace);
            activityLogRepository.save(activityLog);
        }
    }
}