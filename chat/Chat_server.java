

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Chat_server extends JFrame {

    private static ServerSocket servidor; 
    private static Socket socketConectado; 
    public static Chat_server main;
    static Chat_server l = new Chat_server();
    String recibido;
    InputStream ientrada;
    DataInputStream dentrada;
    Socket socket;
     static OutputStream osalida;
    static DataOutputStream dsalida;
    // Variables declaration - do not modify                     
    static javax.swing.JPanel jPanel1;
    static javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTextArea jTextArea1;
    
    private void grafi() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        setVisible(true);
        setTitle("Server");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();

    }// </editor-fold> 
    public void llenar(String mensaje) {
        
        jTextArea1.append(mensaje);
        jTextArea1.append(System.getProperty("line.separator"));
    }
    public static void main(String[] args) {
        Thread h = new Thread();
        h.start();
         
        l.grafi();
        l.setLocationRelativeTo(null); 
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        try {
            ServerSocket s = new ServerSocket(3000);
            l.llenar("Esperando Cliente ...");
            while (true) {
                try {
                   socketConectado = s.accept(); 
                    Runnable nuevoSocket = new SocketServerHilo(socketConectado);
		    Thread hiloSocket = new Thread(nuevoSocket);
		    hiloSocket.start();
                    l.llenar("Cliente conectado a : " + socketConectado.getLocalSocketAddress());                   
                } catch (IOException ex) {
                    Logger.getLogger(Chat_server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException excepcion) {			
			System.out.println(excepcion);
		}
    }
}