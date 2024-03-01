<template>
  <b-container id="app" style="height: 2000px;">

    <carrousel/>
    <!--Carrousel-->
    <b-row style="display: flex; align-items: center; justify-content: center;" class="my-2">
      <b-col cols="4">
        <b-button variant="warning" @click="consultAuthor">Ordenar por autor</b-button>
      </b-col>
      <b-col cols="4">
        <b-button variant="info" @click="consultPublishDate">Ordenar por fecha</b-button>
      </b-col>
      <b-col cols="4">
        <b-button variant="success" @click="consultImage">Mostrar si tiene imagen</b-button>
      </b-col>
    </b-row>

    <b-row>
      <b-col cols="10">
        <b-row>
          <TransitionGroup name="zoom" tag="div" class="d-flex flex-row flex-wrap">
            <b-col v-for="book in books" :key="book.id">
              <b-card :title="book.name" :img-src="book.coverImage" img-alt="Image" img-top tag="article"
                style="max-width: 20rem; min-width: 20rem;" class="mb-2" draggable @dragstart="startDrag($event, book)">
                <b-card-text>Autor: {{ book.author }}</b-card-text>
                <b-card-text>Fecha de publicación: {{ book.publicationDate }}</b-card-text>
                <b-card-text>
                  Estatus: {{ book.status ? 'Activo' : 'Inactivo' }}
                </b-card-text>
                <b-button variant="primary" @click="openEditModal(book)">Editar</b-button>
              </b-card>
            </b-col>
          </TransitionGroup>
        </b-row>
      </b-col>

      <b-col cols="2" class="p-5">
        <b-row>
          <b-col cols="12" class="mb-3">
            <b-button @click="openModal">+</b-button>
          </b-col>
          <b-button cols="6" class="mb-4">
            <b-icon icon="pencil"></b-icon>
          </b-button>
          <b-button cols="6">
            <b-icon icon="trash"></b-icon>
          </b-button>
        </b-row>
      </b-col>


      <!--Modal-->
      <b-modal v-model="showModal" title="Añadir nuevo libro" hide-footer>
        <form @submit.prevent="handleSubmit">
          <!-- Campos del formulario para el nuevo libro -->
          <b-form-group label="Nombre" label-for="newBookName">
            <b-form-input id="newBookName" v-model="newBook.name" required></b-form-input>
          </b-form-group>

          <b-form-group label="Autor" label-for="newBookAuthor">
            <b-form-input id="newBookAuthor" v-model="newBook.author" required></b-form-input>
          </b-form-group>

          <b-form-group label="Fecha de publicación" label-for="newBookPublicationDate">
            <b-form-datepicker id="newBookPublicationDate" v-model="newBook.publicationDate" required></b-form-datepicker>
          </b-form-group>

          <b-form-group label="Imagen de portada" label-for="newBookCoverImage">
            <b-form-file id="newBookCoverImage" v-model="newBook.coverImage" placeholder="Elija una imagen..."
              drop-placeholder="Arrastre la imagen aquí..." accept="image/jpeg, image/png"></b-form-file>
          </b-form-group>

          <b-row class="mt-4">
            <b-col class="text-right">
              <b-button type="submit" variant="primary" class="btn-sm">Registrar</b-button>
              <b-button variant="danger" @click="closeModal" class="btn-sm">Cancelar</b-button>
            </b-col>
          </b-row>
        </form>
      </b-modal>

      <b-modal v-model="showEditModal" title="Editar libro" hide-footer>
        <form @submit.prevent="handleEditSubmit">
            <!-- Campos del formulario para editar el libro -->
            <b-form-group label="Nombre" label-for="updateBookName">
                <b-form-input id="updateBookName" v-model="updateBook.name" required></b-form-input>
            </b-form-group>
    
            <b-form-group label="Autor" label-for="updateBookAuthor">
                <b-form-input id="updateBookAuthor" v-model="updateBook.author" required></b-form-input>
            </b-form-group>
    
            <b-form-group label="Fecha de publicación" label-for="updateBookPublicationDate">
                <b-form-datepicker id="updateBookPublicationDate" v-model="updateBook.publicationDate" required></b-form-datepicker>
            </b-form-group>
    
            <b-form-group label="Imagen de portada" label-for="updateBookCoverImage">
                <b-form-file id="updateBookCoverImage" v-model="updateBook.coverImage" placeholder="Elija una imagen..."
                    drop-placeholder="Arrastre la imagen aquí..." accept="image/jpeg, image/png"></b-form-file>
            </b-form-group>
    
            <b-row class="mt-4">
                <b-col class="text-right">
                    <b-button type="submit" variant="primary" class="btn-sm">Actualizar</b-button>
                    <b-button variant="danger" @click="closeEditModal" class="btn-sm">Cancelar</b-button>
                </b-col>
            </b-row>
        </form>
    </b-modal>

    </b-row>
  </b-container>
</template>

<script>
import Carrousel from './views/Carrousel.vue'
import service from './service/BookService'
import Functions from './service/Functions'


export default {
  components: {
    'carrousel': Carrousel,
  },
  data() {
    return {

      name: "",
      author: "",
      publicationDate: "",
      coverImage: "",
      books: [],
      status: false,
      cargando: false,
      showModal: false,
      showEditModal: false,
      newBook: {
        name: "",
        author: "",
        publicationDate: "",
        coverImage: null, 
      },
      updateBook: {
        id: null,
        name: "",
        author: "",
        publicationDate: "",
        coverImage: "",
      },

    };
  },
  mounted() {
    this.consultBooks();
    //this.consultPublishDate();
    //this.consultAuthor();
    //this.consultImage();
  },
  methods: {
    openModal() {
      this.newBook = {
        name: "",
        author: "",
        publicationDate: "",
        coverImage: null,
      };
      // Abre el modal
      this.showModal = true;
    },

    closeModal() {
      // Cierra el modal
      this.showModal = false;
    },

    openEditModal(book) {
      this.updateBook = { ...book };
      this.showEditModal = true;
    },

    closeEditModal() {
      // Cierra el modal de edición
      this.showEditModal = false;
    },


    async consultBooks() {
      try {
        this.cargando = true;
        const result = await service.getAllBooks();
        this.books = result;
      } catch (error) {
        alert("Error al consultar los libros");
      } finally {
        this.cargando = false;
      }
    },

    async consultPublishDate() {
      try {
        this.cargando = true;
        const result = await service.getBookOrderPublishDate();
        this.books = result;
      } catch (error) {
        alert("Error al consultar los libros");
      } finally {
        this.cargando = false;
      }
    },

    async consultAuthor() {
      try {
        this.cargando = true;
        const result = await service.getBookOrderAuthor();
        this.books = result;
      } catch (error) {
        alert("Error al consultar los libros");
      } finally {
        this.cargando = false;
      }
    },

    async consultImage() {
      try {
        this.cargando = true;
        const result = await service.getBookOrderImage();
        this.books = result;
      } catch (error) {
        alert("Error al consultar los libros");
      } finally {
        this.cargando = false;
      }
    },

    async handleSubmit() {
      try {
        if (this.newBook.coverImage) {
          this.newBook.coverImage = await Functions.blobToBase64(this.newBook.coverImage, "image/jpeg");
        }
        await insertBook(
          this.newBook.name,
          this.newBook.author,
          this.newBook.publicationDate,
          this.newBook.coverImage
        );
        this.showModal = false;
        await this.consultBooks();
      } catch (error) {
        console.error("Error al agregar el libro:", error);
      }
    },

    async handleEditSubmit() {
      try {
        if (this.updateBook.coverImage) {
          this.updateBook.coverImage = await Functions.blobToBase64(this.updateBook.coverImage, "image/jpeg");
        }
        await updateBook(
          this.updateBook.id,
          this.updateBook.name,
          this.updateBook.author,
          this.updateBook.publicationDate,
          this.updateBook.coverImage
        );
        this.showEditModal = false;
        await this.consultBooks();
      } catch (error) {
        console.error("Error al editar el libro:", error);
      }
    },

  },
}

</script>
<style></style>