<template>
  <div class="card">

    <!-- <div
      class="picContainer"
      v-for="photo in this.$store.state.pictureDetails"
      v-bind:key="photo.photo_id"
      
    > -->
    <div
      class="picContainer" 
    >
      <img class="picture" :src=" photo.image_location " />
      <div class="caption">{{ photo.caption }}</div>
      <div class="likes">{{photo.likeCount}}</div>
      <div class="comments">{{photo.comments}}</div>
    </div>
  </div>
  
</template>

<script>
import PhotoService from "../services/PhotoService";
// import commentsList from "@/components/CommentsList";

export default {
  name: "picture-detail",
  components:{
      //CommentsList
  },
    data() {
    return {
        // username: ""
      
      errorMsg: "",
    };
  },
   mounted(){
     const UserString= localStorage.getItem("user");
     let firstIndex = UserString.indexOf("username");
     let secondIndex = UserString.indexOf("authorities");
     this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
     console.log(this.username);
      this.retrievePicture();
   },

  created() {
    this.retrievePicture();
    console.log(this.$route.params.photoId);
  },
  methods: {
    retrievePicture() {
      PhotoService.getOnePicture(this.$route.params.photoId).then(response => {
        this.$store.commit("SET_CURRENT_PIC", response.data);
        
      })
      .catch(error =>{
          if (error.response && error.response.status === 404) {
            alert(
              "Photo not available. This photo may have been deleted or you have entered an invalid card ID."
            );
            this.$router.push("/");
          }
      });
    },
  },
  computed:{
      photo(){
          return this.$store.state.pictureDetails;
      }
  }
 
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
  border-radius: 10px;
  width: 200px;
  height: 100%;
  margin-left: 30px;
  margin-bottom: 30px;
  padding: 10px;
}
.likes:before {
  content: "❤️";
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