package src;

public class SPL extends Matriks {
    // Menyelesaikan sistem persamaan linear dengan berbagai metode

    /* ======================== CEK SOLUSI ======================== */
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
