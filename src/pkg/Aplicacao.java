/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jfree.data.xy.XYDataset;


public class Aplicacao {

    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Informe sua variavel: ");
        String var = scan.readLine();

        System.out.print("Infome f(" + var + "): ");
        String funcao = scan.readLine();

        System.out.println("=============== Você entrou: f(" + var + ") = " + funcao);

        Double[] resultados = new Double[11];

        
        for (int i = -5; i <= 5; i++) 
        {
            String y = funcao.replace(var, "" + i);
            double resultado = Calculadora.EfetuarOperacoes(y);
            
            resultados[i+5] = resultado;
        }
        
        //Cria um data set
        XYDataset dataset = Calculadora.createDataset(resultados);

        //plota o grafico baseado no dataset e salva na raiz do HD        
        Calculadora.Export(Calculadora.plot(dataset));

    }
}
