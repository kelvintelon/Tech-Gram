<template>
  <div class="card">
    <div
      class="picContainer"
      v-for="photo in this.$store.state.userImages"
      v-bind:key="photo.photo_id"
     
    >
      <img class="picture" :src=" photo.image_location "   @click="$router.push({name: 'photoDetails', params: {photoId: photo.photo_id}})"/>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likes">3</div>
      <div class="comments">Comments:</div>
    </div>
  </div>
  
</template>

<script>
import UploadFileService from "../services/UploadFileService";


export default {
  name: "user-picture-card",
   data() {
    return {
      username: ""
    };
  },
   mounted(){
     const UserString= localStorage.getItem("user");
     let firstIndex = UserString.indexOf("username");
     let secondIndex = UserString.indexOf("authorities");
     this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
  
      this.getUserImages();
   },

  created() {
    this.getUserImages();
  },
  methods: {
    getUserImages() {
      UploadFileService.getUserFiles(this.username).then(response => {
        this.$store.commit("SET_USERIMAGES", response.data);
      });
    },
  },
};
</script>

<style>
.card {
  display: -ms-flexbox; 
  display: inline-flex;
  max-width: 1550px;
  -ms-flex-pack: distribute;
  justify-content:left;
  margin: 40px auto;
  flex-wrap: wrap;
}
.picContainer {
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 15px;
  width: 200px;
  height: 100%;
  margin-left: 40px;
  margin-bottom: 40px;
  padding: 20px;
}

img {
  width: 80%;
  height: 80%;
  margin: 20px;
  margin-bottom: 15px;
}
.likes,
.comments {
  margin-bottom: 15px;
}
ul {
  list-style: none;
}
</style>