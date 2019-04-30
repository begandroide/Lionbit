import Auth from "@okta/okta-vue";  
import Vue from 'vue'  
import Router from 'vue-router'  
import Students from './components/Students'  
import Teachers from './components/Teachers'  
  
Vue.use(Auth, {  
  issuer: 'https://dev-971145.okta.com/oauth2/default',  
  client_id: '0oaiuwvpb7zcpnMgU356',  
  redirect_uri: 'http://localhost:8080/implicit/callback',  
  scope: 'openid profile email'  
});  
  
Vue.use(Router);  
  
let router = new Router({  
  mode: 'history',  
  routes: [  
    {  
      path: '/',  
      name: 'Sga',
      meta: {  
        requiresAuth: true  
      }  
    },  
	{  
	  path: '/implicit/callback',  
	  component: Auth.handleCallback(),  
	},  
  ]  
});  
  
router.beforeEach(Vue.prototype.$auth.authRedirectGuard());  
  
export default router;