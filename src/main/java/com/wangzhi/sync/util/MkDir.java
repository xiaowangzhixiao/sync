package com.wangzhi.sync.util;

public class MkDir {
    public static void mkDir(String path) {
        String result = CmdExec.sudoExec("mkdir -p "+path).toString();
        System.out.println(result);
    }
}
