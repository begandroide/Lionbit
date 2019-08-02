import Vue from 'vue'
import Student from './components/Student/Students.vue'
import StudentProfile from './components/Student/StudentProfile.vue'
import Teacher from './components/Teacher/Teachers.vue'
import Assignatures from './components/Assignatures/Assignatures.vue'
import TeacherAssignature from './components/TeacherAssignature/TeacherAssignature.vue'
import Semester from './components/Semester/Semester.vue'
import Users from './components/User/Users.vue'
import LayoutBlank from './views/LayoutBlank.vue'
import Layout from './views/Layout.vue'
import Router from 'vue-router'
import Meta from 'vue-meta'

import NProgress from 'vue-nprogress'


import { TokenService } from './services/storage.service'

Vue.use(Router);
Vue.use(Meta);
// Vue.use(NProgress);


let router = new Router({
  mode: 'history',
  // component:{
  //   NProgress
  // },
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
            requiresAuth: true,
            showProgressBar: true
          },
        },
        {
          path: '/Students',
          component: Student,
          name: 'students',
          meta: {
            auth: true,
            showProgressBar: true
          },
        },
        {
          path: '/Teachers',
          component: Teacher,
          name: 'teachers',
          meta: {
            auth: true,
          },
        },
        {
          path: '/Assignatures',
          component: Assignatures,
          name: 'assignatures',
          meta: {
            auth: true,
          },
        },
        {
          path: '/TeacherAssignatures',
          component: TeacherAssignature,
          name: 'TeacherAssignature',
          meta: {
            auth: true,
          },
        },
        {
          path: '/Semester',
          component: Semester,
          name: 'semester',
          meta: {
            auth: true,
          },
        },
        {
          path: '/Users',
          component: Users,
          name: 'users',
          meta: {
            auth: true,
          },
        },
        {
          path: '/StudentProfile/:id',
          props: true,
          component: StudentProfile,
          name: 'studentProfile',
          meta: {
            auth: false,
          },
        },
      ],
    },
  ]
});

router.beforeEach((to, from, next) => {
  var tok = TokenService.getToken();

  if(to.path != '/login' || tok !== null) {
      NProgress.start();
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

router.afterEach(() => {
  NProgress.done;
});

export default router;