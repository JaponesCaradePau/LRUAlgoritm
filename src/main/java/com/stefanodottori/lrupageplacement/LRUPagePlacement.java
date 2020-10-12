package com.stefanodottori.lrupageplacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Stefano Dottori - RA:T530IF4
 */
public class LRUPagePlacement {

    // Método para achar as faltas de páginas utilizando indices
    static int FaltaDePagina(int paginas[], int p, int capacidade) {

        /*
        Para representar um conjunto de páginas atuais, 
        Vamos utilizar um conjunto não ordenado
        Assim, vamos verificar se a página existe no conjunto ou não
        */
        HashSet<Integer> t = new HashSet<>(capacidade);
        
        /* Para armazenar os índices de páginas que foram menos utilizados 
           Recentemente
        */
        HashMap<Integer, Integer> indices = new HashMap<>();
        
        //Começa pela página inicial
        int faltaPaginas = 0;
        for (int i = 0; i < p; i++) {
            
            //Verifica se o conjunto pode conter mais páginas
            if (t.size() < capacidade) {
                /*Insere a página não conjunto se não existir já que 
                iria representar uma falta de página
                */
                if (!t.contains(paginas[i])) {
                    t.add(paginas[i]);
                    
                    //Incrementa o contador de falta de página
                    faltaPaginas++;
                }
                
                //Armazena o indice mais recentemente utilizada de cada página
                indices.put(paginas[i], i);
            /*
                Se o conjunto estiver cheio,
                então rodamos o algoritmo URU para remover a página menos 
                utilizada e inserir a página atual
                */
            } else {
               
              
                // Verifica se a página atual já não está presente no conjunto 
                if (!t.contains(paginas[i])) {
                    
                    /*
                    Encontra as páginas que foram menos utilizadas 
                    que estão presente no conjunto
                    */
                    int URU = Integer.MAX_VALUE, valor = Integer.MIN_VALUE;

                    Iterator<Integer> intera = t.iterator();

                    while (intera.hasNext()) {
                        int aux = intera.next();
                        if (indices.get(aux) < URU) {
                            URU = indices.get(aux);
                            valor = aux;
                        }
                    }
                    // Remove o índice da página
                    t.remove(valor);
                    
                    //Remove o URU do HashMap
                    indices.remove(valor);
                    
                    //Insere a página atual
                    t.add(paginas[i]);
                    
                    //Incrementa novamente o contador de falta de página
                    faltaPaginas++;
                }
                //Atualiza os indice da página atual
                indices.put(paginas[i], i);

            }

        }

        return faltaPaginas;
    }

    public static void main(String[] args) {
        int paginas[] = {9, 3, 5, 4, 3, 7, 4, 6, 5, 6, 9, 9, 2};

        int capacidade = 4;

        JOptionPane.showMessageDialog(null, FaltaDePagina(paginas, paginas.length, capacidade));
       
    }
}
