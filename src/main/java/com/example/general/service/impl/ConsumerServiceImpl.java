package com.example.general.service.impl;


import com.example.general.model.Authority;
import com.example.general.model.Card;
import com.example.general.model.Consumer;
import com.example.general.model.DTO.ConsumerCardDTO;
import com.example.general.model.ISO.ConsumerStatus;
import com.example.general.repository.AuthorityRepository;
import com.example.general.repository.CardRepository;
import com.example.general.repository.ConsumerRepository;
import com.example.general.service.abst.ConsumerService;
import com.example.general.util.component.MailSenderClient;
import com.example.general.util.exception.AccessDeniedException;
import com.example.general.util.exception.DuplicateException;
import com.example.general.util.exception.NotFoundException;
import com.example.general.util.exception.UnverifiedException;
import com.example.general.util.gen.Generator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

@Log4j2
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MailSenderClient mailSenderClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public Consumer register(ConsumerCardDTO consumer) throws DuplicateException {
        Consumer duplicate = consumerRepository.getByUsername(consumer.getUsername());
        DuplicateException.check(duplicate != null, "duplicate.client");
        DuplicateException.check(cardRepository.getByNumber(consumer.getNumber()) != null, "dup.card");

        Consumer dbConsumer = new Consumer();
        Card card = new Card();
        card.setCvv(consumer.getCvv());
        card.setNumber(consumer.getNumber());
        card.setBalance(consumer.getBalance());
        dbConsumer.setCard(card);

        dbConsumer.setFirst_name(consumer.getFirst_name());
        dbConsumer.setLast_name(consumer.getLast_name());
        dbConsumer.setUsername(consumer.getUsername());

        Authority authority = authorityRepository.getById(1);
        dbConsumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        dbConsumer.setCode(Generator.getRandomDigits(5));
        dbConsumer.setStatus(ConsumerStatus.INACTIVE);
        dbConsumer.setAuthorities(Collections.singletonList(authority));

        Consumer consumer1 = consumerRepository.save(dbConsumer);
        card.setConsumer_id(consumer1.getId());
        cardRepository.save(card);
/*
        mailSenderClient.sendSimpleMessage(consumer1.getFirst_name() + consumer1.getLast_name(), "code is sent", consumer1.getCode());
*/
        return consumer1;
    }

    @Override
    @Transactional
    public void verify(String username, String code) throws NotFoundException, UnverifiedException {
        Consumer consumer = consumerRepository.getByUsername(username);
        NotFoundException.check(consumer == null, "user.is.not.preset");
        UnverifiedException.check(!consumer.getCode().equals(code), "code.is.wrong");
        consumer.setStatus(ConsumerStatus.ACTIVE);
        consumerRepository.save(consumer);
    }

    @Override
    @Transactional
    public Consumer login(String password, String username) throws NotFoundException, UnverifiedException, AccessDeniedException {
        Consumer consumer = consumerRepository.getByUsername(username);
        NotFoundException.check(consumer == null, "no.consumer");
        UnverifiedException.check(consumer.getStatus() == ConsumerStatus.INACTIVE, "unverified.status");
        AccessDeniedException.check(!consumer.getPassword().equals(password), "wrong.password");
        return consumer;
    }


    @Override
    @Transactional
    public List<Consumer> search(Object... item) {
        String[] keys = {"first_name", "last_name", "g_mail"};
        HashMap<String, Object> map = new HashMap();
        for (int i = 0; i < item.length; i++) {
            map.put(keys[i], item[i]);
        }
        for (Iterator<?> it = map.values().iterator();
             it.hasNext(); ) {
            if (it.next() == null) {
                it.remove();
            }
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consumer> criteriaQuery = criteriaBuilder.createQuery(Consumer.class);
        Root<Consumer> movieRoot = criteriaQuery.from(Consumer.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            log.info("size {}", map.size());
            criteriaQuery.where(criteriaBuilder.like(movieRoot.get(entry.getKey()), (String) entry.getValue()));
        }
        TypedQuery<Consumer> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }


}
