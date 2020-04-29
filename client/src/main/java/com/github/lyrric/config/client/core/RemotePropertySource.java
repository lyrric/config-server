package com.github.lyrric.config.client.core;

import com.github.lyrric.config.client.model.RemoteProperty;
import org.springframework.core.env.EnumerablePropertySource;

/**
 * Created on 2020-04-28.
 *
 * @author wangxiaodong
 */
public class RemotePropertySource extends EnumerablePropertySource<RemoteProperty> {

    public RemotePropertySource(String name, RemoteProperty source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }

    @Override
    public Object getProperty(String name) {
        return source.getProperty(name);
    }
}
