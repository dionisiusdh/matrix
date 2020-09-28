package src;

public class SPL extends Matriks {
    // Menyelesaikan sistem persamaan linear dengan berbagai metode

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
