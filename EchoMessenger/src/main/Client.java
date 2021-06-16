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
			System.out.println(ip + "가 서버에 접속중...");
			Socket socket = new Socket(ip, 9001);
			while(true) {
				String outputMsg = scan.nextLine();
				OutputStream output = socket.getOutputStream();
				DataOutputStream outputData = new DataOutputStream(output);
				outputData.writeUTF(outputMsg);
				
				InputStream input = socket.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String inputMsg = inputData.readUTF();
				
				System.out.println("서버로부터 받은 메시지 : " + inputMsg);
				
				if(outputMsg.equals("close")) {
					outputData.close();
					inputData.close();
					socket.close();
					System.out.println("서버 연결을 종료합니다.");
					Thread.sleep(1000);
					break;
				} else {
					System.out.println("서버로 보낼 메시지를 입력하세요. close를 입력하면 종료됩니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
