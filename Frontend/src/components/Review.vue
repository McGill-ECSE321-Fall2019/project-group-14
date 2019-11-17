<template>
  <div class="col-lg-8 col-md-8" id="component">
    <div class="mb-30" id="list">
      <h3 class="mb-20">Sessions</h3>
      <h6 class="mb-20">You have 24 hours to review a session with a student after it ended.</h6>

      <!--       <div class>
        <ul class="unordered-list">
          <li>Fta Keys</li>
          <li>For Women Only Your Computer Usage</li>
          <li>Dealing With Technical Support 10 Useful Tips</li>
          <li>Make Myspace Your Best Designed Space</li>
          <li>Cleaning And Organizing Your Computer</li>
        </ul>
      </div>-->

      <div class="progress-table-wrap">
        <div class="progress-table">
          <div class="table-head">
            <div class="serial">When</div>
            <div class="serial">Where</div>
            <div class="country">Student</div>
            <div class="country">Leave a review</div>
          </div>
          <div class="table-row">
            <div
              v-for="request in acceptedRequests"
              v-bind:key="request.id"
              id="rating"
              class="serial"
            >{{request.date}} at {{request.time}}</div>
            <div
              v-for="request in acceptedRequests"
              v-bind:key="request.id"
              id="rating"
              class="serial"
            >Room #{{request.room.roomNumber}}</div>
            <div
              v-for="request in acceptedRequests"
              v-bind:key="request.id"
              id="rating"
              class="country"
            >{{request.student.name}}</div>
            <div id="rating" class="country">
              <router-link :to="{name: 'create-review'}" class="btn_2">Leave review</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
    <h3 class="mb-30" id="reviews">Your reviews</h3>
    <div class="progress-table-wrap">
      <div class="progress-table">
        <div class="table-head">
          <div class="serial">From</div>
          <div class="serial">Rating</div>
          <div class="country">Comment</div>
        </div>
        <div class="table-row">
          <div
            v-for="review in tutorReviews"
            v-bind:key="review.id"
            id="rating"
            class="serial"
          >{{review.from.name}}</div>
          <div
            v-for="review in tutorReviews"
            v-bind:key="review.id"
            id="rating"
            class="serial"
          >{{review.rating}}</div>
          <div
            v-for="review in tutorReviews"
            v-bind:key="review.id"
            id="rating"
            class="country"
          >{{review.comment}}</div>
        </div>
      </div>
    </div>
    <!--     <ul class="unordered-list" id="example-1">
      <li
        v-for="review in tutorReviews"
        v-bind:key="review.id"
        id="rating"
      >From {{review.from.name}}: {{ review.rating }} stars: {{ review.comment }}</li>
    </ul>-->
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
  display: inline-block;
  size: 200px;
  text-align: center;
  vertical-align: middle;
}
</style>
