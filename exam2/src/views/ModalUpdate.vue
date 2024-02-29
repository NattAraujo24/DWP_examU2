<template>
    <container id="app">
        <!-- Actualizar -->
        <b-modal id="update-form" title="Modal" hide-footer size="lg">
            <form @submit.prevent="handleUpdate">
                <b-row>
                    <b-col cols="12" class="mb-2">
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
                        <b-form-group label="Fecha de publicación" label-fro="publicationDate">
                            <b-form-datepicker id="publicationDate"
                                required></b-form-datepicker>
                        </b-form-group>
                    </b-col>
                    <b-col cols="12" class="mb-2">
                        <b-form-file v-model="fileUpdate" placeholder="Elija una imagen..."
                        id="coverImage"
                            drop-placeholder="Arrastre la imagen de favor..." accept="image/jpeg, image/png"></b-form-file>
                    </b-col>
                    <b-col cols="12">
                        <b-row class="d-flex flex-row-reverse">
                            <b-button type="submit" variant="primary" class="mx-3">Actualizar</b-button>
                            <b-button variant="danger" @click="resetModal">Cancelar</b-button>
                        </b-row>
                    </b-col>
                </b-row>
            </form>
        </b-modal>
    </container>
</template>

<script>

</script>

<style>
</style>