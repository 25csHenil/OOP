import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryTreeWalker {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get(".");
        System.out.println("Walking Directory Tree via java.nio.file.Files.walkFileTree:");

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".java")) {
                    System.out.printf("  [FILE] %-30s | Size: %6d bytes | Last Modified: %s\n",
                        file.getFileName(), attrs.size(), attrs.lastModifiedTime());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
