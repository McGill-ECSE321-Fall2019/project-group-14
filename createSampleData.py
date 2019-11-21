# importing the library
import requests
import random

# api-endpoint
URL = "http://tutoringsystem-backend-14.herokuapp.com"

r = requests.post(url=URL + "/flushdb")
if r.json() != True:
    print("Eror in flushing DB")

# Create the manager
userData = {'name': "Manager Manageroo",
            'email': "ecse321test+manager@protonmail.com", 'password': "manager"}
r = requests.post(url=URL + "/managers/create", data=userData)
print(r.json())

# Create Institutions and Courses
institutions = ["McGill University", "Concordia University"]
courses0 = ["ECSE 200", "ECSE 205", "ECSE 206", "ECSE 210", "ECSE 211", "ECSE 221", "ECSE 222",
            "ECSE 223", "ECSE 291", "ECSE 251", "ECSE 305", "ECSE 306", "ECSE 307", "ECSE 308",
            "ECSE 309", "ECSE 310"]
courses1 = ["COEN212", "COEN231", "COEN243", "COEN244", "COEN311", "COEN313", "COEN316", "COEN317",
            "COEN320", "COEN352", "COEN390", "COEN424", "COEN434", "COEN445", "COEN451", "COEN490"]
courses = [courses0, courses1]
subjects = ["Mathematics", "Engineering"]
for i in range(len(institutions)):
    institutionName = institutions[i]
    institutionLevel = "University"

    institutionData = {'name': institutionName, 'level': institutionLevel}

    r = requests.post(url=URL + "/institutions/create", data=institutionData)
    print(r.json())

    for j in range(len(courses[i])):
        courseName = courses[i][j]
        courseSubject = subjects[random.randint(0, 1)]

        courseData = {"name": courseName,
                      "institution": institutionName, "subject": courseSubject}

        r = requests.post(url=URL + "/courses/create", data=courseData)
        print(r.json())

# Create some Students
students = ["Vikki Fonte", "Hallie Spinks", "Evelyne Briski", "Marina Bazemore", "Myrta Ham",
            "Monique Prince", "Boyce Galeana", "Britta Rorick", "Christie Federico", "Christena Crozier",
            "Merrilee Faulkenberry", "Marica Reavis", "Melinda Houpt", "Arnulfo Macauley",
            "Silva Simonds", "Ali Maloney", "Anglea Goudy", "Julianne Vang", "Junita Gile", "Tamala Galeano"]
studentIdMin = 999999
for i in range(len(students)):
    name = students[i]
    email = "ecse321test+student" + str(i) + "@protonmail.com"
    password = students[i].lower().replace(" ", "")

    userData = {'name': name, 'email': email, 'password': password}

    r = requests.post(url=URL + "/students/create", data=userData)
    if studentIdMin > r.json().get('userId'):
        studentIdMin = r.json().get('userId')
    print(r.json())
studentIdMax = r.json().get('userId')

# Create some (small) Rooms
for i in range(5101, 5126):
    roomData = {'id': i, 'capacity': 2}

    r = requests.post(url=URL + "/rooms/create", data=roomData)
    print(r.json())

# Create some Tutors
tutors = ["Aliza Shoaf", "Rueben Carnley", "Olinda Lail", "Vinita Helbert", "Ranae Naval",
          "Cheryll Tullier", "Jo Foucher", "Giselle Beede", "Eleonor Gillette", "Donnie Donegan"]
for i in range(len(tutors)):
    name = tutors[i]
    email = "ecse321test+tutor" + str(i) + "@protonmail.com"
    password = "pass"

    userData = {'name': name, 'email': email, 'password': password}

    r = requests.post(url=URL + "/tutors/create", data=userData)
    tutorId = r.json().get('userId')
    print(r.json())

    # Create some Wages
    for j in range(random.randint(2, 6)):
        institutionIndex = random.randint(0, 1)
        if institutionIndex == 0:
            course = courses0[random.randint(0, len(courses0) - 1)]
        else:
            course = courses1[random.randint(0, len(courses1) - 1)]
        wage = random.randint(1000, 4000)

        wageData = {'tutorId': tutorId, 'course': course, 'wage': wage}

        r = requests.post(url=URL + "/wages/create", data=wageData)
        print(r.json())

    # Create some Timeslots
    timeslotList = []
    for k in range(random.randint(6, 12)):
        digit = random.randint(9, 21)
        if digit not in timeslotList:
            timeslotList.append(digit)
    for l in range(len(timeslotList)):
        if timeslotList[l] == 9:
            timeslotTime = "09:00:00"
        else:
            timeslotTime = str(timeslotList[l]) + ":00:00"
        timeslotDate = "2019-11-" + str(random.randint(14, 15))

        timeslotData = {'id': tutorId,
                        'date': timeslotDate, 'time': timeslotTime}
        r = requests.post(url=URL + "/timeslots/create", data=timeslotData)
        print(r.json())

    # Create one Request for each Tutor
    studentId = random.randint(studentIdMin, studentIdMax)
    r = requests.get(url=URL + "/tutors/" + str(tutorId))
    timeslotReturn = r.json().get('timeSlots')
    date = timeslotReturn[0]['date']
    time = timeslotReturn[0]['time']
    wageReturn = r.json().get('wages')
    courseName = wageReturn[0]['courseName']
    requestData = {'time': time, 'date': date, 'tutorId': tutorId,
                   'studentId': studentId, 'courseName': courseName}

    r = requests.post(url=URL + "/requests/create", data=requestData)
    print(r.json())

    # Create one Accepted Request for each Tutor
    studentId = random.randint(studentIdMin, studentIdMax)
    date = timeslotReturn[1]['date']
    time = timeslotReturn[1]['time']
    courseName = wageReturn[1]['courseName']
    requestData = {'time': time, 'date': date, 'tutorId': tutorId,
                   'studentId': studentId, 'courseName': courseName}

    r = requests.post(url=URL + "/requests/create", data=requestData)
    print(r.json())

    requestId = r.json().get('requestId')
    r = requests.post(url=URL + "/accept/" + str(requestId))
    print(r.json())

    # Create one Review for each Tutor
    rating = random.randint(1, 5)
    comments = ["When your only tool is a hammer, all problems start looking like nails.",
                "99 percent of lawyers give the rest a bad name.",
                "Artificial intelligence is no match for natural stupidity.",
                "The last thing I want to do is insult you. But it IS on the list.",
                "I don't have a solution, but I do admire the problem.",
                "The only substitute for good manners is fast reflexes.",
                "Support bacteria - they're the only culture some people have.",
                "Letting the cat out of the bag is a whole lot easier than putting it back in.",
                "Well, here I am! What are your other two wishes?"]
    reviewData = {'rating': rating, 'comment': comments[random.randint(
        0, len(comments) - 1)], 'from': studentId, 'to': tutorId}

    r = requests.post(url=URL + "/reviews/create", data=reviewData)
    print(r.json())
