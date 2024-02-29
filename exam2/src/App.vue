<template>
  <b-container id="app" style="height: 2000px;">
  <div>
    <router-view></router-view>
  </div>
  <b-row style="display: flex; align-items: center; justify-content: center;" class="my-2">
    <b-col cols="4">
      <b-button @click="filterBooks('author')">Ordenar por autor</b-button>
    </b-col>
    <b-col cols="4">
      <b-button @click="filterBooks('publishDate')">Ordenar por fecha</b-button>
    </b-col>
    <b-col cols="4">
      <b-button @click="filterBooks('cover')">Mostrar si tiene imagen</b-button>
    </b-col>
  </b-row>

  <b-row>
    <b-col cols="10">
      <b-row>
        <TransitionGroup name="zoom" tag="div" class="d-flex flex-row flex-wrap">
          <b-col v-for="book in books" :key="book.id">
            <b-card :title="book.name" :img-src="book.cover" img-alt="Image" img-top tag="article"
              style="max-width: 20rem; min-width: 20rem;" class="mb-2" draggable @dragstart="startDrag($event, book)">
              <b-card-text>Autor: {{ book.author }}</b-card-text>
              <b-card-text>Fecha de publicación: {{ book.publishDate }}</b-card-text>
            </b-card>
          </b-col>
        </TransitionGroup>
      </b-row>
    </b-col>
    <b-col cols="2" class="p-5">
      <b-row>
        <b-col cols="12" class="my-2">
          <b-button v-b-modal.form>+</b-button>
        </b-col>
        <b-col cols="12" class="mb-2">
          <div class="drop-zone" @drop="update($event)" @dragover.prevent @dragenter.prevent>
            <b-icon icon="pencil"></b-icon>
          </div>
        </b-col>
        <b-col cols="12">
          <div class="drop-zone" @drop="deleteBook($event)" @dragover.prevent @dragenter.prevent>
            <b-icon icon="trash"></b-icon>
          </div>
        </b-col>
      </b-row>
    </b-col>
  </b-row>

  </b-container>
</template>

<script>

</script>
<style>
.zoom-enter-active,
.zoom-leave-active {
  transition: transform 0.5s;
}

.zoom-enter,
.zoom-leave-to {
  transform: scale(0);
}

.drop-zone {
  background-color: #eee;
  margin-bottom: 10px;
  padding: 10px;
  height: 100px;
  width: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 3rem;
}

.drag-el {
  background-color: #fff;
  margin-bottom: 10px;
  padding: 5px;
}
</style>