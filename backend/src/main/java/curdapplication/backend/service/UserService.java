package curdapplication.backend.service;

import curdapplication.backend.entity.User;
import curdapplication.backend.exception.UserNotFoundException;
import curdapplication.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User oldUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User is not existed with the given ID"));
        if (oldUser != null) {
            oldUser.setId(user.getId());
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setAddress(user.getAddress());
            oldUser.setPhone(user.getPhone());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User is not existed with the given ID");
        }
        return user;
    }

    public String deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        if (user != null) {
            userRepository.deleteById(id);
        }
        return "User deleted";
    }
}
