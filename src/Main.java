import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        ArrayList<String> file = new ArrayList<String>();
        Random random = new Random();
        try (BufferedReader br = new BufferedReader(new FileReader("roncelli.csv"))) {
            String linea = br.readLine();
            if (linea != null) {
                String intestazione = linea + ";MioValore;Cancellazione";
                file.add(intestazione);
            }
            while (linea != null) {
                linea = br.readLine();
                int num = random.nextInt(11) + 10;
                file.add(linea + ";" + num + ";0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("roncelli.csv"))) {
            for (int i = 0; i < file.size(); i++) {
                bw.write(file.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] file2 = file.get(0).split(";");
        System.out.println("Numero di campi: " + file2.length);
        int max = file.get(0).length();
        int indice = 0;
        for (int i = 1; i < file.size(); i++) {
            if (file.get(i).length() > max) {
                max = file.get(i).length();
                indice = i + 1;
            }
        }
        System.out.println("Massimo: " + max);
        System.out.println("Riga che contiene il recordo più lungo: " + indice);
        //System.out.println(file.get(indice - 1));
        for(int i=0;i< file.size();i++){
            String riga=file.get(i);
            if(riga.length()<max){
                int diff=max-riga.length();
                for(int j=0;j<diff;j++){
                    riga=riga+"0";
                }
                file.set(i,riga);
            }
        }
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("roncelli.csv"))){
            for(int i=0;i< file.size();i++){
                bw.write(file.get(i));
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("roncelli.csv",true))){
            String nuovoRecord;
            nuovoRecord="NuovoComune;NuovaProvincia;NuovaRegione;NuovoTipo ufficio;NuovaDenominazione ufficio;NuovoIndirizzo;NuovoCAP;NuovaSigla provincia;NuovoTelefono;NuovoFAX;NuovaEmail;NuovoH24 (SI/NO);NuovoIndirizzo internet;NuovaLatitudine;NuovaLongitudine";
            bw.write(nuovoRecord);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Inserisci la sigla della provincia da ricercare: ");
        String sigla=input.next();
        boolean siglaTrovata=false;
        for(int i=0;i< file.size();i++){
            String[]riga=file.get(i).split(";");
            if(riga.length>7&&riga[7].equalsIgnoreCase(sigla)){
                System.out.println(file.get(i));
                siglaTrovata=true;
            }
        }
        if (siglaTrovata==false){
            System.out.println("Sigla inesistente");
        }
        String[] riga= file.get(1).split(";");
        for(int i=0;i< riga.length;i++){
            riga[i]="modifica";
        }
        String totale=riga[0]+";"+riga[1]+";"+riga[2];
        file.set(1,totale);
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("roncelli.csv"))) {
            for(int i=0;i< file.size();i++){
                bw.write(file.get(i));
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}