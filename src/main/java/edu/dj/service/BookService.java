package edu.dj.service;

import edu.dj.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    //增加一本书
    int addBook(Books books);
    //删除一本书
    int deleteBookById(int id);
    //更新书的数据
    int updateBook(Books books);
    //查询一本书
    Books queryBookById(int id);
    //查询所有的书
    List<Books> queryAllBook();
    //按名字搜索书籍
    Books searchBookByName(@Param("bookName") String name);
}
