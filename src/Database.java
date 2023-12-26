import java.io.*;
import java.sql.*;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minimarket";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    public static void main(String[] args) {
        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            Integer pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertData();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    deleteData();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        String sql = "SELECT * FROM pelanggan";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|    DATA PELANGGAN DI INDOMARET   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                String no_faktur = rs.getString("no_faktur");
                String nama = rs.getString("nama");
                String no_hp = rs.getString("no_hp");
                String alamat = rs.getString("alamat");

                
                System.out.println(String.format("%s. %s -- %s -- (%s)", no_faktur, nama, alamat, no_hp));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void insertData() {
        try {
            // ambil input dari user
            System.out.print("Nomor Faktur : ");
            String no_faktur = input.readLine().trim();
            System.out.print("Nama Pelanggan : ");
            String nama = input.readLine().trim();
            System.out.print("Nomor Hp : ");
            String no_hp = input.readLine().trim();
            System.out.print("Alamat : ");
            String alamat = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO pelanggan (no_faktur, nama, no_hp, alamat) VALUE('%s', '%s', '%s', '%s' )";
            sql = String.format(sql, no_faktur, nama, no_hp, alamat);

            // simpan Data
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updateData() {
        try {
            
            // ambil input dari user
            System.out.print("Faktur yang mau diedit : ");
            String no_faktur = (input.readLine());
            System.out.print("Nama Pelanggan : ");
            String nama = input.readLine().trim();
            System.out.print("Nomor HP : ");
            String no_hp = input.readLine().trim();
            System.out.print("Alamat : ");
            String alamat = input.readLine().trim();

            // query update
            String sql = "UPDATE pelanggan SET nama='%s', no_hp='%s', alamat='%s' WHERE no_faktur='%s'";
            sql = String.format(sql, nama, no_hp, alamat, no_faktur);

            // update data Data
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteData() {
        try {
            
            // ambil input dari user
            System.out.print("Faktur yang mau dihapus : ");
            String no_faktur = (input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM pelanggan WHERE no_faktur='%s'", no_faktur);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


