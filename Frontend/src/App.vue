<template>
  <div id="app">
    <header-component></header-component>
    <main>
      <router-view :key="$route.fullPath"></router-view> <!-- This renders the active route -->
    </main>
    <footer-component></footer-component>
  </div>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import FooterComponent from './components/FooterComponent.vue';

export default {
  name: 'App',
  created() {
   
    this.$store.dispatch('checkLoginState');
     
  },
  components: {
    HeaderComponent,
    FooterComponent,
  },
};
</script>

<style>

html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}


#app {
  display: flex;
  flex-direction: column;
  height: 100%; 
}

main {
  flex: 1; 
  padding: 20px;
  overflow-y: auto; 
}

footer-component {
  flex-shrink: 0; 
}
</style>
