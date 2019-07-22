import axios from 'axios'

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
  //Students
  createNewStudent: (Student) => instance.post('api/students', Student,{headers:headers}),  
  getAllStudents: () => instance.get('api/students/all',{ useCredentails: true ,headers: headers}, {  
    transformResponse: [function (data) {  
      console.log(data);
      return data? JSON.parse(data)._embedded.students : data;  
    }]  
  }),    
  updateStudentForId: (id, name, last_name, rol) => instance.put('api/students/'+id, {firstName: firstName, lastName: lastName, rol_usm: rol_usm },{headers:headers}),   
  removeStudentForId: (id) => instance.delete('api/students/'+id,{headers:headers}),
  //-/Students
  
  //Teachers  
  createNewTeacher: (Teacher) => instance.post('api/teachers/',Teacher ,{headers:headers}),  
  getAllTeachers: () => instance.get('api/teachers/',{ useCredentails: true, headers:headers }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.teachers : data;  
    }]  
  }),
  updateTeacherForId: (id, name, last_name, rut) => instance.put('api/teachers/'+id, {name: name, last_name: last_name, rut: rut},{headers:headers}),
  removeTeacherForId: (id) => instance.delete('api/teachers/'+id,{headers:headers}),
  //-/Teachers

  //Assignatures
  createNewAssignature: (Assignature) => instance.post('api/assignatures/', Assignature,{headers:headers}),  
  getAllAssignatures: () => instance.get('api/assignatures/', { useCredentails: true ,headers:headers}, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.assignatures : data;  
    }]  
  }),    
  updateAssignatureForId: (id,Assignature) => instance.put('api/assignatures/'+id, 
        Assignature,{headers:headers}),   
  removeAssignatureForId: (id) => instance.delete('api/assignatures/'+id,{headers:headers}),
  //-/Students
  
}