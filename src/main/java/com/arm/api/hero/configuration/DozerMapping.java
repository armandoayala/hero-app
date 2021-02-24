package com.arm.api.hero.configuration;

import com.arm.api.hero.util.Constant;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DozerMapping {

    @Value("${app.dozer.config-file:-}")
    private String mapperConfig;

    @Bean(Constant.BEAN_ID_DOZER_MAPPER)
    public DozerBeanMapper dozerBean() {
        if (mapperConfig.compareTo("-") == 0) {
            throw new RuntimeException("app.dozer.config-file not configured");
        }

        List<String> mappingFiles = Arrays.asList(
                mapperConfig
        );

        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }

}
