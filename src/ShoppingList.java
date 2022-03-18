import javax.lang.model.type.NullType;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingList {
    HashMap<String, ArrayList<String>> hashList=new HashMap<>();

    private String current_filename;

    public void CreateList(String fileName)  {
        try {
            if(fileName==null){
                fileName=GetFileName();
            }
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            BufferedReader czytacz =new BufferedReader(new FileReader(file));
            String line = null;
            line = czytacz.readLine();
            while (line!= null) {
                String[] splitedCategory=line.split(":");
                hashList.put(splitedCategory[0],new ArrayList<>(Arrays.asList(splitedCategory[1].split(","))));
                line=czytacz.readLine();
            }
            current_filename=fileName;
        }catch (Exception e){
            System.out.println("Coś nie działa dzwoń po bagiety");
        }
    }
    public void DisplayList()
    {
        if(hashList.isEmpty()){
            System.out.println("Lista jest pusta");
        }
        for (String category : hashList.keySet()) {
            System.out.println("\033[0;1m"+category+"\033[0m");
            for (String item : hashList.get(category)) {
                System.out.println("\t"+"*"+"\033[3m"+item+"\033[0m");
            }
        }
        System.out.println("_____________________________");
    }
    public void AddToList(String category,String item)
    {
        if (hashList.containsKey(category))
            hashList.get(category).add(item);
        else {
            ArrayList<String> newCategory=new ArrayList<>();
            newCategory.add(item);
            hashList.put(category,newCategory);
        }
    }
    public void RemoveCategory(String category)
    {
        if(hashList.containsKey(category)){
            hashList.remove(category);
            System.out.println("Usunięto kategorię "+category);
        }
        else
            System.out.println("Podana Kategoria nie istnieje");
    }
    public void RemoveItem(String category,String item)
    {
        if(!hashList.containsKey(category))
            System.out.println("Nie ma takiej kategorii");
        else if (!hashList.get(category).contains(item))
            System.out.println("Nie ma takiego przedmiotu");
        else {
            hashList.get(category).remove(item);
            System.out.println("Usunięto "+item+" z kategorii "+category);
            if(hashList.get(category).isEmpty()){
                RemoveCategory(category);
            }
        }

    }
    public void ClearList(){
        if (hashList.isEmpty())
            System.out.println("Nie ma czego czyścić");
        else
        {
            hashList.clear();
            System.out.println("Usunięto listę");
        }
    }
    public void ListToFile(){
        try {
            SaveName();
            FileWriter myWriter = new FileWriter(current_filename);
            for (String category : hashList.keySet()) {
                myWriter.write(category+":");
                for (String item : hashList.get(category)) {
                    myWriter.write(item+",");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void SaveName(){
        try {
            FileWriter w = new FileWriter("ShoppingList.txt");
            w.write(current_filename);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String GetFileName(){
        File file = new File("ShoppingList.txt");
        try {
            Scanner in = new Scanner(file);
            BufferedReader czytacz =new BufferedReader(new FileReader(file));
            String line = null;
            line = czytacz.readLine();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
            return "lista.txt";
        }

    }


}