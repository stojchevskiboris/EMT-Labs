import React from "react";
import {Link} from "react-router-dom";

const BookItem = ({ book, deleteBook, markBookAsTaken}) => {

    const handleDelete = () => {
        deleteBook(book.id);
    }

    const handleMarkAsTaken= () => {
        markBookAsTaken(book.id);
    }

    return(
        <tr>
            <td>{book.name}</td>
            <td>{book.category}</td>
            <td>{`${book.author.name} ${book.author.surname}`}</td>
            <td>{book.author.country.name}</td>
            <td>{book.availableCopies}</td>
            <td>
                <Link className={"btn btn-sm m-1 btn-info"} to={`/books/edit/${book.id}`} >Edit</Link>
                <button className={"btn btn-sm m-1 btn-danger"} onClick={() => handleDelete()}>Delete</button>
                <button className={"btn btn-sm m-1 btn-success"} onClick={() => handleMarkAsTaken()}>Mark As Taken</button>
            </td>
        </tr>
    );
}

export default BookItem;