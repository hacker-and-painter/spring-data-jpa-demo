package org.hackerandpainter.springdatajpademo.repositories;

import org.hackerandpainter.springdatajpademo.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-07-10 20:11
 **/
public interface CustomerSpecificationRepository extends
        JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {
}
