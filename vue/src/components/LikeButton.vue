<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
    />
    <i class="material-icons" id="notLiked" @click="whenClicked(photoId)" v-if="notLiked"
      >favorite_border</i
    >
    <span class="material-icons" id="Liked" v-else>favorite</span>
    <div class="likes likesCount">{{this.oneCount}}</div>
  </div>
</template>

<script>
import LikesService from "../services/LikesService";

export default {
  name: "like-button",
  props: {
    photoId: Number
  },
  data() {
    return {
      notLiked: true,
      oneCount: '',
      newCount: '',
      photo: {
        photo_id: "",
      }
    };
  },
  mounted() {
    this.getAllLikes(this.photoId);
  },
  methods: {
    delay(time) {
      return new Promise((resolve) => setTimeout(resolve, time));
    },
    whenClicked(pictureId) {
      this.notLiked = false;
      this.checkIfExists(pictureId)
      if(this.oneCount == this.newCount) {
        this.addLike(pictureId);
        this.oneCount ++;
        
      } else {
        this.oneCount --;
        this.oneCount = this.newCount;
      }
      this.delay(400).then(() => (this.notLiked = true));
    },
    getAllLikes(photoId) {
      LikesService.getLikes(photoId).then((response) => {
        this.$store.commit("SET_LIKES", response.data);
        this.oneCount = response.data;
      });
    },
    countLikes(photoId) {
      LikesService.getLikes(photoId).then((response) => {
        this.newCount = response.data;
      });
    },
    checkIfExists(photoId) {
      LikesService.deleteLike(photoId);
      this.countLikes(photoId);
    },
    addLike(photoId) {
      this.photo.photo_id = photoId;
      this.photo.user_id = 9;
      LikesService.addLike(this.photo);
    }
  },
};
</script>

<style>
#notLiked {
  color: #00adee;
  cursor: default;
}

#Liked {
  color: #00adee;
}
</style>