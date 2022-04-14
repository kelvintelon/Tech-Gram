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
 <!-- <div class = "image" v-show="ImagePresent">
<img id="imgPreview1" src="" alt="Preview">
<img id="imgPreview2" src="" alt="Preview">
<img id="imgPreview3" src="" alt="Preview">
</div> -->
  
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
 /*  mounted(){
        const imageObject1= localStorage.getItem("Photo1");
        
        if (imageObject1) {
          let findDataIndex1 = imageObject1.indexOf("data")
        let dataUrl1 = imageObject1.substring(findDataIndex1, imageObject1.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview1").setAttribute("src", dataUrl1);
        }

        const imageObject2= localStorage.getItem("Photo2");
      

        if (imageObject2) {
           let findDataIndex2 = imageObject2.indexOf("data")
        let dataUrl2 = imageObject2.substring(findDataIndex2, imageObject2.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview2").setAttribute("src", dataUrl2);
        }

        const imageObject3= localStorage.getItem("Photo3");
        
        
        if (imageObject3) {
          let findDataIndex3 = imageObject3.indexOf("data")
        let dataUrl3 = imageObject3.substring(findDataIndex3, imageObject3.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview3").setAttribute("src", dataUrl3);
        }
    // mount displays the image when the page loads
    }, */
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