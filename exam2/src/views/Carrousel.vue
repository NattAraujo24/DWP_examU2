<template>
    <b-container id="app" style="height: 1500px;">
        <b-row class="my-5" v-show="showElement">
            <b-col cols="12">
                <b-carousel id="carousel-no-animation" style="text-shadow: 0px 0px 2px #000;" no-animation indicators
                    img-width="800" img-height="400">
                    <div class="carrusel">
                        <ul>
                            <li v-for="(libro, index) in libros" :key="index">
                                <img :src="libro.coverImage" alt="imagen libro">
                            </li>
                        </ul>
                    </div>
                </b-carousel>
            </b-col>
        </b-row>
    </b-container>
</template>
  
<script>
import bookService from "../service/BookService";

export default {
    data() {
        return {
            showElement: true,
            libros: [],
        };
    },
    mounted() {
        this.loadBooks();
    },
    methods: {
        async loadBooks() {
            try {
                const allBooks = await bookService.getAllBooks();
                this.libros = allBooks.filter(libro => libro.coverImage);
            } catch (error) {
                console.error("Error al obtener la lista de libros:", error);
            }
        },
    },
};
</script>
  
<style></style>
  