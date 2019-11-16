import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

document.addEventListener('DOMContentLoaded', function () {
    let stars = document.querySelectorAll('.star');
    stars.forEach(function (star) {
        star.addEventListener('click', setRating);
    });

    let rating = parseInt(document.querySelector('.stars').getAttribute('data-rating'));
    let target = stars[rating - 1];
    target.dispatchEvent(new MouseEvent('click'));
});

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
    data() {
        return {
            name: '',
            reviews: [],
            errorReview: ''
        }
    },

    methods: {
        // hello: function() {
        //     alert('Hello');
        // }
        createReview: function (userRating, comment, from, to) {
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
        },

    },
}
