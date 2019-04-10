package im.heart.demo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import im.heart.cms.dto.ArticleDTO;
import im.heart.cms.service.ArticleService;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/index/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    /**
     * 查询lastName为指定用户昵称
     */
    @RequestMapping("/findByLastName")
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
    @RequestMapping("/findByFirstName")
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
    @RequestMapping("/findByFirstName2")
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
    @RequestMapping("/findByLastName2")
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
    @RequestMapping("/findByName")
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
    @RequestMapping("/findByName2")
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
    @RequestMapping("/findByName3")
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
    @RequestMapping("/findByName4")
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
    @RequestMapping("/modifying")
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
    @RequestMapping("/pageable")
    public void pageable(){
        //Pageable是接口，PageRequest是接口实现
        //PageRequest的对象构造函数有多个，page是页数，初始值是1，size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable =  DynamicPageRequest.buildPageRequest(1,3,"id",Sort.Direction.DESC,Customer.class);
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
    @Autowired
    ArticleService articleService;
    /**
     * find by projections
     */
    @RequestMapping("/findAllProjections")
    public void findAllProjections(){
        List<ArticleDTO> projections=articleService.queryNearById(new BigInteger("1000"),new BigInteger("0"));
        for (ArticleDTO projection:projections){
            log.info("@@@@@title:{},LastName:{},projection:{}",projection.getTitle());
        }
    }
    /**
     * find by projections
     */
    @RequestMapping("/findAllProjectionsPage")
    public void findAllProjectionsPage(){
        Pageable pageable =  DynamicPageRequest.buildPageRequest(1,1,"id",Sort.Direction.DESC,Customer.class);
        Page<CustomerProjection> projections = repository.findAllProjectedBy(pageable);
        for (CustomerProjection projection:projections.getContent()){
            log.info("FirstName:{},LastName:{},projection:{}",projection.getFirstName(),projection.getLastName(),projection.getFullName());
        }
    }
}