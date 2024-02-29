import Vue from 'vue'
import VueRouter from 'vue-router'
import Carrousel from '../views/Carrousel.vue'
import ModalVue from '../views/Modal.vue'

Vue.use(VueRouter)
 
const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/carrousel',
      name: 'carrousel',
      component: () => import('../views/Carrousel.vue'),
      component: Carrousel
    },
    {
        path: '/modal',
        name: 'modal',
        component: () => import('../views/Modal.vue'),
        component: ModalVue
      }
  ]
})

export default router