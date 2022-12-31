import java.sql.*;

//Interface
public interface Penjualan{
    public void lihatdata() throws SQLException;
    public void tambahdata()throws SQLException;
    public void ubahdata() throws SQLException;
    public void hapusdata();
    public void caridata() throws SQLException;
}