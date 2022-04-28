package org.apereo.cas.web.flow;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.configurer.AbstractCasWebflowConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

@Slf4j
public class UpdateUserConfigurer extends AbstractCasWebflowConfigurer {
    @Autowired
    UpdateUserAction updateUserAction;

    public UpdateUserConfigurer(final FlowBuilderServices flowBuilderServices,
                                 final FlowDefinitionRegistry flowDefinitionRegistry,
                                 final ConfigurableApplicationContext applicationContext,
                                 final CasConfigurationProperties casProperties) {

        super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
    }

    @Override
    protected void doInitialize() {
        LOGGER.debug("Inside UpdateUserConfigurer doInit");

        val flow = getLoginFlow();
        if (flow != null) {
            val newActionState = createActionState(flow, "UpdateUserActionCheck", updateUserAction);

            createTransitionForState(newActionState, CasWebflowConstants.STATE_ID_SUCCESS, CasWebflowConstants.STATE_ID_CREATE_TICKET_GRANTING_TICKET);
            createTransitionForState(newActionState, CasWebflowConstants.TRANSITION_ID_ERROR, CasWebflowConstants.STATE_ID_CREATE_TICKET_GRANTING_TICKET);

        }

    }
}
