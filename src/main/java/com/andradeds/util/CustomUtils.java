package com.andradeds.util;

import com.andradeds.model.User;
import com.andradeds.value.object.UserDTO;

public class CustomUtils {

    public static UserDTO mapping(User user){
        return new UserDTO(user.getId(),user.getUsername(), user.getPassword());
    }
}
