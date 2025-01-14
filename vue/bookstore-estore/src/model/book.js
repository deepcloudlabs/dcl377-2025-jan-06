const NO_COVER_PHOTO = "data:image/png;base64,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";
const VUE3_BY_EXAMPLE = {
    title: "Vue.js 3 By Example",
    isbn: "123123123123",
    cover: NO_COVER_PHOTO,
    year: 2021,
    pages: 320,
    price: 180,
    author: "John Au-Yeung",
    publisher: "Packt Publishing"
};

export default class Book {
    constructor(book = VUE3_BY_EXAMPLE) {
        this.title = book.title;
        this.isbn = book.isbn;
        this.cover = book.cover;
        this.year = book.year;
        this.pages = book.pages;
        this.price = book.price;
        this.author = book.author;
        this.publisher = book.publisher;
    }

    load(book) {
        for (let prop in this){
            if (Object.hasOwn(book,prop))
                this[prop] = book[prop];
        }
    }
}

