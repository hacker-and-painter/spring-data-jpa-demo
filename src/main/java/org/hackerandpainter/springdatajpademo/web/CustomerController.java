package org.hackerandpainter.springdatajpademo.web;

import org.hackerandpainter.springdatajpademo.dto.Customer;
import org.hackerandpainter.springdatajpademo.dto.CustomerProjection;
import org.hackerandpainter.springdatajpademo.repositories.CustomerRepository;
import org.hackerandpainter.springdatajpademo.repositories.CustomerSpecificationRepository;
import org.hackerandpainter.springdatajpademo.util.SpecificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-07-09 23:49
 **/
@RestController("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    /**
     * 初始化数据
     */
    @GetMapping("/index")
    public void index() {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
        repository.save(new Customer("Bauer", "Dessler"));
    }

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public void findAll(){
        List<Customer> result = repository.findAll();
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 删除ID为3的数据
     */
    @DeleteMapping("/delete")
    public void delete(){

        System.out.println("删除前数据：");
        List<Customer> customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }

        System.out.println("删除ID=3数据：");
        repository.deleteById(3l);

        System.out.println("删除后数据：");
        customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 查询ID为1的数据
     */
    @GetMapping("/findOne")
    public void findOne(){
        Optional<Customer> result = repository.findById(1L);
        if(result.isPresent()){
            System.out.println(result.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 查询lastName为指定用户昵称
     */
    @GetMapping("/findByLastName")
    public void findByLastName(){
        List<Customer> result = repository.findByLastName("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 查询FirstName为指定用户昵称
     */
    @GetMapping("/findByFirstName")
    public void findByFirstName(){
        Customer customer = repository.findByFirstName("Bauer");
        if(customer!=null){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询
     * 查询FirstName为指定字符串
     */
    @GetMapping("/findByFirstName2")
    public void findByFirstName2(){
        Customer customer = repository.findByFirstName2("Bauer");
        if(customer!=null){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询
     * 查询LastName为指定字符串
     */
    @GetMapping("/findByLastName2")
    public void findByLastName2(){
        List<Customer> result = repository.findByLastName2("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询,
     * 用@Param指定参数，匹配firstName和lastName
     */
    @GetMapping("/findByName")
    public void findByName(){
        List<Customer> result = repository.findByName("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询,使用关键词like
     * 用@Param指定参数，firstName的结尾为e的字符串
     */
    @GetMapping("/findByName2")
    public void findByName2(){
        List<Customer> result = repository.findByName2("e");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询，模糊匹配关键字e
     */
    @GetMapping("/findByName3")
    public void findByName3(){
        List<Customer> result = repository.findByName3("e");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询,
     * 用@Param指定参数，匹配firstName和lastName
     */
    @GetMapping("/findByName4")
    public void findByName4(){
        //按照ID倒序排列
        System.out.println("直接创建sort对象，通过排序方法和属性名");
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Customer> result = repository.findByName4("Bauer",sort);
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
        //按照ID倒序排列
        System.out.println("通过Sort.Order对象创建sort对象");
        Sort sortx = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        List<Customer> resultx = repository.findByName4("Bauer",sort);
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");

        System.out.println("通过排序方法和属性List创建sort对象");
        List<String> sortProperties = new ArrayList<>();
        sortProperties.add("id");
        sortProperties.add("firstName");
        Sort sort2 = new Sort(Sort.Direction.DESC,sortProperties);
        List<Customer> result2 = repository.findByName4("Bauer",sort2);
        for (Customer customer:result2){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");

        System.out.println("通过创建Sort.Order对象的集合创建sort对象");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
        List<Customer> result3 = repository.findByName4("Bauer",new Sort(orders));
        for (Customer customer:result3){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 根据FirstName进行修改
     */
    @GetMapping("/modifying")
    public void modifying(){
        Integer result = repository.setFixedFirstnameFor("Bauorx","Bauer");
        if(result!=null){
            System.out.println("modifying result:"+result);
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 分页
     * 应用查询提示@QueryHints，这里是在查询的适合增加了一个comment
     * 查询结果是lastName和firstName都是bauer这个值的数据
     */
    @GetMapping("/pageable")
    public void pageable(){
        //Pageable是接口，PageRequest是接口实现
        //PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
        Page<Customer> page = repository.findByName("bauer",pageable);
        //查询结果总行数
        System.out.println(page.getTotalElements());
        //按照当前分页大小，总页数
        System.out.println(page.getTotalPages());
        //按照当前页数、分页大小，查出的分页结果集合
        for (Customer customer: page.getContent()) {
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * find by projections
     */
    @GetMapping("/findAllProjections")
    public void findAllProjections(){
        Collection<CustomerProjection> projections = repository.findAllProjectedBy();
        System.out.println(projections);
        System.out.println(projections.size());
        for (CustomerProjection projection:projections){
            System.out.println("FullName:"+projection.getFullName());
            System.out.println("FirstName:"+projection.getFirstName());
            System.out.println("LastName:"+projection.getLastName());
        }
    }

    @Autowired
    private CustomerSpecificationRepository csr;

    /**
     *
     */
    @GetMapping("/spec")
    public void specificationQuery(){
        Specification<Customer> spec = SpecificationFactory.containsLike("lastName","bau");
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"id");
        Page<Customer> page = csr.findAll(spec,pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        for (Customer c:page.getContent()){
            System.out.println(c.toString());
        }
    }

    /**
     *
     */
    @GetMapping("/spec2")
    public void specificationQuery2(){
//        Specification<Customer> spec = new Specification<Customer>() {
//            @Override
//            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                return cb.like(root.get("firstName"),"%bau%");
//            }
//        };

        Specification<Customer> spec2 = Specification
                .where(SpecificationFactory.containsLike("firstName","bau"))
                .or(SpecificationFactory.containsLike("lastName","bau"));
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"id");
        Page<Customer> page = csr.findAll(spec2,pageable);
        System.out.println(page);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        for (Customer c:page.getContent()){
            System.out.println(c.toString());
        }
    }
}

