<template>
<div>
  <div class="home">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card></picture-card>
    <!-- <login></login> -->
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
  </div>
  <div class = "image" v-show="ImagePresent">
<img id="imgPreview" src="" alt="Preview">
</div>
  
  </div>
</template>

<script>
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
import HeaderLogo from "../components/HeaderLogo.vue";

export default {
  name: "home",
  data() {
    return {
        ImagePresent: false,
    }
  },
   mounted(){
        const recentImageDataUrl = localStorage.getItem("img");
        if (recentImageDataUrl) {
          this.ImagePresent = true
          document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
        }
    // mount displays the image when the page loads
    },
  components: {
    SideMenu,
    PictureCard,
    HeaderLogo,
  },
};
</script>
<style>
.image {
  width:30%;
    margin: auto;
    display: block;
    margin-bottom:10px;
}

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