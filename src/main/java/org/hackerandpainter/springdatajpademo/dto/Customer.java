package org.hackerandpainter.springdatajpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-07-09 23:38
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.NamedQuery(name="Customer.findByFirstName",query = "select c from Customer c where c.firstName = ?1")
public class Customer {

    /*
       @GeneratedValue
       TABLE：使用一个特定的数据库表格来保存主键。
       SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
       IDENTITY：主键由数据库自动生成（主要是自动增长型）
       AUTO：主键由程序控制。
    */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    //一对多，一个客户对应多个订单，关联的字段是订单里的cId字段
    @OneToMany
    @JoinColumn(name = "cId")
    private List<MyOrder> myOrders;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
