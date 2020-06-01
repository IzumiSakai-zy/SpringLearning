package zy.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CollectionInjection {
    private String[] stringArray;
    private List<String> list;
    private Map<Integer,String> map;
    private Properties properties;

    public void saveAccount(){
        System.out.println(Arrays.toString(stringArray));
        System.out.println(list);
        System.out.println(map);
        System.out.println(properties);
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
