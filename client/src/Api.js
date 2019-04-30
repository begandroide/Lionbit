import axios from 'axios'  
  
const SERVER_URL = 'http://localhost:8088';  
  
const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 1000  
});  
  
export default {  
  // (C)reate  
  createNewTeacher: (Teacher) => instance.post('teachers/',Teacher ),
  createNew: (text, completed) => instance.post('student', {title: text, completed: completed}),  
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
  updateForId: (id, text, completed) => instance.put('students/'+id, {title: text, completed: completed}),  
  // (D)elete  
  removeForId: (id) => instance.delete('students/'+id)  
}