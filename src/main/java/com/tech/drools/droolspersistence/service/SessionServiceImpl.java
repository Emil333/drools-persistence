package com.tech.drools.droolspersistence.service;

import com.tech.drools.droolspersistence.entity.SessioninfoEntity;
import com.tech.drools.droolspersistence.repository.DroolsSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class SessionServiceImpl implements ISessionService {

    @Lazy
    @Autowired
    private DroolsSessionRepository droolsSessionRepository;

    @Override
    public SessioninfoEntity getStoredSessionDetails() {

        List<SessioninfoEntity> sessionRepositoryAll = droolsSessionRepository.findAll();
        return sessionRepositoryAll.get(0);
    }


}
