import Vue from 'vue'
import Student from './components/Student/Students.vue'
import StudentProfile from './components/Student/StudentProfile.vue'
import Teacher from './components/Teacher/Teachers.vue'
import Assignatures from './components/Assignatures/Assignatures.vue'
import LayoutBlank from './views/LayoutBlank.vue'
import Layout from './views/Layout.vue'
import Router from 'vue-router'
import Meta from 'vue-meta'

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
            requiresAuth: false,
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
          path: '/Assignatures',
          component: Assignatures,
          name: 'assignatures',
          meta: {
            requiresAuth: true
          },
        },
        {
          path: '/Admin',
          component: Assignatures,
          name: 'assignatures',
          meta: {
            requiresAuth: true
          },
        },
        {
          path: '/StudentProfile/:id',
          props: true,
          component: StudentProfile,
          name: 'studentProfile',
          meta: {
            requiresAuth: true
          },
        },
      ],
    },
  ]
});

router.beforeEach((to, from, next) => {
  if(to.path != '/login') {
    /// totalmente parche, podriamos usar una llamada a la api para verificar el token
      if(localStorage.getItem('token') !== null) { 
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
  // next(); - This is in the wrong place
});

export default router;