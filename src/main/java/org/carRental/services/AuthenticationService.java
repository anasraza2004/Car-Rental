package org.carRental.services;

import org.carRental.dao.UserDAO;
import org.carRental.domain.User;

public class AuthenticationService {
    private final UserDAO userDAO = new UserDAO();

    public Boolean checkResult(String username, String passcode) {
        User user = userDAO.getUserByUserNameAndPassword(username, passcode);
        if (user != null) {
            return true;
        }
        return false;
    }
}
