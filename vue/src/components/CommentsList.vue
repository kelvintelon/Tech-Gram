<template>
  <div class="commentsList">
    <h3>Comments: </h3>
    <div class="comments">
    <!-- double check key for comment -->
      <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment">
          
            <p>{{ comment.username }}</p>
            <p>{{ comment.text }}</p>
            <p>{{comment.date_and_time | formatDate}}</p>
        </div>
      <!-- <comment-display
      /> -->
    </div>
  </div>
</template>

<script>
// import CommentDisplay from "./CommentDisplay";
import CommentService from "../services/CommentService";


export default {
  name: "comment-list",
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
  }
};
</script>

<style>
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
