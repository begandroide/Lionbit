import Vue from 'vue'
import Student from './components/Student/Students.vue'
import StudentProfile from './components/Student/StudentProfile.vue'
import Teacher from './components/Teacher/Teachers.vue'
import Assignatures from './components/Assignatures/Assignatures.vue'
import Users from './components/Administration/Users.vue'
import LayoutBlank from './views/LayoutBlank.vue'
import Layout from './views/Layout.vue'
import Router from 'vue-router'
import Meta from 'vue-meta'

import { TokenService } from './services/storage.service'

// Vue.use(Auth, {
//   issuer: 'https://dev-780385.okta.com/oauth2/default',
//   client_id: '0oaiwzvn2i81Jsj3N356',
//   redirect_uri: 'http://localhost:8080/implicit/callback',
//   scope: 'openid profile email'
// });

Vue.use(Router);
Vue.use(Meta)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      component: LayoutBlank,
      children: [
        {
          path:'',
          name: 'Login',
          component: () => import('./views/Login.vue'),
          meta: {
            auth: false,
          },
        }
      ],
    },{
      path: '/',
      component: Layout,
      children: [
        {
          name: 'Sga',
          path:'',
          component: () => import('./views/Home.vue'),
          meta: {
            auth: true,
          },
        },
        {
          path: '/Students',
          component: Student,
          name: 'students',
          meta: {
            auth: true,
          },
        },
        {
          path: '/Teachers',
          component: Teacher,
          name: 'teachers',
          meta: {
            auth: true
          },
        },
        {
          path: '/Assignatures',
          component: Assignatures,
          name: 'assignatures',
          meta: {
            auth: true
          },
        },
        {
          path: '/Admin',
          component: Users,
          name: 'users',
          meta: {
            auth: true
          },
        },
        {
          path: '/StudentProfile/:id',
          props: true,
          component: StudentProfile,
          name: 'studentProfile',
          meta: {
            auth: true
          },
        },
      ],
    },
  ]
});

router.beforeEach((to, from, next) => {
  var tok = TokenService.getToken();
  if(to.path != '/login' || tok !== null) {
      if(tok !== null) { 
          console.log('There is a token, resume. (' + to.path + ')');
          next();
      } else {
          console.log('There is no token, redirect to login. (' + to.path + ')');
          next('login');
      }
  } else {
    console.log('You\'re on the login page');
    next(); // This is where it should have been
  }
});

export default router;