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
    name: 'login',
    data() {
        return {
            errorLogin: '',
            response: ''
        }
    },
    methods: {
        login (email, password) {
            if (email == '') {
                var errorMessage = "Email cannot be empty"
                console.log(errorMessage)
                this.errorLogin = errorMessage
                return
            }
            if (password == '') {
                var errorMessage = "Password cannot be empty"
                console.log(errorMessage)
                this.errorLogin = errorMessage
                return
            }
            AXIOS.post('/login/', $.param({email: email, password: password}))
            .then(response => {
                this.response = response.data
                this.errorLogin =''
                if (this.response != '') {
                    localStorage.setItem('loggedIn', 'Tutor')
                    this.$cookie.set('name', this.response['name'], { expires: '1h'})
                    this.$cookie.set('userId', this.response['userId'], { expires: '1h'})
                    window.location.href = "/"
                }
                else {
                    this.errorLogin = 'Wrong email or password!'
                    console.log(this.errorlogin)
                }
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.errorLogin = errorMessage
            })
        }
    }
}