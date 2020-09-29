package src;

class Program {
    public static void main(String[] args) {
        Matriks M = new Matriks();
        Matriks M2 = new Matriks();

        M.BacaMatriksTxt("src/gauss_test.txt");
        M2.BacaMatriksTxt("src/test.txt");
        // M.BacaMatriks();
        System.out.println("Matriks yang anda masukkan: ");
        M.TulisMatriks();
        System.out.println();

        // DEBUG SPL
        SPL M3 = new SPL();
        M3.BacaMatriksTxt("src/spl_test.txt");
        
        M3.splGaussJordan();


        M3.Cramer();

        //System.out.println("Apakah ada solusi? " + M3.isSolutionExist());
        //System.out.println("Apakah banyak solusi? " + M3.isManySolution());
        //System.out.println("Apakah solusi tunggal? " + M3.isSingleSolution());  
    
        /* DEBUG DETERMINAN
        //Prekondisi M adalah square matrix
        float det = M.DeterminanOBE();
        System.out.println("Determinan matriks OBE: " + det);
        System.out.println();

        float det2 = M2.DeterminanKofaktor();
        System.out.println("Determinan matriks Kofaktor: " + det2);
        */

        /*DEBUG INVERSE
        // Matriks Balikan
        Matriks M3 = new Matriks();
        M3.BacaMatriksTxt("src/inverse_test.txt");

        System.out.println("\n Matriks balikan: ");
        Matriks M3Balikan = M3.BuatMatriksBalikan();
        M3Balikan.TulisMatriks();
        */
    }
}