package src;

class Program {
    public static void main(String[] args) {
        //Matriks M = new Matriks();
        //Matriks M2 = new Matriks();

        //M.BacaMatriksTxt("src/gauss_test.txt");
        //M2.BacaMatriksTxt("src/test.txt");
        // M.BacaMatriks();
        //System.out.println("Matriks yang anda masukkan: ");
        //M.TulisMatriks();
        //System.out.println();

        // DEBUG SPL
        SPL M3 = new SPL();
        M3.BacaMatriksTxt("src/spl_test.txt");
        System.out.println("Matriks yang anda masukkan: ");
        M3.TulisMatriks();

        M3.menuSPL(4);

        //System.out.println("Apakah ada solusi? " + M3.isSolutionExist());
        //System.out.println("Apakah banyak solusi? " + M3.isManySolution());
        //System.out.println("Apakah solusi tunggal? " + M3.isSingleSolution());  
        
        //M3.Cramer();

        /* DEBUG METODE MATRIKS
        System.out.println();
        Matriks MKoef = M3.Koefisien();
        MKoef.TulisMatriks();

        System.out.println();
        Matriks MKons = M3.Konstanta();
        MKons.TulisMatriks();

        System.out.println();
        Matriks MBalikan = M3.BuatMatriksBalikan();
        MBalikan.TulisMatriks();

        System.out.println();
        Matriks MKali = M3.KaliMatriks(MBalikan);
        MKali.TulisMatriks();
        System.out.println();
        */

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