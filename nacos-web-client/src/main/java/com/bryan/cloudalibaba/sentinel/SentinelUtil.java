package com.bryan.cloudalibaba.sentinel;

import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Component
public class SentinelUtil {

//    PropertyKeyConst.SERVER_ADDR
//    PropertyKeyConst.NAMESPACE
//    PropertyKeyConst.CLUSTER_NAME
    public static final String DATA_ID = "dataId";
    public static final String GROUP = "group";


    @Value("${spring.cloud.nacos.config.server-addr}")
    private String server_addr;

    @PostConstruct
    public void init() throws NacosException {
        System.out.println("-------------init-----------");
        String dataId = "sentinel";
        String group1 = "flow";
        String group2 = "degrade";
        String group3 = "paramFlow";
        //namespace
//        final String rule = "[\n"
//                + "  {\n"
//                + "    \"resource\": \"/login/loginByUsername\",\n"
//                + "    \"controlBehavior\": 0,\n"
//                + "    \"count\": 5.0,\n"
//                + "    \"grade\": 1,\n"
//                + "    \"limitApp\": \"default\",\n"
//                + "    \"strategy\": 0\n"
//                + "  }\n"
//                + "]";
//
//        ConfigService configService = NacosFactory.createConfigService(serverAddr);
//        boolean isPublishOk = configService.publishConfig(dataId, group, rule);
//        System.out.println(isPublishOk);


        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(server_addr, group1, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(server_addr, group2, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                }));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());

        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new NacosDataSource<>(server_addr, group3, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                }));
        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());


        //System.out.println(flowRuleDataSource.getProperty());
        Properties propertiesFlow=new Properties();
        propertiesFlow.put(PropertyKeyConst.SERVER_ADDR,server_addr);
        propertiesFlow.put(DATA_ID,dataId);
        propertiesFlow.put(GROUP,group1);


        Properties propertiesDegrade=new Properties();
        propertiesDegrade.put(PropertyKeyConst.SERVER_ADDR,server_addr);
        propertiesDegrade.put(DATA_ID,dataId);
        propertiesDegrade.put(GROUP,group2);

        Properties propertiesParamFlow=new Properties();
        propertiesParamFlow.put(PropertyKeyConst.SERVER_ADDR,server_addr);
        propertiesParamFlow.put(DATA_ID,dataId);
        propertiesParamFlow.put(GROUP,group3);


        WritableDataSource<List<FlowRule>> writableFlowDataSource = new WriteableNacos<>(propertiesFlow);
        WritableDataSourceRegistry.registerFlowDataSource(writableFlowDataSource);


        WritableDataSource<List<DegradeRule>> writableDegradeDataSource = new WriteableNacos<>(propertiesDegrade);
        WritableDataSourceRegistry.registerDegradeDataSource(writableDegradeDataSource);


        WritableDataSource<List<ParamFlowRule>> writableParamFlowDataSource = new WriteableNacos<>(propertiesParamFlow);
        ModifyParamFlowRulesCommandHandler.setWritableDataSource(writableParamFlowDataSource);

//        WritableDataSourceRegistry.registerAuthorityDataSource(writableDataSource);
//        WritableDataSourceRegistry.registerSystemDataSource(writableDataSource);
//        WritableDataSourceRegistry.registerDegradeDataSource(writableDataSource);

    }
}
