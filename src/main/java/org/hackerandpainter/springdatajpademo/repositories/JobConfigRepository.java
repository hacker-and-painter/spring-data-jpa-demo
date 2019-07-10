package org.hackerandpainter.springdatajpademo.repositories;

import org.hackerandpainter.springdatajpademo.dto.JobConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobConfigRepository extends JpaRepository<JobConfig, Integer> {
    List<JobConfig> findAllByStatus(int status);
}
