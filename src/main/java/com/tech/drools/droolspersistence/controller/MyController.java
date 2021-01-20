package com.tech.drools.droolspersistence.controller;

import com.tech.drools.droolspersistence.entity.SessioninfoEntity;
import com.tech.drools.droolspersistence.model.Fare;
import com.tech.drools.droolspersistence.model.Order;
import com.tech.drools.droolspersistence.model.Person;
import com.tech.drools.droolspersistence.model.TaxiRide;
import com.tech.drools.droolspersistence.service.ISessionService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.*;

@RestController
public class MyController {

    //    @Autowired
//    private IPersistentKieService iPersistentKieService;
    @Autowired
    private KieSession session;
//    @Autowired
//    private StatefulKnowledgeSession session;
    @Autowired
    private ISessionService iSessionService;


    @GetMapping("/order/{card-type}/{price}")
    public Order order(@PathVariable("card-type") String cardType, @PathVariable int price) {

        Order order = new Order(cardType, price);
//        KieSession kieSession = iPersistentKieService.getPersistentKieSession();
        session.insert(order);
        session.fireAllRules();
        return order;
    }

    @GetMapping("/taxi/{distance}/{is-night-surcharge}")
    public Long getRideFare(@PathVariable Long distance,
                            @PathVariable("is-night-surcharge") Boolean isNightSurcharge){
        Fare rideFare = new Fare();
        session.setGlobal("rideFare", rideFare);
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setDistanceInMile(distance);
        taxiRide.isNightSurcharge(isNightSurcharge);
        session.insert(taxiRide);
        session.fireAllRules();
//        session.dispose();
        System.out.println("!! RIDE FARE !! " + rideFare.getTotalFare());
        return rideFare.getTotalFare();
    }

    @GetMapping("/person/{name}/{to-compare-name}/{gender}")
    public Person person(@PathVariable String name,
                         @PathVariable("to-compare-name") String toCompareName,
                         @PathVariable String gender) throws NamingException, HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {

        UserTransaction ut = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
        ut.begin();

        Person person = new Person();
        person.setName(name);
        person.setAge(22);
        person.setGender(gender);
        person.setToCompareName(toCompareName);

        session.insert(person);
        session.fireAllRules();
        ut.commit();


        System.out.println("KBASE");

        session.getKieBase().getEntryPointIds().forEach(e -> System.out.println(e));
        //        System.out.println();

        return person;
    }

    @GetMapping("/session-data")
    public void getSessionData() {

        SessioninfoEntity sessioninfoEntity = iSessionService.getStoredSessionDetails();

        System.out.println(sessioninfoEntity.getId());
    }


}