package org.arcsoft;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arcsoft.domain.Department;
import org.arcsoft.domain.User;
import org.arcsoft.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User createUser(String username, Department department) {
        return repository.save(new User(username, department));
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public boolean updateUser(Long id, String newUsername) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        user.setUsername(newUsername);
        return true;
    }

    public boolean deleteUser(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
