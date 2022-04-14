import axios from 'axios';

export default {

      getFiles(){
          return axios.get('/photos');
      },

      getUserFiles(name){
        return http.get(`/photos/user/${name}`);

      }


}