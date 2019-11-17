import axios from 'axios'
import JQuery from 'jquery'
let $ = JQuery
var config = require('../../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = config.dev.backendHost + ':' + config.dev.backendPort
var tutorId = ''

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'schedule',
  props: ['id'],
  mounted() {
    tutorId = this.getCookie('userId')
    this.getAcceptedRequestsFromTutor(tutorId)
    //this.getRequestsFromTutor(tutorId)
    this.getPendingRequestsFromTutor(tutorId)
  },
  data() {
    return {
      requests: [],
      pendingRequests: [],
      acceptedRequests: [],
      errorRequest: '',
      userId: this.$cookie.get('userId')
    }
  },

  methods: {
    getAcceptedRequestsFromTutor: function (id) {
      AXIOS.get('/requests/accepted/' + id)
        .then(response => {
          this.acceptedRequests = response.data
        })
        .catch(e => {
          this.errorRequest = e;
          console.log(e)
        })
        ;
    },

    getRequestsFromTutor: function (id) {
      AXIOS.get('/requests/tutor/' + id)
        .then(response => {
          this.requests = response.data
        })
        .catch(e => {
          this.errorRequest = e;
          console.log(e)
        })
        ;
    },

    getPendingRequestsFromTutor: function(id) {
      this.pendingRequests = [];
      AXIOS.get('/requests/tutor/' + tutorId)
        .then(response => {
          this.requests = response.data
        })
        .catch(e => {
          this.errorRequest = e;
          console.log(e)
        })
        ;
      console.log(this.requests);
      for (var request in this.requests) {
        if (request.room == null) {
          this.pendingRequests.push(request);
        }
      }
    },

    acceptRequest: function(requestId) {
      AXIOS.post('/accept/' + requestId)
        .catch(e => {
          this.errorRequest = e;
          console.log(e)
        })
        ;
      this.getPendingRequestsFromTutor(userId);
      this.getAcceptedRequestsFromTutor(userId);
    },

    rejectRequest: function(requestId) {
      AXIOS.post('/reject/' + requestId)
        .catch(e => {
          this.errorRequest = e;
          console.log(e)
        })
        ;
      this.getPendingRequestsFromTutor(userId);
    },

    getCookie: function (cname) {
      var name = cname + "=";
      var decodedCookie = decodeURIComponent(document.cookie);
      var ca = decodedCookie.split(';');
      for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
          c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
          return c.substring(name.length, c.length);
        }
      }
      return "";
    },


  }
}
