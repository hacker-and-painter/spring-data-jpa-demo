package org.hackerandpainter.springdatajpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-07-09 23:41
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Long cId;
    private BigDecimal total;

    //实体映射重复列必须设置：insertable = false,updatable = false
    @OneToOne
    @JoinColumn(name = "cId", insertable = false, updatable = false)
    private Customer customer;

}
