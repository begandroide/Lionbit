import axios from 'axios'
import { TokenService } from './services/storage.service'


import Vue from "vue"

Vue.prototype.$http = axios;
const token = localStorage.getItem('token');
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}


const SERVER_URL = 'http://localhost:8089'  

const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 1000,
});

const headers={
    'X-Requested-With': 'XMLHttpRequest',
    Authorization: `Bearer ${localStorage.token}`  
}

export default {  
  init(baseURL) {
    axios.defaults.baseURL = baseURL;
  },
  setHeader() {
      axios.defaults.headers.common["Authorization"] = `Bearer ${TokenService.getToken()}`
  },

  removeHeader() {
      axios.defaults.headers.common = {}
  },
  async execute(method, resource, data, config) {  
    let accessToken = localStorage.token
    return instance({  
      method:method,  
      url: resource,  
      data,
      ...config  
    })  
  },  
  //login
  login: (email, password) => instance.post('api/auth/login', {email: email, password: password}),
  //admin
  getAllUsers: () => instance.get('api/users/all', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      console.log(data);
      return data? JSON.parse(data)._embedded.users : data;  
    }]  
  }),
  createNewUser: (User) => instance.post('api/users/register',{ User}),
  //Students
  createNewStudent: (Student) => instance.post('api/students', Student),  
  getAllStudents: () => instance.get('api/students/all',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      console.log(data);
      return data? JSON.parse(data)._embedded.students : data;  
    }]  
  }),    
  updateStudentForId: (Student) => instance.put('api/students/'+Student.studentID, {firstName: Student.firstName, lastName: Student.lastName, rol_usm: Student.rol_usm }),   
  removeStudentForId: (id) => instance.delete('api/students/'+id),
  //-/Students
  
  //Teachers  
  createNewTeacher: (Teacher) => instance.post('api/teachers/', Teacher ),
    
  getAllTeachers: () => instance.get('api/teachers/',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.teachers : data;  
    }]  
  }),
  updateTeacherForId: (Teacher) => instance.put('api/teachers/'+Teacher.id, {name: Teacher.name, lastname: Teacher.lastname, rut: Teacher.rut}),
  removeTeacherForId: (id) => instance.delete('api/teachers/'+id,),
  //-/Teachers

  //Assignatures
  createNewAssignature: (Assignature) => instance.post('api/assignatures/', Assignature),  
  getAllAssignatures: () => instance.get('api/assignatures/', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.assignatures : data;  
    }]  
  }),    
  updateAssignatureForId: (id,Assignature) => instance.put('api/assignatures/'+id, 
        Assignature),   
  removeAssignatureForId: (id) => instance.delete('api/assignatures/'+id),
  //-/Students
  
}