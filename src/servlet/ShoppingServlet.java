package servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ShoppingDao;
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
                // 结算
            } else if (i == 4) {
                // 返回
                break;
            }
        }
    }

    private void showShoppingCar(User user) {
        System.out.println("用户" + user.getName() + "您的购物车商品如下：");
        for (Goods goods : goodsList) {
            System.out.println("\n\t\t" + goods.getName() + " " + goods.getPrice() + " " + goods.getCount());
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
            if (i > 0 && i <= goodsList.size()) {
                boolean find = false;
                for (Goods goods : goodsList) {
                    if (shopGoods[i - 1].equals(goods.getName())) {
                        goods.setCount(goods.getCount() + 1);
                        find = true;
                    }
                }
                if (!find) {
                    Goods goods = new Goods();
                    goods.setCount(1);
                    goods.setName(shopGoods[i - 1]);
                    goods.setPrice(shopGoodsPrices[i - 1]);
                }

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
