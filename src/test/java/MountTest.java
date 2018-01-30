import com.wangzhi.sync.util.Mount;

public class MountTest {
    public static void main(String[] args) {
        boolean result =  Mount.mount("//10.201.6.133/share","pi","w1a2n3g4z5h6i7");
        System.out.println(result);
    }
}
