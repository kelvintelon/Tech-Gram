import axios from 'axios';

export default {

    getCommentsByPhotoID(photoID) {
     return axios.get(`/comments/${photoID}`)
 },

  addComment(comment) {
      return axios.post(`/comments`, comment )
  },

  deleteComment(commentId) {
      return axios.delete(`/comments/comment/${commentId}`)
  },

  updateComment(comment) {
    return axios.put(`/comments`, comment )
},



}