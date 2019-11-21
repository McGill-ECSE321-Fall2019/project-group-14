<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
    <div class="section-top-border">
      <h3 class="mb-30">Apply as a tutor</h3>
      <form action="#">
        <div class="mt-10">
          <input
            type="text"
            name="applicant_name"
            placeholder="Please enter your full name"
            onfocus="this.placeholder = ''"
            onblur="this.placeholder = 'Please enter your full name'"
            required
            class="single-input"
            v-model="name"
          />
        </div>
        <div class="mt-10">
          <input
            type="email"
            name="EMAIL"
            placeholder="Email address"
            onfocus="this.placeholder = ''"
            onblur="this.placeholder = 'Email address'"
            required
            class="single-input"
            v-model="email"
          />
        </div>

        <div class="mt-10">
          <input
            type="text"
            class="single-textarea"
            placeholder="Please enter the list of courses you can teach"
            onfocus="this.placeholder = ''"
            onblur="this.placeholder = 'Please enter the list of courses you can teach'"
            required
            v-model="courses"
          />
        </div>
        <div class="mt-30">
          <div class="switch-wrap" style="padding-bottom:25px;">
            <h6 id ="existingUser">Check if you are already a registered tutor (for update applications).</h6>
            <div class="primary-switch">
              <input v-model="checked" type="checkbox" id="default-switch" />
              <label for="default-switch"></label>
            </div>
          </div>
        </div>
      </form>
      <button class="genric-btn primary circle" @click="createApplication(checked, name, email, courses)">Apply!</button>
    </div>
  </div>
  </div>
</template>

<script src="./js/application.js">
</script>