package ribbon.config;

import com.bryan.cloudalibaba.ribbon.NacosFinalRule;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 这个类最好不要出现在启动类的@ComponentScan扫描范围
* 如果出现在@ComponentScan扫描访问，那么这个配置类就是每个服务共用的配置了
* */
@Configuration(proxyBeanMethods = false)
public class RibbonLoadBalanceOrderConfig {

//    @RibbonClientName
    private String name = "order-service";

    @Bean
    @ConditionalOnMissingBean
    public IClientConfig ribbonClientConfig() {
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties(name);
        config.set(CommonClientConfigKey.MaxAutoRetries,0);
        config.set(CommonClientConfigKey.MaxAutoRetriesNextServer,1);
        config.set(CommonClientConfigKey.ConnectTimeout,2000);
        config.set(CommonClientConfigKey.ReadTimeout,6000);
        config.set(CommonClientConfigKey.OkToRetryOnAllOperations,false);
        return config;
    }
    /*
    * 判断服务是否存活
    * 不建议使用
    * */
//    @Bean
    public IPing ribbonPing() {
        //这个实现类会去调用服务来判断服务是否存活
        return new PingUrl();
    }

    @Bean
    @ConditionalOnMissingBean
    public IRule ribbonRule() {
        //随机
        new RandomRule();
        //可以重试的轮训
        new RetryRule();
        //根据运行情况来计算权重
        new WeightedResponseTimeRule();
        //过滤掉故障实例，选择请求数最小的实例
        new BestAvailableRule();
        new RoundRobinRule();
        return new NacosFinalRule();
    }
}
