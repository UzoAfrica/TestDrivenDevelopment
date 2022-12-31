package org.uzoTestLifeCycleClass.io;

import java.util.HashMap;
import java.util.Map;

public class UsersDatabaseMapImpl implements UsersDatabase {
    Map<String, Map> users;

    @Override
    public void init(){users = new HashMap<>(); }

    public void close() {users= null;}

    public Map save (String userId, Map userDetails){ return users.put(userId, userDetails); }

    public Map update(String userId, Map user) {
        user.put(userId, user);
        return users.get(userId);
    }
    @Override
    public Map find(String userId) {
        return users.get(userId);
    }

    @Override
    public void delete(String userId) {
        users.remove(userId);
    }
}
