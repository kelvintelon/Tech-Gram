<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
    />
    <i
      class="material-icons"
      id="notLiked"
      @click="whenClicked(photoId)"
      v-if="notLiked"
      >favorite_border</i
    >

    <span class="material-icons" id="Liked" v-else>favorite</span>
    
    <div class="likes likesCount" id="likeText">{{ this.oneCount }}</div>
  </div>
</template>

<script>
import LikesService from "../services/LikesService";

export default {
  name: "like-button",
  props: {
    photoId: Number,
  },
  data() {
    return {
      notLiked: true,
      oneCount: 0,
      photo: {
        photo_id: 0,
      },
    };
  },
  mounted() {
    this.getAllLikes(this.photoId);
  },
  methods: {
    delay(time) {
      return new Promise((resolve) => setTimeout(resolve, time));
    },
    whenClicked(photoId) {
      this.notLiked = false;
      this.checkLike(photoId);
      this.delay(400).then(() => (this.notLiked = true));
    },
    getAllLikes(photoId) {
      LikesService.getLikes(photoId).then((response) => {
        this.$store.commit("SET_LIKES", response.data);
        this.oneCount = response.data;
      });
    },
    deleteLike(photoId) {
      LikesService.deleteLike(photoId);
    },
    addLike(photoId) {
      this.photo.photo_id = photoId;
      LikesService.addLike(this.photo);
    },
    checkLike(photoId) {
      LikesService.checkLikes(photoId).then((response) => {
        if (response.data == 0) {
        this.addLike(photoId);
        this.oneCount++;
      } else { 
        this.deleteLike(photoId);
        this.oneCount--;
      }
      });
    },
  },
};
</script>

<style>
#notLiked {
  color: #00adee;
  cursor: pointer;
}

#Liked {
  color: #00adee;
  cursor: pointer;
}
#likeText {
  font-size: 16px;
}

</style>