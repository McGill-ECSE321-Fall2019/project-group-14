<template>
  <div id="login">
      <!-- style CSS -->
  <link rel="stylesheet" href="../static/css/style.css" />
    <div class="action">
    <div class="left half">
      <div class="container" align="center">
        <div align="center" style="padding-left:15%">
          <span>ECSE 321 team 14</span>
        </div>
      </div>
    </div>
    <div class="right half">
      <div class="container" align="center">
        <div class="login" style="padding-right:15%">
          <input class="login-text" type="text" placeholder="Email" v-model="email" />
          <input class="login-text" type="password" placeholder="Password" v-model="password" />
          <input
            @click="login(email,password)"

            type="submit"
            value="Login"
            class="btn_1"
          />
          <br>
          <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
        </div>
      </div>
    </div>
    </div>
  </div>
</template>

<script src="./js/login.js"></script>
