import axios from 'axios';

  export default {

    addPhoto(photo) {
      return axios.post('/photos', photo);
    },
    getPicture(photoId){
      return axios.get(`/photoDetails/${photoId}`);
    },
    getOnePicture(photoId){
      return axios.get(`/photos/${photoId}`);
    },

}



