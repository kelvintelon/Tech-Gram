<template>
  <div class="commentsList">
    <h3 id="commentHeader">Comments: </h3>
    <div class="comments2">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment"
        id="commentDiv">
      <div id="userAndDate">
    <router-link
        v-bind:to="{ name: 'userPage', params: { username: comment.username } }" v-if="checkUser(comment)"
      >
        <div id="commentUsername">{{ comment.username }}</div>
      </router-link>

      <router-link
        v-bind:to="{ name: 'userFeed', params: { username: comment.username } }" v-if="!checkUser(comment)"
      >
        <div id="commentUsername">{{ comment.username }}</div>
      </router-link>

      <p id="commentDate">Posted on {{comment.date_and_time | formatDate}}</p>
      </div>
            <p id="commentText">{{ comment.text }}</p>
            
        </div>
      <!-- <comment-display
      /> -->
    </div>
  </div>
</template>

<script>
import CommentService from "../services/CommentService";


export default {
  name: "comment-list",
  data() {
    return {
        username: "",
    }
  },
  components: {
    // CommentDisplay
  },
  created(){
      this.getCommentsByPhotoId();
  },
  mounted(){
    this.getCommentsByPhotoId();
  },
  methods:{
    getCommentsByPhotoId(){
      CommentService.getCommentsByPhotoID(this.$route.params.photoId)
                    .then((response) =>{
                      this.$store.commit("SET_COMMENTS", response.data)
                    })  
    },
    checkUser(comment){
      
      const UserString = localStorage.getItem("user");
      let firstIndex = UserString.indexOf("username");
      let secondIndex = UserString.indexOf("authorities");
      this.username = UserString.substring(firstIndex + 11, secondIndex - 3);
      if (this.username == comment.username){
        return this.isUser=true;
      }else{
        return this.isUser=false;
      }
    },
  }
};
</script>

<style>
#commentText {
  margin-top: 0;

}
#userAndDate{
 display: flex;
 justify-content: space-between;
 width: 95%;
}
#commentUsername {
  color:#00adee;
}
div.main {
  margin: 1rem 0;
}
.comments2{
  /* background-color: rgb(209, 208, 208); */
  background-color:#efe3ef;
  border: 4px solid rgb(253, 168, 168);
  border-radius: 20px;
}
h3{
  margin: auto;
  font-weight: normal;
}
#commentDiv {
  padding-top: 20px;
  border-bottom-style: solid;
  border-bottom-color:rgb(189, 159, 159) ;
  border-bottom-width: 1px;
  padding-left: 20px;
}
#commentHeader {
  color: white;
  font-size: 20px;
}
#commentDate {
  margin-top: 0;
}
</style>
