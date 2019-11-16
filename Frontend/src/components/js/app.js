import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'app',
    computed: {
        loggedIn() {
            if ((localStorage.getItem('loggedIn') != null)) {
                if (this.$cookie.get("email") == null || this.$cookie.get("password") == null) {
                    localStorage.removeItem('loggedIn')
                    this.$cookie.delete('email');
                    this.$cookie.delete('password');
                    window.location.href ="/";
                }
                else {
                    AXIOS.post('/login/' + this.$cookie.get("email") + '/' + this.$cookie.get("password"), {}, {})
                    .then(response => {
                        if (response.data == "Tutor") {
                            if (localStorage.getItem('loggedIn') != "Tutor") {
                                localStorage.setItem('loggedIn', "Tutor");
                                console.log("Not a tutor");
                                window.location.href = "/";
                            }
                        }
                        else {
                            localStorage.removeItem('loggedIn')
                            this.$cookie.delete('email');
                            this.$cookie.delete('password');
                            console.log("Incorrect log in information");
                            window.location.href = "/";
                        }
                    })
                    .catch (e => {
                        localStorage.removeItem('loggedIn')
                        this.$cookie.delete('email');
                        this.$cookie.delete('password');
                        console.log("error: " + e);
                        window.location.href = "/";
                    });
                    console.log(localStorage.getItem('loggedIn'))
                    return (localSotrage.getItem('loggedIn') == "Tutor")
                }
            }
            return false
        },
        isTutor() {
                console.log(localStorage.getItem('loggedIn'))
                return (localStorage.getItem('loggedIn') == "Tutor")
        }
    }
}
