package com.demo.chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class AppChat implements Runnable {
	private int port = 10000;
	private static List<Client> clients = new ArrayList<Client>();
	private Socket socket;
	private ServerSocket serverSocket;
	
	public static void main(String[] args) {
		new AppChat().run();
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				socket = serverSocket.accept();
				new Thread(new Client(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

}