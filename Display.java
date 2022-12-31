import com.mysql.cj.protocol.Resultset;
import java.sql.*;

//Inheritance (parent Class)
public class Display{
    static Connection conn;    
    String url = "jdbc:mysql://localhost:3306/transaksi";
    public  String namaBrg, kode;
    public Integer jumlah,jual,beli,total,untung;

    public void displaydata() throws SQLException {
        //Pengolahan database
        String sql ="SELECT * FROM listtransaksi";
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
            System.out.print("\nTotal Pendapatan: Rp"+result.getInt("total_pendapatan"));
            System.out.print("\nTotal Keuntungan: Rp ");
            System.out.print(result.getInt("total_untung"));
            System.out.print("\n\n");
		}
    }
}