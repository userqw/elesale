package servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ShoppingDao;
import javafx.beans.binding.DoubleExpression;
import view.MainView;
import vo.Goods;
import vo.User;

public class ShoppingServlet {
    private final static String Goods_PATH = "./goods";
    String[] title = {"ѡ����Ʒ", "�鿴���ﳵ", "����", "����"};
    Scanner scanner = new Scanner(System.in);
    String[] shopGoods = {"����", "����", "���", "�з�"};
    double[] shopGoodsPrices = {2, 5, 4, 5.5};
    private Scanner in = new Scanner(System.in);
    private MainView mainview = new MainView();
    private ShoppingDao shoppingdao = new ShoppingDao();
    private List<Goods> goodsList = new ArrayList<>();

    public void start(User user) {
        goodsList = shoppingdao.showAll(Goods_PATH);
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
                 count();
            } else if (i == 4) {
                // ����
                return;
            }
        }
    }

    private void count() {
        double money=0;
        double total=0;
        for (Goods goods : goodsList) {
            money=goods.getCount()*goods.getPrice();
            System.out.printf("��Ʒ��%s,����%.1fԪ,��%s��",goods.getName(),goods.getPrice(),goods.getCount()+"\n");
            total+=money;
        }
        System.out.printf("�ϼƣ�%.1fԪ",total);
        System.out.println("�Ƿ����(1�ǣ�0��)");
        int i = in.nextInt();
        if (i==1){
            goodsList.removeAll(goodsList);
            shoppingdao.save(goodsList,Goods_PATH);
            System.out.println("����ɹ�");
        }else if (i==0){
            return;
        }
    }

    private void showShoppingCar(User user) {
        System.out.println("�û�" + user.getName() + "���Ĺ��ﳵ��Ʒ���£�");
        System.out.println("����\t����\t����");
        for (Goods goods : goodsList) {
            System.out.println("\n" + goods.getName() + "\t" + goods.getPrice() + "\t\t" + goods.getCount());
        }

    }

    private void buy(User user) {

        while (true) {
            showGoods();
            System.out.println("\t\t0.����");
            System.out.println("\n\t������Ҫ�������Ʒ���:");
            int i = in.nextInt();
            if (i == 0) {
                break;
            }
            boolean find = false;
            for (Goods goods : goodsList) {
                if (shopGoods[i - 1].equals(goods.getName())) {
                    goods.setCount(goods.getCount() + 1);
                    find = true;

                }


            }
            if (!find) {
                Goods goods= new Goods();
                goods.setCount(1);
                goods.setName(shopGoods[i - 1]);
                goods.setPrice(shopGoodsPrices[i - 1]);
                goodsList.add(goods);
            }
            shoppingdao.save(goodsList, Goods_PATH);
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
