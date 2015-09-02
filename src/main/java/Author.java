import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;


public class Author {
  private int id;
  private String name;


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }


  public Author(String name) {
    this.name = name;

  }

  @Override
  public boolean equals(Object otherAuthor){
    if (!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) otherAuthor;
      return this.getName().equals(newAuthor.getName()) &&
             this.getId() == newAuthor.getId();
  }
}

  public static List<Author> all() {
    String sql = "SELECT * FROM authors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Author find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors where id=:id";
      Author book = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Author.class);
      return book;
    }
  }

  public void update(String name) {
    this.name = name;
    try(Connection con = DB.sql2o.open()) {

      String sql = "UPDATE authors SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM authors WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

    public void addBook(Book book) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO books_authors (author_id, book_id) VALUES (:author_id, :book_id)";
        con.createQuery(sql)
        .addParameter("book_id", book.getId())
        .addParameter("author_id", this.getId())
        .executeUpdate();
      }
    }

    public List<Book> getBooks() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT books.* FROM authors JOIN books_authors ON (books_authors.author_id = authors.id) JOIN books ON (books_authors.book_id = books.id) WHERE author_id = :author_id";
        List<Book> books = con.createQuery(sql)
        .addParameter("author_id", this.getId())
        .executeAndFetch(Book.class);
        return books;

      }

    }

}
