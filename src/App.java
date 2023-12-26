import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Kasir kasir = new Kasir();
        kasir.Login(); 
        
        Scanner scanner = new Scanner(System.in); 

        try {
            System.out.print("Masukkan Nomor Faktur: ");
            String nomorFaktur = scanner.nextLine();

            System.out.print("Masukkan Nama Pelanggan: ");
            String namaPelanggan = scanner.nextLine();

            System.out.print("Masukkan Nomor HP: ");
            String nomorHp = scanner.nextLine();

            System.out.print("Masukkan Alamat: ");
            String alamat = scanner.nextLine();

            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang: ");
            Integer hargaBarang = Integer.parseInt(scanner.nextLine());
            if (hargaBarang > 500000) {
                throw new IllegalArgumentException("Harga barang tidak boleh lebih dari 500000");
            }

            System.out.print("Masukkan Jumlah Beli: ");
            Integer jumlahBeli = Integer.parseInt(scanner.nextLine());
            if (jumlahBeli > 100) {
                throw new IllegalArgumentException("Jumlah beli tidak boleh lebih dari 100");
            }

            System.out.print("Masukkan Metode Pembayaran (hanya untuk FakturOnline): ");
            String metodePembayaran = scanner.nextLine();

            faktur faktur;
            if (metodePembayaran.isEmpty()) {
                faktur = new faktur(nomorFaktur, namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            } else {
                faktur = new fakturOnline(nomorFaktur, namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli, metodePembayaran);
            }

            if (faktur instanceof fakturOnline) {
                ((fakturOnline) faktur).tampilkanDetailFakturOnline();
            } else {
                faktur.tampilkanDetailFaktur();
            }

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Kasir : " + kasir.getUsernameBenar());
            scanner.close();
        }
    }
}

