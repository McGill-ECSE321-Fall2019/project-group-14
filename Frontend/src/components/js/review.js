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
  name: 'review',
  props: ['id', 'name'],
  mounted() {
    tutorId = this.getCookie('userId')
    this.getReviewsFromTutor(tutorId)
    this.getRequestsFromTutor(tutorId)

  },
  data() {
    return {
      tutorReviews: [],
      acceptedRequests: [],
      response: [],
      cookieName: '',
      errorReview: '',
      studentId: ''
    }
  },
  methods: {
    hello: function () {
      // alert(tutorId); -> this is a test function
    },
    getReviewsFromTutor: function (id) {
      AXIOS.get('/reviews/tutor/' + id)
        .then(response => {
          this.tutorReviews = response.data
        })
        .catch(e => {
          this.errorReview = e;
          console.log(e)
        })
        ;
    },
    getRequestsFromTutor: function (id) {
      AXIOS.get('/requests/accepted/' + id)
        .then(response => {
          this.acceptedRequests = response.data
        })
        .catch(e => {
          this.errorReview = e;
          console.log(e)
        })
        ;
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