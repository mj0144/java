package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

//Server�� ���� ��� ���� ������
//����� ��� ����� �ϳ�? �����ؿ� Ŭ���̾�Ʈ �� readLine()���
public class ServerThread extends Thread {
	// �����κ��� ���ӹ��� ������ ����
	private Socket socket;
	// ������ �ּ�
	private Ex1_Server server;
	// �������� ���� ����� �� ��Ʈ��
	private PrintWriter pw;
	private BufferedReader in;
	private BufferedOutputStream bout;
	private BufferedInputStream bin;

	// Ex1_Server���� ServerThread���� �̹� ������
	// �� ����ڵ��� ���ϰ� ����� PrintWriter�� �ּҸ� ��������
	// ���� getter�� �������ش�.
	public PrintWriter getPw() {
		return pw;
	}

	public ServerThread(Socket socket, Ex1_Server server) {
		try {
			this.socket = socket;
			this.server = server; // �ʱ�ȭ.
			pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true); // autoflush :
																							// true.//Ŭ���̾�Ʈ���� �����͸� ����
																							// ��ü.
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			// ���� �ϳ��� �����尡 ���� �پ ��Ʈ������
			// ���۵Ǿ�� �����͸� �����ϱ� ���ؼ� ���� �Ѵ�.
			in = new BufferedReader( // in���� � �������� ���°���? //Ŭ���̾�Ʈ���� �����͸� ���� ��ü
					new InputStreamReader(socket.getInputStream())); // �����Ͱ� ���̱⸦ ��ٸ���.
			while (true) {
				String msg = in.readLine(); // ���� �����Ͱ� ������ ���� ��ٷ�����.
				if (msg.contains("U:")) {
					StringTokenizer st = new StringTokenizer(msg, "U:");
					String str = st.nextToken();
					server.conMsg(str);
				} else {
					System.out.println("Client Msg:" + msg);
					transMsg(msg);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void transMsg(String msg) {
		// ex) draw/color/x/y //draw�� �׸��� str1�̰�, color�� �׳� null�� �߳�. �̰͵� �����ؾ��ϰ�, x,y��ǥ��
		// �״�� �߰�. ��������
		StringTokenizer stn = new StringTokenizer(msg, "/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		String str3 = stn.nextToken();
		String str4 = stn.nextToken();
		System.out.println("Log Token :" + str1); // �ؽ�Ʈ�� �Է½�:talk �׸�:draw
		System.out.println("Log Token :" + str2); // �ؽ�Ʈ�� �Է½�:all �׸�:���̸�(������ null�� �߳�)
		System.out.println("Log Token :" + str3); // �ؽ�Ʈ�� �Է½�:client1(Ŭ���̾�Ʈ ��������) �׸� : x��ǥ��
		System.out.println("Log Token :" + str4); // �ؽ�Ʈ�� �Է½� :����(�ؽ�Ʈ ����) �׸� : y��ǥ��
		// Server�� SendMsg�� ȣ��� ���������� �߶� ����
		// ArrayList�� �������ִ� ���� �����̱� ������ ������ sendMsg�� ����� ����
		server.sendMsg(str1, str2, str3, str4, this);

	}

}
