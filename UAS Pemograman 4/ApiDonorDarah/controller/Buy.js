const buyer = require('../model/Buy.js')
const response = require('../config/response')
const darah = require('../model/Darah.js')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId

exports.inputDataBuyer = (id, data) =>
    new Promise(async (resolve, reject) => {
        const buyerBaru = new buyer({
            id : ObjectId(id),
            fullnameBuyer : data.fullnameBuyer,
            type : data.type,
            doFor : data.doFor,
            stok : data.stok,
            qtyBuyer : data.qtyBuyer,
            price : data.price,
            sum : data.sum
        })

        buyerBaru.save()
            .then(r => {
                resolve(response.commonSuccessMsg('yeah... berhasil input data'))
            }).catch(err => {
                reject(response.commonErrorMsg('Opps... gagal input data'))
        })

    })

exports.lihatDataBuyer = () =>
    new Promise(async (resolve, reject) => {
        buyer.find({})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.updateBuyer = (id, data) =>
    new Promise(async (resolve, reject) => {
        buyer.updateOne(
            {_id : ObjectId(id)}, {
                fullnameBuyer : data.fullnameBuyer,
                type : data.type,
                doFor : data.doFor,
                stok : data.stok,
                qtyBuyer : data.qtyBuyer,
                price : data.price,
                sum : data.sum
            }
            ).then(buyer => {
            resolve(response.commonSuccessMsg('yeah... berhasil mengubah data.'))
        }).catch(err => {
            reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.'))
        })
    })

exports.lihatDetailDataBuyer = (id) =>
    new Promise(async (resolve, reject) => {
        buyer.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.buyBuyer = (id) =>
    new Promise(async (resolve, reject) => {
        buyer.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.addBuyer = (id) =>
    new Promise(async (resolve, reject) => {
        buyer.findOne({_id : ObjectId(id)})
            .then(result => {
                resolve(response.commonResult(result))
            })
            .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
    })

exports.beli = (data) =>
    new Promise(async (resolve, reject) => {
        const newStok = Number(data.stok) - Number(data.qtyBuyer)
        try {
            await buyer.create(data)
                .then(async result => {
                    await darah.updateOne({
                        _id: ObjectId(data.id)
                    },{
                        qty: newStok.toString()
                    })
                        .then(() => {
                            resolve(response.commonResult(result))
                        })
                })
                .catch(() => reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server.')))
        } catch (e) {
            console.log(e)
        }
    })

exports.deleteDataBuyer = (id) =>
    new Promise(async (resolve, reject) => {
        await buyer.remove({_id : ObjectId(id)})
            .then(() => {
                resolve(response.commonSuccessMsg('Yeah... berhasil menghapus data.'))
            }).catch(() => {
                reject(response.commonErrorMsg('Opps... terjadi kesalahan pada server'))
            })
    })
