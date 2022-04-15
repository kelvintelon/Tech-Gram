<template>
  <div class="picCard">

    <div class="picDetailContainer" >
      <img class="picture" :src=" photo.image_location " />
      <div class="caption">{{ photo.caption }}</div>
      <div class="likes">{{photo.likeCount}}</div>
    </div>

    <div class="picComments">
      <!-- <div class="comments">{{photo.comments}}</div> -->
  
      <comments-list></comments-list>
  
      <add-comment></add-comment>   
    </div>
          
  </div>
  
</template>

<script>
import PhotoService from "../services/PhotoService";
import CommentsList from "./CommentsList.vue";
import AddComment from "./AddComment.vue";

export default {
  name: "picture-detail",
  components:{
      CommentsList,
      AddComment
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
.picCard {
  /* display: -ms-flexbox;  */
  display: flex;
  flex-direction: column;
  max-width: 1550px;
  /* -ms-flex-pack: distribute; */
  justify-content:left;
  margin: 40px auto;
  flex-wrap: wrap;
}
.picDetailContainer {
  background-color: #ddab23;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 400px;
  height: 100%;
  /* margin-left: 30px; */
  margin-bottom: 30px;
  padding: 10px;
}
.likes:before {
  content: "❤️";
}
img.picture{
  /* width: 80%;
  height: 80%;
  margin: 20px;
  margin-bottom: 10px; */
  margin: 40px 20px 20px 20px;
}
.likes,
.comments {
  margin-bottom: 10px;
}
ul {
  list-style: none;
}
</style>