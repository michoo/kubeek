package com.kubeek.service.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfiguration {

    @Value("${kubeek.threadpool.corepoolsize:5}")
    private int corepoolsize;

    @Value("${kubeek.threadpool.maxpoolsize:20}")
    private int maxpoolsize;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(corepoolsize);
        pool.setMaxPoolSize(maxpoolsize);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

}
