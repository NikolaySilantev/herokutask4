package com.nikolay.task4.service;

import com.nikolay.task4.model.Role;
import com.nikolay.task4.model.User;
import com.nikolay.task4.repo.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void allUsersTest() {
        List<User> users = getUsers();

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.allUsers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void saveUserTest() {
        User user = new User();

        boolean isUserCreated = userService.saveUser(user);

        Assertions.assertTrue(isUserCreated);
        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void saveUserFailTest() {
        User user = new User();

        user.setUsername("Mike");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("Mike");

        boolean isUserCreated = userService.saveUser(user);

        Assertions.assertFalse(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

    private List<User> getUsers() {
        User firstUser = new User();
        User secondUser = new User();

        firstUser.setId(1L);
        firstUser.setEmail("firstUser@mail.ru");
        firstUser.setPassword("$2a$10$iTcUMtxYjiXN.HJKeX.qBecAFdoZB/bJEWTZeOoPuTTJYhpqFAQXa");
        firstUser.setUsername("firstUser");

        secondUser.setId(2L);
        secondUser.setEmail("secondUser@mail.ru");
        secondUser.setPassword("$2a$10$iTcUMtxYjiXN.HJKeX.qBecAFdoZB/bJEWTZeOoPuTTJYhpqFAQXa");
        secondUser.setUsername("secondUser");

        return List.of(firstUser, secondUser);
    }
}
