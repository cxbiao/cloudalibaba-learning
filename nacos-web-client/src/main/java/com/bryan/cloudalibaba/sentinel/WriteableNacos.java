package com.bryan.cloudalibaba.sentinel;


import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.Properties;

/**
 * 只能持久化流控规则
 * @param <T>
 */
public class WriteableNacos<T> implements WritableDataSource<T> {


    private Properties properties;

    //当sentinel update 时候
    public WriteableNacos(Properties properties){
        if(properties==null){
            throw new IllegalArgumentException("持久化参数错误");
        }
        this.properties=properties;
    }


    @Override
    public void write(T value) throws Exception {
        System.out.println("-------------write-----------");

        String rule = JSON.toJSONString(value);
        System.out.println(rule);
        String dataId = properties.getProperty(SentinelUtil.DATA_ID);
        String group = properties.getProperty(SentinelUtil.GROUP);
        ConfigService configService = NacosFactory.createConfigService(properties);
        boolean isPublishOk = configService.publishConfig(dataId, group, rule);
        System.out.println(isPublishOk);
    }

    @Override
    public void close() throws Exception {

    }
}
