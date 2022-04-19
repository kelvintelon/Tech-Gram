<template>
  <div>
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <div class="currentUsernameBox">
      <h1 class="UsernameFont">{{ username }}</h1>
      <div class="numOfPics">{{ photoCount }} Pictures Uploaded</div>
    </div>
    <picture-card v-bind:userFeed="userFeed"></picture-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
import PhotoService from "../services/PhotoService";

export default {
  components: {
    HeaderLogo,
    SideMenu,
    PictureCard,
      },
       data() {
    return {
      userFeed: true,
      username: "",
      photoCount: "",
    };
  },
  mounted() {
   this.checkUser();
  this.getPhotoCount(this.username);
  },
  methods: {
    checkUser() {
      const UserString = this.$route.path;
      this.username = UserString.substring(10);
    },
    getPhotoCount(username) {
      PhotoService.getOtherUserPhotoCount(username).then((response) => {
        this.photoCount = response.data;
      });
    },
  },
};
</script>

<style>

</style>