package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import vo.User;

public class UserDao {

	// 判断注册用户名是否重复
	public boolean findName(String name, String path) {
		List<String> filepaths = new ArrayList<String>();
		File files = new File(path);
		File[] listFiles = files.listFiles();
		for (File file : listFiles) {
			filepaths.add(file.getAbsolutePath());
		}
		
		for (int i = 0; i < filepaths.size(); i++) {
			FileInputStream in = null;
			path=filepaths.get(i);
			try {
				in = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(in);
				
				User user=(User) ois.readObject();
				if(name.equals(user.getName())) {return true;}
				ois.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return false;

	}

	public void save(User user,String path) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			ObjectOutputStream oop = new ObjectOutputStream(out);
			oop.writeObject(user);
			oop.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User findByNamePassword(String name,String password,String path) {
		List<String> filepaths = new ArrayList<String>();
		File files = new File(path);
		File[] listFiles = files.listFiles();
		for (File file : listFiles) {
			filepaths.add(file.getAbsolutePath());
		}
		
		for (int i = 0; i < filepaths.size(); i++) {
			FileInputStream in = null;
			path=filepaths.get(i);
			try {
				in = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(in);
				
				User user=(User) ois.readObject();
				if(name.equals(user.getName())&&password.equals(user.getPassword())) {return user;}
				ois.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return new User();		
	}

}
