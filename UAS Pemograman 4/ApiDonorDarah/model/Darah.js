const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    name : {
        type : String
    },
    qty : {
        type : Number
    },
    harga : {
        type : String
    }
})

module.exports = mongoose.model('darahs', userSchema)
