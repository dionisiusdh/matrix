package src;

import java.util.*;
// import java.io.*;

class Program {
    public static void main(String[] args) throws InterruptedException{
        //Matriks M = new Matriks();
        //Matriks M2 = new Matriks();

        //M.BacaMatriksTxt("src/gauss_test.txt");
        //M2.BacaMatriksTxt("src/test.txt");
        // M.BacaMatriks();
        //System.out.println("Matriks yang anda masukkan: ");
        //M.TulisMatriks();
        //System.out.println();

        // DEBUG SPL
        SPL M3 = new SPL();
        M3.BacaMatriksTxt("src/spl_test.txt");
        System.out.println("Matriks yang anda masukkan: ");
        M3.TulisMatriks();

        M3.menuSPL(4);

        //System.out.println("Apakah ada solusi? " + M3.isSolutionExist());
        //System.out.println("Apakah banyak solusi? " + M3.isManySolution());
        //System.out.println("Apakah solusi tunggal? " + M3.isSingleSolution());  
        
        //M3.Cramer();

        /* DEBUG METODE MATRIKS
        System.out.println();
        Matriks MKoef = M3.Koefisien();
        MKoef.TulisMatriks();

        System.out.println();
        Matriks MKons = M3.Konstanta();
        MKons.TulisMatriks();

        System.out.println();
        Matriks MBalikan = M3.BuatMatriksBalikan();
        MBalikan.TulisMatriks();

        System.out.println();
        Matriks MKali = M3.KaliMatriks(MBalikan);
        MKali.TulisMatriks();
        System.out.println();
        */

        /* DEBUG DETERMINAN
        //Prekondisi M adalah square matrix
        float det = M.DeterminanOBE();
        System.out.println("Determinan matriks OBE: " + det);
        System.out.println();

        float det2 = M2.DeterminanKofaktor();
        System.out.println("Determinan matriks Kofaktor: " + det2);
        */

        /*DEBUG INVERSE
        // Matriks Balikan
        Matriks M3 = new Matriks();
        M3.BacaMatriksTxt("src/inverse_test.txt");

        System.out.println("\n Matriks balikan: ");
        Matriks M3Balikan = M3.BuatMatriksBalikan();
        M3Balikan.TulisMatriks();
        */

        System.out.println("-----------------------------");
        System.out.println("-----------MATRIKS-----------");
        System.out.println();
        //Menu Utama

        boolean exit = false;
        Matriks MInput = new Matriks();
        
        menuInputMatriks(MInput);

        while(!exit){
            loadingSleep();
            //Mengcopy isi MInput ke MProses agar matriks tidak berubah setelah diproses
            Matriks MProses = new Matriks();
            MProses = MProses.BuatMatriks(MInput.NBrsEff, MInput.NKolEff);
            MProses = MProses.CopyMatriks(MInput, MProses);

            Scanner scan = new Scanner(System.in);
            MenuUtama();
            int tipeFitur = scan.nextInt();
            
            switch (tipeFitur) {
                case 1:
                    MenuSPL(MProses);  
                    break;
                
                case 2:
                    MenuDeterminan(MProses);
                    break;

                case 3:
                    menuInverse(MInput, MProses);
                    break;
                case 4:
                    System.out.println("-----------------------------");
                    System.out.println("-----Interpolasi Polinom-----");
                    break;
                case 5:
                    System.out.println("-----------------------------");
                    System.out.println("---Regresi Linier Berganda---");
                    break;
                case 6:
                    System.out.println("-----------------------------");
                    System.out.println("Terima Kasih!");
                    exit = true;
                    break;
                default:
                    System.out.println("Input Anda salah. Silahkan Ulangi!");
                    System.out.println();
                    break;
            }
            System.out.println();
        }
    }

    public static void MenuUtama(){
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("-----------MATRIKS-----------");
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    public static void MenuSPL(Matriks MProses){
        int metode;
        Scanner scan = new Scanner(System.in);
        SPL MP = new SPL();
        MP.NBrsEff = MProses.NBrsEff;
        MP.NKolEff = MProses.NKolEff;
        MP.copySPLMatriks(MP, MProses);

        System.out.println("-------------------------");
        System.out.println("-----------SPL-----------");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.print("Pilihan Anda: ");
        metode = scan.nextInt();
        MP.menuSPL(metode);   
    }

    public static void MenuDeterminan(Matriks MProses){
        int metodeDeterminan;
        float hasilDet;
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("---------DETERMINAN----------");
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.print("Pilihan Anda: ");

        metodeDeterminan = scan.nextInt();
        
        if (metodeDeterminan==1){
            hasilDet = MProses.DeterminanOBE();
            System.out.println("Determinan matriks: " + hasilDet);
        }
        else if(metodeDeterminan==2){
            hasilDet = MProses.DeterminanKofaktor();
            System.out.println("Determinan matriks: " + hasilDet);
        }
        else{
            System.out.println("Anda salah Input!");
        }
    }

    public static void menuInverse(Matriks MInput, Matriks MProses){
        System.out.println("-----------------------------");
        System.out.println("-----------INVERSE-----------");
        
        System.out.println("Sebelum di-inverse: ");
        MInput.TulisMatriks();
        System.out.println();
        
        MProses = MProses.BuatMatriksBalikan();
        System.out.println("Sesudah di-inverse: ");
        MProses.TulisMatriks();
        System.out.println();

        //Save matriks
        Scanner scan = new Scanner(System.in);
        System.out.print("Apakah Anda ingin menyimpan matriks? Y/N: ");
        char ans = scan.next().charAt(0);
        if (ans=='Y' || ans=='y'){
            MProses.save();
        }
    }


    public static void menuInputMatriks(Matriks MInput){
        boolean inputMatriks = true;
        while (inputMatriks){
            int tipeInput;
            Scanner scan = new Scanner(System.in);

            System.out.println("Memasukkan matriks");
            System.out.println("1. Masukan dari papan ketik");
            System.out.println("2. Masukan dari file teks");
            System.out.print("Pilihan Anda: ");
            tipeInput = scan.nextInt();

            if (tipeInput==1){
                MInput.BacaMatriks();
                inputMatriks = false;
            }
            else if(tipeInput==2){
                System.out.print("Masukkan path file .txt: ");
                Scanner in = new Scanner(System.in);
                String pathTxt = in.nextLine();
                MInput.BacaMatriksTxt(pathTxt);
                inputMatriks = false;
            }
            else{
                System.out.println("Input Anda salah. Silahkan ulangi!");
            }    
        }
    }

    public static void loadingSleep() throws InterruptedException{
        System.out.print("Loading to Menu");
        for (int i =0; i<5;i++){
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
    }   
    
}