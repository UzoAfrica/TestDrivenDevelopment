package org.uzoTestLifeCycleClass.service;

import java.util.Map;

public interface UsersService {
    String createUser(Map userDetails);
    Map updateUser(String userId, Map userDetails);
    Map getUserDetails(String userId);
    void deleteUser(String userId);
}
