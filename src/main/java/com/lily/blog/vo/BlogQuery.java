package com.lily.blog.vo;

import lombok.Data;

/**
 * 功能描述：
 *
 * @ClassName: BlogQuery
 * @Author: Lily.
 * @Date: 2019/8/27 11:38
 * @Version: V1.0
 */
@Data
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
