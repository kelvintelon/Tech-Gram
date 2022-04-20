import axios from 'axios';

export default {

    getFavorites() {
     return axios.get(`/favorites`)
 },

  addFavoriteByPhotoID(photoID) {
      return axios.post(`/photodetails/${photoID}`)
  },

  deleteFavoriteByPhotoID(photoID) {
      return axios.delete(`/photodetails/${photoID}`)
  },

  checkFavorite(photoId) {
    return axios.get(`/favorites/${photoId}`)
  },

  getPhotoCount(){
    return axios.get(`/favorites/photoCount`);
  },




}