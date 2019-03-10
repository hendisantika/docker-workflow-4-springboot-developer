package com.hendisantika.dockerworkflow4springbootdeveloper.repository;

import com.hendisantika.dockerworkflow4springbootdeveloper.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:33
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
}
