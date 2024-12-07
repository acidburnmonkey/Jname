// https://github.com/acidburnmonkey/Jname

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    // HELP Switch
    public static void help(){
        String documentation = """
            Jname , rename files in place.
            
            Usage:
            jname /path/to/file newName
            
            Options:
              -h      Display this help message.
              -v      Display verbose.
            """;
        System.out.println(documentation);
    }


    // Checks for switches -v or -h
    public static boolean switches(String zero){
        if (zero.equals("-v")){
            return true;
        }
        else if (zero.equals("-h")) {
            help();
        return false;
        }
        else{
            return false;
        }
    }


    // Does the renaming
    public static void renamingOperation(String[] args){

        Scanner kb = new Scanner(System.in);
        char input;

        // ORIGINAL FILE
        String file1 = args[0].trim();
        File OriginalFile = new File(file1);
        // Builds the parent path
        Path path = Paths.get(file1);
        path = path.getParent();

        // RENAME To
        var user = path.resolve(args[1].trim());
        File newName = new File(user.toString());


        //rename operation
        if (OriginalFile.exists() & OriginalFile.isFile()) {
            if (newName.exists()){
                System.out.print("File already exist , overwrite? y/n  ");
                input = kb.next().charAt(0);
                if (input != 'y'){
                    return;
                }
            }

            OriginalFile.renameTo(newName);
            if (globals.verbose){
                System.out.println(OriginalFile.getPath() +" -> "+ newName.getPath());
            }

        }
        else if (!OriginalFile.exists()) {
            System.out.println("File does not exist");
        }

        //checks for directory !NOT IMPLEMENTED 🤡
        /*else if (OriginalFile.exists() & OriginalFile.isDirectory()) {
            System.out.println(" Directory exist");

            // RENAME To
            var user = path.resolve("old");
            File file2 = new File(user.toString());
            OriginalFile.renameTo(file2);
        } else {
            System.out.println("File not Found");
        }*/
    }


 // MAIN
    public static void main(String[] args) {

        String[] aggs = new String[2];

        if (args.length == 0 | args.length > 3) {
            help();
            return;
        }

        //Checks for -v , false by default
        globals.verbose = switches(args[0]);

       try {
           if (!globals.verbose) {
               renamingOperation(args);

           }//end if
           else {
               //copy args and pass it 1:2
               aggs[0]= args[1] ;
               aggs[1]= args[2] ;
               renamingOperation(aggs);

           }

       }
       catch (Exception e){
           System.out.println("Something crashed :" + e.getMessage());
       }

    }
}