import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

// $(document).ready(function(){
//     // Check Radio-box
//     $(".rating input:radio").attr("checked", false);
//
//     $('.rating input').click(function () {
//         $(".rating span").removeClass('checked');
//         $(this).parent().addClass('checked');
//     });
//
//     $('input:radio').change(
//       function(){
//         var userRating = this.value;
//         alert(userRating);
//     });
// });

export default {
    data() {
        return {
            name : '',
            reviews: [],
            errorReview: ''
        }
    },

    methods: {
        // hello: function() {
        //     alert('Hello');
        // }
        createReview: function(userRating, comment, from, to) {
            AXIOS.post('/reviews/create' + userRating + '&' + comment + '&' + from + '&' + to, {}, {})
            .then(response => {
              this.reviews.push(response.data)
              this.errorReview = ''
            })
            .catch(e => {
              var errorMsg = e.message
              console.log(errorMsg)
              this.errorReview = errorMsg
            });
        }
    }
}
