package org.example.Users;

import org.example.Plans.Plans;
import org.example.Plans.PlansRepository;

public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }
    public void createUserTable(){
        UsersRepository.createUserTable();
    }

    public void saveUser(Users users){
        usersRepository.saveUser(users);
    }
}
