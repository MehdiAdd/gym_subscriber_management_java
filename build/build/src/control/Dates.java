package control;

import java.sql.Date;

public class Dates {
 private int id;
 private Date date;
 
  public Dates(int id,Date date) {
	  this.id=id;
	  this.date=date;
  }

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}
}
