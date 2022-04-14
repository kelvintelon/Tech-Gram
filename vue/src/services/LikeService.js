import axios from 'axios';

export default {

    getLikeCountByPhotoID(photoID) {
     return axios.get(`/likeCount/${photoID}`)
 },

  addLike(photo) {
      return axios.post(`/likes}`, photo)
  },

  unlikeByPhotoID(photoID) {
      return axios.delete(`/unlike/${photoID}`)
  }


}