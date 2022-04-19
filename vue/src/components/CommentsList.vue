<template>
  <div class="commentsList">
    <h3>Comments: </h3>
    <div class="comments">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment">
          
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

            <!-- <p id="commentUsername">{{ comment.username }}</p> -->
            <p>{{ comment.text }}</p>
            <p>{{comment.date_and_time | formatDate}}</p>
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
#commentUsername {
  color:#00adee;
}
div.main {
  margin: 1rem 0;
}
.comments{
  /* background-color: rgb(209, 208, 208); */
  background-color:#efe3ef;
}
h3{
  margin: auto;
  font-weight: normal;
}
</style>
