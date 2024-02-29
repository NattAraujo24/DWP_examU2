<template>
    <container id="app">
        <!-- Registrar -->
        <b-modal id="form" title="Modal" hide-footer size="lg">
            <form @submit.prevent="handleSubmit">
                <b-row>
                    <b-col cols="12" class="my-2">
                        <b-form-group label="Nombre" label-for="name">
                            <b-form-input id="name" v-model="book.name" required></b-form-input>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-group label="Autor" label-for="author">
                            <b-form-input id="author" v-model="book.author" required></b-form-input>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-group label="Fecha de publicación" label-fro="publishDate">
                            <b-form-datepicker id="publishDate" v-model="book.publishDate" required></b-form-datepicker>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-file v-model="file" placeholder="Eliga una imagen..."
                            drop-placeholder="Arrastre la imagen de favor..." accept="image/jpeg, image/png"></b-form-file>
                    </b-col>
                    <b-col cols="12">
                        <b-row class="d-flex flex-row-reverse">
                            <b-button type="submit" variant="success" class="mx-3">Registrar</b-button>
                            <b-button variant="danger" @click="resetModal">Cancelar</b-button>
                        </b-row>
                    </b-col>
                </b-row>
            </form>
        </b-modal>

        <!-- Actualizar -->
        <b-modal id="update-form" title="Modal" hide-footer size="lg">
            <form @submit.prevent="handleUpdate">
                <b-row>
                    <b-col cols="12" class="my-2">
                        <b-form-group label="Nombre" label-for="name">
                            <b-form-input id="name" v-model="updateBook.name" required></b-form-input>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-group label="Autor" label-for="author">
                            <b-form-input id="author" v-model="updateBook.author" required></b-form-input>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-group label="Fecha de publicación" label-fro="publishDate">
                            <b-form-datepicker id="publishDate" v-model="updateBook.publishDate"
                                required></b-form-datepicker>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-file v-model="fileUpdate" placeholder="Eliga una imagen..."
                            drop-placeholder="Arrastre la imagen de favor..." accept="image/jpeg, image/png"></b-form-file>
                    </b-col>
                    <b-col cols="12">
                        <b-row class="d-flex flex-row-reverse">
                            <b-button type="submit" variant="success" class="mx-3">Actualizar</b-button>
                            <b-button variant="danger" @click="resetModal">Cancelar</b-button>
                        </b-row>
                    </b-col>
                </b-row>
            </form>
        </b-modal>
    </container>
</template>

<script>
import LibroService from 'exam2/src/service/LibroService'
export default {
    methods: {
        resetModal() {
            for (const field in this.book) {
                this.book[field] = null
            }
            for (const field in this.updateBook) {
                this.updateBook[field] = null
            }
            this.$bvModal.hide("form");
            this.$bvModal.hide("update-form");
        },
        async handleSubmit() {
            try {
                if (this.file != null)
                    this.book.cover = await Functions.blobToBase64(this.file, "image/jpeg");
                const res = await LibroService.saveBook(this.book);
                if (!res.error) this.getBooks()
            } catch (error) {
                console.error(error);
            } finally {
                this.$bvModal.hide("form");
                this.resetModal()
            }
        },
        async handleUpdate() {
            try {
                const cover = await Functions.blobToBase64(this.fileUpdate, "image/jpeg");
                const res = await LibroService.updateBook({ ...this.updateBook, cover: cover });
                if (!res.error) this.getBooks();
            } catch (error) {
                console.error(error);
            } finally {
                this.$bvModal.hide("update-form");
                this.resetModal()
            }
        },
        async getBooks() {
            try {
                const { data: res } = await LibroService.getBooks(this.params);
                this.books = res.content;
            } catch (error) {
                console.error(error);
            }
        },
        async getBooksCover() {
            try {
                const { data: res } = await LibroService.getBooksCover(this.params);
                this.booksCover = res.content;
            } catch (error) {
                console.error(error);
            }
        },
        async filterBooks(sort) {
            try {
                if (sort == "author") {
                    this.params = { ...this.params, sort: "author" }
                    const { data: res } = await LibroService.getBooks(this.params);
                    this.books = res.content;
                }
                if (sort == "publishDate") {
                    this.params = { ...this.params, sort: "publishDate" }
                    const { data: res } = await LibroService.getBooks(this.params);
                    this.books = res.content;
                }
                if (sort == "cover") {
                    const { data: res } = await LibroService.getBooksCover(this.params);
                    this.books = res.content;
                }
            } catch (error) {
                console.error(error);
            }
        },
    }
}
</script>

<style>
</style>