
public class Cena{
  Tenedores tenedores[];
  int parametro = 5;
 public static void main(String args[]){
  Cena cena=new Cena();
  for(int i=0; i<5; i++){
    Filosofo pensante=new Filosofo(i,cena);
 }
 }
 public Cena(){
 tenedores=new Tenedores[parametro];
 for(int i=0;i<5;i++){
 tenedores[i]=new Tenedores(i);
 }
 }
 public Tenedores getTenedor(int x){
  return tenedores[x];
 }
 public int getTenedorDer(int x){
  return (x+1)%parametro;
  }
 public int getTenedorIzq(int x){
  return x;
 }
 }
