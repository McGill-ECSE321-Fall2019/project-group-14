import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ReviewDto(rating, comment, from, to) {
    this.rating = rating
    this.comment = comment
    this.from = from
    this.to = to
}


export default {
    name: 'review',
    data () {
      return {
        tutorReviews: [],
      }
    },
  
    methods: {
      getReviews: function() { 
        AXIOS.get('/reviews/' + userRating + '&' + comment + '&' + from + '&' + to, {}, {})
        .then(response => {
          this.tutorReviews.push(response.data)
        })
        .catch(e => {
          this.errorReview = e;
        });
    },
      getReviewsFromTutor: function(id) {
        XIOS.get('/reviews/tutor/' + id, {}, {})
        .then(response => {
          this.tutorReviews.push(response.data)
        })
        .catch(e => {
          this.errorReview = e;
        });
      }
    }
}