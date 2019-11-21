<template>
  <div class="action">
    <div class="col-lg-8 col-md-8" id="component">
      <div class="mb-30" id="list">
        <h3 class="mb-20">Sessions</h3>
        <h6 class="mb-20">You have 24 hours to review a session with a student after it ended.</h6>

        <div class="progress-table-wrap">
          <div class="progress-table">
            <div class="table-head">
              <div class="serial" id="rating">When</div>
              <div class="serial" id="rating">Where</div>
              <div class="country" id="rating">Student</div>
              <div class="country" id="rating">Leave a review</div>
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
            <div
              id="rating"
              class="country"
              style="min-width: 250px; width: auto;"
            >{{review.comment}}</div>
          </div>
        </div>
      </div>
      <p style="padding-bottom:100px;"></p>
    </div>
  </div>
</template>

<script src="./js/review.js">
</script>

<style scoped>
h3 {
  margin-top: 200px;
  text-align: left;
}
h6 {
  display: inline-block;
  text-align: left;
  margin-bottom: 40px;
}
#component {
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 10px;
}

#reviews {
  margin-top: 100px;
}
ul {
  margin-top: 50px;
  display: inline-block;
  text-align: left;
}

.btn_1 {
  margin-top: 50px;
  margin-bottom: 50px;
}

#rating {
  margin-top: 20px;
  size: 200px;
  vertical-align: middle;
}
</style>
