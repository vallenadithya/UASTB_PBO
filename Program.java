import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;

//Driver Class
public class Program{

    static Connection conn;    
    public static void main(String[] args){
            //Collection Framework
            LinkedList<String> toko = new LinkedList<String>();
            toko.add("Toko Minimarket");
            toko.add("Padang");
            System.out.println(toko);
            
            //Exception
            try (
                Scanner terimaInput = new Scanner (System.in)) {
                    String pilihanUser;
                    boolean lanjut = true;

                    //Pengolahan database
                    String url = "jdbc:mysql://localhost:3306/transaksi";
                    //Exception
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    	conn = DriverManager.getConnection(url,"root","");
                    	System.out.println("\nClass Driver ditemukan");
                    	
                        Transaksi goods = new Transaksi();
                    	
                        //Perulangan
                        while (lanjut==true) {
                    		System.out.println("\n------------------");
                    		System.out.println("Database Transaksi");
                    		System.out.println("------------------");
                    		System.out.println("1. Lihat Data Transaksi");
                    		System.out.println("2. Tambah Data Transaksi");
                    		System.out.println("3. Ubah Data Transaksi");
                    		System.out.println("4. Hapus Data Transaksi");
                    		System.out.println("5. Cari Data Transaksi");
                            System.out.println("6. Keluar");
                    		
                    		System.out.print("\nPilihan anda (1/2/3/4/5/6): ");
                    		pilihanUser = terimaInput.nextLine();
                    		
                            //Percabangan
                    		switch (pilihanUser) {
                    		case "1":
                                goods.lihatdata();
                    			break;
                    		case "2":
                                goods.tambahdata();
                    			break;
                    		case "3":
                                goods.ubahdata();
                    			break;
                    		case "4":
                                goods.hapusdata();
                    			break;
                    		case "5":
                                goods.caridata();
                    			break;
                            case "6":
                    			lanjut=false;
                    			break;    
                    		default:
                    			System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-6]");
                    		}
                    		
                    	}
                        // Method Date
                        Date tgl = new Date();
                        String str = String.format("\nTanggal/Waktu sekarang: %tc", tgl);
                        System.out.println(str);
                    	System.out.println("\nBye.... Selamat Berjumpa Kembali!!!");
                    	
                    }
                    catch(ClassNotFoundException ex) {
                    	System.err.println("Driver Error");
                    	System.exit(0);
                    }
                    catch(SQLException e){
                    	System.err.println("Tidak berhasil koneksi");
                    }
                }
            }
}