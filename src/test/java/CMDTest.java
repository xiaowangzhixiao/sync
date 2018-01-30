import com.wangzhi.sync.util.CmdExec;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * java在linux环境下执行linux命令，然后返回命令返回值。
 * @author lee
 */
public class CMDTest {

    private static String sudoCmd = "echo \"w1a2n3g4z5h6i7\" | sudo -S ";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String result = CmdExec.exec(sudoCmd + "mount -t cifs -o username=pi,password=\"w1a2n3g4z5h6i7\"  //10.201.6.133/share /home/wangzhi/share").toString();

        System.out.println("==========获得值=============");
        System.out.println(result);
        if(result.equals("")) {
            System.out.println("挂载成功");
        }

        result = CmdExec.exec("ls /home/wangzhi/share").toString();
        System.out.println(result);

        result = CmdExec.exec(sudoCmd + "umount /home/wangzhi/share").toString();
        if(result.equals("")) {
            System.out.println("卸载成功");
        } else {
            System.out.println(result);
        }

    }

}
