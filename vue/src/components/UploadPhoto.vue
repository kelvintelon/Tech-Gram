<template>
  <div class="upload-pic">
      <div v-if="!image">
          <h2>Select an image</h2>
          <input type="file" name="file" id="file"
            class="inputfile" @change="onFileChange">
      </div>
      
    
      <div v-else>
          <img id="upload-img"  :src="image" />
          <div class="caption-container">
                <textarea class="caption-input" placeholder="Write a caption..." 
                    type="text" :caption="caption" 
                    @input="$emit('input', $event.target.value)"
                    cols="200" rows="10"></textarea>
            
          </div>
          <button @click="removeImage">Cancel</button>

      </div>

      

   
  </div>
</template>

<script>
import UploadFileService from "../services/UploadFileService";

export default {
    name: "upload-photo",
    data(){
        return {
            
            image:"",
            caption:"",
            
        };
    },
    beforeMount(){
        let vm = this
        console.log('before Mounted')
        vm.get('img')

    },

    methods:{
        get(key){
            this.image=localStorage.getItem(key);
        },

        set(key){
            // let vm = this
            try{
                localStorage.setItem(key, this.image);
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
            // let image = new Image();
            let reader = new FileReader();
            let vm = this;

            reader.onload = (e) => {
                vm.image = e.target.result;
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