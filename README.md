# Spring Cloud Alibaba Learning

java -javaagent:/path/to/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=application_name -jar yourApp.jar


对于gateway,skywalking7只支持2.1.13
官方测试说到2.1.2.RELEASE

- java -javaagent:F:\weiyun\springcloudAlibaba\cloudalibaba-learning\agent\skywalking-agent.jar -Dskywalking.agent.service_name=gateway
- java -javaagent:F:\weiyun\springcloudAlibaba\cloudalibaba-learning\agent\skywalking-agent.jar -Dskywalking.agent.service_name=web
- java -javaagent:F:\weiyun\springcloudAlibaba\cloudalibaba-learning\agent\skywalking-agent.jar -Dskywalking.agent.service_name=order8901
- java -javaagent:F:\weiyun\springcloudAlibaba\cloudalibaba-learning\agent\skywalking-agent.jar -Dskywalking.agent.service_name=order8902