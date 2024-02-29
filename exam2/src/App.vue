<template>
  <b-container id="app" style="height: 2000px;">
    
    <!--Carrousel-->

  <b-row style="display: flex; align-items: center; justify-content: center;" class="my-2">
    <b-col cols="4">
      <b-button >Ordenar por autor</b-button>
    </b-col>
    <b-col cols="4">
      <b-button>Ordenar por fecha</b-button>
    </b-col>
    <b-col cols="4">
      <b-button>Mostrar si tiene imagen</b-button>
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
              <b-card-text>Estatus: {{ book.status }} </b-card-text>
            </b-card>
          </b-col>
        </TransitionGroup>
      </b-row>
    </b-col>
    
    <b-col cols="2" class="p-5">
      <b-row>
        <b-col cols="12" class="mb-3">
          <b-button v-b-modal.form>+</b-button>
        </b-col>
        <b-button cols="6" class="mb-4">
            <b-icon icon="pencil"></b-icon>
        </b-button>
        <b-button cols="6">
            <b-icon icon="trash"></b-icon>
        </b-button>
      </b-row>
    </b-col>
  </b-row>

  </b-container>
</template>

<script>

import Carrousel from './views/Carrousel.vue'
import ModalInsert from './views/ModalInsert.vue'
import ModalUpdate from './views/ModalUpdate.vue'


export default{
  components:{
    'carrousel': Carrousel,
    'modalInsert': ModalInsert,
    'modalUpdate': ModalUpdate,
  }
}

</script>
<style>
</style>