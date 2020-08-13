package servlet;

import java.util.Scanner;

import dao.ShoppingDao;
import view.MainView;
import vo.Goods;
import vo.User;

public class ShoppingServlet {
	private final static  String Goods_PATH = "D:/data/goods";
	String[] title = { "ѡ����Ʒ", "�鿴���ﳵ", "����", "����" };
	Scanner scanner = new Scanner(System.in);
	String[] shopGoods = { "����", "����", "���", "�з�" };
	double[] shopGoodsPrices = { 2, 5, 4, 5.5 };
	private Scanner in = new Scanner(System.in);
	private MainView mainview = new MainView();
	private ShoppingDao shoppingdao=new ShoppingDao();
	private int count=0;
	public void start(User user) {
		
		while (true) {
			mainview.show(title);
			System.out.println("\n\t������������:");
			int i = in.nextInt();
			if (i == 1) {
				// ѡ����Ʒ
				buy(user);

			} else if (i == 2) {
				// �鿴���ﳵ
				 showShoppingCar(user);
			} else if (i == 3) {
				// ����
			} else if (i == 4) {
				// ����
				break;
			}
		}
	}

	private void showShoppingCar(User user) {
		Goods goods = shoppingdao.showAll(Goods_PATH+"/"+user.getId());
		System.out.println("\n\t\t"+goods.getName()+" "+goods.getPrice()+" "+goods.getCount());
		
	}

	private void buy(User user) {
		
		while (true) {
			showGoods();
			System.out.println("\t\t0.����");
			System.out.println("\n\t������Ҫ�������Ʒ����:");
			int i = in.nextInt();
			if (i == 0) {
				break;
			}
			Goods good = shoppingdao.findName(shopGoods[i-1],Goods_PATH);
			if(good!=null) {
				Goods goods = new Goods();
				goods.setUserId(user.getId());
				goods.setCount(1);
				goods.setName(shopGoods[i-1]);
				goods.setPrice(shopGoodsPrices[i-1]);
				shoppingdao.save(goods,Goods_PATH+"/"+user.getId());
			}else {
				good.setCount(good.getCount()+1);
				shoppingdao.save(good,Goods_PATH+"/"+user.getId());
			}
			
			System.out.println("��ӹ��ﳵ�ɹ�");
				
		}
	}

	private void showGoods() {
		System.out.println("\n\t\t======��ӭѡ��======");
		System.out.println("\t\t��� ���� ����");
		for (int i = 0; i < shopGoods.length; i++) {
			System.out.printf("\t\t%d %s %.1f\n", i + 1, shopGoods[i], shopGoodsPrices[i]);
		}
	}

}
