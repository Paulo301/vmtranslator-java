package br.ufma.ecp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
 //

 private static String fromFile(File file) {        

    byte[] bytes;
    try {
        bytes = Files.readAllBytes(file.toPath());
        String textoDoArquivo = new String(bytes, "UTF-8");
        return textoDoArquivo;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "";
} 

    public static void main( String[] args )
    {
        String fname = "/home/sergio/Google Drive/Arquivados/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.vm";
        File file = new File(fname);

        String outputFileName = fname.substring(0, fname.lastIndexOf(".")) + ".asm";

        String input = fromFile(file);

        CodeWriter code = new CodeWriter(outputFileName);
        Parser p = new Parser(input);
        while (p.hasMoreCommands()) {
            var command = p.nextCommand();
            switch (command.type) {

                case ADD:
                    code.writeArithmeticAdd();
                    break;

                case SUB:
                    code.writeArithmeticSub();
                    break;

                case PUSH:
                    code.writePush(command.args.get(0), Integer.parseInt(command.args.get(1)));
                    break;
                
                case POP:
                    code.writePush(command.args.get(0), Integer.parseInt(command.args.get(1)));
                    break;

                default:
                    System.out.println(command.type.toString()+" not implemented");
            }

    
        } 
        
        System.out.println(code.codeOutput());
        code.save();
        //CodeWriter code = new CodeWriter();
        //code.setFileName("/home/sergio/Google Drive/Arquivados/nand2tetris/projects/07/StackArithmetic/SimpleAdd/Add.vm");

      
        

    

    }
}