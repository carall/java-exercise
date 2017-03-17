import java.io.*;
/**

 * Created by song on 17-2-14.
 */
public class CodesStatistic {
    static int totalCodeLine, totalCommentLine, totalEmptyLine;
    public static void main(String[] args) {
        String rootFolder = "/home/song/codes/java-exercise/src";
        if (args.length != 0) {
            rootFolder = args[0];
        }
        File root = new File(rootFolder);
        folderParse(root);
        System.out.println(root.getPath());
        System.out.println("code lines : " + totalCodeLine);
        System.out.println("comment lines : " + totalCommentLine);
        System.out.println("empty lines : " + totalEmptyLine);
    }

    private static void folderParse (File root) {
        if (root.isDirectory()) {
            File[] childFiles = root.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                folderParse(childFiles[i]);
            }
        } else if (root.isFile()){
            fileParse(root);
        }
    }

    private static void fileParse(File file) {
        int codeLine = 0, commentLine = 0, emptyLine = 0;
        boolean isComment = false;
        if (file.toString().endsWith(".java")) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                //readLine() will cut the '\n' '\r' character
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.matches("^\\s*$")) { // empty line
                        emptyLine ++;
                    } else if (line.matches("^//.*$") || line.matches("^/\\*.*\\*/$")) {
                        commentLine ++;
                    } else if (line.matches("^/\\*.*$")) {
                        isComment = true;
                        commentLine ++;
                    } else if (line.matches("^.*\\*/$") && isComment) {
                        isComment = false;
                        commentLine ++;
                    } else if (isComment) {
                        commentLine ++;
                    } else {
                        codeLine ++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(file.getPath());
            System.out.println("code lines : " + codeLine);
            System.out.println("comment lines : " + commentLine);
            System.out.println("empty lines : " + emptyLine);
            totalCodeLine += codeLine;
            totalCommentLine += commentLine;
            totalEmptyLine += emptyLine;
        }
    }
}
