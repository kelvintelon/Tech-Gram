import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    imagePosts:[],
    userImages:[],
    favoriteImages:[],
    userFeedImages:[],
    pictureComments:[],
    Likes: '',
    Favorites: '',
    pictureDetails:{
      photo_id:'',
      user_id:'',
      caption:'',
      data_and_time:'',
      comments:[],
      likeCount:0,
      username: ''
    },

  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_IMAGEPOSTS(state, data){
      state.imagePosts = data;
    },
    SET_USERIMAGES(state, data){
      state.userImages = data;
    },
    SET_FAVORITEIMAGES(state, data){
      state.favoriteImages = data;
    },
    SET_USERFEEDIMAGES(state, data){
      state.userFeedImages = data
    },
    SET_CURRENT_PIC(state, data){
      state.pictureDetails = data;
    },
    ADD_COMMENT(state, comment) {
      state.pictureDetails.comments.push(comment);
    },
    SET_COMMENTS(state,data){
      state.pictureComments = data;
    },
    SET_LIKES(state, data) {
      state.Likes = data;
    },
    SET_FAVORITES(state, data) {
      state.Favorites = data;
    }
    // FLIP_FAVORITED(state, commentToChange) {
    //   commentToChange.favorited = ! commentToChange.favorited;
    // }
  },
  // actions: {
  // },
  // modules: {
  // },
  // strict: true
})


