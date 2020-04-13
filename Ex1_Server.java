package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex1_Server {

	private ServerSocket ss;
	private String reip;
	private ProtocolVO vo;
	// Ŭ���̾�Ʈ ������ ������ Collection
	// ������ ��� Ŭ���̾�Ʈ ������ �����صξ
	// ��� ������ �ּҸ� �޸𸮿������� ������ �� �ִ�.
	private ArrayList<ServerThread> cliList;

	public Ex1_Server(int port) { // �������ۺκ�. �ʱ�ȭ
		try {
			ss = new ServerSocket(port);
			System.out.println("Server Start!!!!!!!!");
			// Collection ����
			cliList = new ArrayList<>();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Ex1_Server es = new Ex1_Server(9999);
		es.execute();
	}

	public void execute() { // ���� ��ٸ���, ���� �˷��ִ� �޼���
		while (true) {
			try {
				Socket s = ss.accept(); // ���ӱ�ٸ��� ���ŷ �޼��屸��.
				reip = s.getInetAddress().getHostAddress(); // getInetAddress().getHostAddress()�� �����Ǹ�,
															// getInetAddress().getHostName()�� ������ ������ ���� �� �ִ�.
				System.out.println("User IP:" + reip);

				// �����ؿ� ������ ServerThread�� �����ϵ��� �Ѵ�.
				ServerThread ct = new ServerThread(s, this); // ������� ���ϰ�ü�� Ex1_Server�� �ּҸ� ������,

				// ArrayList�� ����
				cliList.add(ct);
				ct.start(); // run�޼��尡 ����.
				System.out.println("Current number of Clients :" + cliList.size());

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public void sendMsg(String str1, String str2, String str3, String str4, ServerThread st) {
		vo = new ProtocolVO();
		
		vo.setType1(str1);
		vo.setType2(str2);
		vo.setType3(str3);
		vo.setType4(str4);

		String str = "";

		if (vo.getType1().equals("talk")) {
			str = "[" + vo.getType3() + "] : " + vo.getType4();
			System.out.println("Message :" + str);
			
		} else if (vo.getType1().equals("enter")) { // ���⼭ ��.................�׸��� ������ ������ �ڵ带 ¥���ϴ� ����.
			// code
		}
		// ��� �������� �ϼ��� str�� ��Ʈ���� ���ؼ� ������ �۾�
		for (ServerThread c : cliList) {
			c.getPw().println(str);
		}
	}

	public void conMsg(String str) {
		String str2 = "[" + str + "���� �����ϼ̽��ϴ�]\n";
		for (ServerThread c : cliList) {
			c.getPw().println(str2);
		}
	}

}