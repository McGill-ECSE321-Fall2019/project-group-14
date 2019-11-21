<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
        <h3 class="mb-30">Schedule</h3>

        <div class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial">When</div>
              <div class="serial">Where</div>
              <div class="serial">Student</div>
              <div class="serial">Course</div>
            </div>
            <div
              class="table-row"
              v-for="request in acceptedRequests"
              v-bind:key="request.requestId"
            >
              <div class="serial">{{request.date}} at {{request.time}}</div>
              <div class="serial">Room #{{request.room.roomNumber}}</div>
              <div class="serial">{{request.student.name}}</div>
              <div class="serial">{{request.course.courseName}}</div>
            </div>
          </div>
        </div>
      </div>

      <div class="section-top-border">
        <h3 class="mb-30">Pending Request</h3>

        <div
          class="progress-table-wrap"
          style="margin-top: 30px; display: flex; flex-direction: column"
        >
          <div class="progress-table">
            <div class="table-head">
              <div class="serial">When</div>
              <div class="serial">Student</div>
              <div class="serial">Course</div>
              <div class="serial" style="width:130px; min-width:130px;"></div>
              <div class="serial" style="width:130px; min-width:130px; margin-right:30px;"></div>
            </div>
            <div
              class="table-row"
              v-for="request in pendingRequests"
              v-bind:key="request.requestId"
            >
              <div class="serial">{{request.date}} at {{request.time}}</div>
              <div class="serial">{{request.student.name}}</div>
              <div class="serial">{{request.course.courseName}}</div>
              <div
                v-show="pendingRequests.length != 0"
                id="acceptButton"
                class="country"
                style="width:130px; min-width:130px;"
              >
                <button
                  @click="acceptRequest(request.requestId)"
                  class="genric-btn primary circle"
                >Accept</button>
              </div>
              <div
                v-show="pendingRequests.length != 0"
                id="RejectButton"
                class="country"
                style="width:130px; min-width:130px; margin-right:30px;"
              >
                <button
                  @click="rejectRequest(request.requestId)"
                  class="genric-btn primary circle"
                >Reject</button>
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