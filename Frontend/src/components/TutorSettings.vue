<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
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
            >
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
            >
          </div>
        </form>
        <br>
        <input
          @click="updatetutor(tutorName, password)"
          type="submit"
          value="Update Name and Password"
          class="btn_1"
        >
        <span v-if="errorMsg" style="color:red">Error: {{errorMsg}}</span>
      </div>

      <div class="section-top-border">
        <h3 class="mb-30">Modify Timeslots</h3>
        <a class="btn_1" href="#">asdasd</a>
      </div>

      <div class="section-top-border">
        <h3 class="mb-30">Modify Wages</h3>
        <div class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial">Course</div>
              <div class="country">Current Wage</div>
              <div class="visit">New Wage (Optional)</div>
            </div>
            <form>
            <div class="table-row" v-for="wage in wages" v-bind:key="wage.wageId">
              <div class="serial">{{ wage.courseName }}</div>
              <div class="country">{{ wage.wage / 100 }}</div>
              <div class="visit"><input type="number" v-model="wageUpdateAmounts[wage.wageId]"></div>
              <div class="percentage">
                <input
                  @click="updateWage(wage.wageId)"
                  type="submit"
                  value="Update Wage"
                  class="genric-btn primary circle"
                >
              </div>
            </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>>

<script src="./js/tutorsettings.js"></script>