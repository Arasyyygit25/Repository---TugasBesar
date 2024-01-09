package praktikum6;
  

import java.util.Scanner;

public class parkir {
    public static void main(String[] args) {
        aplikasi app = new aplikasi();
        app.menu();
    }
}

class aplikasi {
    String tglMasuk;
    String tglKeluar;
    String jamMasuk;
    String jamKeluar;
    String kendaraan;
    int totalBayar;

    Scanner input = new Scanner(System.in);

    void menu() {
        String quit;
        int harga = 0;

        do {
            int ulangi = 0;

            System.out.println("<<-- Aplikasi Parkir -->>");
            System.out.println("1. Mobil");
            System.out.println("2. Motor");
            System.out.print("Pilih Kendaraan :");
            int pilih = input.nextInt();
            if (pilih == 1) {
                this.kendaraan ="Mobil";
                System.out.println("Harga Parkir Perjam Mobil = Rp.7000,-");
                harga = 7000;
            } else if (pilih == 2) {
                this.kendaraan =  "Motor";
                System.out.println("Harga Parkir Perjam Motor = Rp.5000,-");
                harga = 5000;
            } else {
                System.out.println("Kendaraan Tidak tersedia");
                ulangi = 1;
            }

            if (ulangi != 1) {
                System.out.print("Tanggal Masuk     : ");
                this.tglMasuk = input.next();

                System.out.print("Tanggal Keluar    : ");
                this.tglKeluar = input.next();

                System.out.print("Input Jam Masuk (format: 00:00)   : ");
                this.jamMasuk = input.next();

                System.out.print("Input Jam Keluar (format: 00:00)  : ");
                this.jamKeluar = input.next();

                bayarParkir(harga);
            }

            System.out.print("Quit? y/n :");
            quit = input.next();
        } while (quit != "y");
    }
                                                
    void bayarParkir(int harga) {  
        String[] timeMasuk = this.jamMasuk.split(":");
        int hoursMasuk = Integer.parseInt(timeMasuk[0]);
        int minutesMasuk = Integer.parseInt(timeMasuk[1]);

        String[] timeKeluar = this.jamKeluar.split(":");
        int hoursKeluar = Integer.parseInt(timeKeluar[0]);
        int minutesKeluar = Integer.parseInt(timeKeluar[1]);

        if (hoursKeluar > 24 || hoursMasuk > 24 || minutesKeluar > 60 || minutesMasuk > 60) {
            System.out.println("input Format Waktu salah"); 
        } else {
            // for (int i = 0; i < 2; i++) {
            //     System.out.println(timeMasuk[i]);
            // }

            // for (int i = 0; i < 2; i++) {
            //     System.out.println(timeKeluar[i]);
            // }

            int menitTotal = 0;

            if (hoursMasuk > hoursKeluar) {
                int selisih = hoursMasuk - hoursKeluar;
                int jamSelisih = 24 - selisih;
                menitTotal = jamSelisih * 60;

            } else if (hoursKeluar > hoursMasuk) {
                int selisih = hoursKeluar - hoursMasuk;
                menitTotal = selisih * 60;

            } else if (hoursKeluar == hoursMasuk) {
                menitTotal += 0;
            }

            if (minutesMasuk > minutesKeluar) {
                int minutesAwal = (60 - minutesMasuk) + minutesKeluar;
                menitTotal = menitTotal + minutesAwal;
                menitTotal = menitTotal - 60;

            } else if (minutesKeluar > minutesMasuk) {
                int minutesAwal = minutesKeluar - minutesMasuk;
                menitTotal = menitTotal + minutesAwal;

            } else if (minutesKeluar == minutesMasuk) {
                menitTotal += 0;
            }

            
            int totalJam = menitTotal / 60;
            this.totalBayar = harga * totalJam;
            // System.out.println(menitTotal);
            if (menitTotal % 60 != 0) {
                this.totalBayar = this.totalBayar + harga;
            }
            // System.out.println(totalJam);
            

            showStruk(menitTotal, totalJam);
        }
    }

    void showStruk(int menitTotal, int totalJam) {
        System.out.println("<<-- Struk Parkir -->>");
        System.out.println("Kendaraan anda  : " + this.kendaraan);
        System.out.println("Tanggal Masuk   : " + this.tglMasuk);
        System.out.println("Tanggal Keluar  : " + this.tglKeluar);
        System.out.println("Jam Masuk       : " + this.jamMasuk);
        System.out.println("Jam Keluar      : " + this.jamKeluar);
        int menit = menitTotal % 60;
        System.out.println("Total Durasi Parkir : " + totalJam + " Jam " + menit + " Menit");
        System.out.println("Total Bayar Parkir  : Rp." + totalBayar + ",-");
    }
}