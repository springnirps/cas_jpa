package com.newco.service;

import com.newco.entity.UserAttr;
import com.newco.repository.UserAttrRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
@Transactional(readOnly = true)
public class UserAttrService {

    @Autowired
    UserAttrRepository repository;

    public UserAttr getUser(long uid) {
        return repository.getById(uid);
    }
}
