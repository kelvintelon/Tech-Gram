<template>
  <div class="upload-pic">
      <div v-if="!imagePost.imageLocation">
          <h2>Select an image</h2>
          <input type="file" name="file" id="file"
            class="inputfile" @change="onFileChange">
      </div>
      
    
      <div v-else>
          <img id="upload-img"  :src="imagePost.imageLocation" />
          <div class="caption-container">
              <form v-on:submit.prevent="submitForm">
                <textarea class="caption-input" placeholder="Write a caption..." 
                    type="text" :caption="imagePost.caption" 
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
                imageLocation:"",
                caption:"",
            }                     
        };
    },
    beforeMount(){
        let vm = this
        console.log('before Mounted')
        vm.get('img')
    // before mount displays the image when the page loads
        // line 49 is to display nothing when page loads
        this.imagePost.imageLocation='';
        
    },

    methods:{
        submitForm() {
            this.imagePost.caption = document.querySelector('textarea').value;
            console.log(this.imagePost.caption)
            this.imagePost.imageLocation ="";
            this.$router.push("/");
        },
        get(key){
            this.imagePost.imageLocation=localStorage.getItem(key);
        },

        set(key){
            // let vm = this
            try{
                localStorage.setItem(key, this.imagePost.imageLocation);
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
                vm.imagePost.imageLocation = e.target.result;
                vm.set('img');
                            
            };
            reader.readAsDataURL(file);
        },
        removeImage(){
            this.imagePost.imageLocation='';
            localStorage.removeItem('img')

        },
        upload() {
            // this.progress = 0;
            
            UploadFileService.upload(this.imagePost)
            
            .then(response => {
                if (response.status ===201){
                   
                    return UploadFileService.getFiles();
                }
            })
            
            .catch((err) => {
            this.message = "Could not upload the image! " + err;
            this.imagePost.imageLocation = undefined;
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