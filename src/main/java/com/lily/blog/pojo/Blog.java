package com.lily.blog.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：博客实体类
 *
 * @ClassName: Blog
 * @Author: Lily.
 * @Date: 2019/8/25 20:21
 * @Version: V1.0
 */
@Data
@Entity
@Table(name = "t_blog")
public class Blog {
   /**
    * id 主键,自动增长
    */
   @Id
   @GeneratedValue
    private Long id;
   /**
   * 标题
   */
   private String title;
   /**
   * 内容
   */
   @Basic(fetch = FetchType.LAZY)
   @Lob
   private String content;
   /**
   * 首图
   */
   private String firstPicture;
   /**
   * 标记
   */
   private String flag;
   /**
   * 浏览次数
   */
   private Integer views;
   /**
   * 是否开启赞赏
   */
   private boolean appreciation;
   /**
   * 是否开启转载声明
   */
   private boolean shareStatment;
    /**
    * 是否开启评论
    */
    private boolean commentabled;
    /**
    * 是否发布
    */
    private boolean published;
    /**
    * 是否推荐
    */
    private boolean recommend;
    /**
    * 创建时间
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
    * 修改时间
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
    @Transient
    private String tagIds;

    private String description;

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    public String tagsToIds(List<Tag> tags){
       if(!tags.isEmpty()){
           StringBuffer ids = new StringBuffer();
           boolean fag =false;
           for (Tag tag : tags) {
               if (fag){
                   ids.append(",");
               }else {
                   fag = true;
               }
               ids.append(tag.getId());
           }
           return ids.toString();
       }
       return tagIds;
    }

}
