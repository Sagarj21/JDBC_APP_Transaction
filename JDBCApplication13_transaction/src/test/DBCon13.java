package test;
import java.util.*;
import java.sql.*;
public class DBCon13 {

	public static void main(String[] args) {
		
         try {
        	 Scanner s=new Scanner(System.in);
        	 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
     		//loading driver automatically and creating connection
        	 PreparedStatement ps1=con.prepareStatement("select * from BANK41 where accno=? ");
        	 PreparedStatement ps2=con.prepareStatement("update BANK41 set balance=balance+? where accno=? ");
        	 System.out.println("Commit status    "+con.getAutoCommit());
        	 con.setAutoCommit(false);
        	 System.out.println("Commit status    "+con.getAutoCommit());
        	 Savepoint sp=con.setSavepoint();
        	 System.out.println(" enter Home account");
        	 long hAccno=s.nextLong();
        	 ps1.setLong(1, hAccno);
        	 ResultSet rs1=ps1.executeQuery();
        	 if(rs1.next())
        	 {
        		 float bal=rs1.getFloat(3);
        		 System.out.println("ENter beneficiary acc no");
        		 long bAccno=s.nextLong();
        		 ps1.setLong(1,bAccno);
        		 ResultSet rs2=ps1.executeQuery();
        		 if(rs2.next())
        		 {
        			 System.out.println("Enter the ammount to transfer");
        			 int amt=s.nextInt();
        			 if(amt<=bal)
        			 {
        				ps2.setFloat(1, -amt);
        				ps2.setLong(2,hAccno);
        				int i=ps2.executeUpdate();
        				
        				ps2.setFloat(1, amt);
        				ps2.setLong(2,bAccno);
        				int j=ps2.executeUpdate();
        				
        				
        				if(i==1 && j==1)
        				{
        					con.commit();
        				    System.out.println("transatyion sucessful");
        				}else
        				{
        					System.out.println("Transation failed");
        					con.rollback(sp);
        				}
        			 }else
        			 {
        				 System.out.println("insufficient fund");
        			 }
        			 
        		 }else
        		 {
        			 System.out.println("invalid acc no");
        		 }
        	 }else {
        		 System.out.println("invalid acno");
        	 }
        	 
        	 
        	 
         }catch(Exception e)
         {
        	 System.out.println(e.getMessage());
         }
	}

}
