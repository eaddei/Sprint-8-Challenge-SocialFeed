package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        // Validate username begins with an uppercase letter, is at least 4 characters long,
        // and only contains letters, numbers, and underscores
        String usernamePattern = "^[A-Z][A-Za-z0-9_]{3,}$";
        return username.matches(usernamePattern);
    }

    private boolean isValidPassword(String password) {
        // Validate password contains at least 8 characters, at least one uppercase letter,
        // one lowercase letter, one digit, and valid symbols include: !@#$%^&*
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{8,}$";
        return password.matches(passwordPattern);
    }

    @Override
    public void validate(Object userData) {
        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
    }
}
