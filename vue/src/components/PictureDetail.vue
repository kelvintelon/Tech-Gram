<template>

  <div class="picCard">

    <div class="picDetailContainer" >
      <img class="picture" :src=" photo.image_location "  id="img"/>

      <div class="picInfo">
        <div class="like_and_fav">
          <like-button class="likes" v-bind:photoId="photo.photo_id" ></like-button>
          <!-- <p class="like">like{{ (photo.likeCount === 0 || photo.likeCount === 1)? '' : 's' }}</p> -->
           <favorite-button class="favorites" :photoId="photo.photo_id"></favorite-button>
           <edit-caption id="editCaption" :photoUsername="photo.username"></edit-caption>

           
           <!-- <button id="editCaption" v-show="this.yourPhoto">Edit caption</button> -->
        <!-- <div class="likes">{{photo.likeCount}} like{{ (photo.likeCount === 0 || photo.likeCount === 1)? '' : 's' }}</div> -->
        </div>
        
        <div class="userInfo">
          <div class="picusername">{{photo.username}}</div> 
          <div class="piccaption">{{ photo.caption }}</div>
          
        </div>
        
      </div>
      
    </div>

    <div class="picComments">
      <add-comment></add-comment>  
      <comments-list></comments-list>
    </div>
          
  </div>
  
</template>

<script>
import PhotoService from "../services/PhotoService";
import CommentsList from "./CommentsList.vue";
import AddComment from "./AddComment.vue";
import LikeButton from "./LikeButton.vue";
import FavoriteButton from "./FavoriteButton.vue"
import EditCaption from "./EditCaption.vue"

export default {
  name: "picture-detail",
  
  components:{
      CommentsList,
      AddComment,
      LikeButton,
      FavoriteButton,
    EditCaption,
  },
    data() {
    return {
        username: "",
        yourPhoto: false,
      
      errorMsg: "",
    };
  },
   mounted(){
     this.retrievePicture();
     
    //  const UserString= localStorage.getItem("user");
    //  let firstIndex = UserString.indexOf("username");
    //  let secondIndex = UserString.indexOf("authorities");
    //  this.username = UserString.substring(firstIndex + 11, secondIndex - 3);



    //  if (this.username == this.$store.state.pictureDetails.username) {
    //    this.yourPhoto = true;
    // console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)
    //  }
    //  console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)

      
   },

  created() {
    this.retrievePicture();
     
    //  const UserString= localStorage.getItem("user");
    //  let firstIndex = UserString.indexOf("username");
    //  let secondIndex = UserString.indexOf("authorities");
    //  this.username = UserString.substring(firstIndex + 11, secondIndex - 3);



    //  if (this.username == this.$store.state.pictureDetails.username) {
    //    this.yourPhoto = true;
    // console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)
    //  }
    //  console.log(this.username);
    //  console.log(this.$store.state.pictureDetails.username)

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
*{
  font-family: Arial, Helvetica, sans-serif;
}

.picCard {
  /* display: -ms-flexbox;  */
  display: flex;
  flex-direction: column;
  width: 80%;
  /* -ms-flex-pack: distribute; */
  justify-content:center;
  /* margin: 40px auto; */
  /* flex-wrap: wrap; */

}
.picDetailContainer {
  /* background-color: #ddab23; */
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 400px;
  height: 100%;
  /* margin-left: 30px; */
  /* margin-bottom: 30px; */
  margin: 30px auto 0 auto;
  /* padding: 10px; */
}

div.picInfo{
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  
  /* align-items: center; */
  
  column-gap: 30px;
  
  margin-top: 10px;
  border-top:1px solid black;
  /* height: 20px; */
  padding: 20px;
  
}

.like_and_fav{
  display: flex;
  flex-direction: row;
  height: 35px;
  justify-content: space-between;
  padding-bottom: 10px;
}
.likes {
  display: inline-block;
}
.like{
  margin: 6px 0 1px 5px;
  font-size: 20px;
  
}

img.picture{
  
  margin: 20px 20px 20px 20px;
}
#img{
  margin: 40px 20px 20px 40px;
}

.userInfo{
  display: flex;
  flex-direction: row;
  /* this below is hard code */
  margin-top: 15px;
  margin-bottom: 7px;
}
#editCaption {
  margin-bottom: 40px;
  /* display: block; */
  width: 30%;
  height: 50px;
  border: none;
  outline: none;
  border-radius: 50px;
}

/* .likes,
.comments {
  margin-bottom: 10px;
} */
.picusername{
  font-weight: bold;
}
.piccaption{
  margin-left: 5px;
}
ul {
  list-style: none;
}
.picComments{
  width: 80%;
  /* border: 1px black solid; */
  border-radius: 6px;
  padding: 1rem;
  margin: 20px auto 0 auto;

}
</style>