package com.lily.blog.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：标签实体类
 *
 * @ClassName: Tag
 * @Author: Lily.
 * @Date: 2019/8/25 20:37
 * @Version: V1.0
 */
@Data
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签名称不能为空!")
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
