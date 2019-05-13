import Auth from "@okta/okta-vue";
import Vue from 'vue'
import Student from './components/Student/Students.vue'
import Teacher from './components/Teacher/Teachers.vue'
import HelloWorld from './components/HelloWorld.vue'
import Router from 'vue-router'
import Meta from 'vue-meta'

Vue.use(Auth, {
  issuer: 'https://dev-780385.okta.com/oauth2/default',
  client_id: '0oaiwzvn2i81Jsj3N356',
  redirect_uri: 'http://localhost:8080/implicit/callback',
  scope: 'openid profile email'
});

Vue.use(Router);
Vue.use(Meta)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Sga',
      component: HelloWorld,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/Students',
      component: Student,
      name: 'students',
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/Teachers',
      component: Teacher,
      name: 'teachers',
      meta: {
        requiresAuth: true
      },
    },
    {
      path: '/implicit/callback',
      component: Auth.handleCallback(),
    },
  ]
});

router.beforeEach(Vue.prototype.$auth.authRedirectGuard());

export default router;