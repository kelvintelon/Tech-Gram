import axios from 'axios';

  export default {

    addPhoto(photo) {
      return axios.post('/photos', photo);
    },

}



