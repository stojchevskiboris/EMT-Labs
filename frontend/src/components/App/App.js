import React, { useState, useEffect } from 'react';
import { Routes, Route, BrowserRouter, Navigate } from 'react-router-dom';
import BookShopService from '../../repository/bookShopRepository';
import BookAdd from '../Books/BookAdd';
import BookEdit from '../Books/BookEdit';
import BookList from '../Books/BookList';
import CategoriesList from '../Categories/CategoriesList';
import Header from '../Header/Header';

function App() {

  const [books, setBooks] = useState([]);
  const [categories, setCategories] = useState([]);
  const [authors, setAuthors] = useState([]);

  const fetchBooks = () => {
    BookShopService.fetchBooks()
        .then((response) => {
          setBooks(response.data);
        });
  }

  const fetchCategories = () => {
    BookShopService.fetchCategories()
        .then((response) => {
          setCategories(response.data);
        });
  }

  const fetchAuthors = () => {
    BookShopService.fetchAuthors()
        .then((response) => {
          setAuthors(response.data);
        });
  }

  const addBook = (name, category, authorId, availableCopies) => {
    BookShopService.addBook(name, category, authorId, availableCopies)
        .then((response) => {
          fetchBooks();
        });
  }

  const editBook = (id, name, category, author, availableCopies) => {
    BookShopService.editBook(id, name, category, author, availableCopies)
        .then((response) => {
          fetchBooks();
        })
  }

  const deleteBook = (id) => {
    BookShopService.deleteBook(id)
        .then((response) => {
          fetchBooks();
        });
  }

  const markBookAsTaken = (id) => {
    BookShopService.markBookAsTaken(id)
        .then((response) => {
          fetchBooks();
        });
  }

  useEffect(() => {
    fetchBooks();
    fetchCategories();
    fetchAuthors();
  }, []);

  return (
      <div>
        <BrowserRouter>
          <Header />
          <Routes>
            {['/', '/books'].map(path => <Route key={path} path={path} element={<BookList books={books} deleteBook={deleteBook} markBookAsTaken={markBookAsTaken} />} />)}
            <Route path="/books/add" element={ <BookAdd categories={categories} authors={authors} addBook={addBook} /> } />
            <Route path="/books/edit/:id" element={ <BookEdit categories={categories} authors={authors} editBook={editBook} /> } />
            <Route path="/categories" element={ <CategoriesList categories={categories} /> } />
            <Route
                path="*"
                element={<Navigate to="/" replace />}
            />
          </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;
