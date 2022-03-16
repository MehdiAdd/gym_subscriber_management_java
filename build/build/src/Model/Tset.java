package Model;

public class Tset {

	public static void main(String[] args) {
		Abonne a;
		
		Traitement t=new Traitement();
	/*	ArrayList<Abonne> list=t.getAbonnes();

		a=t.getAbonne(1);
		System.out.println(a.toString());
		ArrayList<Date> d=a.getDate();
		java.util.Date date1;
		
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2021");
			d.set(a.getIndexDate().indexOf(16), new java.sql.Date(date1.getTime()));
			System.out.println(a.getIndexDate().indexOf(1));
			a.setDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		a.setNom("mehdi");
		a.setPrenom("adnani");
		a.setTele("0602050608");
		t.modifierAbonne(a);
		//t.payer(1);
		a=t.getAbonne(1);
		System.out.println(a.toString());
		Abonne b=t.supprimerDate(a, 13);
		System.out.println(b.toString());
		System.out.println(t.changePass("men","menouali"));*/
		a=t.getAbonne(1);
		System.out.println(a.toString());
	/*	Calendar cal = Calendar.getInstance();
        cal.setTime(a.getDate().get(0));
        a.getDate().get(0).setMonth(a.getDate().get(0).getMonth()+1);*/
	/*	ArrayList<Date> d=a.getDate();
		java.util.Date date1;
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse("8-12-2020");
			d.set(a.getIndexDate().indexOf(18), new java.sql.Date(date1.getTime()));
			
			a.setDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		t.modifierAbonne(a);
        
		System.out.println(t.isPaye(a));*/

	}
	}
