package com.example.general.service.abst;



import com.example.general.model.Consumer;
import com.example.general.model.DTO.ConsumerCardDTO;
import com.example.general.util.exception.AccessDeniedException;
import com.example.general.util.exception.DuplicateException;
import com.example.general.util.exception.NotFoundException;
import com.example.general.util.exception.UnverifiedException;

import javax.transaction.Transactional;
import java.util.List;

public interface ConsumerService {


    @Transactional
    Consumer register(ConsumerCardDTO consumer) throws DuplicateException;

    @Transactional
    void verify(String g_mail, String code) throws NotFoundException, UnverifiedException;


    @Transactional
    Consumer login(String password, String g_mail) throws NotFoundException, UnverifiedException, AccessDeniedException;


    @Transactional
    List<Consumer> search(Object... item);
}
