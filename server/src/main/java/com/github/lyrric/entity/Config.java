package com.github.lyrric.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
public class Config extends BaseEntity {

    /**
     * 密匙
     */
    @Column(name = "app_key")
    private String appKey;
    @Column(name = "group_id")
    private String groupId;

    @Column(name = "data_id")
    private String dataId;

    private String content;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;
}