<template>
    <b-container id="app" style="height: 2000px;">
        <header>
            <h3>Registro de Libros</h3>
        </header>

        <b-row class="my-5" v-show="showElement">
            <b-col cols="12">
                <b-carousel id="carousel-no-animation" style="text-shadow: 0px 0px 2px #000;" no-animation indicators
                    img-width="800" img-height="400">
                    <div class="carrusel">
                        <ul>
                            <li v-for="(libro, index) in libroFiltrados" :key="index">
                                <img :src="libro.imagen" alt="imagen libro">
                            </li>
                        </ul>
                    </div>
                </b-carousel>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {
    data() {
        return {
            showElement: true,
            books: [],
            booksCover: [],
            lastScrollPosition: 0,
            file: null,
            fileUpdate: null,
            book: {
                id: 0,
                name: "",
                author: "",
                publishDate: "",
                cover: null,
            },
            updateBook: {
                id: 0,
                name: "",
                author: "",
                publishDate: "",
                cover: null,
            },
            params: {
                size: null,
                page: null,
                sort: "id",
                direction: "asc",
            },
        };
    },
}

</script>

<style></style>