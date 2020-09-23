package src;

import java.util.*;

public class Matriks {
    /* Definisi ADT Matriks */
    /* Attribute */
    // int IdxBrsMin = 0;
    // int IdxBrsMax = 99;
    // int IdxKolMin = 0;
    // int IdxKolMax = 99;
    // int IdxUndef = -999;

    int NBrsEff;
    int NKolEff;

    float[][] M = new float[100][100];

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