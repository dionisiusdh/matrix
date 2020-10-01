package src; // Kalo dipake jadi gabisa dicompile, comment dlu sebelum compile pakai javac

public class SPL extends Matriks {
    // Menyelesaikan sistem persamaan linear dengan berbagai metode

    /* ======================== MENU ======================== */
    public void menuSPL(int metode) {
        switch (metode) {
            case 1:
                System.out.println("Penyelesaian SPL menggunakan eliminasi Gauss: ");
                this.splGauss();
                break;
            case 2:
                System.out.println("Penyelesaian SPL menggunakan eliminasi Gauss-Jordan: ");
                this.splGaussJordan();
                break;
            case 3:
                System.out.println("Penyelesaian SPL menggunakan matriks balikan: ");
                this.splMatriksBalikan();
                break;
            case 4:
                System.out.println("Penyelesaian SPL menggunakan kaidah Cramer: ");
                this.splCramer();
                break;
        }
    }

    /* ======================== METODE PENYELESAIAN ======================== */
    public void splGauss() {
        //Mencari solusi SPL menggunakan metode eliminasi Gauss
        //SPL dalam bentuk matriks augmented 
        SPL M1 = this;

        M1.EliminasiGauss();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            // Ada solusi
        }
    }

    public void splGaussJordan() {
        //Mencari solusi SPL menggunakan metode eliminasi Gauss Jordan
        //SPL dalam bentuk matriks augmented 
        SPL M1 = this;

        M1.EliminasiGaussJordan();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else if (M1.isSingleSolution()) {
            M1.EliminasiGaussJordan();
            
            int counter = 1;

            for(int i=0; i < this.NBrsEff; i++) {
                System.out.println("x" + counter + " = " + M1.M[i][this.NKolEff-1]);
                counter += 1;
            } 
        } else {
            /* // ======================================== CARA 1 ========================================
            int [] varBebas = new int[M1.NBrsEff+1];
            boolean leadingOne = false;
            int i = 0;
            int j = 0;
            int var_idx = 0;
            char [] var_list = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

            String [] VarSolusi = new String [100];
            String [] Solusi = new String [100];
            char var = 'a';

            while (j < M1.NKolEff) {
                
                leadingOne = false;

                if (M1.M[i][j] == 1 && !leadingOne) {
                    leadingOne = true;
                    varBebas[i] = j;

                    if (i < M1.NBrsEff) {
                        i += 1;
                    }
                } else {
                    VarSolusi[j] = Character.toString(var);
                    var_idx += 1;
                    var = var_list[var_idx];
                }
                j += 1;
            }

            int z = 0;

            for (int k = 0; k < M1.NBrsEff; k++) {
                String hasil = "";
                for (int l = M1.NKolEff - 1; l > varBebas[k]; l--) {
                    if (M1.M[k][l] > 0 && M1.M[k][l] != 0 && l != M1.NKolEff - l){
                        hasil += " + " + Math.abs(M1.M[k][l]) + VarSolusi[l];
                    }
                    if (M1.M[k][l] > 0 && M1.M[k][l] != 0 && l == M1.NKolEff - l){
                        hasil += " + " + Math.abs(M1.M[k][l]);
                    }
                    if (M1.M[k][l] < 0 && M1.M[k][l] != 0 && l != M1.NKolEff - l) {
                        hasil += " - " + Math.abs(M1.M[k][l]) + VarSolusi[l];
                    }
                    if (M1.M[k][l] < 0 && M1.M[k][l] != 0 && l == M1.NKolEff - l) {
                        hasil += " - " + Math.abs(M1.M[k][l]) + VarSolusi[l];
                    }
                    VarSolusi[varBebas[k]] = M1.M[k][M1.NKolEff] + hasil;
                }
                Solusi[z] = M1.M[k][M1.NKolEff] + hasil;
                z += 1;
            }
            
            
            for (int m = 0; m < M1.NBrsEff; m++) {
                String s = "x" + (m+1) + " = " + Solusi[m];
                System.out.println(s + "\n");
            }
            */

            // ======================================== CARA 2 ========================================
            // Cek variabel bebas dan leading one / satu utama yang ada pada matriks
            int [] varBebas = new int[M1.NKolEff];
            varBebas[0] = 0;

            for (int j = 0; j < M1.NKolEff; j++) {
                int count_varBebas = 0;
                boolean leadingOne = false;

                for (int i = 0; i < M1.NBrsEff; i++) {
                    if (M1.M[i][j] != 0 && M1.M[i][j] != 1) {
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
            while (!M1.Koefisien().IsPersegi()) {
                M1.NBrsEff += 1;
                for (int j = 0; j < M1.NKolEff; j++) {
                    M1.M[M1.NBrsEff=1][j] = 0;
                }
            }

            // Mengurutkan matriks berdasarkan posisi variabel
            int [] IdxKolPivot = new int[M1.NBrsEff+1];
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

            for (int i = 0; i < M1.NKolEff; i++) {
                System.out.println(varBebas[i]);
            }
            System.out.println();
            // Output hasil dalam bentuk string
            String output = "";

            for (int i=0; i < M1.NBrsEff; i++) {
                boolean constant = false;

                if (i == M1.NBrsEff-1) {
                    output += "x" + (i+1) + " = k, untuk k ∊ R ";
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
                                output += " +x" + (j+1) + " ";
                            }
                        }
                    } else if (!constant && varBebas[j] == 1 && M1.M[i][j-1] != 0) {
                        if (M1.M[i][j] != 0) {
                            if (M1.M[i][j] != -1) {
                                output += "(" + (-1)*M1.M[i][j] + ")x" + (j+1) + " ";
                            } else {
                                output += " x" + (j+1) + " ";
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
            output = "";
            
        }
    }

    public void splMatriksBalikan() {
        //Mencari solusi SPL menggunakan metode matriks balikan
        //SPL dalam bentuk matriks augmented 
        Matriks MKoef = this.Koefisien();
        Matriks MKons = this.Konstanta();

        if(!MKoef.IsPersegi()) {
            System.out.println("Matriks koefisien bukan matriks persegi, sehingga tidak dapat diselesaikan dengan metode matriks balikan.");
        } else {
            float det = MKoef.DeterminanKofaktor();
            if (det == 0) {
                System.out.println("Determinan matriks koefisien bernilai 0, sehingga tidak dapat diselesaikan dengan metode matriks balikan.");
            } else {
                Matriks MBalikan = MKoef.BuatMatriksBalikan();
                Matriks MHsl = MBalikan.KaliMatriks(MKons);

                for (int i = 0; i < MHsl.NBrsEff; i++) {
                    System.out.println("x" + (i+1) + " = " + MHsl.M[i][0]);
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
        
        d = temp.DeterminanKofaktor();
        if (d == 0){
            if (!this.isSolutionExist()) {
                System.out.println("Solusi tidak ada.");
            } else {
                System.out.println("Solusi banyak.");
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
                dx = temp1.DeterminanKofaktor();
                solX[i] = dx/d;
                int counter = i+1;
                System.out.println("X" + counter +" : " + solX[i]);
            }
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
}
