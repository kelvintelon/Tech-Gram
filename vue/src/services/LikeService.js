import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
});

export default {

    getLikeCountByPhotoID(photoID) {
     return http.get(`/likeCount/${photoID}`)
 },

  addLike(photo) {
      return http.post(`/likes}`, photo)
  },

  unlikeByPhotoID(photoID) {
      return http.delete(`/unlike/${photoID}`)
  }


}