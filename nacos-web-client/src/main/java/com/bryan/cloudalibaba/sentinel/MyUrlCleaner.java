package com.bryan.cloudalibaba.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;

//@Component
@Slf4j
public class MyUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        // 让 /shares/1 与 /shares/2 的返回值相同
        // 返回/shares/{number}

        log.info(originUrl);
        String[] split = originUrl.split("/");

        return Arrays.stream(split)
            .map(string -> {
                if (NumberUtils.isNumber(string)) {
                    return "{number}";
                }
                return string;
            })
            .reduce((a, b) -> a + "/" + b)
            .orElse("");
    }
}
