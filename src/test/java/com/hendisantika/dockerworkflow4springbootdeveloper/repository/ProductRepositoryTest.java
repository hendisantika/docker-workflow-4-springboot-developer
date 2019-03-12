package com.hendisantika.dockerworkflow4springbootdeveloper.repository;

import com.hendisantika.dockerworkflow4springbootdeveloper.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:55
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-product.sql"})
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {
        Product p = new Product();
        p.setCode("T-001");
        p.setName("Test Product 001");
        p.setPrice(new BigDecimal("100000.01"));

        Assert.assertNull(p.getId());
        productRepository.save(p);
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testFindById() {
        Product p = productRepository.findOne("abc123");
        Assert.assertNotNull(p);
        Assert.assertEquals("P-001", p.getCode());
        Assert.assertEquals("Product 001", p.getName());
        Assert.assertEquals(BigDecimal.valueOf(101000.01), p.getPrice());

        Assert.assertNull(productRepository.findOne("notexist"));
    }
}