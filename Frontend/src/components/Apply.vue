<template>
  <div class="action">
    <div class="col-lg-8 col-md-8" id="component">
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
          <div class="switch-wrap d-flex justify-content-between">
            <h5 id ="existingUser">Existing user?</h5>
            <div class="primary-switch">
              <input v-model="checked" type="checkbox" id="default-switch" />
              <label for="default-switch"></label>
            </div>
          </div>
        </div>
      </form>
      <button class="btn_1" @click="createApplication(checked, name, email, courses)">Apply!</button>
    </div>
  </div>
</template>>

<script src="./js/application.js">
</script>

<style>
h3 {
  margin-top: 200px;
}

#existingUser {
  margin-top: 50px;
}
#component {
  margin-left: auto;
  margin-right: auto;
}

.btn_1 {
  margin-top: 50px;
  margin-bottom: 50px;
}
.mb-30 {
  margin-top: 100px;
}
.primary-switch {
  margin-top: 50px;
}
</style>