import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TokoBuku {
    Scanner inp = new Scanner(System.in);
    ArrayList<Buku> bukuList = new ArrayList<>();
    ArrayList<Keranjang> keranjangList = new ArrayList<>();
    ArrayList<Keranjang> penjualanBuku = new ArrayList<>();
    ArrayList<Penjualan> detailPenjualan = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();


    class User {
        String username;
        String password;
        String role;

        User(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }

    class Buku {
        int nomorBuku, tahunKeluaran, stokBuku, hargaBuku;
        String penciptaBuku;
        String JenisBuku;
        String JudulBuku;

        Buku(int nomor, String jenis, String judul, String pencipta, int tahun, int harga, int stok) {
            this.nomorBuku = nomor;
            this.JenisBuku = jenis;
            this.JudulBuku = judul;
            this.penciptaBuku = pencipta;
            this.tahunKeluaran = tahun;
            this.hargaBuku = harga;
            this.stokBuku = stok;
        }

        void setStok(int stokBaru) {
            this.stokBuku = stokBaru;
        }
    }

    class Keranjang {
        Buku buku;
        int jumlah;

        Keranjang(Buku buku, int jumlah) {
            this.buku = buku;
            this.jumlah = jumlah;
        }
    }

    class Penjualan {
        Buku buku;
        int jumlah;
        int totalHarga;

        Penjualan(Buku buku, int jumlah, int totalHarga) {
            this.buku = buku;
            this.jumlah = jumlah;
            this.totalHarga = totalHarga;
        }

        @Override
        public String toString() {
            return "Nama Buku: " + buku.JudulBuku +
                    ", Jumlah: " + jumlah +
                    ", Total Harga: " + totalHarga;
        }
    }

    public static void main(String[] args) {
        TokoBuku tb = new TokoBuku();
        tb.userList();
        tb.listBuku();
        tb.menu();
    }

    void menu() {
        int pilihan;
        while (true) {
            System.out.println("\n\t========================");
            System.out.println("\t   =   MENU LOGIN   =");
            System.out.println("\t========================");
            System.out.println("\n====================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Keluar");
            System.out.print("\nPilih Menu : ");
            pilihan = inp.nextInt();
            System.out.println("====================");

            switch (pilihan) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Keluar dari program");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    void login() {
        String username, password, role = null;

        inp.nextLine();
        System.out.print("Username : ");
        username = inp.nextLine();

        System.out.print("Password : ");
        password = inp.nextLine();

        boolean found = false;

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                role = user.getRole();
                found = true;
                break;
            }
        }

        if (found) {
            if ("user".equals(role)) {
                System.out.println("Login Berhasil - Selamat Datang " + username);
                System.out.println("====================");
                MenuUser();
            } else if ("admin".equals(role)) {
                System.out.println("Login Berhasil - Selamat Datang Admin");
                System.out.println("====================");
                MenuAdmin();
            }
        } else {
            System.out.println("Login Gagal - Username/Password salah");
            System.out.println("====================");
        }
    }

    void userList() {
        users.add(new User("admin", "admin", "admin"));
    }

    void register() {
        String username, password, role;

        System.out.println("\n====================");
        inp.nextLine();
        System.out.print("Username Baru : ");
        username = inp.nextLine();

        System.out.print("Password Baru : ");
        password = inp.nextLine();

        System.out.println("Selamat Anda Telah Teregister");
        System.out.println("====================");
        role = "user";
        users.add(new User(username, password, role));
    }

    void MenuUser() {
        int pilihan;
        while (true) {
            System.out.println("\n\t=======================");
            System.out.println("\t   =   MENU USER   =");
            System.out.println("\t=======================");
            System.out.println("\n====================");
            System.out.println("1. List Buku");
            System.out.println("2. Pembelian");
            System.out.println("3. Cek Akhir");
            System.out.println("4. Relogin");
            System.out.println("0. Exit");
            System.out.print("Pilih Menu : ");
            pilihan = inp.nextInt();
            System.out.println("====================");

            switch (pilihan) {
                case 1:
                    tampilListBuku();
                    break;
                case 2:
                    Pembelian();
                    break;
                case 3:
                    cekAkhir();
                    break;
                case 4:
                    menu();
                    break;
                case 0:
                    System.out.println("Keluar Aplikasi");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    void listBuku() {
        bukuList.add(new Buku(1, "Islami", "Shalawat", "Arasy", 2020, 10000, 10));
        bukuList.add(new Buku(2, "Sejarah", "Kemerdekaan", "Mufid", 2019, 20000, 15));
        bukuList.add(new Buku(3, "Pendidikan", "Matematika", "Yanto", 2021, 15000, 20));
    }

    public void tampilListBuku() {
        for (Buku b : bukuList) {
            System.out.println("\n====================");
            System.out.println("Nomer Buku: " + b.nomorBuku);
            System.out.println("Jenis Buku: " + b.JenisBuku);
            System.out.println("Nama Buku: " + b.JudulBuku);
            System.out.println("Pencipta Buku: " + b.penciptaBuku);
            System.out.println("Tahun Keluaran: " + b.tahunKeluaran);
            System.out.println("Harga Buku: " + b.hargaBuku);
            System.out.println("Stok Buku: " + b.stokBuku);
            System.out.println("====================");
        }
    }

    void Pembelian() {
        int pilihan;
        int nomorBuku = 0;
        int lanjutBeli = 1;
        while (true) {
            System.out.println("\n\t========================");
            System.out.println("\t  =  MENU PEMBELIAN  =");
            System.out.println("\t========================");
            System.out.println("\n====================");
            System.out.println("1. Lanjut Pembelian");
            System.out.println("2. Cek Keranjang");
            System.out.println("3. Kembali");
            System.out.print("Pilih Menu = ");
            pilihan = inp.nextInt();
            System.out.println("====================");

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nomor buku : ");
                    nomorBuku = inp.nextInt();
                    for (Buku b : bukuList) {
                        if (b.nomorBuku == nomorBuku) {
                            if (b.stokBuku > 0) {
                                System.out.println("====================");
                                System.out.println("Nomer Buku: " + b.nomorBuku);
                                System.out.println("Jenis Buku: " + b.JenisBuku);
                                System.out.println("Nama Buku: " + b.JudulBuku);
                                System.out.println("Pencipta Buku: " + b.penciptaBuku);
                                System.out.println("Tahun Keluaran: " + b.tahunKeluaran);
                                System.out.println("Harga Buku: " + b.hargaBuku);
                                System.out.println("====================");
                                System.out.print("Jumlah yang akan dibeli: ");
                                int jumlahBeli = inp.nextInt();

                                if (jumlahBeli <= b.stokBuku) {
                                    int stok = b.stokBuku -= jumlahBeli;
                                    b.setStok(stok);
                                    // b.stokBuku = stok;

                                    keranjangList.add(new Keranjang(b, jumlahBeli));

                                    // bukuList.set(6, stok);

                                    System.out.println("Pembelian Tersimpan.");
                                    System.out.println("====================");
                                } else {
                                    System.out.println("Maaf, stok tidak mencukupi");
                                    System.out.println("====================");
                                }
                            } else {
                                System.out.println("Stok buku habis");
                                System.out.println("====================");
                            }
                            break;
                        }
                    }
                    if (lanjutBeli != 1) {
                        System.out.println("Nomer buku yang anda input tidak tersedia");
                        System.out.println("====================");
                    }
                    break;

                case 2:
                    System.out.println("\n====================");
                    System.out.println("Keranjang belanja:");
                    System.out.println();
                    for (Keranjang k : keranjangList) {
                        System.out.println("Nama Buku: " + k.buku.JudulBuku);
                        System.out.println("Jumlah: " + k.jumlah);
                        System.out.println("Harga: " + k.buku.hargaBuku * k.jumlah);
                        System.out.println("====================");
                    }
                    break;

                case 3:
                    MenuUser();
                    break;

                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    void cekAkhir() {
        int totalHarga = 0;

        if (keranjangList.isEmpty()) {
            System.out.println("Keranjang belanja masih kosong.");
            System.out.println("====================");
            return;
        }

        System.out.println("====================");
        System.out.println("Keranjang belanja:");
        for (Keranjang k : keranjangList) {
            System.out.println("Nama Buku: " + k.buku.JudulBuku);
            System.out.println("Jumlah: " + k.jumlah);
            int subtotal = k.buku.hargaBuku * k.jumlah;
            System.out.println("Subtotal: " + subtotal);
            totalHarga += subtotal;
            System.out.println("====================");
        }
        System.out.println("Total Harga: " + totalHarga);

        System.out.print("Apakah Anda ingin menyelesaikan pembelian? (y/n): ");
        String jawaban = inp.next();

        if (jawaban.equalsIgnoreCase("y")) {
            int totalPenjualan = 0;

            for (Keranjang k : keranjangList) {
                int totalHargaPerItem = k.buku.hargaBuku * k.jumlah;
                totalPenjualan += totalHargaPerItem;
                detailPenjualan.add(new Penjualan(k.buku, k.jumlah, totalHargaPerItem));
            }

            keranjangList.clear();
            System.out.println("Pembelian berhasil! Total pembelian: " + totalPenjualan);
            System.out.println("====================");
        } else {
            System.out.println("Pembelian dibatalkan.");
            System.out.println("====================");
        }
    }

    void MenuAdmin() {
        int pilihan;
        while (true) {
            System.out.println("\n\t========================");
            System.out.println("\t   =   Menu Admin   =");
            System.out.println("\t========================");
            System.out.println("\n====================");
            System.out.println("1. Stok Buku");
            System.out.println("2. List Buku");
            System.out.println("3. Cek Hasil");
            System.out.println("4. Relogin");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu : ");
            pilihan = inp.nextInt();
            System.out.println("====================");

            switch (pilihan) {
                case 1:
                    stokBuku();
                    break;
                case 2:
                    Listbuku();
                    break;
                case 3:
                    sorttotalHarga(detailPenjualan);
                    break;
                case 4:
                    menu();
                    break;
                case 0:
                    System.out.println("keluar aplikasi");
                    System.exit(0);
                default:
                    System.out.println("pilihan tidak valid");
            }
        }
    }

    void stokBuku() {
        int pilih;
        while (true) {
            System.out.println("\n====================");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Tambah Stok Buku");
            System.out.println("4. Lihat Stok");
            System.out.println("5. Kembali ");
            System.out.print("Pilih Menu : ");
            pilih = inp.nextInt();
            System.out.println("====================");

            switch (pilih) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                System.out.println("\n====================");
                    System.out.print("Tulis judul buku yang ingin dihapus : ");
                    inp.nextLine();
                    String titleToRemove = inp.nextLine();
                    hapusBuku(titleToRemove);
                    break;
                case 3:
                    tambahStok();
                    break;
                case 4:
                    lihatStok();
                    break;
                case 5:
                    MenuAdmin();
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    void tambahBuku() {
        System.out.println("\n====================");
        System.out.print("Nomor buku: ");
        int nomor = inp.nextInt();
        System.out.print("Jenis buku: ");
        String jenisBuku = inp.next();
        inp.nextLine();
        System.out.print("Judul buku: ");
        String judulBuku = inp.nextLine();
        System.out.print("Pencipta buku: ");
        String pencipta = inp.nextLine();
        System.out.print("Tahun keluaran: ");
        int tahun = inp.nextInt();
        System.out.print("Harga buku: ");
        int harga = inp.nextInt();
        System.out.print("Tambah buku: ");
        int tambahBuku = inp.nextInt();

        Buku newBook = new Buku(nomor, jenisBuku, judulBuku, pencipta, tahun, harga, tambahBuku);
        newBook.nomorBuku = nomor;
        newBook.JenisBuku = jenisBuku;
        newBook.JudulBuku = judulBuku;
        newBook.penciptaBuku = pencipta;
        newBook.tahunKeluaran = tahun;
        newBook.hargaBuku = harga;
        newBook.stokBuku = tambahBuku;
        bukuList.add(newBook);

        System.out.println("\nBuku baru berhasil ditambahkan: " + jenisBuku + " - " + judulBuku + " - Stok: " + tambahBuku);
        System.out.println("Jenis: " + jenisBuku + " - Judul: " + judulBuku + " - Pencipta: " + pencipta + " - Tahun: " + tahun);
        System.out.println("Harga buku: Rp." + harga + " - Stok buku: " + tambahBuku);
        System.out.println("====================");
    }

    void hapusBuku(String judul) {
        Buku bookToRemove = null;
        for (Buku buku : bukuList) {
            if (buku.JudulBuku.equals(judul)) {
                bookToRemove = buku;
                break;
            }
        }
        if (bookToRemove != null) {
            bukuList.remove(bookToRemove);
            System.out.println();
            System.out.println("buku berhasil dikurangi: " + judul);
            System.out.println("====================");
        } else {
            System.out.println("Buku tidak ada: " + judul);
            System.out.println("====================");
        }
    }

    void tambahStok() {
        tampilListBuku();
        System.out.println("\n====================");
        System.out.print("Masukkan nomor buku yang ingin ditambah stoknya : ");
        int nomorBuku = inp.nextInt();

        for (Buku buku : bukuList) {
            if (buku.nomorBuku == nomorBuku) {
                System.out.println("====================");
                System.out.println("Nomer Buku: " + buku.nomorBuku);
                System.out.println("Jenis Buku: " + buku.JenisBuku);
                System.out.println("Nama Buku: " + buku.JudulBuku);
                System.out.println("Pencipta Buku: " + buku.penciptaBuku);
                System.out.println("Tahun Keluaran: " + buku.tahunKeluaran);
                System.out.println("Harga Buku: " + buku.hargaBuku);
                System.out.println("Stok Buku Sebelumnya: " + buku.stokBuku);
                System.out.println("====================");

                System.out.print("Masukkan jumlah stok yang ingin ditambah : ");
                int tambahStok = inp.nextInt();

                buku.stokBuku += tambahStok;
                System.out.println("Stok buku berhasil ditambahkan. Stok Sekarang : " + buku.stokBuku);
                System.out.println("====================");
                return;
            }
        }
        System.out.println("Nomer buku yang anda input tidak tersedia");
    }

    void lihatStok() {
        System.out.println("\n====================");
        System.out.println("lihat Stok :");
        System.out.println();
        for (Buku b : bukuList) {
            System.out.println("Jenis Buku: " + b.JenisBuku + " - Judul Buku: " + b.JudulBuku
                    + " - Stok Buku: " + b.stokBuku);
        }
        System.out.println("====================");
    }

    void Listbuku() {
        System.out.println("\n====================");
        System.out.println("List Buku :");
        System.out.println();
        for (Buku book : bukuList) {
            System.out
                    .println("Jenis Buku: " + book.JenisBuku + " - Judul Buku: " + book.JudulBuku + " - Stok Buku: "
                            + book.stokBuku);
        }
        System.out.println("====================");
    }

    class MyObjectComparatorBytotalharga implements Comparator<Penjualan> {
        @Override
        public int compare(Penjualan obj1, Penjualan obj2) {
            return Integer.compare(obj1.totalHarga, obj2.totalHarga);
        }
    }

    void sorttotalHarga(ArrayList<Penjualan> arr) {
        System.out.println("\n====================");
        System.out.println("Cek Hasil :");
        Collections.sort(arr, new MyObjectComparatorBytotalharga());
        traversal(arr, " dari harga minimal");
        Collections.reverse(arr);
        traversal(arr, " dari harga maksimal");
        System.out.println("====================");
    }

    void traversal(ArrayList<Penjualan> data, String jenis) {
        System.out.println("\nData penjualan" + jenis + " : ");
        for (Penjualan penjualan : detailPenjualan) {
            System.out.println(penjualan);
        }
    }
}
