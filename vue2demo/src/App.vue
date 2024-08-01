<template>
  <div>
    <NavBar/>
    <content-field>
      <router-view/>
    </content-field>
  </div>
</template>

<style>
body {
  background-image: url(@/assets/image/background.png);
  background-size: cover;
}
</style>

<script>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import NavBar from '@/components/NavBar.vue'
import ContentField from '@/components/ContentField.vue'

export default {
  components: {
    NavBar,
    ContentField,
  }
}
</script>
