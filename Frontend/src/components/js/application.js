import axios from 'axios'
import JQuery from 'jquery'

let $ = JQuery

var config = require('../../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = config.dev.backendHost + ':' + config.dev.backendPort
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'apply',
    data() {
        return {
            name: '',
            email: '',
            courses: '',
            applications: [],
            errorApplication: '',
            checked: false
        }
    },
    methods: {
        createApplication: function (checked, name, email, courses) {
            AXIOS.post('/applications/create/', $.param({ existing: checked, name: name, email: email, courses: courses }))
                .then(response => {
                    this.applications.push(response.data)
                    this.errorApplication = ''
                    this.$router.push({name:'Hello'})
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorApplication = errorMsg
                });
        }
    }
}