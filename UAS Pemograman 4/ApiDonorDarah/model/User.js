const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    fullname : {
        type : String
    },
    username : {
        type : String
    },
    password : {
        type : String
    },
    nik : {
        type : String
    },
    phone : {
        type : String
    },
    blood : {
        type : String
    },
    address : {
        type : String
    },
    age : {
        type : String
    },
    role : {
        type : Number
    }
})

module.exports = mongoose.model('users', userSchema)
