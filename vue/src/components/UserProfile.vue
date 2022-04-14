

<template>
   <main class="seeProfile">
       <section class="posts">
           <div class ="posts" v-for="post in posts"
           :key="post._id">
           <img :src="post.image" :alt="post.desc"
           class="post-image"/>
           </div>
           <div>
               <img id="imgPreview1" src="" alt="Preview">
                <img id="imgPreview2" src="" alt="Preview">
                <img id="imgPreview3" src="" alt="Preview">
           </div>
    
       </section>       
       </main> 
</template>

<script>

import UploadFileService from "../services/UploadFileService";


export default {
    name: 'user-profile',
    methods:{
    getImagePosts(){
      UploadFileService.getFiles().then(response => {
        this.$store.commit("SET_IMAGEPOSTS", response.data);
      });
    },
    created(){
      this.getImagePosts();
    }
  },
 mounted(){
        const imageObject1= localStorage.getItem("Photo1");
        
        if (imageObject1) {
          let findDataIndex1 = imageObject1.indexOf("data")
        let dataUrl1 = imageObject1.substring(findDataIndex1, imageObject1.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview1").setAttribute("src", dataUrl1);
        }

        const imageObject2= localStorage.getItem("Photo2");
      

        if (imageObject2) {
           let findDataIndex2 = imageObject2.indexOf("data")
        let dataUrl2 = imageObject2.substring(findDataIndex2, imageObject2.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview2").setAttribute("src", dataUrl2);
        }

        const imageObject3= localStorage.getItem("Photo3");
        
        
        if (imageObject3) {
          let findDataIndex3 = imageObject3.indexOf("data")
        let dataUrl3 = imageObject3.substring(findDataIndex3, imageObject3.length-2)
          this.ImagePresent = true
          document.querySelector("#imgPreview3").setAttribute("src", dataUrl3);
        }
}
</script>

<style lang = "scss" scoped>
.seeProfile {
    display: flex;
    flex-direction: column;

    .profile-head {
        background: #00adee;
        padding: 15px;
        box-sizing: border-box;
    }
    .posts{
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-auto-rows: min-content;
        grid-gap: 5px;
        

        .post{
            padding: 0;
            .post-image{
                width: 100%;
                display: block;
            }
        }
    }
}

</style>

