import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://dashboard.heroku.com/apps/tutoringsystem-backend-14'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'login',
    data() {
        return {
            email: this.$cookie.get("email")|| '',
            password: this.$cookie.get("password") || '',
            errorLogin: '',
            response: ''
        }
    },
    methods: {
        login (email, password) {
            if (email == '') {
                var errorMessage = "Invalid Email or Password"
                console.log(errorMessage)
                this.errorLogin = errorMessage
                return
            }
            if (password == '') {
                var errorMessage = "Invalid Email or Password"
                console.log(errorMessage)
                this.errorLogin = errorMessage
                return
            }
            Axios.post('/login/' + email + '/' + password, {}, {})
            .then(response => {
                this.response = response.data
                this.errorLogin =''
                this.$cookie.set("email", email, { expires: '1h'})
                this.$cookie.set("password", password, { expires: '1h'})
                this.email = this.$cookie.get("email") || ''
                this.password = this.$cookie.get("password") || ''
                if (this.response == 'Tutor') {
                    localStorage.setItem('loggedIn', 'Tutor')
                    window.location.href ="/";
                }
                else {
                    this.errorLogin = response.data
                    console.log(this.response)
                }
            })
            .catch(e => {
                var errorMessage = e.message
                console.log(errorMessage)
                this.errorLogin = errorMessage
            })
        }
    }
}