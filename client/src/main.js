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

import 'vuetify/dist/vuetify.min.css';
import NProgress from 'vue-nprogress';

const optionsNpro = {
  latencyThreshold: 800, // Number of ms before progressbar starts showing, default: 100,
  router: true, // Show progressbar when navigating routes, default: true
  http: false, // Show progressbar when doing Vue.http, default: true
};

Vue.use(NProgress,optionsNpro)

const nprogress = new NProgress({parent: ".v-content__wrap"});

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
// if (TokenService.getToken()) {
//   Api.setHeader()
// }

Vue.use(VueLogger, options);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  nprogress,
  template: '<App/>',
  components: { App }
});