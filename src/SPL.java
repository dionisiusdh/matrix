package src;

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
                //this.splGauss();
                break;
        }
    }

    /* ======================== METODE PENYELESAIAN ======================== */
    public void splGauss() {
        SPL M1 = this;

        M1.EliminasiGauss();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else {
            
        }
    }

    public void splGaussJordan() {
        SPL M1 = this;

        M1.EliminasiGauss();

        if(!M1.isSolutionExist()) {
            System.out.println("SPL tidak memiliki solusi.");
        } else if (M1.isSingleSolution()) {
            M1.EliminasiGaussJordan();

            int counter = 1;

            for(int i=0; i < this.NBrsEff; i++) {
                System.out.println("x" + counter + " = " + M1.M[i][NKolEff-1]);
                counter += 1;
            } 
        } else {
            // Banyak solusi
        }
    }

    public void splMatriksBalikan() {
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

                MBalikan.TulisMatriks();
                System.out.println();

                MKons.TulisMatriks();
                System.out.println();

                MHsl.TulisMatriks();
                System.out.println();

                for (int i = 0; i < MHsl.NBrsEff; i++) {
                    System.out.println("x" + (i+1) + " = " + MHsl.M[i][0]);
                }
            }
        }
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

    public float[] Cramer(){
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
            System.out.println("Solusi Tidak ada");
        }
        else{
            temp1 = BuatMatriks(this.NBrsEff, this.NKolEff - 1);
            for (int i = 0; i<temp1.NKolEff; i++){
                for (int j = 0; j<this.NBrsEff;j++){
                    int kolom = 0;
                    for (int k = 0; k<this.NKolEff;k++){
                        if (k!=i){
                            temp1.M[j][kolom] = this.M[j][k];
                            kolom+=1;
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
}
