//package src; // Kalo dipake jadi gabisa dicompile, comment dlu sebelum compile pakai javac

import java.util.*;
import java.io.*;

public class SPL extends Matriks {
    // Menyelesaikan sistem persamaan linear dengan berbagai metode
    String Solusi = "";
    
    /* ======================== MENU ======================== */
    public void menuSPL(int metode) {
        switch (metode) {
            case 1:
                System.out.println("Penyelesaian SPL menggunakan eliminasi Gauss: ");
                this.splGauss();
                this.save_solusi_spl("eliminasi Gauss:");
                break;
            case 2:
                System.out.println("Penyelesaian SPL menggunakan eliminasi Gauss-Jordan: ");
                this.splGaussJordan();
                this.save_solusi_spl("eliminasi Gauss-Jordan:");
                break;
            case 3:
                System.out.println("Penyelesaian SPL menggunakan matriks balikan: ");
                this.splMatriksBalikan();
                this.save_solusi_spl("matriks balikan:");
                break;
            case 4:
                System.out.println("Penyelesaian SPL menggunakan kaidah Cramer: ");
                this.splCramer();
                this.save_solusi_spl("kaidah Cramer:");
                break;
        }
    }

    public void save_solusi_spl(String metode) {
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Apakah Anda ingin menyimpan solusi? Y/N: ");
        char ans = scan1.next().charAt(0);
        if (ans=='Y' || ans=='y'){
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Masukkan nama file (tanpa ekstensi): ");
                String nama_file = scan.nextLine();
                
                nama_file += ".txt";
    
                FileOutputStream save_file = new FileOutputStream(("./test/" + nama_file));
                
                String Solusi_save = "Penyelesaian SPL menggunakan " + metode + "\n" + this.Solusi;
    
                byte value[] = Solusi_save.getBytes();
                save_file.write(value);
                
                // scan.close();
                save_file.close();
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* ======================== METODE PENYELESAIAN SPL ======================== */
    public void splGauss() {
        //Mencari solusi SPL menggunakan metode eliminasi Gauss
        //SPL dalam bentuk matriks augmented 
        SPL M1 = this;

        System.out.println("Setelah dilakukan eliminasi Gauss:");
        M1.EliminasiGauss();
        M1.TulisMatriks();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
            this.Solusi += "SPL tidak memiliki solusi.";
        } else if (M1.isSingleSolution()) {
            System.out.println("SPL memiliki solusi tunggal.\n");
            this.Solusi += "SPL memiliki solusi tunggal.\n";
            M1.EliminasiGauss();
            M1.solveSingleSolution();
        } else {
            System.out.println("SPL memiliki solusi banyak.\n");
            this.Solusi += "SPL memiliki solusi banyak.\n";
            M1.EliminasiGauss();
            M1.solveManySolution();
        }
    }

    public void splGaussJordan() {
        //Mencari solusi SPL menggunakan metode eliminasi Gauss Jordan
        //SPL dalam bentuk matriks augmented 
        SPL M1 = this;

        System.out.println("Setelah dilakukan eliminasi Gauss-Jordan:");
        M1.EliminasiGaussJordan();
        M1.TulisMatriks();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
            this.Solusi = "SPL tidak memiliki solusi.";
        } else if (M1.isSingleSolution()) {
            System.out.println("SPL memiliki solusi tunggal.\n");
            this.Solusi += "SPL memiliki solusi tunggal.\n";
            M1.EliminasiGaussJordan();
            M1.solveSingleSolution();
        } else {
            System.out.println("SPL memiliki solusi banyak.\n");
            this.Solusi += "SPL memiliki solusi banyak.\n";
            M1.EliminasiGaussJordan();
            M1.solveManySolution();
        }
    }

    public void splMatriksBalikan() {
        //Mencari solusi SPL menggunakan metode matriks balikan
        //SPL dalam bentuk matriks augmented 
        Matriks MKoef = this.Koefisien();
        Matriks MKons = this.Konstanta();

        if(!MKoef.IsPersegi()) {
            System.out.println("Matriks koefisien bukan matriks persegi, sehingga tidak dapat diselesaikan dengan metode matriks balikan.");
            this.Solusi = "Matriks koefisien bukan matriks persegi, sehingga tidak dapat diselesaikan dengan metode matriks balikan.";
        } else {
            float det = MKoef.DeterminanKofaktor();
            if (det == 0) {
                System.out.println("Determinan matriks koefisien bernilai 0, sehingga tidak dapat diselesaikan dengan metode matriks balikan.");
                this.Solusi = "Determinan matriks koefisien bernilai 0, sehingga tidak dapat diselesaikan dengan metode matriks balikan.";
            } else {
                Matriks MBalikan = MKoef.BuatMatriksBalikan();
                Matriks MHsl = MBalikan.KaliMatriks(MKons);

                for (int i = 0; i < MHsl.NBrsEff; i++) {
                    System.out.println("x" + (i+1) + " = " + MHsl.M[i][0]);
                    this.Solusi += "x" + (i+1) + " = " + MHsl.M[i][0] + "\n";
                }
            }
        }
    }

    public float[] splCramer(){
        //Mencari solusi SPL menggunakan metode cramer
        //SPL dalam bentuk matriks augmented 
        float [] solX = new float[(this.NKolEff) - 1];

        Matriks temp, temp1;
        float d,dx;
        temp = BuatMatriks(this.NBrsEff, this.NKolEff - 1);
        //mengisi temp dengan matriks koefisien variabel
        for (int i = 0 ; i<this.NBrsEff;i++){
            for (int j = 0; j<(this.NKolEff) -1 ; j++){
                temp.M[i][j] = this.M[i][j];
            }
        }
        
        d = temp.DeterminanOBE();
        if (d == 0){
            if (!this.isSolutionExist()) {
                System.out.println("SPL tidak memiliki solusi.");
                this.Solusi = "SPL tidak memiliki solusi.";
            } else {
                System.out.println("SPL memiliki banyak solusi. Untuk melihat solusi parametrik gunakan eliminasi Gauss atau Gauss-Jordan");
                this.Solusi = "SPL memiliki banyak solusi. Untuk melihat solusi parametrik gunakan eliminasi Gauss atau Gauss-Jordan";
            }
        }
        else{
            temp1 = BuatMatriks(this.NBrsEff, this.NKolEff - 1);
            for (int i = 0; i<temp1.NKolEff; i++){
                for (int j = 0; j<this.NBrsEff;j++){
                    for (int k = 0; k<temp1.NKolEff;k++){
                        if (k!=i){
                            temp1.M[j][k] = this.M[j][k];
                        }
                        else{
                            temp1.M[j][k] = this.M[j][this.NKolEff -1];
                        }
                    }
                }
                dx = temp1.DeterminanOBE();
                solX[i] = dx/d;
                int counter = i+1;
                System.out.println("x" + counter +" = " + solX[i]);
                this.Solusi += "x" + counter +" = " + solX[i] + "\n";
            }
        }
        return solX;
    }

    /* ======================== SOLVER & OUTPUT SOLUSI ======================== */
    public void solveSingleSolution() {
        SPL M1 = this;

        int counter = 1;

        for(int i=0; i < this.NBrsEff; i++) {
            System.out.println("x" + counter + " = " + M1.M[i][this.NKolEff-1]);
            this.Solusi += "x" + counter + " = " + M1.M[i][this.NKolEff-1];
            counter += 1;
            this.Solusi += "\n";
        } 
    }

    public void solveManySolution() {
        SPL M1 = this;

        // Cek variabel bebas dan leading one / satu utama yang ada pada matriks
        int [] varBebas = new int[M1.NKolEff];
        varBebas[0] = 0;

        for (int j = 0; j < M1.NKolEff; j++) {
            int count_varBebas = 0;
            boolean leadingOne = false;

            for (int i = 0; i < M1.NBrsEff; i++) {
                if (M1.M[i][j] != 0 && (M1.M[i][j] != 1)) {
                    count_varBebas += 1;
                }
                if (!leadingOne && M1.M[i][j] == 1) {
                    leadingOne = true;
                }
            }
            if (count_varBebas > 0 || (!leadingOne && count_varBebas == 0)) {
                varBebas[j] = 1; 
            } else {
                varBebas[j] = 0;
            }
        }

        // Memanipulasi matriks agar menjadi sebuah matriks persegi (jika bukan)
        /*while (M1.NBrsEff != M1.NKolEff - 1) {
            M1.NBrsEff += 1;
            for (int j = 0; j < M1.NKolEff - 1; j++) {
                M1.M[M1.NBrsEff-1][j] = 0;
            }
        } */

        // Mengurutkan matriks berdasarkan posisi variabel
        /*int [] IdxKolPivot = new int[M1.NBrsEff+1];
        for (int i = 0; i < M1.NBrsEff; i++) {
            IdxKolPivot[i] = M1.CariIdxKolPivot(i);
        } 
        for (int i = 0; i < IdxKolPivot.length-1; i++) {
            if (IdxKolPivot[i] != i && IdxKolPivot[i] != M1.IdxUndef) {
                int temp = IdxKolPivot[i];
                IdxKolPivot[i] = IdxKolPivot[i+1];
                IdxKolPivot[i+1] = temp;
                M1.swapBaris(i, i+1);
            }
        }

        for (int i=0; i<IdxKolPivot.length; i++)  {
            System.out.println(IdxKolPivot[i]);
        }*/

        // Output hasil dalam bentuk string
        String output = "";

        for (int i=0; i < M1.NBrsEff; i++) {
            boolean constant = false;

            if (i == M1.NBrsEff-1) {
                output += "x" + (i+1) + " = t, untuk t ∊ R ";
            } else {
                output += "x" + (i+1) + " = ";
            }

            if (M1.M[i][M1.NKolEff-1] != 0) {
                output += M1.M[i][M1.NKolEff-1] + " ";
                constant = true;
            }

            for (int j = CariIdxKolPivot(i)+1; j < M1.NKolEff - 1; j++) {
                if (constant && varBebas[j] == 1 && M1.M[i][j] != 0) {
                    if (M1.M[i][j] != 0) {
                        if (M1.M[i][j] != -1) {
                            output += " + (" + (-1)*M1.M[i][j] + ")x" + (j+1) + " ";
                        } else {
                            output += " + x" + (j) + " ";
                        }
                    }
                } else if (!constant && varBebas[j] == 1 && M1.M[i][j-1] != 0) {
                    if (M1.M[i][j] != 0) {
                        if (M1.M[i][j] != -1) {
                            output += "(" + (-1)*M1.M[i][j] + ")x" + (j+1) + " ";
                        } else {
                            output += " + x" + (j) + " ";
                        }
                    }
                }

                if (varBebas[j] == 1 && i == j) {
                    output += "x" + (j+1);
                }
            }
            output += '\n';
        }
        System.out.println(output);
        this.Solusi += output;
        output = "";
    }

    /* ======================== CEK SOLUSI ======================== */
    public boolean isSolutionExist() {
        // Mengecek apakah sebuah matriks SPL memiliki solusi
        boolean ada_solusi = true;

        int i = this.NBrsEff - 1;

        while(ada_solusi && i >= 0){
            int j = 0;
            boolean found = false;

            while(!found && j <= this.NKolEff) {
                if(this.M[i][j] != 0 && j != this.NKolEff - 1) {
                    found = true;
                }
                j += 1;
            }

            if(!found) {
                boolean all_zero_brs = false;

                for(int k = 0; k < this.NBrsEff; k++){
                    if(this.isAllZeroBrs(k)) {
                        all_zero_brs = true;
                    }
                }

                if(!all_zero_brs) {
                    ada_solusi = false;
                }
            }

            i -= 1;
        }

        return ada_solusi;
    }

    public boolean isManySolution() {
        // Cek apakah matriks memiliki banyak solusi (tak terhingga solusi)
        // Keadaan ini dicapai saat ada baris yang memenuhi isAllZeroBrs

        boolean all_zero_brs = false;

        for(int i = 0; i < this.NBrsEff; i++){
            if(this.isAllZeroBrs(i)) {
                all_zero_brs = true;
            }
        }

        return all_zero_brs;
    }

    public boolean isSingleSolution() {
        // Mengecek apakah sebuah matriks memiliki solusi tunggal
        boolean single_solution = false;

        if(!this.isManySolution() && this.isSolutionExist()) {
            single_solution = true;
        }

        return single_solution;
    }

    public float[] BackSubstitution(){
        // Backward Substition untuk Gaussian Elimination
        // Masih cuman work untuk yg solusi tunggal
        float[] solX = new float[this.NBrsEff];
        float temp;
        for (int i=this.NBrsEff-1; i>=0; i--){
            temp =  this.M[i][this.NKolEff-1];
            for (int j=this.NKolEff-2; j>i; j--){
                temp -= solX[j] * this.M[i][j];
            }
            solX[i] = temp/this.M[i][i];
            System.out.println("SolX: "+solX[i]);
        }
        return solX;
    }

    public void copySPLMatriks(SPL a, Matriks B){
        for (int i=0; i<B.NBrsEff; i++){
            for (int j=0; j<B.NKolEff; j++){
                a.M[i][j] = B.M[i][j];
            }
        }
        a.NBrsEff = B.NBrsEff;
        a.NKolEff = B.NKolEff;
    }
}
