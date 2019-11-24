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
              <div class="serial">

  <b-button variant="link" style="padding: 0px 0px;" v-b-modal.modal-center>{{request.student.name}}</b-button>

                <b-modal id="modal-center" centered ok-only>
                  <template v-slot:modal-title>
                    {{ request.student.name }}
                  </template>

                  <div class="author_info">
                                <div class="author_img">
                                    <img src="../../static/img/author_3.png" style="width:70px; float:left;">
                                    <span style="padding-left:90px; vertical-align:top; display:block;"><h6>This student has taken:</h6>ECSE 200, ECSE 210, ECSE 211, ECSE 321</span>
                                </div>
                                <div class="author_rating">
                                    <div class="rating">
                                      <br>
                                        <img src="../../static/img/icon/color_star.svg" alt="">
                                        <img src="../../static/img/icon/color_star.svg" alt="">
                                        <img src="../../static/img/icon/color_star.svg" alt="">
                                        <img src="../../static/img/icon/color_star.svg" alt="">
                                        <img src="../../static/img/icon/star.svg" alt="">
                                    </div>
                                    <p style="margin-bottom:0px;">4.2 Rating</p>
                                </div>
                            </div>
                </b-modal>
                                    


              </div>
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

<style>
.modal-open{overflow:auto;padding-right:0 !important;}
</style>

