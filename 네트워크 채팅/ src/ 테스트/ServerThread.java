pacpackage test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

//Server의 일을 대신 해줄 스레드
//어떤일을 대신 해줘야 하나? 접속해온 클라이언트 당 readLine()담당
public class ServerThread extends Thread {
	// 서버로부터 위임받은 소켓의 정보
	private Socket socket;
	// 서버의 주소
	private Ex1_Server server;
	// 소켓으로 부터 연결된 두 스트림
	private PrintWriter pw;
	private BufferedReader in;
	private BufferedOutputStream bout;
	private BufferedInputStream bin;

	// Ex1_Server에서 ServerThread에서 이미 생성된
	// 각 사용자들의 소켓과 연결된 PrintWriter의 주소를 가져가기
	// 위한 getter를 제공해준다.
	public PrintWriter getPw() {
		return pw;
	}

	public ServerThread(Socket socket, Ex1_Server server) {
		try {
			this.socket = socket;
			this.server = server; // 초기화.
			pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true); // autoflush :
																							// true.//클라이언트에서 데이터를 떤질
																							// 객체.
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			// 소켓 하나당 스레드가 각각 붙어서 스트림에서
			// 전송되어온 데이터를 응답하기 위해서 일을 한다.
			in = new BufferedReader( // in에는 어떤 정보들이 들어가는거지? //클라이언트에서 데이터를 받을 객체
					new InputStreamReader(socket.getInputStream())); // 데이터가 쌓이기를 기다리림.
			while (true) {
				String msg = in.readLine(); // 읽을 데이터가 있을때 까지 기다려야지.
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
		// ex) draw/color/x/y //draw는 그릴때 str1이고, color는 그냥 null로 뜨네. 이것도 설정해야하고, x,y좌표는
		// 그대로 뜨고. ㅇㅋㅇㅋ
		StringTokenizer stn = new StringTokenizer(msg, "/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		String str3 = stn.nextToken();
		String str4 = stn.nextToken();
		System.out.println("Log Token :" + str1); // 텍스트만 입력시:talk 그림:draw
		System.out.println("Log Token :" + str2); // 텍스트만 입력시:all 그림:색이름(지금은 null로 뜨네)
		System.out.println("Log Token :" + str3); // 텍스트만 입력시:client1(클라이언트 정보구나) 그림 : x좌표값
		System.out.println("Log Token :" + str4); // 텍스트만 입력시 :ㅎㅇ(텍스트 내용) 그림 : y좌표값
		// Server의 SendMsg를 호출시 프로토콜을 잘라서 전달
		// ArrayList를 가지고있는 것이 서버이기 때문에 서버에 sendMsg를 만들어 놓음
		server.sendMsg(str1, str2, str3, str4, this);

	}

}
kage test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

//Server의 일을 대신 해줄 스레드
//어떤일을 대신 해줘야 하나? 접속해온 클라이언트 당 readLine()담당
public class ServerThread extends Thread{
    //서버로부터 위임받은 소켓의 정보
    private Socket socket;
    //서버의 주소
    private Ex1_Server server;
    //소켓으로 부터 연결된 두 스트림
    private PrintWriter pw;
    private BufferedReader in;
    private BufferedOutputStream bout;
    private BufferedInputStream bin;
   

    //Ex1_Server에서 ServerThread에서 이미 생성된
    //각 사용자들의 소켓과 연결된 PrintWriter의 주소를 가져가기
    //위한 getter를 제공해준다.
    public PrintWriter getPw() {
        return pw;
    }
    

    public ServerThread(Socket socket, Ex1_Server server) {
        try {
            this.socket = socket;
            this.server = server;	// 초기화.
            pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()),true);	//autoflush : true.//클라이언트에서 데이터를 떤질 객체.
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        
    }
    
    @Override
    public void run() {
        try {
            //소켓 하나당 스레드가 각각 붙어서 스트림에서
            //전송되어온 데이터를 응답하기 위해서 일을 한다.
            in = new BufferedReader(	//in에는 어떤 정보들이 들어가는거지?	//클라이언트에서 데이터를 받을 객체
                    new InputStreamReader(socket.getInputStream()));	//데이터가 쌓이기를 기다리림.
            while(true){
                String msg = in.readLine();	//읽을 데이터가 있을때 까지 기다려야지.
                System.out.println("Client Msg:" + msg);
                transMsg(msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void transMsg(String msg) {
        // ex) draw/color/x/y	//draw는 그릴때 str1이고, color는 그냥 null로 뜨네. 이것도 설정해야하고, x,y좌표는 그대로 뜨고. ㅇㅋㅇㅋ
        StringTokenizer  stn = new StringTokenizer(msg,"/");
        String str1 = stn.nextToken();
        String str2 = stn.nextToken();
        String str3 = stn.nextToken();
        String str4 = stn.nextToken();
        System.out.println("Log Token :" + str1);	//텍스트만 입력시:talk 그림:draw
        System.out.println("Log Token :" + str2);	//텍스트만 입력시:all 그림:색이름(지금은 null로 뜨네)
        System.out.println("Log Token :" + str3);	//텍스트만 입력시:client1(클라이언트 정보구나) 그림 : x좌표값
        System.out.println("Log Token :" + str4);	//텍스트만 입력시 :ㅎㅇ(텍스트 내용) 그림 : y좌표값
        //Server의 SendMsg를 호출시 프로토콜을 잘라서 전달											
        //ArrayList를 가지고있는 것이 서버이기 때문에 서버에 sendMsg를 만들어 놓음
        server.sendMsg(str1, str2, str3, str4, this);
      
    }
  
} 



