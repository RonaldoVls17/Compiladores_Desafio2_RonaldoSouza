/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validarlinhasdecodigo_ronaldosouza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class ValidarLinhasDeCodigo_RonaldoSouza {

    public static void main(String[] args) {
        
        String expr = "";
        String validado = "";


        File arquivo = new File("./src/Strings.txt" );
        if (arquivo.exists()) {
            String dadosConta[] = new String[1];
            try {
                BufferedReader in = new BufferedReader(new FileReader(arquivo));
                FileWriter fw = new FileWriter("./src/Resultados.txt");
                while (in.ready()) {
                    
                    dadosConta = (in.readLine().split(Pattern.quote("\n")));
                    expr = dadosConta[0]; // linha a linha do txt    
                    if (validação(expr)){
                        System.out.println("Valido ");
                        validado += dadosConta[0] + " - " + "Valido" + "\n";
                    }                        
                    else{
                        System.out.println("Invalido");
                        validado += dadosConta[0] + " - " + "Invalido" + "\n";       
                    }                       
                }
                fw.write(validado);
                in.close();
                fw.close();
            } catch (IOException ex) {
           System.out.println("Erro na leitura do arquivo.");
        }
        } else {
            System.out.println("Erro na leitura do arquivo.");
        }   
    }
    
        static boolean validação(String expr)
    {
        Deque<Character> pilha = new ArrayDeque<Character>();
 
        for (int i = 0; i < expr.length(); i++)
        {
            char x = expr.charAt(i);
 
            if (x == '(' || x == '[' || x == '{')
            {
                pilha.push(x);
                continue;
            }
 
            if (pilha.isEmpty())
                return false;
            char caracter;
            switch (x) {
            case ')':
                caracter = pilha.pop();
                if (caracter == '{' || caracter == '[')
                    return false;
                break;

            case ']':
                caracter = pilha.pop();
                if (caracter == '(' || caracter == '{')
                    return false;
                break;
                
            case '}':
                caracter = pilha.pop();
                if (caracter == '(' || caracter == '[')
                    return false;
                break;
            }
        }
        return (pilha.isEmpty());
    }
    
}
