package com.bryan.cloudalibaba;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义谓词工厂
 * 类名一定要以RoutePredicateFactory结束
 * 前缀和yml的配置对应
 */
@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenRoutePredicateFactory.Config> {


    public TimeBetweenRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange->{
            LocalTime now = LocalTime.now();
            return now.isAfter(config.start) && now.isBefore(config.end);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("start","end");
    }

    @Data
    public static class  Config {
        @NotNull
        private LocalTime start;
        @NotNull
        private LocalTime end;
    }


    /**
     * 默认时间格式：org.springframework.format.support.DefaultFormattingConversionService#addDefaultFormatters
     * 时间格式注册：org.springframework.format.datetime.standard.DateTimeFormatterRegistrar#registerFormatters
     *
     *
     * 默认调用  DateTimeFormatterRegistrar
     * private DateTimeFormatter getFallbackFormatter(Type type) {
     * 		switch (type) {
     * 			case DATE: return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
     * 			case TIME: return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
     * 			default: return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
     *                }* 	}
     * @param args
     */
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(formatter.format(LocalTime.now()));
    }
}
