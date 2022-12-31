import com.mysql.cj.protocol.Resultset;
import java.sql.*;

public class Display{
    static Connection conn;    
    String url = "jdbc:mysql://localhost:3306/transaksi";

    public void displaydata() throws SQLException {
        //Pengolahan database
        String sql ="SELECT * FROM listtransaksi";
		conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		//Perulangan
		while(result.next()){
			System.out.print("\nNo. Faktur\t: ");
            System.out.print(result.getInt("no_faktur"));
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