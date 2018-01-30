import com.wangzhi.sync.core.Check;

import java.io.IOException;
import java.util.Vector;

public class SyncTest {
    public static void main(String[] args) {
        Vector<String> file = new Vector<>();
        try {
            Check.getFiles(file,"/home/wangzhi/code/","");
            for (String f:file) {
                System.out.println(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
