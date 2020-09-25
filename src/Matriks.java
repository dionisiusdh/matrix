package src;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;


public class Matriks {
    //
    /* Definisi ADT Matriks */
    /* Attribute */
    int IdxBrsMin = 0;
    int IdxBrsMax = 99;
    int IdxKolMin = 0;
    int IdxKolMax = 99;
    int IdxUndef = -999;

    int NBrsEff;
    int NKolEff;

    float[][] M = new float[IdxBrsMax+1][IdxKolMax+1];

    /* Method */
    /* --- KONSTRUKTOR --- */
    public Matriks() {
        // Mendefinisikan matriks kosong
        // this.NBrsEff = 0;
        // this.NKolEff = 0;

        // for (int i=0; i <= this.IdxBrsMax; i++){
        //     for (int j=0; j <= this.IdxKolMax; j++){
        //         this.M[i][j] = 0;
        //     }
        // }
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

    public void BacaMatriksTxt(){
        //Membaca matriks dari file .txt
        try{
            //Mencari jumlah baris dan kolom dan memasukkan ke matriks
            BufferedReader br = new BufferedReader(new FileReader(new File("src/test.txt"))); 
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

    public float DeterminanOBE(){
        //Determinan menggunakan reduksi baris
        //PREKONDISI Matriks adalah bujur sangkar
        int i,j,k;
        float determinan = 1;

        Matriks M1 = new Matriks();
        M1.M = this.M;
        M1.NBrsEff = this.NBrsEff;
        M1.NKolEff = this.NKolEff;

        //Copy Matriks (bisa dibuat fungsi sendiri)
        for (int x=0; x < this.NBrsEff; x++){
            for (int y=0; y < this.NKolEff; y++){
                M1.M[x][y] = this.M[x][y];
            }
        }

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
                for (x=0; x<M1.NBrsEff; x++){
                    for (y=0; y< M1.NBrsEff; y++){
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
    
}