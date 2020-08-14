package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import vo.User;

public class UserDao {


	/**
	 * 保存用户
	 * @param userList
	 * @param path
	 */
	public void save(List<User> userList,String path) {
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
			out = new FileOutputStream(path);
			ObjectOutputStream oop = new ObjectOutputStream(out);
			oop.writeObject(userList);
			oop.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	//查询所有用户
	public List<User> findAll(String userPath) {
		File file = new File(userPath);

		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(userPath));
			List<User>userList = (List<User>) ois.readObject();
			if (userList.size()!=0){return userList;}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
