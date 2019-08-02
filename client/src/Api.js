import Vue from "vue"
import axios from 'axios'
import { TokenService } from './services/storage.service'
import router from "./router"

const SERVER_URL = 'http://localhost:8089';  

const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 10000,
});


instance.interceptors.request.use(function(config) {
  const token = TokenService.getToken();

  if ( token != null ) {
    instance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
  }

  return config;
}, function(err) {
  return Promise.reject(err);
});

instance.interceptors.response.use(null, function (error) {
  if (error.response.status === 500) {
    console.log('JWT expired');
    TokenService.removeToken();
    
    router.push('/Login');
  }
  return Promise.reject(error)
})

export default {  
  init() {
    instance.defaults.baseURL = SERVER_URL;
    this.setHeader();
  },
  setHeader() {
      instance.defaults.headers.common["Authorization"] = `Bearer ${TokenService.getToken()}`
  },

  removeHeader() {
      instance.defaults.headers.common = {}
  },
  async execute(method, resource, data, config) {  
    let accessToken = TokenService.getToken();
    return instance({  
      method:method,  
      url: resource,  
      data,
      headers: {  
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`  
      },
      ...config  
    })  
  },  
  //login
  login: (email, password) => instance.post('api/auth/login', {email: email, password: password}),
  //admin
  getAllUsers: () => instance.get('api/users', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      console.log(data);
      return data? JSON.parse(data)._embedded.users : data;  
    }]  
  }),
  createNewUser: (User) => instance.post('api/users/register', User ),
  removeUserForId: (id,User) => instance.delete('api/users/'+id),

  //Students
  createNewStudent: (Student) => instance.post('api/students', Student),  
  getAllStudents: () => instance.get('api/students/',{useCredentails: true}, {  
    transformResponse: [function (data) {  
      console.log(data);
      return data? JSON.parse(data)._embedded.students: data;  
    }]  
  }),    
  updateStudentForId: (Student) => instance.put('api/students/'+Student.studentID, {firstName: Student.firstName, lastName: Student.lastName, rol_usm: Student.rol_usm }),   
  removeStudentForId: (id) => instance.delete('api/students/'+id),
  //-/Students
  
  //Teachers  
  createNewTeacher: (Teacher) => instance.post('api/teachers/', Teacher ),
    
  getAllTeachers: () => instance.get('api/teachers/',{useCredentails: true}, {  
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
  createNewTeacherAssignature: (TeacherAssignature) => instance.post('api/teacherassignatures/', TeacherAssignature),  
  getAllTeacherAssignatures: () => instance.get('api/teacherassignatures', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.teacherassignatures : data;  
    }]  
  }),    
  updateTeacherAssignatureForId: (id,TeacherAssignature) => instance.put('api/teacherassignatures/'+id, 
        Assignature),   
  removeTeacherAssignatureForId: (id) => instance.delete('api/teacherassignatures/'+id),
  
  //// semester

  createNewSemester: (Semester) => instance.post('api/semester', Semester),  
  getAllSemesters: () => instance.get('api/semester/', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.semesters : data;  
    }]  
  }),    
  updateSemesterForId: (id,Semester) => instance.put('api/semester/'+id, 
        Semester),   
  removeSemesterForId: (id) => instance.delete('api/semester/'+id),
  
}