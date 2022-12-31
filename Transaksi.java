import java.util.InputMismatchException;
import java.util.Scanner;
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
        //Exception
            try {
            System.out.println("Kode Barang: ");
            kode = inputUser.nextLine();

            System.out.println("Nama Barang: ");
            namaBrg = inputUser.nextLine();

            System.out.println("Harga Jual: ");
            jual = inputUser.nextInt();
            
            System.out.println("Jumlah Barang: ");
            jumlah = inputUser.nextInt();
            
            System.out.println("Harga Beli: ");
            beli = inputUser.nextInt();
            
            //Proses matematis
            total=jual*jumlah;
            System.out.println("Total Pendapatan: "+total);
            //proses matematis
            untung=(jual-beli)*jumlah;
            System.out.println("Total Keuntungan: "+untung);

            //Pengolahan database
            String sql = "INSERT INTO listtransaksi (no_barang, nama_barang, harga_jual, jumlah, harga_beli, total_pendapatan, total_untung) VALUES ('"+kode+"','"+namaBrg+"','"+jual+"','"+jumlah+"','"+beli+"','"+total+"','"+untung+"')";           			
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
		String text3 = "\n===Ubah Nama Barang===";
        //Method String
		System.out.println(text3.toUpperCase());
		
            try {
                //overload
                lihatdata();
                System.out.print("Masukkan Kode Barang yang akan di ubah atau update : ");
                kode = inputUser.nextLine();     

                    System.out.print("Nama Barang \t: ");
                    namaBrg = inputUser.nextLine();
                    //Pengolahan database
                    String sql = "UPDATE listtransaksi SET nama_barang='"+namaBrg+"' WHERE no_barang='"+kode+"'";
                    conn = DriverManager.getConnection(url, "root", "");
                    Statement statement = conn.createStatement();

                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil memperbaharui data transaksi (Kode Barang "+kode+")");
                    }
                statement.close();        
            } 
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
                    System.out.print("Ketik Kode Barang Transaksi yang akan Anda Hapus : ");
                    kode = inputUser.nextLine();
                    
                    //Pengolahan database
                    String sql = "DELETE FROM listtransaksi WHERE no_barang = "+kode;
                    conn = DriverManager.getConnection(url, "root", "");
                    Statement statement = conn.createStatement();
                    
                    if(statement.executeUpdate(sql) > 0){
                        System.out.println("Berhasil menghapus data transaksi (Kode Barang "+kode+")");}
                        else{
                            System.out.println("Input Salah");
                        }
                    }
                    catch(SQLException e){
                            System.out.println(e);
                    }    
                }
 
        @Override
        public void caridata () throws SQLException {
            String text5 = "\n===Cari Data Transaksi===";
            //Method String
            System.out.println(text5.toUpperCase());
            
                System.out.print("Masukkan Kode Barang : ");
                String keyword = inputUser.nextLine();
                
                //Pengolahan database
                String sql = "SELECT * FROM listtransaksi WHERE no_barang LIKE '%"+keyword+"%'";
                conn = DriverManager.getConnection(url, "root", "");
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql); 
                //Perulangan
                while(result.next()){
                    System.out.print("\nKode Barang\t: ");
                    System.out.print(result.getString("no_barang"));
                    System.out.print("\nNama Barang\t: ");
                    System.out.print(result.getString("nama_barang"));
                    System.out.print("\nHarga Jual\t: Rp ");
                    System.out.print(result.getInt("harga_jual"));
                    System.out.print("\nJumlah Barang\t: ");
                    System.out.print(result.getInt("jumlah"));
                    System.out.print("\nHarga Beli\t: Rp ");
                    System.out.print(result.getInt("harga_beli"));
                    System.out.print("\nTotal Pendapatan: Rp\t"+result.getInt("total_pendapatan"));
                    System.out.print("\nTotal Keuntungan: Rp ");
                    System.out.print(result.getInt("total_untung"));
                    System.out.print("\n\n");
                }
            }
        }