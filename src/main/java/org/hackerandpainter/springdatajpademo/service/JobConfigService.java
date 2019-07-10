package org.hackerandpainter.springdatajpademo.service;

import org.hackerandpainter.springdatajpademo.dto.JobConfig;
import org.hackerandpainter.springdatajpademo.repositories.JobConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobConfigService {

    @Autowired
    private JobConfigRepository jobConfigRepository;

    public List<JobConfig> findAllByStatus(Integer status) {
        return jobConfigRepository.findAllByStatus(status);
    }
}
