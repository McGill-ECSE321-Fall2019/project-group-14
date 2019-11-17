import axios from 'axios'
import JQuery from 'jquery'

let $ = JQuery

var config = require('../../../config')
var rating = ''
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
var studentId = '';

function setRating(ev) {
    let span = ev.currentTarget;
    let stars = document.querySelectorAll('.star');
    let match = false;
    let num = 0;
    stars.forEach(function (star, index) {
        if (match) {
            star.classList.remove('rated');
        } else {
            star.classList.add('rated');
        }
        //are we currently looking at the span that was clicked
        if (star === span) {
            match = true;
            num = index + 1;
        }
    });
    document.querySelector('.stars').setAttribute('data-rating', num);
}

export default {
    props: ['id', 'name'],
    mounted() {
        this.loadStars()
    },
    data() {
        return {
            name: '',
            reviews: [],
            errorReview: '',
            comment: '',
            studentId: studentId
        }
    },

    methods: {
        // hello: function() {
        //     alert('Hello');
        // }
        loadStars: function() {
                let stars = document.querySelectorAll('.star');
                stars.forEach(function (star) {
                    star.addEventListener('click', setRating);
                });
            
                rating = parseInt(document.querySelector('.stars').getAttribute('data-rating'));
                let target = stars[rating - 1];
                target.dispatchEvent(new MouseEvent('click'));
        },

        createReview: function (comment, tutorId, studentId) {
            console.log(comment + "," +  tutorId + "," +  this.getRating() + "," +  studentId)
            AXIOS.post('/reviews/create', $.param({ rating: rating, comment: comment, from: tutorId, to: studentId }))
                .then(response => {
                    this.reviews.push(response.data)
                    this.errorReview = ''
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorReview = errorMsg
                });
        },

        getRating() {
            let rating = parseInt(document.querySelector('.stars').getAttribute('data-rating'));
            return rating;
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
