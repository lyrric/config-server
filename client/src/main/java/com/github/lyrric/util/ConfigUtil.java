package com.github.lyrric.util;

import com.github.lyrric.model.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2019/3/18.
 *
 * @author wangxiaodong
 */
public class ConfigUtil {


    /**
     * 拼接配置内容
     * @return
     */
    public static String parseConfigContent(List<Config> configList){
        List<String> contents = configList.stream().map(Config::getContent).collect(Collectors.toList());
        return StringUtils.join(contents, System.getProperty("line.separator"));
    }


}
