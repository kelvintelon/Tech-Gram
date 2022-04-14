import axios from 'axios';

export default {

    getCommentsByPhotoID(photoID) {
     return axios.get(`/comments/${photoID}`)
 },

  addComment(comment) {
      return axios.post(`/comments`, comment )
  },

  deleteComment(comment) {
      return axios.delete(`/comments`, comment )
  },

  updateComment(comment) {
    return axios.put(`/comments`, comment )
}


}