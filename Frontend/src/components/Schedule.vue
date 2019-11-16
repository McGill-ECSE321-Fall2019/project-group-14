<template>
  <div id="schedule">
      <!-- style CSS -->
  <link rel="stylesheet" href="../static/css/style.css" />
    <div class="action">
    <div class="days">
      <div class="container" align="center">
        <div align="left" style="margin-left:5%">
          <ul>
            <h1>Monday</h1>
            <h1>Tuesday</h1>
            <h1>Wednesday</h1>
            <h1>Thursday</h1>
            <h1>Friday</h1>
            <h1>Saturday</h1>
            <h1>Sunday</h1>
          </ul>
        </div>
      </div>
    </div>
    <div class="right half">
      <div class="container" align="left">
        <div class="login" style="padding-right:0%">
          <p>
            9:00 am
            10:00 am
            11:00 am
            12:00 pm
            1:00 pm
            2:00 pm
            3:00 pm
            4:00 pm
            5:00 pm
            6:00 pm
            7:00 pm
            8:00 pm
            9:00 pm
          </p>

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

<script src="./js/schedule.js">
</script>
