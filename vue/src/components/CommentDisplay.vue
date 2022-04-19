<template>
  <div class="comment" >
        <div v-for="comment in this.$store.state.pictureComments"
        v-bind:key="comment.photo_id"
        v-bind:comment="comment">
          
            <p>{{ comment.username }}</p>
            <p>{{ comment.text }}</p>
            <p>{{comment.date_and_time | formatDate}}</p>
        </div>
    <!-- double check comment.commenter -->
    <!-- <h4>{{ comment.username }}</h4> -->
    
  </div>
</template>

<script>
import CommentService from "../services/CommentService";

export default {
  name: "comment-display",
  // props: ["comment"],
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
div.main div.comment {
  border: 1px black solid;
  border-radius: 6px;
  padding: 1rem;
  margin: 10px;
}

div.main div.comment p {
  margin: 20px;
}
div.main div.comment p .date_and_time {
  position:relative;
  left:80px;

}

div.main div.comment h4 {
  font-size: 1rem;
}



</style>
