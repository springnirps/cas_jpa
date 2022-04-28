package org.apereo.cas.web.flow;

import com.newco.entity.UserAttr;
import com.newco.service.UserAttrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Slf4j
@RequiredArgsConstructor
public class UpdateUserAction extends AbstractAction {

    @Autowired
    UserAttrService userAttrService;

    @Override
    protected Event doExecute(final RequestContext context) {
        LOGGER.debug("Inside UpdateUserAction doExecute");

        UserAttr userAttr = userAttrService.getUser(1L);
        return userAttr != null ? super.success() : super.error();

    }
}
