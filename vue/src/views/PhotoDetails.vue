<template>
  <div>
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-detail></picture-detail>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureDetail from "../components/PictureDetail.vue";

export default {
  components: {
    HeaderLogo,
    SideMenu,
    PictureDetail,
  },
};
</script>

<style>
</style>