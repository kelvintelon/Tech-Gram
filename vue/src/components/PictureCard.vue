<template>
  <div class="picContainer" @click="$router.push('photoDetails')">
    <div class="picture">
      <img
        src="https://www.rd.com/wp-content/uploads/2021/01/GettyImages-1175550351.jpg?resize=2048,1339"
      />
    </div>
    <div class="likes">3</div>
    <div class="comments">Comments:</div>
  </div>
</template>

<script>
import UploadFileService from "../services/UploadFileService";


export default {
  name: "picture-card",
  methods:{
    getImagePosts(){
      UploadFileService.getFiles().then(response => {
        this.$store.commit("SET_IMAGEPOSTS", response.data);
      });
    },
    created(){
      this.getImagePosts();
    }
  }

};
</script>

<style>
.picContainer {
  background-color: #efe3ef;
  border: 1px solid black;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  width: 250px;
  height: 100%;
  margin: 20px;
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
</style>