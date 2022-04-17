import axios from 'axios';

export default {

    getLikes(photoId) {
        return axios.get(`/likeCount/${photoId}`)
    },

    deleteLike(photoId) {
        return axios.delete(`/unlike/${photoId}`)
    },
    addLike(photo) {
        return axios.post(`/likes`, photo)
    },
    checkLikes(photoId) {
        return axios.get(`/CheckCount/${photoId}`)
    }
}