package codecheck;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Checker {
    public Map<String,String> files = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        String dir = args[0];
        File rootFile = new File(dir);
        String[] files = rootFile.list();
        System.out.println("A当前目录下共有" + files.length + "文件");
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}
