<template>
    <div class="whole-wrap" style="padding-top: 100px;">
    <div class="container box_1170">
      <div class="section-top-border">
		<h3 class="mb-30" style="text-align:center;">Notifications</h3>
          <h5 v-if="msg" align="center" style="padding-bottom: 10px;">{{msg}}</h5>
      <div
        class="progress-table-wrap"
      >
        <div class="progress-table" style="min-width:700px;">
          <div class="table-head">
            <div class="country" style="padding-left:15px; width:160px;">Notif #</div>
            <div class="country">Requested By</div>
            <div class="country" style="padding-left:20px;">Date</div>
            <div class="country">Time</div>
            <div class="country">Course</div>
            <div class="country">Type</div>
          </div>
          <form>
            <div
              class="table-row"
              v-for="notification in notifications"
              v-bind:key="notification.notificationId"
            >
              <div class="country" style="width:170px;"><router-link class="nav-link" to="/schedule">{{ notification.notificationId }}</router-link></div>
              <div class="country">{{ notification.request.student.name}}</div>
              <div class="country" style="padding-left:20px;">{{ notification.request.date}}</div>
              <div class="country">{{ notification.request.time}}</div>
              <div class="country">{{ notification.request.course.courseName}}</div>
              <div class="country">{{ notification.notificationType}}</div>
            </div>
          </form>
        </div>
      </div>
    </div>
    </div>
    </div>
</template>
<script src="./js/notification.js"></script>

<style scoped>
.progress-table .country {
  min-width: 50px;
}
</style>
