package com.hendisantika.dockerworkflow4springbootdeveloper.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : docker-workflow-4-springboot-developer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-03-11
 * Time: 03:31
 */

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    @Column(nullable = false, unique = true)
    private String code;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal price;

}
