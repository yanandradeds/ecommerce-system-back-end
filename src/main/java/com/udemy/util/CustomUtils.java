package com.udemy.util;

import com.udemy.model.User;
import com.udemy.value.object.UserDTO;

public class CustomUtils {

    public static UserDTO mapping(User user){
        return new UserDTO(user.getId(),user.getUsername(), user.getPassword());
    }
}
