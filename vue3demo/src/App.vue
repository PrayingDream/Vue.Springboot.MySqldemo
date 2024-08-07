<script setup>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import NavBar from "@/components/NavBar.vue"
import ContentField from "@/components/ContentField.vue";
</script>

<template>
  <NavBar/>
  <ContentField>
    <router-view/>
  </ContentField>
</template>

<style>
body {
  background-image: url(@/assets/image/background.png);
  background-size: cover;
}
</style>