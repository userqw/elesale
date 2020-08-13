package view;

public class MainView {
	public void show(String[] str) {
		for (int i = 0; i < str.length; i++) {
			System.out.printf("\t\t%d \t%s\n", i + 1, str[i]);
		}
	}
}
