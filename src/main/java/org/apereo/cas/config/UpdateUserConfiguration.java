package org.apereo.cas.config;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.CasWebflowExecutionPlan;
import org.apereo.cas.web.flow.CasWebflowExecutionPlanConfigurer;
import org.apereo.cas.web.flow.UpdateUserAction;
import org.apereo.cas.web.flow.UpdateUserConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

@Configuration("updateUserConfiguration")
@Slf4j
public class UpdateUserConfiguration implements CasWebflowExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowDefinitionRegistry;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private FlowBuilderServices flowBuilderServices;

    @ConditionalOnMissingBean(name = "updateUserConfigurer")
    @Bean
    public CasWebflowConfigurer updateUserConfigurer() {
        val cfg = new UpdateUserConfigurer(flowBuilderServices, loginFlowDefinitionRegistry,
                applicationContext, casProperties);
        return cfg;
    }

    @Override
    public void configureWebflowExecutionPlan(final CasWebflowExecutionPlan plan) {
        plan.registerWebflowConfigurer(updateUserConfigurer());
    }

    @ConditionalOnMissingBean(name = "updateUserAction")
    @Bean
    public UpdateUserAction updateUserAction() {
        return new UpdateUserAction();
    }
}
