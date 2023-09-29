package test;
import java.sql.*;
public class DBCon14 {

	public static void main(String[] args) {
		try {
			
			Pooling p =new Pooling("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			p.createConnection();
			System.out.println("pool size:"+p.v.size());
			Connection con=p.useConnection();
			PreparedStatement ps=con.prepareStatement("select * from Product41");
			System.out.println("Pool size:"+p.v.size());
			System.out.println("Display using"+con);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getInt(4));
			}
			p.returnConnection(con);
			System.out.println("Pool size::"+p.v.size());
			
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
