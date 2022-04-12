<template>
  <div class="upload-pic">
      <div v-if="!image">
          <h2>Select an image</h2>
          <input type="file" @change="onFileChange">
      </div>
    <!-- why the next div not working... -->
      <div v-else>
          <img id="upload-img"  :src="image" />
          <button @click="removeImage">Remove image</button>
      </div>
   
  </div>
</template>

<script>

export default {
    name: "upload-photo",
    data(){
        // image:''
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
                localStorage.setItem(key,this.image);
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
            // refresh page

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
            // refresh page

        }

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