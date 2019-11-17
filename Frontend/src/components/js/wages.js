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
    name: 'tutorsettings',
    data() {
        return {
            errorMsg: '',
            response: '',
            institution: '',
            course: '',
            institutions: []
        }
    },
    mounted() {
        AXIOS.get('/institutions')
        .then(response => {
            this.response = response.data
            this.errorMsg =''
            if (this.response != '') {
                this.institutions = this.response
            }
            else {
                console.log('Errored')
            }
        })
        .catch(e => {
            console.log(e)
        })
    },
    methods: {
        doNothing () {

        }
    }
}