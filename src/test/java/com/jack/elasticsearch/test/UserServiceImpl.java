package com.jack.elasticsearch.test;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public String getUserName(String userId) {
        return UUID.randomUUID().toString();
    }
}
