<template>
  <div>
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <user-picture-card></user-picture-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import UserPictureCard from "../components/UserPictureCard.vue";

export default {
  components: {
    HeaderLogo,
    SideMenu,
    UserPictureCard,
      },
};
</script>

<style>
</style>