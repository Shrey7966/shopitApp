package com.shopit.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.shopit.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
