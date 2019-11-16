<template>
  <div class="action">
    <div class="col-lg-8 col-md-8" id="component">
      <h3 class="mb-30">Tutor account settings</h3>
      <form action="#">
        <div class="mt-10">
          <input
            type="text"
            name="tutorName"
            placeholder="Tutor Name"
            onfocus="this.placeholder = ''"
            onblur="this.placeholder = 'Tutor name'"
            required
            class="single-input"
            v-model="tutorName"
          />
        </div>
        <div class="mt-10">
          <input
            type="password"
            name="password"
            placeholder="Password"
            onfocus="this.placeholder = ''"
            onblur="this.placeholder = 'Password'"
            required
            class="single-input"
            v-model="password"
          />
        </div>
      </form><br>
      <input
            @click="updatetutor(tutorName, password)"
  
            type="submit"
            value="Update Name and Password"
            class="btn_1"
          />
          <span v-if="errorMsg" style="color:red">Error: {{errorMsg}} </span>
      <br><br><br><br><br>
      <h3 class="mb-30">Modifiy Timeslots</h3>
      <a class="btn_1" href="#">asdasd</a>
      <br><br><br><br><br>
      <h3 class="mb-30">Modifiy Wages</h3>
      <a class="btn_1" href="#">asdasd</a>
    </div>
  </div>
</template>>

<script src="./js/tutorsettings.js"></script>