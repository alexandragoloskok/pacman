// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import auth from './auth'
import axios from 'axios'

import App from './App'
import Pacman from './components/temporaryPacman.vue'
import PacmanTest from './components/pacman.vue'
import Login from './components/login.vue'
import Registration from './components/registration.vue'
import Top from './components/top.vue'
import NotFound from './components/notFound.vue'

Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(ElementUI)

const router = new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      component: PacmanTest
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/registration',
      component: Registration
    },
    {
      path: '/top',
      component: Top
    },

    {
      path: '/logout',
      beforeEnter (to, from, next) {
        auth.logout()
        axios.post('/logout')
        next('/')
      }
    },
    {
      path: "*",
      component: NotFound
    }
  ]
})

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
