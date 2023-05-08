import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCommander {
    private Path path;

    public FileCommander() {
        this.path=Path.of(System.getProperty("user.home"));
    }
    public String pwd(){
        return path.toString();
    }
    public void cd(String path){
        this.path = this.path.resolve(path);
    }
    public List<String> ls(){
        try {
            Stream<Path> stream = Files.list(this.path);
            return stream.sorted((p1,p2) -> p1.compareTo(p2))
                    .sorted((p1,p2) -> Boolean.compare(Files.isDirectory(p2),Files.isDirectory(p1)))
                    .map((p) -> {
                        if(Files.isDirectory(p)){
                            return "["+p.getFileName().toString()+"]";
                        }
                        else{
                            return p.getFileName().toString();
                        }
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
