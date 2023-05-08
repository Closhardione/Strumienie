import java.nio.file.Path;

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
}
