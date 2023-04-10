import axios from '../custom-axios/axios';

const BookShopService = {

    fetchBooks: () => {
        return axios.get('/books');
    },

    fetchBook: (id) => {
        return axios.get(`/books/${id}`)
    },

    fetchCategories: () => {
        return axios.get('/books/categories');
    },

    fetchAuthors: () => {
        return axios.get('/authors');
    },

    addBook: (name, category, author, availableCopies) => {
        return axios.post('/books/add', {
            name,
            category,
            author,
            availableCopies
        });
    },

    editBook: (id, name, category, author, availableCopies) => {
        return axios.post(`/books/edit/${id}`, {
            name,
            category,
            author,
            availableCopies
        });
    },

    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },

    markBookAsTaken: (id) => {
        return axios.post(`/books/mark/${id}`);
    }
}

export default BookShopService;