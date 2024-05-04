import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWordMap extends MyMap {

    MyWordMap(){}
    MyWordMap(ArrayList<Element> keyValues) {

        super(keyValues);
        ArrayList<Element> revelantKeyValues = new ArrayList<>();
        for(Element element: keyValues){
            if(element.getKey().matches(regex)){

                if(!element.getKey().isBlank())
                    revelantKeyValues.add(element);
            }
        }
        setMyMap(revelantKeyValues);
    }
    @Override
    public void getFromFile(String fileName){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName+".ser"))) {
            ArrayList<Element> deserializedMap = (ArrayList<Element>) ois.readObject();
            ArrayList<Element> NormalizedMap = new ArrayList<>();
            for(Element el: deserializedMap){
                if(el.getKey().matches(regex))
                    NormalizedMap.add(el);
            }
            setMyMap(NormalizedMap);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String regex = "[a-z]{4}";
    public String getRegex() {
        return regex;
    }
    public void setRegex(String regex) {
        this.regex = regex;
    }

}
