package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex1_Server {

    private ServerSocket ss;
    private String reip;
    
    //클라이언트 소켓을 저장할 Collection
    //접속한 모든 클라이언트 소켓을 저장해두어서
    //모든 소켓의 주소를 메모리영역에서 참조할 수 있다.
    private ArrayList<ServerThread> cliList;

    public Ex1_Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server Start!!!!!!!!");
            //Collection 생성
            cliList = new ArrayList<>();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void execute() {
        while (true) {
            try {
                Socket s = ss.accept();   // 접속기다리는 블로킹 메서드구나.           
                reip = s.getInetAddress().getHostAddress();	//getInetAddress().getHostAddress()는 아이피를,
                											//getInetAddress().getHostName()은 도메인 정보를 얻을 수 있다.
                System.out.println("User IP:"+reip);
                
                //접속해온 소켓을 ServerThread가 관리하도록 한다.
                ServerThread ct = new ServerThread(s, this); //쓰레드로 소켓객체랑 Ex1_Server의 주소를 떤져줌,
                
                //ArrayList에 저장
                cliList.add(ct);
                ct.start();	//run메서드가 실행.
                System.out.println("Current number of Clients :" + cliList.size());
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    //모든 소스 완성후 현재 메서드를 리펙토링 하시오.(연습문제)
    //POJO로 , 타입이 같을 때는 가변인자
    public void sendMsg(String str1, String str2, String str3,
                      String str4, ServerThread st) {
        //-----------------------------------------
        //protocol은 서버와 클라이언트의 약속이다.
        
        String type1 = str1; //talk, enter등의 프로토콜을 작성
        String type2 = str2; //nickName, all등의 프로토콜을 작성
        String type3 = str3; //none,speaker
        String type4 = str4; //none,say;
        //응답(response)
        String str = "";
        //talk/all/nickName/say
        //-----------------------------------------
        if(type1.equals("talk")){
            str = "talk/none/none/"+"["+type3+":" + reip+"]"+type4;
            System.out.println("Message :" + str);
        }else if(type1.equals("enter")){	//여기서 그.................그리는 정보를 보내는 코드를 짜야하는 구나.
            //code
        }
        //모든 유저에게 완성된 str을 스트림을 통해서 보내는 작업
        for(ServerThread c : cliList){
           c.getPw().println(str);
        }
    }
    public static void main(String[] args) {
        Ex1_Server es = new Ex1_Server(9999);
        es.execute();
    }

}