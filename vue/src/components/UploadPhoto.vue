<template>
  <div class="upload-pic">
      <div v-if="!imagePost.image">
          <h2>Select an image</h2>
          <input type="file" name="file" id="file"
            class="inputfile" @change="onFileChange">
      </div>
      
    
      <div v-else>
          <img id="upload-img"  :src="imagePost.image" />
          <div class="caption-container">
              <form v-on:submit.prevent="submitForm">
                <textarea class="caption-input" placeholder="Write a caption..." 
                    type="text" :caption="caption" 
                    @input="$emit('input', $event.target.value)"
                    cols="200" rows="10" name="textarea"></textarea>
                    <br>
                     <button @click="removeImage">Cancel</button>
                    <button type="submit">Upload</button>
            </form>
          </div>

      </div>
  
  </div>
</template>

<script>
import UploadFileService from "../services/UploadFileService";

export default {
    name: "upload-photo",
    data(){
        return {
            imagePost:{
                image:"",
                caption:"",
            }                     
        };
    },
    beforeMount(){
        let vm = this
        console.log('before Mounted')
        vm.get('img')
    // before mount displays the image when the page loads
    },

    methods:{
        submitForm() {
            this.imagePost.caption = document.querySelector('textarea').value;
            console.log(this.imagePost.caption)
            this.imagePost.image ="";
            this.$router.push("/");
        },
        get(key){
            this.imagePost.image=localStorage.getItem(key);
        },

        set(key){
            // let vm = this
            try{
                localStorage.setItem(key, this.imagePost.image);
            }
            catch(e){
                console.log(`Storage failed: ${e}`);
            }
        },

        onFileChange(e){
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length)
                return;
            this.createImage(files[0]);

        },

        createImage(file){

            let reader = new FileReader();
            // The vm in this case means viewmodel, its a shortcut for writing this.someMethod() so that you can use vm.someMethod()
            let vm = this;

            reader.onload = (e) => {
                vm.imagePost.image = e.target.result;
                vm.set('img');
                            
            };
            reader.readAsDataURL(file);
        },
        removeImage(){
            this.image='';
            localStorage.removeItem('img')

        },
        upload() {
            this.progress = 0;
            UploadFileService.upload(this.image)
            
            .then(response => {
                if (response.status ===201){
                    return UploadFileService.getFiles();
                }
            })
            
            .catch((err) => {
            this.message = "Could not upload the image! " + err;
            this.image = undefined;
        });
    },

    }

    

};
</script>

<style>
textarea {
    width:30%;
}
.upload-pic{
    text-align: center;
}

#upload-img{
    width:30%;
    margin: auto;
    display: block;
    margin-bottom:10px;
}

button{

}


</style>