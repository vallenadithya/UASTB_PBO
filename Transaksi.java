import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

//Inheritance dan Interface (Child Class)
public class Transaksi extends Display implements Penjualan{
    //Constructor
    public Transaksi() {
    }

    Scanner inputUser = new Scanner(System.in);

    @Override
    public void lihatdata() throws SQLException {
		String text1 = "\n===Daftar Seluruh Data Transaksi===";
		//String method
        System.out.println(text1.toUpperCase());
		//Inheritance
        displaydata();
	}

    @Override
    public void tambahdata() throws SQLException {
		String text2 = "\n===Tambah Data Transaksi===";
		//Method String
        System.out.println(text2.toUpperCase());
		
        //Exception
            try {
            System.out.println("No. Faktur: ");
            faktur = inputUser.nextLine();

            System.out.println("Nama Barang: ");
            namaBrg = inputUser.nextLine();

            System.out.println("Harga Barang: ");
            hargaBrg = inputUser.nextInt();
            
            System.out.println("Jumlah Barang: ");
            jumlahBrg = inputUser.nextInt();
            //Proses matematis
            sub=jumlahBrg*hargaBrg;
            System.out.println("Sub Total Harga: "+sub);

            //Percabangan
            if(sub>1000000){
                dis=20;
            }
            else if(sub>700000&&sub<=1000000){
                dis=15;
            }
            else if(sub>500000&&sub<=700000){
                dis=10;
            }
            else if(sub>250000&&sub<=500000){
                dis=5;
            }
            else{
                dis=0;
            }
            System.out.println("Discount: "+dis+"%");

            //Proses matematis
            total=sub-((sub*dis)/100);
            System.out.println("Total Harga: "+total);

            //Pengolahan database
            String sql = "INSERT INTO listtransaksi (no_faktur, nama_barang, harga, jumlah, sub_total, diskon, total) VALUES ('"+faktur+"','"+namaBrg+"','"+hargaBrg+"','"+jumlahBrg+"','"+sub+"','"+dis+"','"+total+"')";           			
            conn = DriverManager.getConnection(url, "root", "");    
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");

            } catch (SQLException e) {
                System.err.println("Terjadi kesalahan input data");
            } catch (InputMismatchException e) {
            	System.err.println("Inputlah dengan angka saja");
            }
    }

    @Override
    public void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Transaksi===";
        //Method String
		System.out.println(text3.toUpperCase());
		
            try {
                //Overloading
                lihatdata();
                System.out.print("Masukkan No. Faktur yang akan di ubah atau update : ");
                String faktur = inputUser.nextLine();             

                //Pengolahan database
                String sql = "SELECT * FROM listtransaksi WHERE no_faktur = " +faktur;
                conn = DriverManager.getConnection(url, "root", "");
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                //percabangan
                if(result.next()){
                    System.out.print("Nama Barang ["+result.getString("nama_barang")+"]\t: ");
                    String namaBrg = inputUser.nextLine();
                    
                    //Pengolahan database
                    sql = "UPDATE transaksi SET nama_barang='"+namaBrg+"' WHERE no_faktur='"+faktur+"'";
                    
                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil memperbaharui data transaksi (No.faktur "+faktur+")");
                    }
                }
                //jdbc statement
                statement.close();        
            } 
            //Error Handling
            catch (SQLException e) {
                System.err.println("Terjadi kesalahan dalam mengedit data");
                System.err.println(e.getMessage());
            }
        } 

        @Override
        public void hapusdata() {
            String text4 = "\n===Hapus Data Transaksi===";
            //Method String
            System.out.println(text4.toUpperCase());

                //try catch error handling
                try{
                    //Overloading
                    lihatdata();
                    System.out.print("Ketik No. Faktur Transaksi yang akan Anda Hapus : ");
                    String faktur = inputUser.nextLine();
                    
                    //Pengolahan database
                    String sql = "DELETE FROM listtransaksi WHERE no_faktur = "+faktur;
                    conn = DriverManager.getConnection(url, "root", "");
                    Statement statement = conn.createStatement();
                    
                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil menghapus data transaksi (No.faktur "+faktur+")");
                    }
                    }
                    catch(SQLException e){
                        System.out.println("Terjadi kesalahan dalam menghapus data");
                            }   
                    }
 
        @Override
        public void caridata () throws SQLException {
            String text5 = "\n===Cari Data Transaksi===";
            //Method String
            System.out.println(text5.toUpperCase());
            
                System.out.print("Masukkan Nama Barang : ");
                
                String keyword = inputUser.nextLine();
                
                //Pengolahan database
                String sql = "SELECT * FROM listtransaksi WHERE nama_barang LIKE '%"+keyword+"%'";
                conn = DriverManager.getConnection(url, "root", "");
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql); 
                //Perulangan
                while(result.next()){
                    System.out.print("\nNo. Faktur\t: ");
                    System.out.print(result.getString("no_faktur"));
                    System.out.print("\nNama Barang\t: ");
                    System.out.print(result.getString("nama_barang"));
                    System.out.print("\nHarga Barang\t: Rp ");
                    System.out.print(result.getInt("harga"));
                    System.out.print("\nJumlah Barang\t: ");
                    System.out.print(result.getInt("jumlah"));
                    System.out.print("\nSubtotal\t: Rp ");
                    System.out.print(result.getInt("sub_total"));
                    System.out.print("\nDiskon\t\t: "+result.getInt("diskon")+"%");
                    System.out.print("\nTotal Harga\t: Rp ");
                    System.out.print(result.getInt("total"));
                    System.out.print("\n");
                }
            }
            
}