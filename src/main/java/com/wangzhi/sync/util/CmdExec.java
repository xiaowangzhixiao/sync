package com.wangzhi.sync.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CmdExec {
    public static Object exec(String cmd) {
        try {
            String[] cmdA = { "/bin/sh", "-c", cmd };
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String sudoCmd = "echo \"w1a2n3g4z5h6i7\" | sudo -S ";

    public static Object sudoExec(String cmd) {
        return exec(sudoCmd + cmd);
    }
}
