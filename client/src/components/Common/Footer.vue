<template>
  <v-footer
    dark
    height="auto"
  >
	<v-card
      class="flex"
      flat
      tile
    >
    <v-card-actions class="grey darken-3 justify-center">
        &copy;2019 — <strong>LionBit</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
</template>
<style>

footer {
  position: absolute;
  bottom: 0;
  width: 100%;
}
</style>

<script>

  const Footer = {
    name: 'Footer',
    props: {
      activeUser: Object
    },
    data: () => ({
    })
  }
  export default Footer
</script>