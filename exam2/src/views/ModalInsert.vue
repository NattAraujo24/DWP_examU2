<template>
    <b-modal id="form" title="Modal" hide-footer size="lg" b-modal @hidden="$emit('close')">
        <form>
            <b-row>
                <b-col cols="12" class="my-2">
                    <b-form-group label="Nombre" label-for="name">
                        <b-form-input id="name" required></b-form-input>
                    </b-form-group>
                </b-col>
                <b-col cols="12" class="mb-2">
                    <b-form-group label="Autor" label-for="author">
                        <b-form-input id="author" required></b-form-input>
                    </b-form-group>
                </b-col>
                <b-col cols="12" class="mb-2">
                    <b-form-group label="Fecha de publicación" label-for="publicationDate">
                        <b-form-datepicker id="publicationDate" required></b-form-datepicker>
                    </b-form-group>
                </b-col>
                <b-col cols="12" class="mb-2">
                    <b-form-file placeholder="Elija una imagen..." drop-placeholder="Arrastre la imagen de favor..."
                        accept="image/jpeg, image/png"></b-form-file>
                </b-col>
                <b-col cols="12">
                    <b-row class="d-flex flex-row-reverse">
                        <b-button type="submit" variant="primary" class="mx-3">Registrar</b-button>
                        <b-button variant="danger">Cancelar</b-button>
                    </b-row>
                </b-col>
            </b-row>
        </form>
    </b-modal>
</template>

<script>
import books from '../service/BookService';

export default {
    methods: {
        async insertBook(event) {
            try {
                await books.onInsert(
                    this.author,
                    this.nameBook,
                    this.gener,
                    this.age
                );
                this.consultBooks();
            } catch (error) {
                alert(error.message);
            } finally {
                this.$bvModal.hide("form");
                this.resetForm();
            }
        },
        openModal() {
            this.$bvModal.show("form");
        },
    }
}

</script>