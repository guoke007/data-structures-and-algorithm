package structures.map.test;


import structures.map.MyHashMap;
import structures.map.MyMap;

public class TestMyHashMap {
    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyHashMap<>();
        for (int i = 0; i <= 20; i++) {
            myMap.put("k" + i, "v" + i);
        }
        System.out.println(myMap.get("k20"));
        System.out.println(myMap.size());
    }
}
