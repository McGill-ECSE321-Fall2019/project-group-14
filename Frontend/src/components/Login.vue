<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border" style="text-align: -webkit-center;">
        <h3 class="mb-30">Log in as Tutor</h3>
        <form action="#">
          <div class="mt-10">
            <input type="text" placeholder="Email" v-model="email" required class="single-input" style="width:35%;">
          </div>
          <div class="mt-10">
            <input
              type="password"
              placeholder="Password"
              v-model="password"
              required
              class="single-input"
              style="width:35%;"
              v-on:keyup.enter="login(email,password)"
            >
          </div>
        </form>
        <br>
        <input @click="login(email,password)" type="submit" value="Login" class="btn_1">
        <h4 v-if="errorLogin" style="color:red; padding-top:20px">Error: {{errorLogin}}</h4>
      </div>
    </div>
  </div>
</template>

<script src="./js/login.js"></script>