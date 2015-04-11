

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Chat_cliente extends JFrame implements Runnable {
    public static JTextField campoTexto; 
    public JTextArea areaTexto; 
    public JButton bEnviar;
    JScrollPane sPane;	
    JPanel jPanel1;
    JButton jButton1;
   JTextField Envia;
    JScrollPane jScrollPane1;
    static JTextArea jTextArea1;
    static String recibido;
    static OutputStream osalida;
    static DataOutputStream dsalida;
    static InputStream ientrada;
    static DataInputStream dentrada;
     static Socket cliente;
     static Thread hilo;
     
  
    public static Chat_cliente main; 
    public ActionListener alEnviar;
    public void llenar(String mensaje) {
        jTextArea1.append(mensaje);
        jTextArea1.append(System.getProperty("line.separator"));
    }
    
    public static void main(String args[]) {
        main = new Chat_cliente();
        main.setLocationRelativeTo(null); 
        
        try {

            cliente = new Socket("127.0.0.1", 3000);
            osalida = cliente.getOutputStream();
            dsalida = new DataOutputStream(osalida);
            ientrada = cliente.getInputStream();
            dentrada = new DataInputStream(ientrada);
           recibido= dentrada.readUTF();	
           jTextArea1.setText(jTextArea1.getText()+"\n recibido desde el cliente: "+recibido);
            main.llenar(recibido); 
           
            
          

        } catch (Exception e) {
            
        }
    
        main.ventana();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }     
    
    public void ventana() {
        
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Envia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Enviar");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);
        setVisible(true);
        setTitle("cliente");
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Envia, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(Envia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                        
                    try {
                        dsalida.writeUTF(Envia.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(Chat_cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        Envia.setText("");
                    
                            }
                });
        
        hilo=new Thread(this);
        hilo.start();
    }

	@Override
	public void run() {
		Thread ct= Thread.currentThread();
		while(true){
            try{			
                                recibido = dentrada.readUTF();
                                jTextArea1.setText(jTextArea1.getText()+"\n recibido desde el cliente: "+recibido);
                                System.out.println(recibido);
			}
		
		catch (IOException excepcion) {
			System.out.println(excepcion.getMessage());
		}		
	    
		}}
}
