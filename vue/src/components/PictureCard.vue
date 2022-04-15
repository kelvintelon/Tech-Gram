<template>
  <div class="card">
    <div
      class="picContainer"
      v-for="photo in this.$store.state.imagePosts"
      v-bind:key="photo.photo_id"
    >
      <img
        class="picture"
        :src="photo.image_location"
        @click="$router.push('photoDetails')"
      />
      <div class="caption">{{ photo.caption }}</div>
      <div class="likesBlock">
        <like-button class="likes"></like-button>
        <div class="likes likesCount">3</div>
      </div>
      <div class="comments">Comments:</div>
    </div>
  </div>
</template>

<script>
import UploadFileService from "../services/UploadFileService";
import LikeButton from "../components/LikeButton.vue";

export default {
  name: "picture-card",
  components: {
    LikeButton,
  },
  created() {
    this.getImagePosts();
  },
  methods: {
    getImagePosts() {
      UploadFileService.getFiles().then((response) => {
        this.$store.commit("SET_IMAGEPOSTS", response.data);
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
  justify-content: left;
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
.likes {
   display: inline-block;
}
.likesCount {
  margin-left: 3px;
  font-size: 20px;
}

</style>