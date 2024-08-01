import Vue from 'vue'
import VueRouter from 'vue-router'
import AddView from '@/views/addView/AddView.vue'
import AllView from '@/views/allView/AllView.vue'
import SelectView from '@/views/selectView/SelectView.vue'
import NotFound from '@/views/404/NotFound.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/all'
  },
  {
    path: '/add',
    name: 'add_index',
    component: AddView,
  },
  {
    path: '/all',
    name: 'all_index',
    component: AllView,
  },
  {
    path: '/select',
    name: 'select_index',
    component: SelectView,
  },
  {
    path: '/404',
    name: 'not_found_index',
    component: NotFound,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: '/',
  routes
})

export default router
