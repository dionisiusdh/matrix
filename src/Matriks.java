package src;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;

import javax.sound.midi.SysexMessage;

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
}