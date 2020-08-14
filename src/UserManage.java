import servlet.UserServlet;
import view.MainView;

import java.util.Scanner;

public class UserManage {
    public static void main(String[] args) {
        UserServlet userServlet = new UserServlet();
        //用户管理
        String []title={"添加用户","删除用户","修改用户","查找用户","退出程序"};
        MainView mainView = new MainView();
        while (true){
            mainView.show(title);
            System.out.println("请输入编号：");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            if (i==1){
                //添加用户
                userServlet.addUser();
            }else if (i==2){
                //删除用户
                userServlet.removeUser();
            }else if (i==3){
                //修改用户
                userServlet.updateUser();
            }else if (i==4){
                //查找用户
                userServlet.findUser();
            }else if (i==5){
                break;
            }

        }
    }


}
