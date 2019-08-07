<template>
  <div>
    <h2>Pacman Zarabotai Pojaluista</h2>
    <div id="pacman"></div>
  </div>
</template>

<script>

import Pacman from './myPacman.js'

export default {
    mounted(){
        Pacman.startNow()
    }
}

</script>

<style>
</style>
