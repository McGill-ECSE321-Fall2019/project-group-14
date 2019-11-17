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
          <div class="table-row" style="display: flex; flex-direction: column">
            <div
            v-for="request in acceptedRequests"
            v-bind:key="request.requestId"
            style="display: flex; margin-top: 18px">
              <div class="serial">{{request.date}} at {{request.time}}</div>
              <div class="serial">Room #{{request.room.roomNumber}}</div>
              <div class="serial">{{request.student.name}}</div>
              <div class="serial">{{request.course.courseName}}</div>
            </div>
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
            v-for="request in pendingRequests"
            v-bind:key="request.requestId"
            style="display: flex; margin-top: 18px">
              <div class="serial">{{request.date}} at {{request.time}}</div>
              <div class="serial">TBA</div>
              <div class="serial">{{request.student.name}}</div>
              <div class="serial">{{request.course.courseName}}</div>
              <div v-show="pendingRequests.length != 0" id="acceptButton" class="country" style="width: 0px">
                <button @click="acceptRequest(request.requestId)" class="btn_4">Accept</button>
              </div>
              <div v-show="pendingRequests.length != 0" id="RejectButton" class="country" style="width: 0px; margin-left: 110px">
                <button @click="rejectRequest(request.requestId)" class="btn_4">Reject</button>
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
