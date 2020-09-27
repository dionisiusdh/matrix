package src;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class Matriks {
    /* Definisi ADT Matriks */
    /* Attribute */
    int IdxBrsMin = 0;
    int IdxBrsMax = 99;
    int IdxKolMin = 0;
    int IdxKolMax = 99;
    int IdxUndef = 999;

    int NBrsEff;
    int NKolEff;

    float[][] M = new float[IdxBrsMax+1][IdxKolMax+1];

    /* Method */
    /* ======================== KONSTRUKTOR ======================== */
    public Matriks() {
        // Mendefinisikan matriks kosong
        this.NBrsEff = 0;
        this.NKolEff = 0;

        for (int i=0; i <= this.IdxBrsMax; i++){
             for (int j=0; j <= this.IdxKolMax; j++){
                 this.M[i][j] = 0;
             }
        }
    }

    public Matriks BuatMatriks(int NBrsEff, int NKolEff) {
        Matriks M1 = new Matriks();
        M1.NBrsEff = NBrsEff;
        M1.NKolEff = NKolEff;

        return M1;
    }
    
    public void BacaMatriks() {
        // Membaca input matriks
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah baris: ");
        this.NBrsEff = scan.nextInt();

        System.out.print("Masukkan jumlah kolom: ");
        this.NKolEff = scan.nextInt();

        for (int i=0; i < this.NBrsEff; i++){
            for (int j=0; j < this.NKolEff; j++){
                this.M[i][j] = scan.nextInt();
            }
        }
    }

    public void BacaMatriksTxt(String path){
        //Membaca matriks dari file .txt
        try{
            //Mencari jumlah baris dan kolom dan memasukkan ke matriks
            BufferedReader br = new BufferedReader(new FileReader(new File(path))); 
            String line;
            int baris = 0;
            int kolom = 0; 
            while ((line = br.readLine()) != null){
                Scanner scan = new Scanner(line);
                int j = 0;
                while (scan.hasNextFloat()){
                    this.M[baris][j] = scan.nextFloat();
                    j++;
                    if (baris==0) kolom++;    
                }
                baris++;
            }
            this.NBrsEff = baris;
            this.NKolEff = kolom;

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TulisMatriks() {
        // Menuliskan matriks ke layar
        for (int i=0; i < this.NBrsEff; i++){
            for (int j=0; j < this.NKolEff; j++){
                System.out.print(this.M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int NbElmt(Matriks M) {
        // Mereturn banyak elemen dari matriks M
        return (M.NBrsEff * M.NKolEff);
    }

    /* ======================== OPERASI MATRIKS ======================== */
    public Matriks CopyMatriks(Matriks M, Matriks MHsl) {
        for (int x=0; x < M.NBrsEff; x++){
            for (int y=0; y < M.NKolEff; y++){
                MHsl.M[x][y] = M.M[x][y];
            }
        }
        return MHsl;
    }
    
    public float[][] Transpose(Matriks M) {
        // Mereturn transpose dari matriks M
        float[][] MTranspose = new float[IdxBrsMax+1][IdxKolMax+1];

        for (int i=0; i < M.NBrsEff; i++){
            for (int j=0; j < M.NKolEff; j++){
                MTranspose[i][j] = M.M[i][j];
            }
        }
        return MTranspose;
    }

    public void TransposeMatriks() {
        this.M = Transpose(this);

        int temp = this.NBrsEff;
        this.NBrsEff = this.NKolEff;
        this.NKolEff = temp;
    }

    /* ======================== DETERMINAN ======================== */
    public float DeterminanOBE() {
        //Determinan menggunakan reduksi baris
        //PREKONDISI Matriks adalah bujur sangkar
        int i,j,k;
        float determinan = 1;

        Matriks M1 = BuatMatriks(this.NBrsEff, this.NKolEff);
        M1.M = this.M;

        //Copy Matriks
        M1 = CopyMatriks(this, M1);

        //Mengecek apakah elemen 0,0 bernilai 0, jika iya tukar baris
        int x,y;
        float sign = 1;
        boolean isZero = true;
        int counter = 0;
        float temp1;
        while ((isZero) && counter < M1.NBrsEff -1 )
        {
            if (M1.M[0][0] == 0){
                sign = sign * (-1);
                for (x=0; x < M1.NBrsEff; x++){
                    for (y=0; y < M1.NBrsEff; y++){
                        temp1 = M1.M[0][y];
                        M1.M[0][y] = M1.M[counter+1][y];
                        M1.M[counter+1][y] = temp1;
                    }
                }
            }
            if (M1.M[0][0]==0){
                isZero = true;
                counter++;
            }
            else{
                isZero = false;
            }
        }
        
        //Jika sebuah kolom element nya 0 semua, determinan = 0
        if (counter==M1.NBrsEff-1){
            return 0;  
        }

        // //Membagi elemen 0,0 supaya menjadi 1 untuk memudahkan perhitungan
        if (M1.M[0][0] != 1){
            int z;
            float firstElmt = M1.M[0][0];
            sign = sign *  firstElmt;   //Masih bingung, mestinya sign/firstElmt, tapi malah jadi salah hasilnya
            for (z=0; z<M1.NBrsEff; z++){
                M1.M[0][z] = M1.M[0][z] / firstElmt;
            }
        }
        //Operasi Baris Elementer
        for (i = 1; i<NBrsEff; i++){    //Looping banyaknya baris matriks
            for (j = 0; j < i; j++){        //looping di dalam baris
                float pembagi = M[j][j];    
                float pengali = M[i][j];         
                for (k = 0; k < NKolEff; k++){  // Proses pengurangan
                    M1.M[i][k] -= (pengali/pembagi * M1.M[j][k]);
                    
                    if (j== i-1 && k==i){
                        if (M1.M[i][k] ==0) determinan = 0; //Jika elemen diagonal 0, maka determinan pasti 0
                        determinan *= M[i][k];
                    }
                }
            }
        }
        return determinan*sign;
    }

    public float DeterminanKofaktor() {
        float det = 0;
        int sign = 1;

        Matriks M1 = BuatMatriks(this.NBrsEff, this.NKolEff);
        M1 = CopyMatriks(this, M1);

        // Mengecek kasus sederhana dimana ukuran M 1x1 atau 2x2 (Basis)
        if(NbElmt(M1) == 1) {
            return this.M[0][0];
        } else if (NbElmt(M1) == 2) {
            return (this.M[0][0] * this.M[1][1] - this.M[0][1] * this.M[1][0]);
        } else { 
            // Ukuran M >= 3x3
            // Ambil pivot pada baris pertama (i = 0)
            for (int j = 0; j <= this.NKolEff; j++){
                M1 = MinorEntri(0, j);
                det += this.M[0][j] * (sign * M1.DeterminanKofaktor());
                sign *= -1;
            }
        }
        return det;
    }

    public Matriks MinorEntri(int x, int y) {
        // Mengembalikan matriks minor entri saat dipivot pada (x,y)
        Matriks MMinorEntri = BuatMatriks(this.NBrsEff-1, this.NKolEff-1);

        int m = 0;

        for (int i = 0; i <= this.NBrsEff; i++) {
            int n = 0;
            if (i != x) {
                for (int j = 0; j <= this.NKolEff; j++) {
                    if (j != y) {
                        MMinorEntri.M[m][n] = this.M[i][j];
                        n += 1;
                    }
                }
                m += 1;
            }
        }
        return MMinorEntri;
    }

    /* ======================== MATRIKS BALIKAN ======================== */
    public Matriks BuatMatriksIdentitas() {
        Matriks MIdentitas = new Matriks();
        MIdentitas.NBrsEff = this.NBrsEff;
        MIdentitas.NKolEff = this.NKolEff;

        for (int i=0; i < MIdentitas.NBrsEff; i++){
            for (int j=0; j < MIdentitas.NKolEff; j++){
                if(i == j){
                    MIdentitas.M[i][j] = 1;
                } else {
                    MIdentitas.M[i][j] = 0;
                }
            }
        }
        return MIdentitas;
    }

    public Matriks BuatMatriksAugmented(Matriks M1, Matriks M2) {
        Matriks MAugmented = new Matriks();

        MAugmented.NBrsEff = M1.NBrsEff;
        MAugmented.NKolEff = M1.NKolEff + M2.NKolEff;

        for (int i=0; i < M1.NBrsEff; i++){
            for (int j=0; j < M1.NKolEff; j++){
                MAugmented.M[i][j] = M1.M[i][j];
            }
        }
        
        for (int i=0; i < MAugmented.NBrsEff; i++){
            for (int j=M1.NKolEff; j < MAugmented.NKolEff; j++){
                MAugmented.M[i][j] = M2.M[i][j-M1.NKolEff];
            }
        }
        return MAugmented;
    }

    public Matriks BuatMatriksBalikan() {
        // Metode yang digunakan adalah OBE
        Matriks MIdentitas = new Matriks();
        Matriks MAugmented = new Matriks();
        Matriks MBalikan = new Matriks();

        Matriks M1 = BuatMatriks(this.NBrsEff, this.NKolEff);
        M1 = CopyMatriks(this, M1);

        MIdentitas = this.BuatMatriksIdentitas();
        MAugmented = this.BuatMatriksAugmented(M1, MIdentitas);

        return MAugmented;
    }

    /* ======================== METODE ELIMINASI ======================== */
    public void EliminasiGauss() {
        // Menggunakan eliminasi Gauss Jordan untuk membuat matriks echelon

        // Pencarian baris pivot
        // Retrieve pembagi dan pengali dari matriks untuk dilakukan OBE
        for(int i=0; i <= this.NBrsEff; i++){
            if(IsBrsPivot(i)){
                int idx_kol_pivot = this.CariIdxKolPivot(i);
                float pembagi = this.PivotPembagi(i); // Pembagi == Elemen paling kiri

                // Hilangkan angka non-zero dibawah posisi pivot
                for(int j = i+1; j <= this.NBrsEff; j++){
                    if(this.M[j][idx_kol_pivot] != 0){
                        float pengali = this.M[j][idx_kol_pivot] / pembagi;

                        for (int k = 0; k <= this.NKolEff; k++){
                            this.M[j][k] -= (pengali * this.M[i][k]);
                        }
                    }
                    this.M[j][idx_kol_pivot] = 0;
                }
            }
        }

        // Bagi semua kolom dengan PivotPembagi agar menghasilkan leading one
        int l = 0;
        while(l <= this.NBrsEff){
            if(this.CariIdxKolPivot(l) != this.IdxUndef){
                scaleBaris(l, 1/PivotPembagi(l));
            }
            l += 1;
        }

        // Ubah urutan leading one agar menjadi matriks echelon
        //for (int m = 0; m <= this.NBrsEff; m++){
        //    for(int n = m+1; n <= this.NBrsEff; n++){
        //        if (this.CariIdxKolPivot(m) > CariIdxKolPivot(n)){
        //            swapBaris(m, n);
        //        }
        //    }
        //}
        
        // Perbaiki output -0.0 agar menjadi 0.0
        for (int m = 0; m <= this.NBrsEff; m++) {
            for(int n = 0; n <= this.NKolEff; n++) {
                this.PerbaikiNol(m, n);
            }
        }
    }
    public void EliminasiGaussV2() {
        
        int kol = 0;
        int pivotPos = -1;
        int skipped = 0;

        for (int i=0; i<this.NBrsEff; i++){
            if (!isAllZeroBrs(i)){
                System.out.println("MASUK");
                //This is a pivot column
                for (int x=i; x<this.NBrsEff-1; x++){
                    if (CariIdxKolPivot(x) > CariIdxKolPivot(x+1) && CariIdxKolPivot(x+1)!=this.IdxUndef){ //this.M[i][kol] == 0 &&this.M[i+1][kol]!=0
                        swapBaris(i, x+1);
                    }
                    else if(CariIdxKolPivot(x+1)==this.IdxUndef){ //jika baris bawahnya 0 semua, taruh bawah langsung
                        //Proses menaruh baris i+1 ke paling bawah, karena baris seluruhnya 0
                        int temp = x+1;
                        for (int j=temp+1; j<this.NBrsEff; j++){
                            swapBaris(temp, j);
                            temp = j;
                        }
                    }
                }
                //Mencari Pivot matriks / submatriks
                pivotPos = CariIdxKolPivot(i);

                //yang discale adalah pivot dari matrix
                System.out.println(this.M[i][pivotPos]);
                System.out.println("PivotPos: "+ pivotPos);

                scaleBaris(i, 1/this.M[i][pivotPos]);
                // pivotPos = CariIdxKolPivot(i);
    
                //replacement operations to create zeros in every position in this column
                //below the pivot position
                for (int a=i+1; a<this.NBrsEff; a++){
                    float pengali = this.M[a][pivotPos];
                    System.out.println("Pengali: " + pengali);
                    for (int b=i; b<this.NKolEff; b++){
                        if (b>=pivotPos){
                            this.M[a][b] = this.M[a][b] - (pengali*this.M[i][b]);
                        }
                    }
                }
                kol = pivotPos + 1; //ini berfungsi untuk mengambil submatrix
            }
            else{
                // skipped++;
                System.out.println("Tidak Masuk!");
                //Jika seluruh barisnya 0, taruh di paling bawah
                //tapi akibatnya, proses loop ter-skip satu kali untuk i tersebut
                //misal ketika i=1, seluruh barisnya 0, maka akan masuk ke else ini
                //kemudian diswap(oke), tapi akibatnya untuk baris i yg udah diswap, dia tidak proses
                //scaler, OBE, dll.
                int temp = i;
                for (int j=temp+1; j<this.NBrsEff; j++){
                    swapBaris(temp, j);
                    temp = j;
                }
            }
        }
        // Perbaiki output -0.0 agar menjadi 0.0
        for (int m = 0; m <= this.NBrsEff; m++) {
            for(int n = 0; n <= this.NKolEff; n++) {
                this.PerbaikiNol(m, n);
            }
        }
    }

    public void EliminasiGaussV3() {
        // Menggunakan eliminasi Gauss Jordan untuk membuat matriks echelon
     
        //Proses menempatkan baris dengan elemen non-zero paling kecil di kiri
        for (int i = 0; i<this.NBrsEff ; i++) {
            for (int j = 0; j<(this.NBrsEff)-1; j++){
                if (CariIdxKolPivot(j) > CariIdxKolPivot(j+1) && ((CariIdxKolPivot(j)!= this.IdxUndef) || (CariIdxKolPivot(j+1) != this.IdxUndef)))
                {
                    swapBaris(j, j+1);
                }
            }
        }
     
        //melakukan eliminasi 
        for (int i = 0; i<this.NBrsEff; i++){
            float pembagi = PivotPembagi(i);
            //proses membagi agar elemen non-zero paling kiri bernilai 1
            //for (int j = 0; j<this.NKolEff;j++){
            //    this.M[i][j] = (this.M[i][j])/pembagi;
            //}
            //proses pengurangan
            for (int k = i+1; k<this.NBrsEff; k++)
            {
                if (CariIdxKolPivot(k) == CariIdxKolPivot(i)){
                    for (int l = CariIdxKolPivot(k); l<this.NKolEff;l++){
                        this.M[k][l] = (this.M[k][l]) - (this.M[i][l]*(PivotPembagi(k)/pembagi));
                    }
                }
            }
        }

        for(int i = 0; i<this.NBrsEff;i++){
            float pembagi = PivotPembagi(i);
            for (int j = 0; j<this.NKolEff;j++){
                this.M[i][j] = this.M[i][j]/pembagi;
            }
        }
    }
    
    public void EliminasiGaussJordan() {
        // Menggunakan eliminasi Gauss Jordan untuk membuat matriks echelon tereduksi
        // Pertama-tama, terapkan eliminasi Gauss pada matrix agar menjadi matriks echelon
        this.EliminasiGauss();
        
        // Pencarian baris pivot
        // Retrieve pembagi dan pengali dari matriks untuk dilakukan OBE
        for(int i=this.NBrsEff; i >= 0; i--) {
            if(IsBrsPivot(i)){
                int idx_kol_pivot = this.CariIdxKolPivot(i);
                float pembagi = this.PivotPembagi(i);

                for(int j = i-1; j >= 0; j--){
                    if(this.M[j][idx_kol_pivot] != 0){
                        float pengali = this.M[j][idx_kol_pivot] / pembagi;

                        for (int k = 0; k <= this.NKolEff; k++){
                            this.M[j][k] -= (pengali * this.M[i][k]);
                        }
                    }
                }
            }
        }
    }

   /* ======================== FUNGSI PEMBANTU ======================== */
    public boolean IsBrsPivot(int brs) {
        // Cek apakah sebuah baris memiliki pivot (elemen yang != 0)
        boolean found = false;
        int j=0;

        while(!found && j<=this.NKolEff) {
            if(this.M[brs][j] != 0) {
                found = true;
            }
            j += 1;
        }
        return found;
    }

    public int CariIdxKolPivot(int brs) {
        // Mencari indeks kolom pivot pada baris brs
        boolean found = false;
        int IdxKolPivot = this.IdxUndef;

        int j = 0;

        while(!found && j<=this.NKolEff) {
            if(this.M[brs][j] != 0) {
                IdxKolPivot = j;
                found = true;
            }
            j += 1;
        }
        return IdxKolPivot;
    }

    public float PivotPembagi(int brs){
        // Mencari pembagi pada sebuah pivot
        // Pembagi == elemen paling kiri dalam sebuah baris
        float pembagi = this.IdxUndef;
        if (CariIdxKolPivot(brs) != this.IdxUndef){
            pembagi = this.M[brs][CariIdxKolPivot(brs)];
        }
        return pembagi;
    }

    public void swapBaris(int baris1, int baris2){
        //Me-swap baris1 dan baris2
        float[] temp = this.M[baris1];
        this.M[baris1] = this.M[baris2];
        this.M[baris2] = temp;
        //for (int kol=0; kol<=this.NKolEff; kol++){
        //    temp = this.M[baris1][kol];
        //    this.M[baris1][kol] = this.M[baris2][kol];
        //    this.M[baris2][kol] = temp;
        //}
    }

    public void scaleBaris(int barisX, float scaler){
        //Mengubah elemen barisX, bisa dibagi atau dikali (diatur di parameter scaler);
        for (int kol=0; kol< this.NKolEff; kol++){
            this.M[barisX][kol]  = this.M[barisX][kol] * scaler;
        }
    }

    public boolean isAllZeroBrs(int barisX){
        //Mengecek apakah elemen pada barisX semuanya 0
        int count = 0;
        for (int kolom=0; kolom<this.NKolEff; kolom++ ){
            if (this.M[barisX][kolom] == 0){
                count++;
            }
        }
        return (count==this.NKolEff);
    }

    public boolean isAllZeroKol(int kolomX){
        //Mengecek apakah elemen pada kolomX semuanya 0 
        int count = 0;
        for (int baris=0; baris<this.NBrsEff; baris++ ){
            if (this.M[baris][kolomX] == 0){
                count++;
            }
        }
        return (count==this.NBrsEff);
    }

    public float PerbaikiNol(int i, int j) {
        // Perbaiki output program -0.0 agar menjadi 0.0
        if(this.M[i][j] == -0.0) {
            this.M[i][j] = 0;
        }
        return this.M[i][j];
    }
}