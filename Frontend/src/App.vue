<template>
  <div id="app">
    <header-component></header-component>
    <main>
      <router-view></router-view> <!-- This renders the active route -->
    </main>
    <footer-component></footer-component>
  </div>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import FooterComponent from './components/FooterComponent.vue';

export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent,
  },
};
</script>

<style>
/* Ensuring that the body and html take the full height */
html, body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}

/* Flexbox to make sure the page uses full height */
#app {
  display: flex;
  flex-direction: column;
  height: 100%; /* Allow the app to fill the full page height */
}

main {
  flex: 1; /* This will make sure the main content grows and uses available space */
  padding: 20px;
  overflow-y: auto; /* Allow scrolling if content overflows */
}

footer-component {
  flex-shrink: 0; /* Ensure footer doesn't shrink */
}
</style>
