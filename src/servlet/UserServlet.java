package servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.UserDao;
import vo.User;

public class UserServlet {
    private List<User> userList = new ArrayList<>();
    private final static String USER_PATH = "./user";
    private UserDao userdao = new UserDao();
    private Scanner in = new Scanner(System.in);

    public User login() {
        userList = userdao.findAll(USER_PATH);

        System.out.println("\n\t请输入用户名:");
        String name = in.next();
        System.out.println("\n\t请输入密码:");
        String password = in.next();

        for (User user : userList) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void register() {
        // 注册
        in = new Scanner(System.in);

        System.out.println("\n\t请输入用户名:");
        String name = in.next();

        // 判断是否存在该用户名
        for (User user : userList) {
            if (name.equals(user.getName())) {
                System.out.println("用户名已被占用");
                return;
            }
        }
        System.out.println("\n\t请输入密码:");
        String password = in.next();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userList.add(user);
        userdao.save(userList, USER_PATH);
        System.out.println("\n\t开户成功!");


    }
}
