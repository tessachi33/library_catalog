import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Patron {
  private int id;
  private String name;
  private String phone_number;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phone_number;
  }


  public Patron(String name, String phone_number) {
    this.name = name;
    this.phone_number = phone_number;

  }

  @Override
  public boolean equals(Object otherPatron){
    if (!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getName().equals(newPatron.getName()) &&
             this.getPhoneNumber().equals(newPatron.getPhoneNumber()) &&
             this.getId() == newPatron.getId();
  }
}

  public static List<Patron> all() {
    String sql = "SELECT * FROM patrons";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons (name, phone_number) VALUES (:name, :phone_number)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("phone_number", phone_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patron find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons where id=:id";
      Patron patron = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patron.class);
      return patron;
    }
  }

  public void update(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {

      String sql = "UPDATE patrons SET (name, phone_number) = (:name, :phone_number) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("phone_number", phone_number)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM patrons WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addCopy(Copy Copy) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO checkouts (Copy_id, patron_id) VALUES (:Copy_id, :patron_id)";
      con.createQuery(sql)
      .addParameter("Copy_id", this.getId())
      .addParameter("patron_id", this.getId())
      .executeUpdate();
    }
  }

  public List<Copy> getCopies() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT Copies.* FROM patrons JOIN checkouts ON (checkouts.patron_id = patrons.id) JOIN Copies ON (checkouts.Copy_id = Copies.id) WHERE patron_id = :patron_id";
      List<Copy> Copies = con.createQuery(sql)
      .addParameter("patron_id", this.getId())
      .executeAndFetch(Copy.class);
      return Copies;

    }

  }
}
