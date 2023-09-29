package test;
import java.sql.*;
import java.util.Vector;
public class Pooling {

	public String url,uName,pWord;
	public Pooling(String url,String uName ,String pWord) {
		this.url=url;
		this.uName=uName;
		this.pWord=pWord;
	}
	Vector <Connection>v=new Vector<Connection>();
	public void createConnection()
	{
		try {
			while(v.size()<5)
			{
				Connection con=DriverManager.getConnection(url, uName, pWord);
				
				System.out.println(con);
				v.add(con);
			}
			if(v.size()==5)
				System.out.println("Pool is full");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized Connection useConnection()
	{
		Connection con=v.elementAt(0);
		v.remove(0);
		return con;
	}
	public synchronized void returnConnection(Connection con) {
		v.add(con);
		System.out.println("connection addred to pool");
		
	}
	

}
