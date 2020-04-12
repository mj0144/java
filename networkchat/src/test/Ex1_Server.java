package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Ex1_Server {

    private ServerSocket ss;
    private String reip;
    
    //Ŭ���̾�Ʈ ������ ������ Collection
    //������ ��� Ŭ���̾�Ʈ ������ �����صξ
    //��� ������ �ּҸ� �޸𸮿������� ������ �� �ִ�.
    private ArrayList<ServerThread> cliList;

    public Ex1_Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server Start!!!!!!!!");
            //Collection ����
            cliList = new ArrayList<>();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void execute() {
        while (true) {
            try {
                Socket s = ss.accept();   // ���ӱ�ٸ��� ���ŷ �޼��屸��.           
                reip = s.getInetAddress().getHostAddress();	//getInetAddress().getHostAddress()�� �����Ǹ�,
                											//getInetAddress().getHostName()�� ������ ������ ���� �� �ִ�.
                System.out.println("User IP:"+reip);
                
                //�����ؿ� ������ ServerThread�� �����ϵ��� �Ѵ�.
                ServerThread ct = new ServerThread(s, this); //������� ���ϰ�ü�� Ex1_Server�� �ּҸ� ������,
                
                //ArrayList�� ����
                cliList.add(ct);
                ct.start();	//run�޼��尡 ����.
                System.out.println("Current number of Clients :" + cliList.size());
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    //��� �ҽ� �ϼ��� ���� �޼��带 �����丵 �Ͻÿ�.(��������)
    //POJO�� , Ÿ���� ���� ���� ��������
    public void sendMsg(String str1, String str2, String str3,
                      String str4, ServerThread st) {
        //-----------------------------------------
        //protocol�� ������ Ŭ���̾�Ʈ�� ����̴�.
        
        String type1 = str1; //talk, enter���� ���������� �ۼ�
        String type2 = str2; //nickName, all���� ���������� �ۼ�
        String type3 = str3; //none,speaker
        String type4 = str4; //none,say;
        //����(response)
        String str = "";
        //talk/all/nickName/say
        //-----------------------------------------
        if(type1.equals("talk")){
            str = "talk/none/none/"+"["+type3+":" + reip+"]"+type4;
            System.out.println("Message :" + str);
        }else if(type1.equals("enter")){	//���⼭ ��.................�׸��� ������ ������ �ڵ带 ¥���ϴ� ����.
            //code
        }
        //��� �������� �ϼ��� str�� ��Ʈ���� ���ؼ� ������ �۾�
        for(ServerThread c : cliList){
           c.getPw().println(str);
        }
    }
    public static void main(String[] args) {
        Ex1_Server es = new Ex1_Server(9999);
        es.execute();
    }

}