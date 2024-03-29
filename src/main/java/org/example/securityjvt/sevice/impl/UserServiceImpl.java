package org.example.securityjvt.sevice.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.securityjvt.entity.Role;
import org.example.securityjvt.entity.User;
import org.example.securityjvt.repo.RoleRepo;
import org.example.securityjvt.repo.UserRepo;
import org.example.securityjvt.sevice.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public String delete(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return "Deleted";
    }
    @Override
    public void blockUser(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setEnabled(false);
            userRepo.save(user);
        }
    }
 @Override
    public void unblockUser(String username) {
     User user = userRepo.findByUsername(username);
     if (user != null) {
         user.setEnabled(true);
         userRepo.save(user);
     }
 }
    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public User getById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}