<template>
  <div class="wholePage">
    <div class="sideMenu"><side-menu></side-menu></div>
    <header-logo></header-logo>
    <div class="currentUsernameBox">
      <h1 class="UsernameFont">{{ username }}</h1>
      <div class="numOfPics">{{ photoCount }} Pictures Uploaded</div>
    </div>
    <picture-card v-bind:userPhotos="userPhotos"></picture-card>
  </div>
</template>

<script>
import HeaderLogo from "../components/HeaderLogo.vue";
import SideMenu from "../components/SideMenu.vue";
import PictureCard from "../components/PictureCard.vue";
import PhotoService from "../services/PhotoService";
import UploadFileService from "../services/PhotoService";

export default {
  components: {
    HeaderLogo,
    SideMenu,
    PictureCard,
  },
  data() {
    return {
      userPhotos: true,
      username: "",
      photoCount: "",
    };
  },
  mounted() {
    this.checkUser();
    this.getPhotoCount();
  },
  methods: {
    checkUser() {
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
    },
    getPhotoCount() {
      PhotoService.getPhotoCount().then((response) => {
        this.photoCount = response.data;
      });
    },
        getUserFeedImages() {
      UploadFileService.getUserFiles(this.$route.params.username).then(
        (response) => {
          this.username = response.data;
        });
    },
  },
};
</script>

<style>
.currentUsernameBox {
  border-bottom: 1px solid black;
  border-bottom-style: none;
  padding-left: 20px;
  display: flex;
  justify-content: space-between;
}

.UsernameFont {
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 55px;
  font-family: Arial, Helvetica, sans-serif;
  color: #efe3ef;

}
.numOfPics {
  background-color: #efe3ef;
  border: 1px solid black;
  border-radius: 30px;
  height: 30px;
  font-size: 16px;
  font-weight: bold;
  padding-top: 10px;
  padding-bottom: 0px;
  padding-right: 15px;
  padding-left: 15px;
  margin-top: 20px;
  margin-right: 21%;
}


</style>