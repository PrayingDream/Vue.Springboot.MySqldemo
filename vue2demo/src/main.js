import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'; // 导入 elementUI
import 'element-ui/lib/theme-chalk/index.css'; // 导入 css
import $ from 'jquery'


Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
  router,
  store,
  $,
  render: h => h(App)
}).$mount('#app')
