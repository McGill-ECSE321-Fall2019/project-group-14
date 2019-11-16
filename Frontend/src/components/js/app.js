import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'app',
    computed: {
        loggedIn() {
            if (this.$cookie.get('userId') != null) {
                return true
            }
            return false
        }
    },
    methods: {
        logout () {
            this.$cookie.delete('name')
            this.$cookie.delete('userId')
            window.location.href = "/"
        }
    }
}
