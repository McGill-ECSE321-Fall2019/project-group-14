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
            tutorName: '',
            password: '',
            errorMsg: '',
            response: '',
            wages: [],
            wageUpdateAmounts: {},
            newDate: '',
            newTime: '',
            timeslots: []
        }
    },
    mounted() {
        AXIOS.get('/tutors/' + this.$cookie.get('userId'))
        .then(response => {
            this.response = response.data
            this.errorMsg =''
            if (this.response != '') {
                this.tutorName = this.response['name']
                this.password = this.response['password']
            }
            else {
                console.log('Errored')
            }
        })
        .catch(e => {
            console.log(e)
        })

        AXIOS.get('/wages/tutor/' + this.$cookie.get('userId'))
        .then(response => {
            this.response = response.data
            this.errorMsg =''
            if (this.response != '') {
                this.wages = this.response
            }
            else {
                console.log('Errored')
            }
        })
        .catch(e => {
            console.log(e)
        })

        AXIOS.get('/timeslots/tutor/' + this.$cookie.get('userId'))
        .then(response => {
            this.response = response.data
            this.errorMsg =''
            if (this.response != '') {
                this.timeslots = this.response
                this.timeslots.sort((a, b) => ((a.date + a.time) > (b.date + b.time)) ? 1 : -1)
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
        updatetutor (tutorName, password) {
            console.log('updating with ' + tutorName + password)
            if (tutorName == '') {
                var errorMessage = "Name cannot be empty"
                console.log(errorMessage)
                this.errorMsg = errorMessage
                return
            }
            if (password == '') {
                var errorMessage = "Password cannot be empty"
                console.log(errorMessage)
                this.errorMsg = errorMessage
                return
            }
            AXIOS.put('/tutors/update/' + this.$cookie.get('userId'), $.param({name: tutorName, password: password}))
            .then(response => {
                this.response = response.data
                this.errorMsg =''
                if (this.response == true) {
                    this.$cookie.set('name', this.tutorName, { expires: '1h'})
                    window.location.reload()
                }
                else {
                    this.errorMsg = 'Error updating info!'
                    console.log(this.errorMsg)
                }
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.errorMsg = errorMessage
            })
        },
        updateWage (updateWageId) {
            AXIOS.put('/wages/update/' + updateWageId, $.param({wage: this.wageUpdateAmounts[updateWageId] * 100}))
            .then(response => {
                this.response = response.data
                this.errorMsg =''
                if (this.response == true) {
                    window.location.reload()
                }
                else {
                    this.errorMsg = 'Error updating info!'
                    console.log(this.errorMsg)
                }
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.errorMsg = errorMessage
            })
        },
        addTimeslot (newDate, newTime) {
            AXIOS.post('/timeslots/create', $.param({id: this.$cookie.get('userId'), date: newDate, time: newTime}))
            .then(response => {
                this.response = response.data
                this.errorMsg =''
                if (this.response != '') {
                    window.location.reload()
                }
                else {
                    this.errorMsg = 'Error creating Timeslot!'
                    console.log(this.errorMsg)
                }
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.errorMsg = errorMessage
            })
        },
        deleteTimeslot (deleteTimeslotId) {
            AXIOS.delete('/timeslots/' + deleteTimeslotId)
            .then(response => {
                this.response = response.data
                this.errorMsg =''
                if (this.response == true) {
                    window.location.reload()
                }
                else {
                    this.errorMsg = 'Error updating info!'
                    console.log(this.errorMsg)
                }
            })
            .catch(e => {
                var errorMessage = e.response
                console.log(e)
                this.errorMsg = errorMessage
            })
        }
    }
}
