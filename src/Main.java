import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        ArrayList<String>file=new ArrayList<String>();
        Random random=new Random();
        try(BufferedReader br=new BufferedReader(new FileReader("roncelli.csv"))){
            String linea=br.readLine();
            if(linea!=null) {
                String intestazione = linea + ";MioValore;Cancellazione";
                file.add(intestazione);
            }
            while (linea!=null){
                linea= br.readLine();
                int num= random.nextInt(11)+10;
                file.add(linea+";"+num+";0");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("roncelli.csv"))){
            for(int i=0;i< file.size();i++){
                bw.write(file.get(i));
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        String[]file2=file.get(0).split(";");
        System.out.println("Numero di campi: "+file2.length);
        int max=file.get(0).length();
        int indice=0;
        for(int i=1;i< file.size();i++){
            if(file.get(i).length()>max){
                max=file.get(i).length();
                indice=i+1;
            }
        }
        System.out.println("Massimo: "+max);
        System.out.println("Riga che contiene il recordo più lungo: "+indice);
    }
}