// How to send FCM notification using rest api
// https://www.youtube.com/watch?v=wqUi6tzN76c&list=PLj76U7gxVixSZIec8QSQCxHONZTZbQheG&index=7
// https://firebase.google.com/docs/cloud-messaging/http-server-ref

send post request to --> https://fcm.googleapis.com/fcm/send
Add
Content-Type:application/json
Authorization:key-serverKey
in Headers

like:
{
        "to":"eGwWfnUORHeRPJnvqSHHWQ:APA91bGg9KnG8q9-G5GrozFjg_It6dkupGbCmWBSH5OpO3sORZuGtecYrJ52ykyYgr9fn_NeHMuUCZ9KUcpG7SI657nPfcYLAAHx70IUHZfEw3C1ID7n4gE51AjMJdlVXUZMhlW07XfN",
        "data":{
            "type":"text",
            "key1":"abcd",
            "key2":"dcba"
        },

        "notification":{
            "title":"FCM notification from postman",
            "body":"working ....",
            "image":"https://cdn.mos.cms.futurecdn.net/TiGR9cagP5nwBtZ6obwFk6-1024-80.jpeg.webp"
        }
}

