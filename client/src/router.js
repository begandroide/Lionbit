import Vue from 'vue'
import Student from './components/Student/Students.vue'
import StudentProfile from './components/Student/StudentProfile.vue'
import Teacher from './components/Teacher/Teachers.vue'
import Assignatures from './components/Assignatures/Assignatures.vue'
import TeacherAssignature from './components/TeacherAssignature/TeacherAssignature.vue'
import Users from './components/Administration/Users.vue'
import LayoutBlank from './views/LayoutBlank.vue'
import Layout from './views/Layout.vue'
import Router from 'vue-router'
import Meta from 'vue-meta'
import NProgress from 'nprogress';


import { TokenService } from './services/storage.service'

Vue.use(Router);
Vue.use(Meta);

const ifAuthenticated = (to, from, next) => {
  if (TokenService.getToken !== null) {
    next()
    return
  }
  next('/login')
}


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
          path: '/Admin',
          component: Users,
          name: 'users',
          meta: {
            auth: false,
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