import java.io.IOException;
import java.security.spec.ECField;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Chcesz użyć ostatniej listy , czy stworzyć nową? 1-OSTATNIA 0-NOWA");
        Scanner input = new Scanner(System.in);
        int cond=input.nextInt();
        input.nextLine();
        String fileName;
        ShoppingList shoppingList = new ShoppingList();
        if (cond == 0) {
            System.out.println("Podaj nazwę pliku text w którym przechowujesz listę zakupów: ");
            while (true) {
                try {
                    fileName = input.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Napotkano błąd w pobieraniu nazwy pliku");
                }
            }
        } else {
            fileName = null;
        }
        shoppingList.CreateList(fileName);
        shoppingList.DisplayList();
        String commandName = null;
        while (true) {
            System.out.println("Dysponujesz następującymi komendami: \n\tAddToList-dodanie przedmiotu do danej kategorii\n\tRemoveCategory-Usunięcie całej kategoirii\n\tRemoveItem-Usunięcie przedmiotu z podanej kategorii\n\tClearList-Usuwa całą listę\n\tListToFile-zapisuje liste do pliku.");
            System.out.println("Aby zakończyć edycję wpisz EXIT");
            System.out.println("Wpisz nazwę komendy aby ją wykonać:");
            try {
                commandName = input.nextLine();
            } catch (Exception e) {
                System.out.println("Napotkano błąd w trakcie wpisywania nazwy komendy.");
                System.exit(1);
            }

            String category, item;
            switch (commandName) {
                case "ListToFile": {
                    shoppingList.ListToFile();
                }break;
                case "EXIT":
                    shoppingList.ListToFile();

                    System.exit(0);
                case "RemoveItem": {
                    System.out.println("Podaj Kategorię");
                    while (true) {
                        try {
                            category = input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Podano złą kategorię");
                        }
                    }
                    System.out.println("Podaj Item");
                    while (true) {
                        try {
                            item = input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Podano zly item");

                        }
                    }
                    shoppingList.RemoveItem(category, item);
                }break;
                case "RemoveCategory": {
                    System.out.println("Podaj Kategorie");
                    while (true) {
                        try {
                            category = input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Nie ma takiej Kategorii");
                        }
                    }
                    shoppingList.RemoveCategory(category);
                }break;
                case "AddToList": {
                    System.out.println("Podaj Kategorię");
                    while (true) {
                        try {
                            category = input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Podano złą kategorię");
                        }
                    }
                    System.out.println("Podaj Item");
                    while (true) {
                        try {
                            item = input.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Podano zly item");
                        }
                    }
                    shoppingList.AddToList(category, item);
                }
                break;
                case "ClearList": {
                    shoppingList.ClearList();
                }break;
                default: System.out.println("Podano niewłaściwą kategorię");
            }
            shoppingList.DisplayList();
        }

    }
}


