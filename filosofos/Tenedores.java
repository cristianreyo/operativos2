
public class Tenedores{
 int numero;
 boolean enUso;
	public Tenedores(int x){
	numero=x;
 	}
	synchronized public void usar(){
	if(enUso){
		}else{
			enUso= true;
        }
 }
 synchronized public void dejar(){
        enUso = false;
    }
 }
