import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        startMenu();

    }
    public static String inputLine(){
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        return inp;
    }
    public static void startMenu() throws Exception {
        MyMap mapMain = new MyMap();
        MyNumberMap numMap = new MyNumberMap();
        MyWordMap wordMap = new MyWordMap();

        mapMain.getFromFile("mapMain");
        numMap.getFromFile("mapNums");
        wordMap.getFromFile("wordMap");
        System.out.println();
        System.out.println("Выберите:\n" +
                "1. Словарь со словами\n" +
                "2. Словарь с цифрами\n" +
                "3. Показать содержимое обоих словарей");

        switch (inputLine()){
            case "1": {
                showMapOperations(wordMap);
                break;
            }
            case "2":{
                showMapOperations(numMap);
                break;
            }
            case "3":{
                System.out.println("Словарь слов");
                wordMap.showMap();
                System.out.println("Словарь цифр");
                numMap.showMap();
                startMenu();
                break;
            }
        }


    }
    public static void showMapOperations(Object map) throws Exception {
        System.out.println();
        System.out.println("Выберите:\n" +
                "1. Найти по ключу\n" +
                "2. Добавить элемент\n" +
                "3. Удалить элемент\n" +
                "4. Вернуться к выбору словаря");
        String opChoose = inputLine();
        switch (opChoose){
            case "1":{
                find(map);
                break;
            }
            case "2":{
                add(map);
                break;
            }
            case "3":{
                remove(map);
                break;
            }
            case "4":{
                startMenu();
                break;
            }
            default:{
                showMapOperations(map);
            }
        }

    }
    public static void find(Object map) throws Exception {
        System.out.println("Введите ключ");
        if(map instanceof MyWordMap){
            MyWordMap wMap = (MyWordMap) map;
            ArrayList<String> values = new ArrayList<>();
            values = wMap.getValuesByKey(inputLine());
            if(!values.isEmpty()){
                System.out.println("Содержание словаря слов:");
                wMap.showMap();
            }
            showMapOperations(wMap);
        }
        else {
            MyNumberMap numMap = (MyNumberMap) map;
            ArrayList<String> values = new ArrayList<>();
            values = numMap.getValuesByKey(inputLine());
            if(!values.isEmpty()){
                System.out.println("Содержание словаря чисел:");
                numMap.showMap();
            }
            showMapOperations(numMap);
        }
    }
    public static void remove(Object map) throws Exception {
        System.out.println("Введите ключ");
        if(map instanceof MyWordMap){
            MyWordMap wMap = (MyWordMap) map;
            wMap.removeElementByKey(inputLine());
            showMapOperations(wMap);
        }
        else {
            MyNumberMap numMap = (MyNumberMap) map;
            numMap.removeElementByKey(inputLine());
            showMapOperations(numMap);
        }
    }
    public static void add(Object map) throws Exception {
        boolean isWordMap = false;
        if(map instanceof MyWordMap) isWordMap = true;
        System.out.println("Введите ключ");
        String key = inputLine();
        if (isWordMap){
            MyWordMap wMap = (MyWordMap) map;
            if (!key.matches(wMap.getRegex()))
                while(!key.matches(wMap.getRegex())){
                    System.out.println("Ключ должен состоять из 4 строчных латинский букв");
                    key = inputLine();
                }
            System.out.println("Введите значения через пробел");
            ArrayList<String> values = new ArrayList<>(Arrays.asList(inputLine().split("")));
            wMap.addKeyValue(key, values);
            showMapOperations(wMap);
        }
        else{
            MyNumberMap numMap = (MyNumberMap) map;
            if (!key.matches(numMap.getRegex()))
                while(!key.matches(numMap.getRegex())){
                    System.out.println("Ключ должен состоять из 5 цифр");
                    key = inputLine();
                }
            System.out.println("Введите значения через пробел");
            ArrayList<String> values = new ArrayList<>(Arrays.asList(inputLine().split(" ")));
            numMap.addKeyValue(key, values);
            showMapOperations(numMap);
        }
    }

}