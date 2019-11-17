<template>
    <div id="id" class="action" style="display: flex; flex-direction: column">
		<h3 class="mb-30">Notifications</h3>
          <span v-if="msg" align="center">{{msg}}</span>
      <div
        class="progress-table-wrap"
        style="overflow-x: hidden; margin-left:auto; margin-right:auto"
      >
        <div class="progress-table" style="width: 100%">
          <div class="table-head">
            <div class="country">Notif #</div>
            <div class="country">Requested By</div>
            <div class="country">Date</div>
            <div class="country">TimeSlot</div>
            <div class="country">Course</div>
            <div class="country">Type</div>
          </div>
          <form>
            <div
              class="table-row"
              v-for="notification in notifications"
              v-bind:key="notification.notificationId"
            >
              <div class="country"><router-link class="nav-link" :to= "{name: 'Request'}">{{ notification.notificationId }}</router-link></div>
              <div class="country">{{ notification.request.student.name}}</div>
              <div class="country">{{ notification.request.date}}</div>
              <div class="country">{{ notification.request.time}}</div>
              <div class="country">{{ notification.request.course.courseName}}</div>
              <div class="country">{{ notification.notificationType}}</div>
            </div>
          </form>
        </div>
      </div>
      <span v-if="msg" align="center">{{msg}}</span>
    </div>
</template>
<script src="./js/notification.js"></script>