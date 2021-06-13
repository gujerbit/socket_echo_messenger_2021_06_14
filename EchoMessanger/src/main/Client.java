package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
	private Scanner scan;
	
	public Client(Scanner scan) {
		this.scan = scan;
	}
	
	@Override
	public void run() {
		try {
			String ip = "127.0.0.1";
			System.out.println(ip + "�� ������ ������...");
			Socket socket = new Socket(ip, 9001);
			while(true) {
				String outputMsg = scan.nextLine();
				OutputStream output = socket.getOutputStream();
				DataOutputStream outputData = new DataOutputStream(output);
				outputData.writeUTF(outputMsg);
				
				InputStream input = socket.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String inputMsg = inputData.readUTF();
				
				System.out.println("�����κ��� ���� �޽��� : " + inputMsg);
				
				if(outputMsg.equals("close")) {
					outputData.close();
					inputData.close();
					socket.close();
					System.out.println("���� ������ �����մϴ�.");
					Thread.sleep(1000);
					break;
				} else {
					System.out.println("������ ���� �޽����� �Է��ϼ���. close�� �Է��ϸ� ����˴ϴ�.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
