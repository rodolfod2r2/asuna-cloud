<template>
  <div id="app">
    <AsunaNavBar/>
    <div class="container-fluid">
      <BreadCrumb/>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import AsunaNavBar from "@/components/navbar/AsunaNavBar";
import BreadCrumb from "@/components/breadcrumbs/BreadCrumbs";

export default {
  name: 'App',
  components: {
    BreadCrumb,
    AsunaNavBar
  }
}
</script>

<style>
#app {
  font-family: "Roboto Light", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
