<template>
  <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
        <h3 class="mb-30">Sessions</h3>
        <h6 class="mb-30">You have 24 hours to review a session with a student after it ended.</h6>

        <div class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial" id="rating">When</div>
              <div class="serial" id="rating">Where</div>
              <div class="country" id="rating">Student</div>
              <div class="country" id="rating"></div>
            </div>
            <div class="table-row" v-for="request in acceptedRequests" v-bind:key="request.id">
              <div class="serial" id="rating">{{request.date}} at {{request.time}}</div>
              <div class="serial" id="rating">Room #{{request.room.roomNumber}}</div>
              <div class="country" id="rating">{{request.student.name}}</div>
              <div class="serial" id="rating" style="padding-left:0px;">
                <router-link
                  :to="{name: 'create-review', params: { id: request.student.userId, name: request.student.name}}"
                  class="genric-btn primary circle"
                >Leave review</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section-top-border">
        <h3 class="mb-30" id="reviews">Your reviews</h3>
        <div id="reviewsTable" class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial" id="rating">From</div>
              <div class="serial" id="rating">Rating</div>
              <div class="country" id="rating">Comment</div>
            </div>
            <div class="table-row" v-for="review in tutorReviews" v-bind:key="review.id">
              <div id="rating" class="serial">{{review.from.name}}</div>
              <div id="rating" class="serial">
                {{review.rating}}
                <img
                  id="stars"
                  src="https://cdn3.iconfinder.com/data/icons/flat-actions-icons-9/792/Star_Gold-512.png"
                  height="40"
                  width="30"
                  style="margin-top: -4px; padding-left:2px;"
                >
              </div>
              <div id="rating" class="country" style="width:auto; padding-right:20px;">{{review.comment}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="./js/review.js">
</script>
