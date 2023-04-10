import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

const BookAdd = ({ categories, authors, addBook }) => {

    const [book, setBook] = useState({
        name: '',
        category: categories[0],
        authorId: authors[0].id,
        availableCopies: 0
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setBook({
            ...book,
            [e.target.name]: e.target.value
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const name = book.name;
        const category = book.category;
        const authorId = book.authorId;
        const availableCopies = book.availableCopies;

        addBook(name, category, authorId, availableCopies);
        navigate("/books");
    }

    return (
        <div className="container mt-3">
            <div className="row">
                <div className="col-md-5">
                    <form onSubmit={(e) => handleSubmit(e)} >
                        <div className="form-group">
                            <label htmlFor="name">Book title</label>
                            <input type="text"
                                   className="form-control"
                                   id="name"
                                   name="name"
                                   required
                                   placeholder="Enter Book Title"
                                   value={book.name}
                                   onChange={(e) => handleChange(e)}
                            />
                        </div>
                        <div className="form-group">
                            <label>Category</label>
                            <select name="category"
                                    className="form-control"
                                    value={book.category}
                                    onChange={(e) => handleChange(e)}
                            >
                                {categories.map((c) => {
                                    return <option key={c} value={c}>{c.charAt(0) + c.substring(1).toLowerCase()}</option>
                                })}
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Author</label>
                            <select name="authorId"
                                    className="form-control"
                                    value={book.authorId?.id}
                                    onChange={(e) => handleChange(e)}
                            >
                                {authors.map((a) => {
                                    return <option key={a.id} value={a.id}>{`${a.name} ${a.surname}`}</option>
                                })}
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="availableCopies">AvailableCopies</label>
                            <input type="text"
                                   className="form-control"
                                   id="availableCopies"
                                   name="availableCopies"
                                   placeholder="Available Copies"
                                   value={book.availableCopies}
                                   onChange={(e) => handleChange(e)}
                            />
                        </div>
                        <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                        <a type="button" className="btn btn-secondary m-1" onClick={() => navigate(-1)} >Back</a>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default BookAdd;