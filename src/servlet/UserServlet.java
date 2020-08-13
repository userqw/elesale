package servlet;

import java.util.Scanner;

import dao.UserDao;
import vo.User;

public class UserServlet {
	private final static String USER_PATH = "D:/data/user";
	private UserDao userdao=new UserDao();
	public User login() {
		Scanner input = new Scanner(System.in);

		System.out.println("\n\t请输入用户名:");
		String name = input.next();
		System.out.println("\n\t请输入密码:");
		String password = input.next();
		User user = userdao.findByNamePassword(name, password, USER_PATH);
		if (user != null) {
			System.out.println("\n\t登录成功!");
			return user;
		}
		return new User();
	}

	public void register() {
		// 注册
		Scanner input = new Scanner(System.in);

		System.out.println("\n\t请输入用户名:");
		String name = input.next();
		System.out.println("\n\t请输入密码:");
		String password = input.next();
		// 判断是否存在该用户名

		if (!userdao.findName(name, USER_PATH)) {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setId(System.currentTimeMillis());
			userdao.save(user, USER_PATH + "/" + user.getName() + user.getId());
			System.out.println("\n\t开户成功!");

		} else {
			System.out.println("\n\t该用户名已经被占用.");
		}

	}
}
