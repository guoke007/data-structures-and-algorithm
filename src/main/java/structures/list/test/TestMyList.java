package structures.list.test;

import structures.list.MyArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2019/4/19.
 */
public class TestMyList {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(4);
        list.add(5);
        list.add(3,5);
        Iterator<Integer> iterator = list.iterator();
          while (iterator.hasNext()){
              Integer next = iterator.next();
              if(next == 4){
                  iterator.remove();
              }else {
                  System.out.println(next);
              }
        }
    }
}
