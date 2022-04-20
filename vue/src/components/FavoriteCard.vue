<template>
  <div class="card">
    <div
      class="picContainer"
      v-for="photo in this.$store.state.favoriteImages"
      v-bind:key="photo.photo_id"
     
    >
      <img class="picture" :src=" photo.image_location "  @click="$router.push({name: 'photoDetails', params: {photoId: photo.photo_id}})"/>
      <div class="caption">{{ photo.caption }}</div>
      <div class="likes">3</div>
      <div class="comments">Comments:</div>
    </div>
  </div>
  
</template>

<script>
import FavoriteService from "../services/FavoriteService";


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
     console.log(this.username);
   },

  created() {
    this.getFavoriteImages();
  },
  methods: {
    getFavoriteImages() {
       FavoriteService.getFavorites().then(response => {
           this.$store.commit("SET_FAVORITEIMAGES", response.data)
       });
    },
  },
};
</script>

<style>
.card {
  display: -ms-flexbox; 
  display: inline-flex;
  /* max-width: 1550px; */
  width:80%;
  -ms-flex-pack: distribute;
  justify-content:left;
  margin: 40px auto;
  flex-wrap: wrap;
}
.picContainer {
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 200px;
  height: 100%;
  margin-left: 30px;
  margin-bottom: 30px;
  padding: 10px;
}

img {
  width: 80%;
  height: 80%;
  margin: 20px;
  margin-bottom: 10px;
}
.likes,
.comments {
  margin-bottom: 10px;
}
ul {
  list-style: none;
}
</style>