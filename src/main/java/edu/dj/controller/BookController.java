package edu.dj.controller;

import edu.dj.pojo.Books;
import edu.dj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //controller层调用service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model, HttpSession session){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);
        Object logined = session.getAttribute("logined");
        model.addAttribute("name",logined);
        return "allBook";
    }

    @RequestMapping("/toAddBookPage")
    public String toAdd(){
        return "addBook";
    }
    @RequestMapping("/toUpdateBookPage")
    public String toUpdate(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("QBook",books);
        return "updateBook";
    }

    //添加
    @RequestMapping("/addBook")
    public String addBook(Books book){
        bookService.addBook(book);
        return "redirect:/book/allBook";
    }
    //修改
    @RequestMapping("/updateBook")
    public String updateBook(Books book){
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }
    //删除
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId")int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //搜索
    @RequestMapping("/searchBook")
    public String searchBook(String searchBookByName,Model model){
        Books books = bookService.searchBookByName(searchBookByName);
        List<Books> list = new ArrayList<>();
        list.add(books);
        if (books == null){
            list = bookService.queryAllBook();
            model.addAttribute("error","未查到此书");
        }
        model.addAttribute("list",list);
        return "allBook";
    }
}
