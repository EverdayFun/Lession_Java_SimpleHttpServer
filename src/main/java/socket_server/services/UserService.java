package socket_server.services;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static List<String> users = new ArrayList<String>();

    public static List<String> listUsers() {
        return users;
    }

    public static void addUser(String name) {
        users.add(name);
    }

}