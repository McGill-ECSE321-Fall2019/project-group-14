<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
        <h3 class="mb-30">Browse wages by course</h3>
        <span>Institution:</span>
        <select v-model="institution" onchange="updateCourses()">
          <option disabled selected>Select Institution</option>
          <option v-for="ins in institutions" v-bind:key="ins.institutionName">{{ ins.institutionName }}</option>
        </select>
        <span style="padding-left: 25px;">Course:</span>
        <select v-model="course">
          <option selected disabled>Select Course</option>
          <option>A</option>
          <option>B</option>
          <option>C</option>
        </select>
        <span style="padding-left: 25px;"></span>
        <input
          @click="doNothing()"
          type="submit"
          value="View wages"
          class="genric-btn primary circle"
        >
      </div>
    </div>
  </div>
</template>

<script src="./js/wages.js"></script>