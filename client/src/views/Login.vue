<template>
  <div class="authentication-wrapper authentication-1 px-4" style="background: #fff">
    <div class="authentication-inner py-5">

      <!-- Logo -->
      <div class="d-flex justify-content-center align-items-center">
        <div class="ui-w-60">
          <div class="w-100 position-relative">
            <!--<svg class="w-100 h-100 position-absolute" viewBox="0 0 148 80" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><defs><linearGradient id="a" x1="46.49" x2="62.46" y1="53.39" y2="48.2" gradientUnits="userSpaceOnUse"><stop stop-opacity=".25" offset="0"></stop><stop stop-opacity=".1" offset=".3"></stop><stop stop-opacity="0" offset=".9"></stop></linearGradient><linearGradient id="e" x1="76.9" x2="92.64" y1="26.38" y2="31.49" xlink:href="#a"></linearGradient><linearGradient id="d" x1="107.12" x2="122.74" y1="53.41" y2="48.33" xlink:href="#a"></linearGradient></defs><path class="fill-primary" transform="translate(-.1)" d="M121.36,0,104.42,45.08,88.71,3.28A5.09,5.09,0,0,0,83.93,0H64.27A5.09,5.09,0,0,0,59.5,3.28L43.79,45.08,26.85,0H.1L29.43,76.74A5.09,5.09,0,0,0,34.19,80H53.39a5.09,5.09,0,0,0,4.77-3.26L74.1,35l16,41.74A5.09,5.09,0,0,0,94.82,80h18.95a5.09,5.09,0,0,0,4.76-3.24L148.1,0Z"></path><path transform="translate(-.1)" d="M52.19,22.73l-8.4,22.35L56.51,78.94a5,5,0,0,0,1.64-2.19l7.34-19.2Z" fill="url(#a)"></path><path transform="translate(-.1)" d="M95.73,22l-7-18.69a5,5,0,0,0-1.64-2.21L74.1,35l8.33,21.79Z" fill="url(#e)"></path><path transform="translate(-.1)" d="M112.73,23l-8.31,22.12,12.66,33.7a5,5,0,0,0,1.45-2l7.3-18.93Z" fill="url(#d)"></path></svg>-->
            <img src="@/assets/logo.jpeg">
          </div>
        </div>
      </div>
      <!-- / Logo -->

      <!-- Form -->
      <form class="my-5">
      <div class="alert alert-danger" v-if="error">{{ error }}</div>
        <b-form-group label="Correo electrónico">
          <b-input v-model="credentials.email" />
        </b-form-group>
        <b-form-group>
          <div slot="label" class="d-flex justify-content-between align-items-end">
            <div>Constraseña</div>
            <a href="javascript:void(0)" class="d-block small" style="margin-left:100px">¿Olvidó contraseña?</a>
          </div>
          <b-input type="password" v-model="credentials.password" />
        </b-form-group>

        <div class="d-flex justify-content-between align-items-center m-0">
          <b-check v-model="credentials.rememberMe" class="m-0">Recordarme</b-check>
          <b-btn variant="primary" v-on:click="login()">Ingresar</b-btn>
        </div>
      </form>
      <!-- / Form -->

      <div class="text-center text-muted">
        LionBit SGA
      </div>

    </div>
  </div>
</template>

<style lang="scss">
$authentication-1-inner-max-width:300px;
$authentication-2-inner-max-width:300px;
$authentication-4-inner-max-width:300px;

.authentication-wrapper {
  display: flex;
  flex-basis: 100%;
  min-height: 100vh;
  width: 100%;

  .authentication-inner {
    width: 100%;
  }

  &.authentication-1,
  &.authentication-2,
  &.authentication-4 {
    align-items: center;
    justify-content: center;
  }

  &.authentication-1 .authentication-inner {
    max-width: $authentication-1-inner-max-width;
  }

  &.authentication-2 .authentication-inner {
    max-width: $authentication-2-inner-max-width;
  }

  &.authentication-3 {
    align-items: stretch;
    justify-content: stretch;

    .authentication-inner {
      display: flex;
      align-items: stretch;
      flex-wrap: nowrap;
      justify-content: stretch;
    }
  }

  &.authentication-4 .authentication-inner {
    max-width: $authentication-4-inner-max-width;
  }
}

// IE 10-11 fix
@media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
  .authentication-wrapper:after {
    content: '';
    display: block;
    flex: 0 0 0%;
    min-height: inherit;
    width: 0;
    font-size: 0;
  }
}

</style>

<script>

import api from '../Api';
import axios from "axios";
import Vue from "vue";
import { TokenService } from '../services/storage.service'

export default {
  name: 'Login',
  metaInfo: {
    title: 'Login'
  },
  data: () => ({
    error:false,
    credentials: {
      email: '',
      password: '',
      // rememberMe: false
    }
  }),
  methods:
  {
     login () {
      api.login( this.credentials.email, this.credentials.password )
      .then(request => this.loginSuccessful(request))
      .catch(() => this.loginFailed())
  },
    loginSuccessful (req) {
      console.log(req);
    if (!req.data.token) {
      this.loginFailed()
      return
    }
    TokenService.saveToken(req.data.token);
    if (TokenService.getToken() !== null) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${TokenService.getToken()}`;
    }

    this.error = false

    this.$router.replace(this.$route.query.redirect || '/')
  },

  loginFailed () {
    this.error = '¡Correo o contraseña equivocada!'
    delete localStorage.token
}
  }
}
</script>
