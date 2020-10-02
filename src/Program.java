//package src; // Kalo dipake jadi gabisa dicompile, comment dlu sebelum compile pakai javac

import java.util.*;
import java.io.*;

class Program {
    public static void main(String[] args) throws InterruptedException{
        /* =========================================== MAIN PROGRAM =======================================*/
        System.out.println("=============================");
        System.out.println("-----------MATRIKS-----------");
        System.out.println("     Tugas Besar Algeo 1     ");
        System.out.println("         Kelompok 39         ");
        System.out.println("=============================");
        //Menu Utama

        boolean exit = false;

        while(!exit){
            Scanner scan = new Scanner(System.in);
            MenuUtama();
            int tipeFitur = scan.nextInt();
            
            switch (tipeFitur) {
                case 1:
                    Matriks M1 = new Matriks();
                    MenuInputMatriks(M1);
                    MenuSPL(M1);  
                    break;
                case 2:
                    Matriks M2 = new Matriks();
                    MenuInputMatriks(M2);
                    MenuDeterminan(M2);
                    break;
                case 3:
                    Matriks M3 = new Matriks();
                    MenuInputMatriks(M3);
                    MenuInverse(M3);
                    break;
                case 4:
                    Matriks M4 = new Matriks();
                    MenuInterpolasi(M4);
                    break;
                case 5:
                    Matriks M5 = new Matriks();
                    MenuRegresi(M5);
                    break;
                case 6:
                    System.out.println("-----------------------------");
                    System.out.println("Terima Kasih!");
                    exit = true;
                    scan.close();
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
        System.out.println("----------MAIN MENU----------");
        System.out.println();
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
        
        String Solusi = "";
        
        if (metodeDeterminan==1){
            hasilDet = MProses.DeterminanOBE();
            System.out.println("Determinan matriks: " + hasilDet);
            Solusi += "Determinan matriks: " + hasilDet;
        }
        else if(metodeDeterminan==2){
            hasilDet = MProses.DeterminanKofaktor();
            System.out.println("Determinan matriks: " + hasilDet);
            Solusi += "Determinan matriks: " + hasilDet;
        }
        else{
            System.out.println("Anda salah Input!");
        }
        save_solusi(Solusi);
    }

    public static void MenuInverse(Matriks MProses){
        System.out.println("-----------------------------");
        System.out.println("-----------INVERSE-----------");
        
        System.out.println("Sebelum di-inverse: ");
        MProses.TulisMatriks();
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

    public static void MenuInterpolasi(Matriks MProses){
        System.out.println("-----------------------------");
        System.out.println("-----Interpolasi Polinom-----");
        MProses.Interpolasi();
    }

    public static void MenuRegresi(Matriks MProses){
        System.out.println("-----------------------------");
        System.out.println("---Regresi Linier Berganda---");
        MProses.regresi();
    }

    public static void MenuInputMatriks(Matriks MInput){
        boolean inputMatriks = true;
        while (inputMatriks){
            int tipeInput;
            Scanner scan = new Scanner(System.in);
            System.out.println("\n-------------------------------");
            System.out.println("---------Input Matriks---------");
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
                System.out.print("Masukkan path file .txt (Contoh: ../test/1a.txt): ");
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
            Thread.sleep(100);
        }
        System.out.println();
    }   
    
    public static void save_solusi(String Solusi) {
        System.out.print("Apakah Anda ingin menyimpan solusi? Y/N: ");
        
        Scanner scan = new Scanner(System.in);
        char ans2 = scan.next().charAt(0);
        if (ans2=='Y' || ans2=='y'){
            try {
                System.out.print("Masukkan nama file: ");
                Scanner scan2 = new Scanner(System.in);
                String nama_file = scan2.nextLine();
                        
                nama_file += ".txt";
            
                FileOutputStream save_file = new FileOutputStream(("./test/" + nama_file));
            
                byte value[] = Solusi.getBytes();
                save_file.write(value);
                
                // scan.close();
                save_file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
