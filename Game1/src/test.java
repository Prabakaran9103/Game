class Print extends Thread{

    public void run(){
        for(int i=0; i<10; i++){
            System.out.println(i);
        }
    }
}


public class test {
    public static void main(String arg[]){
        
        Print print;
        for(int i=0; i<5; i++){
            print = new Print();
            print.start();
        }
    }
}
