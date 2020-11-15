# MatriX
![](https://github.com/BraveUX/for-the-badge/blob/master/src/images/badges/made-with-java.svg)
> A Java program to solve linear equation, interpolation, and multiple linear regression problems using matrix manipulation


| Authors |
| --- |
|Dionisius Darryl H. |
|Rehagana Kevin C. S. |
|Rizky Anggita S. S. |

## Table of contents
- <a href="#deskripsi">Description</a>
- <a href="#struktur">Folder structure</a>
- <a href="#tech">Technologies</a>
- <a href="#inputoutput">Input and Output</a>

### Description
<span id='deskripsi'></span>
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Matris.png/440px-Matris.png" alt="drawing" width="200"/><br>
</p>
MatriX is a program written in java with the following capabilities
  <ul>1. Solving linear equation using Gauss and Gauss-Jordan elimination technique, Inverse Matrix, and Cramer's Rule</ul>
  <ul>2. Solving interpolation and linear regression problems</ul>
  <ul>3. Calculate the determinant of a matrix with various methods such as row reduction and cofactor expansion</ul>

### Folder Structure
<span id='struktur'></span>
```
matrix
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

### Technologies
<span id='tech'></span>
This program is made with **Java 11.0.8** :
```
openjdk 11.0.8 2020-07-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.8+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.8+10, mixed mode)
```
To run the program, simply follow this command:
```
cd bin
java Program
```

To compile the program, use the following command:
```
cd src
javac Program.java
```

### Input dan Output
<span id='inputoutput'></span>

This program could take an input from an external file.
```
Masukkan direktori file eksternal: ../test/1a.txt
```

It also can save the result of a calculation in a .txt file.
```
Masukkan nama file: outputprogram
```
The result will be stored as outputprogram.txt in the default directory ```test```.

### Thank you
