import java.io.File;
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
        return scanner.nextLine();
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
                String mapPath = getFilePath();
                while(mapPath.isBlank()) { mapPath = getFilePath(); }
                System.out.println("Действия с " + mapPath+".ser");
                wordMap.getFromFile(mapPath);
                showMapOperations(wordMap);
                break;
            }
            case "2":{
                String mapPath = getFilePath();
                while(mapPath.isBlank()) { mapPath = getFilePath(); }
                System.out.println("Действия с " + mapPath +".ser");
                numMap.getFromFile(mapPath);
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
            default: startMenu();
        }


    }
    public static void showMapOperations(Object map) throws Exception {
        System.out.println();

        System.out.println("Выберите:\n" +
                "1. Найти по ключу\n" +
                "2. Добавить элемент\n" +
                "3. Удалить элемент\n" +
                "4. Вернуться к выбору словаря\n" +
                "5. Посмотреть содержимое");
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
            case "5":{
                show(map);
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
            ArrayList<String> values;
            values = ((MyWordMap)map).getValuesByKey(inputLine());
            if(!values.isEmpty()){
                System.out.println("Содержание словаря слов:");
                ((MyWordMap)map).showMap();
            }
        }
        else {
            ArrayList<String> values;
            values = ((MyNumberMap)map).getValuesByKey(inputLine());
            if(!values.isEmpty()){
                System.out.println("Содержание словаря чисел:");
                ((MyNumberMap)map).showMap();
            }
        }
        showMapOperations(map);
    }
    public static void remove(Object map) throws Exception {
        System.out.println("Введите ключ");
        if(map instanceof MyWordMap){
            ((MyWordMap)map).removeElementByKey(inputLine());
        }
        else {
            MyNumberMap numMap = (MyNumberMap) map;
            ((MyNumberMap)map).removeElementByKey(inputLine());
        }
        showMapOperations(map);
    }
    public static void show(Object map) throws Exception {
        boolean isWordMap = map instanceof MyWordMap;
        if(isWordMap) ((MyWordMap)map).showMap();
        else((MyNumberMap)map).showMap();
        showMapOperations(map);
    }
    public static void add(Object map) throws Exception {
        boolean isWordMap = map instanceof MyWordMap;
        System.out.println("Введите ключ");
        String key = inputLine();
        if (isWordMap){
            if (!key.matches(((MyWordMap)map).getRegex())){
                String regex = ((MyWordMap)map).getRegex();
                while(!key.matches(regex)){
                    System.out.println("Ключ должен состоять из 4 строчных латинский букв");
                    key = inputLine();
                }
            }
            System.out.println("Введите значения через пробел");
            ArrayList<String> values = new ArrayList<>(Arrays.asList(inputLine().split(" ")));
            ((MyWordMap)map).addKeyValue(key, values);
            ((MyWordMap)map).putInFile("wordMap");

        }
        else{
            if (!key.matches(((MyNumberMap)map).getRegex()))
            {
                String regex = ((MyNumberMap)map).getRegex();
                while(!key.matches(regex)){
                    System.out.println("Ключ должен состоять из 5 цифр");
                    key = inputLine();
                }
            }
            System.out.println("Введите значения через пробел");
            ArrayList<String> values = new ArrayList<>(Arrays.asList(inputLine().split(" ")));
            ((MyNumberMap)map).addKeyValue(key, values);
            ((MyNumberMap)map).putInFile("mapNums");

        }
        showMapOperations(map);
    }

    public static String getFilePath(){
        System.out.println("Введите имя файла в .ser формате без (.ser)");
        String mapPath = inputLine() ;
        File f = new File(mapPath+ ".ser");
        if(!f.exists() || f.isDirectory()){
            return ""; }
        return  mapPath;
    }
}