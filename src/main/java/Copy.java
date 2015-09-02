import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Copy {
  private int id;
  private String title;


//   public int getId() {
//     return id;
//   }
//
//   public String getTitle() {
//     return title;
//   }
//
//
//   public Copy(String title) {
//     this.title = title;
//
//   }
//
//   @Override
//   public boolean equals(Object otherCopy){
//     if (!(otherCopy instanceof Copy)) {
//       return false;
//     } else {
//       Copy newCopy = (Copy) otherCopy;
//       return this.getTitle().equals(newCopy.getTitle()) &&
//              this.getId() == newCopy.getId();
//   }
// }
//
//   public static List<Copy> all() {
//     String sql = "SELECT * FROM books";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Copy.class);
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO books (title) VALUES (:title)";
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("title", title)
//         .executeUpdate()
//         .getKey();
//     }
//   }
//
//   public static Copy find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM books where id=:id";
//       Copy book = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Copy.class);
//       return book;
//     }
//   }
//
//   public void update(String title) {
//     this.title = title;
//     try(Connection con = DB.sql2o.open()) {
//
//       String sql = "UPDATE books SET title = :title WHERE id = :id";
//       con.createQuery(sql)
//         .addParameter("title", title)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//     String sql = "DELETE FROM books WHERE id = :id;";
//       con.createQuery(sql)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//
//   public void addAuthor(Author author) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO books_authors (author_id, book_id) VALUES (:author_id, :book_id)";
//       con.createQuery(sql)
//       .addParameter("author_id", author.getId())
//       .addParameter("book_id", this.getId())
//       .executeUpdate();
//     }
//   }
//
//   public List<Author> getAuthors() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT authors.* FROM books JOIN books_authors ON (books_authors.book_id = books.id) JOIN authors ON (books_authors.author_id = authors.id) WHERE book_id = :book_id";
//       List<Author> authors = con.createQuery(sql)
//       .addParameter("book_id", this.getId())
//       .executeAndFetch(Author.class);
//       return authors;
//
//     }
//
//   }
}
