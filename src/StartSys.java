import servlet.MainServlet;

public class StartSys {
	//电商系统入口
	public static void main(String[] args) {
		MainServlet shoppingServlet = new MainServlet();
		shoppingServlet.run();
	}
}
