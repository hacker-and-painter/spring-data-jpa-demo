package org.hackerandpainter.springdatajpademo.repositories;

import org.hackerandpainter.springdatajpademo.dto.MyOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public interface MyOrderRepository extends JpaSpecificationExecutor<MyOrder>, CrudRepository<MyOrder, Long> {
}
