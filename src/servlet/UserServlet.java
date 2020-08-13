package servlet;

import java.util.Scanner;

import dao.UserDao;
import vo.User;

public class UserServlet {
	private final static String USER_PATH = "D:/data/user";
	private UserDao userdao=new UserDao();
	public User login() {
		Scanner input = new Scanner(System.in);

		System.out.println("\n\t�������û���:");
		String name = input.next();
		System.out.println("\n\t����������:");
		String password = input.next();
		User user = userdao.findByNamePassword(name, password, USER_PATH);
		if (user != null) {
			System.out.println("\n\t��¼�ɹ�!");
			return user;
		}
		return new User();
	}

	public void register() {
		// ע��
		Scanner input = new Scanner(System.in);

		System.out.println("\n\t�������û���:");
		String name = input.next();
		System.out.println("\n\t����������:");
		String password = input.next();
		// �ж��Ƿ���ڸ��û���

		if (!userdao.findName(name, USER_PATH)) {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setId(System.currentTimeMillis());
			userdao.save(user, USER_PATH + "/" + user.getName() + user.getId());
			System.out.println("\n\t�����ɹ�!");

		} else {
			System.out.println("\n\t���û����Ѿ���ռ��.");
		}

	}
}
