package com.fastcampus.fastcampusspringsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // @CreatedDate, @LastModifiedDate 사용
public class JpaConfig {
}
