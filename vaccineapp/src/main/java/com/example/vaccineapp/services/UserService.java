package com.example.vaccineapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.vaccineapp.repositories.UserRepository;
import com.example.vaccineapp.models.User;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm người dùng trong cơ sở dữ liệu
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        // Trả về đối tượng UserDetails cho Spring Security
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(!user.isEnabled())
                .authorities("ROLE_" + user.getRole())   
                .build();
    }
    
    public void disableUserAccount(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        user.setEnabled(false);
        userRepository.save(user);
        System.out.print(id);
    }

    public void enableUserAccount(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> findUsersByUsername(String username) {
        return userRepository.findByUsernameContaining(username); // Sử dụng query để tìm kiếm
    }
    
    public void createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword); 
        user.setRole(role);
        user.setEnabled(false); 
        userRepository.save(user);
    }
}
