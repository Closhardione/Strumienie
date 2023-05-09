import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
    public List<String> ls(Function<String, String> decorator){
        try {
            Stream<Path> stream = Files.list(this.path);
            return stream.sorted((p1,p2) -> p1.compareTo(p2))
                    .sorted((p1,p2) -> Boolean.compare(Files.isDirectory(p2),Files.isDirectory(p1)))
                    .map((p) -> {
//                        if(filter!=null){
//                            if(p.toString().contains(filter)&& decorator.equals(FileCommander::decoratorBracets)){
//                                return decorator.apply(p.getFileName().toString());
//                            }
//                        }
                        if(Files.isDirectory(p)){
                            return decorator.apply(p.getFileName().toString());
                        }
                        else{
                            return p.getFileName().toString();
                        }
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static String decoratorBracets(String directory){
        return "["+directory+"]";
    }
    protected static String decoratorColor(String directory){
        return ConsoleColors.BLUE+directory+ConsoleColors.BLUE;
    }
    public List<String> find(String pattern){
        try {
            Stream<Path> stream = Files.walk(this.path);
            return stream.filter((p) -> p.toString().contains(pattern))
                    .map((p) -> p.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
