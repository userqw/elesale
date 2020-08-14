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
        System.out.println("\n\t�������û���:");
        String name = in.next();
        System.out.println("\n\t����������:");
        String password = in.next();

        for (User user : this.userList) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void register() {
        // ע��
        in = new Scanner(System.in);

        System.out.println("\n\t�������û���:");
        String name = in.next();

        // �ж��Ƿ���ڸ��û���
        for (User user : userList) {
            if (name.equals(user.getName())) {
                System.out.println("�û����ѱ�ռ��");
                return;
            }
        }
        System.out.println("\n\t����������:");
        String password = in.next();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userList.add(user);
        userdao.save(userList, USER_PATH);
        System.out.println("\n\t�����ɹ�!");


    }

    public void addUser() {
        System.out.println("\n\t�������û���:");
        String name = in.next();
        System.out.println("\n\t����������:");
        String password = in.next();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userList.add(user);
        userdao.save(userList,USER_PATH);
        System.out.println("��ӳɹ�");
    }

    public void removeUser() {
       showAllUser();
        System.out.println("��������Ҫɾ�����û����:");
        int i = in.nextInt();
        if (i>0&&i<=userList.size()){
            userList.remove(i-1);
        }
        userdao.save(userList,USER_PATH);
        System.out.println("ɾ���ɹ�");
    }
    public void showAllUser(){
        userList=userdao.findAll(USER_PATH);
        System.out.println("\t\t���\t�û���\t����");
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("\t\t%d\t%s\t%s",i+1,userList.get(i).getName(),userList.get(i).getPassword()+"\n");
        }
    }

    public void updateUser() {
        showAllUser();
        System.out.println("��������Ҫ�޸ĵ��û���ţ�");
        int i = in.nextInt();
        if (i>0&&i<=userList.size()){
            User user = userList.get(i - 1);
            System.out.println("�������µ�����");
            String name = in.next();
            for (User user1 : userList) {
                if (name.equals(user1.getName())){
                    System.out.println("�����ѱ�ռ��");
                    return;
                }
            }
            System.out.println("���������룺");
            String password=in.next();
            user.setName(name);
            user.setPassword(password);
            userdao.save(userList,USER_PATH);
            System.out.println("�޸ĳɹ�");
        }
        userdao.save(userList,USER_PATH);
    }

    public void findUser() {
        System.out.println("��������Ҫ�����û���������");
        String name = in.next();
        boolean find=false;
        for (User user : userList) {
            if (name.equals(user.getName())){
                System.out.println("�ҵ���");
                System.out.printf("\t\t%s\t%s",user.getName(),user.getPassword()+"\n");
                find=true;
            }
        }
        if (!find){
            System.out.println("û�ҵ�");
        }
    }
}
