import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
});

export default {

    getCommentsByPhotoID(photoID) {
     return http.get(`/comments/${photoID}`)
 },

  addComment(comment) {
      return http.post(`/comments`, comment )
  },

  deleteComment(comment) {
      return http.delete(`/comments`, comment )
  },

  updateComment(comment) {
    return http.put(`/comments`, comment )
}


}