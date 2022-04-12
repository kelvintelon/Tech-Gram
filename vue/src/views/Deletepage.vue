<template>
  <div>
      
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>

    
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";

export default {
components: {
    HeaderLogo,
    SideMenu,
  },
}
</script>

<style>

</style>