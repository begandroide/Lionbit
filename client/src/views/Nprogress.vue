<template>
      <nprogress-container></nprogress-container>
      <!-- <v-progress-linear
        :active="loading"
        :indeterminate="loading"
        absolute
        bottom
        color="deep-purple accent-4"
      ></v-progress-linear> -->

</template>

<script>
import NprogressContainer from 'vue-nprogress/src/NprogressContainer';
export default {
  components: {
    NprogressContainer
  }
}
//   export default {
//     data: () => ({
//       loading: false,
//     }),

//     watch: {
//       loading (val) {
//         if (!val) return

//         setTimeout(() => (this.loading = false), 3000)
//       },
//     },
//   }
</script>