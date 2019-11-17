import axios from 'axios'
import JQuery from 'jquery'
let $ = JQuery
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl, 'Content-Type': 'application/x-www-form-urlencoded' }
})
export default {
    data() {
        return {
            notifications: [],
            requests: [],
            msg: '',
            userId: this.$cookie.get('userId')
        };

    },
    created: function() {
        this.userId = this.$route.params.id

        AXIOS.get(`/notifications/tutor/` + this.$cookie.get('userId'))
            .then(response => {
                this.notifications = response.data;
                this.msg = 'You have ' +  this.notifications.length + ' notifications';
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.msg = errorMessage
            })
    }
}
