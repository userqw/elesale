package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import vo.Goods;
import vo.User;

public class ShoppingDao {

	public void save(Goods goods, String path) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			ObjectOutputStream oop = new ObjectOutputStream(out);
			oop.writeObject(goods);
			oop.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 判断注册用户名是否重复
	public Goods findName(String name, String path) {
		List<String> filepaths = new ArrayList<String>();
		File files = new File(path);
		File[] listFiles = files.listFiles();
		for (File file : listFiles) {
			filepaths.add(file.getAbsolutePath());
		}

		for (int i = 0; i < filepaths.size(); i++) {
			FileInputStream in = null;
			path = filepaths.get(i);
			try {
				in = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(in);

				Goods goods = (Goods) ois.readObject();
				if (name.equals(goods.getName())) {
					return goods;
				}
				ois.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new Goods();

	}

	public Goods showAll(String path) {

		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(in);
			Goods goods = (Goods) ois.readObject();
			ois.close();
			in.close();
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Goods();

	}

}
