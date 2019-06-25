import axios from 'axios'  
import Vue from 'vue'  
  
const SERVER_URL_STUDENT = 'http://localhost:8088';  
const SERVER_URL = 'http://localhost:8089';  
  
const instance_student = axios.create({  
  baseURL: SERVER_URL_STUDENT,  
  timeout: 1000  
});  
const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 1000  
});  
  
export default {  

  async execute(method, resource, data, config) {  
    let accessToken = await Vue.prototype.$auth.getAccessToken()  
    return instance({  
      method:method,  
      url: resource,  
      data,  
      headers: {  
            Authorization: `Bearer ${accessToken}`  
      },  
      ...config  
    })  
  },  

  //Students
  createNewStudent: (Student) => instance_student.post('students/', Student),  
  getAllStudents: () => instance_student.get('students/',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.students : data;  
    }]  
  }),    
  updateStudentForId: (id, name, last_name, rol) => instance_student.put('students/'+id, {name: name, last_name: last_name, rol_usm: rol }),   
  removeStudentForId: (id) => instance_student.delete('students/'+id),
  //-/Students
  
  //Teachers  
  createNewTeacher: (Teacher) => instance.post('teachers/',Teacher ),  
  getAllTeachers: () => instance.get('teachers/',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.teachers : data;  
    }]  
  }),
  updateTeacherForId: (id, name, last_name, rut) => instance.put('teachers/'+id, {name: name, last_name: last_name, rut: rut}),
  removeTeacherForId: (id) => instance.delete('teachers/'+id),
  //-/Teachers

  //Assignatures
  createNewAssignature: (Assignature) => instance.post('assignatures/', Assignature),  
  getAllAssignatures: () => instance.get('assignatures/', { useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.assignatures : data;  
    }]  
  }),    
  updateAssignatureForId: (id,Assignature) => instance.put('assignatures/'+id, 
        Assignature),   
  removeAssignatureForId: (id) => instance.delete('assignatures/'+id),
  //-/Students
  
}