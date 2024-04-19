import java.util.ArrayList;

public class MyWordMap extends MyMap {


    MyWordMap(ArrayList<Element> keyValues) {

        super(keyValues);
        ArrayList<Element> revelantKeyValues = new ArrayList<>();
        for(Element element: keyValues){
            if(element.getKey().matches(regex)){
                boolean isBlank = true;
                for (String value : element.getValues()) {
                    if (!value.isBlank())
                        isBlank = false;
                }
                if (!isBlank)
                    revelantKeyValues.add(element);
            }
        }
        setMyMap(revelantKeyValues);
    }
    private String regex = "[a-zA-Z]{4}";
    public String getRegex() {
        return regex;
    }
    public void setRegex(String regex) {
        this.regex = regex;
    }
}
