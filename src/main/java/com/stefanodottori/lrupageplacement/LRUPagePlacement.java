package com.stefanodottori.lrupageplacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author stefa
 */
public class LRUPagePlacement {

    static int FaltaDePagina(int paginas[], int p, int capacidade) {

        HashSet<Integer> t = new HashSet<>(capacidade);

        HashMap<Integer, Integer> indices = new HashMap<>();

        int faltaPaginas = 0;
        for (int i = 0; i < p; i++) {

            if (t.size() < capacidade) {
                if (!t.contains(paginas[i])) {
                    t.add(paginas[i]);

                    faltaPaginas++;
                }

                indices.put(paginas[i], i);
            } else {
               
              
                
                if (!t.contains(paginas[i])) {
                    int URU = Integer.MAX_VALUE, valor = Integer.MIN_VALUE;

                    Iterator<Integer> intera = t.iterator();

                    while (intera.hasNext()) {
                        int aux = intera.next();
                        if (indices.get(aux) < URU) {
                            URU = indices.get(aux);
                            valor = aux;
                        }
                    }
                    t.remove(valor);

                    indices.remove(valor);

                    t.add(paginas[i]);

                    faltaPaginas++;
                }

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
