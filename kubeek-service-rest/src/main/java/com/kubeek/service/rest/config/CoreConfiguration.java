package com.kubeek.service.rest.config;

import com.kubeek.app.AppManager;
import com.kubeek.app.AppStrategySimple;
import com.kubeek.message.MessageConsumer;
import com.kubeek.message.MessageManager;
import com.kubeek.message.ScreenCommand;
import com.kubeek.message.factory.MailMessageFactory;
import com.kubeek.message.factory.ScreenMessageFactorySimple;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.app.KAppStrategy;
import com.kubeek.sdk.command.KScreenCommand;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KMailMessageFactory;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CoreConfiguration {

    @Value("${kubeek.core.app.strategy:simple}")
    private String coreAppStrategy;

    @Bean
    public KScreenCommand getKScreenCommand(){
        return new ScreenCommand();
    }

    @Bean
    public KAppManager getKAppManager(){
        return new AppManager();
    }

    @Bean
    public KMessageManager getKMessageManager(){
        return new MessageManager();
    }

    @Bean
    public KAppStrategy getKAppStrategy(){

        KAppStrategy kAppStrategy = null;

        if(coreAppStrategy.equals("simple")){
            kAppStrategy = new AppStrategySimple();
        }

        return kAppStrategy;
    }

    @Bean
    public KScreenMessageFactory getKScreenMessageFactory(){
        return new ScreenMessageFactorySimple();
    }


    @Bean
    public MessageConsumer getMessageConsumer(){
        return new MessageConsumer();
    }
}
