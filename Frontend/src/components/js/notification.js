import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://dashboard.heroku.com/apps/tutoringsystem-backend-14'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
export default {
    name: 'notification',
    data () {
      return {
          requestId: null,
          tutor: '',
          tutorId = null,
          notificationId: null,
          notificationType: ''
      };
    },
    created: function() {
        this.tutor = this.$route.params.tutor

        AXIOS.get('/notifications/tutor/' + id)
        .then(response => {
            this.tutorId = repsonse.data;
        })
        .then(() => {
            this.tutor = this.t
        })
        
    }
}