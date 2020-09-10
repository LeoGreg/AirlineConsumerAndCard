package com.example.general.configs;


import com.example.general.model.Consumer;
import com.example.general.model.ISO.ConsumerStatus;
import com.example.general.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@Qualifier("detailImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private ConsumerRepository consumerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException {
        Consumer consumer = consumerRepository.getByUsername(username);
        if (consumer == null) {
            throw new UsernameNotFoundException("user.not.found");
        }
        if (consumer.getStatus() != ConsumerStatus.ACTIVE) {
            throw new LockedException("status.is.unverified");
        }
        return new org.springframework.security.core.userdetails.User(consumer.getUsername(), consumer.getPassword(), consumer.getAuthorities());
    }
}

