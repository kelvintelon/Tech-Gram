<template>
  <div class="favorites">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <favorite-card></favorite-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import FavoriteCard from "../components/FavoriteCard.vue";


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
      
  },
  components: {
    HeaderLogo,
    SideMenu,
    FavoriteCard
  },
};
</script>

<style>
</style>