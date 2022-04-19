<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
    />
    <i
      class="material-icons"
      id="notFavorite"
      @click="whenClicked(photoId)"
      v-if="notFavorite"
      >star_border</i
    >
    <span class="material-icons" id="Favorite" v-else>star</span>
    
  </div>
</template>

<script>
import FavoriteService from "../services/FavoriteService";

export default {
  name: "favorite-button",
  props: {
    photoId: Number,
  },
  data() {
    return {
      notFavorite: true,
      isFavorite: "",
      photo: {
        photo_id: "",
      },
    };
  },
  methods: {
    whenClicked(photoId) {
      this.notFavorite = false;
      this.checkFavorite(photoId);
      this.delay(400).then(() => (this.notFavorite = true));
    },
    delay(time) {
      return new Promise((resolve) => setTimeout(resolve, time));
    },
    addFavorite(photoId) {
      FavoriteService.addFavoriteByPhotoID(photoId);
    },
    deleteFavorite(photoId) {
      FavoriteService.deleteFavoriteByPhotoID(photoId);
    },
    checkFavorite(photoId) {
      FavoriteService.checkFavorite(photoId).then((response) => {
        this.$store.commit("SET_FAVORITES", response.data);
        if (response.data == 0) {
        this.addFavorite(photoId);
      } else { 
          this.deleteFavorite(photoId);
      }
      });
    },
  },
};
</script>

<style>
#notFavorite {
  color: #00adee;
  cursor: pointer;
}

#Favorite {
  color: #00adee;
  cursor: pointer;
}

#favoriteText {
  font-size: 12px;
}
</style>