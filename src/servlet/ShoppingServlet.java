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
    String[] title = {"选购商品", "查看购物车", "结算", "返回"};
    Scanner scanner = new Scanner(System.in);
    String[] shopGoods = {"玉米", "键盘", "鼠标", "盒饭"};
    double[] shopGoodsPrices = {2, 5, 4, 5.5};
    private Scanner in = new Scanner(System.in);
    private MainView mainview = new MainView();
    private ShoppingDao shoppingdao = new ShoppingDao();
    private List<Goods> goodsList = new ArrayList<>();

    public void start(User user) {
        goodsList = shoppingdao.showAll(Goods_PATH);
        while (true) {
            mainview.show(title);
            System.out.println("\n\t请输入操作序号:");
            int i = in.nextInt();
            if (i == 1) {
                // 选购商品
                buy(user);

            } else if (i == 2) {
                // 查看购物车
                showShoppingCar(user);
            } else if (i == 3) {
                 count();
            } else if (i == 4) {
                // 返回
                return;
            }
        }
    }

    private void count() {
        double money=0;
        double total=0;
        for (Goods goods : goodsList) {
            money=goods.getCount()*goods.getPrice();
            System.out.printf("商品：%s,单价%.1f元,共%s件",goods.getName(),goods.getPrice(),goods.getCount()+"\n");
            total+=money;
        }
        System.out.printf("合计：%.1f元",total);
        System.out.println("是否结算(1是，0否)");
        int i = in.nextInt();
        if (i==1){
            goodsList.removeAll(goodsList);
            shoppingdao.save(goodsList,Goods_PATH);
            System.out.println("结算成功");
        }else if (i==0){
            return;
        }
    }

    private void showShoppingCar(User user) {
        System.out.println("用户" + user.getName() + "您的购物车商品如下：");
        System.out.println("名称\t单价\t数量");
        for (Goods goods : goodsList) {
            System.out.println("\n" + goods.getName() + "\t" + goods.getPrice() + "\t\t" + goods.getCount());
        }

    }

    private void buy(User user) {

        while (true) {
            showGoods();
            System.out.println("\t\t0.返回");
            System.out.println("\n\t请输入要购买的商品序号:");
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
            System.out.println("添加购物车成功");

        }
    }

    private void showGoods() {
        System.out.println("\n\t\t======欢迎选购======");
        System.out.println("\t\t编号 名称 单价");
        for (int i = 0; i < shopGoods.length; i++) {
            System.out.printf("\t\t%d %s %.1f\n", i + 1, shopGoods[i], shopGoodsPrices[i]);
        }
    }

}
