import axios from 'axios'  
import Vue from 'vue'  
  
const SERVER_URL = 'http://localhost:8088';  
  
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

  // (C)reate  
  createNewTeacher: (Teacher) => instance.post('teachers/',Teacher ),  
  createNewStudent: (Student) => instance.post('students/', Student),  
  // (R)ead  
  getAllStudents: () => instance.get('students/',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.students : data;  
    }]  
  }),    
  getAllTeachers: () => instance.get('teachers/',{ useCredentails: true }, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.teachers : data;  
    }]  
  }),  
  // (U)pdate  
  updateForId: (id, name, rol) => instance.put('students/'+id, {name: name, rol_usm: rol }),   
  // (D)elete  
  removeForId: (id) => instance.delete('students/'+id),
  removeForIdTeacher: (id) => instance.delete('teachers/'+id)
}