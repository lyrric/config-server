package com.github.lyrric.common.model.req;

import java.util.List;

/**
 * Created on 2020-04-28.
 *
 * @author wangxiaodong
 */
public class ReqConfigParam {

    private String groupId;

    private List<String> dataIds;

    public ReqConfigParam() {
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<String> dataIds) {
        this.dataIds = dataIds;
    }
}
