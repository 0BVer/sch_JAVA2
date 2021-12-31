import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class test_in {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket sock = new ServerSocket(40000);
        Thread th = new Thread(()-> {
            try {
                new test_out();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        th.setDaemon(true);
        th.start();

        ObjectInputStream fromServer_OBJ = new ObjectInputStream(sock.accept().getInputStream());

        while (true) {
            temp temp_a = (temp) fromServer_OBJ.readObject();
            ppp(temp_a);
        }
    }

    static void ppp(temp aa) {
        System.out.println(aa.a);
    }
}

class test_out {
    test_out() throws IOException, CloneNotSupportedException {
        Socket sock = new Socket("localhost", 40000);
        System.out.println(sock.toString());
        ObjectOutputStream toServer_Obj = new ObjectOutputStream(sock.getOutputStream());

        temp t = new temp(1);
        toServer_Obj.writeObject(t);
        t.a = 2;
        toServer_Obj.writeObject(t);
        t.a = 3;
        toServer_Obj.writeObject(t.clone());
        toServer_Obj.writeObject(new temp(4));
    }
}

class temp implements Serializable, Cloneable {
    int a;

    temp(int a) {
        this.a = a;
    }

    protected temp clone() throws CloneNotSupportedException {
        return (temp) super.clone();
    }
}