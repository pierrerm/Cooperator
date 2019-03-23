import Vue from 'vue'
import App from './App'
import router from './router'
import jquery from 'jquery';
window.$ = window.jQuery = jquery;
import 'bootstrap';
import BootstrapVue from "bootstrap-vue"
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(BootstrapVue)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
