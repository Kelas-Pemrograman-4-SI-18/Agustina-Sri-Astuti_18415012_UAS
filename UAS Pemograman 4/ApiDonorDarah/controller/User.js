const userModel = require('../model/User.js')
const response = require('../config/response.js')
const bcrypt = require('bcrypt')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId

exports.registrasi = (data) =>
    new Promise((resolve, reject) => {
        userModel.findOne({username : data.username})
            .then(user => {
                if (user){
                    resolve(response.commonErrorMsg('Opps... username sudah digunakan, silahkan gunakan username lain.'))
                } else {
                    bcrypt.hash(data.password, 10, (err, hash) => {
                        if (err){
                            reject(response.commonErrorMsg())
                        } else {
                            data.password = hash

                            userModel.create(data)
                                .then(() => resolve(response.commonSuccessMsg('Yeah... selamat anda berhasil teregistrasi.')))
                                .catch((err) =>{
                                    console.log(err)
                                    reject(response.commonErrorMsg('Opps... anda gagal teregistrasi'))
                                })
                        }
                    })
                }
            }).catch(() => reject(response.commonError))
    })

exports.updateData = (id, data) =>
    new Promise(async (resolve, reject) => {
        bcrypt.hash(data.password, 10, (err, hash) => {
                        if (err){
                            reject(response.commonErrorMsg())
                        } else {
                            data.password = hash
                            console.log(hash)
                            userModel.updateOne(
                                {_id : ObjectId(id)},
                                {
                                        fullname : data.fullname,
                                        username : data.username,
                                        password : hash,
                                        phone : data.phone,
                                        address : data.address
                                }
                            ).then(userModel => {
                                let respMsg = Object.assign(response.commonSuccessMsg("SUKSES"));
                                respMsg["result"] = data;
                                resolve(respMsg);
                                }).catch(err => {
                                    reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.'))
                                })
                        }
                    })
    })

exports.login = (data) =>
    new Promise((resolve, reject) => {
        userModel.findOne({
            username : data.username
        }).then(user => {
            console.log(user)
            if (user){
                if (bcrypt.compareSync(data.password, user.password)){
                    resolve(response.commonResult(user))
                } else {
                    reject(response.commonErrorMsg('Opps... username atau password salah.'))
                }
            } else {
                reject(response.commonErrorMsg('Opps... username tidak ditemukan.'))
            }
        })
    })

exports.lihatData = (role) =>
    new Promise(async (resolve, reject) => {
        userModel.find({role : ('2')})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.lihatDetailData = (id) =>
    new Promise(async (resolve, reject) => {
        userModel.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })
