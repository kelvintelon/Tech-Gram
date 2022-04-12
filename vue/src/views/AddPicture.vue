<template>
  <div>
      
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <upload-photo></upload-photo>
    
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import UploadPhoto from "../components/UploadPhoto.vue";

export default {
components: {
    HeaderLogo,
    SideMenu,
    UploadPhoto,
  },
}
</script>

<style>

</style>