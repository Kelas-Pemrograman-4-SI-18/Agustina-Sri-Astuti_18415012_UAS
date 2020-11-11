const darah = require('../model/Darah.js')
const response = require('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId

exports.inputDataDarah = (data) =>
    new Promise(async (resolve, reject) => {
        const darahBaru = new darah({
            name : data.name,
            qty : data.qty,
            harga : data.harga
        })

        darahBaru.save()
            .then(r => {
                resolve(response.commonSuccessMsg('yeah... berhasil input data'))
            }).catch(err => {
                reject(response.commonErrorMsg('Opps... gagal input data'))
        })
    })

exports.lihatDataDarah = () =>
    new Promise(async (resolve, reject) => {
        darah.find({})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.updateDarah = (id, data) =>
    new Promise(async (resolve, reject) => {
        darah.updateOne(
            {_id : ObjectId(id)}, {
                name : data.name,
                qty : data.qty,
                harga : data.harga
            }
            ).then(darah => {
            resolve(response.commonSuccessMsg('yeah... berhasil mengubah data.'))
        }).catch(err => {
            reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.'))
        })
    })

exports.lihatDetailDataDarah = (id) =>
    new Promise(async (resolve, reject) => {
        darah.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.buyDarah = (id) =>
    new Promise(async (resolve, reject) => {
        darah.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.addDarah = (id) =>
    new Promise(async (resolve, reject) => {
        darah.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.deleteDataDarah = (id) =>
    new Promise(async (resolve, reject) => {
        await darah.remove({_id : ObjectId(id)})
            .then(() => {
                resolve(response.commonSuccessMsg('Yeah... berhasil menghapus data.'))
            }).catch(() => {
                reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server'))
            })
    })
