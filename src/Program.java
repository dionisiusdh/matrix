package src;

class Program {
    public static void main(String[] args) {
        Matriks M = new Matriks();
        Matriks M2 = new Matriks();

        M.BacaMatriksTxt("src/test.txt");
        M2.BacaMatriksTxt("src/test.txt");
        // M.BacaMatriks();
        System.out.println("Matriks yang anda masukkan: ");
        M.TulisMatriks();
        System.out.println();

        //Prekondisi M adalah square matrix
        float det = M.DeterminanOBE();
        System.out.println("Determinan matriks OBE: " + det);
        System.out.println();

        float det2 = M2.DeterminanKofaktor();
        System.out.println("Determinan matriks Kofaktor: " + det2);

        // Matriks Balikan
        Matriks M3 = new Matriks();
        M3.BacaMatriksTxt("src/inverse_test.txt");

        System.out.println("\n Matriks balikan: ");
        Matriks M3Balikan = M3.BuatMatriksBalikan();
        M3Balikan.TulisMatriks();

        //System.out.println();
        //M3Balikan.TulisMatriks();

        //System.out.println();
        //M3.EliminasiGaussV3();
        //M3.TulisMatriks();

        //System.out.println();
        //M3.EliminasiGaussJordan();
        //M3.TulisMatriks();

        //M3Balikan.save();

    }
}