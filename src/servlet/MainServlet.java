package servlet;

import java.util.Scanner;

import vo.User;
import dao.ShoppingDao;
import dao.UserDao;
import view.MainView;
public class MainServlet {
	
	private final static String Goods_PATH = "D:/data/goods";
	private String[]title={"登录","开户","退出"};
	private Scanner scanner = new Scanner(System.in);
	private MainView mainview=new MainView();
	private UserServlet userServlet=new UserServlet();
	private ShoppingServlet shoppingServlet=new ShoppingServlet();
	public void run() {
		
		while(true) {
			System.out.println("\n\t======欢迎进入电商网站======");
			mainview.show(title);
			System.out.println("\n\t请输入操作序号:");

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
