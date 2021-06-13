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
			System.out.println("���� ������...");
			ServerSocket serverSocket = new ServerSocket(9001);

			Socket socket = serverSocket.accept();
			System.out.println(serverSocket.getInetAddress() + "�� �����, ��Ʈ ��ȣ : " + serverSocket.getLocalPort());
			System.out.println("������ ���� �޽����� �Է��ϼ���. close�� �Է��ϸ� ����˴ϴ�.");
			
			while (true) {
				InputStream input = socket.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String inputMsg = inputData.readUTF();
				
				System.out.println("Ŭ���̾�Ʈ�κ��� ���� �޽��� : " + inputMsg);
				
				OutputStream output = socket.getOutputStream();
				DataOutputStream outputData = new DataOutputStream(output);
				outputData.writeUTF(inputMsg);
				
				if(inputMsg.equals("close")) {
					outputData.close();
					inputData.close();
					System.out.println("Ŭ���̾�Ʈ ������ �����մϴ�.");
					socket.close();
					Thread.sleep(2000);
					System.out.println("������ �����մϴ�.");
					serverSocket.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
