import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        String key1 = "hi";
        String key2 = "hell";
        String key3 = "poch";
        String key4 = "poc3";
        String key5 = "12345";
        String key6 = "32345";

        ArrayList<String> values1 = new ArrayList<>();
        values1.add("Привет");  values1.add("Здорово");

        MyMap.Element el1 = new MyMap.Element(key1, values1);
        MyMap.Element el2 = new MyMap.Element(key2, values1);
        MyMap.Element el3 = new MyMap.Element(key3, values1);
        MyMap.Element el4 = new MyMap.Element(key4, values1);
        MyMap.Element el5 = new MyMap.Element(key5, values1);
        MyMap.Element el6 = new MyMap.Element(key6, values1);
        ArrayList<MyMap.Element> elems1 = new ArrayList<>();
        elems1.add(el1);
        elems1.add(el2);
        elems1.add(el3);
        elems1.add(el4);
        elems1.add(el5);
        elems1.add(el6);

        MyMap mapMain = new MyMap(elems1);
        MyNumberMap mapNums = new MyNumberMap(elems1);
        MyWordMap wordMap = new MyWordMap(elems1);
        System.out.println("Весь");
        mapMain.showMap();
        System.out.println("Номера");
        mapNums.showMap();
        System.out.println("Строки");
        wordMap.showMap();
        wordMap.removeElementByKey("hell");
        wordMap.removeElementByKey("hel");
        wordMap.addKeyValue("poch",values1 );
        wordMap.showMap();

    }
}