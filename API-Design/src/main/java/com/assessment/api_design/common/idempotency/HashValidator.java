package com.assessment.api_design.common.idempotency;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashValidator {
    private StringRedisTemplate redisTemplate;

    public boolean isAllowed(String requestHash) {

        long now = System.currentTimeMillis() / 1000; // seconds

        List<String> keys = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            keys.add(requestHash + ":" + (now - i));
        }

        // fetch all counters in last 60 seconds
        List<String> values = redisTemplate.opsForValue().multiGet(keys);

        long total = 0;

        if (values != null) {
            for (String v : values) {
                if (v != null) {
                    total += Long.parseLong(v);
                }
            }
        }

        if (total >= 1) {
            return false;
        }

        // increment current bucket
        String currentKey = requestHash + ":" + now;

        redisTemplate.opsForValue().increment(currentKey);

        // expire after window + buffer
        redisTemplate.expire(currentKey, Duration.ofMinutes(2));

        return true;
    }
}
