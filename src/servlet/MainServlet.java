package servlet;

import java.util.Scanner;

import vo.User;
import dao.ShoppingDao;
import dao.UserDao;
import view.MainView;
public class MainServlet {
	
	private final static String Goods_PATH = "D:/data/goods";
	private String[]title={"��¼","����","�˳�"};
	private Scanner scanner = new Scanner(System.in);
	private MainView mainview=new MainView();
	private UserServlet userServlet=new UserServlet();
	private ShoppingServlet shoppingServlet=new ShoppingServlet();
	public void run() {
		
		while(true) {
			System.out.println("\n\t======��ӭ���������վ======");
			mainview.show(title);
			System.out.println("\n\t������������:");

			int choose = scanner.nextInt();
			switch(choose) {
			case 1:
				User user = userServlet.login();
				if (user!=null) {
					shoppingServlet.start(user);
				}
				break;
			case 2:
				userServlet.register();
				break;
			
			}
			if(choose==3) {break;}
		}
	}
	
}
