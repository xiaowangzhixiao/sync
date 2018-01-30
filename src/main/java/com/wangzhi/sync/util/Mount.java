package com.wangzhi.sync.util;

public class Mount {
    public static boolean mount(String url, String userName, String password) {
        String dir = url;
        dir = dir.substring(1);
        System.out.println(dir);

        MkDir.mkDir("/mnt" + dir);
        String result = CmdExec.sudoExec("mount -t cifs -o username=" +userName + ",password=\"" + password + "\" "+url+" /mnt"+dir).toString();
        return result.equals("");
    }

    public static boolean umount(String url) {
        url = url.substring(1);
        String result = CmdExec.sudoExec("umount /mnt"+url).toString();
        System.out.println(result);
        return result.equals("");
    }

}
