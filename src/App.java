import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

import models.PriorityQueueVector;

public class App {



    PriorityQueue<String> queue = new PriorityQueue<String>();
    PriorityQueueVector<String> vector = new PriorityQueueVector<String>();
    Scanner scanner = new Scanner(System.in);
    int type=0;
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }

    /**
     * initialize the program
     */
    public void start(){
        menu();
    }


    /**
     * first menu to use vector or collection
     */
    public void menu(){
        System.out.println("1. USAR VECTOR");
        System.out.println("2. USAR COLLECTION FRAMEWORK");
        int resp = scanner.nextInt();

        type=resp;

        readFile();

        menuPacient();
    }


    /**
     * read all the pacients
     */
    public void readFile(){
        File file = new File("src/Pacientes.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            
            String st;
            while ((st = br.readLine()) != null){
                String[] line = st.split(",");
                    String pacient = line[2]+","+line[0]+","+line[1];
                if(type==1){
                    vector.add(pacient);
                }else if(type==2){
                    queue.add(pacient);
                }
            }            
            if(type==1){
                int count = vector.size();
                String str[] = new String[count];
                str = vector.toString(vector, count);
                str = vector.sortHeap(str, count);
                vector.listVectorHeap(str, count, vector);

            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("ARCHIVO NO ENCONTRADO");
        }
    }


    /**
     * menu to view pacients or remove
     */
    public void menuPacient(){
        int resp=0;
        while(resp!=3){
            System.out.println("1. ver Pacientes");
            System.out.println("2. Siguiente");
            System.out.println("3. Salir");
            resp = scanner.nextInt();
            switch(resp){
                case 1:
                    viewPacient();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    System.out.println("gracias por usar el programa");
                    break;
            }
        }
    }

    /**
     * view all the pacients
     */
    public void viewPacient(){
        int size = vector.size();
        if(type==1){
            
            for(int i=0;i<size;i++){
                System.out.println(vector.get(i));
            }
        }else{
            System.out.println(queue);
        }
    }

    /**
     * remove the first pacient
     */
    public void remove(){
        if (type==1){
            System.out.println(vector.pop());
        }else{
            if(!queue.isEmpty()){
                System.out.println(queue.poll());
            }else{
                System.out.println("YA NO HAY MÁS PACIENTES");
            }
        }
    }
}
