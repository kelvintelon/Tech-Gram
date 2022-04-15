<template>
  <div>
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card v-bind:userPhotos="userPhotos"></picture-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";

export default {
  components: {
    HeaderLogo,
    SideMenu,
    PictureCard,
      },
       data() {
    return {
        userPhotos: true
    }
  },
};
</script>

<style>
</style>