<template>
  <div id="app">
    <h1>Pacman</h1>
    <div class="nav">
      <router-link to="/home">Game</router-link>
      <router-link to="/top">Top</router-link>
      <router-link v-if="!loggedIn" to="/login">Login</router-link>
      <router-link v-if="loggedIn" to="/logout">Logout</router-link>
    </div>
      <router-view></router-view>
  </div>
</template>

<script>

    import auth from './auth'
    export default {
        data () {
            return {
                loggedIn: auth.loggedIn()
            }
        },
        created () {
            auth.onChange = loggedIn => {
                this.loggedIn = loggedIn
            }
        }
    }
</script>

<style>
  @font-face {
    font-family: 'BDCartoonShoutRegular';
    src: url('./BD_Cartoon_Shout-webfont.ttf') format('truetype');
    font-weight: normal;
    font-style: normal;
  }
  h1, h2 {
    text-align:center;
    color: yellow
  }
  /*body { width:342px; margin:0px auto; font-family: BDCartoonShoutRegular; background-color: black;}*/
  body { width:60%; margin:0px auto; font-family: BDCartoonShoutRegular; background-color: black;}
  a { text-decoration:none; color: yellow;}
  a:hover { text-decoration:underline;}
  .nav{
    text-align: right;
  }
</style>
