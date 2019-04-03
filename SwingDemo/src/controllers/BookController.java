/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;
import models.Book;
import models.FileTableModel;
import views.BookForm;
import views.BookList;

/**
 *
 * @author William Sanchez
 */
public class BookController implements ActionListener{
    BookForm bookForm;
    BookList bookList;
    JFileChooser d;
    Book book;
    public BookController(BookForm f){
        super();
        bookForm = f;
        d = new JFileChooser();
        book = new Book();
    }
     public BookController(BookList bl){
        super();
        bookList = bl;
        d = new JFileChooser();
        book = new Book();
    }   
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "save":
                d.showSaveDialog(bookForm);
                book = bookForm.getBookData();
                writeBook(d.getSelectedFile());
                break;
            case "select":
                d.showOpenDialog(bookForm);
                readBook(d.getSelectedFile());
                bookForm.setBookData(book);
                break;
            case "clear":
                bookForm.clear();
                break;
            case "directory":
                d.setFileSelectionMode(DIRECTORIES_ONLY);
                d.showOpenDialog(bookList);
                File f = d.getSelectedFile();
                bookList.SetDirectory(f.getPath());
                bookList.setBookTableModel(new FileTableModel(f).getModel());
                break;
            case "new":
                showBookForm("");
                break;
            case "show":
                showBookForm(bookList.getSelectedBookFile());
                break;                
        }
    }
    public void writeBook(File file){
        try{
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file));
            ous.writeObject(book);
            ous.flush();
        }
        catch(FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void readBook(File file){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            book = (Book)ois.readObject();
        }
        catch(FileNotFoundException e){
            
        } catch (IOException ex) {
            Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void showBookForm(String selectedBook){
        bookForm = selectedBook.isEmpty()?new BookForm():new BookForm(selectedBook);
        JDesktopPane desktop = (JDesktopPane)bookList.getParent();
        desktop.add(bookForm);
        bookForm.setVisible(true);
    }
    public void setBook(String filePath){
        File f = new File(filePath);
        readBook(f);
    }
    public Book getBook(){
        return book;
    }
}
