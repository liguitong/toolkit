package codecheck;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LineParser {
    private static final Logger logger = LoggerFactory.getLogger(LineParser.class);
    private Map<Integer, String> lineMapper = new ConcurrentHashMap<>();

    public void parseFile(File fileToParse) {

        try {
            logger.info("The file to parse is: " + fileToParse.getCanonicalPath());
            FileReader fileReader =
                    new FileReader(fileToParse.getCanonicalFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int lineNum = 0;
            while (line != null) {
                lineMapper.put(++lineNum, StringUtils.trim(line));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSeparateLine(int lineNum) {
        String line = lineMapper.get(lineNum);
        if (hasNoHeader(line) || hasNoEnd(line)) {
            return false;
        }
        return true;
    }

    private boolean hasNoEnd(String line) {
        return !line.endsWith(";") && !line.endsWith("{") && !line.endsWith("}");
    }

    private boolean hasNoHeader(String line) {
        return line.startsWith(".") || line.startsWith("+") || line.startsWith("\"");
    }

    private boolean isComment(String line) {
        if (StringUtils.startsWith(line, "/*") || StringUtils.startsWith(line, "*") ||
                StringUtils.startsWith(line, "//")) {
            return true;
        }
        return false;
    }

    public void joinLines() {
        lineMapper.forEach((lineNum, line) -> {
            if (hasNoEnd(line)) {
                line += lineMapper.get(lineNum + 1);
                lineMapper.put(lineNum + 1, line);
                lineMapper.remove(lineNum);
            }
        });
        lineMapper.forEach((lineNum,line)->{
            if(isComment(line)){
                lineMapper.remove(lineNum);
            }
        });
    }

    public void parseResult() {
        lineMapper.forEach((lineNum, line) -> {
                    System.out.print(lineNum);
                    System.out.print(":\t");
                    System.out.println(line);
                }
        );
    }
}
