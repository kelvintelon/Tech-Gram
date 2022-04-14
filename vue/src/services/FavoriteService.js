import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
});

export default {

    getFavorites() {
     return http.get(`/favorites`)
 },

  addFavoriteByPhotoID(photoID) {
      return http.post(`/photodetails/${photoID}`)
  },

  deleteFavoriteByPhotoID(photoID) {
      return http.delete(`/photodetails/${photoID}`)
  }


}