package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUserById(int id) {
        return userStorage.getUserById(id);
    }

    public User addFriend(int userId, int friendId) {
        User user = userStorage.getUserById(userId);
        Validator.validateUser(user);
        User friend = userStorage.getUserById(friendId);
        user.addFriend(friendId);
        friend.addFriend(userId);
        log.info("Пользователь " + user.getName() + " добавлен в список друзей " + friend.getName());
        return user;
    }

    public User deleteFriend(int userId, int friendId) {
        User user = userStorage.getUserById(userId);
        Validator.validateUser(user);
        User friend = userStorage.getUserById(friendId);
        user.deleteFriend(friendId);
        friend.deleteFriend(userId);
        log.info("Пользователь " + user.getName() + " удален из списка друзей " + friend.getName());
        return user;
    }

    public List<User> getFriends(int userId) {
        User user = userStorage.getUserById(userId);
        Validator.validateUser(user);
        List<User> friendsList = new ArrayList<>();
        for (Integer id : user.getFriends()) {
            friendsList.add(userStorage.getUserById(id));
        }
        log.info("Список друзей пользователя " + user.getName());
        return friendsList;
    }

    public List<User> corporateFriends(int userId, int friendId) {
        User user = userStorage.getUserById(userId);
        Validator.validateUser(user);
        User friend = userStorage.getUserById(friendId);
        List<User> mutualFriends = new ArrayList<>();
        for (Integer id : user.getFriends()) {
            if (friend.getFriends().contains(id)) {
                User mutualFriend = userStorage.getUserById((id));
                mutualFriends.add(mutualFriend);
            }
        }
        log.info("Список общих друзей пользователей");
        return mutualFriends;
    }

}
