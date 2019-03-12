package com.hendisantika.dockerworkflow4springbootdeveloper.controller;

import com.hendisantika.dockerworkflow4springbootdeveloper.entity.Product;
import com.hendisantika.dockerworkflow4springbootdeveloper.exception.DataNotFoundException;
import com.hendisantika.dockerworkflow4springbootdeveloper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:42
 */
@RestController
@RequestMapping("/api/product")
@Transactional(readOnly = true)
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Product> findAll(Pageable page) {
        return productRepository.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Product p, UriComponentsBuilder uriBuilder) {
        productRepository.save(p);
        URI location = uriBuilder.path("/api/product/{id}")
                .buildAndExpand(p.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Product p) {
        if (p == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Product p) {
        if (!productRepository.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        p.setId(id);
        productRepository.save(p);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!productRepository.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        productRepository.delete(id);
    }
}