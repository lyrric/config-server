package com.github.lyrric.config.client.model;

import lombok.Data;

import java.util.Date;

@Data
public class Config {

    private Integer id;
    private String groupId;

    private String dataId;

    private String content;

    /**
     * 密匙
     */
    private String appKey;
    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;
}