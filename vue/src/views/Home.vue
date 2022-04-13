<template>
  <div class="home">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card></picture-card>
    <login></login>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
  </div>
</template>

<script>
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
import HeaderLogo from "../components/HeaderLogo.vue";

export default {
  name: "home",
  components: {
    SideMenu,
    PictureCard,
    HeaderLogo,
  },
};
</script>
<style>
body {
  background-color: #00adee;
  margin: 0px;
}

.title {
  font-size: 40px;
  font-family: Fredoka One;
}

.logo {
  width: 70px;
  height: 70px;
}
</style>