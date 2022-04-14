import axios from 'axios';

export default {

      getFiles(){
          return axios.get('/photos');
      },


}