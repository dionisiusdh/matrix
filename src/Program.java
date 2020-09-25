package src;

import java.util.*;

class Program {
    public static void main(String[] args) {
        System.out.println("Run"); 
        Matriks M = new Matriks();
        M.BacaMatriksTxt();
        // M.BacaMatriks();
        M.TulisMatriks();

        //Prekondisi M adalah square matrix
        float det = M.DeterminanOBE();
        System.out.println(det);
    }
}