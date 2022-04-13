<template>
  <div class="favorites">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card></picture-card>
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
    PictureCard
  },
};
</script>

<style>
</style>