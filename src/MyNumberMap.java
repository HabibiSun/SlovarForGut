import java.util.ArrayList;

public class MyNumberMap extends MyMap
{

    private String regex = "[0-9]{5}";

    MyNumberMap(ArrayList<Element> keyValues) {
        super(keyValues);
        ArrayList<Element> revelantKeyValues = new ArrayList<>();
        for(Element element: keyValues){
            if(element.getKey().matches(regex)){

                for (String value : element.getValues()) {
                    if (!value.isBlank())
                        revelantKeyValues.add(element);
                }
            }
        }
        setMyMap(revelantKeyValues);
    }
    public String getRegex() {
        return regex;
    }
    public void setRegex(String regex) {
        this.regex = regex;
    }



}
