const mongoose = require('mongoose');
const objectId = mongoose.Schema.ObjectId

const userSchema = mongoose.Schema({
    id : {
      type : objectId
    },
    fullnameBuyer : {
        type : String
    },
    type : {
        type : String
    },
    doFor : {
        type : String
    },
    stok : {
        type : String
    },
    qtyBuyer : {
        type : String
    },
    price : {
        type : String
    },
    sum : {
        type : String
    }
})

module.exports = mongoose.model('buyers', userSchema)
