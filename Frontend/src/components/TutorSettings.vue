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
          class="genric-btn primary circle"
        >
        <span v-if="errorMsg" style="color:red">Error: {{errorMsg}}</span>
      </div>

      <div class="section-top-border">
        <h3 class="mb-30">Modify Timeslots</h3>
        <div class="progress-table-wrap" style="overflow: visible;">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial">Date</div>
              <div class="country">Time</div>
              <div class="visit"></div>
            </div>
            <div class="table-row" v-for="timeslot in timeslots" v-bind:key="timeslot.timeSlotId">
              <div class="serial">{{ timeslot.date }}</div>
              <div class="country">{{ timeslot.time }}</div>
              <div class="visit">
                <input
                  @click="deleteTimeslot(timeslot.timeSlotId)"
                  type="submit"
                  value="Delete timeslot"
                  class="genric-btn primary circle"
                >
              </div>
            </div>
            <form>
              <div class="table-row">
                <div class="serial">
                  <b-form-input v-model="newDate" placeholder="YYYY-MM-DD" style="width:125px;"></b-form-input>
                </div>

                <div class="country">
                  <b-form-select v-model="newTime" style="width:145px;">
                    <option disabled :value="null">Select Time</option>
                    <option>09:00:00</option>
                    <option>10:00:00</option>
                    <option>11:00:00</option>
                    <option>12:00:00</option>
                    <option>13:00:00</option>
                    <option>14:00:00</option>
                    <option>15:00:00</option>
                    <option>16:00:00</option>
                    <option>17:00:00</option>
                    <option>18:00:00</option>
                    <option>19:00:00</option>
                    <option>20:00:00</option>
                  </b-form-select>
                </div>
                <div class="visit">
                  <input
                    @click="addTimeslot(newDate, newTime)"
                    type="submit"
                    value="Create timeslot"
                    class="genric-btn info circle"
                  >
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>

      <div class="section-top-border">
        <h3 class="mb-30">Modify Wages</h3>
        <div class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial">Course</div>
              <div class="country" style="width: 20%;">Current Wage</div>
              <div class="visit">New Wage (Optional)</div>
              <div class="percentage" style="padding-right: 170px;"></div>
            </div>
            <form>
              <div class="table-row" v-for="wage in wages" v-bind:key="wage.wageId">
                <div class="serial">{{ wage.courseName }}</div>
                <div class="country" style="width: 20%;">{{ wage.wage / 100 }}</div>
                <div class="visit">
                  <b-form-input
                    style="width:180px;"
                    type="number"
                    v-model="wageUpdateAmounts[wage.wageId]"
                  ></b-form-input>
                </div>
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
</template>

<script src="./js/tutorsettings.js"></script>