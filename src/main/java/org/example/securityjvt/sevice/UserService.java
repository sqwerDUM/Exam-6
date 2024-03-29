package org.example.securityjvt.sevice;


import org.example.securityjvt.entity.Role;
import org.example.securityjvt.entity.User;
import org.example.securityjvt.model.UserRegistrationModel;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    String delete(Long id);
    void blockUser(String username);
    void unblockUser(String username);
    Role saveRole(Role role);
    User getById (Long id);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
