package core.operations;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileOperations {

    public static boolean isFileExists(String filename) {
        if (filename == null) {
            return false;
        }
        return Files.exists(new File(filename).toPath());
    }

    public static void createFile(String file, String content) throws Exception {
        File f = new File(file);
        f.getParentFile().mkdirs();
        f.createNewFile();
        if (content != null) {
           updateFile(file, content);
        }
    }

    public static String readFile(String file) throws Exception {
        return new String(Files.readAllBytes(new File(file).toPath()));
    }

    public static void updateFile(String file, String content) throws Exception {
        Files.write(new File(file).toPath(), content.getBytes());
    }

    public static void deleteFile(String file) throws Exception {
        Files.delete(new File(file).toPath());
    }

    public static void copyFile(String sourceFile, String targetFile) throws Exception {
        Files.copy(new File(sourceFile).toPath(), new File(targetFile).toPath());
    }

    public static void moveFile(String sourceFile, String targetFile) throws Exception {
        Files.move(new File(sourceFile).toPath(), new File(targetFile).toPath());
    }

    public static ArrayList<String> getFilesList(String rootName, String patternString, boolean recursive) {
        ArrayList<String> filesList = new ArrayList<String>();
        File rootFile = new File(rootName);
        File[] subFiles = rootFile.listFiles();
        if (subFiles == null) {
            return filesList;
        }
        for (File subFile : subFiles) {
            if (subFile.isDirectory() && recursive) {
                filesList.addAll(getFilesList(subFile.getAbsolutePath(), patternString, true));
            } else {
                filesList.add(subFile.getAbsolutePath());
            }
        }
        return filesList;
    }
}
