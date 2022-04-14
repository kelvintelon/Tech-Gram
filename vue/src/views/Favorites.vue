<template>
  <div class="favorites">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card></picture-card>
  <div>{{ photo_id }}</div>
  <div>{{ user_id }}</div>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";

import favoriteService from "../services/AuthService"

export default {
  data() {
    return {
        favoritePhoto: {
          favorite_id: 0,
          photo_id: 0,
          user_id: 0,
          caption: "",
          image_location: "",
          date_and_time: "",
          like_count: 0
        },
    }
  },
  mounted() {
      favoriteService.getFavorites().then(response => {
        this.photo_id = response.data.photo_id;
        this.user_id = response.data.user_id;
      })
  },
  components: {
    HeaderLogo,
    SideMenu,
    PictureCard
  },
};
</script>

<style>
</style>