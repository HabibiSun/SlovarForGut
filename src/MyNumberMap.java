import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MyNumberMap extends MyMap {

    private String regex = "[0-9]{5}";

    MyNumberMap() {
    }

    MyNumberMap(ArrayList<Element> keyValues) {
        super(keyValues);
        ArrayList<Element> revelantKeyValues = new ArrayList<>();
        for (Element element : keyValues) {
            if (element.getKey().matches(regex)) {
//                boolean isBlank = true;
//                for (String value : element.getValues()) {
//                    if (!value.isBlank())
//                        isBlank = false;
//                }
//                if (!isBlank)
//                    revelantKeyValues.add(element);
                if (!element.getKey().isBlank())
                    revelantKeyValues.add(element);
            }
        }
        setMyMap(revelantKeyValues);
    }

    @Override
    public void getFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".ser"))) {
            ArrayList<Element> deserializedMap = (ArrayList<Element>) ois.readObject();
            ArrayList<Element> NormalizedMap = new ArrayList<>();
            for (Element el : deserializedMap) {
                if (el.getKey().matches(regex))
                    NormalizedMap.add(el);
            }
            setMyMap(NormalizedMap);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }


}
