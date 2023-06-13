package com.minidooray.taskapi.priority.service.impl;

import com.minidooray.taskapi.priority.entity.PriorityStatus;
import com.minidooray.taskapi.priority.service.PriorityService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    public List<PriorityStatus> getPriorityStatus() {
        return Arrays.asList(PriorityStatus.values());
    }
}
