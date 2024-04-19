import java.io.*;
import java.util.ArrayList;

public class MyMap implements Serializable {

    public static class Element implements Serializable{
        Element(String key, ArrayList<String> values) throws Exception {
            setKey(key);
            setValues(values);
        }
        Element(String key, String value) throws Exception {
            setKey(key);
            ArrayList<String> values = new ArrayList<String>(0);
            values.add(value);
            setValues(values);
        }


        private String key;
        private ArrayList<String> values = new ArrayList<String>(0);

        public void setValues(ArrayList<String> values) throws Exception {
            for(String value: values)
                if(!value.isBlank() && value.matches("[а-яА-Я]+") && !getValues().contains(value))
                    this.values.add(value);
            if(values.isEmpty()) throw new Exception("Нет валидных значений");
        }
        public ArrayList<String> getValues(){
            return values;
        }
        public void setKey(String key){
            if(!key.isBlank())
                this.key = key;
        }
        public String getKey(){
            return key;
        }
    }
    MyMap(){}
    MyMap(ArrayList<Element> keyValues)
    {
        this.myMap.addAll(keyValues);
    }

    private ArrayList<Element> myMap = new ArrayList<Element>(0);
    public ArrayList<Element> getMyMap(){ return myMap; }
    public void setMyMap(ArrayList<Element> myMap) {
        this.myMap = myMap;
    }


    public void addElement(Element newElement) throws Exception {
        if(newElement.key.isBlank() || newElement.values.isEmpty())
            return;
        for (Element element : myMap)
            if (element.key.equalsIgnoreCase(newElement.key)) {
                element.setValues(newElement.values);
                System.out.println("Значения " + "добавлены к ключу " + newElement.key);
                return;
            }
        myMap.add(newElement);
        System.out.println("Успешно создан ключ " + newElement.key);
    }
    public void addKeyValue(String key, ArrayList<String> values) throws Exception {
        if(key.isBlank())
            return;
        for(String value: values){
            if(value.isBlank()) values.remove(value);
        }
        for (Element element : myMap)
            if (element.key.equalsIgnoreCase(key)) {
                element.setValues(values);
                System.out.println("Значения " + "добавлены к ключу " + key);
                return;
            }
        myMap.add(new Element(key,values));
        System.out.println("Успешно добавлен новый ключ " + key);
    }
    public void removeElementByKey(String key){
        for (Element element: myMap)
            if (element.key.equalsIgnoreCase(key)){
                myMap.remove(element);
                System.out.println("Ключ " + key + " удалён");
                return;
            }
        System.out.println("Ключа нет в словаре");
    }

    public ArrayList<String> getValuesByKey(String key){
        for (Element element: myMap)
            if (element.key.equalsIgnoreCase(key)){
                return element.values;
            }
        System.out.println("Ключа нет в словаре");
        return null;
    }

    public void showMap(){
        for (Element element: myMap)
        {
            System.out.print(element.key + ": ");
            for (String value: element.values)
                System.out.print(value + "; ");
            System.out.print("\n");
        }
    }

    public void getFromFile(String fileName){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName+".ser"))) {
            ArrayList<Element> deserializedMap = (ArrayList<Element>) ois.readObject();
            setMyMap(deserializedMap);
            System.out.println("Файл десериализован!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void putInFile(String fileName){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName +".ser"))) {
            oos.writeObject(myMap);
            System.out.println("Сериализовано!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
