package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Thread t = new Thread(new Server());
		Thread t2 = new Thread(new Client(scan));
		t.start();
		t2.start();
	}

}
