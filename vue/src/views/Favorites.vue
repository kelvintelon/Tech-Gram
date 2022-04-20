<template>
  <div class="favorites">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo id="favHeader"></header-logo>
     <div class="currentUsernameBox">
      <h1 class="UsernameFont">{{ username }}'s favorites</h1>
      <div class="numOfPics">{{ photoCount }} Picture{{ ( photoCount === 0 || photoCount === 1)? '' : 's' }}</div>
    </div>
    <picture-card v-bind:favoritesPhotos="favoritesPhotos"></picture-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
import FavoriteService from "../services/FavoriteService";

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
        favoritesPhotos: true,
        username: "",
        photoCount: "",
    }
  },
  mounted() {
      this.checkUser();
      this.getPhotoCount();
  },
  methods:{
      checkUser() {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
    },
    getPhotoCount() {
      FavoriteService.getPhotoCount().then((response) => {
        this.photoCount = response.data;
      });
    },
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