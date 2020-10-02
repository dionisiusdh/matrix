# Tugas Besar 1 - Aljabar Linear dan Geometri
> Sistem Persamaan Linier, Determinan, dan Aplikasinya
### Kelompok 39
| Anggota | NIM |
| --- | --- |
|Dionisius Darryl H. | 13519058 |	
|Rehagana Kevin C. S. | 13519117 |	
|Rizky Anggita S. S. | 13519132 |

## Daftar Isi
- <a href="#deskripsi">Deskripsi</a>
- <a href="#struktur">Struktur folder</a>
- <a href="#program">Program</a>
- <a href="#inputoutput">Input dan Output</a>

### Deskripsi
<span id='deskripsi'></span>
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Matris.png/440px-Matris.png" alt="drawing" width="200"/><br>
</p>
Pada tugas besar 1 IF2123 Aljabar Linier dan Geometri ini, akan dibuat sebuah program yang dapat menyelesaikan permasalahan matematik sebagai berikut: 

1. Menghitung **solusi SPL** dengan metode eliminasi Gauss, metode Eliminasi Gauss-Jordan, metode matriks balikan, dan kaidah Cramer (kaidah Cramer khusus untuk SPL dengan n peubah dan n persamaan).  
2. Menyelesaikan persoalan **interpolasi** dan **regresi linier**.  
3. Menghitung **matriks balikan**. 
4. Menghitung **determinan** matriks dengan berbagai metode (reduksi baris dan ekspansi kofaktor). 

### Struktur Folder
<span id='struktur'></span>
```
Algeo01-19058
├── bin
│   └── Matriks.class
│   └── Program.class
│   └── SPL.class
│
├── src
│   └── Matriks.java
│   └── Program.java
│   └── SPL.java
│
├── docs
│   └── Laporan-Algeo01-19058.docx
│   └── Laporan-Algeo01-19058.pdf
│
├── test
│   └── ... .txt (Studi kasus 1-8)
│   └── ..._ans.txt (Hasil studi kasus 1-8)
│
└── README.md
```

### Program
<span id='program'></span>
Program ini dibuat menggunakan **Java 11.0.8** dengan:
```
openjdk 11.0.8 2020-07-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.8+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.8+10, mixed mode)
```
Program dapat di **run** dengan menjalankan
```
cd bin
java Program
```

Jika ingin mengcompile program, dapat dijalankan
```
cd src
javac Program.java
```
dengan terlebih dahulu menghilangkan baris ```'package src'``` pada seluruh program berekstensi .java pada ```src```

### Input dan Output
<span id='inputoutput'></span>

Program dapat menerima input dari file external. Berikut adalah contoh cara memasukkan direktori file eksternal saat ingin digunakan oleh program:
```
Masukkan direktori file eksternal: ../test/1a.txt
```

Untuk menyimpan hasil eksekusi program ke sebuah file eksternal berekstensi .txt, tidak perlu dimasukkan ekstensi file, contohnya:
```
Masukkan nama file: outputprogram
```
Maka akan dihasilkan outputprogram.txt pada default directory yaitu ```test```.

### Terima kasih
