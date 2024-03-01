import axios from "axios";

const API_URL = "http://localhost:8080/exam/book/";

const insertBook = async (name, author, publicationDate, coverImage) => {
    try {
        const response = await axios.post(API_URL, {
            name: name,
            author: author,
            publicationDate: publicationDate,
            coverImage: coverImage
        });
        console.log(response.data);
    } catch (error) {
        throw error;
    }
};

const getAllBooks = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data.data;
    } catch (error) {
        throw error;
    }
};

///Extra service ---------------------
const getBookOrderPublishDate = async () => {
    try {
        const response = await axios.get(`${API_URL}ordered-by-publication-date`);
        return response.data;
    } catch (error) {
        throw error;
    }
}

const getBookOrderAuthor = async () => {
    try {
        const response = await axios.get(`${API_URL}ordered-by-author`);
        return response.data;
    } catch (error) {
        throw error;
    }
}

const getBookOrderImage = async () => {
    try {
        const response = await axios.get(`${API_URL}with-image`);
        return response.data;
    } catch (error) {
        throw error;
    }
}

/// -----------------------------------------
const deleteBook = async (idBook) => {
    try {
        const response = await axios.delete(`${API_URL}${idBook}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};

const updateBook = async (id, name, author, publicationDate, coverImage) => {
    try {
        const response = await axios.put(API_URL, {
            id: id,
            name: name,
            author: author,
            publicationDate: publicationDate,
            coverImage: coverImage
        });
        console.log(response.data);
    } catch (error) {
        throw error;
    }
};

export default {
    insertBook,
    getAllBooks,
    getBookOrderPublishDate,
    getBookOrderAuthor,
    getBookOrderImage,
    updateBook,
    deleteBook,
};
