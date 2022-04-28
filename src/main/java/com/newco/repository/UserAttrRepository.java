package com.newco.repository;

import com.newco.entity.UserAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttrRepository  extends JpaRepository<UserAttr, Long> {

}
