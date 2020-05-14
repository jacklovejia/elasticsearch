package com.jack.elasticsearch.test;

public interface UserService {
    @MyAnnotation(MyAnnotation.JACK)
    String getUserName(String userId);
}
