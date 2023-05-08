import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class FileCommanderCLI {
    private BufferedReader reader;
    private BufferedWriter writer;
    private FileCommander fileCommander;

    public FileCommanderCLI(InputStream reader, OutputStream writer, FileCommander fileCommander) {
        this.reader = new BufferedReader(new InputStreamReader(reader));
        this.writer = new BufferedWriter(new OutputStreamWriter(writer));
        this.fileCommander = fileCommander;
    }
    public void runCommand (String command) throws IOException {
       String[] arguments = command.split(" ");
       switch(command){
           case "pwd" -> {
               writer.write(fileCommander.pwd());
           }
           case "cd" -> {
               fileCommander.cd(arguments[1]);
           }
           case "ls" -> {
               if(arguments[1]=="--color"){
                   Object[] output = fileCommander.ls(FileCommander::decoratorColor).toArray();
                   for(Object temp : output){
                       System.out.println(temp+"\n");
                   }
               }
               else if(arguments[1].contains("--filter=")){
                   String[] deepArgument = arguments[1].split("=");
               }
               else {
                   Object[] output = fileCommander.ls(FileCommander::decoratorBracets).toArray();
                   for (Object temp : output) {
                       System.out.println(temp + "\n");
                   }
               }
           }
           case "find" -> {
               fileCommander.find(arguments[1]);
           }
           default -> {
               writer.write("Nieznane polecenie");
           }
       }
    }

}
