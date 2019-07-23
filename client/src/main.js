import Vue from 'vue'
import App from './App'
import vuetify from 'vuetify'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import '@fortawesome/fontawesome-free/css/all.css' // Ensure you are using css-loader

Vue.use(BootstrapVue)
Vue.use(vuetify)
Vue.use( {
  iconfont: 'fa'
 })
Vue.config.productionTip = false

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import VueLogger from 'vuejs-logger';

import 'vuetify/dist/vuetify.min.css'
import Api from './Api';
import { TokenService } from "./services/storage.service"

const options = {
  isEnabled: true,
  logLevel : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : false,
  separator: '|',
  showConsoleColors: true
};

// If token exists set header
if (TokenService.getToken()) {
  Api.setHeader()
}

Vue.use(VueLogger, options);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
});