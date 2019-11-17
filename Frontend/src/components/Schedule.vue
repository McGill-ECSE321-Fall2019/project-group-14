<template>
  <div id="schedule">
        <!-- style CSS -->
    <link rel="stylesheet" href="../static/css/style.css" />
    <div class="mb-30" style="padding-top: 130px;">
      <div class="container box_1170" align="left">
        <h1 align="center">Schedule</h1>
        <div
        id = "Example"
        style="margin-top: 30px; margin-left: 20px">
          <h3>Request by Studentname</h3>
          <ul>
            <li>Date: April 20th 2019 at 4:00 pm</li>
            <li>Room: 11</li>
            <li>Course: ECSE321</li>
          </ul>
          <h3>Request by Studentname</h3>
          <ul>
            <li>Date: April 20th 2019 at 4:00 pm</li>
            <li>Room: 11</li>
            <li>Course: ECSE321</li>
          </ul>
        </div>
        <div
        id = "tutorRequests"
        style="margin-top: 40px; margin-left: 20px"
        v-for="request in acceptedRequests"
        v-bind:key="request.id">
          <h3>Request by {{ request.student.name }}</h3>
          <ul>
            <li>Date: {{ request.date }} at {{ request.time }}</li>
            <li>Room: {{ request.room.roomNumber }}</li>
            <li>Course: {{ request.course.courseName }}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./js/schedule.js">
</script>

<style scoped>
h3{
  margin-top: 40px;
}
</style>
