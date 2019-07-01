package algorithm;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * User: Administrator
 * Date: 2019-07-01
 * Time: 13:40
 */
public class Question2 {

    public String replaceSpace(StringBuffer str) {
        return str.toString().replace(" ","%20");
    }
}
