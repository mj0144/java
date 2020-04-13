package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex1_Server {

	private ServerSocket ss;
	private String reip;
	private ProtocolVO vo;
	// 클라이언트 소켓을 저장할 Collection
	// 접속한 모든 클라이언트 소켓을 저장해두어서
	// 모든 소켓의 주소를 메모리영역에서 참조할 수 있다.
	private ArrayList<ServerThread> cliList;

	public Ex1_Server(int port) { // 서버시작부분. 초기화
		try {
			ss = new ServerSocket(port);
			System.out.println("Server Start!!!!!!!!");
			// Collection 생성
			cliList = new ArrayList<>();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Ex1_Server es = new Ex1_Server(9999);
		es.execute();
	}

	public void execute() { // 접속 기다리고, 접속 알려주는 메서드
		while (true) {
			try {
				Socket s = ss.accept(); // 접속기다리는 블로킹 메서드구나.
				reip = s.getInetAddress().getHostAddress(); // getInetAddress().getHostAddress()는 아이피를,
															// getInetAddress().getHostName()은 도메인 정보를 얻을 수 있다.
				System.out.println("User IP:" + reip);

				// 접속해온 소켓을 ServerThread가 관리하도록 한다.
				ServerThread ct = new ServerThread(s, this); // 쓰레드로 소켓객체랑 Ex1_Server의 주소를 떤져줌,

				// ArrayList에 저장
				cliList.add(ct);
				ct.start(); // run메서드가 실행.
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
			
		} else if (vo.getType1().equals("enter")) { // 여기서 그.................그리는 정보를 보내는 코드를 짜야하는 구나.
			// code
		}
		// 모든 유저에게 완성된 str을 스트림을 통해서 보내는 작업
		for (ServerThread c : cliList) {
			c.getPw().println(str);
		}
	}

	public void conMsg(String str) {
		String str2 = "[" + str + "님이 접속하셨습니다]\n";
		for (ServerThread c : cliList) {
			c.getPw().println(str2);
		}
	}

}