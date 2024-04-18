import java.util.ArrayList;

public class MyMap {

    public static class Element{
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
                if(!value.isBlank() && value.matches("[а-яА-Я]+"))
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
    MyMap(ArrayList<Element> keyValues)
    {
        this.myMap.addAll(keyValues);
    }
    MyMap(ArrayList<Element> keyValues, String keyRegex , String valueRegex)
    {
        this.myMap.addAll(keyValues);

    }

    String valueRegex;
    String keyRegex;
    private ArrayList<Element> myMap = new ArrayList<Element>(0);
    public ArrayList<Element> getMyMap(){ return myMap; }
    public void setMyMap(ArrayList<Element> myMap) {
        this.myMap = myMap;
    }

    public void addValueToKey(String key, ArrayList<String> values){
        // Словарь, где ключ - 4 символа и они буквы латинской раскладки
//        if(key.matches("[a-zA-Z]{4}")){
//            for (String s : values) {
//                if (!s.isBlank())
//                    addElementToMap(wordMap, key, s);
//            }
//        }
//
//        // Словарь, где ключ - 5 символов и они - цифры
//        if(key.matches("[0-9]{5}")){
//            for (String s : values) {
//                if (!s.isBlank())
//                    addElementToMap(numberMap, key, s);
//            }
//        }
//        else{
//            System.out.println("Неправильно задан ключ");
//        }
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
                System.out.print("Значения ключа " + key + ": ");
                for(String value: element.values)
                    System.out.print(value + " ");
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
                System.out.print(value + ", ");
            System.out.print("\n");
        }
    }




}
