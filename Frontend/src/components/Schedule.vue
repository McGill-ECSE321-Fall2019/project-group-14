<template>

  <div class="col-lg-8 col-md-8" id="schedule">
    <!-- style CSS -->
    <link rel="stylesheet" href="../static/css/style.css" />
    <div class="mb-30" id="list">
      <h3 class="mb-20">Schedule</h3>

      <div class="progress-table-wrap" style="margin-top: 30px">
        <div class="progress-table">
          <div class="table-head">
            <div class="serial">When</div>
            <div class="serial">Where</div>
            <div class="serial">Student</div>
            <div class="serial">Course</div>
          </div>
          <div class="table-row">
            <div
                v-for="request in acceptedRequests"
                v-bind:key="request.id"
                id="when"
                class="serial"
              >{{request.date}} at {{request.time}}</div>
              <div
                v-for="request in acceptedRequests"
                v-bind:key="request.id"
                id="where"
                class="serial"
              >Room #{{request.room.roomNumber}}</div>
              <div
                v-for="request in acceptedRequests"
                v-bind:key="request.id"
                id="student"
                class="serial"
              >{{request.student.name}}</div>
              <div
                v-for="request in acceptedRequests"
                v-bind:key="request.id"
                id="course"
                class="serial"
              >{{request.course.courseName}}</div>

            <!-- <div id="when" class="serial">November 22th at 12:00pm</div>
            <div id="where" class="serial">Room #11</div>
            <div id="student" class="serial">Lebron</div>
            <div id="course" class="serial">BALL 321</div>
            <div id="acceptButton" class="country" style="width: 0px">
              <button v-on:click="acceptRequest()" class="btn_4">Accept</button>
            </div>
            <div id="RejectButton" class="country" style="width: 0px; margin-left: 110px">
              <button v-on:click="rejectRequest()" class="btn_4">Reject</button>
            </div> -->
          </div>
        </div>
      </div>
    </div>

    <div class="mb-30" id="list">
      <h3 class="mb-20" style="margin-top: 50px">Pending Request</h3>

      <div class="progress-table-wrap" style="margin-top: 30px; display: flex; flex-direction: column">
        <div class="progress-table">
          <div class="table-head">
            <div class="serial">When</div>
            <div class="serial">Where</div>
            <div class="serial">Student</div>
            <div class="serial">Course</div>
          </div>
          <div class="table-row" style="display: flex; flex-direction: column">
            <div
            v-for="request in requests"
            v-bind:key="request.requestId"
            style="display: flex; margin-top: 18px">
              <div class="serial">{{request.date}} at {{request.time}}</div>
              <div class="serial">{{request.tutor.name}}</div>
              <div class="serial">{{request.student.name}}</div>
              <div class="serial">{{request.course.courseName}}</div>
              <div v-show="pendingRequests.length == 0" id="acceptButton" class="country" style="width: 0px">
                <button v-on:click="acceptRequest(request.requestId)" class="btn_4">Accept</button>
              </div>
              <div v-show="pendingRequests.length != 0" id="RejectButton" class="country" style="width: 0px; margin-left: 110px">
                <button v-on:click="rejectRequest(request.requestId)" class="btn_4">Reject</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./js/schedule.js">
</script>

<style scoped>

  h3 {
    margin-top: 130px;
  }

  #schedule {
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 10px;
  }

</style>
