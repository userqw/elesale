package servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.UserDao;
import vo.User;

public class UserServlet {
    private UserDao userdao = new UserDao();
    private final static String USER_PATH = "./user";
    private List<User> userList =new ArrayList<>() ;
    private Scanner in = new Scanner(System.in);

    public User login() {
        userList = userdao.findAll(USER_PATH);
        System.out.println("\n\t请输入用户名:");
        String name = in.next();
        System.out.println("\n\t请输入密码:");
        String password = in.next();

        for (User user : this.userList) {
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

    public void addUser() {
        System.out.println("\n\t请输入用户名:");
        String name = in.next();
        System.out.println("\n\t请输入密码:");
        String password = in.next();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userList.add(user);
        userdao.save(userList,USER_PATH);
        System.out.println("添加成功");
    }

    public void removeUser() {
       showAllUser();
        System.out.println("请输入所要删除的用户编号:");
        int i = in.nextInt();
        if (i>0&&i<=userList.size()){
            userList.remove(i-1);
        }
        userdao.save(userList,USER_PATH);
        System.out.println("删除成功");
    }
    public void showAllUser(){
        userList=userdao.findAll(USER_PATH);
        System.out.println("\t\t编号\t用户名\t密码");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("\t\t%d\t%s\t%s",i+1,userList.get(i).getName(),userList.get(i).getPassword()+"\n");
        }
    }

    public void updateUser() {
        showAllUser();
        System.out.println("请输入想要修改的用户编号：");
        int i = in.nextInt();
        if (i>0&&i<=userList.size()){
            User user = userList.get(i - 1);
            System.out.println("请输入新的名称");
            String name = in.next();
            for (User user1 : userList) {
                if (name.equals(user1.getName())){
                    System.out.println("名字已被占用");
                    return;
                }
            }
            System.out.println("请输入密码：");
            String password=in.next();
            user.setName(name);
            user.setPassword(password);
            userdao.save(userList,USER_PATH);
            System.out.println("修改成功");
        }
        userdao.save(userList,USER_PATH);
    }

    public void findUser() {
        System.out.println("请输入想要查找用户的姓名：");
        String name = in.next();
        boolean find=false;
        for (User user : userList) {
            if (name.equals(user.getName())){
                System.out.println("找到了");
                System.out.printf("\t\t%s\t%s",user.getName(),user.getPassword()+"\n");
                find=true;
            }
        }
        if (!find){
            System.out.println("没找到");
        }
    }
}
