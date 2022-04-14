import axios from 'axios';

export default {

      getFiles(){
          return axios.get('/photos');
      },

      getUserFiles(name){
        return axios.get(`/photos/user/${name}`);

      }


}