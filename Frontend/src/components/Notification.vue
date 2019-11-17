<template>
    <div id="id" class="action" style="display: flex; flex-direction: column">
		<h3 >Notifications</h3>
          <span v-if="msg" align="center">{{msg}}</span>
      <div
        class="progress-table-wrap"
        style="overflow-x: hidden; margin-left:auto; margin-right:auto"
      >
        <div class="progress-table" style="width: 100%">
          <div class="table-head">
            <div class="serial">Notif #</div>
            <div class="country">Requested By</div>
            <div class="country">Date</div>
            <div class="country">TimeSlot</div>
            <div class="country">Room #</div>
            <div class="country">Type</div>
          </div>
          <form>
            <div
              class="table-row"
              v-for="notification in notifications"
              v-bind:key="notification.notificationId"
            >
              <div class="serial">{{ notification.notificationId }}</div>
              <div class="country">{{ notification.request.student.name}}</div>
              <div class="country">{{ notification.request.date}}</div>
              <div class="country">{{ notification.request.time}}</div>
              <div class="country">{{ notification.request.room}}</div>
              <div class="country">{{ notification.notificationType}}</div>
            </div>
          </form>
        </div>
      </div>
      <span v-if="msg" align="center">{{msg}}</span>
    </div>
</template>
<script src="./js/notification.js"></script>