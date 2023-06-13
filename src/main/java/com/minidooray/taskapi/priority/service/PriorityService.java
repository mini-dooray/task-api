package com.minidooray.taskapi.priority.service;

import com.minidooray.taskapi.priority.entity.PriorityStatus;

import java.util.List;

public interface PriorityService {
    public List<PriorityStatus> getPriorityStatus();
}
