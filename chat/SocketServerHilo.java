


import java.net.*;
import java.io.*;

public class SocketServerHilo implements Runnable {
static Chat_server main2=new Chat_server();

    String recibido;
    OutputStream osalida;
	DataOutputStream dsalida;

	InputStream ientrada;
	DataInputStream dentrada;

	Socket socket;
	public SocketServerHilo(Socket lsocket){
		try{
			socket = lsocket;			
		}
		catch (Exception excepcion) {
			System.out.println(excepcion);
		}		

	}

	public void run() {	
		

		try{			
			osalida = socket.getOutputStream();
			dsalida = new DataOutputStream(osalida);

			ientrada = socket.getInputStream();
			dentrada = new DataInputStream(ientrada);
                        
			dsalida.writeUTF("Bienvenido al server");                      

			do{
                                recibido = dentrada.readUTF();	
                                
				//System.out.println("cliente del puerto "+socket.getPort()+" dice:"+ recibido);
                                dsalida.writeUTF("cliente del puerto "+socket.getPort()+" dice:"+ recibido);
                                main2.llenar("cliente del puerto "+socket.getPort()+" dice:"+ recibido);
                                //main.llenar("cliente del puerto "+socket.getPort()+" dice:"+ recibido);
                                
			}while(!recibido.equals("bye"));
		}
		catch (IOException excepcion) {
			System.out.println(excepcion.getMessage());
		}		
		
		try{
			dsalida.close();
			dentrada.close();
			socket.close();			
		}
		catch (IOException excepcion) {
			System.out.println(excepcion);
		}			
	}
        
}