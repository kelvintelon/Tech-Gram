

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
       <user-picture-card>
       </user-picture-card>       
       </main> 

</template>

<script>

import UserPictureCard from "../components/UserPictureCard.vue";


export default {
    name: 'user-profile',
    components: {
        UserPictureCard
    },
    methods:{
  
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

