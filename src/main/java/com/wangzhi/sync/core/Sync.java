package com.wangzhi.sync.core;

import com.wangzhi.sync.util.CmdExec;
import com.wangzhi.sync.util.MkDir;

import java.util.Set;

public class Sync extends Check {

    private String target;

    public Sync(String sourcePath,String targetPath) {
        super(sourcePath);
        target = targetPath;
        MkDir.mkDir(target);
        CmdExec.sudoExec("cp -r -u "+sourcePath + "/. "+ target);
    }

    @Override
    public void deleteAction(String filePath) {
        if (!(filePath.endsWith(".") || filePath.endsWith("/"))) {
            CmdExec.sudoExec("rm "+target + '/' + filePath);
        }
    }

    @Override
    public void addAction(String filePath) {
        StringBuffer stringBuffer = new StringBuffer(filePath);
        stringBuffer.reverse();
        if (stringBuffer.indexOf("/") != -1) {
            stringBuffer.delete(0,stringBuffer.indexOf("/")+1);
            stringBuffer.reverse();
            //System.out.println(stringBuffer);
            MkDir.mkDir(target + "/"+stringBuffer.toString());
        }

        CmdExec.sudoExec("cp "+ getSource()+ "/" + filePath + " " + target + "/" + filePath);
    }

    @Override
    public void updateAction(String filePath) {
        CmdExec.sudoExec("cp "+ getSource()+ "/" + filePath + " " + target + "/" + filePath);
    }
}
