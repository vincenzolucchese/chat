package com.demo.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client implements Runnable {
	private Socket socket = null;
	private Writer output = null;
	private String clientName = null;

	public Client(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			socket.setSendBufferSize(16384);
			socket.setTcpNoDelay(true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new OutputStreamWriter(socket.getOutputStream());
			clientName="USER-"+new Date().getTime();
			write("Welcome "+clientName+ "\r\n");
			AppChat.getClients().add(this);
			String line = null;
			while ((line = input.readLine()) != null) {
				if (line.equalsIgnoreCase("/exit"))
					return;
				broadcast(this, clientName + "> " + line);
			}
		} catch (Exception e) {
		} finally {
			deregisterClient(this);
			output = null;
			try {
				socket.close();
			} catch (Exception e) {
			}
			socket = null;
		}
	}

	private void deregisterClient(Client client) {
		boolean wasRegistered = false;
		synchronized (this) {
			wasRegistered = AppChat.getClients().remove(client);
		}
		if (wasRegistered)
			broadcast(client, client.clientName + " exit.");
	}

	private void broadcast(Client fromClient, String msg) {
		List<Client> clients = null;
		synchronized (this) {
			clients = new ArrayList<Client>(AppChat.getClients());
		}
		for (Client client : clients) {
			if (client.equals(fromClient))
				continue;
			try {
				client.write(msg + "\r\n");
			} catch (Exception e) {
			}
		}
	}

	public void write(String msg) throws IOException {
		output.write(msg);
		output.flush();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Writer getOutput() {
		return output;
	}

	public void setOutput(Writer output) {
		this.output = output;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
