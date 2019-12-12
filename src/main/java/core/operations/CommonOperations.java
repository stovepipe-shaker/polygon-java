package core.operations;

public class CommonOperations {

    public static boolean isFileContainText(String file, String text) throws Exception {
        String content = FileOperations.readFile(file);
        return content.contains(text);
    }

    public static void replaceTextInFile(String file, String sourceText, String targetText) throws Exception {
        String content = FileOperations.readFile(file);
        content = StringOperations.replaceAllMatchesOfString(content, sourceText, targetText);
        FileOperations.updateFile(file, content);
    }

}
