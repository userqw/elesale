import servlet.UserServlet;
import view.MainView;

import java.util.Scanner;

public class UserManage {
    public static void main(String[] args) {
        UserServlet userServlet = new UserServlet();
        //�û�����
        String []title={"����û�","ɾ���û�","�޸��û�","�����û�","�˳�����"};
        MainView mainView = new MainView();
        while (true){
            mainView.show(title);
            System.out.println("�������ţ�");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            if (i==1){
                //����û�
                userServlet.addUser();
            }else if (i==2){
                //ɾ���û�
                userServlet.removeUser();
            }else if (i==3){
                //�޸��û�
                userServlet.updateUser();
            }else if (i==4){
                //�����û�
                userServlet.findUser();
            }else if (i==5){
                break;
            }

        }
    }


}
