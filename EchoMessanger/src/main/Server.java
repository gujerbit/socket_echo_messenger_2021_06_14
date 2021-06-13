package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("서버 생성중...");
			ServerSocket serverSocket = new ServerSocket(9001);

			Socket socket = serverSocket.accept();
			System.out.println(serverSocket.getInetAddress() + "와 연결됨, 포트 번호 : " + serverSocket.getLocalPort());
			System.out.println("서버로 보낼 메시지를 입력하세요. close를 입력하면 종료됩니다.");
			
			while (true) {
				InputStream input = socket.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String inputMsg = inputData.readUTF();
				
				System.out.println("클라이언트로부터 받은 메시지 : " + inputMsg);
				
				OutputStream output = socket.getOutputStream();
				DataOutputStream outputData = new DataOutputStream(output);
				outputData.writeUTF(inputMsg);
				
				if(inputMsg.equals("close")) {
					outputData.close();
					inputData.close();
					System.out.println("클라이언트 연결을 종료합니다.");
					socket.close();
					Thread.sleep(2000);
					System.out.println("서버를 종료합니다.");
					serverSocket.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
