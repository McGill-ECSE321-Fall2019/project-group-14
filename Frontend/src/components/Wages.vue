<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
        <h3 class="mb-30">Browse wages by course</h3>
        <b-form-select v-model="institution" @change="updateCourses()" style="width:200px;">
          <option disabled :value="null">Select Institution</option>
          <option
            v-for="ins in institutions"
            v-bind:key="ins.institutionName"
          >{{ ins.institutionName }}</option>
        </b-form-select>
        <b-form-select v-model="course" style="width:200px;">
          <option disabled :value="null">Select Course</option>
          <option v-for="crs in courses" v-bind:key="crs.courseName">{{ crs.courseName }}</option>
        </b-form-select>
        <span style="padding-left: 25px;"></span>
        <input
          @click="viewWage()"
          type="submit"
          value="View wages"
          class="genric-btn primary radius"
        >
      </div>

      <div class="section-top-border">
        <div
          class="progress-table-wrap"
          style="width: 340px; overflow-x: hidden; margin-left:auto; margin-right:auto"
        >
          <div class="progress-table">
            <div class="table-head">
              <div class="serial" style="width: 45%;">Course</div>
              <div class="country">Wage</div>
            </div>
            <form>
              <div class="table-row" v-for="wage in wages" v-bind:key="wage.wageId">
                <div class="serial" style="width: 45%;">{{ wage.courseName }}</div>
                <div class="country">{{ wage.wage / 100 }}</div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./js/wages.js"></script>