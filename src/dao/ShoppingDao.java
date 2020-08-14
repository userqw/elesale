package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import vo.Goods;
import vo.User;

public class ShoppingDao {

	public void save(List<Goods> goodsList, String path) {
		File file = new File(path);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream out = null;
		try {

			ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(file));
			oop.writeObject(goodsList);
			oop.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public List<Goods> showAll(String path) {
		File file = new File(path);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ObjectInputStream ois =null;
		try {
			ois= new ObjectInputStream(new FileInputStream(file));
			List<Goods> goodsList = (List<Goods>) ois.readObject();

			if (goodsList.size()!=0) {

				return goodsList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ArrayList<>();

	}

}
