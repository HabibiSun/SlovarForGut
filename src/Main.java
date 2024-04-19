import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {



        MyMap mapMain = new MyMap();
        MyNumberMap mapNums = new MyNumberMap();
        MyWordMap wordMap = new MyWordMap();

        mapMain.getFromFile("mapMain");
        mapNums.getFromFile("mapNums");
        wordMap.getFromFile("wordMap");


    }
    public static String inputLine(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ваш выбор:");
        if (scanner.hasNext()) {
            String inp = scanner.nextLine();
            scanner.close();
            return inp;
        }
        scanner.close();
        return inputLine();
    }
    public static void StartMenu(){
        System.out.println("Выберите:\n" +
                "1. Словарь со словами\n" +
                "2. Словарь с цифрами\n" +
                "3. Показать содержимое обоих словарей");
        switch (inputLine()){
            case "1":

        }


    }
    public void showMapOperations(Object mapType){
        boolean isWordMap = false;
        if(mapType instanceof MyWordMap) isWordMap = true;
        System.out.println("Выберите:\n" +
                "1. Найти по ключу\n" +
                "2. Добавить элемент\n" +
                "3. Удалить элемент");

        String opChoose = inputLine();
        switch (opChoose){
            case "1":
                System.out.println("Введите ключ");
                String key = inputLine();
                if (isWordMap){
                    if()
                }
                else()

        }

    }
}