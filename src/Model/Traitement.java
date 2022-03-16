package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Traitement {

	private Statement st;
	public ResultSet res;
	String sql;
	PreparedStatement pstmt;

	public Traitement() {
	
	}

	public int inscription(Abonne a) {
		int n = 0;
		try {

			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();

			sql = "insert into abonne (nom,prenom,tele,paiement) values(?,?,?,?) ";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setString(1, a.getNom());
			pstmt.setString(2, a.getPrenom());
			pstmt.setString(3, a.getTele());
			pstmt.setDouble(4, a.getPaiement());
			n = pstmt.executeUpdate();

			res = st.executeQuery("select id from abonne order by id desc limit 1 ");

			sql = "insert into date(date,idAbonne) values(?,?)";
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			pstmt.setDate(1, sqlDate);
			res.next();
			pstmt.setInt(2, res.getInt(1));
			n = pstmt.executeUpdate();
		
				ConnexionDB.cnx.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return n;
	}
	
	public Abonne getAbonne(int id) {
		Abonne a=null;
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM `abonne` where id="+id);
			if(res.next()) {
				a=new Abonne();
				
			a.setId(res.getInt(1));
			a.setNom(res.getString(2));
			a.setPrenom(res.getString(3));
			a.setTele(res.getString(4));
			a.setPaiement(res.getDouble(5));
			Statement st1=(Statement) (ConnexionDB.cnx).createStatement();
			ResultSet res1=st1.executeQuery("SELECT * FROM `date` where idAbonne="+id+" order by idDate desc");
			
			ArrayList<Date> dates=new ArrayList<Date>(); 
			
			ArrayList<Integer> indexDate=new ArrayList<Integer>(); 
			while(res1.next()) {
			 	dates.add(res1.getDate(2));
			 	indexDate.add(res1.getInt(1));
			 	
			}
			a.setIndexDate(indexDate);
			a.setDate(dates);
			}
			ConnexionDB.cnx.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnexionDB.cnx.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return a;
	}
	
	
	public void payer(int id) {
		sql = "insert into date(date,idAbonne) values(?,?)";
	
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt.setDate(1, sqlDate);
		
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
	}
	
	public ArrayList<Abonne> getAbonnes() {
		
		ArrayList<Abonne> list=new ArrayList<Abonne>();
		
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res=st.executeQuery("select * from abonne  ");
			while(res.next()){
				Abonne a=new Abonne();
				
			a.setId(res.getInt(1));
			a.setNom(res.getString(2));
			a.setPrenom(res.getString(3));
			a.setTele(res.getString(4));
			a.setPaiement(res.getDouble(5));
			Statement st1=(Statement) (ConnexionDB.cnx).createStatement();
			ResultSet res1=st1.executeQuery("SELECT * FROM `date` where idAbonne="+a.getId()+" order by idDate desc");
		
			ArrayList<Date> dates=new ArrayList<Date>(); 
		
			ArrayList<Integer> indexDate=new ArrayList<Integer>(); 
			while(res1.next()) {
			 	dates.add(res1.getDate(2));
			 	indexDate.add(res1.getInt(1));
			 	
			}
			a.setIndexDate(indexDate);
			a.setDate(dates);
			list.add(a);
			}
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return list;
	}
	
	public int supprimerAbonne(int id) {
		int n=0;
		Abonne a=getAbonne(id);

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			sql = "Delete from date where idAbonne="+a.getId();
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			n=pstmt.executeUpdate();
			sql = "Delete from abonne where id="+a.getId();
			pstmt = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			n=pstmt.executeUpdate();
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return n;
	}
	
	public int modifierAbonne(Abonne a) {
		int n=0;
		

		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			sql = "update abonne set nom=?,prenom=?,paiement=?,tele=? where id=?";
			PreparedStatement pstmt1 = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt1.setString(1, a.getNom());
			pstmt1.setString(2, a.getPrenom());
			pstmt1.setDouble(3, a.getPaiement());
			pstmt1.setString(4, a.getTele());
			pstmt1.setInt(5, a.getId());
			n=pstmt1.executeUpdate();
			
			ArrayList<Date> dates=a.getDate();
			ArrayList<Integer> indexDate=a.getIndexDate();
			for(Date d:dates) {
			sql = "update date set date=? where idDate=?";
			pstmt1 = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt1.setDate(1, d);
			System.out.println(indexDate.get(0));
			pstmt1.setInt(2, a.getIndexDate().get(dates.indexOf(d)));
			n=pstmt1.executeUpdate();
			}
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return n;
	}
	
	public void truncate() {
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			st.executeUpdate("truncate table date");
			st.executeUpdate("truncate table abonne");
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
	}
	
	
	public Abonne supprimerDate(Abonne b,int index) {
		Abonne a=b;
		
		try {
			ConnexionDB.getconnection();
			
			sql = "delete from date where idDate=? ";
			PreparedStatement pstmt1 = (PreparedStatement) (ConnexionDB.cnx).prepareStatement(sql);
			pstmt1.setInt(1, index);
			pstmt1.executeUpdate();
			
			ResultSet res1=st.executeQuery("SELECT * FROM `date` where idAbonne="+b.getId()+" order by idDate desc");
			ArrayList<Date> dates=new ArrayList<Date>(); 
			ArrayList<Integer> indexDate=new ArrayList<Integer>(); 
			while(res1.next()) {
			 	dates.add(res1.getDate(2));
			 	indexDate.add(res1.getInt(1));
			 	
			}
			a.setIndexDate(indexDate);
			a.setDate(dates);
			ConnexionDB.cnx.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return a;
	}
	
	
	public int connect(String pass) {
		
		ResultSet res1;
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			res1 = st.executeQuery("SELECT * FROM `password` where password='"+pass+"'");
			
			if(res1.next()) { 
				ConnexionDB.cnx.close();
				return 1;}
			else {
				ConnexionDB.cnx.close();
				return 0;}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return 0;
	}
	
	public int changePass(String pass1,String pass2) {
		try {
			ConnexionDB.getconnection();
			st = (Statement) (ConnexionDB.cnx).createStatement();
			int n=st.executeUpdate("update password set password='"+pass2+"' where password='"+pass1+"' ");
		System.out.println(n);
			ConnexionDB.cnx.close();
		return n;
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ConnexionDB.cnx.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		return 0;
		
	}
	
	@SuppressWarnings("deprecation")
	public static int isPaye(Abonne a) {
		java.util.Date dateAbonne=new java.util.Date(a.getDate().get(0).getTime());
		java.util.Date dateDaye=new java.util.Date();
		dateAbonne.setMonth(dateAbonne.getMonth()+1);
		System.out.println(dateAbonne);
		if(dateAbonne.after(dateDaye) || dateAbonne.equals(dateDaye) ) return 1;
		return 0;
	}
}
