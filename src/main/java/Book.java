import java.util.List;
import org.sql2o.*;

public class Book {
  private int id;
  private String title;
  private String author;
  private int copy_id;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

    public int getCopy_id() {
      return copy_id;
    }


  public Book(String title, String author, int copy_id) {
    this.title = title;
    this.author = author;
    this.copy_id = copy_id;

  }

  @Override
  public boolean equals(Object otherBook){
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
             this.getId() == newBook.getId()&&
             this.getAuthor().equals(newBook.getAuthor()) &&
             this.getCopy_id() == newBook.getCopy_id();
    }
  }


  public static List<Book> all() {
    String sql = "SELECT * FROM books";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }
  //
  // public void save() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO tasks(description) VALUES (:description)";
  //     this.id = (int) con.createQuery(sql, true)
  //       .addParameter("description", description)
  //       .executeUpdate()
  //       .getKey();
  //   }
  // }
  //
  // public static Task find(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM tasks where id=:id";
  //     Task task = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Task.class);
  //     return task;
  //   }
  // }
  //
  // public void update(String description) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE tasks SET description = :description) WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("description", description)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String sql = "DELETE FROM tasks WHERE id = :id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
}
