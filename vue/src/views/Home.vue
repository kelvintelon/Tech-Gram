<template>
<div>
  <div class="home">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <picture-card v-bind:allPhotos="allPhotos"></picture-card>
    <!-- <div class="bg-image"></div> -->
    <img id="logoutimage" src="../../img/image.png" v-if="$store.state.token == ''"/>
    <!-- <img src="../../img/mark.png" alt=""> -->
 
    <link href="http://fonts.cdnfonts.com/css/billabong" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Grand+Hotel&display=swap" rel="stylesheet">

    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Fredoka+One"
    />
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
        allPhotos: true
    }
  },
  mounted(){
        // const imageObject1= localStorage.getItem("Photo1");
        
        // if (imageObject1) {
        //   let findDataIndex1 = imageObject1.indexOf("data")
        // let dataUrl1 = imageObject1.substring(findDataIndex1, imageObject1.length-2)
        //   this.ImagePresent = true
        //   document.querySelector("#imgPreview1").setAttribute("src", dataUrl1);
        // }

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
  /* margin-bottom: 20px; */
  /* font-family: Fredoka One; */
  /* font-family: 'Grand Hotel', cursive; */
  font-family: 'Billabong', sans-serif;
  /* color:rgb(62, 62, 63) */
  color: aliceblue;
  
}
.sideMenu{
  z-index: 1;
}

#logoutimage{
  filter: blur(7px);
  z-index: -100;
  margin:0;
  border-radius: 30px;
  width: 79%;
}


/* .logo {
  width: 60px;
  height: 60px;
} */
</style>