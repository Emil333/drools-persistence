package com.tech.drools.droolspersistence.service;

import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public interface IPersistentKieService {

    StatefulKnowledgeSession getPersistentKnowledgeSession();

    KieSession getPersistentKieSession();
}
